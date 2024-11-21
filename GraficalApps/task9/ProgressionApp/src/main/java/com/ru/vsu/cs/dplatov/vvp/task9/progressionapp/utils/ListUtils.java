package com.ru.vsu.cs.dplatov.vvp.task9.progressionapp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ListUtils {
    public static String readLineFromFile(String fileName) throws FileNotFoundException {
        String line = "";
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            line = scanner.nextLine();
        }
        return line;
    }

    public static List<Integer> toIntList(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    public static List<Integer> readIntListFromFile(String fileName) {
        try {
            return toIntList(readLineFromFile(fileName));
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
