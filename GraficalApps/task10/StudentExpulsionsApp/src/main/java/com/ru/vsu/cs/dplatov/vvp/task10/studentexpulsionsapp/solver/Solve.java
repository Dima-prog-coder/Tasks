package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver;//25.	(*) Входные данные соответствуют предыдущей задаче, но теперь студентов будем отчислять.
//Необходимо выбрать студентов для отчисления следующим образом: всех, у кого средний балл ниже X.
//Однако после отчисления на каждом курсе должно остаться не менее N студентов. Если по критерию отчисления «средний балл
//        < X» для какого-то курса будет отчислено слишком много студентов, то отчислить самых слабых, оставив на данном курсе
//ровно N студентов. Если при этом окажется так, что несколько студентов с одинаковыми баллами окажется на границе отчисления,
//то не отчислять никого (например, надо до N отчислить 3 человек, а у нас 5 совершенно одинаковых двоечников, то все эти 5
//        двоечников продолжат учиться, а на курсе, соответственно, останется N+2 студента).

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solve {
    public static void test() {
        List<Student> students = Arrays.asList(
                new Student("Dima", 1, 88),
                new Student("Anastasia", 1, 50)
        );
//        for (List<Student> list : sortByCourses(students)) {
//            System.out.println(list);
//        }
        System.out.println();
        for (Student student : expelStudents(students, 85, 1)) {
            System.out.println(student);
        }
    }


    public static List<Student> expelStudents(List<Student> studentsList, int minAvgPoint, int keepCnt) {
        List<List<Student>> studentsListSortedByCourses = sortByCourses(studentsList);
        for (List<Student> studentsListOnCourse : studentsListSortedByCourses) {
            expelOnCourse(studentsListOnCourse, minAvgPoint, keepCnt);
        }
        return listMatrixToList(studentsListSortedByCourses);
    }

    public static List<Student> listMatrixToList(List<List<Student>> matrix) {
        List<Student> outList = new ArrayList<>();
        for (List<Student> courseList : matrix) {
            outList.addAll(courseList);
        }
        return outList;
    }

    public static List<List<Student>> sortByCourses(List<Student> studentsList) {
        List<List<Student>> studentsMatrix = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            studentsMatrix.add(new ArrayList<>());
        }
        for (Student student : studentsList) {
            studentsMatrix.get(student.getCourse() - 1).add(student);
        }
        return studentsMatrix;
    }

    public static void expelOnCourse(List<Student> studentsListOnCourse, int minAvgPoint, int keepCnt) {
        List<Student> onExpulsion = new ArrayList<>();
        for (int i = 0; i < studentsListOnCourse.size(); i++) {
            Student currentStudent = studentsListOnCourse.get(i);
            if (currentStudent.getAvgPoint() < minAvgPoint) {
                onExpulsion.add(currentStudent);
                studentsListOnCourse.remove((int) i);
            }
        }
        if (onExpulsion.size() + studentsListOnCourse.size() <= keepCnt) {
            studentsListOnCourse.addAll(onExpulsion);
        } else {
            while (studentsListOnCourse.size() < keepCnt) {
                List<Student> studentsBack = findStudentsByPoint(onExpulsion, findMaxAvgPoint(onExpulsion));
                for (Student student : studentsBack) {
                    studentsListOnCourse.add(student);
                    onExpulsion.remove(student);
                }
            }
        }
    }

    public static int findMaxAvgPoint(List<Student> studentList) {
        int maxPoint = Integer.MIN_VALUE;
        for (Student student : studentList) {
            if (student.getAvgPoint() > maxPoint) {
                maxPoint = student.getAvgPoint();
            }
        }
        return maxPoint;
    }

    public static List<Student> findStudentsByPoint(List<Student> studentsList, int point) {
        List<Student> studentsListWithThisPoint = new ArrayList<>();
        for (Student student : studentsList) {
            if (student.getAvgPoint() == point) {
                studentsListWithThisPoint.add(student);
            }
        }
        return studentsListWithThisPoint;
    }
}
