package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.DateTimeUpdater;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsSettings;

import java.io.IOException;
import java.util.Objects;

public class _0_MainScreenController {

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
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);

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
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "1.settingsWindow.fxml");
    }

    //Метод для перехода на страницу сценария теста выключателя
     public void goToTestOfSwitcher (ActionEvent event) throws IOException {
         InterfaceElementsLogic.switchScene((Node) event.getSource(), "2.TestOfSwitcher3X.fxml");
     }

    //Метод для перехода на страницу сценария теста РЗА
    public void goToTestOfStageProtection (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "3.TestOfStageProtection3X.fxml");
    }

    //Метод для перехода на страницу сценария Журнал событий
    public void goToEventLogger (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "4.EventLogger.fxml");
    }

    //Метод для перехода на страницу сценария Проверка измерительного трансформатора
    public void goToTestOfMeasurementTransformer (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "5.TestOfMeasurementTransformer.fxml");
    }

    //Метод для перехода на страницу сценария Comtrade
    public void goToComTrade (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "6.ComTrade.fxml");
    }

    //Метод для перехода на страницу сценария диф.защиты
    public void goToDifProtection (ActionEvent event) throws IOException {
        //установка флага
        InterfaceElementsLogic.setFromCheckingStartConditions(false);

        InterfaceElementsLogic.switchScene((Node) event.getSource(), "7.DifProtection.fxml");
    }

    //Метод для перехода на страницу сценария Ручное управление
    public void goToHandControl (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "8.HandControl.fxml");
    }

    //Метод для перехода на страницу сценария Отладка
    public void goToDeBugger (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "9.DeBugger.fxml");
    }
    /**
     * Тестовый метод для проверки работоспособности кнопки. Печатает в консоль текст "Кнопка работает".
     * Позже удалю.
     */
    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }
}
