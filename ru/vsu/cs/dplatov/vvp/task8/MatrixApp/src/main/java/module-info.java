module com.example.matrixapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.task8.matrixapp to javafx.fxml;
    exports com.task8.matrixapp;
}