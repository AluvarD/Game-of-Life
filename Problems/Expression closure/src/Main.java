import java.util.function.DoubleUnaryOperator;

class Operator {

    public static int a = 10;
    public static int b = 20;
    public static int c = 30;

    public static DoubleUnaryOperator unaryOperator = (double x) -> (double) a * Math.pow(x, 2) + (double) b * x + (double) c;// Write your code here
}