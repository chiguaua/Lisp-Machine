package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visitExpression;

public class PrintHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder
                .append("System.out.println")
                .append(visitStringConcat(ctx));
        return javaLineBuilder.toString();
    }
    public String visitStringConcat(lisp_to_javaParser.ExpressionContext ctx) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder.append("(");
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode node) {
                javaLineBuilder.append(node.getText()).append(" + ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child, false)).append(", ");
            }
        }
        if (javaLineBuilder.length() > 2) {
            javaLineBuilder.delete(javaLineBuilder.length() - 2, javaLineBuilder.length());
        }
        javaLineBuilder.append(")");
        return javaLineBuilder.toString();
    }


}
