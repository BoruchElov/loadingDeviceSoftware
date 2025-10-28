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
    //ТЕКСТОВЫЕ ПОЛЯ
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
    //текстовые поля для амперметра и вольтметра
    @FXML
    SimpleTextField Ampermetr;
    @FXML
    SimpleTextField Voltmetr;
    //Текстовое поле для вывода времени
    @FXML
    SimpleTextField timeOutput;
    //Текстовое поле для ввода времени
    @FXML
    SimpleTextField timeInput;

    //КАРТИНКИ
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
    //Картинки ампер и вольт метров
    @FXML
    SimpleImageView PicAmpermetr;
    @FXML
    SimpleImageView PicVoltmetr;


    //КНОПКИ С КАРТИНКОЙ
    //Кнопки контактов контакторов
    @FXML
    ButtonWithPicture contactOneButton;
    @FXML
    ButtonWithPicture contactTwoButton;

    //КРУЖКИ
    //Кружки состояния контактов
    @FXML
    Circle contactOne;
    @FXML
    Circle contactTwo;
    // Кружки модулей
    @FXML
    Circle CiModuleA1;
    @FXML
    Circle CiModuleB1;
    @FXML
    Circle CiModuleC1;
    @FXML
    Circle CiModuleA2;
    @FXML
    Circle CiModuleB2;
    @FXML
    Circle CiModuleC2;

    //кнопки справа
    @FXML
    SimpleButton modeButton;
    @FXML
    SimpleButton conditionButton;
    @FXML
    Text time;

    //Текстовые поля для формы
    //Текст над модулями
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
    //Текст фаз
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
    //Текст справа
    @FXML
    Text feedingWinding;
    @FXML
    Text faultLocation;
    @FXML
    Text brokenPhases;
    //Текст для времени
    @FXML
    Text timerText;
    //Текст ам и вм
    @FXML
    Text amperText;
    @FXML
    Text voltText;


    @FXML
    public void initialize() {
        super.initialize();
        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                //НАСТРОЙКИ КНОПОК
                case SimpleButton button when button == modeButton || button == conditionButton -> {
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
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "t/сраб", "контакт"}, FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button, 385.);
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
                        || textField == phaseARAngle || textField == phaseBRAngle || textField == phaseCRAngle || textField == timeInput
                        || textField == timeOutput || textField == Ampermetr || textField == Voltmetr-> {
                    // Переменные для настройки размеров
                    Double Y1 = 314.;           //Высота первого уровня текстовых полей
                    Double Y2 = Y1+60;          //Второй уровень
                    Double Y3 = Y2+60;          //Третий уровень

                    Double X1 = 10.;            //Отступ по горизонтали для текстовых полей
                    Double X2 = X1 + 85;
                    Double X3 = 780.;
                    Double X4 = X3 + 85;

                    switch (textField) {
                        //Текстовые поля модулей слева
                        case SimpleTextField tf1 when tf1 == phaseALCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseALAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBLCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBLAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCLCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCLAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
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
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBRCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBRAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCRCurrent -> {
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCRAngle -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        //Амперметр и вольтметр
                        case SimpleTextField tf1 when tf1 == Ampermetr -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, 590.);
                            AnchorPane.setLeftAnchor(tf1, 560.);
                            tf1.setEditable(false);
                        }
                        case SimpleTextField tf1 when tf1 == Voltmetr -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, 590.);
                            AnchorPane.setLeftAnchor(tf1, 760.);
                            tf1.setEditable(false);
                        }
                        //Настройка внешнего вида и расположения кнопки вывода времени отключения
                        case SimpleTextField tf1 when tf1 == timeOutput -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1,  177.);
                            AnchorPane.setLeftAnchor(tf1, 750.);
                            tf1.setEditable(false);
                        }
                        //Настройка внешнего вида и расположения кнопки ввода времени отключения
                        case SimpleTextField tf1 when tf1 == timeInput -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, 560.);
                            AnchorPane.setLeftAnchor(tf1, 1005.);
                            tf1.setPrefSize(200., 67.);
                        }
                        default -> {
                        }
                    }
                }
                //НАСТРОЙКА ИЗОБРАЖЕНИЙ
                case SimpleImageView imageView when imageView == moduleA1 || imageView == moduleB1 || imageView == moduleC1
                        || imageView == moduleA2 || imageView == moduleB2 || imageView == moduleC2 ||  imageView == PicAmpermetr
                        ||  imageView == PicVoltmetr-> {
                    // Переменные для настройки размеров
                    Double k = 110.;            //Размер картинки инверторов

                    Double Y1 = 240.;           //Высота первого уровня текстовых полей
                    Double Y2 = 330.;           //Второй уровень
                    Double Y3 = 420.;           //Третий уровень

                    Double X1 = 350.;           //Отступ по горизонтали для текстовых полей
                    Double X2 = 490.;

                    switch (imageView) {
                        //Изображение инвертора
                        case SimpleImageView iv1 when iv1 == moduleA1 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y1);
                            AnchorPane.setLeftAnchor(imageView, X1);
                        }
                        case SimpleImageView iv1 when iv1 == moduleB1 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y2);
                            AnchorPane.setLeftAnchor(imageView, X1 );
                        }
                        case SimpleImageView iv1 when iv1 == moduleC1 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y3);
                            AnchorPane.setLeftAnchor(imageView, X1);
                        }
                        case SimpleImageView iv1 when iv1 == moduleA2 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                                    new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y1);
                            AnchorPane.setLeftAnchor(imageView, X2);
                        }
                        case SimpleImageView iv1 when iv1 == moduleB2 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE}, new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y2 );
                            AnchorPane.setLeftAnchor(imageView, X2 );
                        }
                        case SimpleImageView iv1 when iv1 == moduleC2 -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE}, new double[][]{{k, k}});
                            AnchorPane.setTopAnchor(imageView, Y3);
                            AnchorPane.setLeftAnchor(imageView, X2);
                        }
                        //Картинки вольт и ампер метров
                        case SimpleImageView iv1 when iv1 == PicAmpermetr -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.AMPERMETR}, new double[][]{{60., 60.}});
                            AnchorPane.setTopAnchor(imageView, 585.);
                            AnchorPane.setLeftAnchor(imageView, 680.);
                        }
                        case SimpleImageView iv1 when iv1 == PicVoltmetr -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.VOLTMETR}, new double[][]{{60., 60.}});
                            AnchorPane.setTopAnchor(imageView, 585.);
                            AnchorPane.setLeftAnchor(imageView, 480.);
                        }
                        default -> {}
                    }
                }
                // НАСТРОЙКА КРУЖКОВ
                case Circle c when c == contactOne || c == contactTwo || c == CiModuleA1 || c == CiModuleB1 || c == CiModuleC1
                        || c == CiModuleA2 || c == CiModuleB2 || c == CiModuleC2-> {
                    c.setRadius(10.);
                    c.getStyleClass().add("circles");
                    Double Y1 = 285.;           //Первый уровень
                    Double Y2 = 375.;           //Второй уровень
                    Double Y3 = 465.;           //Третий уровень

                    Double X1 = 195.;           //Отступ по горизонтали для кружочков Модуль 1
                    Double X2 = 615.;           //Модуль 2

                    switch (c) {
                        //кружки контактора
                        case Circle c1 when c1 == contactOne -> {
                            AnchorPane.setTopAnchor(c1, 189.);
                            AnchorPane.setLeftAnchor(c1, 165.);
                        }
                        case Circle c1 when c1 == contactTwo -> {
                            AnchorPane.setTopAnchor(c1, 189.);
                            AnchorPane.setLeftAnchor(c1, 265.);
                        }
                        //кружки модулей
                        case Circle c1 when c1 == CiModuleA1 -> {
                            AnchorPane.setTopAnchor(c1, Y1);
                            AnchorPane.setLeftAnchor(c1, X1);
                        }
                        case Circle c1 when c1 == CiModuleB1 -> {
                            AnchorPane.setTopAnchor(c1, Y2);
                            AnchorPane.setLeftAnchor(c1, X1);
                        }
                        case Circle c1 when c1 == CiModuleC1 -> {
                            AnchorPane.setTopAnchor(c1, Y3);
                            AnchorPane.setLeftAnchor(c1, X1);
                        }
                        case Circle c1 when c1 == CiModuleA2 -> {
                            AnchorPane.setTopAnchor(c1, Y1);
                            AnchorPane.setLeftAnchor(c1, X2);
                        }
                        case Circle c1 when c1 == CiModuleB2 -> {
                            AnchorPane.setTopAnchor(c1, Y2);
                            AnchorPane.setLeftAnchor(c1, X2);
                        }
                        case Circle c1 when c1 == CiModuleC2 -> {
                            AnchorPane.setTopAnchor(c1, Y3);
                            AnchorPane.setLeftAnchor(c1, X2);
                        }
                        default -> {}
                    }
                }
                // НАСТРОЙКА КНОПОК КОНТАКТОРА
                case ButtonWithPicture button when button == contactOneButton || button == contactTwoButton -> {
                    button.setup(new ImageView(), ButtonWithPicture.ButtonSizes.SMALL, ButtonWithPicture.ImagViewSizes.SMALLEST,
                            new String[]{"contacts-imageview", "contacts-imageview", "contacts-imageview"},
                            new Image[]{null, ApplicationConstants.OPENED_CONTACT, ApplicationConstants.CLOSED_CONTACT});
                    button.setOnAction(this::changeConfiguration);
                    switch (button) {
                        case ButtonWithPicture button1 when button1 == contactOneButton -> {        //Кнопка контактора 1
                            AnchorPane.setTopAnchor(button1, 177.);
                            AnchorPane.setLeftAnchor(button1, 200.);
                        }
                        case ButtonWithPicture button1 when button1 == contactTwoButton -> {        //Контактор 2
                            AnchorPane.setTopAnchor(button1, 177.);
                            AnchorPane.setLeftAnchor(button1, 300.);
                        }

                        default -> {}
                    }
                }
                //ТЕКСТОВЫЕ ПОЛЯ НА ФОРМЕ
                case Text text when text == contacts || text == one || text == two || text == currentOne ||
                        text == currentTwo || text == phaseOne || text == phaseTwo || text == aOne ||
                        text == aTwo || text == bOne || text == bTwo || text == cOne || text == cTwo ||
                        text == timerText || text == amperText || text == voltText -> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                    text.setFill(Color.BLACK);

                    Double n = 605.;
                    Double k = 110.;

                    Double Y1 = 285.;           //Первый уровень
                    Double Y2 = 375.;           //Второй уровень
                    Double Y3 = 465.;           //Третий уровень

                    Double X1 = 230.;           //Отступ по горизонтали для текста Модуль 1
                    Double X2 = 650.;           //Модуль 2

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
                            AnchorPane.setLeftAnchor(text1, 35.);
                        }
                        case Text text1 when text1 == currentTwo -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 800.);
                        }
                        case Text text1 when text1 == phaseOne -> {
                            text1.setText("fi, град");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 90.);
                        }
                        case Text text1 when text1 == phaseTwo -> {
                            text1.setText("fi, град");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 855.);
                        }//Текст для модулей
                        case Text text1 when text1 == aOne -> {
                            text1.setText("МОДУЛЬ A1");
                            AnchorPane.setTopAnchor(text1, Y1);
                            AnchorPane.setLeftAnchor(text1, X1);
                        }
                        case Text text1 when text1 == bOne -> {
                            text1.setText("МОДУЛЬ B1");
                            AnchorPane.setTopAnchor(text1, Y2);
                            AnchorPane.setLeftAnchor(text1, X1);
                        }
                        case Text text1 when text1 == cOne -> {
                            text1.setText("МОДУЛЬ C1");
                            AnchorPane.setTopAnchor(text1, Y3);
                            AnchorPane.setLeftAnchor(text1, X1);
                        }
                        case Text text1 when text1 == aTwo -> {
                            text1.setText("МОДУЛЬ A2");
                            AnchorPane.setTopAnchor(text1, Y1);
                            AnchorPane.setLeftAnchor(text1, X2);
                        }
                        case Text text1 when text1 == bTwo -> {
                            text1.setText("МОДУЛЬ B2");
                            AnchorPane.setTopAnchor(text1, Y2);
                            AnchorPane.setLeftAnchor(text1, X2);
                        }
                        case Text text1 when text1 == cTwo -> {
                            text1.setText("МОДУЛЬ C2");
                            AnchorPane.setTopAnchor(text1, Y3);
                            AnchorPane.setLeftAnchor(text1, X2);
                        }
                        //Окно tсраб.
                        case Text text1 when text1 == timerText -> {
                            text1.setText("tсраб.");
                            AnchorPane.setTopAnchor(text1, 190.);
                            AnchorPane.setLeftAnchor(text1, 690.);
                        }
                        //текст для АМ и ВМ
                        case Text text1 when text1 == amperText -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 550.);
                            AnchorPane.setLeftAnchor(text1, 495.);
                        }
                        case Text text1 when text1 == voltText -> {
                            text1.setText("U, В");
                            AnchorPane.setTopAnchor(text1, 550.);
                            AnchorPane.setLeftAnchor(text1, 690.);
                        }
                        default -> {}
                    }
                }
                //Текст для меню справа
                case Text text when text == feedingWinding || text == faultLocation || text == brokenPhases -> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    switch (text) {

                        case Text text1 when text1 == feedingWinding -> {
                            text1.setText("Режим");
                            AnchorPane.setTopAnchor(text1, 160.);
                            AnchorPane.setLeftAnchor(text1, 1060.);

                        }
                        case Text text1 when text1 == faultLocation -> {
                            text1.setText("Условия отключения");
                            AnchorPane.setTopAnchor(text1, 335.);
                            AnchorPane.setLeftAnchor(text1, 975.);

                        }
                        case Text text1 when text1 == brokenPhases -> {
                            text1.setText("Время, с");
                            AnchorPane.setTopAnchor(text1, 505.);
                            AnchorPane.setLeftAnchor(text1, 1045.);
                        }
                        default -> {}
                    }
                }
                default -> {}
            }
        }
        AnchorPane.setTopAnchor(time, 260.);
    }
    //Функция для отображения активности элементов на форме
    @Override
    public void changeConfiguration(Event event) {
        switch (event.getSource()) {
            //Изменение кнопки "Режим"
            case SimpleButton button when button == modeButton:
                fun_change_contact(button);
                break;
            case SimpleButton button when button == conditionButton:
                fun_change_contact(button);
                break;
                //Управление контактами
            case ButtonWithPicture button when button == contactOneButton:
                if (flags[1]) {
                    button.changePosition(2);
                    flags[1] = false;
                } else {
                    button.changePosition(1);
                    flags[1] = true;
                }
                break;
            case ButtonWithPicture button when button == contactTwoButton:
                if (flags[2]) {
                    button.changePosition(2);
                    flags[2] = false;
                } else {
                    button.changePosition(1);
                    flags[2] = true;
                }
                break;
            case null, default:
                break;
        }
    }

    //Функция по смене состояния контактов NC/NO
    public void fun_change_contact(SimpleButton button) {
        if (flags[0]) {
            button.changePosition(1);
            flags[0] = false;
        } else {
            button.changePosition(2);
            flags[0] = true;
        }
    }


}

