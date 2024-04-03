package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class PrognHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode) {
                TerminalNode node = (TerminalNode) child;
                javaLineBuilder.append(node.getText()).append(";\n ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext && i != ctx.getChildCount() - 2) {
                javaLineBuilder.append(visit(child, false) + ";\n");
            } else {
                javaLineBuilder.append(visit(child, needReturn));
            }
        }
        return javaLineBuilder.toString();
    }
}
