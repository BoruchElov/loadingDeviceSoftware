package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class _4_EventLoggerScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    //кнопки
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button event1;
    @FXML
    private Button event2;
    @FXML
    private Button event3;
    @FXML
    private Button event4;
    @FXML
    private Button event5;
    @FXML
    private Button event6;

    // картинка фона
    @FXML
    private ImageView backgroundImageView;

    //инвертора
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
    //картинка кнопок старт/меню
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/9.отладка/отладка(безКнопок).png")).toExternalForm());


    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        //Задание изображений для статусов инверторов
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);

        //Настройка кнопки "Меню"
        setupTextBottoms(event1, "СОБЫТИЕ 1");
        //Настройка кнопки "Пуск"
        setupTextBottoms(event2, "СОБЫТИЕ 2");
        //Настройка кнопки "Меню"
        setupTextBottoms(event3, "СОБЫТИЕ 3");
        //Настройка кнопки "Пуск"
        setupTextBottoms(event4, "СОБЫТИЕ 4");
        //Настройка кнопки "Меню"
        setupTextBottoms(event5, "СОБЫТИЕ 5");
        //Настройка кнопки "Пуск"
        setupTextBottoms(event6, "СОБЫТИЕ 6");

        interfaceElementsSettings.getBlackMenuButton(toMenuButton,toMenuButtonImageView);
        interfaceElementsSettings.getBlackSaveButton(startButton,startButtonImageView);
    }

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
    }

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupTextBottoms(Button button, String text) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
        button.setText(text);
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }


}