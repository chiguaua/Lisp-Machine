import java.util.Scanner;

public class TestOut {
    public static Object x = 1;
    public Object b = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        Object i = 0;
        Object j = 10;
        while (!(((Number) i).doubleValue() >= ((Number) 5).doubleValue())) {
            System.out.println(java.util.Arrays.asList(i, j));
            System.out.println(java.util.Arrays.asList(j, i));
            i = (((Number) i).doubleValue() + ((Number) 1).doubleValue());
            j = (((Number) j).doubleValue() - ((Number) 2).doubleValue());

        }
        ;
        scanner.close();
    }
}