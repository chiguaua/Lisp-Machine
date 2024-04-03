package ru.nsu.lispMachine.Handlers;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import java.util.HashMap;
import java.util.Objects;

import static ru.nsu.lispMachine.Vision.*;

public class DefunHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder
                .append(visitArg(ctx))
                .append(" {\n");
        for (int i = 4; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode) {
                TerminalNode node = (TerminalNode) child;
                javaLineBuilder.append("return ").append(node.getText()).append(";\n ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child, true));
            }
        }
        javaLineBuilder.append("}");
        return javaLineBuilder.toString();
    }

    public String visitArg(lisp_to_javaParser.ExpressionContext ctx) {
        lisp_to_javaParser.ExpressionContext args = (lisp_to_javaParser.ExpressionContext) ctx.getChild(3);
        HashMap<String, String> childName = new HashMap<>();
        for (ParseTree child : args.children) {
            if (child instanceof TerminalNode) {
                if (!child.getText().equals("(") && !child.getText().equals(")")) {
                    childName.put(child.getText(), "");
                }
            }
        }
        checkTypeBase(childName, ctx, "");

        StringBuilder argBuilder = new StringBuilder();

        argBuilder
                .append("public static " + type + " ");

        argBuilder.append(ctx.getChild(2).getText() + "(");
        for (ParseTree child : args.children) {
            if (child instanceof TerminalNode) {
                if (!child.getText().equals("(") && !child.getText().equals(")")) {
                    String localType = childName.get(child.getText());
                    if (localType.trim().isEmpty()) {
                        localType = type;
                    }
                    argBuilder.append(type + " ").append(child.getText()).append(", ");
                }
            }
        }
        // Remove the trailing comma and space if parameters exist
        if (args.getChildCount() > 2) {
            argBuilder.setLength(argBuilder.length() - 2);
        }
        argBuilder.append(")");
        return argBuilder.toString();
    }

    public void checkTypeBase(HashMap<String, String> argName, lisp_to_javaParser.ExpressionContext ctx, String expected) {
        for (int i = 4; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof lisp_to_javaParser.ExpressionContext) {
                checkType(argName, (lisp_to_javaParser.ExpressionContext) child, "");
            }
        }
    }

    public void checkType(HashMap<String, String> argName, ParseTree ctx, String expected) {
        if (ctx instanceof TerminalNode) {
            TerminalNode node = (TerminalNode) ctx;
            if (argName.containsKey(node.getText())) {
                argName.put(node.getText(), argName.get(node.getText()) + " " + expected);
            } else {
                argName.put(node.getText(), expected);
            }
        } else if (ctx instanceof lisp_to_javaParser.ExpressionContext) {
            switch (((lisp_to_javaParser.ExpressionContext) ctx).IDENTIFIER(0).getText()) {
                case "+", "-", "*", "/", ">", "<", "==", ">=", "<=" -> {
                    for (int i = 2; i < ctx.getChildCount() - 1; i++) {
                        ParseTree child = ctx.getChild(i);
                        checkType(argName, child, "double");
                    }
                }
                case "print" -> {
                }
                case "let" -> {
                }
                case "if" -> {
                    checkType(argName, ctx.getChild(2), "boolean");
                    checkType(argName, ctx.getChild(3), "");

                    //else branch
                    if (!Objects.equals(ctx.getChild(4).toStringTree(parser), ")")) {
                        checkType(argName, ctx.getChild(4), "");
                    }
                }
                case "lambda" -> {

                }

                default -> {
                    checkType(argName, ctx.getChild(3), "");
                }
            }
        }

    }
}
