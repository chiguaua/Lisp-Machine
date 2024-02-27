import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/main/resources/test.lisp";
        CharStream inputFile = null;
        try {
            inputFile = CharStreams.fromPath(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lisp_to_javaLexer lexer = new lisp_to_javaLexer(inputFile);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        lisp_to_javaParser parser = new lisp_to_javaParser(tokens);

        Vision visitor = new Vision(parser);
        visitor.visit(parser.program());
    }
}
