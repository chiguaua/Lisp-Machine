import java.util.Scanner;

public class TestOut {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Object x = 2;
        if ((((Number) x).doubleValue() == ((Number) 1).doubleValue())) {
            System.out.println("1");
        } else if ((((Number) x).doubleValue() == ((Number) 2).doubleValue())) {
            System.out.println("2");
        } else if ((((Number) x).doubleValue() == ((Number) 3).doubleValue())) {
            System.out.println("3");
        } else if (true) {
            System.out.println("S");
        } else {
            throw new IllegalArgumentException("No branch of cond matched");
        }
        ;
        scanner.close();
    }
}