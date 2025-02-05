package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class _2_TestOfSwitcher3XScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    private boolean contactOneStatus = false;
    private boolean contactTwoStatus = false;

    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button contactOneButton;
    @FXML
    private Button contactTwoButton;
    @FXML
    private ToggleButton testOfSwitcher1X;
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
    private ImageView backgroundImageView;
    @FXML
    private ImageView contactOneView;
    @FXML
    private ImageView contactTwoView;
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;

    //Объявление текстового поля для задания названия объекта
    @FXML
    private TextField objectNameTextField;
    @FXML
    private TextField namePerfomerTextField;
    //Объявление текстовых полей для задания токов фаз
    @FXML
    private TextField phaseA1TextField;
    @FXML
    private TextField phaseB1TextField;
    @FXML
    private TextField phaseC1TextField;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объекты фоновых картинок
    Image backImageOutThree = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/2.проверкаВыключателя1Х3Х/switchCheckBackgroundl(3X).png")).toExternalForm());
    //Объекты фоновых картинок
    Image backImageOutOne = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/2.проверкаВыключателя1Х3Х/switchCheckBackgroundl(1X).png")).toExternalForm());

    public void initialize() {
        //Привязка текстового поля к потоку обновления даты и времени
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");
        setupObjectNameField(namePerfomerTextField, "Введите ФИО исполнителя");
        setupObjectNameField(phaseA1TextField, "Ток А1, А");
        setupObjectNameField(phaseB1TextField, "Ток В1, А");
        setupObjectNameField(phaseC1TextField, "Ток С1, А");
        //Задание изображения для статуса инвертора
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutThree);
        //Настройка кнопки смены конфигурации выключателя
        setupRightSideButton(testOfSwitcher1X);
        testOfSwitcher1X.setText("3 фазы");
        //Настройка кнопок для задания положения контактов
        setupConnectionSchemesButtons(contactOneButton, contactOneView, 45, 30);
        setupConnectionSchemesButtons(contactTwoButton, contactTwoView, 45, 30);

        interfaceElementsSettings.getWhiteMenuButton(toMenuButton,toMenuButtonImageView);
        interfaceElementsSettings.getWhiteStartButton(startButton,startButtonImageView);
    }

    //Метод для перехода в главное меню
    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }
    //Метод для перехода в старт
    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("2.TestOfSwitcher3X.fxml");
    }

    //Методы для настройки кнопок выбора контактов
    @FXML
    public void setPictureForContactOne() {
        contactOneView.setVisible(true);
        if(contactOneStatus) {
            contactOneView.setImage(ApplicationConstants.NORMALLY_CLOSED_CONTACT);
            contactOneStatus = false;
        } else {
            contactOneView.setImage(ApplicationConstants.NORMALLY_OPENED_CONTACT);
            contactOneStatus = true;
        }
    }
    @FXML
    public void setPictureForContactTwo() {
        contactTwoView.setVisible(true);
        if(contactTwoStatus) {
            contactTwoView.setImage(ApplicationConstants.NORMALLY_CLOSED_CONTACT);
            contactTwoStatus = false;
        } else {
            contactTwoView.setImage(ApplicationConstants.NORMALLY_OPENED_CONTACT);
            contactTwoStatus = true;
        }
    }

    @FXML
    public void changeSwitcherConfiguration() {
        if (testOfSwitcher1X.isSelected()) {
            testOfSwitcher1X.setText("1 фаза");
            backgroundImageView.setImage(backImageOutOne);
            phaseA1TextField.setVisible(false);
            phaseC1TextField.setVisible(false);
            setupObjectNameField(phaseB1TextField, "Ток, А");
        } else {
            testOfSwitcher1X.setText("3 фазы");
            backgroundImageView.setImage(backImageOutThree);
            phaseA1TextField.setVisible(true);
            phaseC1TextField.setVisible(true);
            setupObjectNameField(phaseB1TextField, "Ток В1, А");
        }
    }

    //Метод для настройки кнопок соединения обмоток
    public void setupConnectionSchemesButtons(Button button, ImageView imageView, int width, int height) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.WHITE, 0,
                imageView, null, button, width, height, false);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        interfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3,17,15, ApplicationConstants.colours.BLACK,20,0,textField,
                prompt);
    }
    //Метод для настройки кнопки смены конфигурации
    private void setupRightSideButton (ButtonBase button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

}