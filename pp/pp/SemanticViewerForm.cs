using System;
using Krypton.Toolkit;

namespace pp
{
    public partial class SemanticViewerForm : KryptonForm
    {
        private KryptonTextBox txtReport;

        public SemanticViewerForm(string report)
        {
            this.Text = "📘 مرحلة التحليل الدلالي";
            this.Width = 700;
            this.Height = 800;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;

            txtReport = new KryptonTextBox
            {
                Multiline = true,
                Dock = System.Windows.Forms.DockStyle.Fill,
                ScrollBars = System.Windows.Forms.ScrollBars.Vertical,
                ReadOnly = true,
                Text = report ?? "لا يوجد تقرير متاح."
            };

            this.Controls.Add(txtReport);
        }
    }
}
