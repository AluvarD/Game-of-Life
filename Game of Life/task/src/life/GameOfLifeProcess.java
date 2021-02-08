package life;

import java.util.Arrays;
import java.util.Random;

public class GameOfLifeProcess {
    public static boolean reset = false;

    static void start () throws InterruptedException {
        char[][] universe = new char[30][30];
        Random random = new Random();
        int seed = random.nextInt();

        gameOfLifeProcess(universe, seed, 30);
    }

    static void gameOfLifeProcess (char[][] universe, int seed, int size) throws InterruptedException {
        GameOfLife view = new GameOfLife();

        while (true) {
            fillUniverse(universe, seed, size);

            view.setGeneration(1);
            view.setAlive(aliveCount(universe, size));

            int i = 2;
            while (!reset) {
                while (!view.isPlay() && !reset) {
                    Thread.sleep(50L);
                }
                UniverseGeneration universeGeneration = new UniverseGeneration(universe, size);
                universeGeneration.run();


                System.out.println("Generation: #" + i);
                System.out.println("Alive: " + aliveCount(universe, size));
                view.setGeneration(i);
                view.setAlive(aliveCount(universe, size));
                view.setUniverse(universe);


                if (aliveCount(universe, size) == 0) {
                    System.out.println("All die");
                    break;
                }
                i++;
            }
            reset = false;
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


    static class UniverseGeneration implements Runnable {
        char[][] universe;
        int size;

        public UniverseGeneration(char[][] universe, int size) {
            this.universe = universe;
            this.size = size;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500L);
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
