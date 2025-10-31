using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Krypton.Toolkit;

namespace pp
{
    public class ParseTreeViewerForm : KryptonForm
    {
        private Panel scrollPanel;
        private PictureBox canvas;
        private ToolStrip toolStrip;
        private ToolStripButton btnZoomIn, btnZoomOut, btnSave;

        private IParseTree tree;
        private Parser parser;
        private float scale = 1.0f;
        private Bitmap offscreenBitmap;

        private const int NodeWidth = 140;
        private const int NodeHeight = 40;
        private const int HorizontalSpacing = 40;
        private const int VerticalSpacing = 80;

        public ParseTreeViewerForm(IParseTree tree, Parser parser)
        {
            this.tree = tree;
            this.parser = parser;

            Text = "🌳 عرض شجرة التحليل";
            Width = 1200;
            Height = 900;
            StartPosition = FormStartPosition.CenterScreen;
            BackColor = Color.White;

            // 🔹 شريط الأدوات
            //toolStrip = new ToolStrip
            //{
            //    GripStyle = ToolStripGripStyle.Hidden,
            //    Dock = DockStyle.Top,
            //    BackColor = Color.WhiteSmoke
            //};
           
            btnSave = new ToolStripButton("💾 حفظ كصورة", null, SaveImage) { ToolTipText = "حفظ الشجرة كصورة" };
            // ✅ أنشئ شريط الأدوات أولًا
            var toolStrip = new ToolStrip();
            toolStrip.Dock = DockStyle.Top;

            // ✅ أنشئ أزرار التكبير والتصغير
            var btnZoomIn = new ToolStripButton("➕ تكبير");
            btnZoomIn.Click += (s, e) => ZoomIn();

            var btnZoomOut = new ToolStripButton("➖ تصغير");
            btnZoomOut.Click += (s, e) => ZoomOut();

            // ✅ الآن أضف الأزرار بأمان
            toolStrip.Items.Add(btnZoomIn);
            toolStrip.Items.Add(btnZoomOut);

            // ✅ أضف الشريط للفورم
            this.Controls.Add(toolStrip);

            toolStrip.Items.Add(new ToolStripLabel("التحكم: "));
            toolStrip.Items.Add(btnZoomIn);
            toolStrip.Items.Add(btnZoomOut);
            toolStrip.Items.Add(new ToolStripSeparator());
            toolStrip.Items.Add(btnSave);

            // 🔹 منطقة التمرير
            scrollPanel = new Panel
            {
                Dock = DockStyle.Fill,
                AutoScroll = true,
                BackColor = Color.White
            };

            // 🔹 الصورة الثابتة
            canvas = new PictureBox
            {
                BackColor = Color.White,
                SizeMode = PictureBoxSizeMode.AutoSize
            };
            scrollPanel.Controls.Add(canvas);

            Controls.Add(scrollPanel);
            Controls.Add(toolStrip);

            DrawTreeToBitmap();
        }

        // ===============================================================
        // رسم الشجرة داخل Bitmap واحدة (ثابت)
        // ===============================================================
        private void DrawTreeToBitmap()
        {
            if (tree == null || parser == null)
                return;

            Dictionary<IParseTree, PointF> positions = new Dictionary<IParseTree, PointF>();
            float totalWidth = LayoutTree(tree, 0, 0, positions);

            float bmpWidth = Math.Max(totalWidth + 200, 1000);
            float bmpHeight = GetTreeHeight(tree) * (NodeHeight + VerticalSpacing) + 200;

            // 🔹 حساب مقياس البداية التلقائي (ليظهر بشكل مناسب للمستخدم)
            float maxWidth = this.ClientSize.Width - 100;
            float maxHeight = this.ClientSize.Height - 150;
            float scaleX = maxWidth / bmpWidth;
            float scaleY = maxHeight / bmpHeight;
            scale = Math.Min(1.0f, Math.Min(scaleX, scaleY)); // لا تتجاوز 1

            offscreenBitmap?.Dispose();
            offscreenBitmap = new Bitmap((int)(bmpWidth * scale), (int)(bmpHeight * scale));

            using (Graphics g = Graphics.FromImage(offscreenBitmap))
            {
                g.Clear(Color.White);
                g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
                g.ScaleTransform(scale, scale);

                DrawConnections(g, tree, positions);
                DrawNodes(g, positions);
            }

            canvas.Image = offscreenBitmap;
            canvas.Size = offscreenBitmap.Size;

            AutoFit();
        }

        // ----------------------------------------------------
        // تجعل الصورة في المنتصف داخل منطقة التمرير
        // ----------------------------------------------------
        private void AutoFit()
        {
            int x = Math.Max(0, (scrollPanel.ClientSize.Width - canvas.Width) / 2);
            int y = Math.Max(0, (scrollPanel.ClientSize.Height - canvas.Height) / 2);
            canvas.Location = new Point(x, y);
        }


        // ===============================================================
        // توزيع العقد بشكل متناسق هندسيًا (layout)
        // ===============================================================
        private float LayoutTree(IParseTree node, float x, float y, Dictionary<IParseTree, PointF> map)
        {
            if (node.ChildCount == 0)
            {
                map[node] = new PointF(x, y);
                return NodeWidth;
            }

            float totalWidth = 0;
            List<float> childWidths = new List<float>();

            for (int i = 0; i < node.ChildCount; i++)
            {
                float w = LayoutTree(node.GetChild(i), x + totalWidth, y + NodeHeight + VerticalSpacing, map);
                childWidths.Add(w);
                totalWidth += w + HorizontalSpacing;
            }

            totalWidth -= HorizontalSpacing; // إزالة المسافة الزائدة في النهاية
            float centerX = x + totalWidth / 2 - NodeWidth / 2;
            map[node] = new PointF(centerX, y);

            return Math.Max(NodeWidth, totalWidth);
        }

        private int GetTreeHeight(IParseTree node)
        {
            if (node.ChildCount == 0) return 1;
            int max = 0;
            for (int i = 0; i < node.ChildCount; i++)
                max = Math.Max(max, GetTreeHeight(node.GetChild(i)));
            return 1 + max;
        }

        // ===============================================================
        // رسم الخطوط بين العقد
        // ===============================================================
        private void DrawConnections(Graphics g, IParseTree node, Dictionary<IParseTree, PointF> positions)
        {
            PointF parent = positions[node];
            for (int i = 0; i < node.ChildCount; i++)
            {
                var child = node.GetChild(i);
                PointF childPos = positions[child];

                float x1 = parent.X + NodeWidth / 2;
                float y1 = parent.Y + NodeHeight;
                float x2 = childPos.X + NodeWidth / 2;
                float y2 = childPos.Y;

                using (Pen pen = new Pen(Color.Gray, 1.5f))
                {
                    pen.CustomEndCap = new System.Drawing.Drawing2D.AdjustableArrowCap(3, 3);
                    g.DrawLine(pen, x1, y1, x2, y2);
                }

                DrawConnections(g, child, positions);
            }
        }

        // ===============================================================
        // رسم العقد (الصناديق)
        // ===============================================================
        private void DrawNodes(Graphics g, Dictionary<IParseTree, PointF> positions)
        {
            foreach (var kv in positions)
            {
                IParseTree node = kv.Key;
                PointF p = kv.Value;

                string text = Trees.GetNodeText(node, parser);
                bool isLeaf = node.ChildCount == 0;

                RectangleF rect = new RectangleF(p.X, p.Y, NodeWidth, NodeHeight);
                Color fill = isLeaf ? Color.FromArgb(220, 255, 220) : Color.FromArgb(230, 240, 255);
                Color border = isLeaf ? Color.Green : Color.DodgerBlue;

                using (SolidBrush b = new SolidBrush(fill))
                    g.FillRectangle(b, rect);
                using (Pen pen = new Pen(border, 2))
                    g.DrawRectangle(pen, rect.X, rect.Y, rect.Width, rect.Height);

                using (StringFormat sf = new StringFormat() { Alignment = StringAlignment.Center, LineAlignment = StringAlignment.Center })
                    g.DrawString(text, new Font("Consolas", 10, FontStyle.Bold), Brushes.Black, rect, sf);
            }
        }

        // ===============================================================
        // تكبير / تصغير
        // ===============================================================
        private void ZoomIn()
        {
            scale *= 1.2f; // تكبير 20%
            RedrawTree();
        }

        private void ZoomOut()
        {
            scale /= 1.2f; // تصغير 20%
            if (scale < 0.2f) scale = 0.2f; // لا نسمح بتصغير مفرط
            RedrawTree();
        }

        // تعيد رسم الشجرة بالمقياس الحالي دون إعادة التحليل
        private void RedrawTree()
        {
            if (tree == null || parser == null)
                return;

            offscreenBitmap?.Dispose();

            Dictionary<IParseTree, PointF> positions = new Dictionary<IParseTree, PointF>();
            float totalWidth = LayoutTree(tree, 0, 0, positions);

            float bmpWidth = Math.Max(totalWidth + 200, 1000);
            float bmpHeight = GetTreeHeight(tree) * (NodeHeight + VerticalSpacing) + 200;

            offscreenBitmap = new Bitmap((int)(bmpWidth * scale), (int)(bmpHeight * scale));

            using (Graphics g = Graphics.FromImage(offscreenBitmap))
            {
                g.Clear(Color.White);
                g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
                g.ScaleTransform(scale, scale);
                DrawConnections(g, tree, positions);
                DrawNodes(g, positions);
            }

            canvas.Image = offscreenBitmap;
            canvas.Size = offscreenBitmap.Size;
            AutoFit();
        }

        // ===============================================================
        // حفظ الصورة كملف PNG
        // ===============================================================
        private void SaveImage(object sender, EventArgs e)
        {
            using (SaveFileDialog dlg = new SaveFileDialog())
            {
                dlg.Filter = "صورة PNG|*.png";
                dlg.Title = "حفظ شجرة التحليل";
                dlg.FileName = "ParseTree.png";
                if (dlg.ShowDialog() == DialogResult.OK)
                    offscreenBitmap.Save(dlg.FileName, System.Drawing.Imaging.ImageFormat.Png);
            }
        }

        protected override void OnFormClosed(FormClosedEventArgs e)
        {
            offscreenBitmap?.Dispose();
            base.OnFormClosed(e);
        }

        private void btnZoomIn_Click(object sender, EventArgs e)
        {
            ZoomIn();
        }

        private void btnZoomOut_Click(object sender, EventArgs e)
        {
            ZoomOut();
        }

    }
}
