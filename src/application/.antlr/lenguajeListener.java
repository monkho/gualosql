// Generated from c:/Users/Obgoh/OneDrive - Instituto Tecnológico de Morelia/Documentos/08_Semestre/03 - Lenguajes y automatas II/practicas/proyecto_2/src/application/lenguaje.g4 by ANTLR 4.13.1

package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link lenguajeParser}.
 */
public interface lenguajeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(lenguajeParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(lenguajeParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(lenguajeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(lenguajeParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#crudStmt}.
	 * @param ctx the parse tree
	 */
	void enterCrudStmt(lenguajeParser.CrudStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#crudStmt}.
	 * @param ctx the parse tree
	 */
	void exitCrudStmt(lenguajeParser.CrudStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#extraStmt}.
	 * @param ctx the parse tree
	 */
	void enterExtraStmt(lenguajeParser.ExtraStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#extraStmt}.
	 * @param ctx the parse tree
	 */
	void exitExtraStmt(lenguajeParser.ExtraStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#createbaseDatostmt}.
	 * @param ctx the parse tree
	 */
	void enterCreatebaseDatostmt(lenguajeParser.CreatebaseDatostmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#createbaseDatostmt}.
	 * @param ctx the parse tree
	 */
	void exitCreatebaseDatostmt(lenguajeParser.CreatebaseDatostmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#createTableStmt}.
	 * @param ctx the parse tree
	 */
	void enterCreateTableStmt(lenguajeParser.CreateTableStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#createTableStmt}.
	 * @param ctx the parse tree
	 */
	void exitCreateTableStmt(lenguajeParser.CreateTableStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void enterFieldList(lenguajeParser.FieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void exitFieldList(lenguajeParser.FieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(lenguajeParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(lenguajeParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#foreignRef}.
	 * @param ctx the parse tree
	 */
	void enterForeignRef(lenguajeParser.ForeignRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#foreignRef}.
	 * @param ctx the parse tree
	 */
	void exitForeignRef(lenguajeParser.ForeignRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#insertStmt}.
	 * @param ctx the parse tree
	 */
	void enterInsertStmt(lenguajeParser.InsertStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#insertStmt}.
	 * @param ctx the parse tree
	 */
	void exitInsertStmt(lenguajeParser.InsertStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#insertFieldList}.
	 * @param ctx the parse tree
	 */
	void enterInsertFieldList(lenguajeParser.InsertFieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#insertFieldList}.
	 * @param ctx the parse tree
	 */
	void exitInsertFieldList(lenguajeParser.InsertFieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#insertField}.
	 * @param ctx the parse tree
	 */
	void enterInsertField(lenguajeParser.InsertFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#insertField}.
	 * @param ctx the parse tree
	 */
	void exitInsertField(lenguajeParser.InsertFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void enterSelectStmt(lenguajeParser.SelectStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void exitSelectStmt(lenguajeParser.SelectStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#selectFields}.
	 * @param ctx the parse tree
	 */
	void enterSelectFields(lenguajeParser.SelectFieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#selectFields}.
	 * @param ctx the parse tree
	 */
	void exitSelectFields(lenguajeParser.SelectFieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#fieldListSpec}.
	 * @param ctx the parse tree
	 */
	void enterFieldListSpec(lenguajeParser.FieldListSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#fieldListSpec}.
	 * @param ctx the parse tree
	 */
	void exitFieldListSpec(lenguajeParser.FieldListSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#selectFrom}.
	 * @param ctx the parse tree
	 */
	void enterSelectFrom(lenguajeParser.SelectFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#selectFrom}.
	 * @param ctx the parse tree
	 */
	void exitSelectFrom(lenguajeParser.SelectFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(lenguajeParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(lenguajeParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#condiciones}.
	 * @param ctx the parse tree
	 */
	void enterCondiciones(lenguajeParser.CondicionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#condiciones}.
	 * @param ctx the parse tree
	 */
	void exitCondiciones(lenguajeParser.CondicionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#deleteStmt}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStmt(lenguajeParser.DeleteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#deleteStmt}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStmt(lenguajeParser.DeleteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#deleteTableStmt}.
	 * @param ctx the parse tree
	 */
	void enterDeleteTableStmt(lenguajeParser.DeleteTableStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#deleteTableStmt}.
	 * @param ctx the parse tree
	 */
	void exitDeleteTableStmt(lenguajeParser.DeleteTableStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#deleteDBStmt}.
	 * @param ctx the parse tree
	 */
	void enterDeleteDBStmt(lenguajeParser.DeleteDBStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#deleteDBStmt}.
	 * @param ctx the parse tree
	 */
	void exitDeleteDBStmt(lenguajeParser.DeleteDBStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#updateStmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStmt(lenguajeParser.UpdateStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#updateStmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStmt(lenguajeParser.UpdateStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#updateFieldList}.
	 * @param ctx the parse tree
	 */
	void enterUpdateFieldList(lenguajeParser.UpdateFieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#updateFieldList}.
	 * @param ctx the parse tree
	 */
	void exitUpdateFieldList(lenguajeParser.UpdateFieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#updateField}.
	 * @param ctx the parse tree
	 */
	void enterUpdateField(lenguajeParser.UpdateFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#updateField}.
	 * @param ctx the parse tree
	 */
	void exitUpdateField(lenguajeParser.UpdateFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#finishDB}.
	 * @param ctx the parse tree
	 */
	void enterFinishDB(lenguajeParser.FinishDBContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#finishDB}.
	 * @param ctx the parse tree
	 */
	void exitFinishDB(lenguajeParser.FinishDBContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(lenguajeParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(lenguajeParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link lenguajeParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(lenguajeParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link lenguajeParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(lenguajeParser.DataTypeContext ctx);
}