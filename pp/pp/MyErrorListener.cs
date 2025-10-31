using System;
using System.IO;
using Antlr4.Runtime;
using Krypton.Toolkit;

namespace pp
{
    public class MyErrorListener : BaseErrorListener
    {
        private readonly KryptonRichTextBox _output;
        public bool HasErrors { get; private set; } = false;

        public MyErrorListener(KryptonRichTextBox output)
        {
            _output = output;
        }

        public override void SyntaxError(
            TextWriter output,
            IRecognizer recognizer,
            IToken offendingSymbol,
            int line,
            int charPositionInLine,
            string msg,
            RecognitionException e)
        {
            HasErrors = true;

            // 🔹 تنسيق الرسالة ليظهر السطر والخطأ بشكل واضح
            string errorMsg = $"[❌] خطأ نحوي في السطر {line}، عند الموضع {charPositionInLine}:\n   ↳ {msg}\n";

            // 🔹 تلوين السطر بالأحمر داخل الـ KryptonRichTextBox
            _output.StateCommon.Content.Color1 = System.Drawing.Color.Red;
            _output.AppendText(errorMsg);

            // 🔹 سجل الخطأ في وحدة الإخراج للمطورين (debug)
            System.Diagnostics.Debug.WriteLine(errorMsg);
        }
    }
}
