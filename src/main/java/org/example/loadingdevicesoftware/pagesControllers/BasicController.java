package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BasicButton;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;

import java.io.IOException;

class BasicController {

    @FXML
    BasicButton clearButton;
    @FXML
    BasicButton saveButton;
    @FXML
    BasicButton menuButton;
    @FXML
    BasicButton startButton;
    @FXML
    BasicButton cancelButton;
    @FXML
    BasicButton stopButton;
    @FXML
    BasicButton continueButton;
    @FXML
    BasicButton finishButton;


    @FXML
    public void initialize() {
        menuButton.setup();
        menuButton.setOnAction(event -> {
            try {
                toMenu(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void toMenu(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    //Метод по очистке окна
    private void clearAll(Node node) {
        //Очищаем текстовые поля TextField
        if (node instanceof TextInputControl) {
            ((TextInputControl) node).clear();
        }

        //Очищаем кнопки (ToggleButton) в дефолтное состояние
        else if (node instanceof ToggleButton toggleButton) {
            toggleButton.setUserData(null);
            toggleButton.setSelected(false);
        }

        //рекурсивный метод для очистки
        else if (node instanceof Pane) {
            for (Node child : ((Pane) node).getChildren()) {
                clearAll(child);
            }
        }
    }

}
