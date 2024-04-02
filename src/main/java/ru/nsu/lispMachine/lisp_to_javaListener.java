package ru.nsu.lispMachine;// Generated from C:/Global/prog/Lisp/Lisp-Machine/src/main/java/lisp_to_java.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link lisp_to_javaParser}.
 */
public interface lisp_to_javaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link lisp_to_javaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(lisp_to_javaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link lisp_to_javaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(lisp_to_javaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link lisp_to_javaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(lisp_to_javaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link lisp_to_javaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(lisp_to_javaParser.ExpressionContext ctx);
}