package life;

import java.util.Arrays;
import java.util.Random;

public class GameOfLife {

    private static long mainThreadId = Thread.currentThread().getId();

    static void gameOfLife (char[][] universe, int seed, int size) throws InterruptedException {
        fillUniverse(universe, seed, size);

        System.out.println("Generation: #" + 1);
        System.out.println("Alive: " + aliveCount(universe, size));
        printUniverse(universe, size);

        for (int i = 0; i < 10; i++) {

            UniverseGeneration universeGeneration = new UniverseGeneration(universe, size);
            universeGeneration.start();
            universeGeneration.join();

            System.out.println("Generation: #" + (i + 2));
            System.out.println("Alive: " + aliveCount(universe, size));
            printUniverse(universe, size);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
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


    static class UniverseGeneration extends Thread {
        char[][] universe;
        int size;

        public UniverseGeneration(char[][] universe, int size) {
            this.universe = universe;
            this.size = size;
        }

        @Override
        public void run() {
            final long currentId = Thread.currentThread().getId();

            if (currentId == mainThreadId) {
                throw new RuntimeException("You must start a new thread!");
            }

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
}
