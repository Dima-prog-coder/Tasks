module com.example.matrixapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires commons.cli;


    opens com.task8.matrixapp to javafx.fxml;
    exports com.task8.matrixapp;
}