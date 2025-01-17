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

public class _0_MainScreenController {
    //Объявление объекта для получения текущих значений даты и времени
    LocalDateTime currentDateTime;
    //Создание объекта для задания форматирования значений даты и времени
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
    //Объявление объекта для выполнения команды по заданному расписанию
    private ScheduledExecutorService scheduler;

    //объявление методов для перехода в настройки
    private Stage stageForSettings;
    private Scene sceneForSettings;
    private Parent rootForSettings;

    //объявление методов для перехода в тестирование выключателя
    private Stage stageForTestOfSwitcher;
    private Scene sceneForTestOfSwitcher;
    private Parent rootForTestOfSwitcher;

    //объявление методов для перехода в тестирование РЗА
    private Stage stageForTestOfStageProtection;
    private Scene sceneForTestOfStageProtection;
    private Parent rootForTestOfStageProtection;

    //объявление методов для перехода в Журнал событий
    private Stage stageForEventLogger;
    private Scene sceneForEventLogger;
    private Parent rootForEventLogger;

    //объявление методов для перехода в тестирование Измерительного трансформатора
    private Stage stageForTestOfMeasurementTransformer;
    private Scene sceneForTestOfMeasurementTransformer;
    private Parent rootForTestOfMeasurementTransformer;

    //объявление методов для перехода в тестирование комтрейда
    private Stage stageForComTrade;
    private Scene sceneForComTrade;
    private Parent rootForComTrade;

    //объявление методов для перехода в тестирование Диф/Защиты
    private Stage stageForDifProtection;
    private Scene sceneForDifProtection;
    private Parent rootForDifProtection;

    //объявление методов для перехода в тестирование ручного режима
    private Stage stageForHandControl;
    private Scene sceneForHandControl;
    private Parent rootForHandControl;

    //объявление методов для перехода в отладка
    private Stage stageForDeBugger;
    private Scene sceneForDeBugger;
    private Parent rootForDeBugger;


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
    private ImageView deBuggerButtonBackground;
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
            getResource("/screen/0.main/Главная страница(без кнопок).png")).toExternalForm());
    Image settingsButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/1.png")).toExternalForm());
    Image testOfSwitcherButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/2.png")).toExternalForm());
    Image testOfStageProtectionButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/3.png")).toExternalForm());
    Image eventLoggerButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/4.png")).toExternalForm());
    Image testOfMeasurementTransformerButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/5.png")).toExternalForm());
    Image comTradeButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/6.png")).toExternalForm());
    Image difProtectionButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/7.png")).toExternalForm());
    Image handControlButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/8.png")).toExternalForm());
    Image deBuggerButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/0.main/иконки/9.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());




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
    @FXML
    private Button deBuggerButton;

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

        //Настройка кнопки "Проверка релейной защиты"
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

        //Настройка кнопки "Отладка"
        //Загрузка картинки, настройка её ширины и высоты
        setupMenuButtons(deBuggerButtonBackground, deBuggerButtonImage, deBuggerButton);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("1.settingsWindow.fxml"));
        rootForSettings = loader.load();

        _1_SettingsScreenController settingsController = loader.getController();

        stageForSettings = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForSettings = new Scene(rootForSettings, 1280, 800);
        stageForSettings.setScene(sceneForSettings);
        stageForSettings.show();
    }

    //Метод для перехода на страницу сценария теста выключателя
     public void goToTestOfSwitcher (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("2.TestOfSwitcher3X.fxml"));
        rootForTestOfSwitcher = loader.load();

        _2_TestOfSwitcher3XScreenController a2TestOfSwitcher1XScreenController = loader.getController();

        stageForTestOfSwitcher = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForTestOfSwitcher = new Scene(rootForTestOfSwitcher, 1280, 800);
        stageForTestOfSwitcher.setScene(sceneForTestOfSwitcher);
        stageForTestOfSwitcher.show();
    }

    //Метод для перехода на страницу сценария теста РЗА
    public void goToTestOfStageProtection (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("3.TestOfStageProtection3X.fxml"));
        rootForTestOfStageProtection = loader.load();

        _3_TestOfStageProtection3XScreenController test3TestOfStageProtection1XScreenController = loader.getController();

        stageForTestOfStageProtection = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForTestOfStageProtection = new Scene(rootForTestOfStageProtection, 1280, 800);
        stageForTestOfStageProtection.setScene(sceneForTestOfStageProtection);
        stageForTestOfStageProtection.show();
    }

    //Метод для перехода на страницу сценария Журнал событий
    public void goToEventLogger (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("4.EventLogger.fxml"));
        rootForEventLogger = loader.load();

        _4_EventLoggerScreenController a4EventLoggerScreenController = loader.getController();

        stageForEventLogger = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForEventLogger = new Scene(rootForEventLogger, 1280, 800);
        stageForEventLogger.setScene(sceneForEventLogger);
        stageForEventLogger.show();
    }

    //Метод для перехода на страницу сценария Проверка измерительного трансформатора
    public void goToTestOfMeasurementTransformer (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("5.TestOfMeasurementTransformer.fxml"));
        rootForTestOfMeasurementTransformer = loader.load();

        _5_TestOfMeasurementTransformerScreenController a5TestOfMeasurementTransformerScreenController = loader.getController();

        stageForTestOfMeasurementTransformer = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForTestOfMeasurementTransformer = new Scene(rootForTestOfMeasurementTransformer, 1280, 800);
        stageForTestOfMeasurementTransformer.setScene(sceneForTestOfMeasurementTransformer);
        stageForTestOfMeasurementTransformer.show();
    }

    //Метод для перехода на страницу сценария Журнал событий
    public void goToComTrade (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("6.ComTrade.fxml"));
        rootForComTrade = loader.load();

        _6_ComTradeScreenController comTraderScreenController = loader.getController();

        stageForComTrade = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForComTrade = new Scene(rootForComTrade, 1280, 800);
        stageForComTrade.setScene(sceneForComTrade);
        stageForComTrade.show();
    }

    //Метод для перехода на страницу сценария диф.защиты
    public void goToDifProtection (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("7.DifProtection.fxml"));
        rootForDifProtection = loader.load();

        _7_DifProtectionScreenController DifProtectionController = loader.getController();

        stageForDifProtection = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForDifProtection = new Scene(rootForDifProtection, 1280, 800);
        stageForDifProtection.setScene(sceneForDifProtection);
        stageForDifProtection.show();
    }

    //Метод для перехода на страницу сценария Журнал событий
    public void goToHandControl (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("8.HandControl.fxml"));
        rootForHandControl = loader.load();

        _8_HandControlScreenController a8HandControlScreenController = loader.getController();

        stageForHandControl = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForHandControl = new Scene(rootForHandControl, 1280, 800);
        stageForHandControl.setScene(sceneForHandControl);
        stageForHandControl.show();
    }

    //Метод для перехода на страницу сценария Журнал событий
    public void goToDeBugger (ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("9.DeBugger.fxml"));
        rootForDeBugger = loader.load();

        _9_DeBuggerScreenController a9DeBuggerScreenController = loader.getController();

        stageForDeBugger = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForDeBugger = new Scene(rootForDeBugger, 1280, 800);
        stageForDeBugger.setScene(sceneForDeBugger);
        stageForDeBugger.show();
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
