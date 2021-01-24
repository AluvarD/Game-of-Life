package life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] input = input().split(" ");

        char[][] universe = new char[Integer.parseInt(input[0])][Integer.parseInt(input[0])];
        fillUniverse(universe, Integer.parseInt(input[1]), Integer.parseInt(input[0]));

        for (int i = 0; i < Integer.parseInt(input[2]); i++) {
            System.out.print("\n Generation: " + (i + 1) + "\n");
            universeGeneration(universe, Integer.parseInt(input[0]));
            printUniverse(universe, Integer.parseInt(input[0]));
        }

        //printUniverse(universe, Integer.parseInt(input[0]));
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

    static void universeGeneration (char[][] universe, int size) {
        int count = 0;
        char[][] tempUniverse = copyUniverse(universe, size);;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i - 1 < 0 && j - 1 < 0) { //left upper corner
                    for (int n = 0; n < 2; n++) {
                        for (int m = 0; m < 2; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if (i + 1 >= size && j - 1 < 0) { //left down corner
                    for (int n = size - 2; n < size; n++) {
                        for (int m = 0; m < 2; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if (i - 1 < 0 && j + 1 >= size) { //right upper corner
                    for (int n = 0; n < 2; n++) {
                        for (int m = size - 2; m < size; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if (i + 1 >= size && j + 1 >= size) { //right down corner
                    for (int n = size - 2; n < size; n++) {
                        for (int m = size - 2; m < size; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if (i - 1 < 0 && (j - 1 == 0 || j + 1 <= size)) { //top line
                    for (int n = 0; n < 2; n++) {
                        for (int m = j - 1; m <= j + 1; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if (i + 1 >= size && (j - 1 == 0 || j + 1 <= size)) { //bottom line
                    for (int n = size - 2; n < size; n++) {
                        for (int m = j - 1; m <= j + 1; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if ((i - 1 == 0 || i + 1 < size) && (j - 1 < 0)) { //left line
                    for (int n = i - 1; n < i + 2; n++) {
                        for (int m = 0; m < 2; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else if ((i - 1 == 0 || i + 1 < size) && (j == size - 1)) { //right line
                    for (int n = i - 1; n < i + 2; n++) {
                        for (int m = size - 2; m < size; m++) {
                            if (tempUniverse[n][m] == 'O') {
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
                } else { //remaining field
                    for (int n = i - 1; n < i + 2; n++) {
                        for (int m = j - 1; m < j + 2; m++) {
                            if (tempUniverse[n][m] == 'O') {
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

    static char[][] copyUniverse (char[][] universe, int size) {
        char[][] copy = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = universe[i][j];
            }
        }
        return copy;
    }
}
