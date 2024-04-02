package ru.nsu.lispMachine.Handlers;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;


public class MathAndLogicHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        String operator = "";
        switch (ctx.IDENTIFIER(0).getText()) {
            case "+", "-", "*", "/", ">", "<", ">=", "<=" -> {
                operator = ctx.IDENTIFIER(0).getText();
            }
            case "=" -> {
                operator = "==";
            }
            case "rem", "mod" -> {
                operator = "%";
            }
            default -> operator = "Programming Error";
        }

        if (needReturn) {
            javaLineBuilder
                    .append("return (")
                    .append("((Number) " + visit(ctx.getChild(2), false) + ").doubleValue() ")
                    .append(operator)
                    .append(" ((Number) " + visit(ctx.getChild(3), false) + ").doubleValue()")
                    .append(");\n ");
        } else {
            javaLineBuilder
                    .append("(((Number) " + visit(ctx.getChild(2), false) + ").doubleValue()")
                    .append(operator)
                    .append(" ((Number) " + visit(ctx.getChild(3), false) + ").doubleValue()" + ")");
        }
        return javaLineBuilder.toString();
    }
}
