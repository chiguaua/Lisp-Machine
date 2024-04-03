package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class LoopHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();

        String variable = ctx.getChild(3).getText();
        String start = ctx.getChild(5).getText();
        String end = ctx.getChild(7).getText();

        javaLineBuilder.append("for (int ")
                .append(variable)
                .append(" = ")
                .append(start)
                .append("; ")
                .append(variable)
                .append(" <= ")
                .append(end)
                .append("; ")
                .append(variable)
                .append("++) {\n");

        for (int i = 9; i < ctx.getChildCount() - 1; i++) {
            javaLineBuilder.append(visit(ctx.getChild(i), false)).append(";\n");
        }
        javaLineBuilder.append("}\n");

        return javaLineBuilder.toString();
    }
}
