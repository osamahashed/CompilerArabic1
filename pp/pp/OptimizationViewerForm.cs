using System;
using System.Drawing;
using System.Windows.Forms;
using Krypton.Toolkit;

namespace pp
{
    public partial class OptimizationViewerForm : KryptonForm
    {
        public OptimizationViewerForm(string originalCode, string optimizedCode)
        {
            // إعدادات الفورم العامة
            this.Text = "🧠 مرحلة تحسين الكود (Optimization)";
            this.Width = 1100;
            this.Height = 750;
            this.StartPosition = FormStartPosition.CenterParent;
            this.Padding = new Padding(10);

            // إنشاء لوحة رئيسية لتقسيم النوافذ
            var split = new KryptonSplitContainer
            {
                Dock = DockStyle.Fill,
                Orientation = Orientation.Vertical,
                SplitterDistance = this.Width / 2,
                IsSplitterFixed = false
            };

            // العنوان الأول
            var lblOriginal = new KryptonLabel
            {
                Text = "📄 الكود الأصلي",
                Dock = DockStyle.Top,
                StateCommon =
                {
                    ShortText = { Font = new Font("Segoe UI Semibold", 11f), Color1 = Color.DarkRed }
                },
                Padding = new Padding(5)
            };

            // العنوان الثاني
            var lblOptimized = new KryptonLabel
            {
                Text = "⚙️ الكود المحسّن",
                Dock = DockStyle.Top,
                StateCommon =
                {
                    ShortText = { Font = new Font("Segoe UI Semibold", 11f), Color1 = Color.SeaGreen }
                },
                Padding = new Padding(5)
            };

            // صندوق الكود الأصلي
            var txtOriginal = new KryptonRichTextBox
            {
                Dock = DockStyle.Fill,
                ReadOnly = true,
                WordWrap = false,
                Font = new Font("Consolas", 11),
                StateCommon =
                {
                    Back = { Color1 = Color.FromArgb(255, 250, 250) },
                    Content = { Color1 = Color.Black }
                },
                Text = originalCode ?? "غير متاح"
            };

            // صندوق الكود المحسن
            var txtOptimized = new KryptonRichTextBox
            {
                Dock = DockStyle.Fill,
                ReadOnly = true,
                WordWrap = false,
                Font = new Font("Consolas", 11),
                StateCommon =
                {
                    Back = { Color1 = Color.FromArgb(245, 255, 245) },
                    Content = { Color1 = Color.Black }
                },
                Text = optimizedCode ?? "غير متاح"
            };

            // إضافة المكونات إلى اللوحتين
            var leftPanel = new KryptonPanel { Dock = DockStyle.Fill };
            var rightPanel = new KryptonPanel { Dock = DockStyle.Fill };

            leftPanel.Controls.Add(txtOriginal);
            leftPanel.Controls.Add(lblOriginal);

            rightPanel.Controls.Add(txtOptimized);
            rightPanel.Controls.Add(lblOptimized);

            // ربطهما بالمقسم
            split.Panel1.Controls.Add(leftPanel);
            split.Panel2.Controls.Add(rightPanel);

            // إضافة الكل إلى الفورم
            this.Controls.Add(split);
        }
    }
}
