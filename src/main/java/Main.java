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
        // Замените "path/to/your/file.txt" на реальный путь к вашему файлу
        String filePath = "src/main/resources/test.lisp";

        // Создаем входной поток из файла
        CharStream inputFile = null;
        try {
            inputFile = CharStreams.fromPath(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Инициализируем лексер
        lisp_to_javaLexer lexer = new lisp_to_javaLexer(inputFile);
        // Создаем поток токенов на основе лексера
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Инициализируем парсер
        lisp_to_javaParser parser = new lisp_to_javaParser(tokens);
        // Вызываем начальное правило грамматики (expr)
        ParseTree tree = parser.program().expression(1);
        // Выводим дерево разбора (для отладки)
        System.out.println(tree.toStringTree(parser));

        // Создаем и запускаем посетителя или слушателя
        Vision visitor = new Vision();
        visitor.visitExpression(parser.program().expression(1));
    }
}
