package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class ConsHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        String newElement = visit(ctx.getChild(2), needReturn);
        String existingList = visit(ctx.getChild(3), needReturn);
        // In Java, to add an element to the beginning of a list, we can use LinkedList's addFirst method
        return existingList + ".addFirst(" + newElement + ")";
    }
}
