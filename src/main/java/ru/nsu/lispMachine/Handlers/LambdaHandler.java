package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.*;

public class LambdaHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        String lambdaName = "lambdaFunction" + mainBody.size();
        StringBuilder javaAdditionalLineBuilder = new StringBuilder();
        javaAdditionalLineBuilder
                .append("Function<" + type + ", " + type + "> ")
                .append(lambdaName + " = ");

        // Generate parameter list
        ParseTree parameters = ctx.getChild(2);
        if (parameters instanceof lisp_to_javaParser.ExpressionContext) {
            String parameterList = visitLambdaArg((lisp_to_javaParser.ExpressionContext) parameters);
            javaAdditionalLineBuilder.append(parameterList);
        } else {
            throw new IllegalArgumentException("Lambda expression is missing parameters.");
        }

        // Generate lambda body
        ParseTree body = ctx.getChild(3);
        if (body instanceof lisp_to_javaParser.ExpressionContext) {
            javaAdditionalLineBuilder
                    .append(" -> ")
                    .append("{")
                    .append(visitExpression((lisp_to_javaParser.ExpressionContext) body, true))
                    .append("}");
        } else {
            throw new IllegalArgumentException("Lambda expression is missing body.");
        }
        javaLineBuilder.append(lambdaName);
        // If a return statement is needed, add it
        if (needReturn) {
            javaLineBuilder.insert(0, "return ");
        }
        mainBody.add(javaAdditionalLineBuilder.toString());
        return javaLineBuilder.toString();
    }

    public String visitLambdaArg(lisp_to_javaParser.ExpressionContext ctx) {
        StringBuilder argBuilder = new StringBuilder("(");
        for (ParseTree child : ctx.children) {
            if (child instanceof TerminalNode) {
                // Skip parentheses
                if (!child.getText().equals("(") && !child.getText().equals(")")) {
                    argBuilder.append(" ").append(child.getText()).append(", ");
                }
            }
        }
        // Remove the trailing comma and space if parameters exist
        if (argBuilder.length() > 2) {
            argBuilder.setLength(argBuilder.length() - 2);
        }
        argBuilder.append(")");
        return argBuilder.toString();
    }

}
