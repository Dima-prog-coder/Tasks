package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.utils;

import com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentUtils {
    public static List<Student> readCandyListFromFile(String fileName) {
        try {
            return toStudentList(readLinesFromFile(fileName));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static List<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    public static List<Student> toStudentList(List<String> lines) {
        List<Student> studentList = new ArrayList<>();
        String regex = "[,.:]";
        for (String line : lines) {
            try {
                String[] elInfo = line.split(regex);
                Student student;
                if (elInfo.length == 4) {
                    student = new Student(elInfo[0].trim(), Integer.parseInt(elInfo[1].trim()), Integer.parseInt(elInfo[2].trim()), elInfo[3].trim());
                } else {
                    student = new Student(elInfo[0].trim(), Integer.parseInt(elInfo[1].trim()), Integer.parseInt(elInfo[2].trim()));
                }
                studentList.add(student);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return studentList;
    }
}


