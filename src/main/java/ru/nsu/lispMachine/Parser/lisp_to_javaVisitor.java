package ru.nsu.lispMachine.Parser;// Generated from C:/Global/prog/Lisp/Lisp-Machine/src/main/java/lisp_to_java.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link lisp_to_javaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface lisp_to_javaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link lisp_to_javaParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(lisp_to_javaParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link lisp_to_javaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(lisp_to_javaParser.ExpressionContext ctx);
}