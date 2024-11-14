package com.task8.matrixapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

import static com.task8.matrixapp.utils.ArrayUtils.readIntArray2FromFile;

public class MainApplication extends Application {

    private GridPane inpMatrixGrid = new GridPane();
    private GridPane outMatrixGrid = new GridPane();
    private int rows = 3;
    private int cols = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button addRowButton = new Button("+ Строка");
        Button removeRowButton = new Button("- Строка");
        Button addColButton = new Button("+ Столбец");
        Button removeColButton = new Button("- Столбец");
        Button randomizeMatrixButton = new Button("Заполнить случайными");
        Button loadMatrixFromFile = new Button("Матрица из файла");

        Button solveTheTask = new Button("Выполнить");
        CheckBox saveInTxtFile = new CheckBox("Сохранять в txt файл");

        addRowButton.setOnAction(e -> addRow());
        removeRowButton.setOnAction(e -> removeRow());
        addColButton.setOnAction(e -> addColumn());
        removeColButton.setOnAction(e -> removeColumn());
        randomizeMatrixButton.setOnAction(e -> randomizeMatrix());
        loadMatrixFromFile.setOnAction(e -> addMatrixFromFile());

        solveTheTask.setOnAction(e -> updateMatrixOut(saveInTxtFile.isSelected()));

        ToolBar toolBarInp = new ToolBar(addRowButton, removeRowButton, addColButton, removeColButton, randomizeMatrixButton, loadMatrixFromFile);
        ToolBar toolBarOut = new ToolBar(solveTheTask, saveInTxtFile);

        updateMatrixGrid(new int[rows][cols]);


        VBox inpRoot = new VBox(toolBarInp, inpMatrixGrid);
        VBox outRoot = new VBox(toolBarOut, outMatrixGrid);
        VBox mainRoot = new VBox(15);
        mainRoot.getChildren().addAll(inpRoot, outRoot);
        Scene scene = new Scene(mainRoot, 800, 600);

        primaryStage.setTitle("Matrix App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //    Блок для работы с вводом пользователя
    private void addRow() {
        int[][] currentInp = parseMatrixGrid();
        rows++;
        updateMatrixGrid(currentInp);
    }

    private void removeRow() {
        int[][] currentInp = parseMatrixGrid();
        if (rows > 1) {
            rows--;
            updateMatrixGrid(currentInp);
        }
    }

    private void addColumn() {
        int[][] currentInp = parseMatrixGrid();
        cols++;
        updateMatrixGrid(currentInp);
    }

    private void removeColumn() {
        int[][] currentInp = parseMatrixGrid();
        if (cols > 1) {
            cols--;
            updateMatrixGrid(currentInp);
        }
    }

    private void randomizeMatrix() {
        int[][] randomMatrix = createRandomMatrix();
        updateMatrixGrid(randomMatrix);
    }

    /**
     * Загружает матрицу из выбранного файла и обновляет отображение в графическом интерфейсе.
     * <p>
     * Функция открывает диалоговое окно, позволяя пользователю выбрать файл.
     * Затем она пытается прочитать матрицу из этого файла, используя метод
     * {@code readIntArray2FromFile(String filePath)}, который возвращает матрицу в виде двумерного массива int.
     * <p>
     * Если файл корректен и содержит матрицу, то размеры текущей матрицы (rows и cols)
     * обновляются в соответствии с размерами загруженной матрицы, и она отображается в сетке.
     * В случае ошибки (пустой файл, некорректный формат) выводится всплывающее окно с
     * сообщением об ошибке, информирующее пользователя о необходимых форматах и условиях.
     * <p>
     * Исключения:
     * - IllegalArgumentException: если матрица пуста.
     * - Всплывающее окно с описанием ошибок: если формат данных в файле некорректен.
     * <p>
     */
    private void addMatrixFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл с матрицей");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                int[][] matrixFromFile = readIntArray2FromFile(file.getAbsolutePath());
                if (matrixFromFile.length > 0 && matrixFromFile[0].length > 0) {
                    rows = matrixFromFile.length;
                    cols = matrixFromFile[0].length;
                } else {
                    throw new IllegalArgumentException("Матрица не должна быть пустой!");
                }

                updateMatrixGrid(matrixFromFile);
            } catch (Exception e) {
                showErrorAlert("• Проверьте корректность файла.\n• Убедитесь, что он не пустой.\n• Между элементами должны стоять разделители: \n\t• Пробельные символы \n\t• \",\" \n\t• \";\" \n• Каждая новая строка матрицы должна идти в новой строке файла. Недопустимы пустые строки посреди строк матрицы.");
                return;
            }
        }
    }

    /**
     * Обновляет текущую таблицу добавляя или удаляя из нее строки и столбцы.
     * Сохраняет уже введенные пользователем числа.
     */
    private void updateMatrixGrid(int[][] currentInp) {
        inpMatrixGrid.getChildren().clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TextField cell = new TextField();
                try {
                    cell.setText(String.valueOf(currentInp[i][j]));
                } catch (IndexOutOfBoundsException e) {
                    cell.setText("0");
                }
                inpMatrixGrid.add(cell, j, i);
            }
        }
    }

    private void updateMatrixOut(boolean isSave) {
        int[][] matrixOut = Solver.solve(parseMatrixGrid());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TextField cell = new TextField();
                try {
                    cell.setText(String.valueOf(matrixOut[i][j]));
                } catch (IndexOutOfBoundsException e) {
                    cell.setText("0");
                }
                inpMatrixGrid.add(cell, j, i);
            }
        }
    }


    /**
     * Парсит содержимое таблицы и возвращает его в виде двумерного массива.
     * Если текстовое поле пустое или содержит некорректные данные, то для этой ячейки устанавливается 0.
     *
     * @return двумерный массив int, содержащий значения из таблицы.
     */
    private int[][] parseMatrixGrid() {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    TextField cell = (TextField) inpMatrixGrid.getChildren().get(i * cols + j);
                    String digitInStr = cell.getText();
                    matrix[i][j] = Integer.parseInt(digitInStr);
                } catch (Exception e) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /**
     * Создаёт случайную матрицу размером {@code rows} x {@code cols},
     * заполняя её случайными целыми числами в диапазоне от 0 до 100.
     *
     * @return двумерный массив {@code int}, представляющий случайную матрицу.
     */
    private int[][] createRandomMatrix() {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(101);
            }
        }
        return matrix;
    }

    /**
     * Показывает алерт об ошибке пользователю
     */
    private void showErrorAlert(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Произошла ошибка");
        alert.setContentText(errorText);
        alert.showAndWait();
    }
}