
import java.util.Scanner;

public class TestOut { public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Object x = 10;
    Object y = "Hello";
    Object z = (((Number) x).doubleValue()+ ((Number) 5).doubleValue());
    System.out.println(z );
    scanner.close();
}
}
