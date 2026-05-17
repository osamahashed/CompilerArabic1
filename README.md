Arabic Programming Language Compiler 🚀

A compiler for an Arabic programming language that translates Arabic source code into intermediate code and Assembly-like target code, then executes it using a built-in virtual machine.

📌 Overview

This project provides a complete Arabic programming environment that allows users to write programs using Arabic syntax and keywords.

The compiler performs:

Lexical Analysis
Syntax Analysis
Semantic Processing
Parse Tree Generation
Intermediate Code Generation
Assembly-like Code Generation
Virtual Execution

The project was built using C#, ANTLR4, and Windows Forms.

✨ Features
Arabic programming syntax
Interpreter and compiler support
Parse Tree visualization
Intermediate Code (Three-Address Code)
Assembly-like code generation
Built-in virtual machine executor
Code optimization stage
GUI editor using Krypton Toolkit
Support for:
Variables
Arithmetic operations
Conditions
Loops
Procedures
Input / Output
🛠 Technologies Used
C#
.NET Framework 4.7.2
Windows Forms
Krypton Toolkit
ANTLR4
Visual Studio 2015
🧠 Compilation Stages
Arabic Source Code
        ↓
Lexical Analysis
        ↓
Syntax Analysis
        ↓
Parse Tree
        ↓
Semantic Analysis
        ↓
Intermediate Code Generation
        ↓
Assembly-like Code Generation
        ↓
Virtual Machine Execution
📝 Example
Arabic Source Code
برنامج مضروب_عدد;
{
    متغير ن, مضروب, س : صحيح;

    اطبع("ادخل عدداً:");
    اقرأ(ن);

    مضروب = 1;

    كرر(س = 1 الى ن اضف 1) {
        مضروب = مضروب * س;
    };

    اطبع("الناتج:", مضروب);
}
.
Intermediate Code
مضروب = 1
س = 1

L0:
if س > ن goto L1

t0 = مضروب * س
مضروب = t0

س = س + 1
goto L0

L1:
print "الناتج:", مضروب
Assembly-like Code
MOV مضروب, 1
MOV س, 1

L0:
CMP س, ن
JG L1

MOV R1, مضروب
MUL R1, س
MOV مضروب, R1

ADD س, 1
JMP L0

L1:
OUT "الناتج:", مضروب
HALT
📂 Project Structure
pp/
│
├── ArabicLang.g4
├── MyLanguageVisitor.cs
├── IntermediateCodeGenerator.cs
├── TargetCodeGenerator.cs
├── TargetCodeExecutor.cs
├── CodeOptimizer.cs
├── OptimizationViewerForm.cs
├── ParseTreeViewerForm.cs
├── InputDialog.cs
└── Form1.cs
▶️ Running the Project
Requirements
Visual Studio 2015
.NET Framework 4.7.2
ANTLR4 Runtime
Steps
Open the project in Visual Studio.
Restore required packages.
Build the solution.
Run the application.
Write Arabic code and execute it.
🎯 Project Goals
Support Arabic programming languages
Simplify programming education for Arabic speakers
Demonstrate compiler construction stages
Build a complete educational compiler environment
🔮 Future Improvements
Real Assembly generation
Function support
Array and record support
Executable file generation
Advanced optimization
Debugger support
Syntax highlighting improvements
👨‍💻 Developer

Osama Hashed
Information Technology Graduate
