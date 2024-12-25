package org.example.loadingdevicesoftware;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class MainScreenController {
    //Объявление объекта для получения текущих значений даты и времени
    LocalDateTime currentDateTime;
    //Создание объекта для задания форматирования значений даты и времени
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
    //Объявление объекта для выполнения команды по заданному расписанию
    private ScheduledExecutorService scheduler;

    private Stage stageForSettings;
    private Scene sceneForSettings;
    private Parent rootForSettings;

    private Stage stageForDifProtection;
    private Scene sceneForDifProtection;
    private Parent rootForDifProtection;

    @FXML
    private ImageView backgroungImage;
    @FXML
    private ImageView setingsButtonBackground;
    @FXML
    private ImageView testOfHandControlButtonBackground;
    @FXML
    private ImageView testOfSwitcherButtonBackground;
    @FXML
    private ImageView testOfDifProtectionButtonBackground;
    @FXML
    private ImageView comTradeButtonBackground;
    @FXML
    private ImageView testOfStageProtectionButtonBackground;
    @FXML
    private ImageView eventLoggerButtonBackground;
    @FXML
    private ImageView testOfMeasurementTransformerButtonBackground;
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


    Image backImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/Главная страница(без кнопок).png")).toExternalForm());
    Image settingsButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/1.png")).toExternalForm());
    Image testOfSwitcherButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/2.png")).toExternalForm());
    Image testOfStageProtectionButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/3.png")).toExternalForm());
    Image eventLoggerButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/4.png")).toExternalForm());
    Image comTradeButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/5.png")).toExternalForm());
    Image difProtectionButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/6.png")).toExternalForm());
    Image testOfMeasurementTransformerButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/7.png")).toExternalForm());
    Image handControlButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/8.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());


    @FXML
    private Button settingsButton;
    @FXML
    private Button handControlButton;
    @FXML
    private Button testOfSwitcherButton;
    @FXML
    private Button testOfDifProtectionButton;
    @FXML
    private Button comTradeButton;
    @FXML
    private Button testOfStageProtectionButton;
    @FXML
    private Button eventLoggerButton;
    @FXML
    private Button testOfMeasurementTransformerButton;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;


    @FXML
    public void initialize() {
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
        backgroungImage.setImage(backImage);
        //__________________________________________________________________________//

        //Настройка кнопки "Настройка"
        //Загрузка картинки, установка её ширины и высоты
        setupMenuButtons(setingsButtonBackground, settingsButtonImage, settingsButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка автоматического выключателя"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(testOfSwitcherButtonBackground, testOfSwitcherButtonImage, testOfSwitcherButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка ступенчатой защиты"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(testOfStageProtectionButtonBackground, testOfStageProtectionButtonImage,
                testOfStageProtectionButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Журнал событий"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(eventLoggerButtonBackground, eventLoggerButtonImage, eventLoggerButton);
        //__________________________________________________________________________//

        //Настройка кнопки "COMTRADE"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(comTradeButtonBackground, comTradeButtonImage, comTradeButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка дифференциальной защиты"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(testOfDifProtectionButtonBackground, difProtectionButtonImage,
                testOfDifProtectionButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка измерительного трансформатора"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(testOfMeasurementTransformerButtonBackground, testOfMeasurementTransformerButtonImage,
                testOfMeasurementTransformerButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Ручной ввод"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(testOfHandControlButtonBackground, handControlButtonImage, handControlButton);
        //__________________________________________________________________________//
    }

    public void setupMenuButtons(ImageView backgroundImageView, Image backgroundImage, Button button) {
        backgroundImageView.setImage(backgroundImage);
        backgroundImageView.setFitWidth(223);
        backgroundImageView.setFitHeight(200);
        button.setStyle("-fx-background-color: #D1EDFA; " + // Голубой фон
                "-fx-border-color: #404041; " + // Чёрная граница
                "-fx-border-width: 0px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;" +   // Цвет текста
                "-fx-padding: 0;"); //Величина отступа
    }

    //Метод для перехода на страницу настроек
    @FXML
    public void goToSettings (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsWindow.fxml"));
        rootForSettings = loader.load();

        SettingsScreenController settingsController = loader.getController();

        stageForSettings = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForSettings = new Scene(rootForSettings, 1280, 800);
        stageForSettings.setScene(sceneForSettings);
        stageForSettings.show();
    }

    //Метод для перехода на страницу сценария диф.защиты
    public void goToDifProtection (ActionEvent event) throws IOException {
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

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
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
}
