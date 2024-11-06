package ru.vsu.cs.dplatov.vvp.task3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * "Горизонтальная" парабола вида x = a * (y - y0) + x0
 */

public class HorizontalParabola {
    public double x0;
    public double y0;
    public double a;
    public double b;
    public double c;

    public HorizontalParabola(double x0, double y0, double a, double b, double c) {
        this.x0 = x0;
        this.y0 = y0;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Проверяет, находится ли точка (x, y) внутри
     * области ограниченной параболой
     */
    public boolean isPointInParabola(double x, double y) {
        ArrayList<Double> yCoords = new ArrayList<>();
        if (this.a > 0) {
            if (!(this.x0 <= x && x <= 10)) {
                return false;
            }
            for (double i = -10; i <= 10; i += 0.1) {
                if (Math.abs(this.a * Math.pow(i - this.y0, 2) + x0 - x) <= 0.5) {
                    yCoords.add(i);
                }
            }
        } else {
            if (!(-10 <= x && x <= this.x0)) {
                return false;
            }
            for (double i = -10; i <= 10; i += 0.1) {
                if (Math.abs(this.a * Math.pow(i - this.y0, 2) + x0 - x) <= 0.5) {
                    yCoords.add(i);
                }
            }
        }
//        System.out.println(yCoords);
        return Collections.min(yCoords) <= y && y <= Collections.max(yCoords);
    }

    /**
     * Проверяет, находится ли точка (x, y) справа
     * (сверху, если повернуть изобюражение на 90 градусов
     * против часовой стрелки)
     * от параболы
     */
    public boolean isPointRightOfParabola(double x, double y) {
        return x > a * Math.pow(y - y0, 2) + x0;
    }
}



