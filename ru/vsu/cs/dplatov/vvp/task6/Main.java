package ru.vsu.cs.dplatov.vvp.task6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = getUserN(scanner);
        double eps = getUserEps(scanner);
        double x = getUserX(scanner);
        printAnswerInConsole("C точностью n",calculateSumUntilN(x, n));
        printAnswerInConsole("С точностью эпсилон",calculateSumDependsOfE(x, eps));
        printAnswerInConsole("С точностью эпсилон:10", calculateSumDependsOfE(x, eps / 10));
        printAnswerInConsole("При помощи модуля Math", calculateByMath(x));
        scanner.close();
    }

    public static double calculateSumDependsOfE(double x, double eps) {
        double rowResultE = 0;
        double thisStep = x;
        double power = x;
        for (int i = 1; Math.abs(thisStep) > eps; i++) {
            rowResultE += thisStep;
            power *= x * x;
            thisStep = power / (2 * i + 1);
        }
        return rowResultE * 2;
    }

    public static double calculateSumUntilN(double x, int n) {
        double sum = x;
        double newElement;
        double power = x;
        for (int i = 1; i < n; i++) {
            power *= x * x;
            System.out.println((2 * i + 1));
            newElement = power / (2 * i + 1);
            sum += newElement;
        }
        return sum * 2;
    }

    public static double calculateByMath(double x) {
        return Math.log((1 + x) / (1 - x));
    }

    public static int getUserN(Scanner scanner) {
        int n;
        while (true) {
            System.out.print("Введите число n (n>=0): ");
            try {
                n = scanner.nextInt();
                if (n < 0) {
                    System.out.println("Неверный ввод");
                    continue;
                }
                return n;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Неверный ввод");
            }
        }
    }

    public static double getUserEps(Scanner scanner) {
        double eps;
        while (true) {
            System.out.print("Введите число eps (0.1, 0.01, ...): ");
            try {
                eps = scanner.nextDouble();
                if (eps < 0 || Math.log10(eps) != Math.floor(Math.log10(eps))) {
                    System.out.println("Неверный ввод");
                    continue;
                }
                return eps;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Неверный ввод");
            }
        }
    }

    public static double getUserX(Scanner scanner) {
        double x;
        while (true) {
            System.out.print("Введите число x [-1;1]: ");
            try {
                x = scanner.nextDouble();
                if (-1 > x || x > 1) {
                    System.out.println("Неверный ввод");
                    continue;
                }
                return x;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Неверный ввод");
            }
        }
    }

    public static void printAnswerInConsole(String description, double ans) {
        System.out.printf("%s: %f \n", description, ans);
    }
}


