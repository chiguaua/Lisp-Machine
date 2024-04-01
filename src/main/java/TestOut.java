import java.util.Scanner;

public class TestOut { public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Object input = scanner.nextInt();
    System.out.println(input );
    System.out.println("CALL JAVA FROM LISP");
    Object i = 0;
    Object j = 10;
    while (!(((Number) i).doubleValue()>= ((Number) 5).doubleValue())) {
        System.out.println(java.util.Arrays.asList(i, j));
        System.out.println(java.util.Arrays.asList(j, i));
        i = (((Number) i).doubleValue()+ ((Number) 1).doubleValue());
        j = (((Number) j).doubleValue()- ((Number) 2).doubleValue());

    };
    scanner.close();
}
}