package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;

public class _101_deviceWorkingScreenControl {

    @FXML
    Text headerText;
    @FXML
    Text checkOne;
    @FXML
    Text checkTwo;
    @FXML
    Text checkThree;
    @FXML
    Text checkFour;
    @FXML
    Text checkFive;
    @FXML
    Text checkSix;

    @FXML
    AnchorPane anchorPane;

    @FXML
    SimpleButton menuButton;
    @FXML
    SimpleButton cancelButton;
    @FXML
    SimpleButton startButton;

    @FXML
    Text dateTimeText;

    @FXML
    ImageView imageView;

    @FXML
    public void initialize() {
        //Настройка области для расположения элементов и создание ImageView для расположения фонового изображения
        anchorPane.setPrefSize(ApplicationConstants.APPLICATION_WINDOW_WIDTH, ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        imageView.setFitHeight(ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        imageView.setFitWidth(ApplicationConstants.APPLICATION_WINDOW_WIDTH);
        imageView.toBack();
        imageView.setImage(ApplicationConstants.BACKGROUND_BLUE);
        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case Text text when text == dateTimeText:
                    text.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
                    text.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    AnchorPane.setTopAnchor(text, 714.0);
                    AnchorPane.setLeftAnchor(text, 385.0);
                    break;
                case SimpleButton button when button == cancelButton:
                    AnchorPane.setTopAnchor(button, 695.0);
                    AnchorPane.setLeftAnchor(button, 770.0);
                    button.setup(SimpleButton.Presets.CANCEL);
                    button.setOnAction(event -> {
                        try {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.fxmlName);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    button.changePosition(2);
                    break;
                case SimpleButton button when button == startButton:
                    AnchorPane.setTopAnchor(button, 695.0);
                    AnchorPane.setLeftAnchor(button, 1030.0);
                    button.setup(SimpleButton.Presets.START);
                    button.setOnAction(event -> {
                        try {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.fxmlName);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    button.setActualStatus(Changeable.Status.LOCKED);
                    break;
                case SimpleButton button when button == menuButton:
                    AnchorPane.setTopAnchor(button, 695.0);
                    AnchorPane.setLeftAnchor(button, 50.0);
                    button.setup(SimpleButton.Presets.MENU);
                    button.setOnAction(event -> {
                        try {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    break;
                case Text text when text == headerText || text == checkOne || text == checkTwo || text == checkThree ||
                        text == checkFour || text == checkFive || text == checkSix:
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    Object[] params = switch(text) {
                        case Text textOne when textOne == headerText -> new Object[]{"Проверка условий пуска", 480., 150.};
                        case Text textOne when textOne == checkOne -> new Object[]{"1. Проверка параметров формы", 80., 230.};
                        case Text textOne when textOne == checkTwo -> new Object[]{"2. Проверка настройки модулей", 80., 296.};
                        case Text textOne when textOne == checkThree -> new Object[]{"3. Проверка питания модулей", 80., 362.};
                        case Text textOne when textOne == checkFour -> new Object[]{"4. Проверка синхронизации", 80., 428.};
                        case Text textOne when textOne == checkFive -> new Object[]{"5. Проверка диапазона тока", 80., 494.};
                        case Text textOne when textOne == checkSix -> new Object[]{"6. Проверка сопротивления силовых контактов", 80., 560.};
                        default -> null;
                    };
                    text.setText((String) params[0]);
                    AnchorPane.setLeftAnchor(text, (double) params[1]);
                    AnchorPane.setTopAnchor(text, (double) params[2]);
                    break;
                case null, default:
                    break;
            }
        }


    }

}
