import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Vision {

    Parser parser;

    List<String> mainBody = new ArrayList<>();

    String outputFile = "defaultOutput.txt";

    String type = "Object";

    HashMap<String, String> nameSpace = new HashMap<>();

    Vision(Parser parser) {

        this.parser = parser;
    }

    Vision(Parser parser, String txt) {
        this.outputFile = txt;
        this.parser = parser;
    }


    public String visitExpression(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        //System.out.println("Идентификатор: " + ctx.IDENTIFIER(0).getText());
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

        switch (ctx.IDENTIFIER(0).getText()) {
            case "defun" -> {
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
            }

            case "+", "-", "*", "/", ">", "<", "=", "rem", "mod", ">=", "<=" -> {
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
            }
            case "print" -> {
                javaLineBuilder
                        .append("System.out.println")
                        .append(visitStringConcat(ctx));
            }
            case "read" -> {
                javaLineBuilder.append("scanner.nextInt()");
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
            case "and" -> {
                javaLineBuilder
                        .append(visit(ctx.getChild(2), false))
                        .append(" && ")
                        .append(visit(ctx.getChild(3), false));
            }
            case "or" -> {
                javaLineBuilder
                        .append(visit(ctx.getChild(2), false))
                        .append(" || ")
                        .append(visit(ctx.getChild(3), false));
            }
            case "not" -> {
                javaLineBuilder.append("!")
                        .append(visit(ctx.getChild(2), false));
            }
            // lambda - creates an anonymous function.
            case "lambda" -> {
                handleLambda(ctx, javaLineBuilder, needReturn);
            }
            case "list" -> {
                javaLineBuilder.append(handleList(ctx, needReturn));
            }
            case "@call-java" -> {
                for (int i = 2; i < ctx.getChildCount() - 1; i++) {
                    ParseTree child = ctx.getChild(i);
                    javaLineBuilder.append(child.getText());
                }
                System.out.println("sss");
            }

            // cond expression
            case "cond" -> {
                javaLineBuilder.append(handleCond(ctx, needReturn));
            }

            case "do" -> {
                javaLineBuilder.append(handleDo(ctx, false));
            }

            case "setq" -> {
               javaLineBuilder.append(visitLetSetq(ctx));
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

    private void handleLambda(lisp_to_javaParser.ExpressionContext ctx, StringBuilder javaLineBuilder, boolean needReturn) {

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

    public String visitStringConcat(lisp_to_javaParser.ExpressionContext ctx) {
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
        System.out.println(childName);

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
        if (argBuilder.length() > 2) {
            argBuilder.setLength(argBuilder.length() - 2);
        }
        argBuilder.append(")");
        return argBuilder.toString();
    }

    //Аргументы функции ~ function arguments
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


    public String visitLetParam(ParseTree parseTree) {
        StringBuilder javaLineBuilder = new StringBuilder();
        for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
            ParseTree exprCtx = parseTree.getChild(i);
            javaLineBuilder
                    .append(type + " ")
                    .append(exprCtx.getChild(1))
                    .append(" = ")
                    .append(visit(exprCtx.getChild(2), false))
                    .append(";\n");
        }
        return javaLineBuilder.toString();
    }
    public String visitLetSetq(ParseTree parseTree) {
        StringBuilder javaLineBuilder = new StringBuilder();
        for (int i = 2; i < parseTree.getChildCount() - 1; i+=2) {
            javaLineBuilder
                    .append("public static " + type + " ")
                    .append(parseTree.getChild(i))
                    .append(" = ")
                    .append(visit(parseTree.getChild(i+1), false))
                    .append(";\n");
        }
        return javaLineBuilder.toString();
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

    private String handleList(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();

        javaLineBuilder.append("java.util.Arrays.asList(");

        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode) {
                TerminalNode node = (TerminalNode) child;
                javaLineBuilder.append(node.getText()).append(", ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child, false)).append(", ");
            }
        }

        if (javaLineBuilder.length() > 2) {
            javaLineBuilder.setLength(javaLineBuilder.length() - 2); // Remove trailing comma
        }

        javaLineBuilder.append(")");

        if (needReturn) {
            javaLineBuilder.append("return list;\n");
        }

        return javaLineBuilder.toString();
    }

    private String handleCond(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();

        javaLineBuilder.append("if (");
        for (int i = 2; i < ctx.getChildCount() - 1; i += 1) {
            ParseTree branch = ctx.getChild(i);

            javaLineBuilder.append(visit(branch.getChild(1), false))
                    .append(") {")
                    .append(visit(branch.getChild(2), needReturn) + ";\n");
            if (i < ctx.getChildCount() - 2) {
                javaLineBuilder.append("} else if (");
            } else {
                javaLineBuilder.append("} else {\n");
            }
        }
        // Add a default case if none of the conditions match
        javaLineBuilder.append("throw new IllegalArgumentException(\"No branch of cond matched\");\n");
        javaLineBuilder.append("}");
        return javaLineBuilder.toString();
    }

  
    private String handleDo(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        //ctx.getChild(2)  действие
        //ctx.getChild(3) Условие
        //ctx.getChild(4+) Тело
        ParseTree action =  ctx.getChild(2);
        StringBuilder javaLineBuilderEndWhile = new StringBuilder();
        for (int i = 1; i < action.getChildCount() - 1; i++) {
            ParseTree child = action.getChild(i);
            if (child instanceof TerminalNode) {
                throw new RuntimeException("Do Exception " + child.getText());
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder
                        .append(type + " ")
                        .append(child.getChild(1))
                        .append(" = ")
                        .append(visit(child.getChild(2), false))
                        .append(";\n");
                javaLineBuilderEndWhile.append(child.getChild(1) + " = " + visit(child.getChild(3), false) + ";\n");
            }
        }

        javaLineBuilder
                .append("while (!")
                .append(visit(ctx.getChild(3).getChild(1), false))
                .append(") {\n");
        for (int i = 4; i < ctx.getChildCount() - 1; i++) {
            javaLineBuilder.append(visit(ctx.getChild(i), false) + ";\n");
        }


        javaLineBuilder
                .append(javaLineBuilderEndWhile)
                .append("\n}");



        return javaLineBuilder.toString();
    }
}
