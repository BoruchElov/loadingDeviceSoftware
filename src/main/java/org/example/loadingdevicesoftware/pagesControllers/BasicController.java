package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BasicButton;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.DateTimeUpdater;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;

import java.io.IOException;

class BasicController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView imageView;
    @FXML
    Text dateTimeText;
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
        menuButton.setup(BasicButton.Presets.MENU);
        menuButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        startButton.setup(BasicButton.Presets.START);
        startButton.changeActualStatus(BasicButton.Status.WARNING);
        menuButton.changeActualStatus(BasicButton.Status.LOCKED);
        clearButton.setup(BasicButton.Presets.CLEAR);
        clearButton.setOnAction(event -> {clearAll(anchorPane);});

        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        dateTimeText.fontProperty().set(Font.font(ApplicationConstants.NEW_FONT_NAME, FontWeight.BOLD, 30));
        dateTimeText.setFill(Color.WHITE);
        AnchorPane.setTopAnchor(dateTimeText, 708.0);
        AnchorPane.setLeftAnchor(dateTimeText, 380.0);
    }

    //Метод по очистке окна
    private void clearAll(Node node) {
        if (node instanceof Pane) {
            for (Node child : ((Pane) node).getChildren()) {
                if (child instanceof BasicButton button && child != clearButton) {
                    button.changeActualStatus(BasicButton.Status.DEFAULT);
                }
            }
        }
    }

}
