package ru.nsu.lispMachine.Handlers;


import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.lispMachine.LispHandler;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import static ru.nsu.lispMachine.Vision.visit;

public class CondHandler implements LispHandler {
    @Override
    public String apply(lisp_to_javaParser.ExpressionContext ctx, boolean needReturn) {
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
}
