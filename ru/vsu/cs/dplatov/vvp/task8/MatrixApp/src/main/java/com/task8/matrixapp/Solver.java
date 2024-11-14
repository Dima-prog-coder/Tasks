package com.task8.matrixapp;

import java.util.Arrays;

public class Solver {
    public static void main(String[] args) {
        System.out.print(Arrays.deepToString(solve(new int[][]{{1, 2, 3}, {2, 3, 4}})));
    }

    public static int[][] solve(int[][] arr) {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                newArr[i][j] = arr[i][j] + 1;
            }
        }
        return newArr;
    }
}
