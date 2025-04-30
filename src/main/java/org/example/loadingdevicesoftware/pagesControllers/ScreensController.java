package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;

class ScreensController extends BasicController {

    @FXML
    SimpleButton clearButton;
    @FXML
    SimpleButton saveButton;
    @FXML
    SimpleButton menuButton;
    @FXML
    SimpleButton startButton;
    @FXML
    SimpleButton cancelButton;
    @FXML
    SimpleButton stopButton;
    @FXML
    SimpleButton continueButton;
    @FXML
    SimpleButton finishButton;


    @FXML
    public void initialize() {
        super.initialize();
        //Настройка положения кнопок в нижней части приложения
        AnchorPane.setTopAnchor(menuButton, 695.0);
        AnchorPane.setLeftAnchor(menuButton, 50.0);
        AnchorPane.setTopAnchor(startButton, 695.0);
        AnchorPane.setLeftAnchor(startButton, 1030.0);
        AnchorPane.setTopAnchor(clearButton, 695.0);
        AnchorPane.setLeftAnchor(clearButton, 770.0);
        //Визуальная настройка и привязка метода для кнопки МЕНЮ
        menuButton.setup(SimpleButton.Presets.MENU);
        menuButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        startButton.setup(SimpleButton.Presets.START);
        menuButton.setActualStatus(SimpleButton.Status.NORMAL);
        clearButton.setup(SimpleButton.Presets.CLEAR);
        clearButton.setOnAction(event -> {clearAll(anchorPane);});
    }

    //Метод по очистке всех элементов окна приложения
    private void clearAll (Node node) {
        if (node instanceof Pane) {
            for (Node child : ((Pane) node).getChildren()) {
                if (child instanceof SimpleButton button && child != clearButton) {
                    button.setActualStatus(SimpleButton.Status.NORMAL);
                    button.changePosition(0);
                }
                if (child instanceof SimpleTextField textField) {
                    textField.clear();
                }
                if (child instanceof ButtonWithPicture button) {
                    button.setActualStatus(ButtonWithPicture.Status.NORMAL);
                    button.changePosition(0);
                }
            }
        }
    }

    //Метод по очистке всех элементов окна приложения
    public void lockAll (Node node) {
        if (node instanceof Pane) {
            for (Node child : ((Pane) node).getChildren()) {
                if (child instanceof SimpleButton button && child != clearButton) {
                    button.setActualStatus(SimpleButton.Status.LOCKED);
                }
                if (child instanceof SimpleTextField textField) {
                    textField.setActualStatus(SimpleTextField.Status.LOCKED);
                }
                if (child instanceof ButtonWithPicture button) {
                    button.setActualStatus(ButtonWithPicture.Status.LOCKED);
                }
            }
        }
    }



}
