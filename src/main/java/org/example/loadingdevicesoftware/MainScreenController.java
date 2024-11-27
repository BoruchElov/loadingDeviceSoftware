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

    @FXML
    private Button applyScenarioButton;

    @FXML
    private Button openConnectionSettingsButton;
    @FXML
    private Button backToMenuButton;

    @FXML
    private ComboBox<String> scenariosComboBox;

    @FXML
    public void initialize() {
        scenariosComboBox.getItems().addAll("Проверка дифференциальной защиты силового трансформатора",
                "Проверка автоматических выключателей и силовых цепей подключения РЗ");
        scenariosComboBox.setValue("Проверка дифференциальной защиты силового трансформатора");
    }
    @FXML
    public void goToSettings (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsWindow.fxml"));
        rootForSettings = loader.load();

        SettingsScreenController settingsController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForSettings = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForSettings = new Scene(rootForSettings);
        stageForSettings.setScene(sceneForSettings);
        stageForSettings.show();
    }

    }
