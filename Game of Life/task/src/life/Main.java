package life;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] input = input().split(" ");

        char[][] universe = new char[Integer.parseInt(input[0])][Integer.parseInt(input[0])];
        Random random = new Random();
        int seed = random.nextInt();
        GameOfLife.gameOfLife(universe, seed, Integer.parseInt(input[0]));

    }

    static String input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}
