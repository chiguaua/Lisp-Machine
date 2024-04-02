import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
//        String filePath = "src/main/resources/testDefault.lisp";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        switch (input) {
            case "test" -> {
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

                Vision visitor = new Vision(parser, "src/test/java/TestOut.java");
                visitor.visit(parser.program(), false);
            }
            case "file" -> {
                String lispFileName = scanner.next();
                String javaFileName = scanner.next();

                CharStream inputFile = null;
                try {
                    inputFile = CharStreams.fromPath(Paths.get(lispFileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lisp_to_javaLexer lexer = new lisp_to_javaLexer(inputFile);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                lisp_to_javaParser parser = new lisp_to_javaParser(tokens);

                Vision visitor = new Vision(parser, javaFileName);
                visitor.visit(parser.program(), false);
            }
            case "code" -> {
                StringBuilder inputBuilder = new StringBuilder();

                System.out.println("Введите Lisp код (двойной Enter для завершения):");
                String line = scanner.next();
                while (!line.equals("$$")) {
                    inputBuilder.append(line).append(" "); // Добавляем перенос строки для сохранения формата
                    line = scanner.next();
                }

                String code = inputBuilder.toString();
                CharStream inputStream = CharStreams.fromString(code);

                lisp_to_javaLexer lexer = new lisp_to_javaLexer(inputStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                lisp_to_javaParser parser = new lisp_to_javaParser(tokens);

                Vision visitor = new Vision(parser);
                visitor.visit(parser.program(), false);
            }
            default -> System.out.println(input);
        }



        scanner.close();
    }
}
