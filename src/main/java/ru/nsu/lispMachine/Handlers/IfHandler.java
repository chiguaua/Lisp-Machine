package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import java.util.Objects;

import static ru.nsu.lispMachine.Vision.parser;
import static ru.nsu.lispMachine.Vision.visit;

public class IfHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder
                .append(visit(ctx.getChild(1), false))
                .append(" (")
                .append(visit(ctx.getChild(2), false))
                .append(") { \n")
                .append(visit(ctx.getChild(3), needReturn));
        if (!Objects.equals(ctx.getChild(4).toStringTree(parser), ")")) {
            javaLineBuilder
                    .append("\n} else { \n")
                    .append(visit(ctx.getChild(4), needReturn));
        }
        javaLineBuilder.append("\n}");
        return javaLineBuilder.toString();
    }
}
