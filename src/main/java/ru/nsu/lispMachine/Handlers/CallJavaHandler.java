package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

public class CallJavaHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
        StringBuilder javaLineBuilder = new StringBuilder();
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            ParseTree child = ctx.getChild(i);
            javaLineBuilder.append(child.getText());
        }
        return javaLineBuilder.toString();
    }
}
