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

public class _102_protocolScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();


    //Объявление области изображения для фона
    @FXML
    private ImageView backgroundImageView;
    //картинка кнопок старт/меню
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;

    //Объявление текстовых полей для вывода данных
    @FXML
    private TextField current1;
    @FXML
    private TextField current2;
    @FXML
    private TextField current3;
    @FXML
    private TextField current4;
    @FXML
    private TextField current5;
    @FXML
    private TextField current6;
    @FXML
    private TextField current7;
    @FXML
    private TextField current8;

    //кнопки
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;


    //Объект картинки для фона
    private Image background = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_3форма_предупреждение(без кнопок).png")).toExternalForm());

    @FXML
    public void initialize() {

        //Установка изображения на фон
        backgroundImageView.setImage(background);

        interfaceElementsSettings.getBlackMenuButton(toMenuButton, toMenuButtonImageView);
        interfaceElementsSettings.getBlackSaveButton(startButton, startButtonImageView);

        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(current1, "500");
        setupObjectNameField(current2, "500");
        setupObjectNameField(current3, "500");
        setupObjectNameField(current4, "500");
        setupObjectNameField(current5, "500");
        setupObjectNameField(current6, "500");
        setupObjectNameField(current7, "500");
        setupObjectNameField(current8, "500");
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        interfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3,17,15, ApplicationConstants.colours.BLACK,20,0,textField,
                prompt);
    }

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }
}
