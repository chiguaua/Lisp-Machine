import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создаем входной поток из строки или файла
        String expression = "(+ 5 2)  (add (* 4 3) 5)";
        ANTLRInputStream input = new ANTLRInputStream(expression);

        // Инициализируем лексер
        lisp_to_javaLexer lexer = new lisp_to_javaLexer(input);

        // Создаем поток токенов на основе лексера
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Инициализируем парсер
        lisp_to_javaParser parser = new lisp_to_javaParser(tokens);

        // Вызываем начальное правило грамматики (expr)
        ParseTree tree = parser.expression();

        // Выводим дерево разбора (для отладки)
        System.out.println(tree.toStringTree(parser));

    }
}
