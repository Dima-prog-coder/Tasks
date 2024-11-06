package ru.vsu.cs.dplatov.vvp.task4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double n = getUserdigit(new Scanner(System.in));
        printAnswerInConsole(calculateLeastFibonachiMoreThanN(n));
    }

    public static double getUserdigit(Scanner scanner) {
        double n;
        while (true) {
            System.out.print("Введите число n (n>=0): ");
            try {
                n = scanner.nextDouble();
                if (n < 0) {
                    continue;
                }
                return n;
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }


    public static int calculateLeastFibonachiMoreThanN(double n) {
        if (n == 0) {
            return 0;
        }
        int v1 = 0, v2 = 0, v3 = 1, v4 = v1 + v2 + v3;
        while (n > v4) {
            v1 = v2;
            v2 = v3;
            v3 = v4;
            v4 = v1 + v2 + v3;
        }
        return v4;
    }

    public static void printAnswerInConsole(int cakesCount) {
        System.out.printf("Ваше число %d", cakesCount);
    }
}
