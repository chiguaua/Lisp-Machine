import java.util.function.Function;

public class TestOut {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int aAdd(int a, int b) {
        return a + b + a;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    public static int factorial(int a) {
        if (a > 1) {
            return a * factorial(a - 1);

        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(java.util.Arrays.asList(1, 2, 3, 4));
        Function<Integer, Integer> lambdaFunction1 = (x) -> {
            return x * x;
        };
        lambdaFunction1.apply(5);
        add(5, 3);
        subtract(10, 4);
        multiply(2, 6);
        divide(8, 2);
        System.out.println("dddqddq");
        int x = 10;
        int y = 5;
        System.out.println(x + y);
    }
}