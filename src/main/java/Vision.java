import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

// Изначально был план переписать стандартного Vision под нашу задачу, но теперь решил полностью своего.
// Думаю он будет сильно отличаться
public class Vision {

        Parser parser;
        Vision(Parser parser){
            this.parser = parser;
        }
        public Void visitProgram(lisp_to_javaParser.ProgramContext ctx) {

            for (ParseTree exprCtx :  ctx.children) {
                System.out.println(exprCtx.toStringTree(parser) + "============================================================");
                System.out.println(visit(exprCtx));
            }
            return null;
        }

        public String visitExpression(lisp_to_javaParser.ExpressionContext ctx) {
            System.out.println("Идентификатор: " + ctx.IDENTIFIER(0).getText());
            StringBuilder javaLineBuilder = new StringBuilder();
            switch ( ctx.IDENTIFIER(0).getText()) {
                case "defun" -> {
                    javaLineBuilder
                            .append("public static int ")
                            .append(ctx.getChild(2).getText())
                            .append(visitArg((lisp_to_javaParser.ExpressionContext) ctx.getChild(3)))
                            .append(" {\n")
                            .append("   return ");//Думаю неудачное решение.
                    for (int i = 4; i < ctx.getChildCount() - 1; i++) {
                        ParseTree child = ctx.getChild(i);
                        if (child instanceof TerminalNode) {
                            TerminalNode node = (TerminalNode) child;
                            javaLineBuilder.append(node.getText());
                        } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                            javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child));
                        }
                    }
                    javaLineBuilder.append(";\n}");
                }
                case "+", "-", "*", "/" -> {
                    javaLineBuilder
                            .append(visit(ctx.getChild(2)))
                            .append(visit(ctx.getChild(1)))
                            .append(visit(ctx.getChild(3)));
                }
                default -> {
                    javaLineBuilder.append(ctx.getChild(1).getText());
                    javaLineBuilder.append(visitFunBody(ctx));
                }

            }
            return javaLineBuilder.toString();
        }
    public String visit(ParseTree parseTree) {

        if (parseTree instanceof lisp_to_javaParser.ProgramContext) {
            visitProgram((lisp_to_javaParser.ProgramContext) parseTree);
        } else if (parseTree instanceof lisp_to_javaParser.ExpressionContext) {
            return visitExpression((lisp_to_javaParser.ExpressionContext) parseTree);
        } else {
            //Полагаю тут может быть константа/переменная. ~ const/var
            return parseTree.toStringTree(parser);
        }
        return null;
    }

    public String visitFunBody(lisp_to_javaParser.ExpressionContext ctx) {
        StringBuilder javaLineBuilder = new StringBuilder();
        javaLineBuilder.append("(");
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode node) {
                System.out.println("Токен: " + node.getText());
                javaLineBuilder.append(node.getText()).append(", ");
            } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                javaLineBuilder.append(visitExpression((lisp_to_javaParser.ExpressionContext) child)).append(", ");
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
        return "(" +
                ctx.children.stream().skip(1)
                .limit(Math.max(1, ctx.children.size() - 2))
                .map(ParseTree::getText)
                .reduce((x, y)->x + ", " + y)
                .orElse("")
                 + ")";
    }

}
