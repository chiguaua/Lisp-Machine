import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<String, String> lispToJavaMap = new HashMap<>();

    static {
        lispToJavaMap.put("+", "Math.addExact");
        lispToJavaMap.put("-", "Math.subtractExact");
        lispToJavaMap.put("*", "Math.multiplyExact");
        lispToJavaMap.put("/", "/");
    }

    public static void main(String[] args) {
        String lispCodeFile = "src/test.lisp";
        try {
            String javaCode = translateLispToJava(lispCodeFile);
            System.out.println(javaCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String translateLispToJava(String fileName) throws IOException {
        StringBuilder javaCodeBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                javaCodeBuilder.append(translateLine(line)).append("\n");
            }
        }
        return javaCodeBuilder.toString();
    }

    private static String translateLine(String lispLine) {
        StringBuilder javaLineBuilder = new StringBuilder();
        String[] tokens = lispLine.trim().split("\\s+");

        if (tokens[0].equals("(defun")) {
            // Function definition translation
            String functionName = tokens[1];
            javaLineBuilder.append("public static int ").append(functionName).append("(");
            for (int i = 2; i < tokens.length - 1; i++) {
                // Adjusted loop termination condition to avoid removing last parameter
                javaLineBuilder.append("int ").append(tokens[i].replaceAll("[()]", "")).append(", ");
            }
            if (tokens.length > 2) {
                javaLineBuilder.delete(javaLineBuilder.length() - 2, javaLineBuilder.length()); // Remove the last comma and space
            }
            javaLineBuilder.append(") {\nreturn ");
        } else {
            // Function call translation
            javaLineBuilder.append(tokens[0].replaceAll("[()]", "")).append("(");
        }

        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (!token.equals("(") && !token.equals(")")) {
                if (lispToJavaMap.containsKey(token)) {
                    javaLineBuilder.append(lispToJavaMap.get(token)).append(", ");
                } else {
                    javaLineBuilder.append(token.replaceAll("[()]", "")).append(", ");
                }
            }
        }

        if (!tokens[0].equals("(defun")) {
            if (javaLineBuilder.length() > 0) {
                // Ensure StringBuilder has content before attempting deletion
                if (javaLineBuilder.length() > 1) {
                    javaLineBuilder.delete(javaLineBuilder.length() - 2, javaLineBuilder.length()); // Remove the last comma and space
                }
            }
        }

        javaLineBuilder.append(");\n");
        return javaLineBuilder.toString().trim();
    }

}
