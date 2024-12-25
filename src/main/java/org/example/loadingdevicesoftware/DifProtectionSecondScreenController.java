package org.example.loadingdevicesoftware;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    //Объявление текстовых полей для демонстрации статуса запуска
    @FXML
    private Text status1;
    @FXML
    private Text status2;
    @FXML
    private Text status3;
    @FXML
    private Text status4;
    @FXML
    private Text status5;
    @FXML
    private Text status6;

    //Объявление кнопок для перехода в меню, пуска и отмены
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button cancelButton;
    //Объявление кнопок для задания статуса выполнения операций при пуске
    @FXML
    private Button status1True;
    @FXML
    private Button status1False;
    @FXML
    private Button status2True;
    @FXML
    private Button status2False;
    @FXML
    private Button status3True;
    @FXML
    private Button status3False;
    @FXML
    private Button status4True;
    @FXML
    private Button status4False;
    @FXML
    private Button status5True;
    @FXML
    private Button status5False; 
    @FXML
    private Button status6True;
    @FXML
    private Button status6False;
    //Объявление областей изображений для кнопок статусов
    @FXML
    private ImageView status1TrueImageView;
    @FXML
    private ImageView status1FalseImageView;
    @FXML
    private ImageView status2TrueImageView;
    @FXML
    private ImageView status2FalseImageView;
    @FXML
    private ImageView status3TrueImageView;
    @FXML
    private ImageView status3FalseImageView;
    @FXML
    private ImageView status4TrueImageView;
    @FXML
    private ImageView status4FalseImageView;
    @FXML
    private ImageView status5TrueImageView;
    @FXML
    private ImageView status5FalseImageView;
    @FXML
    private ImageView status6TrueImageView;
    @FXML
    private ImageView status6FalseImageView;
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
            getResource("/screen/дифзащита/Кнопка(черная).png")).toExternalForm());
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
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ", 138, 70);
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "ПУСК", 138, 70);
        //Настройка кнопки "Отмена"
        setupBottomButtons(cancelButton,cancelButtonImageView,lowButtoncImage, "ОТМЕНА", 138, 70);
        //Печать текста первой проверки
        typeText(status1,"1. Введены все параметры формы", 100);
        //Скрытие кнопок остальных проверок
        setStatucButtonDisabled(status2True,status2TrueImageView);
        setStatucButtonDisabled(status2False,status2FalseImageView);
        setStatucButtonDisabled(status3True,status3TrueImageView);
        setStatucButtonDisabled(status3False,status3FalseImageView);
        setStatucButtonDisabled(status4True,status4TrueImageView);
        setStatucButtonDisabled(status4False,status4FalseImageView);
        setStatucButtonDisabled(status5True,status5TrueImageView);
        setStatucButtonDisabled(status5False,status5FalseImageView);
        setStatucButtonDisabled(status6True,status6TrueImageView);
        setStatucButtonDisabled(status6False,status6FalseImageView);
        //Настройка всех кнопок статусов проверок
        setupStatusButton(status1True,status1TrueImageView,statusConnected);
        setupStatusButton(status1False,status1FalseImageView,statusDisconnected);
        setupStatusButton(status2True,status2TrueImageView,statusConnected);
        setupStatusButton(status2False,status2FalseImageView,statusDisconnected);
        setupStatusButton(status3True,status3TrueImageView,statusConnected);
        setupStatusButton(status3False,status3FalseImageView,statusDisconnected);
        setupStatusButton(status4True,status4TrueImageView,statusConnected);
        setupStatusButton(status4False,status4FalseImageView,statusDisconnected);
        setupStatusButton(status5True,status5TrueImageView,statusConnected);
        setupStatusButton(status5False,status5FalseImageView,statusDisconnected);
        setupStatusButton(status6True,status6TrueImageView,statusConnected);
        setupStatusButton(status6False,status6FalseImageView,statusDisconnected);
        //Отключение кнопки запуска сценария
        startButton.setDisable(true);
    }

    //Метод для выполнения задачи обновления строки даты-времени
    public void startUpdatingDateAndTime() {
        Runnable updateDateTimeTask = () -> {
            currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);

            // Обновляем текстовое поле в JavaFX Application Thread
            Platform.runLater(() -> dateTimeText.setText(formattedDateTime));
            //Platform.runLater(() -> System.out.println(1));
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

    /**
     * Функция для побуквенного вывода текста в Text
     *
     * @param textNode Текстовый узел, куда будет выведен текст
     * @param text     Текст, который нужно вывести
     * @param delay    Задержка между символами в миллисекундах
     */
    private void typeText(Text textNode, String text, int delay) {
        Timeline timeline = new Timeline();
        for (int i = 0; i <= text.length(); i++) {
            final int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay * i), event -> {
                textNode.setText(text.substring(0, index));
            }));
        }
        timeline.play();
    }
    //Метод для настройки кнопок статусов
    private void setupStatusButton(Button button, ImageView imageView, Image image) {
        imageView.setImage(image);
        // Делаем кнопку невидимой
        //button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setStyle("-fx-background-color: #D0ECF8; " + // Светло-голубой фон
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-text-fill: black; " +        // Цвет текста
                "-fx-font-size: 26px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }
    //Метод для отключения видимости кнопок статусов
    public void setStatucButtonDisabled(Button button, ImageView imageView) {
        button.setVisible(false);
        imageView.setVisible(false);
    }
    //Метод для включения видимости кнопок статусов
    public void setStatusButtonEnabled(Button button, ImageView imageView) {
        button.setVisible(true);
        imageView.setVisible(true);
    }
    //Метод, срабатывающий при нажатии галочки первого статуса
    public void statusOneTrue() {
        typeText(status2,"2. Количество настроенных инверторов", 100);
        setStatusButtonEnabled(status2True,status2TrueImageView);
        setStatusButtonEnabled(status2False,status2FalseImageView);
    }
    //Метод, срабатывающий при нажатии галочки второго статуса
    public void statusTwoTrue() {
        typeText(status3,"3. Включены ли инверторы", 100);
        setStatusButtonEnabled(status3True,status3TrueImageView);
        setStatusButtonEnabled(status3False,status3FalseImageView);
    }
    //Метод, срабатывающий при нажатии галочки третьего статуса
    public void statusThreeTrue() {
        typeText(status4,"4. Проверка синхронизации", 100);
        setStatusButtonEnabled(status4True,status4TrueImageView);
        setStatusButtonEnabled(status4False,status4FalseImageView);
    }
    //Метод, срабатывающий при нажатии галочки четвёртого статуса
    public void statusFourTrue() {
        typeText(status5,"5. Проверка галетного переключателя", 100);
        setStatusButtonEnabled(status5True,status5TrueImageView);
        setStatusButtonEnabled(status5False,status5FalseImageView);
    }
    //Метод, срабатывающий при нажатии галочки пятого статуса
    public void statusFiveTrue() {
        typeText(status6,"6. Проверка сопротивления", 100);
        setStatusButtonEnabled(status6True,status6TrueImageView);
        setStatusButtonEnabled(status6False,status6FalseImageView);
    }
    //Метод, срабатывающий при нажатии галочки пятого статуса
    public void statusSixTrue() {
        startButton.setDisable(false);
    }
    /**
     * Показывает всплывающее окно с заданным текстом и заголовком.
     *
     * @param alertType Тип окна (например, INFORMATION, WARNING, ERROR)
     * @param title     Заголовок окна
     * @param message   Текст сообщения
     */
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);          // Устанавливаем заголовок
        alert.setHeaderText(null);      // Убираем стандартный заголовок
        alert.setContentText(message);  // Устанавливаем текст сообщения

        alert.showAndWait(); // Показываем окно и ждем его закрытия
    }
    //метод для вызова всплывающего окна
    public void errorMethod1() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Отсутствуют параметры формы");
    }
    //метод для вызова всплывающего окна
    public void errorMethod2() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Настроено недостаточное количество инверторов");
    }
    //метод для вызова всплывающего окна
    public void errorMethod3() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Включено недостаточное количество инверторов");
    }
    //метод для вызова всплывающего окна
    public void errorMethod4() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Ошибка синхронизации");
    }
    //метод для вызова всплывающего окна
    public void errorMethod5() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Ошибка проверки галетного переключателя");
    }
    //метод для вызова всплывающего окна
    public void errorMethod6() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Ошибка проверки сопротивления");
    }
}