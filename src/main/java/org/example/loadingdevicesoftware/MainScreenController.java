package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainScreenController {

    private Stage stageForSettings;
    private Scene sceneForSettings;
    private Parent rootForSettings;

    private Stage stageForDifProtection;
    private Scene sceneForDifProtection;
    private Parent rootForDifProtection;

    @FXML
    ImageView backgroungImage;
    @FXML
    ImageView setingsButtonBackground;
    @FXML
    ImageView handControlButtonBackground;
    @FXML
    ImageView testOfSwitcherButtonBackground;
    @FXML
    ImageView difProtectionButtonBackground;
    @FXML
    ImageView comTradeButtonBackground;

    Image backImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/Главная страница(без кнопок).png")).toExternalForm());
    Image settingsButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/images/icon/Настройка.png")).toExternalForm());
    Image handControlButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/images/icon/РучнойРежим.png")).toExternalForm());
    Image testOfSwitcherButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/images/icon/проверкаВыключателя.png")).toExternalForm());
    Image difProtectionButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/images/icon/ДифЗащита3.png")).toExternalForm());
    Image comTradeButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/images/icon/КомТрейд.png")).toExternalForm());

    @FXML
    private Button settingsButton;
    @FXML
    private Button handControlButton;
    @FXML
    private Button testOfSwitcherButton;
    @FXML
    private Button difProtectionButton;
    @FXML
    private Button comTradeButton;

    @FXML
    public void initialize() {
        backgroungImage.setImage(backImage);
        setingsButtonBackground.setImage(settingsButtonImage);
        settingsButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        handControlButtonBackground.setImage(handControlButtonImage);
        handControlButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        testOfSwitcherButtonBackground.setImage(testOfSwitcherButtonImage);
        testOfSwitcherButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        difProtectionButtonBackground.setImage(difProtectionButtonImage);
        difProtectionButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        comTradeButtonBackground.setImage(comTradeButtonImage);
        comTradeButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
    }


    @FXML
    public void goToSettings (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsWindow.fxml"));
        rootForSettings = loader.load();

        SettingsScreenController settingsController = loader.getController();

        stageForSettings = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForSettings = new Scene(rootForSettings);
        stageForSettings.setScene(sceneForSettings);
        stageForSettings.show();
    }
    public void goToDifProtection (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DifProtection.fxml"));
        rootForDifProtection = loader.load();

        DifProtectionScreenController DifProtectionController = loader.getController();

        stageForDifProtection = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForDifProtection = new Scene(rootForDifProtection);
        stageForDifProtection.setScene(sceneForDifProtection);
        stageForDifProtection.show();
    }
    public void testClick() {
        System.out.println("Кнопка работает");
    }

}
