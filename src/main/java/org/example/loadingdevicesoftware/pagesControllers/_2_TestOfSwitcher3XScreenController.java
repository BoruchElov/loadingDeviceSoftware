package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;


public class _2_TestOfSwitcher3XScreenController extends ScreensController implements Configurable {

    @FXML
    SimpleTextField phaseACurrent;
    @FXML
    SimpleTextField phaseBCurrent;
    @FXML
    SimpleTextField phaseCCurrent;
    @FXML
    SimpleTextField contactOneTime;
    @FXML
    SimpleTextField contactTwoTime;
    @FXML
    Text numberOfPhases;
    @FXML
    Text contacts;
    @FXML
    Text time;
    @FXML
    Text current;

    @FXML
    SimpleImageView switcher;

    @FXML
    SimpleButton phaseButton;

    @FXML
    ButtonWithPicture contactOneButton;
    @FXML
    ButtonWithPicture contactTwoButton;

    @FXML
    public void initialize() {
        super.initialize();
        //Настройка текстовых полей ввода значений токов
        SimpleTextField[] textFields = new SimpleTextField[]{phaseACurrent, phaseBCurrent, phaseCCurrent};
        double topPosition = 335.;
        for (SimpleTextField textField : textFields) {
            textField.setup("0", SimpleTextField.Sizes.MEDIUM);
            textField.setAlignment(Pos.CENTER);
            textField.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
            AnchorPane.setTopAnchor(textField, topPosition);
            topPosition += 60.;
            AnchorPane.setLeftAnchor(textField, 200.);
        }
        //Настройка текстовых полей ввода значений временных уставок контактов
        contactOneTime.setup("0", SimpleTextField.Sizes.SMALL);
        contactOneTime.setAlignment(Pos.CENTER);
        contactOneTime.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        contactOneTime.setActualStatus(Changeable.Status.LOCKED);
        AnchorPane.setTopAnchor(contactOneTime, 250.);
        AnchorPane.setLeftAnchor(contactOneTime, 200.);

        contactTwoTime.setup("0", SimpleTextField.Sizes.SMALL);
        contactTwoTime.setAlignment(Pos.CENTER);
        contactTwoTime.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        contactTwoTime.setActualStatus(Changeable.Status.LOCKED);
        AnchorPane.setTopAnchor(contactTwoTime, 250.);
        AnchorPane.setLeftAnchor(contactTwoTime, 300.);

        //Настройка простых текстовых элементов
        Text[] text = new Text[]{numberOfPhases, contacts, time, current};
        String[] texts = new String[]{"Количество фаз", "КОНТАКТЫ", "tсраб, с", "I, А"};
        for (int i = 0; i < text.length; i++) {
            if (text[i].equals(numberOfPhases)) {
                text[i].setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                text[i].setFill(Color.WHITE);
                AnchorPane.setTopAnchor(text[i], 155.);
                AnchorPane.setLeftAnchor(text[i], 1000.);
            } else {
                text[i].setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                text[i].setFill(Color.BLACK);
                AnchorPane.setLeftAnchor(text[i], 50.);
            }
            text[i].setText(texts[i]);
        }
        AnchorPane.setTopAnchor(contacts, 190.);
        AnchorPane.setTopAnchor(time, 260.);
        AnchorPane.setTopAnchor(current, 410.);
        switcher.setup(new String[]{"", ""},
                new Image[]{ApplicationConstants.THREE_PHASE_SWITCH, ApplicationConstants.SINGLE_PHASE_SWITCH},
                new double[][]{{250, 250}, {68, 221}});
        AnchorPane.setTopAnchor(switcher, 290.);
        AnchorPane.setLeftAnchor(switcher, 550.);

        //Настройка внешнего вида и расположения кнопки изменения конфигурации
        phaseButton.setOnAction(this::changeConfiguration);
        phaseButton.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "1", "3"},
                FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        AnchorPane.setTopAnchor(phaseButton, 210.);
        AnchorPane.setLeftAnchor(phaseButton, 1008.);
        //Настройка внешнего вида и расположения кнопок положения контактов
        contactOneButton.setup(new ImageView(), ButtonWithPicture.ButtonSizes.SMALL, ButtonWithPicture.ImagViewSizes.SMALLEST,
                new String[]{"contacts-imageview", "contacts-imageview", "contacts-imageview"},
                new Image[]{null, ApplicationConstants.OPENED_CONTACT, ApplicationConstants.CLOSED_CONTACT});
        contactOneButton.setOnAction(this::changeConfiguration);
        AnchorPane.setTopAnchor(contactOneButton, 177.);
        AnchorPane.setLeftAnchor(contactOneButton, 200.);
        contactTwoButton.setup(new ImageView(), ButtonWithPicture.ButtonSizes.SMALL, ButtonWithPicture.ImagViewSizes.SMALLEST,
                new String[]{"contacts-imageview", "contacts-imageview", "contacts-imageview"},
                new Image[]{null, ApplicationConstants.OPENED_CONTACT, ApplicationConstants.CLOSED_CONTACT});
        contactTwoButton.setOnAction(this::changeConfiguration);
        AnchorPane.setTopAnchor(contactTwoButton, 177.);
        AnchorPane.setLeftAnchor(contactTwoButton, 300.);
    }

    //Общий метод для изменения конфигурации страницы
    @Override
    public void changeConfiguration(Event event) {
        switch (event.getSource()) {
            case SimpleButton button when button == phaseButton:
                if (flags[0]) {
                    phaseButton.changePosition(1);
                    switcher.changePosition(1);
                    phaseBCurrent.clear();
                    phaseBCurrent.setActualStatus(Changeable.Status.LOCKED);
                    phaseCCurrent.clear();
                    phaseCCurrent.setActualStatus(Changeable.Status.LOCKED);
                    AnchorPane.setTopAnchor(switcher, 290.);
                    AnchorPane.setLeftAnchor(switcher, 642.);
                    flags[0] = false;
                } else {
                    phaseButton.changePosition(2);
                    switcher.changePosition(0);
                    phaseBCurrent.setActualStatus(Changeable.Status.NORMAL);
                    phaseCCurrent.setActualStatus(Changeable.Status.NORMAL);
                    AnchorPane.setTopAnchor(switcher, 290.);
                    AnchorPane.setLeftAnchor(switcher, 550.);
                    flags[0] = true;
                }
                break;
            case ButtonWithPicture button when button == contactOneButton:
                if (flags[1]) {
                    contactOneButton.changePosition(2);
                    flags[1] = false;
                } else {
                    contactOneButton.changePosition(1);
                    flags[1] = true;
                }
                break;
            case ButtonWithPicture button when button == contactTwoButton:
                if (flags[2]) {
                    contactTwoButton.changePosition(2);
                    flags[2] = false;
                } else {
                    contactTwoButton.changePosition(1);
                    flags[2] = true;
                }
                break;
            case null, default:
                AnchorPane.setTopAnchor(switcher, 290.);
                AnchorPane.setLeftAnchor(switcher, 550.);
                break;
        }
    }
}