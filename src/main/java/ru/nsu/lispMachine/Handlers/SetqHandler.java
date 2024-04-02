package ru.nsu.lispMachine.Handlers;

import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.type;
import static ru.nsu.lispMachine.Vision.visit;

public class SetqHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        if (ctx instanceof ParseTree) {
            for (int i = 2; i < ctx.getChildCount() - 1; i += 2) {
                javaLineBuilder
                        .append("public static " + type + " ")
                        .append(ctx.getChild(i))
                        .append(" = ")
                        .append(visit(ctx.getChild(i + 1), false))
                        .append(";\n");
            }
        }
        return javaLineBuilder.toString();
    }
}