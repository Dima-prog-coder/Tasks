package ru.vsu.cs.dplatov.vvp.task3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static final Line L1 = new Line(1, -3, -4);
    public static final Line L2 = new Line(-2, 9, -2.5);
    public static final HorizontalParabola HP1 = new HorizontalParabola(4, 4, -0.5, 4, -4);


    public static void printColorInConsole(SimpleColor color, double x, double y) {
        System.out.printf("(%.3f, %.3f) -> " + color +"\n", x, y);
    }

    public static SimpleColor getColor(double x, double y) {
        if (!HP1.isPointInParabola(x, y) && L1.isPointAboveLine(x, y) && L2.isPointAboveLine(x, y)) {
            return SimpleColor.WHITE;
        }
        if (HP1.isPointInParabola(x, y) && !L1.isPointAboveLine(x, y) && !L2.isPointAboveLine(x, y)) {
            return SimpleColor.GREEN;
        }
        if (HP1.isPointInParabola(x, y) && L1.isPointAboveLine(x, y) && !L2.isPointAboveLine(x, y) && y <= 3 && y >= 1) {
            return SimpleColor.GRAY;
        }
        if (HP1.isPointInParabola(x, y) && L1.isPointAboveLine(x, y) && L2.isPointAboveLine(x, y) || L1.isPointAboveLine(x, y) && !L2.isPointAboveLine(x, y) || !L1.isPointAboveLine(x, y) && L2.isPointAboveLine(x, y)) {
            return SimpleColor.BLUE;
        }
        if (y > 7) {
            return SimpleColor.GRAY;
        } else {
            return SimpleColor.YELLOW;
        }
    }

    public static double validateInputCoord(Scanner scanner, String message) {
        double coord;
        while (true) {
            System.out.print(message);
            try {
                coord = scanner.nextDouble();
                if (!(-10 <= coord && coord <= 10)) {
                    continue;
                }
                return coord;
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }
}