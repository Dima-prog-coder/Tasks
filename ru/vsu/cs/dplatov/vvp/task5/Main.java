package ru.vsu.cs.dplatov.vvp.task5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printTrianglePatternInConsole(getUserdigit(scanner));
        scanner.close();
    }

    public static void printTrianglePatternInConsole(int s) {
        int firstSpace, beetweenSpace;
        for (int i = 0; i <= s; i++) {
            for (int numOfPyramid = -s; numOfPyramid <= s * 2 - 1; numOfPyramid++) {
                beetweenSpace = (s - 1) + (s - 2) - (i - 2) * 2 - 1;
                if (i != 1) {
                    firstSpace = calculateLength(s) / 2 - i * i - beetweenSpace * (i - 1);
                } else {
                    firstSpace = calculateLength(s) / 2 - 1;
                }
                int space;
                if (numOfPyramid <= 0 && i - Math.abs(numOfPyramid) == 1 && i != s) {
                    space = firstSpace;
                } else if (i - Math.abs(numOfPyramid) <= 0 || i == s) {
                    space = 0;
                } else {
                    space = beetweenSpace;
                }
                while (space > 0) {
                    System.out.print(' ');
                    space -= 1;
                }
                for (int j = 0; j < i - Math.abs(numOfPyramid); j++) {
                    System.out.print('/');
                }
                for (int j = 0; j < i - Math.abs(numOfPyramid); j++) {
                    System.out.print('\\');
                }
            }
            System.out.println();
        }
    }

    public static int calculateLength(int s) {
        int element = 0;
        int sum = 0;
        for (int i = 0; i < s - 1; i++) {
            element += 2;
            sum += element * 2;
        }
        sum += element + 2;
        return sum;
    }

    public static int getUserdigit(Scanner scanner) {
        int s;
        while (true) {
            System.out.print("Введите число s (s>=1): ");
            try {
                s = scanner.nextInt();
                if (s < 1) {
                    System.out.println("Неверный ввод");
                    continue;
                }
                return s;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Неверный ввод");
            }
        }
    }
}

//public static void printTrianglePatternInConsole(int s) {
//    for (int i = 0; i <= s; i++) {
//        for (int l = 0; i > l; l++) {
//            int space;
//            int leftSpace = calculateLength(s);
//            if (i != s && l > 0) {
//                space = (s - i) * 2;
//            } else if (l == 0) {
//                space = s - i + leftSpace / 2 - 1;
//            } else {
//                space = s - i;
//            }
//            while (space > 0) {
//                System.out.print(' ');
//                space -= 1;
//            }
//            for (int j = 0; j < i - l; j++) {
//                System.out.print('/');
//            }
//            for (int j = 0; j < i - l; j++) {
//                System.out.print('\\');
//            }
//        }
//        System.out.println();
//    }
//}