package life;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        //String[] input = input().split(" ");

        char[][] universe = new char[30][30];
        Random random = new Random();
        int seed = random.nextInt();

        GameOfLifeProcess.gameOfLifeProcess(universe, seed, 30);
    }

    static String input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}
