import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Vision implements lisp_to_javaVisitor<Void> {
        @Override
        public Void visitProgram(lisp_to_javaParser.ProgramContext ctx) {
            // Обрабатываем каждое выражение в программе
            for (lisp_to_javaParser.ExpressionContext exprCtx : ctx.expression()) {
                visit(exprCtx);
            }
            return null; // Возвращаем null, так как не собираемся собирать данные из дерева
        }

        @Override
        public Void visitExpression(lisp_to_javaParser.ExpressionContext ctx) {
            System.out.println("Операция: " + ctx.OP().getText());
            System.out.println("Идентификатор: " + ctx.IDENTIFIER(0).getText());
            // Обработка дополнительных идентификаторов, строк, чисел или вложенных выражений
            for (int i = 1; i < ctx.getChildCount() - 1; i++) { // Пропускаем OP и CP
                ParseTree child = ctx.getChild(i);
                if (child instanceof TerminalNode) {
                    TerminalNode node = (TerminalNode) child;
                    System.out.println("Токен: " + node.getText());
                } else if (child instanceof lisp_to_javaParser.ExpressionContext) {
                    visitExpression((lisp_to_javaParser.ExpressionContext) child);
                }
            }
            return null;
        }

    @Override
    public Void visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Void visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Void visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Void visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
