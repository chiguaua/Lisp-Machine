package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class OrHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder
                .append(visit(ctx.getChild(2), false))
                .append(" || ")
                .append(visit(ctx.getChild(3), false));
        return javaLineBuilder.toString();
    }
}
