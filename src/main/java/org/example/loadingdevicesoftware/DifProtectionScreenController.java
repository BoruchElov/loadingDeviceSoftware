package org.example.loadingdevicesoftware;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DifProtectionScreenController {

    private boolean shortCircuitButtonStatus = false;
    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;

    @FXML
    private ToggleButton shortCircuitLocationButton;
    @FXML
    private ToggleButton phaseAButton;
    @FXML
    private ToggleButton phaseBButton;
    @FXML
    private ToggleButton phaseCButton;
    @FXML
    private Button windingOneButton;
    @FXML
    private Button windingTwoButton;
    @FXML
    private Button windingOneConnection;
    @FXML
    private Button windingTwoConnection;
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private ImageView backgroundImageView;

    @FXML
    ImageView windingOneView;
    @FXML
    ImageView windingTwoView;
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Star1.png")).toExternalForm());
    Image backImageInSC = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_I/RS__I.png")).toExternalForm());
    Image backImageInSCA = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_I/RS_A_I.png")).toExternalForm());
    Image backImageInSCB = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_I/RS_B_I.png")).toExternalForm());
    Image backImageInSCC = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_I/RS_C_I.png")).toExternalForm());
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_O/RS__O.png")).toExternalForm());
    Image backImageOutSCA = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_O/RS_A_O.png")).toExternalForm());
    Image backImageOutSCB = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_O/RS_B_O.png")).toExternalForm());
    Image backImageOutSCC = new Image(Objects.requireNonNull(getClass().
            getResource("/images/RS_O/RS_C_O.png")).toExternalForm());

    @FXML
    public void initialize() {
        backgroundImageView.setImage(backImageOutSC);
        shortCircuitLocationButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        phaseAButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        phaseBButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        phaseCButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        windingOneButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        windingTwoButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        toMenuButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        startButton.setStyle("-fx-background-color: #9BB6E9; " + // Голубой фон
                "-fx-border-color: #EDEDED; " + // Серая граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: black;"); // Цвет текста
        windingOneConnection.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        windingOneView.setImage(deltaConnection);
        windingTwoConnection.setStyle("-fx-background-color: #79859C; " + // Голубой фон
                "-fx-border-color: #0A1733; " + // Тёмно-синяя граница
                "-fx-border-width: 4px; " + // Ширина границы
                "-fx-background-radius: 15px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;"); // Цвет текста
        windingTwoView.setImage(deltaConnection);
    }

    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("baseWindow.fxml"));
        rootForMainScreen = loader.load();

        MainScreenController mainController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForMainScreen = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForMainScreen = new Scene(rootForMainScreen);
        stageForMainScreen.setScene(sceneForMainScreen);
        stageForMainScreen.show();
    }

    @FXML
    public void setProperPicture() {
        if(shortCircuitLocationButton.isSelected()) {
            shortCircuitLocationButton.setText("Внутреннее КЗ");
            if (phaseAButton.isSelected()) {
                backgroundImageView.setImage(backImageInSCA);
                phaseBButton.setDisable(true);
                phaseCButton.setDisable(true);
                shortCircuitLocationButton.setDisable(true);
            } else {
                phaseBButton.setDisable(false);
                phaseCButton.setDisable(false);
                shortCircuitLocationButton.setDisable(false);
                if (phaseBButton.isSelected()) {
                    backgroundImageView.setImage(backImageInSCB);
                    phaseAButton.setDisable(true);
                    phaseCButton.setDisable(true);
                    shortCircuitLocationButton.setDisable(true);
                } else {
                    phaseAButton.setDisable(false);
                    phaseCButton.setDisable(false);
                    shortCircuitLocationButton.setDisable(false);
                    if (phaseCButton.isSelected()) {
                        backgroundImageView.setImage(backImageInSCC);
                        phaseAButton.setDisable(true);
                        phaseBButton.setDisable(true);
                        shortCircuitLocationButton.setDisable(true);
                    } else {
                        phaseAButton.setDisable(false);
                        phaseBButton.setDisable(false);
                        shortCircuitLocationButton.setDisable(false);
                        backgroundImageView.setImage(backImageInSC);
                    }
                }
            }
        } else {
            shortCircuitLocationButton.setText("Внешнее КЗ");
            if (phaseAButton.isSelected()) {
                backgroundImageView.setImage(backImageOutSCA);
                phaseBButton.setDisable(true);
                phaseCButton.setDisable(true);
                shortCircuitLocationButton.setDisable(true);
            } else {
                phaseBButton.setDisable(false);
                phaseCButton.setDisable(false);
                shortCircuitLocationButton.setDisable(false);
                if (phaseBButton.isSelected()) {
                    backgroundImageView.setImage(backImageOutSCB);
                    phaseAButton.setDisable(true);
                    phaseCButton.setDisable(true);
                    shortCircuitLocationButton.setDisable(true);
                } else {
                    phaseAButton.setDisable(false);
                    phaseCButton.setDisable(false);
                    shortCircuitLocationButton.setDisable(false);
                    if (phaseCButton.isSelected()) {
                        backgroundImageView.setImage(backImageOutSCC);
                        phaseAButton.setDisable(true);
                        phaseBButton.setDisable(true);
                        shortCircuitLocationButton.setDisable(true);
                    } else {
                        phaseAButton.setDisable(false);
                        phaseBButton.setDisable(false);
                        shortCircuitLocationButton.setDisable(false);
                        backgroundImageView.setImage(backImageOutSC);
                    }
                }
            }
        }
    }
    @FXML
    public void setPictureForWindingOne() {
        if(windingOneStatus) {
            windingOneView.setImage(starConnection);
            windingOneStatus = false;
        } else {
            windingOneView.setImage(deltaConnection);
            windingOneStatus = true;
        }
    }
    @FXML
    public void setPictureForWindingTwo() {
        if(windingTwoStatus) {
            windingTwoView.setImage(starConnection);
            windingTwoStatus = false;
        } else {
            windingTwoView.setImage(deltaConnection);
            windingTwoStatus = true;
        }
    }


}
