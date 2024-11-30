package com.ru.vsu.cs.dplatov.vvp.task10.studentexpulsionsapp;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MyView {
    // Основные элементы
    private final HBox root;
    private VBox inputStudentsBox;
    private ToolBar verticalToolbar;
    private VBox outputStudentsBox;

    // Малые элементы
    private Button addStudentButton;
    private Button removeLastStudentButton;
    private Button addStudentsFromFileButton;
    private Button solveButton;
    private Spinner<Integer> leaveStudentsCnt;
    private Label labelForLeaveStudentsCnt;
    private Spinner<Integer> requiredAVGPoint;
    private Label labelForRequiredAVGPoint;

    // Инициализация интерфейса
    public MyView() {
        root = new HBox(); // main Box
        addButtonsOnToolbar(); // ads all buttons form createButtons to verticalToolbar
        createInputsBox(); // creates VBox for input students
        createOutputBox();
        ScrollPane beforeExpulsionsScroll = makeNewScrollPaneForVBox(inputStudentsBox);
        ScrollPane afterExpulsionsScroll = makeNewScrollPaneForVBox(outputStudentsBox);
        root.getChildren().addAll(beforeExpulsionsScroll, verticalToolbar, afterExpulsionsScroll);

    }

    // getters
    public VBox getInputStudentsBox() {
        return inputStudentsBox;
    }


    public VBox getOutputStudentsBox() {
        return outputStudentsBox;
    }

    public Button getSolveButton() {
        return solveButton;
    }

    public Button getAddStudentButton() {
        return addStudentButton;
    }

    public HBox getRoot() {
        return root;
    }

    public Button getRemoveLastStudentButton() {
        return removeLastStudentButton;
    }

    public Spinner<Integer> getLeaveStudentsCnt() {
        return leaveStudentsCnt;
    }

    public Spinner<Integer> getRequiredAVGPoint() {
        return requiredAVGPoint;
    }

    // custom getters
    public List<HBox> getBeforeExplusionsInsideList() {
        List<HBox> list = new ArrayList<>();
        for (Node el : inputStudentsBox.getChildren()) {
            if (el instanceof HBox) {
                list.add((HBox) el);
            }
        }
        return list;
    }

    // scrollPane creator and scrollPane settings
    private ScrollPane makeNewScrollPaneForVBox(VBox vBox) {
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.prefWidthProperty().bind(root.widthProperty().multiply(0.45));
        return scrollPane;
    }

    // buttons adder
    private void addButtonsOnToolbar() {
        verticalToolbar = new ToolBar();
        verticalToolbar.setOrientation(Orientation.VERTICAL);
        createButtons();
        createForm();
        verticalToolbar.getStyleClass().add("verticalToolbar");
        verticalToolbar.getItems().addAll(addStudentButton, removeLastStudentButton, addStudentsFromFileButton, labelForLeaveStudentsCnt, leaveStudentsCnt, labelForRequiredAVGPoint, requiredAVGPoint, solveButton);
    }

    // form initializer
    private void createForm() {
        leaveStudentsCnt = new Spinner<>();
        leaveStudentsCnt.setEditable(true);
        leaveStudentsCnt.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 30));
        labelForLeaveStudentsCnt = new Label("Leave students");
        labelForLeaveStudentsCnt.setLabelFor(leaveStudentsCnt);
        requiredAVGPoint = new Spinner<>();
        requiredAVGPoint.setEditable(true);
        requiredAVGPoint.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50));
        labelForRequiredAVGPoint = new Label("Required AVG point");
        labelForLeaveStudentsCnt.setLabelFor(requiredAVGPoint);
    }

    // buttons creating
    private void createButtons() {
        addStudentButton = new Button("Add student");
        removeLastStudentButton = new Button("Remove last student");
        solveButton = new Button("Expel students");
        addStudentsFromFileButton = new Button("Add students from file");
    }

    // input and output VBox initializer
    private void createInputsBox() {
        inputStudentsBox = new VBox();
        inputStudentsBox.prefWidthProperty().bind(root.widthProperty().multiply(0.45));
        inputStudentsBox.getStyleClass().add("inputStudentsBox");
    }

    private void createOutputBox() {
        outputStudentsBox = new VBox();
        outputStudentsBox.prefWidthProperty().bind(root.widthProperty().multiply(0.45));
    }

    public void setStudentBoxStyles(HBox studentBox) {
        VBox.setMargin(studentBox, new Insets(5));
        studentBox.prefWidthProperty().bind(inputStudentsBox.widthProperty());
        studentBox.getStyleClass().add("studentBox");
        for (Node node : studentBox.getChildren()) {
            VBox.setMargin(node, new Insets(5));
        }
    }
}
