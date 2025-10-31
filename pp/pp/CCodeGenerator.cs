using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Linq;
using Antlr4.Runtime.Tree;
using System.Text.RegularExpressions;

namespace pp
{


        // مولّد كود C من شجرة الـ ANTLR (ArabicLang)
        // يرجع نص C كامل (headers + functions + main)
        public class CCodeGenerator : ArabicLangBaseVisitor<string>
        {
            private StringBuilder _header = new StringBuilder();
            private StringBuilder _procs = new StringBuilder();
            private StringBuilder _mainBody = new StringBuilder();


            // مؤقت لتجميع أسطر الدالة أثناء زيارة بلوك الدالة
            private StringBuilder _currentFunctionBody = null;

            // جدول أنواع المتغيرات (اسم -> key) لاستخدام printf/scanf
            private Dictionary<string, string> _varTypes = new Dictionary<string, string>();

            // ------------------------
            // Helpers (واحدة فقط، لا تكرار)
            // ------------------------
            private void AppendLineToHeader(string line) => _header.AppendLine(line);
            private void AppendLineToProcs(string line) => _procs.AppendLine(line);
            private void AppendLineToMain(string line) => _mainBody.AppendLine("    " + line);
            private void AppendLineToCurrentFunction(string line)
            {
                if (_currentFunctionBody != null)
                    _currentFunctionBody.AppendLine("    " + line);
                else
                    AppendLineToMain(line);
            }

            // ------------------------
            // Entry: توليد كامل
            // ------------------------
            public string Generate(IParseTree tree)
            {
                _header.Clear();
                _procs.Clear();
                _mainBody.Clear();
                _varTypes.Clear();

                // ترويسات افتراضية
                AppendLineToHeader("#include <stdio.h>");
                AppendLineToHeader("#include <stdlib.h>");
                AppendLineToHeader("#include <stdbool.h>");
                AppendLineToHeader("#include <math.h>");
                AppendLineToHeader("#include <string.h>");
                AppendLineToHeader("");

                // زيارة الشجرة (Start)
                Visit(tree);

                // توليف الناتج النهائي
                var finalSb = new StringBuilder();
                finalSb.Append(_header.ToString());
                finalSb.AppendLine();
                finalSb.Append(_procs.ToString());
                finalSb.AppendLine();
            finalSb.AppendLine("int main(void)");
            finalSb.AppendLine("{");
            finalSb.Append(_mainBody.ToString());
            finalSb.AppendLine("    system(\"pause\");");
            finalSb.AppendLine("    return 0;");
            finalSb.AppendLine("}");

            return finalSb.ToString();
            }

            // ------------------------
            // VisitProgram -> عادة يزور block داخل grammar الذي أرسلتَه
            // ------------------------
            public override string VisitProgram(ArabicLangParser.ProgramContext context)
            {
                // grammar: program : KW_PROGRAM NAME_ID SEMICOLON block DOT ;
                // زيارة الـ block (سيجمع التعاريف وملف main)
                Visit(context.block());
                return "";
            }

            // ------------------------
            // Block : { (definitions_part)? stmtList }
            // ------------------------
            public override string VisitBlock(ArabicLangParser.BlockContext context)
            {
                // إذا فيه definitions (متغيرات/اجراءات) نزورها أولًا
                if (context.definitions_part() != null)
                    Visit(context.definitions_part());

                // ثم تعليمات الجسم — نجمعها في main (أو في دالة حالياً إذا كنا داخل دالة)
                if (context.stmtList() != null)
                    Visit(context.stmtList());

                return "";
            }

            // ------------------------
            // Definitions part
            // ------------------------
            public override string VisitDefinitions_part(ArabicLangParser.Definitions_partContext context)
            {
                foreach (var child in context.children)
                {
                    Visit(child);
                }
                return "";
            }

            // ------------------------
            // Procedures (proc_def)
            // ------------------------
            public override string VisitProcs_def_part(ArabicLangParser.Procs_def_partContext context)
            {
                foreach (var p in context.proc_def())
                    Visit(p);
                return "";
            }

            public override string VisitProc_def(ArabicLangParser.Proc_defContext context)
            {
                // اسم الإجراء
                string procName = context.proc_header().NAME_ID().GetText();

                // جمع المعاملات
                var paramList = new List<string>();
                var header = context.proc_header();
                if (header.formal_params_list() != null)
                {
                    foreach (var pd in header.formal_params_list().param_def())
                    {
                        bool byRef = pd.KW_BY_REF() != null;
                        var vg = pd.vars_group();
                        string typeName = vg.data_type().GetText();
                        string ctype = ConvertToCTypeSimple(typeName);
                        foreach (var id in vg.NAME_ID())
                        {
                            string name = id.GetText();
                            if (ctype == "string")
                            {
                                // نستخدم char name[] للمصفوفة
                                paramList.Add("char " + name + "[]");
                            }
                            else
                            {
                                if (byRef)
                                    paramList.Add(ctype + "* " + name);
                                else
                                    paramList.Add(ctype + " " + name);
                            }
                        }
                    }
                }

                // بناء التوقيع
                string signature = $"void {procName}({string.Join(", ", paramList)})";
                AppendLineToProcs(signature);
                AppendLineToProcs("{");

                // افتح مؤقت لجمع جسم الدالة
                _currentFunctionBody = new StringBuilder();

                // زيارة الـ proc_block (هو block)
                Visit(context.proc_block());

                // أدخل محتوى الدالة إلى _procs
                AppendLineToProcs(_currentFunctionBody.ToString().TrimEnd());
                _currentFunctionBody = null;

                AppendLineToProcs("}");
                AppendLineToProcs("");
                return "";
            }

            // ------------------------
            // Variables definitions
            // ------------------------
            public override string VisitVars_def_part(ArabicLangParser.Vars_def_partContext context)
            {
                foreach (var d in context.var_def())
                    Visit(d);
                return "";
            }

            public override string VisitVar_def(ArabicLangParser.Var_defContext context)
            {
                return Visit(context.vars_group());
            }

            public override string VisitVars_group(ArabicLangParser.Vars_groupContext context)
            {
                string typeName = context.data_type().GetText();
                string ctypeKey = MapTypeKey(typeName);
                string ctypeDecl = ConvertToCTypeForDecl(typeName);

                foreach (var id in context.NAME_ID())
                {
                    string name = id.GetText();
                    _varTypes[name] = ctypeKey;

                    if (ctypeKey == "string")
                    {
                        AppendLineToMain($"char {name}[256] = {{0}};");
                    }
                    else
                    {
                        AppendLineToMain($"{ctypeDecl} {name} = 0;");
                    }
                }
                return "";
            }

            // ------------------------
            // Statements
            // ------------------------
            public override string VisitStmtList(ArabicLangParser.StmtListContext context)
            {
                if (context.statement() == null) return "";
                foreach (var s in context.statement())
                    Visit(s);
                return "";
            }

            public override string VisitAssign_stmt(ArabicLangParser.Assign_stmtContext context)
            {
                string left = Visit(context.var_access());
                string right = Visit(context.expression());
                AppendLineToCurrentFunction($"{left} = {right};");
                return "";
            }

            public override string VisitInput_stmt(ArabicLangParser.Input_stmtContext context)
            {
                var vars = context.var_access();
                foreach (var v in vars)
                {
                    string name = v.NAME_ID().GetText();
                    string typeKey = _varTypes.ContainsKey(name) ? _varTypes[name] : "int";
                    if (typeKey == "string")
                    {
                        AppendLineToCurrentFunction($"printf(\"{name}: \");");
                        AppendLineToCurrentFunction($"scanf(\"%255s\", {name});");
                    }
                    else
                    {
                        string fmt = ScanfFormatForType(typeKey);
                        AppendLineToCurrentFunction($"printf(\"{name}: \");");
                        AppendLineToCurrentFunction($"scanf(\"{fmt}\", &{name});");
                    }
                }
                return "";
            }

            public override string VisitOutput_stmt(ArabicLangParser.Output_stmtContext context)
            {
                var elements = context.print_list().print_element();
                if (elements.Length == 0)
                {
                    AppendLineToCurrentFunction("printf(\"\\n\");");
                    return "";
                }

                var formats = new List<string>();
                var args = new List<string>();
                foreach (var el in elements)
                {
                    if (el.STRING_LITERAL() != null)
                    {
                        string txt = el.STRING_LITERAL().GetText();
                        formats.Add("%s");
                        args.Add(TrimStringLiteral(txt));
                    }
                    else if (el.var_access() != null)
                    {
                        string varName = el.var_access().NAME_ID().GetText();
                        string typeKey = _varTypes.ContainsKey(varName) ? _varTypes[varName] : "int";
                        formats.Add(PrintfFormatForType(typeKey));
                        args.Add(varName);
                    }
                    else
                    {
                        string expr = Visit(el);
                        formats.Add("%d");
                        args.Add(expr);
                    }
                }

                string formatStr = string.Join(" ", formats) + "\\n";
                if (args.Count == 0)
                    AppendLineToCurrentFunction($"printf(\"{formatStr}\");");
                else
                    AppendLineToCurrentFunction($"printf(\"{formatStr}\", {string.Join(", ", args)});");

                return "";
            }

            public override string VisitCall_stmt(ArabicLangParser.Call_stmtContext context)
            {
                string name = context.NAME_ID().GetText();
                var args = new List<string>();
                if (context.actual_params_list() != null)
                {
                    foreach (var a in context.actual_params_list().actual_param())
                    {
                        if (a.var_access() != null) args.Add(a.var_access().GetText());
                        else args.Add(Visit(a.expression()));
                    }
                }
                AppendLineToCurrentFunction($"{name}({string.Join(", ", args)});");
                return "";
            }

            public override string VisitConditional_stmt(ArabicLangParser.Conditional_stmtContext context)
            {
                string cond = Visit(context.expression());
                AppendLineToCurrentFunction($"if ({cond})");
                AppendLineToCurrentFunction("{");
                Visit(context.statement(0));
                AppendLineToCurrentFunction("}");

                if (context.statement().Length > 1)
                {
                    AppendLineToCurrentFunction("else");
                    AppendLineToCurrentFunction("{");
                    Visit(context.statement(1));
                    AppendLineToCurrentFunction("}");
                }
                return "";
            }

            public override string VisitRepeat_for_stmt(ArabicLangParser.Repeat_for_stmtContext context)
            {
                var rng = context.repetition_range();
                string varName = rng.NAME_ID().GetText();
                string startExpr = Visit(rng.expression(0));
                string endExpr = Visit(rng.expression(1));
                string stepExpr = "1";
                if (rng.expression().Length > 2) stepExpr = Visit(rng.expression(2));

                AppendLineToCurrentFunction($"for (int {varName} = {startExpr}; {varName} <= {endExpr}; {varName} += {stepExpr})");
                AppendLineToCurrentFunction("{");
                Visit(context.statement());
                AppendLineToCurrentFunction("}");
                return "";
            }

            public override string VisitRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext context)
            {
                string cond = Visit(context.expression());
                AppendLineToCurrentFunction($"while ({cond})");
                AppendLineToCurrentFunction("{");
                Visit(context.statement());
                AppendLineToCurrentFunction("}");
                return "";
            }

            public override string VisitRepeat_until_stmt(ArabicLangParser.Repeat_until_stmtContext context)
            {
                AppendLineToCurrentFunction("do");
                AppendLineToCurrentFunction("{");
                Visit(context.statement());
                AppendLineToCurrentFunction("} while (!(" + Visit(context.expression()) + "));");
                return "";
            }

            // ------------------------
            // Expressions -> تحويل إلى سلاسل نصية تمثل تعبير C
            // ------------------------
            public override string VisitPowerExpr(ArabicLangParser.PowerExprContext context)
            {
                string l = Visit(context.expression(0));
                string r = Visit(context.expression(1));
                return $"pow((double){l}, (double){r})";
            }

            public override string VisitMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext context)
            {
                string left = Visit(context.expression(0));
                string op = context.GetChild(1).GetText();
                string right = Visit(context.expression(1));

                if (op == "\\") return $"((int){left} / (int){right})";
                if (op == "%") return $"((int){left} % (int){right})";
                return $"({left} {op} {right})";
            }

            public override string VisitAddSubOrExpr(ArabicLangParser.AddSubOrExprContext context)
            {
                string left = Visit(context.expression(0));
                string op = context.GetChild(1).GetText();
                string right = Visit(context.expression(1));
                return $"({left} {op} {right})";
            }

            public override string VisitRelationalExpr(ArabicLangParser.RelationalExprContext context)
            {
                string left = Visit(context.expression(0));
                string op = context.GetChild(1).GetText();
                string right = Visit(context.expression(1));
                return $"({left} {op} {right})";
            }

            public override string VisitNotExpr(ArabicLangParser.NotExprContext context)
            {
                string f = Visit(context.factor());
                return $"(!{f})";
            }

            public override string VisitUnaryPlus(ArabicLangParser.UnaryPlusContext context) => Visit(context.factor());
            public override string VisitUnaryMinus(ArabicLangParser.UnaryMinusContext context) => $"(-{Visit(context.factor())})";
            public override string VisitAtomicFactor(ArabicLangParser.AtomicFactorContext context) => Visit(context.factor());

            public override string VisitFactor(ArabicLangParser.FactorContext context)
            {
                if (context.expression() != null) return "(" + Visit(context.expression()) + ")";
                if (context.var_access() != null) return Visit(context.var_access());
                if (context.const_value() != null) return Visit(context.const_value());
                return "0";
            }

            public override string VisitConst_value(ArabicLangParser.Const_valueContext context)
            {
                if (context.NUMERIC_LITERAL() != null) return context.NUMERIC_LITERAL().GetText();
                if (context.BOOLEAN_LITERAL() != null) return context.BOOLEAN_LITERAL().GetText() == "صح" ? "true" : "false";
                if (context.STRING_LITERAL() != null) return TrimStringLiteral(context.STRING_LITERAL().GetText());
                if (context.NAME_ID() != null) return context.NAME_ID().GetText();
                return "0";
            }

            public override string VisitVar_access(ArabicLangParser.Var_accessContext context)
            {
                string name = context.NAME_ID().GetText();
                if (context.selector() != null)
                {
                    if (context.selector().indexed_selector() != null)
                    {
                        string idx = Visit(context.selector().indexed_selector().expression());
                        return $"{name}[{idx}]";
                    }
                    if (context.selector().field_selector() != null)
                    {
                        string fld = context.selector().field_selector().NAME_ID().GetText();
                        return $"{name}.{fld}";
                    }
                }
                return name;
            }

            // ------------------------
            // Helpers type mapping
            // ------------------------
            private string ConvertToCTypeSimple(string dataType)
            {
                if (dataType == "صحيح") return "int";
                if (dataType == "حقيقي") return "double";
                if (dataType == "منطقي") return "bool";
                if (dataType == "حرفي") return "char";
                if (dataType == "خيط رمزي") return "string";
                return "int";
            }

            private string ConvertToCTypeForDecl(string dataType)
            {
                if (dataType == "صحيح") return "int";
                if (dataType == "حقيقي") return "double";
                if (dataType == "منطقي") return "bool";
                if (dataType == "حرفي") return "char";
                if (dataType == "خيط رمزي") return "char";
                return "int";
            }

            private string MapTypeKey(string dataType)
            {
                if (dataType == "صحيح") return "int";
                if (dataType == "حقيقي") return "float";
                if (dataType == "منطقي") return "bool";
                if (dataType == "حرفي") return "char";
                if (dataType == "خيط رمزي") return "string";
                return "int";
            }

            private string PrintfFormatForType(string key)
            {
                if (key == "int") return "%d";
                if (key == "float" || key == "double") return "%f";
                if (key == "char") return "%c";
                if (key == "bool") return "%d";
                if (key == "string") return "%s";
                return "%d";
            }

            private string ScanfFormatForType(string key)
            {
                if (key == "int") return "%d";
                if (key == "float" || key == "double") return "%lf";
                if (key == "char") return " %c";
                if (key == "string") return "%s";
                return "%d";
            }

            private static string TrimStringLiteral(string s)
            {
                if (s.Length >= 2 && ((s.StartsWith("\"") && s.EndsWith("\"")) || (s.StartsWith("'") && s.EndsWith("'"))))
                    return s;
                return "\"" + s + "\"";
            }

        // تحسين بسيط للكود المولّد (يمكن توسيعها لاحقاً)
        public string OptimizeCode(string rawCode)
        {
            if (string.IsNullOrEmpty(rawCode)) return rawCode;

            string optimized = rawCode;

            // 1) حذف الأسطر الفارغة المتكررة
            optimized = Regex.Replace(optimized, @"(\r?\n\s*\r?\n)+", "\n");

            // 2) constant folding بسيط: احسب تعابير عددية بسيطة ثابتة (مثال محدود)
            // يتعرّف على تعابير من الشكل: int a = NUMBER op NUMBER;
            optimized = Regex.Replace(optimized,
                @"(\bint\b\s+\w+\s*=\s*)(\d+)\s*([\+\-\*\/])\s*(\d+)\s*;",
                m =>
                {
                    try
                    {
                        long left = long.Parse(m.Groups[2].Value);
                        string op = m.Groups[3].Value;
                        long right = long.Parse(m.Groups[4].Value);
                        long res = op == "+" ? left + right
                                 : op == "-" ? left - right
                                 : op == "*" ? left * right
                                 : right != 0 ? left / right : left;
                        return $"{m.Groups[1].Value}{res};";
                    }
                    catch { return m.Value; }
                });

            // 3) إزالة عمليات لا فائدة منها: x = x + 0;  x = x * 1;
            optimized = Regex.Replace(optimized, @"(\b\w+\b)\s*=\s*\1\s*\+\s*0\s*;", "// removed no-op add");
            optimized = Regex.Replace(optimized, @"(\b\w+\b)\s*=\s*\1\s*\*\s*1\s*;", "// removed no-op mul");

            // 4) إزالة تعابير printf/scanf الفارغة المكررة (تنظيف)
            optimized = Regex.Replace(optimized, @"printf\(""\s*\\n""\);\s*", "");

            // 5) تقليم فراغات زائدة في السطور
            optimized = string.Join("\n", optimized.Split(new[] { '\n' }, StringSplitOptions.None).Select(s => s.TrimEnd()));

            return optimized;
        }

        // ------------------------
        // مرحلة توليد الكود النهائي بعد التحسين
        // ------------------------
        public string GenerateFinalCCode(string sourceCode, Antlr4.Runtime.Tree.IParseTree tree)
        {
            // 1) توليد الكود الخام
            string raw = Generate(tree);

            // 2) تحسين الكود الخام
            string optimized = OptimizeCode(raw);

            // 3) دمج النتيجة النهائية في شكل كامل جاهز للعرض أو الحفظ
            StringBuilder final = new StringBuilder();
            final.AppendLine("// -------------------------");
            final.AppendLine("// الكود النهائي المحسن بلغة C");
            final.AppendLine("// -------------------------");
            final.AppendLine(optimized);

            return final.ToString();
        }

    }
}
    


