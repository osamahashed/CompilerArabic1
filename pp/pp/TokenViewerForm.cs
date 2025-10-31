using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Krypton.Toolkit;
using Antlr4.Runtime;
using System.Windows.Forms;

namespace pp
{


    // لاحظ أننا لم نعد نحتاج إلى partial class لأننا لا نستخدم ملف مصمم
    public class TokenViewerForm : KryptonForm
    {
        // تعريف المكون مباشرة كمتغير في الكلاس
        private KryptonDataGridView dgvTokens;

        public TokenViewerForm(IEnumerable<IToken> tokens, IVocabulary vocabulary)
        {
            // لا يوجد استدعاء لـ InitializeComponent()

            // 1. إعداد خصائص الفورم نفسه
            this.Text = "جدول التوكنات (مخرجات المحلل اللغوي)";
            this.Width = 700;
            this.Height = 800;
            this.StartPosition = FormStartPosition.CenterParent;

            // 2. إنشاء كائن جدول البيانات وتعيين خصائصه
            dgvTokens = new KryptonDataGridView
            {
                Dock = DockStyle.Fill,
                ReadOnly = true,
                AllowUserToAddRows = false,
                AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
                // يمكنك إضافة المزيد من خصائص المظهر هنا
            };

            // 3. إضافة جدول البيانات إلى مجموعة عناصر التحكم في الفورم
            this.Controls.Add(dgvTokens);

            // 4. إضافة الأعمدة بشكل برمجي
            dgvTokens.Columns.Add("Line", "السطر");
            dgvTokens.Columns.Add("Column", "العمود");
            dgvTokens.Columns.Add("Type", "النوع");
            dgvTokens.Columns.Add("Text", "النص");

            // 5. ملء الجدول بالبيانات
            foreach (var token in tokens)
            {
                // لا نعرض توكن نهاية الملف (EOF) لأنه غير مهم للمستخدم
                if (token.Type == TokenConstants.EOF) break;

                string typeName = vocabulary.GetSymbolicName(token.Type);
                dgvTokens.Rows.Add(token.Line, token.Column, typeName, token.Text);
            }
        }
    }
}
