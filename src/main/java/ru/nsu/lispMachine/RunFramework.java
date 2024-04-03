package ru.nsu.lispMachine;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ru.nsu.lispMachine.Handlers.*;
import ru.nsu.lispMachine.Parser.lisp_to_javaLexer;
import ru.nsu.lispMachine.Parser.lisp_to_javaParser;

import java.io.IOException;
import java.nio.file.Paths;

public class RunFramework {
    static String inpFile;
    public static void SetDefaultHandler(){
        Vision.addHandler("defun", new DefunHandler());
        Vision.addHandler("do", new DoHandler());
        Vision.addHandler("+", new MathAndLogicHandler());
        Vision.addHandler("-", new MathAndLogicHandler());
        Vision.addHandler("*", new MathAndLogicHandler());
        Vision.addHandler("/", new MathAndLogicHandler());
        Vision.addHandler(">", new MathAndLogicHandler());
        Vision.addHandler("<", new MathAndLogicHandler());
        Vision.addHandler("=", new MathAndLogicHandler());
        Vision.addHandler("rem", new MathAndLogicHandler());
        Vision.addHandler("mod", new MathAndLogicHandler());
        Vision.addHandler(">=", new MathAndLogicHandler());
        Vision.addHandler("<=", new MathAndLogicHandler());

        Vision.addHandler("print", new PrintHandler());
        Vision.addHandler("read", new ReadHandler());
        Vision.addHandler("let", new LetHandler());

        Vision.addHandler("and", new AndHandler());
        Vision.addHandler("if", new IfHandler());
        Vision.addHandler("or", new OrHandler());
        Vision.addHandler("not", new NotHandler());

        Vision.addHandler("lambda", new LambdaHandler());
        Vision.addHandler("loop", new LoopHandler());
        Vision.addHandler("list", new ListHandler());

        Vision.addHandler("car", new CarHandler());
        Vision.addHandler("cdr", new CdrHandler());
        Vision.addHandler("cons", new ConsHandler());
        Vision.addHandler("cond", new CondHandler());

        Vision.addHandler("setf", new SetfHandler());
        Vision.addHandler("setq", new SetqHandler());
        Vision.addHandler("progn", new PrognHandler());
        Vision.addHandler("@call-java", new CallJavaHandler());


    }

    public static void SetFiles (String inputFiles, String outputFiles) {
        Vision.setOutputFile(outputFiles);
        inpFile = inputFiles;
    }

    public static void SetCustomHandler(String triger, LispHandler handler) {
        Vision.addHandler(triger, handler);
    }

    public static boolean Translate (){

        CharStream inputFile = null;
        try {
            inputFile = CharStreams.fromPath(Paths.get(inpFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lisp_to_javaLexer lexer = new lisp_to_javaLexer(inputFile);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        lisp_to_javaParser parser = new lisp_to_javaParser(tokens);

        Vision.setParser(parser);
        Vision.visit(parser.program(), false);
        return false;
    }
}
