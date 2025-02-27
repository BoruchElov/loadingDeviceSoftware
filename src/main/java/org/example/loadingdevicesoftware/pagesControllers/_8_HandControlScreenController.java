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

public class _8_HandControlScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button stopwatchSetting;
    @FXML
    private Button shutdownConditions;
    @FXML
    private Button symmetricalElements;

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

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/8.ручнойРежим/ручнойРежим1Форма(безКнопок).png")).toExternalForm());


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
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);

        //настройка кнопок в правой части
        setupRightSideButtons(stopwatchSetting);
        setupRightSideButtons(shutdownConditions);
        setupRightSideButtons(symmetricalElements);

        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);
        interfaceElementsSettings.getWhiteMenuButton(toMenuButton,toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        interfaceElementsSettings.getWhiteStartButton(startButton,startButtonImageView, InterfaceElementsSettings.Background.BLUE);
    }

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("8.HandControl.fxml");
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

