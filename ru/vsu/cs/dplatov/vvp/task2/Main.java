package ru.vsu.cs.dplatov.vvp.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // получение пользовательских данных
        int p1 = getUserDataAboutCakes(scanner, "Введите цену первого то́рта: ");
        int n1 = getUserDataAboutCakes(scanner, "Введите количество первых то́ртов в наличии: ");
        int p2 = getUserDataAboutCakes(scanner, "Введите цену второго то́рта: ");
        int n2 = getUserDataAboutCakes(scanner, "Введите количество вторых то́ртов в наличии: ");
        int m = getUserDataAboutCakes(scanner, "Сколько у Вас денег? ");
        // вычисление кол-ва тортов для покупки
        printAnswerInConsole(calculateShoppingPossibilities(p1, n1, p2, n2, m));
    }

    public static int calculateShoppingPossibilities(int p1, int n1, int p2, int n2, int m) {
        if (n1 == 0 || n2 == 0 || (p1 + p2 > m)) {
            return 0;
        }
        m -= (p1 + p2);
        n1--;
        n2--;
        int cakesCount = 2;
        if (p2 > p1) {
            cakesCount += n1;
            m -= p1 * n1;
            if (m > 0) {
                m -= p2 * n2;
                cakesCount += n2;
                if (m < 0) {
                    cakesCount -= Math.ceil((double) Math.abs(m) / p2);
                }
                return cakesCount;
            } else if (m == 0) {
                return cakesCount;
            } else {
                cakesCount -= Math.ceil((double) Math.abs(m) / p1);
                return cakesCount;
            }
        } else {
            cakesCount += n2;
            m -= p2 * n2;
            if (m > 0) {
                m -= p1 * n1;
                cakesCount += n1;
                if (m < 0) {
                    cakesCount -= Math.ceil((double) Math.abs(m) / p1);
                }
                return cakesCount;
            } else if (m == 0) {
                return cakesCount;
            } else {
                cakesCount -= Math.ceil((double) Math.abs(m) / p2);
                return cakesCount;
            }
        }
    }

    public static int getUserDataAboutCakes(Scanner scanner, String userMessage) {
        while (true) {
            System.out.print(userMessage);
            int retDigit = scanner.nextInt();
            if (retDigit >= 0) {
                return retDigit;
            } else {
                System.out.println("Число должно быть целым и неотрицательным!");
            }
        }
    }

    public static void printAnswerInConsole(int cakesCount) {
        System.out.printf("Вы можете купить %d шт", cakesCount);
    }
}

// test data 1 20 10 1 15 --> 6
// 20 1 10 3 40 --> 3
//


//if (!(n1 == 0 || n2 == 0 || (p1 + p2 > m))) {
//int last_money = m - (p1 + p2);
//int cnt_cakes = 2;
//n1--;
//n2--;
//        while (last_money >= Math.min(p1, p2) && n1 + n2 > 0) {
//        if (n1 > 0 && n2 > 0) {
//        if (p1 > p2) {
//n2--;
//last_money -= p2;
//cnt_cakes++;
//        } else {
//n1--;
//last_money -= p1;
//cnt_cakes++;
//        }
//        } else {
//        if (n1 > 0) {
//        if (p1 <= last_money) {
//n1--;
//last_money -= p1;
//cnt_cakes++;
//        } else {
//        break;
//        }
//        } else if (n2 > 0) {
//        if (p2 <= last_money) {
//n2--;
//last_money -= p2;
//cnt_cakes++;
//        } else {
//        break;
//        }
//        }
//        }
//        }
//        return cnt_cakes;
//
//        } else {
//                return 0;
//                }