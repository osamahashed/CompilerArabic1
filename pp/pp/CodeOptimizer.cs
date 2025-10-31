using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace pp
{
    public class CodeOptimizer
    {
        public string Optimize(string intermediateCode)
        {
            if (string.IsNullOrWhiteSpace(intermediateCode))
                return "// ⚠️ لا يوجد كود لتحسينه.";

            var lines = intermediateCode
                .Split(new[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries);

            var optimized = new List<string>();
            var constants = new Dictionary<string, string>();
            var lastAssignments = new Dictionary<string, string>();
            var assignedVars = new HashSet<string>();
            var usedVars = new HashSet<string>();

            // 🔹 المرحلة 1: التحليل الأولي وجمع المعلومات
            foreach (var raw in lines)
            {
                string line = raw.Trim();
                if (string.IsNullOrWhiteSpace(line) || line.StartsWith("//"))
                    continue;

                // نمط: x = expr;
                var match = Regex.Match(line, @"^(\w+)\s*=\s*(.+)$");
                if (match.Success)
                {
                    string varName = match.Groups[1].Value.Trim();
                    string expr = match.Groups[2].Value.Trim();

                    assignedVars.Add(varName);

                    // تتبع المتغيرات المستخدمة في التعبير
                    foreach (Match m in Regex.Matches(expr, @"\b[a-zA-Z_]\w*\b"))
                        if (m.Value != varName)
                            usedVars.Add(m.Value);
                }
            }

            // 🔹 المرحلة 2: تحسينات التعبيرات وتبسيطها
            foreach (var raw in lines)
            {
                string line = raw.Trim();
                if (string.IsNullOrWhiteSpace(line) || line.StartsWith("//"))
                    continue;

                var match = Regex.Match(line, @"^(\w+)\s*=\s*(.+)$");
                if (!match.Success)
                {
                    optimized.Add(line);
                    continue;
                }

                string varName = match.Groups[1].Value;
                string expr = match.Groups[2].Value.Trim();

                // ✅ 1. Constant Folding (حساب القيم الثابتة)
                expr = EvaluateConstants(expr);

                // ✅ 2. Strength Reduction (تحويل الضرب والقسمة)
                expr = Regex.Replace(expr, @"(\w+)\s*\*\s*2\b", m => $"{m.Groups[1].Value} + {m.Groups[1].Value}");
                expr = Regex.Replace(expr, @"2\s*\*\s*(\w+)", m => $"{m.Groups[1].Value} + {m.Groups[1].Value}");
                expr = Regex.Replace(expr, @"(\w+)\s*\/\s*2\b", m => $"({m.Groups[1].Value} >> 1)");

                // ✅ 3. Constant Propagation (نشر الثوابت)
                foreach (var kv in constants)
                    expr = Regex.Replace(expr, $@"\b{kv.Key}\b", kv.Value);

                // ✅ 4. Copy Propagation (إزالة النسخ الزائدة)
                if (Regex.IsMatch(expr, @"^\w+$") && constants.ContainsKey(expr))
                    expr = constants[expr];

                // ✅ 5. Common Subexpression Elimination
                if (lastAssignments.ContainsValue(expr))
                {
                    foreach (var kv in lastAssignments)
                    {
                        if (kv.Value == expr)
                        {
                            expr = kv.Key;
                            break;
                        }
                    }
                }

                // ✅ 6. Dead Code Elimination (إزالة الأكواد الميتة)
                if (!usedVars.Contains(varName) && !IsOutputVariable(varName))
                    continue;

                // ✅ 7. Constant Table Update
                if (Regex.IsMatch(expr, @"^\d+$"))
                    constants[varName] = expr;
                else
                    constants.Remove(varName);

                // ✅ 8. إزالة التكرار (x = x)
                if (expr == varName)
                    continue;

                lastAssignments[varName] = expr;
                optimized.Add($"{varName} = {expr}");
            }

            // 🔹 المرحلة 3: إزالة المتغيرات غير المستخدمة فعليًا
            var finalLines = new List<string>();
            foreach (var line in optimized)
            {
                var match = Regex.Match(line, @"^(\w+)\s*=");
                if (match.Success)
                {
                    var varName = match.Groups[1].Value;
                    if (usedVars.Contains(varName) || IsOutputVariable(varName))
                        finalLines.Add(line);
                }
                else
                {
                    finalLines.Add(line);
                }
            }

            // 🔹 إخراج منسّق
            var sb = new StringBuilder();
            sb.AppendLine("// ✅ الكود بعد التحسينات المتقدمة:\n");
            foreach (var l in finalLines)
                sb.AppendLine(l);

            return sb.ToString();
        }

        // ⚙️ دالة تقييم العمليات الحسابية البسيطة
        private string EvaluateConstants(string expr)
        {
            try
            {
                bool changed;
                do
                {
                    changed = false;
                    expr = Regex.Replace(expr, @"(\d+)\s*([\+\-\*\/])\s*(\d+)", m =>
                    {
                        int a = int.Parse(m.Groups[1].Value);
                        int b = int.Parse(m.Groups[3].Value);
                        string op = m.Groups[2].Value;
                        int result = 0;

                        if (op == "+") result = a + b;
                        else if (op == "-") result = a - b;
                        else if (op == "*") result = a * b;
                        else if (op == "/") result = b != 0 ? a / b : 0;

                        changed = true;
                        return result.ToString();
                    });
                } while (changed);
            }
            catch { }

            return expr;
        }

        // ⚙️ يعتبر بعض المتغيرات "خارجة" فلا تُحذف (مثل نتائج نهائية أو طباعة)
        private bool IsOutputVariable(string varName)
        {
            return varName.Equals("result", StringComparison.OrdinalIgnoreCase) ||
                   varName.Equals("output", StringComparison.OrdinalIgnoreCase) ||
                   varName.Equals("print", StringComparison.OrdinalIgnoreCase);
        }
    }
}
