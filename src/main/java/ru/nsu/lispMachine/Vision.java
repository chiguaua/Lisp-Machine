package ru.nsu.lispMachine;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.logging.Handler;

public class Vision {

    public static Parser  parser;

    public static List<String> mainBody = new ArrayList<>();

    static String outputFile = "";

    public static String type = "Object";

    static HashMap<String, LispHandler> handlers = new HashMap<>();

    static void addHandler(String trigger, LispHandler handler){
        handlers.put(trigger,handler);
    }

    public static void setParser(Parser newParser){
        parser = newParser;
    }
    public static void setOutputFile(String newOutputJavaFile){
        outputFile = newOutputJavaFile;
    }

    public static String visit(ParseTree parseTree, boolean needReturn) {
        if (parseTree instanceof lisp_to_javaParser.ProgramContext) {
            lisp_to_javaParser.ProgramContext ctx = (lisp_to_javaParser.ProgramContext) parseTree;
            try {
                OutputStream outputStream;
                String filename = "TestOut";
                if (outputFile == "") {
                    outputStream = System.out;
                } else {
                    File myFile = new File(outputFile);
                    filename = myFile.getName().split("\\.")[0];
                    outputStream = new FileOutputStream(myFile);
                }

                byte[] buffer = ("public class " + filename + " {").getBytes();
                outputStream.write(("import java.util.Scanner;\n").getBytes());
                outputStream.write(buffer);
                for (ParseTree exprCtx : ctx.children) {
                    System.out.println(exprCtx.toStringTree(parser) + "============================================================");
                    String currOut = visit(exprCtx, needReturn);
                    System.out.println(currOut);
                    if (currOut.startsWith("public")) {
                        outputStream.write(currOut.getBytes());
                        outputStream.write("\n".getBytes());
                    } else if (!currOut.startsWith("<EOF>")) {
                        mainBody.add(currOut);
                    }

                }
                buffer = " public static void main(String[] args) {\nScanner scanner = new Scanner(System.in);\n".getBytes();
                outputStream.write(buffer);

                for (String x : mainBody) {
                    outputStream.write(x.getBytes());
                    outputStream.write(";\n".getBytes());
                }
                buffer = "scanner.close();\n}\n}".getBytes();
                outputStream.write(buffer);
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (parseTree instanceof lisp_to_javaParser.ExpressionContext) {
            return visitExpression((lisp_to_javaParser.ExpressionContext) parseTree, needReturn);
        } else {
            // Handle other cases
            String operator = "";
            switch (parseTree.toStringTree(parser)) {
                case "T" -> operator = "true";
                case "NIL" -> operator = "false";
                default -> operator = parseTree.toStringTree(parser);
            }
            if (needReturn) {
                return "return " + operator + ";";
            } else {
                return operator;
            }
        }
        return null;
    }

    public static String visitExpression(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();

        if (ctx.getChild(1) instanceof lisp_to_javaParser.ExpressionContext) {
            String applyLam = "";
            if (ctx.getChild(1).getChild(1).toStringTree(parser).contains("lambda")) {
                applyLam = ".apply";
            }
            if (needReturn) {
                javaLineBuilder
                        .append("return ")
                        .append(visit(ctx.getChild(1), false) + applyLam)
                        .append(visitFunBody(ctx))
                        .append(";\n ");
            } else {
                javaLineBuilder
                        .append(visit(ctx.getChild(1), false) + applyLam)
                        .append(visitFunBody(ctx));
            }
            return javaLineBuilder.toString();
        }


        LispHandler handler = handlers.get(ctx.IDENTIFIER(0).getText());
        if (handler != null) {
            javaLineBuilder.append(handler.apply(ctx, needReturn));
        } else {
            if (needReturn) {
                javaLineBuilder
                        .append("return ")
                        .append(ctx.getChild(1).getText())
                        .append(visitFunBody(ctx))
                        .append(";\n ");
            } else {
                javaLineBuilder
                        .append(ctx.getChild(1).getText())
                        .append(visitFunBody(ctx));
            }
        }


        return javaLineBuilder.toString();
    }

    public static String visitFunBody(lisp_to_javaParser.ExpressionContext ctx) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder.append("(");
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode node) {
                javaLineBuilder.append(node.getText()).append(", ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child, false)).append(", ");
            }
        }
        if (javaLineBuilder.length() > 2) {
            javaLineBuilder.delete(javaLineBuilder.length() - 2, javaLineBuilder.length());
        }
        javaLineBuilder.append(")");
        return javaLineBuilder.toString();
    }

}
