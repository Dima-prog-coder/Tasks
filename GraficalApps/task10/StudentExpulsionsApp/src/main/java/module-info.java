module com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp to javafx.fxml;
    exports com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp;
}