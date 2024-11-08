package ru.vsu.cs.dplatov.vvp.task7;

import ru.vsu.cs.dplatov.vvp.task7.util.ArrayUtils;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        test();
        System.out.print(Arrays.toString(findLongestSubsequenceWithDifferentElements(ArrayUtils.readIntArrayFromConsole())));
    }

    public static int[] findLongestSubsequenceWithDifferentElements(int[] lst) {
        int firstSubsequenceEl = 0;
        int maxLength = 0;
        int r = 1;
        int l = 0;
        for (; r <= lst.length; r++) {
            int[] currentSlice = listSlice(lst, l, r);
            while (isRepeatingElExists(currentSlice)) {
                l += 1;
                currentSlice = listSlice(lst, l, r);
            }
            if (r - l >= maxLength) {
                firstSubsequenceEl = l;
                maxLength = r - l;
            }
        }
        return new int[]{firstSubsequenceEl, maxLength};
    }

    public static boolean isRepeatingElExists(int[] lstSlice) {
        int newEl = lstSlice[lstSlice.length - 1];
        for (int i = 0; i < lstSlice.length - 1; i++) {
            int el = lstSlice[i];
            if (el == newEl) {
                return true;
            }
        }
        return false;
    }

    public static int[] listSlice(int[] lst, int l, int r) {
        int[] slice = new int[r - l];
        for (int i = 0; r - l > i; i++) {
            slice[i] = lst[l + i];
        }
        return slice;
    }

    public static void test() {
        System.out.println("1,2,3,1,4");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{1, 2, 3, 1, 4})));

        System.out.println("1,2,2,3,4,2,2,11");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{1, 2, 2, 3, 4, 2, 2, 1})));

        System.out.println("5,5,5,5,5,5,5");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{5, 5, 5, 5, 5, 5, 5})));

        System.out.println("1,2,3,4,5,6,7,8,9");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})));

        System.out.println("1,2,1,2,1,2,3,4,5");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{1, 2, 1, 2, 1, 2, 3, 4, 5})));

        System.out.println("1");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{1})));

        System.out.println("10, 20, 30, 40, 50, 20, 30, 40, 50, 60, 70");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{10, 20, 30, 40, 50, 20, 30, 40, 50, 60, 70})));

        System.out.println("1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7})));

        System.out.println("100, 101, 102, 103, 104, 105, 100, 106, 107, 108, 109");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{100, 101, 102, 103, 104, 105, 100, 106, 107, 108, 109})));

        System.out.println("5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 6, 7, 8, 9, 10");
        System.out.println(Arrays.toString(findLongestSubsequenceWithDifferentElements(new int[]{5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 6, 7, 8, 9, 10})));
    }

}

