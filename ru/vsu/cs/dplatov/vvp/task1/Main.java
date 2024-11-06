package ru.vsu.cs.dplatov.vvp.task1;


import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
//        создание переменных и запись в них значений из консольного ввода
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввод значений переменных:");
        System.out.print("a = ");
        int a = scanner.nextInt();
        System.out.print("b = ");
        int b = scanner.nextInt();
        System.out.print("c = ");
        int c = scanner.nextInt();
//        перестановка значений переменных
        a += b + c;
        b = a - b - c;
        c = a - b - c;
        a -= b + c;
//        вывод результатов
        System.out.println("Итог перестановки:");
        System.out.printf("a = %d\n", a);
        System.out.printf("b = %d\n", b);
        System.out.printf("c = %d", c);
        scanner.close();
    }
}
