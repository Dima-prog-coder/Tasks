package com.ru.vsu.cs.dplatov.vvp.task9.progressionapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.File;
import java.util.*;

import static com.ru.vsu.cs.dplatov.vvp.task9.progressionapp.utils.ListUtils.readIntListFromFile;


public class ProgressionApplication extends Application {
    private GridPane inpListGrid = new GridPane();
    private GridPane outListGrid = new GridPane();
    private int cols = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Создание основных элементов интерфейса для inp
        Button addColButton = new Button("Столбец");
        setGraphicOnButton(addColButton, "/img/add.png", 30, 30);
        Button removeColButton = new Button("Столбец");
        setGraphicOnButton(removeColButton, "/img/remove.png", 30, 30);
        Button randomizeListButton = new Button("Заполнить случайными");
        setGraphicOnButton(randomizeListButton, "/img/random.png", 30, 30);
        Button loadListFromFile = new Button("Массив из файла");
        setGraphicOnButton(loadListFromFile, "/img/folder.png", 30, 30);
        Button randomizeProgressionButton = new Button("Создать случайную прогрессию");
        setGraphicOnButton(randomizeProgressionButton, "/img/randomProgression.png", 30, 30);


        // Создание основных элементов интерфейса для out
        Button solveTheTask = new Button("Сформировать");
        setGraphicOnButton(solveTheTask, "/img/play.png", 30, 30);
        CheckBox saveInTxtFile = new CheckBox("Сохранять в txt файл");
        TextArea progressionInfo = new TextArea();
        progressionInfo.setEditable(false);
        progressionInfo.setFocusTraversable(false);

        Label labelProgressionInfo = new Label("Данные о прогрессии");
        labelProgressionInfo.setLabelFor(progressionInfo);


        // listeners
        addColButton.setOnAction(e -> addColumn());
        removeColButton.setOnAction(e -> removeColumn());
        randomizeListButton.setOnAction(e -> randomizeList());
        loadListFromFile.setOnAction(e -> addListFromFile());
        randomizeProgressionButton.setOnAction(e -> randomizeProgression());

        // listener for every cell in inpMatrixGrid
        solveTheTask.setOnAction(e -> updateMatrixOut(saveInTxtFile.isSelected()));

        // Панели инструментов и для inp и для out
        ToolBar toolBarInp = new ToolBar(addColButton, removeColButton, randomizeListButton, randomizeProgressionButton, loadListFromFile);
        ToolBar toolBarOut = new ToolBar(solveTheTask, saveInTxtFile);

        // инициализация таблицы по умолчанию
        updateMatrixGrid(Arrays.asList(0, 0, 0));

        // служебный объект для управления размерами элементов(VBox в основном)
        Screen screen = Screen.getPrimary();

        // VBox inp settings
        VBox inpRoot = new VBox(toolBarInp, inpListGrid);
        inpRoot.setMaxHeight(screen.getVisualBounds().getHeight() / 2);
        VBox.setVgrow(inpRoot, Priority.ALWAYS);

        // VBox out settings
        VBox outRoot = new VBox(toolBarOut, outListGrid);
        outRoot.setMaxHeight(screen.getVisualBounds().getHeight() / 4);
        VBox.setVgrow(outRoot, Priority.ALWAYS);

        // VBox progression info
        VBox infoRoot = new VBox(labelProgressionInfo, progressionInfo);
        infoRoot.setMaxHeight(screen.getVisualBounds().getHeight() / 4);
        VBox.setVgrow(infoRoot, Priority.ALWAYS);

        // Vbox main settings
        VBox mainRoot = new VBox();
        mainRoot.getChildren().addAll(inpRoot, outRoot, infoRoot);

        // scene and settings + loading css
        Scene scene = new Scene(mainRoot);
        scene.getStylesheets().add(getClass().getResource("/stylesCss/main.css").toExternalForm());

        // primaryStage settings
        primaryStage.setTitle("Progression App");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // listeners realization
    private void addColumn() {
        List<Integer> currentInp = parseMatrixGrid();
        if (cols >= 30) {
            showErrorAlert("Максимально поддерживаемое количество cтолбцов - 30.");
        } else {
            cols++;
            updateMatrixGrid(currentInp);
        }
    }

    private void removeColumn() {
        List<Integer> currentInp = parseMatrixGrid();
        if (cols > 1) {
            cols--;
            updateMatrixGrid(currentInp);
        }
    }

    private void randomizeList() {
        List<Integer> lst = createRandomList();
        updateMatrixGrid(lst);
    }

    private void randomizeProgression() {
        List<Integer> lst = createRandomArithmeticProgressionNotSorted();
        updateMatrixGrid(lst);
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
    private void addListFromFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterTxt = new FileChooser.ExtensionFilter("Текстовые файлы (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(extensionFilterTxt);
        fileChooser.setTitle("Выберите файл с массивом");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                List<Integer> lstFromFile = readIntListFromFile(file.getAbsolutePath());
                if (!lstFromFile.isEmpty()) {
                    if (lstFromFile.size() > 30) {
                        showErrorAlert("Слишком большая матрица. Максимально поддерживаемое количество строк - 15. Максимально поддерживаемое количество cтолбцов - 30.");
                    } else {
                        cols = lstFromFile.size();
                    }
                } else {
                    throw new IllegalArgumentException("Матрица не должна быть пустой!");
                }

                updateMatrixGrid(lstFromFile);
            } catch (Exception e) {
                showErrorAlert("• Проверьте корректность файла.\n• Убедитесь, что он не пустой.\n• Между элементами должны стоять разделители: \n\t• Пробельные символы \n\t• \",\" \n\t• \";\"");
                return;
            }
        }
    }

    /**
     * Обновляет текущую таблицу добавляя или удаляя из нее строки и столбцы.
     * Сохраняет уже введенные пользователем числа.
     */
    private void updateMatrixGrid(List<Integer> currentInp) {
        inpListGrid.getChildren().clear();
        outListGrid.getChildren().clear();
        for (int i = 0; i < cols; i++) {
            TextField cell = new TextField();
            try {
                cell.setText(String.valueOf(currentInp.get(i)));
            } catch (IndexOutOfBoundsException e) {
                cell.setText("0");
            }
            cell.textProperty().addListener(e -> {
                outListGrid.getChildren().clear();
            });
            inpListGrid.add(cell, i, 0);
        }
    }


    /**
     * Обновляет текущую таблицу вывода, получая данные из Solver, где прописана вся логика обработки.
     * Сохраняет данные в папку(outputFiles), лежащую в корне проекта под названием, состоящим из данных времени создания. Это
     * происходит если стоит галочка CheckBox saveInTxtFile.
     */
    private void updateMatrixOut(boolean isSave) {
        List<Integer> listOut = Solver.isArithmeticProgression(parseMatrixGrid());
        outListGrid.getChildren().clear();
        for (int i = 0; i < cols; i++) {
            TextField cell = new TextField();
            try {
                cell.setText(String.valueOf(listOut.get(i)));
            } catch (IndexOutOfBoundsException e) {
                cell.setText("0");
            }
            cell.setEditable(false);
            cell.setFocusTraversable(false);
            outListGrid.add(cell, i, 0);
        }
//        if (isSave) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//            String dateTimeString = sdf.format(new Date());
//            String fileName = "matrix_" + dateTimeString + ".txt";
//            String folderPath = "outputFiles";
//            Path filePath = Paths.get(folderPath, fileName);
//            try {
//                Files.createFile(filePath);
//                writeArrayToFile(filePath.toString(), matrixOut);
//            } catch (IOException e) {
//                showErrorAlert("Директория для записи не была найдена.");
//            }
//        }
    }


    /**
     * Парсит содержимое таблицы и возвращает его в виде двумерного массива.
     * Если текстовое поле пустое или содержит некорректные данные, то для этой ячейки устанавливается 0.
     *
     * @return двумерный массив int, содержащий значения из таблицы.
     */
    private List<Integer> parseMatrixGrid() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            try {
                TextField cell = (TextField) inpListGrid.getChildren().get(i);
                String digitInStr = cell.getText();
                list.add(Integer.parseInt(digitInStr));
            } catch (Exception e) {
                list.add(0);
            }

        }
        return list;
    }

    /**
     * Создаёт случайную матрицу размером {@code rows} x {@code cols},
     * заполняя её случайными целыми числами в диапазоне от 0 до 100.
     *
     * @return двумерный массив {@code int}, представляющий случайную матрицу.
     */
    private List<Integer> createRandomList() {
        List<Integer> lst = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < cols; i++) {
            lst.add(random.nextInt(101));
        }
        return lst;
    }

    private List<Integer> createRandomArithmeticProgressionNotSorted() {
        List<Integer> randomProgression = new ArrayList<>();
        List<Integer> indexesList = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            randomProgression.add(0);
            indexesList.add(i);
        }
        Random random = new Random();
        int curEl = 1000000 - random.nextInt(2001000);
        int d = random.nextInt(10000);
        for (int i = 0; i < cols; i++) {
            int rndIndx = randomChoice(indexesList);
            randomProgression.set(rndIndx, curEl);
            indexesList.remove((Integer) rndIndx);
            curEl += d;
        }
        return randomProgression;
    }

    private int randomChoice(List<Integer> lst) {
        Random random = new Random();
        int randInd = random.nextInt(0, lst.size());
        return lst.get(randInd);
    }

    /**
     * Показывает алерт об ошибке пользователю
     */
    private void showErrorAlert(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ErrorAlert");
        alert.setHeaderText("Произошла ошибка");
        alert.setContentText(errorText);
        alert.showAndWait();
    }

    private void setGraphicOnButton(Button button, String imgPath, int width, int height) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imgPath));
            ImageView imgView = new ImageView(image);
            imgView.setFitWidth(width);
            imgView.setFitHeight(height);
            button.setGraphic(imgView);
        } catch (Exception e) {

        }
    }
}
