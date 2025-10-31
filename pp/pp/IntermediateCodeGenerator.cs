using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Antlr4.Runtime.Tree;

namespace pp
{
    // مولّد كود وسيط (Three-Address Code)
    // يزور شجرة ANTLR وينتج قائمة تعليمات نصية (TAC).
    public class IntermediateCodeGenerator : ArabicLangBaseVisitor<string>
    {
        private readonly StringBuilder _code = new StringBuilder();
        private int _tempCounter = 0;
        private int _labelCounter = 0;

        // جمع التعليمات خطًا بخط
        private void Emit(string line)
        {
            _code.AppendLine(line);
        }

        private string NewTemp() => $"t{_tempCounter++}";
        private string NewLabel() => $"L{_labelCounter++}";

        // API العام
        public string Generate(IParseTree tree)
        {
            _code.Clear();
            _tempCounter = 0;
            _labelCounter = 0;

            // ترويسة بسيطة
            Emit("// --- Intermediate Three-Address Code ---");
            Visit(tree);

            return _code.ToString();
        }

        // program -> block
        public override string VisitProgram(ArabicLangParser.ProgramContext context)
        {
            Visit(context.block());
            return "";
        }

        public override string VisitBlock(ArabicLangParser.BlockContext context)
        {
            if (context.definitions_part() != null) Visit(context.definitions_part());
            if (context.stmtList() != null) Visit(context.stmtList());
            return "";
        }

        public override string VisitVars_group(ArabicLangParser.Vars_groupContext context)
        {
            // declarations - in TAC we may emit comments
            string rawType = context.data_type().GetText();
            foreach (var id in context.NAME_ID())
            {
                Emit($"// decl {id.GetText()} : {rawType}");
            }
            return "";
        }

        public override string VisitConst_def(ArabicLangParser.Const_defContext context)
        {
            string name = context.NAME_ID().GetText();
            var val = context.const_value().GetText();
            Emit($"// const {name} = {val}");
            return "";
        }

        public override string VisitStmtList(ArabicLangParser.StmtListContext context)
        {
            if (context.statement() == null) return "";
            foreach (var s in context.statement()) Visit(s);
            return "";
        }

        public override string VisitAssign_stmt(ArabicLangParser.Assign_stmtContext context)
        {
            // var_access ASSIGN expression
            string lhs = Visit(context.var_access()); // name or name[index]
            string rhsTemp = Visit(context.expression());
            // if expression returned a temp or literal, emit assignment
            Emit($"{lhs} = {rhsTemp}");
            return "";
        }

        public override string VisitVar_access(ArabicLangParser.Var_accessContext context)
        {
            string name = context.NAME_ID().GetText();
            if (context.selector() != null)
            {
                if (context.selector().indexed_selector() != null)
                {
                    string idx = Visit(context.selector().indexed_selector().expression());
                    string tmp = NewTemp();
                    Emit($"{tmp} = {name}[{idx}]  // indexed access");
                    return tmp;
                }
                if (context.selector().field_selector() != null)
                {
                    string fld = context.selector().field_selector().NAME_ID().GetText();
                    string tmp = NewTemp();
                    Emit($"{tmp} = {name}.{fld}  // field access");
                    return tmp;
                }
            }
            return name;
        }

        public override string VisitInput_stmt(ArabicLangParser.Input_stmtContext context)
        {
            foreach (var v in context.var_access())
            {
                string name = v.NAME_ID().GetText();
                Emit($"read {name}");
            }
            return "";
        }

        public override string VisitOutput_stmt(ArabicLangParser.Output_stmtContext context)
        {
            var els = context.print_list().print_element();
            var args = new List<string>();
            foreach (var el in els)
            {
                if (el.STRING_LITERAL() != null) args.Add(el.STRING_LITERAL().GetText());
                else
                {
                    string tmp = Visit(el);
                    args.Add(tmp);
                }
            }
            Emit($"print {string.Join(", ", args)}");
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
                    if (a.var_access() != null) args.Add(Visit(a.var_access()));
                    else args.Add(Visit(a.expression()));
                }
            }
            Emit($"call {name}({string.Join(", ", args)})");
            return "";
        }

        public override string VisitConditional_stmt(ArabicLangParser.Conditional_stmtContext context)
        {
            string cond = Visit(context.expression());
            string lElse = NewLabel();
            string lEnd = NewLabel();

            Emit($"ifFalse {cond} goto {lElse}");
            Visit(context.statement(0));
            Emit($"goto {lEnd}");
            Emit($"{lElse}:");
            if (context.statement().Length > 1)
            {
                Visit(context.statement(1));
            }
            Emit($"{lEnd}:");
            return "";
        }

        public override string VisitRepeat_for_stmt(ArabicLangParser.Repeat_for_stmtContext context)
        {
            var r = context.repetition_range();
            string varName = r.NAME_ID().GetText();
            string start = Visit(r.expression(0));
            string end = Visit(r.expression(1));
            string step = "1";
            if (r.expression().Length > 2) step = Visit(r.expression(2));

            string loopLabel = NewLabel();
            string endLabel = NewLabel();

            Emit($"{varName} = {start}");
            Emit($"{loopLabel}:");
            Emit($"if {varName} > {end} goto {endLabel}");
            Visit(context.statement());
            Emit($"{varName} = {varName} + {step}");
            Emit($"goto {loopLabel}");
            Emit($"{endLabel}:");
            return "";
        }

        public override string VisitRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext context)
        {
            string loopLabel = NewLabel();
            string endLabel = NewLabel();
            Emit($"{loopLabel}:");
            string cond = Visit(context.expression());
            Emit($"ifFalse {cond} goto {endLabel}");
            Visit(context.statement());
            Emit($"goto {loopLabel}");
            Emit($"{endLabel}:");
            return "";
        }

        public override string VisitRepeat_until_stmt(ArabicLangParser.Repeat_until_stmtContext context)
        {
            string loopLabel = NewLabel();
            Emit($"{loopLabel}:");
            Visit(context.statement());
            string cond = Visit(context.expression());
            Emit($"ifFalse {cond} goto {loopLabel}");
            return "";
        }

        // Expressions produce either a temp name or literal
        public override string VisitPowerExpr(ArabicLangParser.PowerExprContext context)
        {
            string l = Visit(context.expression(0));
            string r = Visit(context.expression(1));
            string t = NewTemp();
            Emit($"{t} = pow({l}, {r})");
            return t;
        }

        public override string VisitMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext context)
        {
            string left = Visit(context.expression(0));
            string right = Visit(context.expression(1));
            string op = context.GetChild(1).GetText();
            string tmp = NewTemp();
            if (op == "\\") Emit($"{tmp} = (int)({left} / {right})");
            else Emit($"{tmp} = {left} {op} {right}");
            return tmp;
        }

        public override string VisitAddSubOrExpr(ArabicLangParser.AddSubOrExprContext context)
        {
            string left = Visit(context.expression(0));
            string right = Visit(context.expression(1));
            string op = context.GetChild(1).GetText();
            string t = NewTemp();
            Emit($"{t} = {left} {op} {right}");
            return t;
        }

        public override string VisitRelationalExpr(ArabicLangParser.RelationalExprContext context)
        {
            string left = Visit(context.expression(0));
            string right = Visit(context.expression(1));
            string op = context.GetChild(1).GetText();
            string t = NewTemp();
            Emit($"{t} = {left} {op} {right}");
            return t;
        }

        public override string VisitNotExpr(ArabicLangParser.NotExprContext context)
        {
            string f = Visit(context.factor());
            string t = NewTemp();
            Emit($"{t} = !{f}");
            return t;
        }

        public override string VisitUnaryPlus(ArabicLangParser.UnaryPlusContext context) => Visit(context.factor());
        public override string VisitUnaryMinus(ArabicLangParser.UnaryMinusContext context)
        {
            string v = Visit(context.factor());
            string t = NewTemp();
            Emit($"{t} = -{v}");
            return t;
        }

        public override string VisitAtomicFactor(ArabicLangParser.AtomicFactorContext context)
        {
            return Visit(context.factor());
        }

        public override string VisitFactor(ArabicLangParser.FactorContext context)
        {
            if (context.expression() != null) return Visit(context.expression());
            if (context.var_access() != null) return Visit(context.var_access());
            if (context.const_value() != null) return Visit(context.const_value());
            return "0";
        }

        public override string VisitConst_value(ArabicLangParser.Const_valueContext context)
        {
            if (context.NUMERIC_LITERAL() != null) return context.NUMERIC_LITERAL().GetText();
            if (context.BOOLEAN_LITERAL() != null) return context.BOOLEAN_LITERAL().GetText() == "صح" ? "1" : "0";
            if (context.STRING_LITERAL() != null) return context.STRING_LITERAL().GetText();
            if (context.NAME_ID() != null) return context.NAME_ID().GetText(); // could be const name
            return "0";
        }
    }
}
