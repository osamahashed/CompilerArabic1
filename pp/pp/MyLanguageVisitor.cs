using System;
using System.Collections.Generic;
using System.Text;
using Antlr4.Runtime.Tree;
using Krypton.Toolkit;
using System.Windows.Forms;

// MyLanguageVisitor for Visual Studio 2015 (C# 6.0)
// Interpreter that executes ArabicLang parse trees and writes output to a KryptonRichTextBox.
// Single-file implementation that includes a small Value helper internally.

public class MyLanguageVisitor : ArabicLangBaseVisitor<object>
{
    private KryptonRichTextBox outputBox;

    // Scopes stack: each scope is a dictionary of name -> object or VariableRef
    private Stack<Dictionary<string, object>> scopes = new Stack<Dictionary<string, object>>();

    // Procedures table: name -> proc_def context
    private Dictionary<string, ArabicLangParser.Proc_defContext> procs = new Dictionary<string, ArabicLangParser.Proc_defContext>(StringComparer.Ordinal);

    // Simple return signal
    private class ReturnSignal : Exception
    {
        public object Value;
        public ReturnSignal(object v) { Value = v; }
    }

    // Variable reference wrapper for pass-by-ref
    private class VariableRef
    {
        public Dictionary<string, object> Dict;
        public string Name;
        public VariableRef(Dictionary<string, object> dict, string name) { Dict = dict; Name = name; }
    }

    public MyLanguageVisitor(KryptonRichTextBox output)
    {
        this.outputBox = output;
        // push global scope
        scopes.Push(new Dictionary<string, object>(StringComparer.Ordinal));
    }

    #region Program / Block / Definitions
    public override object VisitProgram(ArabicLangParser.ProgramContext context)
    {
        // Visit block
        try
        {
            return Visit(context.block());
        }
        catch (ReturnSignal rs)
        {
            return rs.Value; // top-level return
        }
    }

    public override object VisitBlock(ArabicLangParser.BlockContext context)
    {
        // new inner scope
        scopes.Push(new Dictionary<string, object>(StringComparer.Ordinal));

        if (context.definitions_part() != null)
        {
            Visit(context.definitions_part());
        }

        if (context.stmtList() != null)
        {
            Visit(context.stmtList());
        }

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

    public override object VisitProcs_def_part(ArabicLangParser.Procs_def_partContext context)
    {
        foreach (var p in context.proc_def())
        {
            Visit(p);
        }
        return null;
    }

    public override object VisitProc_def(ArabicLangParser.Proc_defContext context)
    {
        string name = context.proc_header().NAME_ID().GetText();
        procs[name] = context;
        return null;
    }
    #endregion

    #region Variables definitions
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
        string typeName = context.data_type().GetText();
        var cur = scopes.Peek();
        foreach (var id in context.NAME_ID())
        {
            string varName = id.GetText();
            if (!cur.ContainsKey(varName)) cur[varName] = GetDefaultValue(typeName);
        }
        return null;
    }
    #endregion

    #region Statements
    public override object VisitStmtList(ArabicLangParser.StmtListContext context)
    {
        if (context.statement() == null) return null;
        var stmts = context.statement();
        for (int i = 0; i < stmts.Length; i++)
        {
            Visit(stmts[i]);
        }
        return null;
    }

    public override object VisitAssign_stmt(ArabicLangParser.Assign_stmtContext context)
    {
        string varName = context.var_access().NAME_ID().GetText();
        object value = Visit(context.expression());
        SetVariable(varName, value);
        return null;
    }

    public override object VisitInput_stmt(ArabicLangParser.Input_stmtContext context)
    {
        foreach (var va in context.var_access())
        {
            string name = va.NAME_ID().GetText();
            string prompt = string.Format("أدخل قيمة للمتغير {0}: ", name);
            AppendOutput(prompt);
            // For GUI mode we can't block on Console.ReadLine; instead read from a simple prompt dialog
            string line = PromptForInput(prompt);
            object parsed = TryParseLiteral(line);
            SetVariable(name, parsed);
        }
        return null;
    }

    public override object VisitOutput_stmt(ArabicLangParser.Output_stmtContext context)
    {
        var elements = context.print_list().print_element();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.Length; i++)
        {
            var el = elements[i];
            object v = Visit(el);
            if (v != null) sb.Append(v.ToString());
            if (i < elements.Length - 1) sb.Append(" ");
        }
        AppendOutput(sb.ToString());
        return null;
    }

    public override object VisitPrint_element(ArabicLangParser.Print_elementContext context)
    {
        if (context.STRING_LITERAL() != null)
        {
            return TrimStringLiteral(context.STRING_LITERAL().GetText());
        }
        else
        {
            string name = context.var_access().NAME_ID().GetText();
            object val = GetVariable(name);
            if (val == null) return string.Format("[غير معرّف: {0}]", name);
            return val;
        }
    }

    public override object VisitCall_stmt(ArabicLangParser.Call_stmtContext context)
    {
        string name = context.NAME_ID().GetText();
        if (!procs.ContainsKey(name)) throw new Exception("الإجراء '" + name + "' غير موجود.");

        var proc = procs[name];
        var header = proc.proc_header();

        // prepare callee scope
        var calleeScope = new Dictionary<string, object>(StringComparer.Ordinal);

        // collect formals
        var formalList = header.formal_params_list();
        var formalEntries = new List<KeyValuePair<bool, string>>(); // byRef,name
        if (formalList != null)
        {
            foreach (var fd in header.formal_params_list().param_def())
            {
                bool byRef = fd.KW_BY_REF() != null;
                foreach (var id in fd.vars_group().NAME_ID())
                {
                    formalEntries.Add(new KeyValuePair<bool, string>(byRef, id.GetText()));
                }
            }
        }

        // actuals
        var actualsCtx = context.actual_params_list();
        var actuals = new List<ArabicLangParser.Actual_paramContext>();
        if (actualsCtx != null)
        {
            foreach (var a in actualsCtx.actual_param()) actuals.Add(a);
        }

        for (int i = 0; i < formalEntries.Count; i++)
        {
            var pair = formalEntries[i];
            bool isRef = pair.Key;
            string fname = pair.Value;
            object actualVal = null;
            if (i < actuals.Count)
            {
                var a = actuals[i];
                if (a.var_access() != null)
                {
                    string callerVar = a.var_access().NAME_ID().GetText();
                    if (isRef)
                    {
                        var callerScope = FindScopeContaining(callerVar);
                        if (callerScope == null) throw new Exception("المتغير '" + callerVar + "' غير معرّف للتمرير بالمرجع.");
                        calleeScope[fname] = new VariableRef(callerScope, callerVar);
                        continue;
                    }
                    else
                    {
                        actualVal = GetVariable(callerVar);
                    }
                }
                else
                {
                    actualVal = Visit(a.expression());
                }
            }
            calleeScope[fname] = actualVal;
        }

        // push callee scope and execute
        scopes.Push(calleeScope);
        try
        {
            Visit(proc.proc_block());
        }
        catch (ReturnSignal rs)
        {
            scopes.Pop();
            return rs.Value;
        }
        scopes.Pop();
        return null;
    }

    public override object VisitConditional_stmt(ArabicLangParser.Conditional_stmtContext context)
    {
        object res = Visit(context.expression());
        bool cond = Convert.ToBoolean(res);
        if (cond)
        {
            Visit(context.statement(0));
        }
        else if (context.statement().Length > 1)
        {
            Visit(context.statement(1));
        }
        return null;
    }

    public override object VisitRepeat_for_stmt(ArabicLangParser.Repeat_for_stmtContext context)
    {
        var range = context.repetition_range();
        string varName = range.NAME_ID().GetText();
        int start = Convert.ToInt32(Visit(range.expression(0)));
        int end = Convert.ToInt32(Visit(range.expression(1)));
        int step = 1;
        if (range.expression().Length > 2) step = Convert.ToInt32(Visit(range.expression(2)));

        for (int v = start; v <= end; v += step)
        {
            SetVariable(varName, v);
            Visit(context.statement());
        }
        return null;
    }

    public override object VisitRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext context)
    {
        while (Convert.ToBoolean(Visit(context.expression())))
        {
            Visit(context.statement());
        }
        return null;
    }

    public override object VisitRepeat_until_stmt(ArabicLangParser.Repeat_until_stmtContext context)
    {
        do
        {
            Visit(context.statement());
        }
        while (!Convert.ToBoolean(Visit(context.expression())));
        return null;
    }

    public override object VisitStatement(ArabicLangParser.StatementContext context)
    {
        if (context.KW_RETURN() != null)
        {
            object val = null;
            if (context.expression() != null) val = Visit(context.expression());
            throw new ReturnSignal(val);
        }
        return base.VisitStatement(context);
    }
    #endregion

    #region Expressions
    public override object VisitPowerExpr(ArabicLangParser.PowerExprContext context)
    {
        double left = Convert.ToDouble(Visit(context.expression(0)));
        double right = Convert.ToDouble(Visit(context.expression(1)));
        return Math.Pow(left, right);
    }

    public override object VisitMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext context)
    {
        object left = Visit(context.expression(0));
        object right = Visit(context.expression(1));
        string op = context.GetChild(1).GetText();

        switch (op)
        {
            case "*": return Convert.ToDouble(left) * Convert.ToDouble(right);
            case "/": return Convert.ToDouble(left) / Convert.ToDouble(right);
            case "\\": return (int)Convert.ToDouble(left) / (int)Convert.ToDouble(right);
            case "%": return (int)Convert.ToDouble(left) % (int)Convert.ToDouble(right);
            case "&&": return Convert.ToBoolean(left) && Convert.ToBoolean(right);
        }
        return null;
    }

    public override object VisitAddSubOrExpr(ArabicLangParser.AddSubOrExprContext context)
    {
        object left = Visit(context.expression(0));
        object right = Visit(context.expression(1));
        string op = context.GetChild(1).GetText();

        switch (op)
        {
            case "+":
                if (left is string || right is string) return Convert.ToString(left) + Convert.ToString(right);
                return Convert.ToDouble(left) + Convert.ToDouble(right);
            case "-": return Convert.ToDouble(left) - Convert.ToDouble(right);
            case "||": return Convert.ToBoolean(left) || Convert.ToBoolean(right);
        }
        return null;
    }

    public override object VisitRelationalExpr(ArabicLangParser.RelationalExprContext context)
    {
        object left = Visit(context.expression(0));
        object right = Visit(context.expression(1));
        string op = context.GetChild(1).GetText();

        switch (op)
        {
            case "==": return Equals(left, right);
            case "!=": return !Equals(left, right);
            case "<": return Compare(left, right) < 0;
            case ">": return Compare(left, right) > 0;
            case "<=":
            case "=<": return Compare(left, right) <= 0;
            case ">=":
            case "=>": return Compare(left, right) >= 0;
        }
        return false;
    }

    public override object VisitNotExpr(ArabicLangParser.NotExprContext context)
    {
        return !Convert.ToBoolean(Visit(context.factor()));
    }

    public override object VisitUnaryPlus(ArabicLangParser.UnaryPlusContext context)
    {
        return Visit(context.factor());
    }

    public override object VisitUnaryMinus(ArabicLangParser.UnaryMinusContext context)
    {
        object v = Visit(context.factor());
        return -Convert.ToDouble(v);
    }

    public override object VisitAtomicFactor(ArabicLangParser.AtomicFactorContext context)
    {
        return Visit(context.factor());
    }

    public override object VisitFactor(ArabicLangParser.FactorContext context)
    {
        if (context.const_value() != null) return Visit(context.const_value());
        if (context.var_access() != null) return Visit(context.var_access());
        if (context.expression() != null) return Visit(context.expression());
        return null;
    }

    public override object VisitConst_value(ArabicLangParser.Const_valueContext context)
    {
        if (context.NUMERIC_LITERAL() != null)
        {
            string t = context.NUMERIC_LITERAL().GetText();
            if (t.Contains(".")) return double.Parse(t);
            return int.Parse(t);
        }
        if (context.BOOLEAN_LITERAL() != null)
        {
            return context.BOOLEAN_LITERAL().GetText() == "صح";
        }
        if (context.STRING_LITERAL() != null)
            return TrimStringLiteral(context.STRING_LITERAL().GetText());
        if (context.NAME_ID() != null)
        {
            return GetVariable(context.NAME_ID().GetText());
        }
        return null;
    }

    public override object VisitVar_access(ArabicLangParser.Var_accessContext context)
    {
        string name = context.NAME_ID().GetText();
        object val = GetVariableRaw(name);
        var vr = val as VariableRef;
        if (vr != null) return vr.Dict[vr.Name];
        return val;
    }
    #endregion

    #region Helpers
    private object GetDefaultValue(string typeName)
    {
        if (typeName == "صحيح") return 0;
        if (typeName == "حقيقي") return 0.0;
        if (typeName == "منطقي") return false;
        if (typeName == "حرفي") return string.Empty;
        if (typeName == "خيط رمزي") return string.Empty;
        return null;
    }

    private void SetVariable(string name, object value)
    {
        var scope = FindScopeContaining(name) ?? scopes.Peek();
        object raw = null;
        if (scope.ContainsKey(name)) raw = scope[name];
        var vr = raw as VariableRef;
        if (vr != null)
        {
            vr.Dict[vr.Name] = value;
        }
        else
        {
            scope[name] = value;
        }
    }

    private object GetVariable(string name)
    {
        object raw = GetVariableRaw(name);
        var vr = raw as VariableRef;
        if (vr != null) return vr.Dict[vr.Name];
        return raw;
    }

    private object GetVariableRaw(string name)
    {
        foreach (var s in scopes)
        {
            if (s.ContainsKey(name)) return s[name];
        }
        return null;
    }

    private Dictionary<string, object> FindScopeContaining(string name)
    {
        foreach (var s in scopes)
        {
            if (s.ContainsKey(name)) return s;
        }
        return null;
    }

    private static string TrimStringLiteral(string s)
    {
        if ((s.StartsWith("\"") && s.EndsWith("\"")) || (s.StartsWith("'") && s.EndsWith("'")))
            return s.Substring(1, s.Length - 2);
        return s;
    }

    private static object TryParseLiteral(string s)
    {
        int i;
        double d;
        if (int.TryParse(s, out i)) return i;
        if (double.TryParse(s, out d)) return d;
        if (s == "صح" || s.Equals("true", StringComparison.OrdinalIgnoreCase)) return true;
        if (s == "خطأ" || s.Equals("false", StringComparison.OrdinalIgnoreCase)) return false;
        return s;
    }

    private static int Compare(object a, object b)
    {
        if (a == null && b == null) return 0;
        if (a == null) return -1;
        if (b == null) return 1;
        var ac = a as IComparable;
        var bc = b as IComparable;
        if (ac != null && bc != null)
        {
            try
            {
                return ac.CompareTo(Convert.ChangeType(b, a.GetType()));
            }
            catch
            {
            }
        }
        return string.Compare(a.ToString(), b.ToString(), StringComparison.Ordinal);
    }

    private void AppendOutput(string s)
    {
        if (outputBox == null) return;
        // Append text in thread-safe way
        if (outputBox.InvokeRequired)
        {
            outputBox.Invoke(new Action<string>(AppendOutput), new object[] { s });
            return;
        }
        outputBox.AppendText(s + "\n");
    }

    private string PromptForInput(string prompt)
    {
        string result = null;
        if (outputBox != null && outputBox.InvokeRequired)
        {
            outputBox.Invoke(new Action<string>(AppendOutput), new object[] { prompt });
        }
        else
        {
            AppendOutput(prompt);
        }

        // show a simple dialog to get input
        using (var dlg = new Form())
        {
            dlg.Text = "إدخال";
            dlg.StartPosition = FormStartPosition.CenterParent;
            dlg.Width = 500;
            dlg.Height = 140;
            var tb = new System.Windows.Forms.TextBox();
            tb.Dock = DockStyle.Top;
            tb.Width = 460;
            tb.Font = new System.Drawing.Font("Segoe UI", 12);
            dlg.Controls.Add(tb);
            var ok = new System.Windows.Forms.Button();
            ok.Text = "موافق";
            ok.Dock = DockStyle.Bottom;
            ok.DialogResult = System.Windows.Forms.DialogResult.OK;
            dlg.Controls.Add(ok);
            dlg.AcceptButton = ok;
            if (dlg.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                result = tb.Text;
            }
        }
        return result ?? string.Empty;
    }
    #endregion
}
