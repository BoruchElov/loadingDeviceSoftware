package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.DateTimeUpdater;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsSettings;

import java.io.IOException;
import java.util.Objects;

public class _9_DeBuggerScreenController {

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

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объявление текстовых полей
    @FXML
    private TextField alphaTextField;
    @FXML
    private TextField beta1TextField;
    @FXML
    private TextField beta2TextField;
    @FXML
    private TextField beta3TextField;
    @FXML
    private TextField beta4TextField;
    @FXML
    private TextField gamma1TextField;
    @FXML
    private TextField gamma2TextField;
    @FXML
    private TextField gamma3TextField;
    @FXML
    private TextField gamma4TextField;
    @FXML
    private TextField deltaTextField;

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

        interfaceElementsSettings.getBlackMenuButton(toMenuButton,toMenuButtonImageView, InterfaceElementsSettings.Background.LIGHT_BLUE);
        interfaceElementsSettings.getBlackSaveButton(startButton,startButtonImageView, InterfaceElementsSettings.Background.LIGHT_BLUE);
        //настройка текстовых полей для переменных
        setupObjectNameField(alphaTextField, "0");
        setupObjectNameField(beta1TextField, "0");
        setupObjectNameField(beta2TextField, "0");
        setupObjectNameField(beta3TextField, "0");
        setupObjectNameField(beta4TextField, "0");
        setupObjectNameField(gamma1TextField, "0");
        setupObjectNameField(gamma2TextField, "0");
        setupObjectNameField(gamma3TextField, "0");
        setupObjectNameField(gamma4TextField, "0");
        setupObjectNameField(deltaTextField, "0");

    }

    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }
    @FXML
    public void goToStartScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
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