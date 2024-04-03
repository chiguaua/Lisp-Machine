package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visitExpression;

public class ListHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();

        javaLineBuilder.append("java.util.Arrays.asList(");

        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode) {
                TerminalNode node = (TerminalNode) child;
                javaLineBuilder.append(node.getText()).append(", ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child, false)).append(", ");
            }
        }

        if (javaLineBuilder.length() > 2) {
            javaLineBuilder.setLength(javaLineBuilder.length() - 2); // Remove trailing comma
        }

        javaLineBuilder.append(")");

        if (needReturn) {
            javaLineBuilder.append("return list;\n");
        }

        return javaLineBuilder.toString();
    }
}
