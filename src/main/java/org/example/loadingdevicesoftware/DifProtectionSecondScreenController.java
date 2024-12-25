package org.example.loadingdevicesoftware;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DifProtectionSecondScreenController {

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;

    private Stage stageForDifProtection;
    private Scene sceneForDifProtection;
    private Parent rootForDifProtection;

    //Объявление объекта для получения текущих значений даты и времени
    LocalDateTime currentDateTime;
    //Создание объекта для задания форматирования значений даты и времени
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
    //Объявление объекта для выполнения команды по заданному расписанию
    private ScheduledExecutorService scheduler;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;
    //Объявление кнопок для перехода в меню, пуска и отмены
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button cancelButton;
    //Объявление областей изображений для кнопок
    @FXML
    private ImageView toMenuButtonImageView;
    @FXML
    private ImageView startButtonImageView;
    @FXML
    private ImageView cancelButtonImageView;
    //Объявление области изображения для фона
    @FXML
    private ImageView backgroundImageView;
    //Объекты картинок для кнопок и статусов инверторов
    private Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаРамкаПуска.png")).toExternalForm());
    private Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    private Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());
    //Объект картинки для фона
    private Image background = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/диф_защита_2форма(без кнопок).png")).toExternalForm());


    @FXML
    public void initialize() {
        //Установка изображения на фон
        backgroundImageView.setImage(background);
        //Инициализация планировщика задач
        scheduler = Executors.newSingleThreadScheduledExecutor();
        //Вызов метода для запуска задачи по обновлению строки даты и времени
        startUpdatingDateAndTime();
        //Настройка кнопки "Меню"
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ", 138, 64);
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "ПУСК", 138, 64);
        //Настройка кнопки "Отмена"
        setupBottomButtons(cancelButton,cancelButtonImageView,lowButtoncImage, "ОТМЕНА", 138, 64);
    }

    //Метод для выполнения задачи обновления строки даты-времени
    public void startUpdatingDateAndTime() {
        Runnable updateDateTimeTask = () -> {
            currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);

            // Обновляем текстовое поле в JavaFX Application Thread
            Platform.runLater(() -> dateTimeText.setText(formattedDateTime));
            Platform.runLater(() -> System.out.println(1));
        };
        // Запуск задачи с интервалом в 1 минуту
        scheduler.scheduleAtFixedRate(updateDateTimeTask, 0, 1, TimeUnit.SECONDS);
    }
    //Метод для остановки обновления строки даты-времени
    public void stopUpdatingDateAndTime() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text,
                                   double width, double height) {
        imageView.setImage(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        button.setText(text);
        button.setStyle("-fx-background-color: #D0ECF8; " + // Светло-голубой фон
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-text-fill: black; " +        // Цвет текста
                "-fx-font-size: 26px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }
    //Метод для перехода на экран главного меню
    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("baseWindow.fxml"));
        rootForMainScreen = loader.load();

        MainScreenController mainController = loader.getController();

        stageForMainScreen = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForMainScreen = new Scene(rootForMainScreen);
        stageForMainScreen.setScene(sceneForMainScreen);
        stageForMainScreen.show();

    }
    //Метод для перехода на экран сценария диф.защиты
    public void goToDPScreen(ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DifProtection.fxml"));
        rootForDifProtection = loader.load();

        DifProtectionScreenController DifProtectionController = loader.getController();

        stageForDifProtection = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForDifProtection = new Scene(rootForDifProtection, 1280, 800);
        stageForDifProtection.setScene(sceneForDifProtection);
        stageForDifProtection.show();
    }
}