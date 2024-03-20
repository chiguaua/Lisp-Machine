import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Изначально был план переписать стандартного Vision под нашу задачу, но теперь решил полностью своего.
// Думаю он будет сильно отличаться
public class Vision {

    Parser parser;

    Vision(Parser parser) {
        this.parser = parser;
    }

    public String visitExpression(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        //System.out.println("Идентификатор: " + ctx.IDENTIFIER(0).getText());
        StringBuilder javaLineBuilder = new StringBuilder();
        if (ctx.getChild(1) instanceof lisp_to_javaParser.ExpressionContext) {
            if (needReturn) {
                javaLineBuilder
                        .append("return ")
                        .append(visit(ctx.getChild(1), false))
                        .append(visitFunBody(ctx))
                        .append(";\n ");
            } else {
                javaLineBuilder
                        .append(visit(ctx.getChild(1), false))
                        .append(visitFunBody(ctx));
            }
            return javaLineBuilder.toString();
        }

        switch (ctx.IDENTIFIER(0).getText()) {
            case "defun" -> {
                javaLineBuilder
                        .append("public static int ")
                        .append(ctx.getChild(2).getText())
                        .append(visitArg((lisp_to_javaParser.ExpressionContext) ctx.getChild(3)))
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
            }
            case "+", "-", "*", "/", ">", "<", "==" -> {
                if (needReturn) {
                    javaLineBuilder
                            .append("return ")
                            .append(visit(ctx.getChild(2), false))
                            .append(visit(ctx.getChild(1), false))
                            .append(visit(ctx.getChild(3), false))
                            .append(";\n ");
                } else {
                    javaLineBuilder
                            .append(visit(ctx.getChild(2), false))
                            .append(visit(ctx.getChild(1), false))
                            .append(visit(ctx.getChild(3), false));
                }
            }
            case "print" -> {
                javaLineBuilder
                        .append("System.out.println")
                        .append(visitStringConcat(ctx))
                        .append(";\n ");
            }
            case "let" -> {
                javaLineBuilder
                        .append(visitLetParam(ctx.getChild(2)))
                        .append(visit(ctx.getChild(3), needReturn));
            }
            case "if" -> {
                javaLineBuilder
                        .append(visit(ctx.getChild(1), false))
                        .append(" (")
                        .append(visit(ctx.getChild(2), false))
                        .append(") { \n")
                        .append(visit(ctx.getChild(3), needReturn));
                if (!Objects.equals(ctx.getChild(4).toStringTree(parser), ")")) {
                    javaLineBuilder
                            .append("\n} else { \n")
                            .append(visit(ctx.getChild(4), needReturn));
                }
                javaLineBuilder.append("\n}");
            }

            // lambda - creates an anonymous function.

            case "lambda" -> {
                handleLambda(ctx, javaLineBuilder, needReturn);
                break;
            }

            default -> {
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

        }
        return javaLineBuilder.toString();
    }

    // handeling lambda

    private void handleLambda(lisp_to_javaParser.ExpressionContext ctx, StringBuilder javaLineBuilder, boolean needReturn) {

        System.out.println("Lambda parameters: " + ctx.getChild(1).getText()); // Add this line
        javaLineBuilder.append("new ");

        // Generate parameter list
        ParseTree parameters = ctx.getChild(2);
        if (parameters instanceof lisp_to_javaParser.ExpressionContext) {
            String parameterList = visitArg((lisp_to_javaParser.ExpressionContext) parameters);
            javaLineBuilder.append(parameterList);
        } else {
            throw new IllegalArgumentException("Lambda expression is missing parameters.");
        }

        // Generate lambda body
        ParseTree body = ctx.getChild(3);
        if (body instanceof lisp_to_javaParser.ExpressionContext) {
            javaLineBuilder.append(" -> ");
            javaLineBuilder.append("{");
            javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) body, true));
            javaLineBuilder.append("}");
        } else {
            throw new IllegalArgumentException("Lambda expression is missing body.");
        }

        // If a return statement is needed, add it
        if (needReturn) {
            javaLineBuilder.insert(0, "return ");
        }
    }

    private void handleDefault(lisp_to_javaParser.ExpressionContext ctx, StringBuilder javaLineBuilder, boolean needReturn) {
        if (needReturn) {
            javaLineBuilder.append("return ")
                    .append(ctx.getChild(1).getText())
                    .append(visitFunBody(ctx))
                    .append(";\n ");
        } else {
            javaLineBuilder.append(ctx.getChild(1).getText())
                    .append(visitFunBody(ctx));
        }
    }





    public String visit(ParseTree parseTree, boolean needReturn) {
        if (parseTree instanceof lisp_to_javaParser.ProgramContext) {
            lisp_to_javaParser.ProgramContext ctx = (lisp_to_javaParser.ProgramContext) parseTree;
            FileOutputStream outputStream = null;
            File myFile = new File("testOut.txt");
            List<String> mainBody = new ArrayList<>();
            try {
                outputStream = new FileOutputStream(myFile);

                byte[] buffer = "public class TestOut {".getBytes();
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
                buffer = " public static void main(String[] args) {".getBytes();
                outputStream.write(buffer);

                for (String x : mainBody) {
                    outputStream.write(x.getBytes());
                    outputStream.write(";\n".getBytes());
                }
                buffer = "}\n}".getBytes();
                outputStream.write(buffer);
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (parseTree instanceof lisp_to_javaParser.ExpressionContext) {
            return visitExpression((lisp_to_javaParser.ExpressionContext) parseTree, needReturn);
        } else {
            //Полагаю тут может быть константа/переменная. ~ const/var
            if (needReturn) {
                return "return " + parseTree.toStringTree(parser) + ";";
            } else {
                return parseTree.toStringTree(parser);
            }
        }
        return null;
    }


    public String visitFunBody(lisp_to_javaParser.ExpressionContext ctx) {
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
    public String visitStringConcat (lisp_to_javaParser.ExpressionContext ctx) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder.append("(");
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode node) {
                javaLineBuilder.append(node.getText()).append(" + ");
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


    //Аргументы функции ~ function arguments
    public String visitArg(lisp_to_javaParser.ExpressionContext ctx) {
        StringBuilder argBuilder = new StringBuilder("(");
        for (ParseTree child : ctx.children) {
            if (child instanceof TerminalNode) {
                // Skip parentheses
                if (!child.getText().equals("(") && !child.getText().equals(")")) {
                    argBuilder.append("int ").append(child.getText()).append(", ");
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


    public String visitLetParam(ParseTree parseTree) {
        StringBuilder javaLineBuilder = new StringBuilder();
        for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
            ParseTree exprCtx = parseTree.getChild(i);
            javaLineBuilder
                    .append("int ")
                    .append(exprCtx.getChild(1))
                    .append(" = ")
                    .append(visit(exprCtx.getChild(2), false))
                    .append(";\n");
        }
        return javaLineBuilder.toString();
    }
}
