// Generated from c:/Users/MK/Desktop/مجلد جديد/pp/pp/ArabicLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArabicLangParser}.
 */
public interface ArabicLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ArabicLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ArabicLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ArabicLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ArabicLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#definitions_part}.
	 * @param ctx the parse tree
	 */
	void enterDefinitions_part(ArabicLangParser.Definitions_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#definitions_part}.
	 * @param ctx the parse tree
	 */
	void exitDefinitions_part(ArabicLangParser.Definitions_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#consts_def_part}.
	 * @param ctx the parse tree
	 */
	void enterConsts_def_part(ArabicLangParser.Consts_def_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#consts_def_part}.
	 * @param ctx the parse tree
	 */
	void exitConsts_def_part(ArabicLangParser.Consts_def_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#const_def}.
	 * @param ctx the parse tree
	 */
	void enterConst_def(ArabicLangParser.Const_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#const_def}.
	 * @param ctx the parse tree
	 */
	void exitConst_def(ArabicLangParser.Const_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#types_def_part}.
	 * @param ctx the parse tree
	 */
	void enterTypes_def_part(ArabicLangParser.Types_def_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#types_def_part}.
	 * @param ctx the parse tree
	 */
	void exitTypes_def_part(ArabicLangParser.Types_def_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#type_def}.
	 * @param ctx the parse tree
	 */
	void enterType_def(ArabicLangParser.Type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#type_def}.
	 * @param ctx the parse tree
	 */
	void exitType_def(ArabicLangParser.Type_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#complex_type}.
	 * @param ctx the parse tree
	 */
	void enterComplex_type(ArabicLangParser.Complex_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#complex_type}.
	 * @param ctx the parse tree
	 */
	void exitComplex_type(ArabicLangParser.Complex_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#list_type}.
	 * @param ctx the parse tree
	 */
	void enterList_type(ArabicLangParser.List_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#list_type}.
	 * @param ctx the parse tree
	 */
	void exitList_type(ArabicLangParser.List_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#record_type}.
	 * @param ctx the parse tree
	 */
	void enterRecord_type(ArabicLangParser.Record_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#record_type}.
	 * @param ctx the parse tree
	 */
	void exitRecord_type(ArabicLangParser.Record_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#fields_list}.
	 * @param ctx the parse tree
	 */
	void enterFields_list(ArabicLangParser.Fields_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#fields_list}.
	 * @param ctx the parse tree
	 */
	void exitFields_list(ArabicLangParser.Fields_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#field_def}.
	 * @param ctx the parse tree
	 */
	void enterField_def(ArabicLangParser.Field_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#field_def}.
	 * @param ctx the parse tree
	 */
	void exitField_def(ArabicLangParser.Field_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#vars_def_part}.
	 * @param ctx the parse tree
	 */
	void enterVars_def_part(ArabicLangParser.Vars_def_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#vars_def_part}.
	 * @param ctx the parse tree
	 */
	void exitVars_def_part(ArabicLangParser.Vars_def_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#var_def}.
	 * @param ctx the parse tree
	 */
	void enterVar_def(ArabicLangParser.Var_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#var_def}.
	 * @param ctx the parse tree
	 */
	void exitVar_def(ArabicLangParser.Var_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#vars_group}.
	 * @param ctx the parse tree
	 */
	void enterVars_group(ArabicLangParser.Vars_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#vars_group}.
	 * @param ctx the parse tree
	 */
	void exitVars_group(ArabicLangParser.Vars_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#procs_def_part}.
	 * @param ctx the parse tree
	 */
	void enterProcs_def_part(ArabicLangParser.Procs_def_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#procs_def_part}.
	 * @param ctx the parse tree
	 */
	void exitProcs_def_part(ArabicLangParser.Procs_def_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#proc_def}.
	 * @param ctx the parse tree
	 */
	void enterProc_def(ArabicLangParser.Proc_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#proc_def}.
	 * @param ctx the parse tree
	 */
	void exitProc_def(ArabicLangParser.Proc_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#proc_header}.
	 * @param ctx the parse tree
	 */
	void enterProc_header(ArabicLangParser.Proc_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#proc_header}.
	 * @param ctx the parse tree
	 */
	void exitProc_header(ArabicLangParser.Proc_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#proc_block}.
	 * @param ctx the parse tree
	 */
	void enterProc_block(ArabicLangParser.Proc_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#proc_block}.
	 * @param ctx the parse tree
	 */
	void exitProc_block(ArabicLangParser.Proc_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#formal_params_list}.
	 * @param ctx the parse tree
	 */
	void enterFormal_params_list(ArabicLangParser.Formal_params_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#formal_params_list}.
	 * @param ctx the parse tree
	 */
	void exitFormal_params_list(ArabicLangParser.Formal_params_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#param_def}.
	 * @param ctx the parse tree
	 */
	void enterParam_def(ArabicLangParser.Param_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#param_def}.
	 * @param ctx the parse tree
	 */
	void exitParam_def(ArabicLangParser.Param_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(ArabicLangParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(ArabicLangParser.Data_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void enterStmtList(ArabicLangParser.StmtListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void exitStmtList(ArabicLangParser.StmtListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ArabicLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ArabicLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#conditional_stmt}.
	 * @param ctx the parse tree
	 */
	void enterConditional_stmt(ArabicLangParser.Conditional_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#conditional_stmt}.
	 * @param ctx the parse tree
	 */
	void exitConditional_stmt(ArabicLangParser.Conditional_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#repetition_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRepetition_stmt(ArabicLangParser.Repetition_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#repetition_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRepetition_stmt(ArabicLangParser.Repetition_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#repeat_for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_for_stmt(ArabicLangParser.Repeat_for_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#repeat_for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_for_stmt(ArabicLangParser.Repeat_for_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#repetition_range}.
	 * @param ctx the parse tree
	 */
	void enterRepetition_range(ArabicLangParser.Repetition_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#repetition_range}.
	 * @param ctx the parse tree
	 */
	void exitRepetition_range(ArabicLangParser.Repetition_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#repeat_while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#repeat_while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_while_stmt(ArabicLangParser.Repeat_while_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#repeat_until_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_until_stmt(ArabicLangParser.Repeat_until_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#repeat_until_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_until_stmt(ArabicLangParser.Repeat_until_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(ArabicLangParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(ArabicLangParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#input_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInput_stmt(ArabicLangParser.Input_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#input_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInput_stmt(ArabicLangParser.Input_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#output_stmt}.
	 * @param ctx the parse tree
	 */
	void enterOutput_stmt(ArabicLangParser.Output_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#output_stmt}.
	 * @param ctx the parse tree
	 */
	void exitOutput_stmt(ArabicLangParser.Output_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#print_list}.
	 * @param ctx the parse tree
	 */
	void enterPrint_list(ArabicLangParser.Print_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#print_list}.
	 * @param ctx the parse tree
	 */
	void exitPrint_list(ArabicLangParser.Print_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#print_element}.
	 * @param ctx the parse tree
	 */
	void enterPrint_element(ArabicLangParser.Print_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#print_element}.
	 * @param ctx the parse tree
	 */
	void exitPrint_element(ArabicLangParser.Print_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#call_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCall_stmt(ArabicLangParser.Call_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#call_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCall_stmt(ArabicLangParser.Call_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#actual_params_list}.
	 * @param ctx the parse tree
	 */
	void enterActual_params_list(ArabicLangParser.Actual_params_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#actual_params_list}.
	 * @param ctx the parse tree
	 */
	void exitActual_params_list(ArabicLangParser.Actual_params_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#actual_param}.
	 * @param ctx the parse tree
	 */
	void enterActual_param(ArabicLangParser.Actual_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#actual_param}.
	 * @param ctx the parse tree
	 */
	void exitActual_param(ArabicLangParser.Actual_paramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PowerExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPowerExpr(ArabicLangParser.PowerExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PowerExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPowerExpr(ArabicLangParser.PowerExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubOrExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubOrExpr(ArabicLangParser.AddSubOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubOrExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubOrExpr(ArabicLangParser.AddSubOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPlus}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPlus(ArabicLangParser.UnaryPlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPlus}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPlus(ArabicLangParser.UnaryPlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivModAndExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivModAndExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivModAndExpr(ArabicLangParser.MultDivModAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinus}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinus(ArabicLangParser.UnaryMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinus}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinus(ArabicLangParser.UnaryMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(ArabicLangParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(ArabicLangParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(ArabicLangParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(ArabicLangParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomicFactor}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomicFactor(ArabicLangParser.AtomicFactorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomicFactor}
	 * labeled alternative in {@link ArabicLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomicFactor(ArabicLangParser.AtomicFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ArabicLangParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ArabicLangParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#var_access}.
	 * @param ctx the parse tree
	 */
	void enterVar_access(ArabicLangParser.Var_accessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#var_access}.
	 * @param ctx the parse tree
	 */
	void exitVar_access(ArabicLangParser.Var_accessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ArabicLangParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ArabicLangParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#indexed_selector}.
	 * @param ctx the parse tree
	 */
	void enterIndexed_selector(ArabicLangParser.Indexed_selectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#indexed_selector}.
	 * @param ctx the parse tree
	 */
	void exitIndexed_selector(ArabicLangParser.Indexed_selectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#field_selector}.
	 * @param ctx the parse tree
	 */
	void enterField_selector(ArabicLangParser.Field_selectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#field_selector}.
	 * @param ctx the parse tree
	 */
	void exitField_selector(ArabicLangParser.Field_selectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArabicLangParser#const_value}.
	 * @param ctx the parse tree
	 */
	void enterConst_value(ArabicLangParser.Const_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArabicLangParser#const_value}.
	 * @param ctx the parse tree
	 */
	void exitConst_value(ArabicLangParser.Const_valueContext ctx);
}