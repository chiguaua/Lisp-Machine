import java.util.function.Function;

public class TestOut {public static Object add(Object a, Object b) {
    return (((Number) a).doubleValue()+((Number) b).doubleValue());
}
    public static Object aAdd(Object a, Object b) {
        return (((Number) (((Number) a).doubleValue()+((Number) b).doubleValue())).doubleValue()+((Number) a).doubleValue());
    }
    public static Object subtract(Object a, Object b) {
        return (((Number) a).doubleValue()-((Number) b).doubleValue());
    }
    public static Object multiply(Object a, Object b) {
        return (((Number) a).doubleValue()*((Number) b).doubleValue());
    }
    public static Object divide(Object a, Object b) {
        return (((Number) a).doubleValue()/((Number) b).doubleValue());
    }
    public static Object factorial(Object a) {
        if ((((Number) a).doubleValue()>((Number) 1).doubleValue())) {
            return (((Number) a).doubleValue()*((Number) factorial((((Number) a).doubleValue()-((Number) 1).doubleValue()))).doubleValue());

        } else {
            return 1;
        }}
    public static void main(String[] args) {
        Function<Object, Object> lambdaFunction0 = (x) -> {return (((Number) x).doubleValue()*((Number) x).doubleValue());
    };
        lambdaFunction0.apply(5);
        add(3, 1);
        subtract(10, 4);
        multiply(2, 6);
        divide(8, 2);
        System.out.println("dddqddq" );
        Object x = 10;
        Object y = 5;
        System.out.println(add(x, y));
        System.out.println(factorial(5));
    }
}