using System;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Krypton.Toolkit;

using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System.Threading.Tasks;

namespace pp
{
    public partial class Form1 : KryptonForm
    {
        private MenuStrip menuStrip;
        private ToolStrip toolBar;
        private TabControl tabControl;
        // تم التعديل: استخدام RichTextBox لعرض المخرجات
        private KryptonRichTextBox outputBox;
        private System.Windows.Forms.ProgressBar progressBar;

        private StatusStrip statusStrip;
        private ToolStripStatusLabel statusLabel;
        private KryptonPanel welcomePanel;
        private KryptonManager kryptonManager;
        private bool isDarkTheme = true;

        public Form1()
        {
            InitializeComponent();

            kryptonManager = new KryptonManager();
            kryptonManager.GlobalPaletteMode = PaletteMode.Office2010Black;

            this.Text = "بيئة تطوير عربية";
            this.WindowState = FormWindowState.Maximized;
            this.RightToLeft = RightToLeft.Yes;
            this.RightToLeftLayout = true;

            InitMenu();
            InitToolbar();
            InitStatus();
            InitTabs();
            // تم التعديل: استدعاء الدالة المحدثة
            InitOutput();
            InitWelcomePanel();
            ApplyTheme();

            this.Controls.Add(tabControl);
            this.Controls.Add(outputBox);
            this.Controls.Add(toolBar);
            this.Controls.Add(menuStrip);
            this.Controls.Add(statusStrip);
            this.Controls.Add(welcomePanel);
            welcomePanel.BringToFront();

            this.progressBar = new System.Windows.Forms.ProgressBar();
            this.progressBar.Location = new System.Drawing.Point(10, this.Height - 80);
            this.progressBar.Size = new System.Drawing.Size(600, 20);
            this.progressBar.Minimum = 0;
            this.progressBar.Maximum = 5;
            this.progressBar.Value = 0;
            this.progressBar.Step = 1;
            this.Controls.Add(this.progressBar);

        }

        // ... (دوال الواجهة مثل InitMenu, InitToolbar, InitTabs, etc. تبقى كما هي) ...
        private void InitMenu()
        {
            menuStrip = new MenuStrip();
            var mFile = new ToolStripMenuItem("ملف");
            mFile.DropDownItems.Add("جديد", null, NewFile);
            mFile.DropDownItems.Add("فتح", null, OpenFile);
            mFile.DropDownItems.Add("حفظ", null, SaveFile);
            mFile.DropDownItems.Add("حفظ باسم", null, SaveAs);
            mFile.DropDownItems.Add(new ToolStripSeparator());
            mFile.DropDownItems.Add("إغلاق التبويب", null, CloseTab);
            mFile.DropDownItems.Add("خروج", null, (s, e) => this.Close());
            var mEdit = new ToolStripMenuItem("تحرير");
            mEdit.DropDownItems.Add("تراجع", null, Undo);
            mEdit.DropDownItems.Add("إعادة", null, Redo);

            // 1. إنشاء قائمة رئيسية جديدة
            var mViewStages = new ToolStripMenuItem("عرض مراحل المترجم");

            // 2. إضافة قائمة فرعية لعرض جدول التوكنات (المرحلة الأولى: التحليل اللغوي)
            mViewStages.DropDownItems.Add("عرض جدول التوكنات (Lexer)", null, ShowTokenList_Click);

            // 3. إضافة قائمة فرعية لعرض شجرة التحليل (المرحلة الثانية: التحليل النحوي)
            mViewStages.DropDownItems.Add("عرض شجرة التحليل (Parser)", null, ShowParseTree_Click);

            // 4. المرحلة الثالثة: تحليل المعاني (Semantic Analysis)
            mViewStages.DropDownItems.Add("عرض مرحلة التحليل الدلالي (Semantic)", null, ShowSemanticStage_Click);

            // 5. المرحلة الرابعة: توليد الكود الوسيط (Intermediate Code)
            mViewStages.DropDownItems.Add("عرض الكود الوسيط (Intermediate Code)", null, ShowIntermediateCode_Click);

            // 6. المرحلة الخامسة: تحسين الكود (Optimization)
            mViewStages.DropDownItems.Add("عرض مرحلة تحسين الكود (Optimization)", null, ShowOptimizationStage_Click);

            // 7. المرحلة السادسة: الكود الهدف (Target Code)
            mViewStages.DropDownItems.Add("عرض الكود الهدف (C Code)", null, ShowGeneratedCode_Click);

            // تم التعديل: ربط دالة التشغيل الجديدة
            // في InitMenu()
            var mRun = new ToolStripMenuItem("تشغيل");
            mRun.DropDownItems.Add("تشغيل كمفسر", null, RunInterpreter);
            mRun.DropDownItems.Add("تشغيل كمترجم", null, RunCompiler);

            var mSettings = new ToolStripMenuItem("الإعدادات");
            mSettings.DropDownItems.Add("تبديل الثيم", null, ToggleTheme);
            menuStrip.Items.AddRange(new[] { mFile, mEdit, mRun, mSettings, mViewStages });
            this.MainMenuStrip = menuStrip;
        }

        private void InitToolbar()
        {
            toolBar = new ToolStrip();
            toolBar.Items.Add("🆕 جديد", null, NewFile);
            toolBar.Items.Add("📂 فتح", null, OpenFile);
            toolBar.Items.Add("💾 حفظ", null, SaveFile);
            toolBar.Items.Add(new ToolStripSeparator());
            // تم التعديل: ربط دالة التشغيل الجديدة
            toolBar.Items.Add("▶ مفسر", null, RunInterpreter);
            toolBar.Items.Add("⚙ مترجم", null, RunCompiler);

        }



        private void InitStatus()
        {
            statusStrip = new StatusStrip();
            statusLabel = new ToolStripStatusLabel("جاهز");
            statusStrip.Items.Add(statusLabel);
        }

        private void InitTabs()
        {
            tabControl = new TabControl { Dock = DockStyle.Fill, RightToLeftLayout = true };
        }

        // --- تعديل جوهري ---
        // تم استبدال ListBox بـ RichTextBox ليكون مناسبًا لعرض المخرجات
        private void InitOutput()
        {
            outputBox = new KryptonRichTextBox
            {
                Dock = DockStyle.Bottom,
                Height = 150,
                ReadOnly = true,
                StateCommon = {
                    Content = { Font = new Font("Consolas", 11, FontStyle.Regular) }
                }
            };
        }

        private void InitWelcomePanel()
        {
            // ... (لا تغيير هنا)
            welcomePanel = new KryptonPanel { Dock = DockStyle.Fill, Visible = true };
            KryptonLabel title = new KryptonLabel { Text = "مرحبًا بك", StateCommon = { ShortText = { Font = new Font("Cairo", 20, FontStyle.Bold), TextH = PaletteRelativeAlign.Center } }, Dock = DockStyle.Top, Height = 200, Padding = new Padding(0, 150, 0, 0) };
            KryptonButton newBtn = new KryptonButton { Text = "🆕 ملف جديد", Size = new Size(200, 50), StateCommon = { Content = { ShortText = { Font = new Font("Segoe UI", 12) } } }, };
            newBtn.Click += NewFile;
            KryptonButton openBtn = new KryptonButton { Text = "📂 فتح ملف", Size = new Size(200, 50), StateCommon = { Content = { ShortText = { Font = new Font("Segoe UI", 12) } } }, };
            openBtn.Click += OpenFile;
            welcomePanel.Resize += (s, e) => { newBtn.Location = new Point((welcomePanel.Width - newBtn.Width) / 2, 250); openBtn.Location = new Point((welcomePanel.Width - openBtn.Width) / 2, 310); };
            welcomePanel.Controls.Add(newBtn);
            welcomePanel.Controls.Add(openBtn);
            welcomePanel.Controls.Add(title);
        }

        // --- تعديل جوهري ---
        // تم استبدال المحلل اليدوي القديم بمحرك ANTLR الكامل
        private async void RunCode(bool isCompiler)
        {
            var editor = GetActiveEditor();
            if (editor == null)
            {
                MessageBox.Show("لا يوجد ملف مفتوح لتشغيله.", "خطأ", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            outputBox.Clear();
            statusLabel.Text = "جاري التحليل...";
            progressBar.Value = 0;
            progressBar.Visible = true;

            string sourceCode = editor.Text;

            await Task.Run(() =>
            {
                try
                {
                    // 🔹 المرحلة 1: التحليل
                    Invoke(new Action(() =>
                    {
                        progressBar.Value = 1;
                        statusLabel.Text = "🔹 المرحلة 1: التحليل...";
                    }));

                    var inputStream = new AntlrInputStream(sourceCode);
                    var lexer = new ArabicLangLexer(inputStream);
                    var tokenStream = new CommonTokenStream(lexer);
                    var parser = new ArabicLangParser(tokenStream);

                    parser.RemoveErrorListeners();
                    var errorListener = new MyErrorListener(outputBox);
                    parser.AddErrorListener(errorListener);

                    IParseTree tree = parser.program();

                    if (errorListener.HasErrors)
                    {
                        Invoke(new Action(() =>
                        {
                            statusLabel.Text = "❌ فشل التحليل.";
                            progressBar.Value = 0;
                        }));
                        return;
                    }

                    if (!isCompiler)
                    {
                        Invoke(new Action(() =>
                        {
                            statusLabel.Text = "جاري التفسير...";
                            progressBar.Visible = false;
                        }));

                        var visitor = new MyLanguageVisitor(outputBox);
                        visitor.Visit(tree);

                        Invoke(new Action(() =>
                        {
                            statusLabel.Text = "✅ تم التنفيذ بنجاح (مفسر).";
                        }));
                    }
                    else
                    {
                        // 🔹 المرحلة 2: توليد الكود الوسيط
                        Invoke(new Action(() =>
                        {
                            progressBar.Value = 2;
                            statusLabel.Text = "🔹 المرحلة 2: توليد الكود الوسيط...";
                        }));

                        var generator = new CCodeGenerator();
                        string generatedCode = generator.Generate(tree);

                        // 🔹 المرحلة 3: تحسين الكود
                        Invoke(new Action(() =>
                        {
                            progressBar.Value = 3;
                            statusLabel.Text = "🔹 المرحلة 3: تحسين الكود...";
                        }));

                        generatedCode = generator.OptimizeCode(generatedCode);

                        // 🔹 المرحلة 4: توليد الكود الهدف
                        Invoke(new Action(() =>
                        {
                            progressBar.Value = 4;
                            statusLabel.Text = "🔹 المرحلة 4: توليد الكود الهدف...";
                            outputBox.Clear();
                            outputBox.AppendText("// الكود المولّد بلغة C:\n\n");
                            outputBox.AppendText(generatedCode + "\n\n");
                        }));

                        string arbFilePath = (string)Invoke(new Func<string>(() => tabControl.SelectedTab?.Tag as string));

                        if (!string.IsNullOrEmpty(arbFilePath))
                        {
                            string folder = Path.GetDirectoryName(arbFilePath);
                            string fileNameWithoutExt = Path.GetFileNameWithoutExtension(arbFilePath);
                            string projectFolder = Path.Combine(folder, fileNameWithoutExt);

                            if (!Directory.Exists(projectFolder))
                                Directory.CreateDirectory(projectFolder);

                            string cFilePath = Path.Combine(projectFolder, fileNameWithoutExt + ".c");
                            File.WriteAllText(cFilePath, generatedCode);

                            // 🔹 المرحلة 5: فحص GCC
                            Invoke(new Action(() =>
                            {
                                progressBar.Value = 5;
                                statusLabel.Text = "🔹 المرحلة 5: فحص الكود باستخدام GCC...";
                                outputBox.AppendText("🔍 جاري فحص الكود المولّد باستخدام GCC...\n");
                            }));

                            var checkProcess = new System.Diagnostics.Process();
                            checkProcess.StartInfo.FileName = "cmd.exe";
                            checkProcess.StartInfo.Arguments = $"/C gcc -fsyntax-only \"{cFilePath}\"";
                            checkProcess.StartInfo.WorkingDirectory = projectFolder;
                            checkProcess.StartInfo.CreateNoWindow = true;
                            checkProcess.StartInfo.UseShellExecute = false;
                            checkProcess.StartInfo.RedirectStandardError = true;
                            checkProcess.StartInfo.RedirectStandardOutput = true;
                            checkProcess.Start();

                            string checkOutput = checkProcess.StandardOutput.ReadToEnd();
                            string checkErrors = checkProcess.StandardError.ReadToEnd();
                            checkProcess.WaitForExit();

                            if (string.IsNullOrWhiteSpace(checkErrors))
                            {
                                Invoke(new Action(() =>
                                {
                                    outputBox.AppendText("\n✅ تم فحص الكود بنجاح — لا توجد أخطاء نحوية.\n");
                                    statusLabel.Text = "✅ تم فحص الكود بنجاح ✔";
                                }));
                            }
                            else
                            {
                                Invoke(new Action(() =>
                                {
                                    outputBox.AppendText("\n⚠️ أخطاء أثناء فحص الكود:\n" + checkErrors + "\n");
                                    statusLabel.Text = "⚠️ فشل الفحص — يوجد أخطاء.";
                                }));
                            }

                            string arbCopyPath = Path.Combine(projectFolder, fileNameWithoutExt + ".arb");
                            if (!File.Exists(arbCopyPath))
                                File.Copy(arbFilePath, arbCopyPath, true);

                            Invoke(new Action(() =>
                            {
                                statusLabel.Text = $"✅ تم توليد الكود وحفظه في: {cFilePath}";
                                outputBox.AppendText($"\n\n// ✅ تم حفظ الكود في: {cFilePath}");
                            }));
                        }

                        Invoke(new Action(() =>
                        {
                            progressBar.Visible = false;
                        }));
                    }
                }
                catch (Exception ex)
                {
                    Invoke(new Action(() =>
                    {
                        outputBox.AppendText($"\n❌ خطأ أثناء التنفيذ: {ex.Message}");
                        statusLabel.Text = "فشل التنفيذ.";
                        progressBar.Visible = false;
                    }));
                }
            });
        }




        private void RunInterpreter(object sender, EventArgs e)
        {
            RunCode(false); // تشغيل المفسر
        }

        private void RunCompiler(object sender, EventArgs e)
        {
            RunCode(true); // تشغيل المترجم
        }


        // ... (بقية دوال الواجهة مثل GetActiveEditor, NewFile, OpenFile, SaveFile, etc. تبقى كما هي) ...

        // أضف هذه الدالة الجديدة داخل كلاس Form1.cs

        private void ShowTokenList_Click(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null || string.IsNullOrWhiteSpace(editor.Text))
            {
                MessageBox.Show("الرجاء فتح ملف يحتوي على كود أولاً.", "تنبيه");
                return;
            }

            var lexer = new ArabicLangLexer(new AntlrInputStream(editor.Text));
            var tokens = lexer.GetAllTokens();

            // السطر التالي هو المهم: يقوم بإنشاء الفورم الاحترافي الجديد
            var tokenForm = new TokenViewerForm(tokens, lexer.Vocabulary);
            tokenForm.Show(this);
        }
        private void ShowParseTree_Click(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null || string.IsNullOrWhiteSpace(editor.Text))
            {
                MessageBox.Show("الرجاء فتح ملف يحتوي على كود أولاً.", "تنبيه");
                return;
            }

            var inputStream = new AntlrInputStream(editor.Text);
            var lexer = new ArabicLangLexer(inputStream);
            var tokenStream = new CommonTokenStream(lexer);
            var parser = new ArabicLangParser(tokenStream);

            IParseTree tree = parser.program();

            // السطر التالي هو المهم: يقوم بإنشاء الفورم الاحترافي الجديد
            var treeForm = new ParseTreeViewerForm(tree, parser);
            treeForm.Show(this);
        }

        // ✅ المرحلة الثالثة: التحليل الدلالي
        // عرض التحليل الدلالي في فورم منفصل
        private void ShowSemanticStage_Click(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null || string.IsNullOrWhiteSpace(editor.Text))
            {
                MessageBox.Show("الرجاء فتح ملف يحتوي على كود أولاً.", "تنبيه");
                return;
            }

            var inputStream = new AntlrInputStream(editor.Text);
            var lexer = new ArabicLangLexer(inputStream);
            var tokenStream = new CommonTokenStream(lexer);
            var parser = new ArabicLangParser(tokenStream);
            var tree = parser.program();

            var semantic = new MySemanticAnalyzer(null); // لا نمرر الـ outputBox لأننا سنعرض التقرير في فورم مستقل
            semantic.Visit(tree);
            string report = semantic.Report;

            var form = new SemanticViewerForm(report);
            form.Show(this);
        }

        // عرض الكود الوسيط في فورم منفصل
        private void ShowIntermediateCode_Click(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null || string.IsNullOrWhiteSpace(editor.Text))
            {
                MessageBox.Show("الرجاء فتح ملف يحتوي على كود أولاً.", "تنبيه");
                return;
            }

            var inputStream = new AntlrInputStream(editor.Text);
            var lexer = new ArabicLangLexer(inputStream);
            var tokenStream = new CommonTokenStream(lexer);
            var parser = new ArabicLangParser(tokenStream);
            var tree = parser.program();

            var intermediate = new IntermediateCodeGenerator();
            string code = intermediate.Generate(tree);

            var form = new IntermediateCodeViewerForm(code);
            form.Show(this);
        }


        // ✅ المرحلة الخامسة: تحسين الكود
        // ✅ المرحلة الخامسة: تحسين الكود
        private void ShowOptimizationStage_Click(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null || string.IsNullOrWhiteSpace(editor.Text))
            {
                MessageBox.Show("الرجاء فتح ملف يحتوي على كود أولاً.", "تنبيه");
                return;
            }

            var inputStream = new AntlrInputStream(editor.Text);
            var lexer = new ArabicLangLexer(inputStream);
            var tokenStream = new CommonTokenStream(lexer);
            var parser = new ArabicLangParser(tokenStream);
            var tree = parser.program();

            outputBox.AppendText("\n\n--- المرحلة الخامسة: تحسين الكود (Optimization) ---\n");

            // 1️⃣ توليد الكود الوسيط
            var intermediateGen = new IntermediateCodeGenerator();
            string intermediate = intermediateGen.Generate(tree);

            // 2️⃣ تطبيق التحسين
            var generator = new CCodeGenerator();
            string originalCode = generator.Generate(tree);
            string optimizedCode = generator.OptimizeCode(originalCode);

            var form = new OptimizationViewerForm(originalCode, optimizedCode);
            form.Show(this);

        }


        // ✅ المرحلة السادسة: الكود الهدف
        // ✅ المرحلة السادسة: توليد الكود الهدف
        private void ShowGeneratedCode_Click(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null || string.IsNullOrWhiteSpace(editor.Text))
            {
                MessageBox.Show("الرجاء فتح ملف يحتوي على كود أولاً.", "تنبيه");
                return;
            }

            var inputStream = new AntlrInputStream(editor.Text);
            var lexer = new ArabicLangLexer(inputStream);
            var tokenStream = new CommonTokenStream(lexer);
            var parser = new ArabicLangParser(tokenStream);
            var tree = parser.program();

            // 1️⃣ توليد الكود الوسيط
            var intermediateGen = new IntermediateCodeGenerator();
            string intermediate = intermediateGen.Generate(tree);

            // 2️⃣ تحسين الكود
            var optimizer = new CodeOptimizer();
            string optimized = optimizer.Optimize(intermediate);

            // 3️⃣ توليد الكود الهدف
            var generator = new CCodeGenerator();
            string code = generator.Generate(tree);

            var form = new TargetCodeViewerForm(code);
            form.Show(this);

        }


        private KryptonRichTextBox GetActiveEditor()
        {
            if (tabControl.SelectedTab == null || tabControl.SelectedTab.Controls.Count == 0) return null;
            var container = tabControl.SelectedTab.Controls[0] as KryptonPanel;
            return container?.Controls.OfType<KryptonRichTextBox>().FirstOrDefault(c => c.Dock == DockStyle.Fill);
        }

        private void NewFile(object sender, EventArgs e)
        {
            if (welcomePanel != null) welcomePanel.Hide();

            // اطلب اسم المشروع
            string projectName = SimpleInputBox.Show("أدخل اسم المشروع الجديد:", "مشروع جديد", "مشروعي");
            if (string.IsNullOrWhiteSpace(projectName)) return; // المستخدم ألغى

            string basePath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments), projectName);


            // تحقق إن كان المجلد موجود مسبقاً
            if (Directory.Exists(basePath))
            {
                var result = MessageBox.Show(
                    $"المجلد '{projectName}' موجود مسبقًا.\nهل ترغب في فتحه؟",
                    "تحذير",
                    MessageBoxButtons.YesNoCancel,
                    MessageBoxIcon.Warning);

                if (result == DialogResult.Yes)
                {
                    // فقط افتح المشروع القديم
                    statusLabel.Text = $"تم فتح المشروع الموجود: {projectName}";
                }
                else if (result == DialogResult.No)
                {
                    // احذف القديم وأنشئ من جديد
                    Directory.Delete(basePath, true);
                    Directory.CreateDirectory(basePath);
                    statusLabel.Text = $"تم إنشاء مشروع جديد واستبدال القديم: {projectName}";
                }
                else
                {
                    return; // المستخدم اختار إلغاء
                }
            }
            else
            {
                Directory.CreateDirectory(basePath);
                statusLabel.Text = $"تم إنشاء مشروع جديد: {projectName}";
            }

            // أنشئ تبويب جديد للمحرر
            var page = new TabPage("ملف جديد");
            var editor = new KryptonRichTextBox();
            var container = CreateEditorWithLineNumbers(editor);
            page.Controls.Add(container);
            tabControl.TabPages.Add(page);
            tabControl.SelectedTab = page;
            editor.Focus();
            ApplyThemeForEditor(editor);
        }



        // 1. دالة فتح ملف من القرص
        private void OpenFile(object sender, EventArgs e)
        {
            // استخدم صندوق حوار فتح الملفات القياسي
            using (var ofd = new OpenFileDialog())
            {
                // قم بتصفية الملفات لتسهيل العثور على ملفات لغتك
                ofd.Filter = "ملفات اللغة العربية (*.arb)|*.arb|كل الملفات (*.*)|*.*";
                ofd.Title = "فتح ملف";

                if (ofd.ShowDialog() == DialogResult.OK)
                {
                    // إذا كان المستخدم قد اختار ملفًا، قم بإخفاء شاشة الترحيب
                    if (welcomePanel != null) welcomePanel.Hide();

                    // اقرأ محتوى الملف بالكامل
                    string filePath = ofd.FileName;
                    string fileContent = File.ReadAllText(filePath);

                    // أنشئ تبويبًا جديدًا باسم الملف
                    var page = new TabPage(Path.GetFileName(filePath));
                    // خزن مسار الملف في الخاصية Tag للتبويب لنستخدمه لاحقًا في الحفظ
                    page.Tag = filePath;

                    // أنشئ محرر نصوص جديدًا وضع فيه محتوى الملف
                    var editor = new KryptonRichTextBox { Text = fileContent };

                    // استخدم دالتك لإنشاء المحرر مع أرقام الأسطر
                    var container = CreateEditorWithLineNumbers(editor);
                    page.Controls.Add(container);

                    // أضف التبويب الجديد إلى الحاوية الرئيسية
                    tabControl.TabPages.Add(page);
                    tabControl.SelectedTab = page;

                    // طبق الثيم الحالي على المحرر الجديد
                    ApplyThemeForEditor(editor);
                }
            }
        }

        // 2. دالة حفظ التغييرات في الملف الحالي
        private void SaveFile(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null) return;

            var currentTab = tabControl.SelectedTab;
            string filePath = currentTab.Tag as string;

            if (string.IsNullOrEmpty(filePath))
            {
                SaveAs(sender, e);
            }
            else
            {
                // حفظ الملف الأصلي
                File.WriteAllText(filePath, editor.Text);
                statusLabel.Text = $"تم حفظ الملف: {Path.GetFileName(filePath)}";

                // 🔹 حفظ ملف المترجم (الناتج)
                SaveCompiledVersion(filePath, editor.Text);
            }
        }


        // 3. دالة حفظ الملف في مكان جديد أو باسم جديد
        private void SaveAs(object sender, EventArgs e)
        {
            var editor = GetActiveEditor();
            if (editor == null) return;

            using (var sfd = new SaveFileDialog())
            {
                sfd.Filter = "ملفات اللغة العربية (*.arb)|*.arb|كل الملفات (*.*)|*.*";
                sfd.Title = "حفظ ملف باسم";

                if (sfd.ShowDialog() == DialogResult.OK)
                {
                    // اكتب نص المحرر إلى المسار الجديد الذي اختاره المستخدم
                    File.WriteAllText(sfd.FileName, editor.Text);

                    // قم بتحديث معلومات التبويب الحالي
                    var currentTab = tabControl.SelectedTab;
                    currentTab.Text = Path.GetFileName(sfd.FileName); // غير اسم التبويب
                    currentTab.Tag = sfd.FileName; // حدث مسار الملف

                    statusLabel.Text = $"تم حفظ الملف بنجاح في: {sfd.FileName}";
                    SaveCompiledVersion(sfd.FileName, editor.Text);
                }
            }
        }

        private void SaveCompiledVersion(string sourcePath, string sourceCode)
        {
            try
            {
                // مسار واسم الملف الناتج (.c) بنفس مجلد المصدر
                string baseName = Path.GetFileNameWithoutExtension(sourcePath);
                string outputPath = Path.Combine(Path.GetDirectoryName(sourcePath), baseName + ".c");

                // ---- استخدم ANTLR لبناء شجرة التحليل من sourceCode ----
                var input = new AntlrInputStream(sourceCode);
                var lexer = new ArabicLangLexer(input);
                var tokens = new CommonTokenStream(lexer);
                var parser = new ArabicLangParser(tokens);

                // ضع مستمع الأخطاء إذا تريد (اختياري)
                // parser.RemoveErrorListeners();
                // parser.AddErrorListener(new MyErrorListener(outputBox));

                var tree = parser.program(); // جذر الشجرة

                // ---- استدع مولّد الكود الموجود في المشروع ----
                var generator = new CCodeGenerator(); // تأكد أن CCodeGenerator public ومتوافق
                string compiledCode = generator.Generate(tree); // أو generator.Visit(tree) حسب تعريفك

                // ---- احفظ ملف الـ C ----
                File.WriteAllText(outputPath, compiledCode);

                statusLabel.Text = $"تم حفظ الملف المترجم: {Path.GetFileName(outputPath)}";
            }
            catch (Exception ex)
            {
                MessageBox.Show("حدث خطأ أثناء حفظ الملف المترجم:\n" + ex.Message, "خطأ", MessageBoxButtons.OK, MessageBoxIcon.Error);
                statusLabel.Text = "فشل حفظ الملف المترجم.";
            }
        }



        // 4. دالة إغلاق التبويب الحالي مع التأكد من الحفظ
        private void CloseTab(object sender, EventArgs e)
        {
            if (tabControl.SelectedTab == null) return;

            var currentTab = tabControl.SelectedTab;

            // أظهر رسالة تأكيد للمستخدم قبل الإغلاق
            var result = MessageBox.Show(
                "هل تريد حفظ التغييرات قبل إغلاق الملف؟",
                "تأكيد الإغلاق",
                MessageBoxButtons.YesNoCancel,
                MessageBoxIcon.Question);

            // إذا ضغط المستخدم "إلغاء الأمر"، لا تفعل شيئًا
            if (result == DialogResult.Cancel)
            {
                return;
            }
            // إذا ضغط "نعم"، قم بحفظ الملف أولاً
            if (result == DialogResult.Yes)
            {
                SaveFile(sender, e);
            }

            // قم بإزالة التبويب من الحاوية
            tabControl.TabPages.Remove(currentTab);

            // إذا لم يتبق أي تبويبات، أظهر شاشة الترحيب مجددًا
            if (tabControl.TabPages.Count == 0 && welcomePanel != null)
            {
                welcomePanel.Show();
            }
        }
        private void Undo(object sender, EventArgs e) { GetActiveEditor()?.Undo(); }
        private void Redo(object sender, EventArgs e) { GetActiveEditor()?.Redo(); }

        
          private KryptonPanel CreateEditorWithLineNumbers(KryptonRichTextBox editor)
        {
            KryptonPanel container = new KryptonPanel { Dock = DockStyle.Fill };
            KryptonRichTextBox lineNumbers = new KryptonRichTextBox
            {
                Dock = DockStyle.Right,
                Width = 40,
                ReadOnly = true,
                Font = new Font("Segoe UI", 10),
                Enabled = false,
                ScrollBars = RichTextBoxScrollBars.None,
                RightToLeft = RightToLeft.No
            };

            editor.Dock = DockStyle.Fill;
            editor.Font = new Font("Cairo", 14);
            editor.AcceptsTab = true;
            editor.WordWrap = false; // من الأفضل إيقاف التفاف النص في محررات الأكواد
            editor.RightToLeft = RightToLeft.Yes;
            editor.ScrollBars = RichTextBoxScrollBars.Both;

            // ربط الأحداث لتحديث أرقام الأسطر
            editor.TextChanged += (s, e) => UpdateLineNumbers(editor, lineNumbers);
            editor.VScroll += (s, e) => SyncLineNumberScroll(editor, lineNumbers);
            editor.FontChanged += (s, e) => UpdateLineNumbers(editor, lineNumbers);
            editor.TextChanged += (s, e) => HighlightSyntax();
            
            container.Controls.Add(editor);
            container.Controls.Add(lineNumbers);

            // قم بتحديث الأرقام مرة واحدة في البداية
            UpdateLineNumbers(editor, lineNumbers);

            return container;
        }
    
    private void UpdateLineNumbers(KryptonRichTextBox editor, KryptonRichTextBox lineBox)
    {
        Point pos = editor.GetPositionFromCharIndex(0);
        int firstIndex = editor.GetCharIndexFromPosition(new Point(0, 0));
        int firstLine = editor.GetLineFromCharIndex(firstIndex);

        int lastIndex = editor.GetCharIndexFromPosition(new Point(editor.ClientSize.Width, editor.ClientSize.Height));
        int lastLine = editor.GetLineFromCharIndex(lastIndex);

        lineBox.Text = "";
        for (int i = firstLine + 1; i <= lastLine + 1; i++)
        {
            lineBox.AppendText(i + "\n");
        }
    }
    private void SyncLineNumberScroll(KryptonRichTextBox editor, KryptonRichTextBox lineBox)
    {
        // هذه الدالة تضمن تزامن التمرير بين المحرر وأرقام الأسطر
        int d = editor.GetPositionFromCharIndex(0).Y % editor.Font.Height;
        lineBox.Location = new Point(lineBox.Location.X, d);
        UpdateLineNumbers(editor, lineBox);
    }
        // 1. هذه الدالة هي المشغل الرئيسي لتبديل الثيم
        private void ToggleTheme(object sender, EventArgs e)
        {
            // اعكس قيمة متغير الثيم (إذا كان ليليًا، اجعله نهاريًا، والعكس)
            isDarkTheme = !isDarkTheme;
            // استدعِ الدالة الرئيسية لتطبيق التغييرات على كل الواجهة
            ApplyTheme();
        }

        // 2. هذه الدالة تطبق الثيم على كل مكونات الواجهة الرئيسية
        private void ApplyTheme()
        {
            // غيّر الثيم العام لمكونات كريبتون بناءً على الاختيار
            kryptonManager.GlobalPaletteMode = isDarkTheme ? PaletteMode.Office2010Black : PaletteMode.Office2010Blue;

            // خصص ألوان صندوق المخرجات ليتناسب مع الثيم
            outputBox.StateCommon.Back.Color1 = isDarkTheme ? Color.FromArgb(30, 30, 30) : Color.White;
            outputBox.StateCommon.Content.Color1 = isDarkTheme ? Color.LightGreen : Color.Black;

            // قم بالمرور على كل التبويبات المفتوحة حاليًا لتطبيق الثيم على المحررات بداخلها
            foreach (TabPage page in tabControl.TabPages)
            {
                var editor = GetActiveEditorFromTab(page);
                if (editor != null)
                {
                    ApplyThemeForEditor(editor);
                }
            }
        }

        // 3. هذه الدالة تطبق الثيم على محرر نصوص واحد وأرقام الأسطر الخاصة به
        private void ApplyThemeForEditor(KryptonRichTextBox editor)
        {
            // اضبط لون خلفية ولون خط المحرر
            editor.StateCommon.Back.Color1 = isDarkTheme ? Color.FromArgb(30, 30, 30) : Color.WhiteSmoke;
            editor.StateCommon.Content.Color1 = isDarkTheme ? Color.White : Color.Black;

            // ابحث عن صندوق أرقام الأسطر المرتبط بهذا المحرر
            var lineNumbers = editor.Parent.Controls.OfType<KryptonRichTextBox>()
                                          .FirstOrDefault(c => c.Dock == DockStyle.Right);

            if (lineNumbers != null)
            {
                // اضبط لون خلفية ولون خط أرقام الأسطر لتمييزها قليلاً
                lineNumbers.StateCommon.Back.Color1 = isDarkTheme ? Color.FromArgb(45, 45, 48) : Color.Gainsboro;
                lineNumbers.StateCommon.Content.Color1 = Color.Gray;
            }
        }

        // دالة مساعدة صغيرة للحصول على المحرر من تبويب معين
        private KryptonRichTextBox GetActiveEditorFromTab(TabPage page)
        {
            if (page == null || page.Controls.Count == 0) return null;
            var container = page.Controls[0] as KryptonPanel;
            return container?.Controls.OfType<KryptonRichTextBox>().FirstOrDefault(c => c.Dock == DockStyle.Fill);
        }

        private void HighlightSyntax()
        {
            var editor = GetActiveEditor();
            if (editor == null) return;

            // --- إعدادات الألوان الاحترافية ---
            Color defaultColor = isDarkTheme ? Color.WhiteSmoke : Color.Black;
            Color keywordColor = Color.FromArgb(0, 156, 255);      // أزرق سماوي للكلمات المفتاحية (برنامج, متغير, اذا)
            Color stringColor = Color.FromArgb(230, 219, 116);     // أصفر/ذهبي للنصوص
            Color numberColor = Color.FromArgb(174, 129, 255);     // بنفسجي للأرقام
            Color operatorColor = Color.FromArgb(255, 128, 0);     // برتقالي للمعاملات (+, *, ==)
            Color punctuationColor = Color.FromArgb(160, 160, 160); // رمادي لعلامات الترقيم (؛ , .)

            // 1. حفظ موقع المؤشر
            int selectionStart = editor.SelectionStart;
            int selectionLength = editor.SelectionLength;

            // 2. إعادة تلوين النص بالكامل باللون الافتراضي
            editor.SelectAll();
            editor.SelectionColor = defaultColor;

            // 3. تحليل النص باستخدام الـ Lexer الصحيح (ArabicLangLexer)
            var lexer = new ArabicLangLexer(new AntlrInputStream(editor.Text));
            var tokens = lexer.GetAllTokens();

            // 4. المرور على التوكنات وتلوين كل شيء
            foreach (var token in tokens)
            {
                int tokenType = token.Type;
                int startIndex = token.StartIndex;
                int length = token.Text.Length;

                // تجاهل توكن نهاية الملف
                if (tokenType == ArabicLangLexer.Eof) continue;

                // -- تلوين الكلمات المفتاحية والأنواع --
                if (tokenType >= ArabicLangLexer.KW_PROGRAM && tokenType <= ArabicLangLexer.KW_RETURN)
                {
                    editor.Select(startIndex, length);
                    editor.SelectionColor = keywordColor;
                }
                // -- تلوين النصوص والقيم المنطقية --
                else if (tokenType == ArabicLangLexer.STRING_LITERAL)
                {
                    editor.Select(startIndex, length);
                    editor.SelectionColor = stringColor;
                }
                else if (tokenType == ArabicLangLexer.BOOLEAN_LITERAL)
                {
                    editor.Select(startIndex, length);
                    editor.SelectionColor = numberColor; // استخدام لون الأرقام للقيم المنطقية
                }
                // -- تلوين الأرقام --
                else if (tokenType == ArabicLangLexer.NUMERIC_LITERAL)
                {
                    editor.Select(startIndex, length);
                    editor.SelectionColor = numberColor;
                }
                // -- تلوين المعاملات الحسابية والمنطقية --
                else if (tokenType >= ArabicLangLexer.OP_LT && tokenType <= ArabicLangLexer.OP_NOT)
                {
                    editor.Select(startIndex, length);
                    editor.SelectionColor = operatorColor;
                }
                // -- تلوين علامات الترقيم والأقواس --
                else if (tokenType >= ArabicLangLexer.DOT && tokenType <= ArabicLangLexer.RPAREN)
                {
                    editor.Select(startIndex, length);
                    editor.SelectionColor = punctuationColor;
                }
                // ملاحظة: المعرفات (NAME_ID) ستأخذ اللون الافتراضي
            }

            // 5. إعادة المؤشر لمكانه الأصلي
            editor.Select(selectionStart, selectionLength);
            editor.SelectionColor = defaultColor;
            editor.Focus();
        }
    }
    public static class SimpleInputBox
    {
        public static string Show(string text, string caption, string defaultValue = "")
        {
            Form prompt = new Form()
            {
                Width = 400,
                Height = 160,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                Text = caption,
                StartPosition = FormStartPosition.CenterScreen
            };

            Label textLabel = new Label() { Left = 20, Top = 20, Text = text, AutoSize = true };
            TextBox inputBox = new TextBox() { Left = 20, Top = 50, Width = 340, Text = defaultValue };
            Button okButton = new Button() { Text = "موافق", Left = 200, Width = 80, Top = 85, DialogResult = DialogResult.OK };
            Button cancelButton = new Button() { Text = "إلغاء", Left = 280, Width = 80, Top = 85, DialogResult = DialogResult.Cancel };

            prompt.Controls.Add(textLabel);
            prompt.Controls.Add(inputBox);
            prompt.Controls.Add(okButton);
            prompt.Controls.Add(cancelButton);
            prompt.AcceptButton = okButton;
            prompt.CancelButton = cancelButton;

            return prompt.ShowDialog() == DialogResult.OK ? inputBox.Text : "";
        }
    }

}

