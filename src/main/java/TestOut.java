import java.util.Scanner;

public class TestOut {
    public static Object checkProgn() {
        System.out.println("e");
        System.out.println("9");
        return (((Number) 5).doubleValue() + ((Number) 5).doubleValue());
    }

    public static Object factorial(Object a) {
        if ((((Number) a).doubleValue() > ((Number) 1).doubleValue())) {
            return (((Number) a).doubleValue() * ((Number) factorial((((Number) a).doubleValue() - ((Number) 1).doubleValue()))).doubleValue());

        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}