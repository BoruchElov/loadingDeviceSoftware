package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class _3_TestOfStageProtection3XScreenController {

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;

    private Stage stageForStartScreen;
    private Scene sceneForStartScreen;
    private Parent rootForStartScreen;

    //private Stage stageForTestOfStageProtection3X;
    //private Scene sceneForTestOfStageProtection3X;
    //private Parent rootForTestOfStageProtection3X;


    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    //@FXML
    //private Button testStageProtection1X;

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("0.baseWindow.fxml"));
        rootForMainScreen = loader.load();

        _0_MainScreenController mainController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForMainScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneForMainScreen = new Scene(rootForMainScreen);
        stageForMainScreen.setScene(sceneForMainScreen);
        stageForMainScreen.show();
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        //stopUpdatingDateAndTime();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("9.DifProtectionStart.fxml"));
        rootForStartScreen = loader.load();

        _7_DifProtectionSecondScreenController StartController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForStartScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneForStartScreen = new Scene(rootForStartScreen);
        stageForStartScreen.setScene(sceneForStartScreen);
        stageForStartScreen.show();
    }

    // Функция для кнопки перехода в 1 фазный режим
    //@FXML
    //public void goToTestOfStageProtection3X(ActionEvent event) throws IOException {
        //Вызов метода для остановки выполнения задачи по обновлению даты и времени
        //stopUpdatingDateAndTime();

}