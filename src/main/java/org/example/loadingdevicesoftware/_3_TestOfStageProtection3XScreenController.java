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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _3_TestOfStageProtection3XScreenController {

    private boolean contactOneStatus = false;
    private boolean contactTwoStatus = false;

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;

    private Stage stageForStartScreen;
    private Scene sceneForStartScreen;
    private Parent rootForStartScreen;


    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button contactOneButton;
    @FXML
    private Button contactTwoButton;
    @FXML
    private ImageView inverterA1Status;
    @FXML
    private ImageView inverterA2Status;
    @FXML
    private ImageView inverterB1Status;
    @FXML
    private ImageView inverterB2Status;
    @FXML
    private ImageView inverterC1Status;
    @FXML
    private ImageView inverterC2Status;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private ImageView contactOneView;
    @FXML
    private ImageView contactTwoView;
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;

    //Объявление текстового поля для задания названия объекта
    @FXML
    private TextField objectNameTextField;
    @FXML
    private TextField namePerfomerTextField;
    //Объявление текстовых полей для задания токов фаз
    @FXML
    private TextField phaseA1TextField;
    @FXML
    private TextField phaseB1TextField;
    @FXML
    private TextField phaseC1TextField;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;
    //Объявление объекта для получения текущих значений даты и времени
    LocalDateTime currentDateTime;
    //Создание объекта для задания форматирования значений даты и времени
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
    //Объявление объекта для выполнения команды по заданному расписанию
    private ScheduledExecutorService scheduler;

    //Объекты картинок контактов
    Image normallyClosedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаНормЗамкКонт.png")).toExternalForm());
    Image normallyOpenedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаНормРазомкКонт.png")).toExternalForm());

    //Объекты фоновых картинок
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/2.проверкаВыключателя1Х3Х/switchCheckBackgroundl(3X).png")).toExternalForm());

    //Объекты картинок для кнопок и статусов инверторов
    Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаРамкаПуска.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());

    public void initialize() {
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");
        setupObjectNameField(namePerfomerTextField, "Введите ФИО исполнителя");
        setupObjectNameField(phaseA1TextField, "Ток А1, А");
        setupObjectNameField(phaseB1TextField, "Ток В1, А");
        setupObjectNameField(phaseC1TextField, "Ток С1, А");
        //Задание изображения для статуса инвертора
        inverterA1Status.setImage(statusConnected);
        inverterA2Status.setImage(statusConnected);
        inverterB1Status.setImage(statusConnected);
        inverterB2Status.setImage(statusConnected);
        inverterC1Status.setImage(statusConnected);
        inverterC2Status.setImage(statusConnected);
        //Инициализация планировщика задач
        scheduler = Executors.newSingleThreadScheduledExecutor();
        //Вызов метода для запуска задачи по обновлению строки даты и времени
        startUpdatingDateAndTime();
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);
        //Настройка кнопки "Меню"
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ");
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "ПУСК");
        //Настройка кнопок для задания положения контактов
        setupConnectionSchemesButtons(contactOneButton, contactOneView, 45, 30);
        setupConnectionSchemesButtons(contactTwoButton, contactTwoView, 45, 30);
    }

    //Метод для перехода в главное меню
    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("0.baseWindow.fxml"));
        rootForMainScreen = loader.load();

        _0_MainScreenController mainController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForMainScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneForMainScreen = new Scene(rootForMainScreen);
        stageForMainScreen.setScene(sceneForMainScreen);
        stageForMainScreen.show();
    }
    //Метод для перехода в старт
    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        //stopUpdatingDateAndTime();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("9.DifProtectionStart.fxml"));
        rootForStartScreen = loader.load();

        _7_DifProtectionSecondScreenController StartController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForStartScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneForStartScreen = new Scene(rootForStartScreen);
        stageForStartScreen.setScene(sceneForStartScreen);
        stageForStartScreen.show();
    }

    //Методы для настройки кнопок выбора контактов
    @FXML
    public void setPictureForContactOne() {
        contactOneView.setVisible(true);
        if(contactOneStatus) {
            contactOneView.setImage(normallyClosedContact);
            contactOneStatus = false;
        } else {
            contactOneView.setImage(normallyOpenedContact);
            contactOneStatus = true;
        }
    }
    @FXML
    public void setPictureForContactTwo() {
        contactTwoView.setVisible(true);
        if(contactTwoStatus) {
            contactTwoView.setImage(normallyClosedContact);
            contactTwoStatus = false;
        } else {
            contactTwoView.setImage(normallyOpenedContact);
            contactTwoStatus = true;
        }
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
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text) {
        imageView.setImage(image);
        imageView.setFitWidth(138);
        imageView.setFitHeight(64);
        button.setText(text);
        button.setStyle("-fx-background-color: #0F5D9C; " + // Синий фон
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-text-fill: white; " +        // Цвет текста
                "-fx-font-size: 26px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }

    //Метод для настройки кнопок соединения обмоток
    public void setupConnectionSchemesButtons(Button button, ImageView imageView, double width, double height) {
        button.setStyle("-fx-background-color: #CFECF8; " + // Голубой фон
                "-fx-border-color: #221E1F; " + // Тёмно-синяя граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;" +
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; "); // Цвет текста
        imageView.setVisible(false);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        //Настройка стиля текстового поля
        textField.setStyle("-fx-background-color: #CFECF8; " + // Голубой фон
                "-fx-border-color: #221E1F; " + // Чёрная граница
                "-fx-border-width: 3px; " +        // Ширина границы
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-border-radius: 15px; " +     // Закругление границы (совпадает с фоном)
                "-fx-text-fill: black; " +        // Цвет текста
                "-fx-font-size: 20px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
        //Задание текста по умолчанию
        textField.setPromptText(prompt);
        //Выравнивание по центру
        textField.setAlignment(javafx.geometry.Pos.CENTER);
        //Код для отключения мигания каретки при вводе
        textField.setOnAction(event -> {
            textField.getParent().requestFocus();
        });
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

    // Функция для кнопки перехода в 1 фазный режим
    //@FXML
    //public void goToTest1X(ActionEvent event) throws IOException {
    //Вызов метода для остановки выполнения задачи по обновлению даты и времени
    //stopUpdatingDateAndTime();

    //}
}