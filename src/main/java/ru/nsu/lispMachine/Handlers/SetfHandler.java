package ru.nsu.lispMachine.Handlers;


import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.type;
import static ru.nsu.lispMachine.Vision.visit;

public class SetfHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();

        // Extract variable name and value expression from setf call
        String variable = ctx.getChild(2).getText();
        String value = visit(ctx.getChild(3), false); // Value is always a child expression

        // Generate Java code to assign value to the variable
        javaLineBuilder.append(type + " ").append(variable).append(" = ").append(value);

        // Add semicolon if needed
        if (needReturn) {
            javaLineBuilder.append(";");
        }

        return javaLineBuilder.toString();
    }
}
