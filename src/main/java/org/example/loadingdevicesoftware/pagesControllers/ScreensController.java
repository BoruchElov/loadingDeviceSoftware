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
        startButton.changePosition(1);
        menuButton.setActualStatus(SimpleButton.Status.LOCKED);
        menuButton.changePosition(2);
        clearButton.setup(SimpleButton.Presets.CLEAR);
        clearButton.setOnAction(event -> {clearAll(anchorPane);});

        Text[] texts = new Text[]{inverterA1, inverterB1, inverterC1, inverterA2, inverterB2, inverterC2};
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        Font dateFont = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
        dateTimeText.fontProperty().set(dateFont);
        dateTimeText.setFill(Color.WHITE);
        Font moduleFont = FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL);
        for (Text text : texts) {
            text.fontProperty().set(moduleFont);
            text.setFill(Color.WHITE);
        }
        inverterA1.setText("МОДУЛЬ А1");
        inverterA2.setText("МОДУЛЬ А2");
        inverterB1.setText("МОДУЛЬ В1");
        inverterB2.setText("МОДУЛЬ В2");
        inverterC1.setText("МОДУЛЬ С1");
        inverterC2.setText("МОДУЛЬ С2");
        AnchorPane.setTopAnchor(inverterA1, 37.5);
        AnchorPane.setLeftAnchor(inverterA1, 85.0);
        AnchorPane.setTopAnchor(inverterA2, 77.5);
        AnchorPane.setLeftAnchor(inverterA2, 85.0);
        AnchorPane.setTopAnchor(inverterB1, 37.5);
        AnchorPane.setLeftAnchor(inverterB1, 295.0);
        AnchorPane.setTopAnchor(inverterB2, 77.5);
        AnchorPane.setLeftAnchor(inverterB2, 295.0);
        AnchorPane.setTopAnchor(inverterC1, 37.5);
        AnchorPane.setLeftAnchor(inverterC1, 505.0);
        AnchorPane.setTopAnchor(inverterC2, 77.5);
        AnchorPane.setLeftAnchor(inverterC2, 505.0);
        AnchorPane.setTopAnchor(dateTimeText, 714.0);
        AnchorPane.setLeftAnchor(dateTimeText, 385.0);

        circleA1.setRadius(10);
        circleA1.setFill(Color.web(ApplicationConstants.Green));
        AnchorPane.setTopAnchor(circleA1, 37.5);
        AnchorPane.setLeftAnchor(circleA1, 52.5);

        circleA2.setRadius(10);
        circleA2.setFill(Color.web(ApplicationConstants.Green));
        AnchorPane.setTopAnchor(circleA2, 77.5);
        AnchorPane.setLeftAnchor(circleA2, 52.5);

        circleB1.setRadius(10);
        circleB1.setFill(Color.web(ApplicationConstants.Green));
        AnchorPane.setTopAnchor(circleB1, 37.5);
        AnchorPane.setLeftAnchor(circleB1, 262.5);

        circleB2.setRadius(10);
        circleB2.setFill(Color.web(ApplicationConstants.Green));
        AnchorPane.setTopAnchor(circleB2, 77.5);
        AnchorPane.setLeftAnchor(circleB2, 262.5);

        circleC1.setRadius(10);
        circleC1.setFill(Color.web(ApplicationConstants.Green));
        AnchorPane.setTopAnchor(circleC1, 37.5);
        AnchorPane.setLeftAnchor(circleC1, 472.5);

        circleC2.setRadius(10);
        circleC2.setFill(Color.web(ApplicationConstants.Green));
        AnchorPane.setTopAnchor(circleC2, 77.5);
        AnchorPane.setLeftAnchor(circleC2, 472.5);
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
