package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class CarHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        return visit(ctx.getChild(2), needReturn);
    }
}
