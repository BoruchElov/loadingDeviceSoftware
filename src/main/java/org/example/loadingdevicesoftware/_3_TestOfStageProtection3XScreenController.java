package org.example.loadingdevicesoftware;

import javafx.application.Platform;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _3_TestOfStageProtection3XScreenController {

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

    //Объекты картинок контактов
    Image normallyClosedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаНормЗамкКонт.png")).toExternalForm());
    Image normallyOpenedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаНормРазомкКонт.png")).toExternalForm());

    //Объекты фоновых картинок
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/2.проверкаВыключателя1Х3Х/switchCheckBackgroundl(3X).png")).toExternalForm());

    //Объекты картинок для кнопок и статусов инверторов
    Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаРамкаПуска.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());

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
        inverterA1Status.setImage(statusConnected);
        inverterA2Status.setImage(statusConnected);
        inverterB1Status.setImage(statusConnected);
        inverterB2Status.setImage(statusConnected);
        inverterC1Status.setImage(statusConnected);
        inverterC2Status.setImage(statusConnected);
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);
        //Настройка кнопки "Меню"
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ");
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "ПУСК");
        //Настройка кнопок для задания положения контактов
        setupConnectionSchemesButtons(contactOneButton, contactOneView, 45, 30);
        setupConnectionSchemesButtons(contactTwoButton, contactTwoView, 45, 30);
    }

    //Метод для перехода в главное меню
    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }
    //Метод для перехода в старт
    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "7.DifProtectionStart.fxml");
    }

    //Методы для настройки кнопок выбора контактов
    @FXML
    public void setPictureForContactOne() {
        contactOneView.setVisible(true);
        if(contactOneStatus) {
            contactOneView.setImage(normallyClosedContact);
            contactOneStatus = false;
        } else {
            contactOneView.setImage(normallyOpenedContact);
            contactOneStatus = true;
        }
    }
    @FXML
    public void setPictureForContactTwo() {
        contactTwoView.setVisible(true);
        if(contactTwoStatus) {
            contactTwoView.setImage(normallyClosedContact);
            contactTwoStatus = false;
        } else {
            contactTwoView.setImage(normallyOpenedContact);
            contactTwoStatus = true;
        }
    }

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.BLUE, ApplicationConstants.colours.BLUE,
                0, 17, 0, ApplicationConstants.colours.WHITE, 26, 0,
                imageView, image, button, 138, 64, true, text);
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

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

    // Функция для кнопки перехода в 1 фазный режим
    //@FXML
    //public void goToTest1X(ActionEvent event) throws IOException {
    //Вызов метода для остановки выполнения задачи по обновлению даты и времени
    //stopUpdatingDateAndTime();

    //}
}