import javax.imageio.ImageTranscoder;
import java.util.Scanner;

public class Main {

    public static int convert(Long val) {
        // write your code here
        if (val == null) {
            return 0;
        } else if (Long.parseLong(String.valueOf(Integer.MAX_VALUE)) < val) {
            return Integer.MAX_VALUE;
        } else if (Long.parseLong(String.valueOf(Integer.MIN_VALUE)) > val) {
            return Integer.MIN_VALUE;
        } else {
            return Integer.parseInt(String.valueOf(val));
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        Long longVal = "null".equals(val) ? null : Long.parseLong(val);
        System.out.println(convert(longVal));
    }
}