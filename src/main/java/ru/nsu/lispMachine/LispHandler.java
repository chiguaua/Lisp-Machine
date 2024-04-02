package ru.nsu.lispMachine;

import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

public interface LispHandler {
    String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn);
}