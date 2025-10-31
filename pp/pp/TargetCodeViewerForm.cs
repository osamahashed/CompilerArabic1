using System;
using Krypton.Toolkit;

namespace pp
{
    public partial class TargetCodeViewerForm : KryptonForm
    {
        private KryptonTextBox txtTargetCode;

        public TargetCodeViewerForm(string targetCode)
        {
            this.Text = "💻 الكود الهدف (Target Code)";
            this.Width = 800;
            this.Height = 850;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;

            txtTargetCode = new KryptonTextBox
            {
                Multiline = true,
                Dock = System.Windows.Forms.DockStyle.Fill,
                ScrollBars = System.Windows.Forms.ScrollBars.Both,
                ReadOnly = true,
                Text = targetCode ?? "لا يوجد كود هدف متاح."
            };

            this.Controls.Add(txtTargetCode);
        }
    }
}
