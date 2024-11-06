package ru.vsu.cs.dplatov.vvp.lessonstraining;

public class Main1 {
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        for (int j : arr) {
            System.out.println(j);
        }
        Integer a = 3;
        a += 1;
        int b = 4;
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
