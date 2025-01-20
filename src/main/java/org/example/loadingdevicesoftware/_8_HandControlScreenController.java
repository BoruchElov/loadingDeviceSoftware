package org.example.loadingdevicesoftware;

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
import java.util.Objects;

public class _8_HandControlScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;


    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button stopwatchSetting;
    @FXML
    private Button shutdownConditions;

    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;
    @FXML
    private ImageView backgroundImageView;

    //Объявление текстового поля для задания названия объекта
    @FXML
    private TextField objectNameTextField;
    //Объявление текстового поля для задания ФИО работника
    @FXML
    private TextField userNameTextField;
    //Объявление текстовых полей для задания токов фаз/ фазового угла
    @FXML
    private TextField phaseA1TextField;
    @FXML
    private TextField angleA1TextField;
    @FXML
    private TextField phaseA2TextField;
    @FXML
    private TextField angleA2TextField;
    @FXML
    private TextField phaseB1TextField;
    @FXML
    private TextField angleB1TextField;
    @FXML
    private TextField phaseB2TextField;
    @FXML
    private TextField angleB2TextField;
    @FXML
    private TextField phaseC1TextField;
    @FXML
    private TextField angleC1TextField;
    @FXML
    private TextField phaseC2TextField;
    @FXML
    private TextField angleC2TextField;
    @FXML
    private TextField IP_A1;
    @FXML
    private TextField IP_A2;
    @FXML
    private TextField IP_B1;
    @FXML
    private TextField IP_B2;
    @FXML
    private TextField IP_C1;
    @FXML
    private TextField IP_C2;
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
    private ImageView contactOneView;
    @FXML
    private ImageView contactTwoView;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/8.ручнойРежим/ручнойРежим1Форма(безКнопок).png")).toExternalForm());

    //Объекты картинок для кнопок и статусов инверторов
    Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаРамкаПуска.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());

    @FXML
    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");
        setupObjectNameField(userNameTextField, "Введите ФИО исполнителя");


        setupObjectNameField(phaseA1TextField, "         А");
        setupObjectNameField(angleA1TextField, "    °");
        setupObjectNameField(phaseA2TextField, "         А");
        setupObjectNameField(angleA2TextField, "    °");
        setupObjectNameField(phaseB1TextField, "         А");
        setupObjectNameField(angleB1TextField, "    °");
        setupObjectNameField(phaseB2TextField, "         А");
        setupObjectNameField(angleB2TextField, "    °");
        setupObjectNameField(phaseC1TextField, "         А");
        setupObjectNameField(angleC1TextField, "    °");
        setupObjectNameField(phaseC2TextField, "         А");
        setupObjectNameField(angleC2TextField, "    °");
        setupObjectNameField(IP_A1, "");
        setupObjectNameField(IP_A2, "");
        setupObjectNameField(IP_B1, "");
        setupObjectNameField(IP_B2, "");
        setupObjectNameField(IP_C1, "");
        setupObjectNameField(IP_C2, "");
        //Задание изображений для статусов инверторов
        inverterA1Status.setImage(statusConnected);
        inverterA2Status.setImage(statusConnected);
        inverterB1Status.setImage(statusConnected);
        inverterB2Status.setImage(statusConnected);
        inverterC1Status.setImage(statusConnected);
        inverterC2Status.setImage(statusConnected);

        //настройка кнопок в правой части
        setupRightSideButtons(stopwatchSetting);
        setupRightSideButtons(shutdownConditions);

        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);
        //Настройка кнопки "Меню"
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ");
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "ПУСК");
    }

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "7.DifProtectionStart.fxml");
    }

    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.BLUE, ApplicationConstants.colours.BLUE,
                0, 17, 0, ApplicationConstants.colours.WHITE, 26, 0,
                imageView, image, button, 138, 64, true, text);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        interfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 20, 0, textField,
                prompt);
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

    //метод для настройки кнопок в правой части окна сценария
    public void setupRightSideButtons(Button button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
    }
}

