// Generated from c:/Users/MK/Desktop/مجلد جديد/pp/pp/ArabicLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ArabicLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KW_PROGRAM=1, KW_CONST=2, KW_TYPE=3, KW_RECORD=4, KW_LIST=5, KW_VAR=6, 
		KW_PROC=7, KW_BY_VALUE=8, KW_BY_REF=9, KW_INTEGER=10, KW_REAL=11, KW_BOOLEAN=12, 
		KW_CHAR=13, KW_STRING=14, KW_PRINT=15, KW_READ=16, KW_IF=17, KW_THEN=18, 
		KW_ELSE=19, KW_REPEAT=20, KW_FOR=21, KW_ADD=22, KW_WHILE=23, KW_CONTINUE=24, 
		KW_DO=25, KW_UNTIL=26, KW_FROM=27, KW_RETURN=28, DOT=29, COLON=30, SEMICOLON=31, 
		COMMA=32, ASSIGN=33, LBRACE=34, RBRACE=35, LBRACK=36, RBRACK=37, LPAREN=38, 
		RPAREN=39, OP_LT=40, OP_GT=41, OP_LE=42, OP_GE=43, OP_EQ=44, OP_NE=45, 
		OP_PLUS=46, OP_MINUS=47, OP_OR=48, OP_POWER=49, OP_MULT=50, OP_DIV_REAL=51, 
		OP_DIV_INT=52, OP_MOD=53, OP_AND=54, OP_NOT=55, NAME_ID=56, BOOLEAN_LITERAL=57, 
		NUMERIC_LITERAL=58, REAL_NUM=59, INTEGER_NUM=60, STRING_LITERAL=61, WS=62;
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_definitions_part = 2, RULE_consts_def_part = 3, 
		RULE_const_def = 4, RULE_types_def_part = 5, RULE_type_def = 6, RULE_complex_type = 7, 
		RULE_list_type = 8, RULE_record_type = 9, RULE_fields_list = 10, RULE_field_def = 11, 
		RULE_vars_def_part = 12, RULE_var_def = 13, RULE_vars_group = 14, RULE_procs_def_part = 15, 
		RULE_proc_def = 16, RULE_proc_header = 17, RULE_proc_block = 18, RULE_formal_params_list = 19, 
		RULE_param_def = 20, RULE_data_type = 21, RULE_stmtList = 22, RULE_statement = 23, 
		RULE_conditional_stmt = 24, RULE_repetition_stmt = 25, RULE_repeat_for_stmt = 26, 
		RULE_repetition_range = 27, RULE_repeat_while_stmt = 28, RULE_repeat_until_stmt = 29, 
		RULE_assign_stmt = 30, RULE_input_stmt = 31, RULE_output_stmt = 32, RULE_print_list = 33, 
		RULE_print_element = 34, RULE_call_stmt = 35, RULE_actual_params_list = 36, 
		RULE_actual_param = 37, RULE_expression = 38, RULE_factor = 39, RULE_var_access = 40, 
		RULE_selector = 41, RULE_indexed_selector = 42, RULE_field_selector = 43, 
		RULE_const_value = 44;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "definitions_part", "consts_def_part", "const_def", 
			"types_def_part", "type_def", "complex_type", "list_type", "record_type", 
			"fields_list", "field_def", "vars_def_part", "var_def", "vars_group", 
			"procs_def_part", "proc_def", "proc_header", "proc_block", "formal_params_list", 
			"param_def", "data_type", "stmtList", "statement", "conditional_stmt", 
			"repetition_stmt", "repeat_for_stmt", "repetition_range", "repeat_while_stmt", 
			"repeat_until_stmt", "assign_stmt", "input_stmt", "output_stmt", "print_list", 
			"print_element", "call_stmt", "actual_params_list", "actual_param", "expression", 
			"factor", "var_access", "selector", "indexed_selector", "field_selector", 
			"const_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\\u0628\\u0631\\u0646\\u0627\\u0645\\u062C'", "'\\u062B\\u0627\\u0628\\u062A'", 
			"'\\u0646\\u0648\\u0639'", "'\\u0633\\u062C\\u0644'", "'\\u0642\\u0627\\u0626\\u0645\\u0629'", 
			"'\\u0645\\u062A\\u063A\\u064A\\u0631'", "'\\u0627\\u062C\\u0631\\u0627\\u0621'", 
			"'\\u0628\\u0627\\u0644\\u0642\\u064A\\u0645\\u0629'", "'\\u0628\\u0627\\u0644\\u0645\\u0631\\u062C\\u0639'", 
			"'\\u0635\\u062D\\u064A\\u062D'", "'\\u062D\\u0642\\u064A\\u0642\\u064A'", 
			"'\\u0645\\u0646\\u0637\\u0642\\u064A'", "'\\u062D\\u0631\\u0641\\u064A'", 
			"'\\u062E\\u064A\\u0637 \\u0631\\u0645\\u0632\\u064A'", "'\\u0627\\u0637\\u0628\\u0639'", 
			"'\\u0627\\u0642\\u0631\\u0623'", "'\\u0627\\u0630\\u0627'", "'\\u0641\\u0627\\u0646'", 
			"'\\u0648\\u0627\\u0644\\u0627'", "'\\u0643\\u0631\\u0631'", "'\\u0627\\u0644\\u0649'", 
			"'\\u0627\\u0636\\u0641'", "'\\u0637\\u0627\\u0644\\u0645\\u0627'", "'\\u0627\\u0633\\u062A\\u0645\\u0631'", 
			"'\\u0627\\u0639\\u062F'", "'\\u062D\\u062A\\u0649'", "'\\u0645\\u0646'", 
			null, "'.'", "':'", null, "','", "'='", "'{'", "'}'", "'['", "']'", "'('", 
			"')'", "'<'", "'>'", null, null, "'=='", null, "'+'", "'-'", "'||'", 
			"'^'", "'*'", "'/'", "'\\'", "'%'", "'&&'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "KW_PROGRAM", "KW_CONST", "KW_TYPE", "KW_RECORD", "KW_LIST", "KW_VAR", 
			"KW_PROC", "KW_BY_VALUE", "KW_BY_REF", "KW_INTEGER", "KW_REAL", "KW_BOOLEAN", 
			"KW_CHAR", "KW_STRING", "KW_PRINT", "KW_READ", "KW_IF", "KW_THEN", "KW_ELSE", 
			"KW_REPEAT", "KW_FOR", "KW_ADD", "KW_WHILE", "KW_CONTINUE", "KW_DO", 
			"KW_UNTIL", "KW_FROM", "KW_RETURN", "DOT", "COLON", "SEMICOLON", "COMMA", 
			"ASSIGN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "LPAREN", "RPAREN", 
			"OP_LT", "OP_GT", "OP_LE", "OP_GE", "OP_EQ", "OP_NE", "OP_PLUS", "OP_MINUS", 
			"OP_OR", "OP_POWER", "OP_MULT", "OP_DIV_REAL", "OP_DIV_INT", "OP_MOD", 
			"OP_AND", "OP_NOT", "NAME_ID", "BOOLEAN_LITERAL", "NUMERIC_LITERAL", 
			"REAL_NUM", "INTEGER_NUM", "STRING_LITERAL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ArabicLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArabicLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode KW_PROGRAM() { return getToken(ArabicLangParser.KW_PROGRAM, 0); }
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(ArabicLangParser.SEMICOLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ArabicLangParser.DOT, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(KW_PROGRAM);
			setState(91);
			match(NAME_ID);
			setState(92);
			match(SEMICOLON);
			setState(93);
			block();
			setState(94);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ArabicLangParser.LBRACE, 0); }
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(ArabicLangParser.RBRACE, 0); }
		public Definitions_partContext definitions_part() {
			return getRuleContext(Definitions_partContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(LBRACE);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 204L) != 0)) {
				{
				setState(97);
				definitions_part();
				}
			}

			setState(100);
			stmtList();
			setState(101);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Definitions_partContext extends ParserRuleContext {
		public List<Consts_def_partContext> consts_def_part() {
			return getRuleContexts(Consts_def_partContext.class);
		}
		public Consts_def_partContext consts_def_part(int i) {
			return getRuleContext(Consts_def_partContext.class,i);
		}
		public List<Types_def_partContext> types_def_part() {
			return getRuleContexts(Types_def_partContext.class);
		}
		public Types_def_partContext types_def_part(int i) {
			return getRuleContext(Types_def_partContext.class,i);
		}
		public List<Vars_def_partContext> vars_def_part() {
			return getRuleContexts(Vars_def_partContext.class);
		}
		public Vars_def_partContext vars_def_part(int i) {
			return getRuleContext(Vars_def_partContext.class,i);
		}
		public List<Procs_def_partContext> procs_def_part() {
			return getRuleContexts(Procs_def_partContext.class);
		}
		public Procs_def_partContext procs_def_part(int i) {
			return getRuleContext(Procs_def_partContext.class,i);
		}
		public Definitions_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitions_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterDefinitions_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitDefinitions_part(this);
		}
	}

	public final Definitions_partContext definitions_part() throws RecognitionException {
		Definitions_partContext _localctx = new Definitions_partContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_definitions_part);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(107);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case KW_CONST:
					{
					setState(103);
					consts_def_part();
					}
					break;
				case KW_TYPE:
					{
					setState(104);
					types_def_part();
					}
					break;
				case KW_VAR:
					{
					setState(105);
					vars_def_part();
					}
					break;
				case KW_PROC:
					{
					setState(106);
					procs_def_part();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 204L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Consts_def_partContext extends ParserRuleContext {
		public TerminalNode KW_CONST() { return getToken(ArabicLangParser.KW_CONST, 0); }
		public List<Const_defContext> const_def() {
			return getRuleContexts(Const_defContext.class);
		}
		public Const_defContext const_def(int i) {
			return getRuleContext(Const_defContext.class,i);
		}
		public Consts_def_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_consts_def_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterConsts_def_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitConsts_def_part(this);
		}
	}

	public final Consts_def_partContext consts_def_part() throws RecognitionException {
		Consts_def_partContext _localctx = new Consts_def_partContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_consts_def_part);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(KW_CONST);
			setState(113); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(112);
					const_def();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(115); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Const_defContext extends ParserRuleContext {
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode ASSIGN() { return getToken(ArabicLangParser.ASSIGN, 0); }
		public Const_valueContext const_value() {
			return getRuleContext(Const_valueContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ArabicLangParser.SEMICOLON, 0); }
		public Const_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterConst_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitConst_def(this);
		}
	}

	public final Const_defContext const_def() throws RecognitionException {
		Const_defContext _localctx = new Const_defContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_const_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(NAME_ID);
			setState(118);
			match(ASSIGN);
			setState(119);
			const_value();
			setState(120);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Types_def_partContext extends ParserRuleContext {
		public TerminalNode KW_TYPE() { return getToken(ArabicLangParser.KW_TYPE, 0); }
		public List<Type_defContext> type_def() {
			return getRuleContexts(Type_defContext.class);
		}
		public Type_defContext type_def(int i) {
			return getRuleContext(Type_defContext.class,i);
		}
		public Types_def_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types_def_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterTypes_def_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitTypes_def_part(this);
		}
	}

	public final Types_def_partContext types_def_part() throws RecognitionException {
		Types_def_partContext _localctx = new Types_def_partContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_types_def_part);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(KW_TYPE);
			setState(124); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(123);
					type_def();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(126); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_defContext extends ParserRuleContext {
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode ASSIGN() { return getToken(ArabicLangParser.ASSIGN, 0); }
		public Complex_typeContext complex_type() {
			return getRuleContext(Complex_typeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ArabicLangParser.SEMICOLON, 0); }
		public Type_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterType_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitType_def(this);
		}
	}

	public final Type_defContext type_def() throws RecognitionException {
		Type_defContext _localctx = new Type_defContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(NAME_ID);
			setState(129);
			match(ASSIGN);
			setState(130);
			complex_type();
			setState(131);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Complex_typeContext extends ParserRuleContext {
		public Record_typeContext record_type() {
			return getRuleContext(Record_typeContext.class,0);
		}
		public List_typeContext list_type() {
			return getRuleContext(List_typeContext.class,0);
		}
		public Complex_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterComplex_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitComplex_type(this);
		}
	}

	public final Complex_typeContext complex_type() throws RecognitionException {
		Complex_typeContext _localctx = new Complex_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_complex_type);
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_RECORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				record_type();
				}
				break;
			case KW_LIST:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				list_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class List_typeContext extends ParserRuleContext {
		public TerminalNode KW_LIST() { return getToken(ArabicLangParser.KW_LIST, 0); }
		public TerminalNode LBRACK() { return getToken(ArabicLangParser.LBRACK, 0); }
		public TerminalNode INTEGER_NUM() { return getToken(ArabicLangParser.INTEGER_NUM, 0); }
		public TerminalNode RBRACK() { return getToken(ArabicLangParser.RBRACK, 0); }
		public TerminalNode KW_FROM() { return getToken(ArabicLangParser.KW_FROM, 0); }
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public List_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterList_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitList_type(this);
		}
	}

	public final List_typeContext list_type() throws RecognitionException {
		List_typeContext _localctx = new List_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_list_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(KW_LIST);
			setState(138);
			match(LBRACK);
			setState(139);
			match(INTEGER_NUM);
			setState(140);
			match(RBRACK);
			setState(141);
			match(KW_FROM);
			setState(142);
			data_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Record_typeContext extends ParserRuleContext {
		public TerminalNode KW_RECORD() { return getToken(ArabicLangParser.KW_RECORD, 0); }
		public TerminalNode LBRACE() { return getToken(ArabicLangParser.LBRACE, 0); }
		public Fields_listContext fields_list() {
			return getRuleContext(Fields_listContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(ArabicLangParser.RBRACE, 0); }
		public Record_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_record_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRecord_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRecord_type(this);
		}
	}

	public final Record_typeContext record_type() throws RecognitionException {
		Record_typeContext _localctx = new Record_typeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_record_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(KW_RECORD);
			setState(145);
			match(LBRACE);
			setState(146);
			fields_list();
			setState(147);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Fields_listContext extends ParserRuleContext {
		public List<Field_defContext> field_def() {
			return getRuleContexts(Field_defContext.class);
		}
		public Field_defContext field_def(int i) {
			return getRuleContext(Field_defContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(ArabicLangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(ArabicLangParser.SEMICOLON, i);
		}
		public Fields_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterFields_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitFields_list(this);
		}
	}

	public final Fields_listContext fields_list() throws RecognitionException {
		Fields_listContext _localctx = new Fields_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fields_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			field_def();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(150);
				match(SEMICOLON);
				setState(151);
				field_def();
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Field_defContext extends ParserRuleContext {
		public List<TerminalNode> NAME_ID() { return getTokens(ArabicLangParser.NAME_ID); }
		public TerminalNode NAME_ID(int i) {
			return getToken(ArabicLangParser.NAME_ID, i);
		}
		public TerminalNode COLON() { return getToken(ArabicLangParser.COLON, 0); }
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(ArabicLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArabicLangParser.COMMA, i);
		}
		public Field_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterField_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitField_def(this);
		}
	}

	public final Field_defContext field_def() throws RecognitionException {
		Field_defContext _localctx = new Field_defContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_field_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(NAME_ID);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(158);
				match(COMMA);
				setState(159);
				match(NAME_ID);
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(COLON);
			setState(166);
			data_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Vars_def_partContext extends ParserRuleContext {
		public TerminalNode KW_VAR() { return getToken(ArabicLangParser.KW_VAR, 0); }
		public List<Var_defContext> var_def() {
			return getRuleContexts(Var_defContext.class);
		}
		public Var_defContext var_def(int i) {
			return getRuleContext(Var_defContext.class,i);
		}
		public Vars_def_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars_def_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterVars_def_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitVars_def_part(this);
		}
	}

	public final Vars_def_partContext vars_def_part() throws RecognitionException {
		Vars_def_partContext _localctx = new Vars_def_partContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_vars_def_part);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(KW_VAR);
			setState(170); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(169);
					var_def();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(172); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_defContext extends ParserRuleContext {
		public Vars_groupContext vars_group() {
			return getRuleContext(Vars_groupContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ArabicLangParser.SEMICOLON, 0); }
		public Var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterVar_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitVar_def(this);
		}
	}

	public final Var_defContext var_def() throws RecognitionException {
		Var_defContext _localctx = new Var_defContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_var_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			vars_group();
			setState(175);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Vars_groupContext extends ParserRuleContext {
		public List<TerminalNode> NAME_ID() { return getTokens(ArabicLangParser.NAME_ID); }
		public TerminalNode NAME_ID(int i) {
			return getToken(ArabicLangParser.NAME_ID, i);
		}
		public TerminalNode COLON() { return getToken(ArabicLangParser.COLON, 0); }
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(ArabicLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArabicLangParser.COMMA, i);
		}
		public Vars_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterVars_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitVars_group(this);
		}
	}

	public final Vars_groupContext vars_group() throws RecognitionException {
		Vars_groupContext _localctx = new Vars_groupContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_vars_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(NAME_ID);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(178);
				match(COMMA);
				setState(179);
				match(NAME_ID);
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185);
			match(COLON);
			setState(186);
			data_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Procs_def_partContext extends ParserRuleContext {
		public List<Proc_defContext> proc_def() {
			return getRuleContexts(Proc_defContext.class);
		}
		public Proc_defContext proc_def(int i) {
			return getRuleContext(Proc_defContext.class,i);
		}
		public Procs_def_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procs_def_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterProcs_def_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitProcs_def_part(this);
		}
	}

	public final Procs_def_partContext procs_def_part() throws RecognitionException {
		Procs_def_partContext _localctx = new Procs_def_partContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_procs_def_part);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(188);
					proc_def();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(191); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Proc_defContext extends ParserRuleContext {
		public Proc_headerContext proc_header() {
			return getRuleContext(Proc_headerContext.class,0);
		}
		public Proc_blockContext proc_block() {
			return getRuleContext(Proc_blockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ArabicLangParser.SEMICOLON, 0); }
		public Proc_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterProc_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitProc_def(this);
		}
	}

	public final Proc_defContext proc_def() throws RecognitionException {
		Proc_defContext _localctx = new Proc_defContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_proc_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			proc_header();
			setState(194);
			proc_block();
			setState(195);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Proc_headerContext extends ParserRuleContext {
		public TerminalNode KW_PROC() { return getToken(ArabicLangParser.KW_PROC, 0); }
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public Formal_params_listContext formal_params_list() {
			return getRuleContext(Formal_params_listContext.class,0);
		}
		public Proc_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterProc_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitProc_header(this);
		}
	}

	public final Proc_headerContext proc_header() throws RecognitionException {
		Proc_headerContext _localctx = new Proc_headerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_proc_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(KW_PROC);
			setState(198);
			match(NAME_ID);
			setState(199);
			match(LPAREN);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037928704L) != 0)) {
				{
				setState(200);
				formal_params_list();
				}
			}

			setState(203);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Proc_blockContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Proc_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterProc_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitProc_block(this);
		}
	}

	public final Proc_blockContext proc_block() throws RecognitionException {
		Proc_blockContext _localctx = new Proc_blockContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_proc_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Formal_params_listContext extends ParserRuleContext {
		public List<Param_defContext> param_def() {
			return getRuleContexts(Param_defContext.class);
		}
		public Param_defContext param_def(int i) {
			return getRuleContext(Param_defContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(ArabicLangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(ArabicLangParser.SEMICOLON, i);
		}
		public Formal_params_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_params_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterFormal_params_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitFormal_params_list(this);
		}
	}

	public final Formal_params_listContext formal_params_list() throws RecognitionException {
		Formal_params_listContext _localctx = new Formal_params_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_formal_params_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			param_def();
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(208);
				match(SEMICOLON);
				setState(209);
				param_def();
				}
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Param_defContext extends ParserRuleContext {
		public Vars_groupContext vars_group() {
			return getRuleContext(Vars_groupContext.class,0);
		}
		public TerminalNode KW_BY_VALUE() { return getToken(ArabicLangParser.KW_BY_VALUE, 0); }
		public TerminalNode KW_BY_REF() { return getToken(ArabicLangParser.KW_BY_REF, 0); }
		public Param_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterParam_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitParam_def(this);
		}
	}

	public final Param_defContext param_def() throws RecognitionException {
		Param_defContext _localctx = new Param_defContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_param_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_BY_VALUE || _la==KW_BY_REF) {
				{
				setState(215);
				_la = _input.LA(1);
				if ( !(_la==KW_BY_VALUE || _la==KW_BY_REF) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(218);
			vars_group();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Data_typeContext extends ParserRuleContext {
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode KW_INTEGER() { return getToken(ArabicLangParser.KW_INTEGER, 0); }
		public TerminalNode KW_REAL() { return getToken(ArabicLangParser.KW_REAL, 0); }
		public TerminalNode KW_BOOLEAN() { return getToken(ArabicLangParser.KW_BOOLEAN, 0); }
		public TerminalNode KW_CHAR() { return getToken(ArabicLangParser.KW_CHAR, 0); }
		public TerminalNode KW_STRING() { return getToken(ArabicLangParser.KW_STRING, 0); }
		public Data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterData_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitData_type(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037959680L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtListContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(ArabicLangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(ArabicLangParser.SEMICOLON, i);
		}
		public StmtListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterStmtList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitStmtList(this);
		}
	}

	public final StmtListContext stmtList() throws RecognitionException {
		StmtListContext _localctx = new StmtListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stmtList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057611529453568L) != 0)) {
				{
				setState(222);
				statement();
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(223);
						match(SEMICOLON);
						setState(224);
						statement();
						}
						} 
					}
					setState(229);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLON) {
					{
					setState(230);
					match(SEMICOLON);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public Input_stmtContext input_stmt() {
			return getRuleContext(Input_stmtContext.class,0);
		}
		public Output_stmtContext output_stmt() {
			return getRuleContext(Output_stmtContext.class,0);
		}
		public Call_stmtContext call_stmt() {
			return getRuleContext(Call_stmtContext.class,0);
		}
		public Conditional_stmtContext conditional_stmt() {
			return getRuleContext(Conditional_stmtContext.class,0);
		}
		public Repetition_stmtContext repetition_stmt() {
			return getRuleContext(Repetition_stmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode KW_RETURN() { return getToken(ArabicLangParser.KW_RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_statement);
		int _la;
		try {
			setState(246);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				assign_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				input_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(237);
				output_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
				call_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(239);
				conditional_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(240);
				repetition_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(241);
				block();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(242);
				match(KW_RETURN);
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2846486345608593408L) != 0)) {
					{
					setState(243);
					expression(0);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Conditional_stmtContext extends ParserRuleContext {
		public TerminalNode KW_IF() { return getToken(ArabicLangParser.KW_IF, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public TerminalNode KW_THEN() { return getToken(ArabicLangParser.KW_THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode KW_ELSE() { return getToken(ArabicLangParser.KW_ELSE, 0); }
		public Conditional_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterConditional_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitConditional_stmt(this);
		}
	}

	public final Conditional_stmtContext conditional_stmt() throws RecognitionException {
		Conditional_stmtContext _localctx = new Conditional_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_conditional_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(KW_IF);
			setState(249);
			match(LPAREN);
			setState(250);
			expression(0);
			setState(251);
			match(RPAREN);
			setState(252);
			match(KW_THEN);
			setState(253);
			statement();
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(254);
				match(KW_ELSE);
				setState(255);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Repetition_stmtContext extends ParserRuleContext {
		public Repeat_for_stmtContext repeat_for_stmt() {
			return getRuleContext(Repeat_for_stmtContext.class,0);
		}
		public Repeat_while_stmtContext repeat_while_stmt() {
			return getRuleContext(Repeat_while_stmtContext.class,0);
		}
		public Repeat_until_stmtContext repeat_until_stmt() {
			return getRuleContext(Repeat_until_stmtContext.class,0);
		}
		public Repetition_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repetition_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRepetition_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRepetition_stmt(this);
		}
	}

	public final Repetition_stmtContext repetition_stmt() throws RecognitionException {
		Repetition_stmtContext _localctx = new Repetition_stmtContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_repetition_stmt);
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_REPEAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				repeat_for_stmt();
				}
				break;
			case KW_WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				repeat_while_stmt();
				}
				break;
			case KW_DO:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				repeat_until_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Repeat_for_stmtContext extends ParserRuleContext {
		public TerminalNode KW_REPEAT() { return getToken(ArabicLangParser.KW_REPEAT, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public Repetition_rangeContext repetition_range() {
			return getRuleContext(Repetition_rangeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Repeat_for_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRepeat_for_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRepeat_for_stmt(this);
		}
	}

	public final Repeat_for_stmtContext repeat_for_stmt() throws RecognitionException {
		Repeat_for_stmtContext _localctx = new Repeat_for_stmtContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_repeat_for_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(KW_REPEAT);
			setState(264);
			match(LPAREN);
			setState(265);
			repetition_range();
			setState(266);
			match(RPAREN);
			setState(267);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Repetition_rangeContext extends ParserRuleContext {
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode ASSIGN() { return getToken(ArabicLangParser.ASSIGN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode KW_FOR() { return getToken(ArabicLangParser.KW_FOR, 0); }
		public TerminalNode KW_ADD() { return getToken(ArabicLangParser.KW_ADD, 0); }
		public Repetition_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repetition_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRepetition_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRepetition_range(this);
		}
	}

	public final Repetition_rangeContext repetition_range() throws RecognitionException {
		Repetition_rangeContext _localctx = new Repetition_rangeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_repetition_range);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(NAME_ID);
			setState(270);
			match(ASSIGN);
			setState(271);
			expression(0);
			setState(272);
			match(KW_FOR);
			setState(273);
			expression(0);
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ADD) {
				{
				setState(274);
				match(KW_ADD);
				setState(275);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Repeat_while_stmtContext extends ParserRuleContext {
		public TerminalNode KW_WHILE() { return getToken(ArabicLangParser.KW_WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public TerminalNode KW_CONTINUE() { return getToken(ArabicLangParser.KW_CONTINUE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Repeat_while_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRepeat_while_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRepeat_while_stmt(this);
		}
	}

	public final Repeat_while_stmtContext repeat_while_stmt() throws RecognitionException {
		Repeat_while_stmtContext _localctx = new Repeat_while_stmtContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_repeat_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(KW_WHILE);
			setState(279);
			match(LPAREN);
			setState(280);
			expression(0);
			setState(281);
			match(RPAREN);
			setState(282);
			match(KW_CONTINUE);
			setState(283);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Repeat_until_stmtContext extends ParserRuleContext {
		public TerminalNode KW_DO() { return getToken(ArabicLangParser.KW_DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode KW_UNTIL() { return getToken(ArabicLangParser.KW_UNTIL, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public Repeat_until_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_until_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRepeat_until_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRepeat_until_stmt(this);
		}
	}

	public final Repeat_until_stmtContext repeat_until_stmt() throws RecognitionException {
		Repeat_until_stmtContext _localctx = new Repeat_until_stmtContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_repeat_until_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(KW_DO);
			setState(286);
			statement();
			setState(287);
			match(KW_UNTIL);
			setState(288);
			match(LPAREN);
			setState(289);
			expression(0);
			setState(290);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Assign_stmtContext extends ParserRuleContext {
		public Var_accessContext var_access() {
			return getRuleContext(Var_accessContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ArabicLangParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterAssign_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitAssign_stmt(this);
		}
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_assign_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			var_access();
			setState(293);
			match(ASSIGN);
			setState(294);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Input_stmtContext extends ParserRuleContext {
		public TerminalNode KW_READ() { return getToken(ArabicLangParser.KW_READ, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public List<Var_accessContext> var_access() {
			return getRuleContexts(Var_accessContext.class);
		}
		public Var_accessContext var_access(int i) {
			return getRuleContext(Var_accessContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ArabicLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArabicLangParser.COMMA, i);
		}
		public Input_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterInput_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitInput_stmt(this);
		}
	}

	public final Input_stmtContext input_stmt() throws RecognitionException {
		Input_stmtContext _localctx = new Input_stmtContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_input_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(KW_READ);
			setState(297);
			match(LPAREN);
			setState(298);
			var_access();
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(299);
				match(COMMA);
				setState(300);
				var_access();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Output_stmtContext extends ParserRuleContext {
		public TerminalNode KW_PRINT() { return getToken(ArabicLangParser.KW_PRINT, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public Print_listContext print_list() {
			return getRuleContext(Print_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public Output_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_output_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterOutput_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitOutput_stmt(this);
		}
	}

	public final Output_stmtContext output_stmt() throws RecognitionException {
		Output_stmtContext _localctx = new Output_stmtContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_output_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(KW_PRINT);
			setState(309);
			match(LPAREN);
			setState(310);
			print_list();
			setState(311);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Print_listContext extends ParserRuleContext {
		public List<Print_elementContext> print_element() {
			return getRuleContexts(Print_elementContext.class);
		}
		public Print_elementContext print_element(int i) {
			return getRuleContext(Print_elementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ArabicLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArabicLangParser.COMMA, i);
		}
		public Print_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterPrint_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitPrint_list(this);
		}
	}

	public final Print_listContext print_list() throws RecognitionException {
		Print_listContext _localctx = new Print_listContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_print_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			print_element();
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(314);
				match(COMMA);
				setState(315);
				print_element();
				}
				}
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Print_elementContext extends ParserRuleContext {
		public Var_accessContext var_access() {
			return getRuleContext(Var_accessContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(ArabicLangParser.STRING_LITERAL, 0); }
		public Print_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterPrint_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitPrint_element(this);
		}
	}

	public final Print_elementContext print_element() throws RecognitionException {
		Print_elementContext _localctx = new Print_elementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_print_element);
		try {
			setState(323);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME_ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				var_access();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Call_stmtContext extends ParserRuleContext {
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public Actual_params_listContext actual_params_list() {
			return getRuleContext(Actual_params_listContext.class,0);
		}
		public Call_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterCall_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitCall_stmt(this);
		}
	}

	public final Call_stmtContext call_stmt() throws RecognitionException {
		Call_stmtContext _localctx = new Call_stmtContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_call_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(NAME_ID);
			setState(326);
			match(LPAREN);
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2846486345608593408L) != 0)) {
				{
				setState(327);
				actual_params_list();
				}
			}

			setState(330);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Actual_params_listContext extends ParserRuleContext {
		public List<Actual_paramContext> actual_param() {
			return getRuleContexts(Actual_paramContext.class);
		}
		public Actual_paramContext actual_param(int i) {
			return getRuleContext(Actual_paramContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ArabicLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArabicLangParser.COMMA, i);
		}
		public Actual_params_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actual_params_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterActual_params_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitActual_params_list(this);
		}
	}

	public final Actual_params_listContext actual_params_list() throws RecognitionException {
		Actual_params_listContext _localctx = new Actual_params_listContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_actual_params_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			actual_param();
			setState(337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(333);
				match(COMMA);
				setState(334);
				actual_param();
				}
				}
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Actual_paramContext extends ParserRuleContext {
		public Var_accessContext var_access() {
			return getRuleContext(Var_accessContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Actual_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actual_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterActual_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitActual_param(this);
		}
	}

	public final Actual_paramContext actual_param() throws RecognitionException {
		Actual_paramContext _localctx = new Actual_paramContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_actual_param);
		try {
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				var_access();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowerExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_POWER() { return getToken(ArabicLangParser.OP_POWER, 0); }
		public PowerExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterPowerExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitPowerExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubOrExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_PLUS() { return getToken(ArabicLangParser.OP_PLUS, 0); }
		public TerminalNode OP_MINUS() { return getToken(ArabicLangParser.OP_MINUS, 0); }
		public TerminalNode OP_OR() { return getToken(ArabicLangParser.OP_OR, 0); }
		public AddSubOrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterAddSubOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitAddSubOrExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryPlusContext extends ExpressionContext {
		public TerminalNode OP_PLUS() { return getToken(ArabicLangParser.OP_PLUS, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public UnaryPlusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterUnaryPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitUnaryPlus(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultDivModAndExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_MULT() { return getToken(ArabicLangParser.OP_MULT, 0); }
		public TerminalNode OP_DIV_REAL() { return getToken(ArabicLangParser.OP_DIV_REAL, 0); }
		public TerminalNode OP_DIV_INT() { return getToken(ArabicLangParser.OP_DIV_INT, 0); }
		public TerminalNode OP_MOD() { return getToken(ArabicLangParser.OP_MOD, 0); }
		public TerminalNode OP_AND() { return getToken(ArabicLangParser.OP_AND, 0); }
		public MultDivModAndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterMultDivModAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitMultDivModAndExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusContext extends ExpressionContext {
		public TerminalNode OP_MINUS() { return getToken(ArabicLangParser.OP_MINUS, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public UnaryMinusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterUnaryMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitUnaryMinus(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExpressionContext {
		public TerminalNode OP_NOT() { return getToken(ArabicLangParser.OP_NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public NotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitNotExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OP_EQ() { return getToken(ArabicLangParser.OP_EQ, 0); }
		public TerminalNode OP_NE() { return getToken(ArabicLangParser.OP_NE, 0); }
		public TerminalNode OP_LT() { return getToken(ArabicLangParser.OP_LT, 0); }
		public TerminalNode OP_GT() { return getToken(ArabicLangParser.OP_GT, 0); }
		public TerminalNode OP_LE() { return getToken(ArabicLangParser.OP_LE, 0); }
		public TerminalNode OP_GE() { return getToken(ArabicLangParser.OP_GE, 0); }
		public RelationalExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitRelationalExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomicFactorContext extends ExpressionContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public AtomicFactorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterAtomicFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitAtomicFactor(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 76;
		enterRecursionRule(_localctx, 76, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP_NOT:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(345);
				match(OP_NOT);
				setState(346);
				factor();
				}
				break;
			case OP_PLUS:
				{
				_localctx = new UnaryPlusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(347);
				match(OP_PLUS);
				setState(348);
				factor();
				}
				break;
			case OP_MINUS:
				{
				_localctx = new UnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(349);
				match(OP_MINUS);
				setState(350);
				factor();
				}
				break;
			case LPAREN:
			case NAME_ID:
			case BOOLEAN_LITERAL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				{
				_localctx = new AtomicFactorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(351);
				factor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(368);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(366);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						_localctx = new PowerExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(354);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(355);
						match(OP_POWER);
						setState(356);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new MultDivModAndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(357);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(358);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34902897112121344L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(359);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new AddSubOrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(360);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(361);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 492581209243648L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(362);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new RelationalExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(363);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(364);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 69269232549888L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(365);
						expression(6);
						}
						break;
					}
					} 
				}
				setState(370);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ArabicLangParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ArabicLangParser.RPAREN, 0); }
		public Var_accessContext var_access() {
			return getRuleContext(Var_accessContext.class,0);
		}
		public Const_valueContext const_value() {
			return getRuleContext(Const_valueContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_factor);
		try {
			setState(377);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				match(LPAREN);
				setState(372);
				expression(0);
				setState(373);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				var_access();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				const_value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_accessContext extends ParserRuleContext {
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public SelectorContext selector() {
			return getRuleContext(SelectorContext.class,0);
		}
		public Var_accessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_access; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterVar_access(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitVar_access(this);
		}
	}

	public final Var_accessContext var_access() throws RecognitionException {
		Var_accessContext _localctx = new Var_accessContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_var_access);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(NAME_ID);
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(380);
				selector();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectorContext extends ParserRuleContext {
		public Indexed_selectorContext indexed_selector() {
			return getRuleContext(Indexed_selectorContext.class,0);
		}
		public Field_selectorContext field_selector() {
			return getRuleContext(Field_selectorContext.class,0);
		}
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitSelector(this);
		}
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_selector);
		try {
			setState(385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				indexed_selector();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				field_selector();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Indexed_selectorContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(ArabicLangParser.LBRACK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(ArabicLangParser.RBRACK, 0); }
		public Indexed_selectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexed_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterIndexed_selector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitIndexed_selector(this);
		}
	}

	public final Indexed_selectorContext indexed_selector() throws RecognitionException {
		Indexed_selectorContext _localctx = new Indexed_selectorContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_indexed_selector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(LBRACK);
			setState(388);
			expression(0);
			setState(389);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Field_selectorContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(ArabicLangParser.DOT, 0); }
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public Field_selectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterField_selector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitField_selector(this);
		}
	}

	public final Field_selectorContext field_selector() throws RecognitionException {
		Field_selectorContext _localctx = new Field_selectorContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_field_selector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(DOT);
			setState(392);
			match(NAME_ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Const_valueContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(ArabicLangParser.BOOLEAN_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(ArabicLangParser.STRING_LITERAL, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(ArabicLangParser.NUMERIC_LITERAL, 0); }
		public TerminalNode NAME_ID() { return getToken(ArabicLangParser.NAME_ID, 0); }
		public Const_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).enterConst_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArabicLangListener ) ((ArabicLangListener)listener).exitConst_value(this);
		}
	}

	public final Const_valueContext const_value() throws RecognitionException {
		Const_valueContext _localctx = new Const_valueContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_const_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2810246167479189504L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 38:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u018d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0003\u0001c\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002l\b"+
		"\u0002\u000b\u0002\f\u0002m\u0001\u0003\u0001\u0003\u0004\u0003r\b\u0003"+
		"\u000b\u0003\f\u0003s\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0004\u0005}\b\u0005\u000b\u0005"+
		"\f\u0005~\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0088\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0005\n\u0099\b\n\n\n\f\n\u009c\t\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00a1\b\u000b\n\u000b\f\u000b\u00a4"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0004\f\u00ab"+
		"\b\f\u000b\f\f\f\u00ac\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u00b5\b\u000e\n\u000e\f\u000e\u00b8\t\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0004\u000f\u00be\b\u000f\u000b"+
		"\u000f\f\u000f\u00bf\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00ca\b\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u00d3\b\u0013\n\u0013\f\u0013\u00d6\t\u0013\u0001\u0014"+
		"\u0003\u0014\u00d9\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u00e2\b\u0016\n\u0016"+
		"\f\u0016\u00e5\t\u0016\u0001\u0016\u0003\u0016\u00e8\b\u0016\u0003\u0016"+
		"\u00ea\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u00f5\b\u0017"+
		"\u0003\u0017\u00f7\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0101\b\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0106\b\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0003\u001b\u0115\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0005\u001f\u012e\b\u001f\n\u001f\f\u001f\u0131\t\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001"+
		"!\u0005!\u013d\b!\n!\f!\u0140\t!\u0001\"\u0001\"\u0003\"\u0144\b\"\u0001"+
		"#\u0001#\u0001#\u0003#\u0149\b#\u0001#\u0001#\u0001$\u0001$\u0001$\u0005"+
		"$\u0150\b$\n$\f$\u0153\t$\u0001%\u0001%\u0003%\u0157\b%\u0001&\u0001&"+
		"\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u0161\b&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0005&\u016f\b&\n&\f&\u0172\t&\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0003\'\u017a\b\'\u0001(\u0001(\u0003(\u017e\b(\u0001)\u0001"+
		")\u0003)\u0182\b)\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001"+
		",\u0001,\u0001,\u0000\u0001L-\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"TVX\u0000\u0006\u0001\u0000\b\t\u0002\u0000\n\u000e88\u0001\u000026\u0001"+
		"\u0000.0\u0001\u0000(-\u0002\u00008:==\u018f\u0000Z\u0001\u0000\u0000"+
		"\u0000\u0002`\u0001\u0000\u0000\u0000\u0004k\u0001\u0000\u0000\u0000\u0006"+
		"o\u0001\u0000\u0000\u0000\bu\u0001\u0000\u0000\u0000\nz\u0001\u0000\u0000"+
		"\u0000\f\u0080\u0001\u0000\u0000\u0000\u000e\u0087\u0001\u0000\u0000\u0000"+
		"\u0010\u0089\u0001\u0000\u0000\u0000\u0012\u0090\u0001\u0000\u0000\u0000"+
		"\u0014\u0095\u0001\u0000\u0000\u0000\u0016\u009d\u0001\u0000\u0000\u0000"+
		"\u0018\u00a8\u0001\u0000\u0000\u0000\u001a\u00ae\u0001\u0000\u0000\u0000"+
		"\u001c\u00b1\u0001\u0000\u0000\u0000\u001e\u00bd\u0001\u0000\u0000\u0000"+
		" \u00c1\u0001\u0000\u0000\u0000\"\u00c5\u0001\u0000\u0000\u0000$\u00cd"+
		"\u0001\u0000\u0000\u0000&\u00cf\u0001\u0000\u0000\u0000(\u00d8\u0001\u0000"+
		"\u0000\u0000*\u00dc\u0001\u0000\u0000\u0000,\u00e9\u0001\u0000\u0000\u0000"+
		".\u00f6\u0001\u0000\u0000\u00000\u00f8\u0001\u0000\u0000\u00002\u0105"+
		"\u0001\u0000\u0000\u00004\u0107\u0001\u0000\u0000\u00006\u010d\u0001\u0000"+
		"\u0000\u00008\u0116\u0001\u0000\u0000\u0000:\u011d\u0001\u0000\u0000\u0000"+
		"<\u0124\u0001\u0000\u0000\u0000>\u0128\u0001\u0000\u0000\u0000@\u0134"+
		"\u0001\u0000\u0000\u0000B\u0139\u0001\u0000\u0000\u0000D\u0143\u0001\u0000"+
		"\u0000\u0000F\u0145\u0001\u0000\u0000\u0000H\u014c\u0001\u0000\u0000\u0000"+
		"J\u0156\u0001\u0000\u0000\u0000L\u0160\u0001\u0000\u0000\u0000N\u0179"+
		"\u0001\u0000\u0000\u0000P\u017b\u0001\u0000\u0000\u0000R\u0181\u0001\u0000"+
		"\u0000\u0000T\u0183\u0001\u0000\u0000\u0000V\u0187\u0001\u0000\u0000\u0000"+
		"X\u018a\u0001\u0000\u0000\u0000Z[\u0005\u0001\u0000\u0000[\\\u00058\u0000"+
		"\u0000\\]\u0005\u001f\u0000\u0000]^\u0003\u0002\u0001\u0000^_\u0005\u001d"+
		"\u0000\u0000_\u0001\u0001\u0000\u0000\u0000`b\u0005\"\u0000\u0000ac\u0003"+
		"\u0004\u0002\u0000ba\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"cd\u0001\u0000\u0000\u0000de\u0003,\u0016\u0000ef\u0005#\u0000\u0000f"+
		"\u0003\u0001\u0000\u0000\u0000gl\u0003\u0006\u0003\u0000hl\u0003\n\u0005"+
		"\u0000il\u0003\u0018\f\u0000jl\u0003\u001e\u000f\u0000kg\u0001\u0000\u0000"+
		"\u0000kh\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kj\u0001\u0000"+
		"\u0000\u0000lm\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001"+
		"\u0000\u0000\u0000n\u0005\u0001\u0000\u0000\u0000oq\u0005\u0002\u0000"+
		"\u0000pr\u0003\b\u0004\u0000qp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000"+
		"\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000t\u0007\u0001"+
		"\u0000\u0000\u0000uv\u00058\u0000\u0000vw\u0005!\u0000\u0000wx\u0003X"+
		",\u0000xy\u0005\u001f\u0000\u0000y\t\u0001\u0000\u0000\u0000z|\u0005\u0003"+
		"\u0000\u0000{}\u0003\f\u0006\u0000|{\u0001\u0000\u0000\u0000}~\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000"+
		"\u007f\u000b\u0001\u0000\u0000\u0000\u0080\u0081\u00058\u0000\u0000\u0081"+
		"\u0082\u0005!\u0000\u0000\u0082\u0083\u0003\u000e\u0007\u0000\u0083\u0084"+
		"\u0005\u001f\u0000\u0000\u0084\r\u0001\u0000\u0000\u0000\u0085\u0088\u0003"+
		"\u0012\t\u0000\u0086\u0088\u0003\u0010\b\u0000\u0087\u0085\u0001\u0000"+
		"\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u000f\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005\u0005\u0000\u0000\u008a\u008b\u0005$\u0000"+
		"\u0000\u008b\u008c\u0005<\u0000\u0000\u008c\u008d\u0005%\u0000\u0000\u008d"+
		"\u008e\u0005\u001b\u0000\u0000\u008e\u008f\u0003*\u0015\u0000\u008f\u0011"+
		"\u0001\u0000\u0000\u0000\u0090\u0091\u0005\u0004\u0000\u0000\u0091\u0092"+
		"\u0005\"\u0000\u0000\u0092\u0093\u0003\u0014\n\u0000\u0093\u0094\u0005"+
		"#\u0000\u0000\u0094\u0013\u0001\u0000\u0000\u0000\u0095\u009a\u0003\u0016"+
		"\u000b\u0000\u0096\u0097\u0005\u001f\u0000\u0000\u0097\u0099\u0003\u0016"+
		"\u000b\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009c\u0001\u0000"+
		"\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000"+
		"\u0000\u0000\u009b\u0015\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000"+
		"\u0000\u0000\u009d\u00a2\u00058\u0000\u0000\u009e\u009f\u0005 \u0000\u0000"+
		"\u009f\u00a1\u00058\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a5\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005\u001e\u0000\u0000\u00a6"+
		"\u00a7\u0003*\u0015\u0000\u00a7\u0017\u0001\u0000\u0000\u0000\u00a8\u00aa"+
		"\u0005\u0006\u0000\u0000\u00a9\u00ab\u0003\u001a\r\u0000\u00aa\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u0019\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0003\u001c\u000e\u0000\u00af\u00b0\u0005"+
		"\u001f\u0000\u0000\u00b0\u001b\u0001\u0000\u0000\u0000\u00b1\u00b6\u0005"+
		"8\u0000\u0000\u00b2\u00b3\u0005 \u0000\u0000\u00b3\u00b5\u00058\u0000"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0005\u001e\u0000\u0000\u00ba\u00bb\u0003*\u0015\u0000"+
		"\u00bb\u001d\u0001\u0000\u0000\u0000\u00bc\u00be\u0003 \u0010\u0000\u00bd"+
		"\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0"+
		"\u001f\u0001\u0000\u0000\u0000\u00c1\u00c2\u0003\"\u0011\u0000\u00c2\u00c3"+
		"\u0003$\u0012\u0000\u00c3\u00c4\u0005\u001f\u0000\u0000\u00c4!\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0005\u0007\u0000\u0000\u00c6\u00c7\u00058\u0000"+
		"\u0000\u00c7\u00c9\u0005&\u0000\u0000\u00c8\u00ca\u0003&\u0013\u0000\u00c9"+
		"\u00c8\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005\'\u0000\u0000\u00cc#"+
		"\u0001\u0000\u0000\u0000\u00cd\u00ce\u0003\u0002\u0001\u0000\u00ce%\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d4\u0003(\u0014\u0000\u00d0\u00d1\u0005\u001f"+
		"\u0000\u0000\u00d1\u00d3\u0003(\u0014\u0000\u00d2\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\'\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00d9\u0007\u0000\u0000\u0000"+
		"\u00d8\u00d7\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000"+
		"\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u00db\u0003\u001c\u000e\u0000"+
		"\u00db)\u0001\u0000\u0000\u0000\u00dc\u00dd\u0007\u0001\u0000\u0000\u00dd"+
		"+\u0001\u0000\u0000\u0000\u00de\u00e3\u0003.\u0017\u0000\u00df\u00e0\u0005"+
		"\u001f\u0000\u0000\u00e0\u00e2\u0003.\u0017\u0000\u00e1\u00df\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e8\u0005\u001f"+
		"\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e8\u00ea\u0001\u0000\u0000\u0000\u00e9\u00de\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea-\u0001\u0000\u0000"+
		"\u0000\u00eb\u00f7\u0003<\u001e\u0000\u00ec\u00f7\u0003>\u001f\u0000\u00ed"+
		"\u00f7\u0003@ \u0000\u00ee\u00f7\u0003F#\u0000\u00ef\u00f7\u00030\u0018"+
		"\u0000\u00f0\u00f7\u00032\u0019\u0000\u00f1\u00f7\u0003\u0002\u0001\u0000"+
		"\u00f2\u00f4\u0005\u001c\u0000\u0000\u00f3\u00f5\u0003L&\u0000\u00f4\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u00f7"+
		"\u0001\u0000\u0000\u0000\u00f6\u00eb\u0001\u0000\u0000\u0000\u00f6\u00ec"+
		"\u0001\u0000\u0000\u0000\u00f6\u00ed\u0001\u0000\u0000\u0000\u00f6\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f6\u00ef\u0001\u0000\u0000\u0000\u00f6\u00f0"+
		"\u0001\u0000\u0000\u0000\u00f6\u00f1\u0001\u0000\u0000\u0000\u00f6\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f7/\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005"+
		"\u0011\u0000\u0000\u00f9\u00fa\u0005&\u0000\u0000\u00fa\u00fb\u0003L&"+
		"\u0000\u00fb\u00fc\u0005\'\u0000\u0000\u00fc\u00fd\u0005\u0012\u0000\u0000"+
		"\u00fd\u0100\u0003.\u0017\u0000\u00fe\u00ff\u0005\u0013\u0000\u0000\u00ff"+
		"\u0101\u0003.\u0017\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0100\u0101"+
		"\u0001\u0000\u0000\u0000\u01011\u0001\u0000\u0000\u0000\u0102\u0106\u0003"+
		"4\u001a\u0000\u0103\u0106\u00038\u001c\u0000\u0104\u0106\u0003:\u001d"+
		"\u0000\u0105\u0102\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000"+
		"\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u01063\u0001\u0000\u0000\u0000"+
		"\u0107\u0108\u0005\u0014\u0000\u0000\u0108\u0109\u0005&\u0000\u0000\u0109"+
		"\u010a\u00036\u001b\u0000\u010a\u010b\u0005\'\u0000\u0000\u010b\u010c"+
		"\u0003.\u0017\u0000\u010c5\u0001\u0000\u0000\u0000\u010d\u010e\u00058"+
		"\u0000\u0000\u010e\u010f\u0005!\u0000\u0000\u010f\u0110\u0003L&\u0000"+
		"\u0110\u0111\u0005\u0015\u0000\u0000\u0111\u0114\u0003L&\u0000\u0112\u0113"+
		"\u0005\u0016\u0000\u0000\u0113\u0115\u0003L&\u0000\u0114\u0112\u0001\u0000"+
		"\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u01157\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0005\u0017\u0000\u0000\u0117\u0118\u0005&\u0000\u0000"+
		"\u0118\u0119\u0003L&\u0000\u0119\u011a\u0005\'\u0000\u0000\u011a\u011b"+
		"\u0005\u0018\u0000\u0000\u011b\u011c\u0003.\u0017\u0000\u011c9\u0001\u0000"+
		"\u0000\u0000\u011d\u011e\u0005\u0019\u0000\u0000\u011e\u011f\u0003.\u0017"+
		"\u0000\u011f\u0120\u0005\u001a\u0000\u0000\u0120\u0121\u0005&\u0000\u0000"+
		"\u0121\u0122\u0003L&\u0000\u0122\u0123\u0005\'\u0000\u0000\u0123;\u0001"+
		"\u0000\u0000\u0000\u0124\u0125\u0003P(\u0000\u0125\u0126\u0005!\u0000"+
		"\u0000\u0126\u0127\u0003L&\u0000\u0127=\u0001\u0000\u0000\u0000\u0128"+
		"\u0129\u0005\u0010\u0000\u0000\u0129\u012a\u0005&\u0000\u0000\u012a\u012f"+
		"\u0003P(\u0000\u012b\u012c\u0005 \u0000\u0000\u012c\u012e\u0003P(\u0000"+
		"\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u0131\u0001\u0000\u0000\u0000"+
		"\u012f\u012d\u0001\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000"+
		"\u0130\u0132\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000"+
		"\u0132\u0133\u0005\'\u0000\u0000\u0133?\u0001\u0000\u0000\u0000\u0134"+
		"\u0135\u0005\u000f\u0000\u0000\u0135\u0136\u0005&\u0000\u0000\u0136\u0137"+
		"\u0003B!\u0000\u0137\u0138\u0005\'\u0000\u0000\u0138A\u0001\u0000\u0000"+
		"\u0000\u0139\u013e\u0003D\"\u0000\u013a\u013b\u0005 \u0000\u0000\u013b"+
		"\u013d\u0003D\"\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013d\u0140"+
		"\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u0001\u0000\u0000\u0000\u013fC\u0001\u0000\u0000\u0000\u0140\u013e\u0001"+
		"\u0000\u0000\u0000\u0141\u0144\u0003P(\u0000\u0142\u0144\u0005=\u0000"+
		"\u0000\u0143\u0141\u0001\u0000\u0000\u0000\u0143\u0142\u0001\u0000\u0000"+
		"\u0000\u0144E\u0001\u0000\u0000\u0000\u0145\u0146\u00058\u0000\u0000\u0146"+
		"\u0148\u0005&\u0000\u0000\u0147\u0149\u0003H$\u0000\u0148\u0147\u0001"+
		"\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u014a\u0001"+
		"\u0000\u0000\u0000\u014a\u014b\u0005\'\u0000\u0000\u014bG\u0001\u0000"+
		"\u0000\u0000\u014c\u0151\u0003J%\u0000\u014d\u014e\u0005 \u0000\u0000"+
		"\u014e\u0150\u0003J%\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u0150\u0153"+
		"\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0151\u0152"+
		"\u0001\u0000\u0000\u0000\u0152I\u0001\u0000\u0000\u0000\u0153\u0151\u0001"+
		"\u0000\u0000\u0000\u0154\u0157\u0003P(\u0000\u0155\u0157\u0003L&\u0000"+
		"\u0156\u0154\u0001\u0000\u0000\u0000\u0156\u0155\u0001\u0000\u0000\u0000"+
		"\u0157K\u0001\u0000\u0000\u0000\u0158\u0159\u0006&\uffff\uffff\u0000\u0159"+
		"\u015a\u00057\u0000\u0000\u015a\u0161\u0003N\'\u0000\u015b\u015c\u0005"+
		".\u0000\u0000\u015c\u0161\u0003N\'\u0000\u015d\u015e\u0005/\u0000\u0000"+
		"\u015e\u0161\u0003N\'\u0000\u015f\u0161\u0003N\'\u0000\u0160\u0158\u0001"+
		"\u0000\u0000\u0000\u0160\u015b\u0001\u0000\u0000\u0000\u0160\u015d\u0001"+
		"\u0000\u0000\u0000\u0160\u015f\u0001\u0000\u0000\u0000\u0161\u0170\u0001"+
		"\u0000\u0000\u0000\u0162\u0163\n\b\u0000\u0000\u0163\u0164\u00051\u0000"+
		"\u0000\u0164\u016f\u0003L&\b\u0165\u0166\n\u0007\u0000\u0000\u0166\u0167"+
		"\u0007\u0002\u0000\u0000\u0167\u016f\u0003L&\b\u0168\u0169\n\u0006\u0000"+
		"\u0000\u0169\u016a\u0007\u0003\u0000\u0000\u016a\u016f\u0003L&\u0007\u016b"+
		"\u016c\n\u0005\u0000\u0000\u016c\u016d\u0007\u0004\u0000\u0000\u016d\u016f"+
		"\u0003L&\u0006\u016e\u0162\u0001\u0000\u0000\u0000\u016e\u0165\u0001\u0000"+
		"\u0000\u0000\u016e\u0168\u0001\u0000\u0000\u0000\u016e\u016b\u0001\u0000"+
		"\u0000\u0000\u016f\u0172\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000"+
		"\u0000\u0000\u0170\u0171\u0001\u0000\u0000\u0000\u0171M\u0001\u0000\u0000"+
		"\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0173\u0174\u0005&\u0000\u0000"+
		"\u0174\u0175\u0003L&\u0000\u0175\u0176\u0005\'\u0000\u0000\u0176\u017a"+
		"\u0001\u0000\u0000\u0000\u0177\u017a\u0003P(\u0000\u0178\u017a\u0003X"+
		",\u0000\u0179\u0173\u0001\u0000\u0000\u0000\u0179\u0177\u0001\u0000\u0000"+
		"\u0000\u0179\u0178\u0001\u0000\u0000\u0000\u017aO\u0001\u0000\u0000\u0000"+
		"\u017b\u017d\u00058\u0000\u0000\u017c\u017e\u0003R)\u0000\u017d\u017c"+
		"\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017eQ\u0001"+
		"\u0000\u0000\u0000\u017f\u0182\u0003T*\u0000\u0180\u0182\u0003V+\u0000"+
		"\u0181\u017f\u0001\u0000\u0000\u0000\u0181\u0180\u0001\u0000\u0000\u0000"+
		"\u0182S\u0001\u0000\u0000\u0000\u0183\u0184\u0005$\u0000\u0000\u0184\u0185"+
		"\u0003L&\u0000\u0185\u0186\u0005%\u0000\u0000\u0186U\u0001\u0000\u0000"+
		"\u0000\u0187\u0188\u0005\u001d\u0000\u0000\u0188\u0189\u00058\u0000\u0000"+
		"\u0189W\u0001\u0000\u0000\u0000\u018a\u018b\u0007\u0005\u0000\u0000\u018b"+
		"Y\u0001\u0000\u0000\u0000\"bkms~\u0087\u009a\u00a2\u00ac\u00b6\u00bf\u00c9"+
		"\u00d4\u00d8\u00e3\u00e7\u00e9\u00f4\u00f6\u0100\u0105\u0114\u012f\u013e"+
		"\u0143\u0148\u0151\u0156\u0160\u016e\u0170\u0179\u017d\u0181";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}