package life;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] input = input().split(" ");

        char[][] universe = new char[Integer.parseInt(input[0])][Integer.parseInt(input[0])];
        Random random = new Random();
        int seed = random.nextInt();

        //fillUniverse(universe, Integer.parseInt(input[1]), Integer.parseInt(input[0]));
        fillUniverse(universe, seed, Integer.parseInt(input[0]));

        System.out.println("Generation: #" + 1);
        System.out.println("Alive: " + aliveCount(universe, Integer.parseInt(input[0])));
        printUniverse(universe, Integer.parseInt(input[0]));

        for (int i = 0; i < 10; i++) {
            universeGeneration(universe, Integer.parseInt(input[0]));
            //consolePrint(universe, Integer.parseInt(input[0]), i);
            //Thread.sleep(1000);
            System.out.println("Generation: #" + (i + 2));
            System.out.println("Alive: " + aliveCount(universe, Integer.parseInt(input[0])));
            printUniverse(universe, Integer.parseInt(input[0]));
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        //System.out.println(System.getProperty("os.name"));
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

    static Integer aliveCount(char[][] universe, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (universe[i][j] == 'O') {
                    count++;
                }
            }
        }
        return count;
    }

    static void consolePrint (char[][] universe, int size, int step) {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {
            System.out.println("error");
        }

        try {
            if (System.getProperty("os.name").contains("Mac OS X"))
                new ProcessBuilder("bash","-c","ls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {
            System.out.println("error");
        }

        System.out.println("Generation: " + (step + 1));
        System.out.println("Alive: " + aliveCount(universe, size));
    }

    static void universeGeneration(char[][] universe, int size) {
        int count = 0;
        char[][] tempUniverse = Arrays.stream(universe).map(char[]::clone).toArray(char[][]::new);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int n = i - 1; n < i + 2; n++) {
                    for (int m = j - 1; m < j + 2; m++) {
                        if (n >= size && m >= size) {
                            if (tempUniverse[n - size][m - size] == 'O') {
                                count++;
                            }
                        } else if (n >= size && m < 0) {
                            if (tempUniverse[n - size][m + size] == 'O') {
                                count++;
                            }
                        } else if (n < 0 && m >= size) {
                            if (tempUniverse[n + size][m - size] == 'O') {
                                count++;
                            }
                        } else if (n < 0 && m < 0) {
                            if (tempUniverse[n + size][m + size] == 'O') {
                                count++;
                            }
                        } else if (n < 0) {
                            if (tempUniverse[n + size][m] == 'O') {
                                count++;
                            }
                        } else if (n >= size) {
                            if (tempUniverse[n - size][m] == 'O') {
                                count++;
                            }
                        } else if (m >= size) {
                            if (tempUniverse[n][m - size] == 'O') {
                                count++;
                            }
                        } else if (m < 0) {
                            if (tempUniverse[n][m + size] == 'O') {
                                count++;
                            }
                        } else if (tempUniverse[n][m] == 'O') {
                            count++;
                        }
                    }
                }
                if (universe[i][j] == 'O' && (count - 1 < 2 || count - 1 > 3)) {
                    universe[i][j] = ' ';
                    //System.out.println(i + " " + j + " die");
                }
                if (universe[i][j] == ' ' && count == 3) {
                    universe[i][j] = 'O';
                    //System.out.println(i + " " + j + " life");
                }
                count = 0;

            }
        }
    }
}
