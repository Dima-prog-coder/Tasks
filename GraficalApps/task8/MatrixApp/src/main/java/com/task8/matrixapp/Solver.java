package com.task8.matrixapp;

import java.util.Arrays;
import java.util.List;

public class Solver {
    public static boolean isElInArr(List<int[]> lst, int[] el) {
        for (int[] lstEl : lst) {
            if (Arrays.equals(lstEl, el)) {
                return true;
            }
        }
        return false;
    }

    public static FriendsInfo findFriendly(int[][] arr, int i, int j, FriendsInfo friendsInfo) {
        int[] di = {-1, 0, 0, 1};  // Directions for rows
        int[] dj = {0, -1, 1, 0};  // Directions for columns

        for (int d = 0; d < di.length; d++) {
            if ((i + di[d] >= 0 && di[d] + i < arr.length) && (j + dj[d] >= 0 && dj[d] + j < arr[0].length)) {
                if (arr[i + di[d]][j + dj[d]] == arr[i][j] && !isElInArr(friendsInfo.lstIndexes, new int[]{i + di[d], j + dj[d]})) {
                    friendsInfo.cnt += 1;
                    friendsInfo.lstIndexes.add(new int[]{i + di[d], j + dj[d]});
                    findFriendly(arr, i + di[d], j + dj[d], friendsInfo);
                }
            } else {
                continue;
            }
        }
        return friendsInfo;


    }


    public static int[][] solve(int[][] arr) {
        int[][] newArr = initializationNewMatrix(arr.length, arr[0].length, -1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (newArr[i][j] == -1) {
                    FriendsInfo friendsInfo = findFriendly(arr, i, j, new FriendsInfo(new int[]{i, j}, 0));
                    for (int[] indxs : friendsInfo.lstIndexes) {
                        newArr[indxs[0]][indxs[1]] = friendsInfo.cnt;
                    }
                } else {
                    continue;
                }
            }
        }
        return newArr;
    }

    public static int[][] initializationNewMatrix(int rows, int cols, int defaultValue) {
        int[][] retMatrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                retMatrix[i][j] = defaultValue;
            }
        }
        return retMatrix;
    }
}

