package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    private Stage stageForSettings;
    private Scene sceneForSettings;
    private Parent rootForSettings;

    private Stage stageForDifProtection;
    private Scene sceneForDifProtection;
    private Parent rootForDifProtection;

    @FXML
    private Button applyScenarioButton;
    @FXML
    private Button openConnectionSettingsButton;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Button DifProtection;

    @FXML
    private ComboBox<String> scenariosComboBox;


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

}
