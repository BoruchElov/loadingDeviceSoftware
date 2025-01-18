package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class MainScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

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
        //Привязка текстового поля к потоку обновления даты и времени
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());

        //Задание изображения для статусов инверторов
        inverterA1Status.setImage(statusConnected);
        inverterA2Status.setImage(statusConnected);
        inverterB1Status.setImage(statusConnected);
        inverterB2Status.setImage(statusConnected);
        inverterC1Status.setImage(statusConnected);
        inverterC2Status.setImage(statusConnected);

        //Установка картинки на фон
        backgroungImage.setImage(backImage);
        //__________________________________________________________________________//

        //Настройка кнопки "Настройка"
        setupMenuButtons(setingsButtonBackground, settingsButtonImage, settingsButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка автоматического выключателя"
        setupMenuButtons(testOfSwitcherButtonBackground, testOfSwitcherButtonImage, testOfSwitcherButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка релейной защиты"
        setupMenuButtons(testOfStageProtectionButtonBackground, testOfStageProtectionButtonImage,
                testOfStageProtectionButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Журнал событий"
        setupMenuButtons(eventLoggerButtonBackground, eventLoggerButtonImage, eventLoggerButton);
        //__________________________________________________________________________//

        //Настройка кнопки "COMTRADE"
        setupMenuButtons(comTradeButtonBackground, comTradeButtonImage, comTradeButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка дифференциальной защиты"
        setupMenuButtons(testOfDifProtectionButtonBackground, difProtectionButtonImage,
                testOfDifProtectionButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка измерительного трансформатора"
        setupMenuButtons(testOfMeasurementTransformerButtonBackground, testOfMeasurementTransformerButtonImage,
                testOfMeasurementTransformerButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Ручной ввод"
        setupMenuButtons(testOfHandControlButtonBackground, handControlButtonImage, handControlButton);
        //__________________________________________________________________________//

        //Настройка кнопки "Отладка"
        setupMenuButtons(deBuggerButtonBackground, deBuggerButtonImage, deBuggerButton);
        //__________________________________________________________________________//

    }

    /**
     * Метод для настройки кнопок в главном меню. Представляет собой необходимую прослойку между классом-контроллером
     * и классом <code>InterfaceElementsSettings</code> , содержащим методы для настройки элементов интерфейса.
     * @param backgroundImageView <code>ImageView</code> для картинки на кнопке
     * @param backgroundImage <code>Image</code> объект картинки на кнопке
     * @param button кнопка
     */
    public void setupMenuButtons(ImageView backgroundImageView, Image backgroundImage, Button button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                0, 15, 15, ApplicationConstants.colours.WHITE, 0,
                backgroundImageView, backgroundImage, button, 223, 200, true);
    }

    /**
     * Метод для перехода на страницу настроек.
     * @param event
     * @throws IOException
     */
    @FXML
    public void goToSettings (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "settingsWindow.fxml");
    }

    /**
     * Метод для перехода на страницу сценария Диф.защиты.
     * @param event
     * @throws IOException
     */
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
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "DifProtection.fxml");
        stopUpdatingDateAndTime();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("7.DifProtection.fxml"));
        rootForDifProtection = loader.load();

        _7_DifProtectionScreenController DifProtectionController = loader.getController();

        stageForDifProtection = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForDifProtection = new Scene(rootForDifProtection, 1280, 800);
        stageForDifProtection.setScene(sceneForDifProtection);
        stageForDifProtection.show();
    }

    /**
     * Тестовый метод для проверки работоспособности кнопки. Печатает в консоль текст "Кнопка работает".
     * Позже удалю.
     */
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
}
