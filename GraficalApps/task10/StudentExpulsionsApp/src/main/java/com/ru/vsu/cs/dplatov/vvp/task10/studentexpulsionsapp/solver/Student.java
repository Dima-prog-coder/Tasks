package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver;

public class Student {
    private String imgPath;
    private String fio;
    private int course;
    private int avgPoint;

    public Student(String fio, int course, int avgPoint, String imgPath) {
        this.fio = fio;
        this.course = course;
        this.avgPoint = avgPoint;
        this.imgPath = imgPath;
    }

    public Student(String fio, int course, int avgPoint) {
        this(fio, course, avgPoint, "/img/defaultStudent.png");
    }

    @Override
    public String toString() {
        return fio + "_" + course + "_" + avgPoint + "_" + imgPath;
    }

    public String getFio() {
        return fio;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course > 0 && course < 7) {
            this.course = course;
        }
    }


    public int getAvgPoint() {
        return avgPoint;
    }

    public void setAvgPoint(int avgPoint) {
        this.avgPoint = avgPoint;
    }
}
