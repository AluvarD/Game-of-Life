package life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] input = input().split(" ");
        char[][] universe = new char[Integer.parseInt(input[0])][Integer.parseInt(input[0])];
        fillUniverse(universe, Integer.parseInt(input[1]), Integer.parseInt(input[0]));
        printUniverse(universe, Integer.parseInt(input[0]));
    }

    static String input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    static void fillUniverse (char[][] universe, int seed, int size) {
        Random random = new Random(seed);
        boolean value;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                value = random.nextBoolean();;
                if (value) {
                    universe[i][j] = 'O';
                } else {
                    universe[i][j] = ' ';
                }
            }
        }
    }

    static void printUniverse (char[][] universe, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(universe[i][j]);
            }
            System.out.print("\n");
        }
    }
}
