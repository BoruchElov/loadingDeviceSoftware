package org.example.loadingdevicesoftware;

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

    //Объекты картинок для кнопок и статусов инверторов
    Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/Кнопка(черная).png")).toExternalForm());

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
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ");
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "СОХРАНИТЬ");

        //Настройка кнопки "Меню"
        setupBottomButtons1(event1, toMenuButtonImageView, lowButtoncImage, "СОБЫТИЕ 1");
        //Настройка кнопки "Пуск"
        setupBottomButtons1(event2, startButtonImageView, lowButtoncImage, "СОБЫТИЕ 2");
        //Настройка кнопки "Меню"
        setupBottomButtons1(event3, toMenuButtonImageView, lowButtoncImage, "СОБЫТИЕ 3");
        //Настройка кнопки "Пуск"
        setupBottomButtons1(event4, startButtonImageView, lowButtoncImage, "СОБЫТИЕ 4");
        //Настройка кнопки "Меню"
        setupBottomButtons1(event5, toMenuButtonImageView, lowButtoncImage, "СОБЫТИЕ 5");
        //Настройка кнопки "Пуск"
        setupBottomButtons1(event6, startButtonImageView, lowButtoncImage, "СОБЫТИЕ 6");
    }

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "7.DifProtectionStart.fxml");
    }

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.LIGHT_BLUE,
                0, 17, 0, ApplicationConstants.colours.BLACK, 26, 0,
                imageView, image, button, 138, 64, true, text);
    }

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons1(Button button, ImageView imageView, Image image, String text) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 13, ApplicationConstants.colours.BLACK, 26, 0,
                imageView, image, button, 138, 64, true, text);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        interfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3,17,15, ApplicationConstants.colours.BLACK,20,0,textField,
                prompt);
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }


}