using System;
using Krypton.Toolkit;

namespace pp
{
    public partial class IntermediateCodeViewerForm : KryptonForm
    {
        private KryptonTextBox txtCode;

        public IntermediateCodeViewerForm(string code)
        {
            this.Text = "⚙️ الكود الوسيط (Intermediate Code)";
            this.Width = 700;
            this.Height = 800;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;

            txtCode = new KryptonTextBox
            {
                Multiline = true,
                Dock = System.Windows.Forms.DockStyle.Fill,
                ScrollBars = System.Windows.Forms.ScrollBars.Both,
                ReadOnly = true,
                Text = code ?? "لا يوجد كود وسيط متاح."
            };

            this.Controls.Add(txtCode);
        }
    }
}
