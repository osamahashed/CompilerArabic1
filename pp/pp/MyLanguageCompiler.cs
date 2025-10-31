using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace pp
{
    using System;
    using System.IO;
    using System.Text;
    using Antlr4.Runtime.Tree;

    public class MyArabicCompiler : ArabicLangBaseVisitor<object>
    {
        private readonly StringBuilder _csCode = new StringBuilder();

        public string Compile(IParseTree tree)
        {
            _csCode.Clear();
            _csCode.AppendLine("using System;");
            _csCode.AppendLine("using System.Collections.Generic;");
            _csCode.AppendLine("class Program {");
            _csCode.AppendLine("    static void Main() {");

            Visit(tree); // المرور على كل أوامر اللغة

            _csCode.AppendLine("    }");
            _csCode.AppendLine("}");
            return _csCode.ToString();
        }

        // -----------------------------
        // تعليمات الطباعة والإدخال
        // -----------------------------
        public override object VisitOutput_stmt(ArabicLangParser.Output_stmtContext context)
        {
            var list = context.print_list().print_element();
            StringBuilder sb = new StringBuilder();

            foreach (var el in list)
            {
                if (el.STRING_LITERAL() != null)
                    sb.Append(el.STRING_LITERAL().GetText());
                else if (el.var_access() != null)
                    sb.Append(el.var_access().GetText());
                sb.Append(" + \" \" + ");
            }

            string expr = sb.ToString().TrimEnd(' ', '+');
            _csCode.AppendLine($"        Console.WriteLine({expr});");
            return null;
        }

        public override object VisitInput_stmt(ArabicLangParser.Input_stmtContext context)
        {
            foreach (var v in context.var_access())
            {
                string varName = v.GetText();
                _csCode.AppendLine($"        Console.Write(\"{varName}: \");");
                _csCode.AppendLine($"        var {varName} = Console.ReadLine();");
            }
            return null;
        }

        // -----------------------------
        // تعليمة الإسناد
        // -----------------------------
        public override object VisitAssign_stmt(ArabicLangParser.Assign_stmtContext context)
        {
            string name = context.var_access().GetText();
            string expr = Visit(context.expression())?.ToString() ?? "0";
            _csCode.AppendLine($"        {name} = {expr};");
            return null;
        }

        // -----------------------------
        // الثوابت
        // -----------------------------
        public override object VisitConst_value(ArabicLangParser.Const_valueContext context)
        {
            if (context.NUMERIC_LITERAL() != null)
                return context.NUMERIC_LITERAL().GetText();
            if (context.STRING_LITERAL() != null)
                return context.STRING_LITERAL().GetText();
            if (context.BOOLEAN_LITERAL() != null)
                return context.BOOLEAN_LITERAL().GetText() == "صح" ? "true" : "false";
            return context.GetText();
        }

        // -----------------------------
        // الجمل الشرطية
        // -----------------------------
        public override object VisitConditional_stmt(ArabicLangParser.Conditional_stmtContext context)
        {
            string condition = Visit(context.expression()).ToString();
            _csCode.AppendLine($"        if ({condition})");
            _csCode.AppendLine("        {");
            Visit(context.statement(0));
            _csCode.AppendLine("        }");
            if (context.statement().Length > 1)
            {
                _csCode.AppendLine("        else");
                _csCode.AppendLine("        {");
                Visit(context.statement(1));
                _csCode.AppendLine("        }");
            }
            return null;
        }

        // -----------------------------
        // تكرار (طالما)
        // -----------------------------
        public override object VisitRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext context)
        {
            string cond = Visit(context.expression()).ToString();
            _csCode.AppendLine($"        while ({cond})");
            _csCode.AppendLine("        {");
            Visit(context.statement());
            _csCode.AppendLine("        }");
            return null;
        }

        // -----------------------------
        // التعبيرات الحسابية والمنطقية
        // -----------------------------
        public override object VisitAddSubOrExpr(ArabicLangParser.AddSubOrExprContext context)
        {
            string left = Visit(context.expression(0)).ToString();
            string right = Visit(context.expression(1)).ToString();
            string op = context.OP_PLUS() != null ? "+" :
                        context.OP_MINUS() != null ? "-" : "||";
            return $"{left} {op} {right}";
        }

        public override object VisitMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext context)
        {
            string left = Visit(context.expression(0)).ToString();
            string right = Visit(context.expression(1)).ToString();
            string op = context.OP_MULT() != null ? "*" :
                        context.OP_DIV_REAL() != null ? "/" :
                        context.OP_MOD() != null ? "%" : "&&";
            return $"{left} {op} {right}";
        }

        public override object VisitRelationalExpr(ArabicLangParser.RelationalExprContext context)
        {
            string left = Visit(context.expression(0)).ToString();
            string right = Visit(context.expression(1)).ToString();
            string op = context.GetChild(1).GetText();
            return $"{left} {op} {right}";
        }

        public override object VisitAtomicFactor(ArabicLangParser.AtomicFactorContext context)
        {
            return Visit(context.factor());
        }

        public override object VisitFactor(ArabicLangParser.FactorContext context)
        {
            if (context.expression() != null)
                return Visit(context.expression());
            if (context.var_access() != null)
                return context.var_access().GetText();
            if (context.const_value() != null)
                return Visit(context.const_value());
            return null;
        }
    }

}
