package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;

class BasicController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView imageView;
    @FXML
    Text dateTimeText;
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
        anchorPane.setPrefSize(1280,800);
        imageView.setFitHeight(800);
        imageView.setFitWidth(1280);
        imageView.toBack();
        imageView.setImage(ApplicationConstants.NEW_BACKGROUND);
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
        startButton.setActualStatus(SimpleButton.Status.WARNING);
        menuButton.setActualStatus(SimpleButton.Status.LOCKED);
        clearButton.setup(SimpleButton.Presets.CLEAR);
        clearButton.setActualStatus(SimpleButton.Status.NORMAL);
        clearButton.setOnAction(event -> {clearAll(anchorPane);});

        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        dateTimeText.fontProperty().set(Font.font(ApplicationConstants.NEW_FONT_NAME, FontWeight.BOLD, 30));
        dateTimeText.setFill(Color.WHITE);
        AnchorPane.setTopAnchor(dateTimeText, 708.0);
        AnchorPane.setLeftAnchor(dateTimeText, 380.0);

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
