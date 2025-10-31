using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Antlr4.Runtime.Tree;
using Krypton.Toolkit;

namespace pp
{
    // تحليل دلالي متقدّم: نطاقات، جداول رموز، فحص أنواع، فحص معاملات الإجراءات، ثوابت.
    public class MySemanticAnalyzer : ArabicLangBaseVisitor<object>
    {
        private readonly KryptonRichTextBox _output;
        private readonly StringBuilder _report = new StringBuilder();

        public string Report => _report.ToString();

        // Stack of scopes: each scope maps name -> Symbol
        private readonly Stack<Dictionary<string, Symbol>> scopes = new Stack<Dictionary<string, Symbol>>();

        // Procedures table: name -> ProcSymbol
        private readonly Dictionary<string, ProcSymbol> procs = new Dictionary<string, ProcSymbol>(StringComparer.Ordinal);

        // Collected errors/warnings
        private readonly List<string> errors = new List<string>();
        private readonly List<string> warnings = new List<string>();

        public MySemanticAnalyzer(KryptonRichTextBox output)
        {
            _output = output;
            // push global scope
            scopes.Push(new Dictionary<string, Symbol>(StringComparer.Ordinal));
        }

        #region Symbol classes
        private class Symbol
        {
            public string Name;
            public string Type; // canonical type name: int, double, bool, char, string, custom
            public bool IsConst;
            public object ConstValue;
            public Symbol(string name, string type, bool isConst = false, object constValue = null)
            {
                Name = name; Type = type; IsConst = isConst; ConstValue = constValue;
            }
        }

        private class ProcSymbol
        {
            public string Name;
            public List<ParamInfo> Params = new List<ParamInfo>();
            public ArabicLangParser.Proc_defContext Context;
            public ProcSymbol(string name, ArabicLangParser.Proc_defContext ctx) { Name = name; Context = ctx; }
        }

        private class ParamInfo
        {
            public string Name;
            public string Type;
            public bool ByRef;
            public ParamInfo(string name, string type, bool byRef) { Name = name; Type = type; ByRef = byRef; }
        }
        #endregion

        #region Utilities
        private void AddError(string msg)
        {
            errors.Add(msg);
            _output?.AppendText("[خطأ دلالي] " + msg + "\n");
        }
        private void AddWarning(string msg)
        {
            warnings.Add(msg);
            _output?.AppendText("[تنبيه] " + msg + "\n");
        }

        private string CanonicalType(string raw)
        {
            if (string.IsNullOrEmpty(raw)) return null;
            raw = raw.Trim();
            // map Arabic keywords or custom names to simple canonical types
            if (raw == "صحيح") return "int";
            if (raw == "حقيقي") return "double";
            if (raw == "منطقي") return "bool";
            if (raw == "حرفي") return "char";
            if (raw == "خيط رمزي" || raw == "خيط_رمزي" || raw == "String") return "string";
            // if it's already a simple token like int/double (unlikely) return as-is
            return raw; // custom type names will be returned as-is
        }

        private Symbol LookupSymbol(string name)
        {
            foreach (var s in scopes)
            {
                if (s.ContainsKey(name)) return s[name];
            }
            return null;
        }

        private Dictionary<string, Symbol> CurrentScope()
        {
            return scopes.Peek();
        }
        #endregion

        #region Top-level / Scopes
        public override object VisitProgram(ArabicLangParser.ProgramContext context)
        {
            // program : KW_PROGRAM NAME_ID SEMICOLON block DOT
            // push global scope already done in ctor
            Visit(context.block());
            // finish
            FinishReport();
            return null;
        }

        public override object VisitBlock(ArabicLangParser.BlockContext context)
        {
            // new inner scope for block (but keep global if top-level)
            scopes.Push(new Dictionary<string, Symbol>(StringComparer.Ordinal));

            if (context.definitions_part() != null)
                Visit(context.definitions_part());

            if (context.stmtList() != null)
                Visit(context.stmtList());

            scopes.Pop();
            return null;
        }

        public override object VisitDefinitions_part(ArabicLangParser.Definitions_partContext context)
        {
            foreach (var ch in context.children)
            {
                Visit(ch);
            }
            return null;
        }
        #endregion

        #region Constants / Types / Vars
        public override object VisitConsts_def_part(ArabicLangParser.Consts_def_partContext context)
        {
            // KW_CONST (const_def)+
            foreach (var def in context.const_def())
            {
                Visit(def);
            }
            return null;
        }

        public override object VisitConst_def(ArabicLangParser.Const_defContext context)
        {
            // NAME_ID ASSIGN const_value SEMICOLON
            string name = context.NAME_ID().GetText();
            var valCtx = context.const_value();
            object val = null;
            string type = null;
            if (valCtx != null)
            {
                val = Visit(valCtx);
                if (val != null)
                {
                    // determine type
                    if (val is int) type = "int";
                    else if (val is double) type = "double";
                    else if (val is bool) type = "bool";
                    else type = "string";
                }
            }

            var cur = CurrentScope();
            if (cur.ContainsKey(name))
            {
                AddError($"الثابت '{name}' معرف مسبقًا في نفس النطاق.");
            }
            else
            {
                cur[name] = new Symbol(name, type ?? "int", isConst: true, constValue: val);
            }
            return null;
        }

        public override object VisitTypes_def_part(ArabicLangParser.Types_def_partContext context)
        {
            // for simplicity: accept user types but we won't deeply check record/list internals here
            foreach (var td in context.type_def())
            {
                Visit(td);
            }
            return null;
        }

        public override object VisitType_def(ArabicLangParser.Type_defContext context)
        {
            // NAME_ID ASSIGN complex_type SEMICOLON
            string name = context.NAME_ID().GetText();
            // register as custom type name (no details stored but used to avoid false "unknown type" errors)
            var cur = CurrentScope();
            if (cur.ContainsKey(name))
            {
                AddError($"النوع '{name}' معرف مسبقًا في نفس النطاق.");
            }
            else
            {
                cur[name] = new Symbol(name, "type", isConst: true);
            }
            return null;
        }

        public override object VisitVars_def_part(ArabicLangParser.Vars_def_partContext context)
        {
            foreach (var d in context.var_def()) Visit(d);
            return null;
        }

        public override object VisitVar_def(ArabicLangParser.Var_defContext context)
        {
            return Visit(context.vars_group());
        }

        public override object VisitVars_group(ArabicLangParser.Vars_groupContext context)
        {
            // NAME_ID (COMMA NAME_ID)* COLON data_type
            string rawType = context.data_type().GetText();
            string type = CanonicalType(rawType);
            var cur = CurrentScope();
            foreach (var id in context.NAME_ID())
            {
                string name = id.GetText();
                if (cur.ContainsKey(name))
                {
                    AddError($"المتغير '{name}' معرف مسبقًا في نفس النطاق.");
                }
                else
                {
                    cur[name] = new Symbol(name, type ?? "int", isConst: false);
                }
            }
            return null;
        }
        #endregion

        #region Procedures
        public override object VisitProcs_def_part(ArabicLangParser.Procs_def_partContext context)
        {
            foreach (var p in context.proc_def()) Visit(p);
            return null;
        }

        public override object VisitProc_def(ArabicLangParser.Proc_defContext context)
        {
            // proc_def : proc_header proc_block SEMICOLON
            var header = context.proc_header();
            string procName = header.NAME_ID().GetText();

            if (procs.ContainsKey(procName))
            {
                AddError($"الإجراء '{procName}' معرف مسبقًا.");
                return null;
            }

            var ps = new ProcSymbol(procName, context);
            // parse formal params
            var fpl = header.formal_params_list();
            if (fpl != null)
            {
                foreach (var pd in fpl.param_def())
                {
                    bool byRef = pd.KW_BY_REF() != null;
                    var vg = pd.vars_group();
                    string rawType = vg.data_type().GetText();
                    string ctype = CanonicalType(rawType);
                    foreach (var id in vg.NAME_ID())
                    {
                        ps.Params.Add(new ParamInfo(id.GetText(), ctype ?? "int", byRef));
                    }
                }
            }

            procs[procName] = ps;

            // we DO NOT visit body now — we defer visiting proc_block inside call or we can visit for checking
            // but we'll perform semantic checks of the body now inside a new scope to register local vars & check statements
            // create new scope, push it and fill with params as local symbols
            scopes.Push(new Dictionary<string, Symbol>(StringComparer.Ordinal));
            var cur = CurrentScope();
            foreach (var pinfo in ps.Params)
            {
                // param names are local symbols
                cur[pinfo.Name] = new Symbol(pinfo.Name, pinfo.Type, isConst: false);
            }
            // visit block to analyze body
            Visit(context.proc_block());
            scopes.Pop();

            return null;
        }
        #endregion

        #region Statements & Calls
        public override object VisitStmtList(ArabicLangParser.StmtListContext context)
        {
            if (context.statement() == null) return null;
            foreach (var s in context.statement())
                Visit(s);
            return null;
        }

        public override object VisitAssign_stmt(ArabicLangParser.Assign_stmtContext context)
        {
            // var_access ASSIGN expression
            string varName = context.var_access().NAME_ID().GetText();
            var sym = LookupSymbol(varName);
            if (sym == null)
            {
                AddError($"المتغير '{varName}' لم يُعرّف قبل الاستخدام في إسناد.");
            }
            else if (sym.IsConst)
            {
                AddError($"المتغير '{varName}' ثابت ولا يمكن إسناد قيمة له.");
            }

            var rhsVal = Visit(context.expression());
            // If RHS produced a typed-value, and LHS has type, check compatibility
            string rhsType = rhsVal as string; // we use Visit(expression) to return type token (see below)
            if (sym != null && sym.Type != null && rhsType != null)
            {
                // simple compatibility: allow int->double promotion
                if (!TypesCompatible(sym.Type, rhsType))
                {
                    AddError($"عدم توافق الأنواع في الإسناد: المتغير '{varName}' من نوع '{sym.Type}' لكن التعبير من نوع '{rhsType}'.");
                }
            }
            return null;
        }

        public override object VisitInput_stmt(ArabicLangParser.Input_stmtContext context)
        {
            // READ (var_access (, var_access)*) 
            foreach (var va in context.var_access())
            {
                string name = va.NAME_ID().GetText();
                var sym = LookupSymbol(name);
                if (sym == null)
                {
                    AddError($"المتغير '{name}' غير معرف لاستخدامه في READ.");
                }
            }
            return null;
        }

        public override object VisitOutput_stmt(ArabicLangParser.Output_stmtContext context)
        {
            // PRINT (print_list)
            // check variables used in print
            foreach (var el in context.print_list().print_element())
            {
                if (el.var_access() != null)
                {
                    string name = el.var_access().NAME_ID().GetText();
                    var sym = LookupSymbol(name);
                    if (sym == null)
                    {
                        AddWarning($"طباعة متغير غير معرف: '{name}'. سيمثل كـ [غير معرف].");
                    }
                }
            }
            return null;
        }

        public override object VisitCall_stmt(ArabicLangParser.Call_stmtContext context)
        {
            string name = context.NAME_ID().GetText();
            if (!procs.ContainsKey(name))
            {
                AddError($"استدعاء لإجراء غير موجود: '{name}'.");
                return null;
            }
            var ps = procs[name];
            var actualsCtx = context.actual_params_list();
            int actualCount = actualsCtx == null ? 0 : actualsCtx.actual_param().Length;
            if (actualCount != ps.Params.Count)
            {
                AddError($"عدد معاملات الاستدعاء للإجراء '{name}' مختلف: متوقع {ps.Params.Count} لكن عُطي {actualCount}.");
            }
            else
            {
                // for each param check by-ref vs by-value when actual is var_access
                if (actualsCtx != null)
                {
                    var actuals = actualsCtx.actual_param();
                    for (int i = 0; i < actuals.Length && i < ps.Params.Count; i++)
                    {
                        var ai = actuals[i];
                        var pi = ps.Params[i];
                        if (pi.ByRef)
                        {
                            if (ai.var_access() == null)
                            {
                                AddError($"الوسيط رقم {i + 1} للإجراء '{name}' متوقع أن يكون مرجعًا (بالمرجع) لكن أعطيت تعبيرًا.");
                            }
                            else
                            {
                                // ensure actual var exists
                                string actualName = ai.var_access().NAME_ID().GetText();
                                var symActual = LookupSymbol(actualName);
                                if (symActual == null)
                                {
                                    AddError($"الوسيط بالمرجع '{actualName}' غير معرف عند استدعاء '{name}'.");
                                }
                                else
                                {
                                    // type check
                                    if (!TypesCompatible(pi.Type, symActual.Type))
                                    {
                                        AddError($"نوع الوسيط بالمرجع {actualName} لا يتوافق مع نوع المعامل في التعريف للإجراء '{name}'.");
                                    }
                                }
                            }
                        }
                        else
                        {
                            // by-value: if expression present, we can try to infer its type via Visit(expression) returning type string
                            if (ai.expression() != null)
                            {
                                var exprType = Visit(ai.expression()) as string;
                                if (exprType != null && !TypesCompatible(pi.Type, exprType))
                                {
                                    AddError($"نوع الوسيط رقم {i + 1} للاستدعاء '{name}' لا يتوافق مع تعريف الإجراء.");
                                }
                            }
                            else if (ai.var_access() != null)
                            {
                                string actualName = ai.var_access().NAME_ID().GetText();
                                var symActual = LookupSymbol(actualName);
                                if (symActual == null)
                                {
                                    AddError($"الوسيط '{actualName}' غير معرف عند استدعاء '{name}'.");
                                }
                                else if (!TypesCompatible(pi.Type, symActual.Type))
                                {
                                    AddError($"نوع الوسيط '{actualName}' لا يتوافق مع نوع المعامل في تعريف الإجراء '{name}'.");
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
        #endregion

        #region Control flow: if / loops / return
        public override object VisitConditional_stmt(ArabicLangParser.Conditional_stmtContext context)
        {
            var condType = Visit(context.expression()) as string;
            if (condType != null && condType != "bool")
            {
                AddWarning($"شرط if ليس منطقيًا (نوع: {condType}). سيتم تحويله إلى boolean إذا أمكن.");
            }
            Visit(context.statement(0));
            if (context.statement().Length > 1) Visit(context.statement(1));
            return null;
        }

        public override object VisitRepeat_for_stmt(ArabicLangParser.Repeat_for_stmtContext context)
        {
            // check loop variable exists or create it in current scope (Pascal-like)
            string varName = context.repetition_range().NAME_ID().GetText();
            var sym = LookupSymbol(varName);
            if (sym == null)
            {
                // declare implicitly in current scope as int
                CurrentScope()[varName] = new Symbol(varName, "int", false);
                AddWarning($"تم تعريف متغير الحلقة '{varName}' ضمنيًا كـ int.");
            }
            // check expressions types numeric
            Visit(context.repetition_range().expression(0));
            Visit(context.repetition_range().expression(1));
            if (context.repetition_range().expression().Length > 2) Visit(context.repetition_range().expression(2));
            Visit(context.statement());
            return null;
        }

        public override object VisitRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext context)
        {
            Visit(context.expression());
            Visit(context.statement());
            return null;
        }

        public override object VisitRepeat_until_stmt(ArabicLangParser.Repeat_until_stmtContext context)
        {
            Visit(context.statement());
            Visit(context.expression());
            return null;
        }

        public override object VisitStatement(ArabicLangParser.StatementContext context)
        {
            if (context.KW_RETURN() != null)
            {
                if (context.expression() != null)
                {
                    Visit(context.expression());
                }
                // return allowed anywhere inside procedure - check could be added to ensure inside proc
            }
            return base.VisitStatement(context);
        }
        #endregion

        #region Expressions type inference
        // We return a string representing the type of the expression when possible
        public override object VisitPowerExpr(ArabicLangParser.PowerExprContext context)
        {
            var lt = Visit(context.expression(0)) as string;
            var rt = Visit(context.expression(1)) as string;
            // power yields double if any operand double
            if (lt == "double" || rt == "double") return "double";
            return "int";
        }

        public override object VisitMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext context)
        {
            var lt = Visit(context.expression(0)) as string;
            var rt = Visit(context.expression(1)) as string;
            string op = context.GetChild(1).GetText();
            if (op == "&&") return "bool";
            if (op == "\\" || op == "%") return "int";
            if (lt == "double" || rt == "double" || op == "/") return "double";
            return "int";
        }

        public override object VisitAddSubOrExpr(ArabicLangParser.AddSubOrExprContext context)
        {
            var lt = Visit(context.expression(0)) as string;
            var rt = Visit(context.expression(1)) as string;
            string op = context.GetChild(1).GetText();
            if (op == "||") return "bool";
            if (lt == "string" || rt == "string") return "string";
            if (lt == "double" || rt == "double") return "double";
            return "int";
        }

        public override object VisitRelationalExpr(ArabicLangParser.RelationalExprContext context)
        {
            // relational always bool (we don't deeply check operand types here)
            Visit(context.expression(0));
            Visit(context.expression(1));
            return "bool";
        }

        public override object VisitNotExpr(ArabicLangParser.NotExprContext context)
        {
            Visit(context.factor());
            return "bool";
        }

        public override object VisitUnaryPlus(ArabicLangParser.UnaryPlusContext context)
        {
            return Visit(context.factor());
        }

        public override object VisitUnaryMinus(ArabicLangParser.UnaryMinusContext context)
        {
            return Visit(context.factor());
        }

        public override object VisitAtomicFactor(ArabicLangParser.AtomicFactorContext context)
        {
            return Visit(context.factor());
        }

        public override object VisitFactor(ArabicLangParser.FactorContext context)
        {
            if (context.expression() != null) return Visit(context.expression());
            if (context.const_value() != null) return Visit(context.const_value());
            if (context.var_access() != null) return Visit(context.var_access());
            return null;
        }

        public override object VisitConst_value(ArabicLangParser.Const_valueContext context)
        {
            if (context.NUMERIC_LITERAL() != null)
            {
                string t = context.NUMERIC_LITERAL().GetText();
                if (t.Contains(".")) return "double";
                return "int";
            }
            if (context.BOOLEAN_LITERAL() != null) return "bool";
            if (context.STRING_LITERAL() != null) return "string";
            if (context.NAME_ID() != null)
            {
                string nm = context.NAME_ID().GetText();
                var sym = LookupSymbol(nm);
                if (sym == null)
                {
                    AddError($"ثابت/متغير '{nm}' المستخدم في قيمة ثابتة غير معرف.");
                    return null;
                }
                return sym.Type;
            }
            return null;
        }

        public override object VisitVar_access(ArabicLangParser.Var_accessContext context)
        {
            string name = context.NAME_ID().GetText();
            var sym = LookupSymbol(name);
            if (sym == null)
            {
                // don't spam same error repeatedly; add once
                AddError($"المتغير '{name}' غير معرف عند الوصول إليه.");
                return null;
            }
            // if selector (index/field) you could refine type info here
            return sym.Type;
        }
        #endregion

        #region Helpers types compatibility & final report
        private bool TypesCompatible(string target, string source)
        {
            if (target == null || source == null) return true; // be lenient if unknown
            if (target == source) return true;
            if (target == "double" && source == "int") return true; // promotion
            // bool and int: disallow (but could allow 0/1)
            return false;
        }

        private void FinishReport()
        {
            _report.Clear();
            _report.AppendLine("--- تقرير التحليل الدلالي ---");
            if (errors.Count == 0 && warnings.Count == 0)
            {
                _report.AppendLine("لا أخطاء دلالية. ✅");
            }
            else
            {
                if (errors.Count > 0)
                {
                    _report.AppendLine($"الأخطاء ({errors.Count}):");
                    foreach (var e in errors) _report.AppendLine(" - " + e);
                }
                if (warnings.Count > 0)
                {
                    _report.AppendLine($"التحذيرات ({warnings.Count}):");
                    foreach (var w in warnings) _report.AppendLine(" - " + w);
                }
            }

            // طباعة النهاية إلى الـ outputBox أيضاً
            _output?.AppendText("\n" + _report.ToString());
        }
        #endregion
    }
}
