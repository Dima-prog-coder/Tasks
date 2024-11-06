package ru.vsu.cs.dplatov.vvp.task7;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        test();
        System.out.print(findLongestSubsequenceWithDifferentElements(readListFromConsole("Somelist")));
    }

    public static List<Integer> findLongestSubsequenceWithDifferentElements(List<Integer> lst) {
        int firstSubsequenceEl = 0;
        int maxLength = 0;
        int r = 0;
        int l = 0;
        for (; r <= lst.size(); r++) {
            List<Integer> currentSlice = listSlice(lst, l, r);
            HashSet<Integer> set = new HashSet<>(currentSlice);
            while (set.size() != currentSlice.size()) {
                l += 1;
                currentSlice = listSlice(lst, l, r);
                set = new HashSet<>(currentSlice);
            }
            if (r - l >= maxLength) {
                firstSubsequenceEl = l;
                maxLength = r - l;
            }
        }
        return new ArrayList<Integer>(Arrays.asList(firstSubsequenceEl, maxLength));
    }

    public static List<Integer> listSlice(List<Integer> lst, int l, int r) {
        List<Integer> slice = new ArrayList<>();
        for (int i = l; i < r; i++) {
            slice.add(lst.get(i));
        }
        return slice;
    }

    public static List<Integer> toIntList(String s) {
        Scanner scanner = new Scanner(s);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> retLst = new ArrayList<>();
        while (scanner.hasNext()) {
            retLst.add(scanner.nextInt());
        }
        return retLst;
    }

    public static List<Integer> readListFromConsole(String listName) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (listName == null || listName.isEmpty()) {
                    listName = " ";
                }
                System.out.printf("Введите список %s : ", listName);
                String line = scanner.nextLine();
                return toIntList(line);

            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }
    }

    public static void test(){
        System.out.println("1,2,3,4,5,6,1,1,2,3,4,5,6,7,8,9");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(1,2,3,4,5,6,1,1,2,3,4,5,6,7,8,9)));

        System.out.println("1,2,2,3,4,2,2,11");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(1,2,2,3,4,2,2,1)));

        System.out.println("5,5,5,5,5,5,5");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(5,5,5,5,5,5,5)));

        System.out.println("1,2,3,4,5,6,7,8,9");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(1,2,3,4,5,6,7,8,9)));

        System.out.println("1,2,1,2,1,2,3,4,5");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(1,2,1,2,1,2,3,4,5)));

        System.out.println("1");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(1)));

        System.out.println("10, 20, 30, 40, 50, 20, 30, 40, 50, 60, 70");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(10, 20, 30, 40, 50, 20, 30, 40, 50, 60, 70)));

        System.out.println("1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7)));

        System.out.println("100, 101, 102, 103, 104, 105, 100, 106, 107, 108, 109");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(100, 101, 102, 103, 104, 105, 100, 106, 107, 108, 109)));

        System.out.println("5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 6, 7, 8, 9, 10");
        System.out.println(findLongestSubsequenceWithDifferentElements(List.of(5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 6, 7, 8, 9, 10)));
    }

}

