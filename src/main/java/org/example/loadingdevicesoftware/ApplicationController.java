package org.example.loadingdevicesoftware;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;


public class ApplicationController {

    @FXML
    private Button applyScenarioButton;

    @FXML
    private Button openConnectionSettingsButton;

    @FXML
    private ComboBox<String> scenariosComboBox;

    @FXML
    public void initialize() {
        scenariosComboBox.getItems().addAll("Проверка дифференциальной защиты силового трансформатора",
                "Проверка автоматических выключателей и силовых цепей подключения РЗ");
        scenariosComboBox.setValue("Проверка дифференциальной защиты силового трансформатора");
    }
}
