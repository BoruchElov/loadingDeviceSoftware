package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;


class BasicController {

    @FXML
    Circle circleA1;
    @FXML
    Circle circleA2;
    @FXML
    Circle circleB1;
    @FXML
    Circle circleB2;
    @FXML
    Circle circleC1;
    @FXML
    Circle circleC2;

    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView imageView;
    @FXML
    Text dateTimeText;
    @FXML
    Text inverterA1;
    @FXML
    Text inverterB1;
    @FXML
    Text inverterC1;
    @FXML
    Text inverterA2;
    @FXML
    Text inverterB2;
    @FXML
    Text inverterC2;


    @FXML
    public void initialize() {
        //Настройка области для расположения элементов и создание ImageView для расположения фонового изображения
        anchorPane.setPrefSize(ApplicationConstants.APPLICATION_WINDOW_WIDTH, ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        imageView.setFitHeight(ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        imageView.setFitWidth(ApplicationConstants.APPLICATION_WINDOW_WIDTH);
        imageView.toBack();

        //Создание текстовых элементов: названий модулей и строки даты-времени
        Text[] texts = new Text[]{inverterA1, inverterB1, inverterC1, inverterA2, inverterB2, inverterC2};
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        dateTimeText.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        dateTimeText.setFill(Color.WHITE);
        for (Text text : texts) {
            text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
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

        //Настройка кругов статусов инверторов
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



}
