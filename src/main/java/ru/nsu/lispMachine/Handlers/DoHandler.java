package ru.nsu.lispMachine.Handlers;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.type;
import static ru.nsu.lispMachine.Vision.visit;

public class DoHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
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
