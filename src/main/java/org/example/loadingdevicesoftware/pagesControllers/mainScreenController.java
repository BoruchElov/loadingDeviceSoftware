package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ButtonWithPicture;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.FontManager;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;

import java.io.IOException;

public class mainScreenController extends BasicController{

    @FXML
    ButtonWithPicture settingsButton;
    @FXML
    ButtonWithPicture switcherTestButton;
    @FXML
    ButtonWithPicture relayProtectionTestButton;
    @FXML
    ButtonWithPicture eventLoggerButton;
    @FXML
    ButtonWithPicture measurementTransformerTestButton;
    @FXML
    ButtonWithPicture comtradeButton;
    @FXML
    ButtonWithPicture differentialProtectionTestButton;
    @FXML
    ButtonWithPicture handControlButton;
    @FXML
    ButtonWithPicture developmentButton;

    @FXML
    public void initialize() {
        super.initialize();
        imageView.setImage(ApplicationConstants.NEW_BASE_BACKGROUND);
        //Создание шрифта для кнопок
        Font buttonsFont = FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL);
        //Создание массива надписей на кнопках
        String[] buttonsTexts = new String[]{"Настройка", "Проверка автоматического выключателя", "Проверка релейной защиты",
                "Журнал событий", "Проверка измерительного трансформатора", "COMTRADE", "Проверка дифференциальной защиты",
                "Ручной ввод", "Отладка"};
        //Создание массива размеров ImageView
        ButtonWithPicture.ImagViewSizes[] imageViewSizes = new ButtonWithPicture.ImagViewSizes[]{ButtonWithPicture.ImagViewSizes.LARGE,
        ButtonWithPicture.ImagViewSizes.SMALL, ButtonWithPicture.ImagViewSizes.LARGE, ButtonWithPicture.ImagViewSizes.LARGE,
        ButtonWithPicture.ImagViewSizes.MEDIUM, ButtonWithPicture.ImagViewSizes.LARGE, ButtonWithPicture.ImagViewSizes.MEDIUM,
        ButtonWithPicture.ImagViewSizes.MEDIUM, ButtonWithPicture.ImagViewSizes.LARGE};
        //Создание массива изображений
        Image[] images = new Image[]{ApplicationConstants.SETTINGS, ApplicationConstants.SWITCHER_TEST, ApplicationConstants.RELAY_PROTECTION,
        ApplicationConstants.EVENT_LOGGER, ApplicationConstants.MEASUREMENT_TRANSFORMER, ApplicationConstants.COMTRADE,
        ApplicationConstants.DIFFERENTIAL_PROTECTION, ApplicationConstants.HAND_CONTROL, ApplicationConstants.DEBUGGER};
        //Настройка внешнего вида кнопок
        int j = 0;
        double topPosition  = 170.;
        double leftPosition = 47.;
        for (int i = 0; i < anchorPane.getChildren().size(); i++) {
            if (anchorPane.getChildren().get(i) instanceof ButtonWithPicture button) {
                button.setup(new ImageView(), ButtonWithPicture.ButtonSizes.LARGE, imageViewSizes[j], new String[]{"image-button-main"},
                        new Image[]{images[j]});
                button.changePosition(0);
                button.setFont(buttonsFont);
                button.setText(buttonsTexts[j]);
                if (j == 5) {
                    topPosition = 410.;
                    leftPosition = 47.;
                }
                AnchorPane.setTopAnchor(button, topPosition);
                AnchorPane.setLeftAnchor(button, leftPosition);
                leftPosition += 247.;
                j++;
            }
        }

        //Настройка положения строки даты-времени
        AnchorPane.setTopAnchor(dateTimeText, 714.0);
        AnchorPane.setLeftAnchor(dateTimeText, 490.0);

        //Привязка метода к кнопке "Настройка"
        settingsButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "1.settingsWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Проверка автоматического выключателя"
        switcherTestButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "2.TestOfSwitcher3X.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Проверка релейной защиты"
        relayProtectionTestButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "3.TestOfStageProtection3X.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Журнал событий"
        eventLoggerButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "4.EventLogger.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Проверка измерительного трансформатора"
        measurementTransformerTestButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "5.TestOfMeasurementTransformer.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Comtrade"
        comtradeButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "6.ComTrade.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Проверка дифференциальной защиты"
        differentialProtectionTestButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "7.DifProtection.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Ручной ввод"
        handControlButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "8.HandControl.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Привязка метода к кнопке "Отладка"
        developmentButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "9.DeBugger.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
