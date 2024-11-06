package ru.vsu.cs.dplatov.vvp.task3;

import java.util.Scanner;

import static ru.vsu.cs.dplatov.vvp.task3.Program.*;

public class Main {
    public static void main(String[] args) {
        printColorInConsole(getColor(-3, 2), -3, 2);
        printColorInConsole(getColor(-4, 9), -4, 9);
        printColorInConsole(getColor(-2, 0), -2, 0);
        printColorInConsole(getColor(1, -1), 1, -1);
        printColorInConsole(getColor(4, 0), 4, 0);
        printColorInConsole(getColor(0, 1.5), 0, 1.5);
        Scanner scanner = new Scanner(System.in);
        double x = validateInputCoord(scanner, "Input X: ");
        double y = validateInputCoord(scanner, "Input Y: ");
        printColorInConsole(getColor(x, y), x, y);
        scanner.close();
    }
}
