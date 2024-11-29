package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp;

import com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver.Solve;
import com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class MyController {
    private final MyView myView;
    private List<Student> studentsListInp;
    private List<Student> studentsListOut;


    public MyController(MyView myView) {
        this.myView = myView;
        addListeners();
    }

    // adding listeners for myView(get from initializing) buttons
    private void addListeners() {
        myView.getAddStudentButton().addEventHandler(ActionEvent.ACTION, event -> addNewRowListener());
        myView.getRemoveLastStudentButton().addEventHandler(ActionEvent.ACTION, event -> removeLastRowListener());
        myView.getSolveButton().addEventHandler(ActionEvent.ACTION, event -> solveListener());
    }

    // listeners section
    private void addNewRowListener() {
        HBox newStudent = makeStudentBox();
        myView.getInputStudentsBox().getChildren().add(newStudent);
        myView.getOutputStudentsBox().getChildren().clear();
    }

    private void removeLastRowListener() {
        try {
            myView.getInputStudentsBox().getChildren().removeLast();
            myView.getOutputStudentsBox().getChildren().clear();
        } catch (Exception e) {
            System.out.println("No elements to remove");
        }
    }

    private void solveListener() {
        try {
            myView.getOutputStudentsBox().getChildren().clear();
            parseStudentsInp();
            studentsListOut = Solve.expelStudents(studentsListInp, Integer.parseInt(myView.getRequiredAVGPoint().getText()), myView.getLeaveStudentsCnt().getValue());
            for (Student student : studentsListOut) {
                myView.getOutputStudentsBox().getChildren().add(makeStudentBox(student.getFio(), Integer.toString(student.getCourse()), Integer.toString(student.getAvgPoint()), student.getImgPath()));
            }
        } catch (Exception e) {
            System.out.println("Wrong params");
        }
    }


    // supporting methods
    private HBox makeStudentBox() {
        return makeStudentBox("", "", "", "/img/defaultStudent.png");
    }

    private HBox makeStudentBox(String fio, String course, String AvgPoint, String imgPath) {
        TextField fioField = new TextField(fio);
        TextField courseField = new TextField(course);
        TextField avgPointField = new TextField(AvgPoint);
        Image image = new Image(getClass().getResourceAsStream(imgPath));
        ImageView studentImg = new ImageView(image);
        studentImg.getProperties().put("relativePath", imgPath);
        HBox studentBox = new HBox(studentImg, fioField, courseField, avgPointField);
        myView.setStudentBoxStyles(studentBox);
        return studentBox;
    }


    private void parseStudentsInp() {
        studentsListInp = new ArrayList<>();
        for (HBox student : myView.getBeforeExplusionsInsideList()) {
            try {
                studentsListInp.add(createStudentObjectByHBox(student));
            } catch (Exception e) {
                System.out.println("The wrong input line ignored");
            }
        }
    }

    private Student createStudentObjectByHBox(HBox hBox) {
        String imagePath = (String) hBox.getChildren().get(0).getProperties().get("relativePath");
        String fio = ((TextField) hBox.getChildren().get(1)).getText();
        int course = Integer.parseInt(((TextField) hBox.getChildren().get(2)).getText());
        int avgPoint = Integer.parseInt(((TextField) hBox.getChildren().get(3)).getText());
        return new Student(fio, course, avgPoint, imagePath);
    }
}
