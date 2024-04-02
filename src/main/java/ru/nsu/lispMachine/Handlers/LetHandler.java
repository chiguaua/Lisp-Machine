package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.type;
import static ru.nsu.lispMachine.Vision.visit;

public class LetHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder
                .append(visitLetParam(ctx.getChild(2)))
                .append(visit(ctx.getChild(3), needReturn));
        return javaLineBuilder.toString();
    }

    public String visitLetParam(ParseTree parseTree) {
        StringBuilder javaLineBuilder = new StringBuilder();
        for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
            ParseTree exprCtx = parseTree.getChild(i);
            javaLineBuilder
                    .append(type + " ")
                    .append(exprCtx.getChild(1))
                    .append(" = ")
                    .append(visit(exprCtx.getChild(2), false))
                    .append(";\n");
        }
        return javaLineBuilder.toString();
    }
}
