package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentExpulsionsApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        MyView root = new MyView();
        Scene scene = new Scene(root.getRoot());
        scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
        new MyController(root);
        primaryStage.setTitle("Progression App");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
