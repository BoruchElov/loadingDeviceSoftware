package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.Objects;


public class _100_checkingStartConditionsScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();


    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;
    //Объявление текстовых полей для демонстрации статуса запуска
    @FXML
    private Text status1;
    @FXML
    private Text status2;
    @FXML
    private Text status3;
    @FXML
    private Text status4;
    @FXML
    private Text status5;
    @FXML
    private Text status6;

    //Объявление кнопок для перехода в меню, пуска и отмены
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button cancelButton;

    //Объявление кнопок для задания статуса выполнения операций при пуске
    @FXML
    private Button status1True;
    @FXML
    private Button status1False;
    @FXML
    private Button status2True;
    @FXML
    private Button status2False;
    @FXML
    private Button status3True;
    @FXML
    private Button status3False;
    @FXML
    private Button status4True;
    @FXML
    private Button status4False;
    @FXML
    private Button status5True;
    @FXML
    private Button status5False;
    @FXML
    private Button status6True;
    @FXML
    private Button status6False;

    //Объявление областей изображений для кнопок статусов
    @FXML
    private ImageView status1TrueImageView;
    @FXML
    private ImageView status1FalseImageView;
    @FXML
    private ImageView status2TrueImageView;
    @FXML
    private ImageView status2FalseImageView;
    @FXML
    private ImageView status3TrueImageView;
    @FXML
    private ImageView status3FalseImageView;
    @FXML
    private ImageView status4TrueImageView;
    @FXML
    private ImageView status4FalseImageView;
    @FXML
    private ImageView status5TrueImageView;
    @FXML
    private ImageView status5FalseImageView;
    @FXML
    private ImageView status6TrueImageView;
    @FXML
    private ImageView status6FalseImageView;
    //Объявление областей изображений для кнопок
    @FXML
    private ImageView toMenuButtonImageView;
    @FXML
    private ImageView startButtonImageView;
    @FXML
    private ImageView cancelButtonImageView;
    //Объявление области изображения для фона
    @FXML
    private ImageView backgroundImageView;


    private Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/иконкаЗеленыйКруг.png")).toExternalForm());
    private Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/иконкаКрасныйКруг.png")).toExternalForm());
    //Объект картинки для фона
    private Image background = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_2форма(без кнопок).png")).toExternalForm());

    /**
     * Показывает всплывающее окно с заданным текстом и заголовком.
     *
     * @param alertType Тип окна (например, INFORMATION, WARNING, ERROR)
     * @param title     Заголовок окна
     * @param message   Текст сообщения
     */
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);          // Устанавливаем заголовок
        alert.setHeaderText(null);      // Убираем стандартный заголовок
        alert.setContentText(message);  // Устанавливаем текст сообщения

        alert.showAndWait(); // Показываем окно и ждем его закрытия
    }

    @FXML
    public void initialize() {
        //Привязка текстового поля к потоку обновления даты и времени
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());

        //Установка изображения на фон
        backgroundImageView.setImage(background);
        //Печать текста первой проверки
        typeText(status1, "1. Проверка параметров формы");
        //Скрытие кнопок остальных проверок
        setStatucButtonDisabled(status2True, status2TrueImageView);
        setStatucButtonDisabled(status2False, status2FalseImageView);
        setStatucButtonDisabled(status3True, status3TrueImageView);
        setStatucButtonDisabled(status3False, status3FalseImageView);
        setStatucButtonDisabled(status4True, status4TrueImageView);
        setStatucButtonDisabled(status4False, status4FalseImageView);
        setStatucButtonDisabled(status5True, status5TrueImageView);
        setStatucButtonDisabled(status5False, status5FalseImageView);
        setStatucButtonDisabled(status6True, status6TrueImageView);
        setStatucButtonDisabled(status6False, status6FalseImageView);
        //Настройка всех кнопок статусов проверок
        setupStatusButton(status1True, status1TrueImageView, statusConnected);
        setupStatusButton(status1False, status1FalseImageView, statusDisconnected);
        setupStatusButton(status2True, status2TrueImageView, statusConnected);
        setupStatusButton(status2False, status2FalseImageView, statusDisconnected);
        setupStatusButton(status3True, status3TrueImageView, statusConnected);
        setupStatusButton(status3False, status3FalseImageView, statusDisconnected);
        setupStatusButton(status4True, status4TrueImageView, statusConnected);
        setupStatusButton(status4False, status4FalseImageView, statusDisconnected);
        setupStatusButton(status5True, status5TrueImageView, statusConnected);
        setupStatusButton(status5False, status5FalseImageView, statusDisconnected);
        setupStatusButton(status6True, status6TrueImageView, statusConnected);
        setupStatusButton(status6False, status6FalseImageView, statusDisconnected);

        interfaceElementsSettings.getBlackMenuButton(toMenuButton, toMenuButtonImageView, InterfaceElementsSettings.Background.LIGHT_BLUE);
        interfaceElementsSettings.getBlackCancelButton(cancelButton, cancelButtonImageView, InterfaceElementsSettings.Background.LIGHT_BLUE);
        interfaceElementsSettings.getBlackStartButton(startButton, startButtonImageView, InterfaceElementsSettings.Background.LIGHT_BLUE);
        //Отключение кнопки запуска сценария
        startButton.setDisable(true);
    }

    //Метод для перехода на экран главного меню
    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    //Метод для перехода на экран сценария диф.защиты
    public void goToPreviousPage(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.fxmlName);
    }

    // это чтобы вернуть как было все
    public void goToWorkDevice(ActionEvent event) throws IOException {
        //установка флага
        InterfaceElementsLogic.setFromCheckingStartConditions(true);
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "7.DifProtection.fxml");
    }

    /**
     * Функция для побуквенного вывода текста в Text
     *
     * @param textNode Текстовый узел, куда будет выведен текст
     * @param text     Текст, который нужно вывести
     */
    private void typeText(Text textNode, String text) {
        textNode.setText(text);
    }

    //Метод для настройки кнопок статусов
    private void setupStatusButton(Button button, ImageView imageView, Image image) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                0, 17, 0, ApplicationConstants.colours.WHITE, 0,
                imageView, image, button, 20, 20, true);
    }

    //Метод для отключения видимости кнопок статусов
    public void setStatucButtonDisabled(Button button, ImageView imageView) {
        button.setVisible(false);
        imageView.setVisible(false);
    }

    //Метод для включения видимости кнопок статусов
    public void setStatusButtonEnabled(Button button, ImageView imageView) {
        button.setVisible(true);
        imageView.setVisible(true);
    }

    //Метод, срабатывающий при нажатии галочки первого статуса
    public void statusOneTrue() {
        typeText(status2, "2. Проверка настройки модулей");
        setStatusButtonEnabled(status2True, status2TrueImageView);
        setStatusButtonEnabled(status2False, status2FalseImageView);
    }

    //Метод, срабатывающий при нажатии галочки второго статуса
    public void statusTwoTrue() {
        typeText(status3, "3. Проверка питания модулей");
        setStatusButtonEnabled(status3True, status3TrueImageView);
        setStatusButtonEnabled(status3False, status3FalseImageView);
    }

    //Метод, срабатывающий при нажатии галочки третьего статуса
    public void statusThreeTrue() {
        typeText(status4, "4. Проверка синхронизации");
        setStatusButtonEnabled(status4True, status4TrueImageView);
        setStatusButtonEnabled(status4False, status4FalseImageView);
    }

    //Метод, срабатывающий при нажатии галочки четвёртого статуса
    public void statusFourTrue() {
        typeText(status5, "5. Проверка диапазона тока");
        setStatusButtonEnabled(status5True, status5TrueImageView);
        setStatusButtonEnabled(status5False, status5FalseImageView);
    }

    //Метод, срабатывающий при нажатии галочки пятого статуса
    public void statusFiveTrue() {
        typeText(status6, "6. Проверка сопротивления силовых контактов");
        setStatusButtonEnabled(status6True, status6TrueImageView);
        setStatusButtonEnabled(status6False, status6FalseImageView);
    }

    //Метод, срабатывающий при нажатии галочки пятого статуса
    public void statusSixTrue() {
        startButton.setDisable(false);
    }

    //метод для вызова всплывающего окна
    public void errorMethod1() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Отсутствуют параметры формы");
    }

    //метод для вызова всплывающего окна
    public void errorMethod2() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Настроено недостаточное количество инверторов");
    }

    //метод для вызова всплывающего окна
    public void errorMethod3() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Включено недостаточное количество инверторов");
    }

    //метод для вызова всплывающего окна
    public void errorMethod4() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Ошибка синхронизации");
    }

    //метод для вызова всплывающего окна
    public void errorMethod5() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Ошибка положения галетного переключателя");
    }

    //метод для вызова всплывающего окна
    public void errorMethod6() {
        showAlert(Alert.AlertType.CONFIRMATION, "Ошибка!",
                "Превышение допустимого сопротивления");
    }
}