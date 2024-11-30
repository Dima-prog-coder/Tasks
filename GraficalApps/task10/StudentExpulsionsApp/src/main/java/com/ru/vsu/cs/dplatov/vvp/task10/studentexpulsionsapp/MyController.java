package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp;

import com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver.Solve;
import com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp.solver.Student;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
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
        myView.getOutputStudentsBox().getChildren().clear();
        try {
            myView.getOutputStudentsBox().getChildren().clear();
            parseStudentsInp();
            studentsListOut = Solve.expelStudents(studentsListInp, myView.getRequiredAVGPoint().getValue(), myView.getLeaveStudentsCnt().getValue());
            for (Student student : studentsListOut) {
                myView.getOutputStudentsBox().getChildren().add(makeStudentBox(student.getFio(), Integer.toString(student.getCourse()), Integer.toString(student.getAvgPoint()), student.getImgPath()));
            }
        } catch (Exception e) {
            System.out.println("Wrong params");
        }
    }

    private void changeImgListener(Event event) {
        ImageView imageToChange = (ImageView) event.getSource();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterImg = new FileChooser.ExtensionFilter("Изображения (*.png, *.jpeg, *.jpg, *.gif)", "*.png", "*.jpeg", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extensionFilterImg);
        fileChooser.setTitle("Выберете изображение");
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            return;
        }
        Image image = new Image(file.toURI().toString());
        imageToChange.setImage(image);
        imageToChange.setFitHeight(100);
        imageToChange.setFitWidth(100);
        imageToChange.getProperties().put("relativePath", "/img/" + file.getName());
    }


    // supporting methods
    private HBox makeStudentBox() {
        return makeStudentBox("", "", "", "/img/defaultStudent.png");
    }

    private HBox makeStudentBox(String fio, String course, String AvgPoint, String imgPath) {
        TextField fioField = new TextField(fio);
        Label fioLabel = new Label("Fio");
        fioLabel.setLabelFor(fioField);
        TextField courseField = new TextField(course);
        Label courseLabel = new Label("Course");
        courseLabel.setLabelFor(courseField);
        TextField avgPointField = new TextField(AvgPoint);
        Label avgPointLabel = new Label("AvgPoint");
        avgPointLabel.setLabelFor(avgPointField);
        Image image = new Image((getClass().getResourceAsStream(imgPath)) == null ? (getClass().getResourceAsStream("/img/defaultStudent.png")) : (getClass().getResourceAsStream(imgPath)));
        ImageView studentImg = new ImageView(image);
        studentImg.setOnMouseClicked(this::changeImgListener);
        studentImg.getProperties().put("relativePath", imgPath);
        studentImg.setFitWidth(100);
        studentImg.setFitHeight(100);
        HBox studentBox = new HBox(studentImg, fioLabel, fioField, courseLabel, courseField, avgPointLabel, avgPointField);
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
        String fio = ((TextField) hBox.getChildren().get(2)).getText();
        int course = Integer.parseInt(((TextField) hBox.getChildren().get(4)).getText());
        int avgPoint = Integer.parseInt(((TextField) hBox.getChildren().get(6)).getText());
        return new Student(fio, course, avgPoint, imagePath);
    }
}
