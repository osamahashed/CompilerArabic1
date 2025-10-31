grammar ArabicLang;

// ----------------------------------------------------------------------
// Parser Rules (Non-terminals)
// ----------------------------------------------------------------------

program
 : KW_PROGRAM NAME_ID SEMICOLON block DOT // استخدام SEMICOLON و DOT
 ;

block
 : LBRACE (definitions_part)? stmtList RBRACE // استخدام LBRACE/RBRACE
 ;

definitions_part
: (consts_def_part | types_def_part | vars_def_part | procs_def_part)+
;

// --- Constants Definitions ---
consts_def_part
 : KW_CONST (const_def)+
;

const_def
 : NAME_ID ASSIGN const_value SEMICOLON
 ;

// --- Types Definitions ---
types_def_part
 : KW_TYPE (type_def)+
 ;

type_def
 : NAME_ID ASSIGN complex_type SEMICOLON
 ;

complex_type
 : record_type
 | list_type
 ;

list_type
 : KW_LIST LBRACK INTEGER_NUM RBRACK KW_FROM data_type
 ;

record_type
 : KW_RECORD LBRACE fields_list RBRACE
 ;

fields_list
 : field_def (SEMICOLON field_def)*
 ;

field_def
 : NAME_ID (COMMA NAME_ID)* COLON data_type
 ;

// --- Variables Definitions ---
vars_def_part
 : KW_VAR (var_def)+
 ;

var_def
 : vars_group SEMICOLON
 ;

vars_group
 : NAME_ID (COMMA NAME_ID)* COLON data_type
 ;

// --- Procedures Definitions ---
procs_def_part
 : (proc_def)+
 ;

proc_def
 : proc_header proc_block SEMICOLON
 ;

proc_header
 : KW_PROC NAME_ID LPAREN (formal_params_list)? RPAREN
 ;

proc_block
 : block
 ;

formal_params_list
 : param_def (SEMICOLON param_def)*
 ;

param_def
 : (KW_BY_VALUE | KW_BY_REF)? vars_group
 ;

data_type
 : NAME_ID // اسم نوع مُعرَّف
 | KW_INTEGER
 | KW_REAL
 | KW_BOOLEAN
 | KW_CHAR
 | KW_STRING
 ;

// --- Statements ---
stmtList
 : (statement (SEMICOLON statement)* (SEMICOLON)?)?
 ;

statement
 : assign_stmt
 | input_stmt
 | output_stmt
 | call_stmt
 | conditional_stmt
 | repetition_stmt
 | block // قائمة التعليمات (ممثلة بالكتلة البرمجية)
 | KW_RETURN expression? // تعليمة ارجع
 ;

// استخدام بنية واحدة للتخلص من مشكلة Dangling Else
conditional_stmt
 : KW_IF LPAREN expression RPAREN KW_THEN statement ( KW_ELSE statement )?
 ;

repetition_stmt
 : repeat_for_stmt
 | repeat_while_stmt
 | repeat_until_stmt
 ;

repeat_for_stmt
 : KW_REPEAT LPAREN repetition_range RPAREN statement
 ;

repetition_range
 : NAME_ID ASSIGN expression KW_FOR expression (KW_ADD expression)?
 ;

repeat_while_stmt
 : KW_WHILE LPAREN expression RPAREN KW_CONTINUE statement
 ;

repeat_until_stmt
 : KW_DO statement KW_UNTIL LPAREN expression RPAREN
 ;

assign_stmt
 : var_access ASSIGN expression
 ;

input_stmt
 : KW_READ LPAREN var_access (COMMA var_access)* RPAREN
 ;

output_stmt
 : KW_PRINT LPAREN print_list RPAREN
 ;

print_list
 : print_element (COMMA print_element)*
 ;

print_element
 : var_access
 | STRING_LITERAL
 ;

call_stmt
 : NAME_ID LPAREN (actual_params_list)? RPAREN
 ;

actual_params_list
: actual_param (COMMA actual_param)*
 ;

actual_param
 : var_access
 | expression
 ;

// --- Expressions --- (تم تصحيحها لفرض الأسبقية)
expression
    : <assoc=right> expression OP_POWER expression # PowerExpr
    | expression (OP_MULT | OP_DIV_REAL | OP_DIV_INT | OP_MOD | OP_AND) expression # MultDivModAndExpr
    | expression (OP_PLUS | OP_MINUS | OP_OR) expression # AddSubOrExpr
    | expression (OP_EQ | OP_NE | OP_LT | OP_GT | OP_LE | OP_GE) expression # RelationalExpr
    | OP_NOT factor # NotExpr
    | OP_PLUS factor # UnaryPlus
    | OP_MINUS factor # UnaryMinus
    | factor # AtomicFactor
    ;

factor
 : LPAREN expression RPAREN
 | var_access
 | const_value
 ;

var_access
 : NAME_ID selector?
 ;

selector
 : indexed_selector
 | field_selector
 ;

indexed_selector
 : LBRACK expression RBRACK
 ;

field_selector
 : DOT NAME_ID
 ;

const_value
 : BOOLEAN_LITERAL
 | STRING_LITERAL
 | NUMERIC_LITERAL
 | NAME_ID // يمثل اسم الثابت
 ;

// ----------------------------------------------------------------------
// Lexer Rules (Terminals)
// ----------------------------------------------------------------------

// Keywords
KW_PROGRAM : 'برنامج';
KW_CONST : 'ثابت';
KW_TYPE : 'نوع';
KW_RECORD : 'سجل';
KW_LIST : 'قائمة';
KW_VAR : 'متغير';
KW_PROC : 'اجراء';
KW_BY_VALUE : 'بالقيمة';
KW_BY_REF : 'بالمرجع';
KW_INTEGER : 'صحيح';
KW_REAL : 'حقيقي';
KW_BOOLEAN : 'منطقي';
KW_CHAR : 'حرفي';
KW_STRING : 'خيط رمزي';
KW_PRINT : 'اطبع';
KW_READ : 'اقرأ';
KW_IF : 'اذا';
KW_THEN : 'فان';
KW_ELSE : 'والا';
KW_REPEAT : 'كرر';
KW_FOR : 'الى';
KW_ADD : 'اضف';
KW_WHILE : 'طالما';
KW_CONTINUE : 'استمر';
KW_DO : 'اعد';
KW_UNTIL : 'حتى';
KW_FROM : 'من';
KW_RETURN : 'ارجع' | 'عودة';

// Special Symbols
DOT : '.';
COLON : ':';
SEMICOLON : '؛' | ';';
COMMA : ',';
ASSIGN : '=';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
LPAREN : '(';
RPAREN : ')';

// Operators
OP_LT : '<';
OP_GT : '>';
OP_LE : '<=' | '=<';
OP_GE : '>=' | '=>';
OP_EQ : '==';
OP_NE : '!=' | '=!';
OP_PLUS : '+';
OP_MINUS : '-';
OP_OR : '||';
OP_POWER : '^';
OP_MULT : '*';
OP_DIV_REAL : '/';
OP_DIV_INT : '\\';
OP_MOD : '%';
OP_AND : '&&';
OP_NOT : '!';

// Identifiers
NAME_ID
 : LETTER (LETTER | DIGIT | '_')*
 ;

// Literals
BOOLEAN_LITERAL
 : 'صح' | 'خطأ'
 ;

// Numeric Literals
NUMERIC_LITERAL
 : REAL_NUM
 | INTEGER_NUM
 ;

REAL_NUM
 : DIGIT+ DOT DIGIT+
 ;

INTEGER_NUM
 : DIGIT+
 ;

STRING_LITERAL
 : '"' (~["\r\n])* '"'
 | '\'' (~['\r\n])* '\''
 ;

// Basic Lexer Components
fragment LETTER
 : '\u0621'..'\u064A' // Arabic letters
 | 'A'..'Z'
 | 'a'..'z'
 ;

fragment DIGIT
 : '0'..'9'
 | '\u0660'..'\u0669' // Arabic-Indic digits
 ;

// Whitespace and Comments
WS
 : [ \t\r\n\u00A0\u200F\u200E\uFEFF]+ -> skip
 ;