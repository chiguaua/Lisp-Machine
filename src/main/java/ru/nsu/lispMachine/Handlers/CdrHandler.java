package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class CdrHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        String list = visit(ctx.getChild(2), needReturn);
        // In Lisp, cdr returns the list without its first element, which can be represented in Java as a sublist starting from index 1
        return list + ".subList(1, " + list + ".size())";
    }
}
