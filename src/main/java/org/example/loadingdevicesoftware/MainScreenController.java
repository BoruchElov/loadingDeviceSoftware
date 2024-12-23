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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    ImageView testOfHandControlButtonBackground;
    @FXML
    ImageView testOfSwitcherButtonBackground;
    @FXML
    ImageView testOfDifProtectionButtonBackground;
    @FXML
    ImageView comTradeButtonBackground;
    @FXML
    ImageView testOfStageProtectionButtonBackground;
    @FXML
    ImageView eventLoggerButtonBackground;
    @FXML
    ImageView testOfMeasurementTransformerButtonBackground;


    Image backImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/Главная страница(без кнопок).png")).toExternalForm());
    Image settingsButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/1.png")).toExternalForm());
    Image testOfSwitcherButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/2.png")).toExternalForm());
    Image handControlButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/8.png")).toExternalForm());
    Image difProtectionButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/6.png")).toExternalForm());
    Image comTradeButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/главное меню/иконки/5.png")).toExternalForm());


    @FXML
    private Button settingsButton;
    @FXML
    private Button handControlButton;
    @FXML
    private Button testOfSwitcherButton;
    @FXML
    private Button testOfDifProtectionButton;
    @FXML
    private Button comTradeButton;
    @FXML
    private Button testOfStageProtectionButton;
    @FXML
    private Button eventLoggerButton;
    @FXML
    private Button testOfMeasurementTransformerButton;


    @FXML
    public void initialize() {
        //Установка картинки на фон
        backgroungImage.setImage(backImage);
        //__________________________________________________________________________//

        //Настройка кнопки "Настройка"
        //Загрузка картинки, установка её ширины и высоты
        setingsButtonBackground.setImage(settingsButtonImage);
        setingsButtonBackground.setFitWidth(223);
        setingsButtonBackground.setFitHeight(200);
        //Настройка отображения кнопки
        settingsButton.setStyle("-fx-background-color: #D1EDFA; " + // Голубой фон
                "-fx-border-color: #404041; " + // Тёмно-синяя граница
                "-fx-border-width: 1px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;" +   // Цвет текста
                "-fx-padding: 0;"); //Величина отступа
        //__________________________________________________________________________//

        //Настройка кнопки "Проверка автоматического выключателя
        //Загрузка картинки, настройка её ширины и высоты
        testOfSwitcherButtonBackground.setImage(testOfSwitcherButtonImage);
        testOfSwitcherButtonBackground.setFitWidth(223);
        testOfSwitcherButtonBackground.setFitHeight(200);
        testOfSwitcherButton.setStyle("-fx-background-color: #D1EDFA; " + // Голубой фон
                "-fx-border-color: #404041; " + // Тёмно-синяя граница
                "-fx-border-width: 1px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;" +   // Цвет текста
                "-fx-padding: 0;"); //Величина отступа
        //__________________________________________________________________________//


        /*testOfHandControlButtonBackground.setImage(handControlButtonImage);
        handControlButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        //__________________________________________________________________________//

        testOfDifProtectionButtonBackground.setImage(difProtectionButtonImage);
        testOfDifProtectionButton.setStyle("-fx-background-color: #79859C; " + // Голубой фон
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
                "-fx-text-fill: white;"); // Цвет текста*/
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
