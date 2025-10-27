package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;


public class _8_HandControlScreenController extends ScreensController implements Configurable {
    // Переменные для настройки размеров
    Double Y1 = 314.;           //Высота первого уровня текстовых полей
    Double Y2 = Y1+60;          //Второй уровень
    Double Y3 = Y2+60;          //Третий уровень

    Double X1 = 20.;            //Отступ по горизонтали для текстовых полей
    Double X2 = X1 + 85;
    Double X3 = 750.;
    Double X4 = X3 + 85;

    Double k = 120.;            //Размер картинки инверторов

    //текстовые поля для токов и углов слева
    @FXML
    SimpleTextField phaseALCurrent;
    @FXML
    SimpleTextField phaseALAngle;
    @FXML
    SimpleTextField phaseBLCurrent;
    @FXML
    SimpleTextField phaseBLAngle;
    @FXML
    SimpleTextField phaseCLCurrent;
    @FXML
    SimpleTextField phaseCLAngle;
    //текстовые поля для токов и углов справа
    @FXML
    SimpleTextField phaseARCurrent;
    @FXML
    SimpleTextField phaseARAngle;
    @FXML
    SimpleTextField phaseBRCurrent;
    @FXML
    SimpleTextField phaseBRAngle;
    @FXML
    SimpleTextField phaseCRCurrent;
    @FXML
    SimpleTextField phaseCRAngle;

    //Картинки модулей
    @FXML
    SimpleImageView moduleA1;
    @FXML
    SimpleImageView moduleB1;
    @FXML
    SimpleImageView moduleC1;
    @FXML
    SimpleImageView moduleA2;
    @FXML
    SimpleImageView moduleB2;
    @FXML
    SimpleImageView moduleC2;

    //Кнопки контактов контакторов
    @FXML
    ButtonWithPicture contactOneButton;
    @FXML
    ButtonWithPicture contactTwoButton;
    // Изображение состояния контактов
    @FXML
    Circle contactOne;
    @FXML
    Circle contactTwo;

    //кнопки справа
    @FXML
    SimpleButton modeButton;
    @FXML
    SimpleButton conditionButton;
    @FXML
    SimpleButton timeModeButton;
    @FXML
    Text time;

    //Текстовые поля для формы
    @FXML
    Text contacts;
    @FXML
    Text one;
    @FXML
    Text two;
    @FXML
    Text currentOne;
    @FXML
    Text currentTwo;
    @FXML
    Text phaseOne;
    @FXML
    Text phaseTwo;
    @FXML
    Text aOne;
    @FXML
    Text aTwo;
    @FXML
    Text bOne;
    @FXML
    Text bTwo;
    @FXML
    Text cOne;
    @FXML
    Text cTwo;
    @FXML
    Text feedingWinding;
    @FXML
    Text faultLocation;
    @FXML
    Text brokenPhases;

    @FXML
    public void initialize() {

        super.initialize();
        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                //НАСТРОЙКИ КНОПОК
                case SimpleButton button when button == modeButton || button == conditionButton
                        || button == timeModeButton -> {
                    button.setOnAction(this::changeConfiguration);
                    switch (button) {
                        //Настройка внешнего вида и расположения кнопки изменения режима
                        case SimpleButton bt1 when bt1 == modeButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "1", "3"}, FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button, 210.);
                            AnchorPane.setLeftAnchor(button, 1005.);
                        }
                        //Настройка внешнего вида и расположения кнопки условия отключения
                        case SimpleButton bt1 when bt1 == conditionButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "t/сраб", "сух/конт"}, FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button, 385.);
                            AnchorPane.setLeftAnchor(button, 1005.);
                        }
                        //Настройка внешнего вида и расположения кнопки изменения времени
                        case SimpleButton bt1 when bt1 == timeModeButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "1", "2"}, FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button, 560.);
                            AnchorPane.setLeftAnchor(button, 1005.);
                        }
                        default -> {
                        }
                    }
                }
                //НАСТРОЙКА ТЕКСТОВЫХ ПОЛЕЙ
                case SimpleTextField textField when textField == phaseALCurrent || textField == phaseBLCurrent || textField == phaseCLCurrent
                        || textField == phaseALAngle || textField == phaseBLAngle || textField == phaseCLAngle
                        || textField == phaseARCurrent || textField == phaseBRCurrent || textField == phaseCRCurrent
                        || textField == phaseARAngle || textField == phaseBRAngle || textField == phaseCRAngle -> {
                    switch (textField) {
                        //Текстовые поля слева
                        case SimpleTextField tf1 when tf1 == phaseALCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseALAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBLCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBLAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCLCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCLAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        //Текстовые поля справа
                        case SimpleTextField tf1 when tf1 == phaseARCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseARAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBRCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBRAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCRCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCRAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        default -> {
                        }
                    }
                }
                //НАСТРОЙКА ИЗОБРАЖЕНИЙ ИНВЕРТОРА
                case SimpleImageView imageView when imageView == moduleA1 || imageView == moduleB1 || imageView == moduleC1
                        || imageView == moduleA2 || imageView == moduleB2 || imageView == moduleC2 -> {
                    switch (imageView) {
                        case SimpleImageView iv1 when iv1 == moduleA1 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y1 - 85);
                            AnchorPane.setLeftAnchor(imageView, X1 + 320);
                        }
                        case SimpleImageView iv1 when iv1 == moduleB1 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y2 - 45);
                            AnchorPane.setLeftAnchor(imageView, X1 + 320);
                        }
                        case SimpleImageView iv1 when iv1 == moduleC1 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y3 - 5);
                            AnchorPane.setLeftAnchor(imageView, X1 + 320);
                        }

                        case SimpleImageView iv1 when iv1 == moduleA2 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y1 - 85);
                            AnchorPane.setLeftAnchor(imageView, X2 + 390);
                        }
                        case SimpleImageView iv1 when iv1 == moduleB2 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y2 - 45);
                            AnchorPane.setLeftAnchor(imageView, X2 + 390);
                        }
                        case SimpleImageView iv1 when iv1 == moduleC2 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y3 - 5);
                            AnchorPane.setLeftAnchor(imageView, X2 + 390);
                        }
                        default -> {
                        }
                    }
                }
                // НАСТРОЙКА КРУЖКОВ КОНТАКТОРА
                case Circle c when c == contactOne || c == contactTwo -> {
                    c.setRadius(10.);
                    c.getStyleClass().add("circles");
                    switch (c) {
                        case Circle c1 when c1 == contactOne -> {
                            AnchorPane.setTopAnchor(c1, 189.);
                            AnchorPane.setLeftAnchor(c1, 165.);
                        }
                        case Circle c1 when c1 == contactTwo -> {
                            AnchorPane.setTopAnchor(c1, 189.);
                            AnchorPane.setLeftAnchor(c1, 265.);
                        }
                        default -> {
                        }
                    }
                }
                // НАСТРОЙКА КНОПОК КОНТАКТОРА
                case ButtonWithPicture button when button == contactOneButton || button == contactTwoButton -> {
                    button.setup(new ImageView(), ButtonWithPicture.ButtonSizes.SMALL, ButtonWithPicture.ImagViewSizes.SMALLEST,
                            new String[]{"contacts-imageview", "contacts-imageview", "contacts-imageview"},
                            new Image[]{null, ApplicationConstants.OPENED_CONTACT, ApplicationConstants.CLOSED_CONTACT});
                    button.setOnAction(this::changeConfiguration);
                    switch (button) {
                        case ButtonWithPicture button1 when button1 == contactOneButton -> {
                            AnchorPane.setTopAnchor(button1, 177.);
                            AnchorPane.setLeftAnchor(button1, 200.);
                        }
                        case ButtonWithPicture button1 when button1 == contactTwoButton -> {
                            AnchorPane.setTopAnchor(button1, 177.);
                            AnchorPane.setLeftAnchor(button1, 300.);
                        }

                        default -> {}
                    }
                }
                //ТЕКСТОВЫЕ ПОЛЯ НА ФОРМЕ
                case Text text when text == contacts || text == one || text == two || text == currentOne ||
                        text == currentTwo || text == phaseOne || text == phaseTwo || text == aOne ||
                        text == aTwo || text == bOne || text == bTwo || text == cOne || text == cTwo -> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                    text.setFill(Color.BLACK);
                    switch (text) {
                        case Text text1 when text1 == contacts -> {
                            text1.setText("КОНТАКТЫ");
                            AnchorPane.setTopAnchor(text1, 190.);
                            AnchorPane.setLeftAnchor(text1, 50.);
                        }
                        case Text text1 when text1 == one -> {
                            text1.setText("1");
                            AnchorPane.setTopAnchor(text1, 150.);
                            AnchorPane.setLeftAnchor(text1, 220.);
                        }
                        case Text text1 when text1 == two -> {
                            text1.setText("2");
                            AnchorPane.setTopAnchor(text1, 150.);
                            AnchorPane.setLeftAnchor(text1, 322.);
                        }
                        case Text text1 when text1 == currentOne -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 45.);
                        }
                        case Text text1 when text1 == currentTwo -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 755.);
                        }
                        case Text text1 when text1 == phaseOne -> {
                            text1.setText("fi, град");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 111.);
                        }
                        case Text text1 when text1 == phaseTwo -> {
                            text1.setText("fi, град");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 855.);
                        }
                        case Text text1 when text1 == aOne -> {
                            text1.setText("МОДУЛЬ A1");
                            AnchorPane.setTopAnchor(text1, 320.);
                            AnchorPane.setLeftAnchor(text1, 210.);
                        }
                        case Text text1 when text1 == aTwo -> {
                            text1.setText("МОДУЛЬ A2");
                            AnchorPane.setTopAnchor(text1, 320.);
                            AnchorPane.setLeftAnchor(text1, 705.);
                        }
                        case Text text1 when text1 == bOne -> {
                            text1.setText("МОДУЛЬ B1");
                            AnchorPane.setTopAnchor(text1, 375.);
                            AnchorPane.setLeftAnchor(text1, 210.);
                        }
                        case Text text1 when text1 == bTwo -> {
                            text1.setText("МОДУЛЬ B2");
                            AnchorPane.setTopAnchor(text1, 375.);
                            AnchorPane.setLeftAnchor(text1, 705.);
                        }
                        case Text text1 when text1 == cOne -> {
                            text1.setText("МОДУЛЬ C1");
                            AnchorPane.setTopAnchor(text1, 430.);
                            AnchorPane.setLeftAnchor(text1, 210.);
                        }
                        case Text text1 when text1 == cTwo -> {
                            text1.setText("МОДУЛЬ C2");
                            AnchorPane.setTopAnchor(text1, 430.);
                            AnchorPane.setLeftAnchor(text1, 705.);
                        }
                        default -> {}
                    }
                }
                default -> {}
            }
        }
        AnchorPane.setTopAnchor(time, 260.);
    }

    @Override
    public void changeConfiguration(Event event) {
        switch (event.getSource()) {
            case SimpleButton button when button == modeButton:
                if (flags[0]) {
                    modeButton.changePosition(1);
                    flags[0] = false;
                } else {
                    modeButton.changePosition(2);
                    flags[0] = true;
                }
                break;
            case SimpleButton button when button == conditionButton:
                if (flags[0]) {
                    conditionButton.changePosition(1);
                    flags[0] = false;
                } else {
                    conditionButton.changePosition(2);
                    flags[0] = true;
                }
                break;
            case SimpleButton button when button == timeModeButton:
                if (flags[0]) {
                    timeModeButton.changePosition(1);
                    flags[0] = false;
                } else {
                    timeModeButton.changePosition(2);
                    flags[0] = true;
                }
            break;

            case null, default:
                break;
        }
    }
}

