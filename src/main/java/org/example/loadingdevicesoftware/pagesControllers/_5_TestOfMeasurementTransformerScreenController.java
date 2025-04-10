package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.Objects;

public class _5_TestOfMeasurementTransformerScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    //кнопки
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;

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
    private TextField currentMeasure;
    @FXML
    private TextField voltageMeasure;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/5.проверкаИзмерительногоТрансформатора/ПроверкаИзмерительногоТранса1форма(безКнопок).png")).toExternalForm());

    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");
        setupObjectNameField(userNameTextField, "Введите ФИО исполнителя");

        setupObjectNameField(phaseA1TextField, "         А");
        setupObjectNameField(angleA1TextField, "    °");
        setupObjectNameField(voltageMeasure, "0");
        setupObjectNameField(currentMeasure, "0");

        //Задание изображений для статусов инверторов
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);

        interfaceElementsSettings.getWhiteMenuButton(toMenuButton,toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        interfaceElementsSettings.getWhiteStartButton(startButton,startButtonImageView, InterfaceElementsSettings.Background.BLUE);
    }
    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }
    @FXML
    public void goToStartScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("5.TestOfMeasurementTransformer.fxml");
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

