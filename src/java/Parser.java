import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    //Понимаю, глобальные переменные зло, но всё же...
    static List<Function> allCode = new ArrayList<>();
    static boolean expectedFunction = true;
    static Function currentFunction;
    static String newFunctionName = "";
    public static void main(String[] args) {
        String filePath = "src/resource/test.lisp";
        try {
            //Check Mismatched parentheses
            Stack<String> stack = new Stack<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int nestingLevel = 0; // Не уверен как правильно пишется, но типа уровень вложености функций :D
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {

                    switch (c) {
                       case('(') -> {
                           check(nestingLevel);
                           expectedFunction = true;
                           nestingLevel+=1;
                           stack.push("(");
                       }
                       case (')') -> {
                           check(nestingLevel);
                           nestingLevel-=1;
                           if (stack.isEmpty() || !stack.pop().equals("(")) {
                               System.err.println("Error: Mismatched parentheses");
                               return;
                           }
                           currentFunction = currentFunction.usedIn;
                       }
                       case(' ') -> {
                           check(nestingLevel);
                       }
                        default -> {
                           newFunctionName = newFunctionName + c;
                        }
                    }
                }
            }
            if (!stack.isEmpty()) {
                System.err.println("Error: Mismatched parentheses");
            } else {
                System.out.println("All parentheses are balanced.");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Converter conv = new Converter(allCode);
        conv.convert();
    }

    //Попытка визуализации
    private static void check(int n) {
        if (!newFunctionName.equals("")) {
            String infinitySpace = "                                                                                                    ";
            Function newFunctionArrive = new Function(newFunctionName, n, currentFunction);
            allCode.add(newFunctionArrive);
            if(n > 1) {
                currentFunction.arguments.add(newFunctionArrive);
            }
            if (expectedFunction) {
                System.out.println(infinitySpace.substring(0, n) + newFunctionName + " ;expected function. level - " + n);
                currentFunction = newFunctionArrive;
                expectedFunction = false;
            } else {
                System.out.println(infinitySpace.substring(0, n) + newFunctionName);
            }

            newFunctionName = "";
        }
    }

}


