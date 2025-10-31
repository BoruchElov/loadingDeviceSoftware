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
    //Текстовые поля для ввода частоты
    @FXML
    SimpleTextField frequencyInput;


    //КАРТИНКИ
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
    //Кнопки модулей
    @FXML
    ButtonWithPicture moduleA1Button;
    @FXML
    ButtonWithPicture moduleB1Button;
    @FXML
    ButtonWithPicture moduleC1Button;
    @FXML
    ButtonWithPicture moduleA2Button;
    @FXML
    ButtonWithPicture moduleB2Button;
    @FXML
    ButtonWithPicture moduleC2Button;

    //КРУЖКИ
    //Кружки состояния контактов
    @FXML
    Circle contactOne;
    @FXML
    Circle contactTwo;

    //кнопки справа
    @FXML
    SimpleButton conditionButton;
    @FXML
    SimpleButton dryWetButton;


    //Текстовые поля для формы
    @FXML
    Text time;
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
    Text dryWetContText;
    @FXML
    Text shutUpConditionText;
    @FXML
    Text CurOutputTimeText;
    //Текст для времени
    @FXML
    Text timerText;
    //Текст ввода частоты
    @FXML
    Text frequencyText;
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
                case SimpleButton button when  button == conditionButton || button == dryWetButton -> {
                    button.setOnAction(this::changeConfiguration);
                    switch (button) {
                        //Настройка внешнего вида и расположения кнопки условия отключения
                        case SimpleButton bt1 when bt1 == conditionButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "t/сраб", "контакт"}, FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button, 225.);
                            AnchorPane.setLeftAnchor(button, 1005.);
                        }
                        //Кнопка выбора сухого или мокрого контакта
                        case SimpleButton bt1 when bt1 == dryWetButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "сухой", "мокрый"}, FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button, 400.);
                            AnchorPane.setLeftAnchor(button, 1005.);
                        }
                        default ->{}
                    }
                }
                //НАСТРОЙКА ТЕКСТОВЫХ ПОЛЕЙ
                case SimpleTextField textField when textField == phaseALCurrent || textField == phaseBLCurrent || textField == phaseCLCurrent
                        || textField == phaseALAngle || textField == phaseBLAngle || textField == phaseCLAngle
                        || textField == phaseARCurrent || textField == phaseBRCurrent || textField == phaseCRCurrent
                        || textField == phaseARAngle || textField == phaseBRAngle || textField == phaseCRAngle || textField == timeInput
                        || textField == timeOutput || textField == Ampermetr || textField == Voltmetr || textField == frequencyInput
                        -> {
                    // Переменные для настройки размеров
                    Double Y1 = 314.;           //Высота первого уровня текстовых полей
                    Double Y2 = Y1+60;          //Второй уровень
                    Double Y3 = Y2+60;          //Третий уровень

                    Double X1 = 10.;            //Отступ по горизонтали для текстовых полей
                    Double X2 = X1 + 85;
                    Double X3 = 780.;
                    Double X4 = X3 + 85;
                    textField.setEditable(false);   //Блокировка всех текстовых полей
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
                            AnchorPane.setLeftAnchor(tf1, 485.);
                        }
                        case SimpleTextField tf1 when tf1 == Voltmetr -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, 590.);
                            AnchorPane.setLeftAnchor(tf1, 650.);
                                }
                        //Настройка внешнего вида и расположения кнопки вывода времени отключения
                        case SimpleTextField tf1 when tf1 == timeOutput -> {
                            tf1.setup("", SimpleTextField.Sizes.SMALL);
                            AnchorPane.setTopAnchor(tf1,  590.);
                            AnchorPane.setLeftAnchor(tf1, 825.);
                        } // Ввод частоты модулей
                        case SimpleTextField tf1 when tf1 == frequencyInput -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(tf1, 177.);
                            AnchorPane.setLeftAnchor(tf1, 670.);
                            tf1.setEditable(true);
                        }
                        //Настройка внешнего вида и расположения кнопки ввода времени отключения
                        case SimpleTextField tf1 when tf1 == timeInput -> {
                            tf1.setup("0.01-3600", SimpleTextField.Sizes.LARGE);
                            AnchorPane.setTopAnchor(tf1, 580.);
                            AnchorPane.setLeftAnchor(tf1, 1005.);
                            tf1.setPrefSize(200., 67.);
                            tf1.setEditable(true);
                        }
                        default -> {
                        }
                    }
                }
                //НАСТРОЙКА ИЗОБРАЖЕНИЙ
                case SimpleImageView imageView when imageView == PicAmpermetr ||  imageView == PicVoltmetr-> {
                    switch (imageView) {
                        //Картинки вольт и ампер метров
                        case SimpleImageView iv1 when iv1 == PicAmpermetr -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.AMPERMETR}, new double[][]{{60., 60.}});
                            AnchorPane.setTopAnchor(imageView, 585.);
                            AnchorPane.setLeftAnchor(imageView, 400.);
                        }
                        case SimpleImageView iv1 when iv1 == PicVoltmetr -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.VOLTMETR}, new double[][]{{60., 60.}});
                            AnchorPane.setTopAnchor(imageView, 585.);
                            AnchorPane.setLeftAnchor(imageView, 580.);
                        }
                        default -> {}
                    }
                }
                // НАСТРОЙКА КРУЖКОВ
                case Circle c when c == contactOne || c == contactTwo -> {
                    c.setRadius(10.);
                    c.getStyleClass().add("circles");
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
                        default -> {}
                    }
                }
                // НАСТРОЙКА КНОПОК КОНТАКТОРА
                case ButtonWithPicture button when button == contactOneButton || button == contactTwoButton  -> {
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

                case ButtonWithPicture button when button == moduleA1Button || button == moduleB1Button || button == moduleC1Button
                        || button == moduleA2Button || button == moduleB2Button || button == moduleC2Button -> {
                    // Переменные для настройки размеров
                    Double k = 110.;            //Размер картинки инверторов

                    Double Y1 = 260.;           //Высота первого уровня текстовых полей
                    Double Y2 = 345.;           //Второй уровень
                    Double Y3 = 430.;           //Третий уровень

                    Double X1 = 360.;           //Отступ по горизонтали для текстовых полей
                    Double X2 = 510.;
                    switch (button) {
                        case ButtonWithPicture bt1 when bt1 == moduleA1Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            AnchorPane.setTopAnchor(bt1, Y1);
                            AnchorPane.setLeftAnchor(bt1, X1);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleB1Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            AnchorPane.setTopAnchor(bt1, Y2);
                            AnchorPane.setLeftAnchor(bt1, X1);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleC1Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            AnchorPane.setTopAnchor(bt1, Y3);
                            AnchorPane.setLeftAnchor(bt1, X1);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleA2Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            AnchorPane.setTopAnchor(bt1, Y1);
                            AnchorPane.setLeftAnchor(bt1, X2);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleB2Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            AnchorPane.setTopAnchor(bt1, Y2);
                            AnchorPane.setLeftAnchor(bt1, X2);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleC2Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            AnchorPane.setTopAnchor(bt1, Y3);
                            AnchorPane.setLeftAnchor(bt1, X2);
                        }
                        default -> {}
                    }
                }

                //ТЕКСТОВЫЕ ПОЛЯ НА ФОРМЕ
                case Text text when text == contacts || text == one || text == two || text == currentOne ||
                        text == currentTwo || text == phaseOne || text == phaseTwo || text == aOne ||
                        text == aTwo || text == bOne || text == bTwo || text == cOne || text == cTwo ||
                        text == timerText || text == amperText || text == voltText || text == frequencyText
                        -> {
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
                            AnchorPane.setTopAnchor(text1, 555.);
                            AnchorPane.setLeftAnchor(text1, 825.);
                        }
                        //Окно frequency.
                        case Text text1 when text1 == frequencyText -> {
                            text1.setText("частота");
                            AnchorPane.setTopAnchor(text1, 190.);
                            AnchorPane.setLeftAnchor(text1, 590.);
                        }
                        //текст для АМ и ВМ
                        case Text text1 when text1 == amperText -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 555.);
                            AnchorPane.setLeftAnchor(text1, 410.);
                        }
                        case Text text1 when text1 == voltText -> {
                            text1.setText("U, В");
                            AnchorPane.setTopAnchor(text1, 555.);
                            AnchorPane.setLeftAnchor(text1, 590.);
                        }
                        default -> {}
                    }
                }
                //Текст для меню справа
                case Text text when text == shutUpConditionText || text == CurOutputTimeText
                        || text == dryWetContText-> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    switch (text) {
                        case Text text1 when text1 == shutUpConditionText -> {
                            text1.setText("Условия отключения");
                            AnchorPane.setTopAnchor(text1, 180.);
                            AnchorPane.setLeftAnchor(text1, 980.);
                        }
                        case Text text1 when text1 == dryWetContText -> {
                            text1.setText("Тип контактов");
                            AnchorPane.setTopAnchor(text1, 350.);
                            AnchorPane.setLeftAnchor(text1, 1025.);
                        }
                        case Text text1 when text1 == CurOutputTimeText -> {
                            text1.setText("Время выдачи, с");
                            AnchorPane.setTopAnchor(text1, 525.);
                            AnchorPane.setLeftAnchor(text1, 990.);
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
            //Изменение кнопок справа в меню
            case SimpleButton button when button ==  conditionButton || button == dryWetButton:
                funChangeButtonMenu(button);
                break;
            //Управление контактами
            case ButtonWithPicture button when button == contactOneButton || button == contactTwoButton:
                funChangeModeContact(button);
                break;
            case ButtonWithPicture button when button == moduleA1Button || button == moduleB1Button || button == moduleC1Button
                    || button == moduleA2Button || button == moduleB2Button || button == moduleC2Button :
                funOnOffModule(button);
                break;
            case SimpleTextField stf when stf == phaseALCurrent || stf == phaseALAngle || stf == phaseBLCurrent
                        || stf == phaseBLAngle || stf == phaseCLCurrent || stf == phaseCLAngle || stf == phaseARCurrent
                        || stf == phaseARAngle || stf == phaseBRCurrent || stf == phaseBRAngle || stf == phaseCRCurrent
                        || stf == phaseCRAngle:
                funUnlockTextField();
                break;
                //Определение поведения кнопки ПУСК
            case null, default:
                break;
        }
    }

    //Функция по смене состояния контактов NC/NO
    public void funChangeButtonMenu(SimpleButton button) {
        if (flags[0]) {
            button.changePosition(1);
            flags[0] = false;
        } else {
            button.changePosition(2);
            flags[0] = true;
        }
    }
    //Функция выбора и активации инвертора
    public void funChangeModeContact(ButtonWithPicture button) {
        if (flags[1]) {
            button.changePosition(0);
            flags[1] = false;
        } else {
            button.changePosition(1);
            flags[1] = true;
        }
    }
//    Функция по активации модуля
    public void funOnOffModule(ButtonWithPicture button) {
        int index = 0;
        SimpleTextField stf1 = null;
        SimpleTextField stf2 = null;
        switch(button) {
            case ButtonWithPicture button1 when button1 == moduleA1Button:
                index = 2;
                stf1 = phaseALCurrent;
                stf2 = phaseALAngle;
                break;
            case ButtonWithPicture button1 when button1 == moduleB1Button:
                index = 3;
                stf1 = phaseBLCurrent;
                stf2 = phaseBLAngle;
                break;
            case ButtonWithPicture button1 when button1 == moduleC1Button:
                index = 4;
                stf1 = phaseCLCurrent;
                stf2 = phaseCLAngle;
                break;
            case ButtonWithPicture button1 when button1 == moduleA2Button:
                index = 5;
                stf1 = phaseARCurrent;
                stf2 = phaseARAngle;
                break;
            case ButtonWithPicture button1 when button1 == moduleB2Button:
                index = 6;
                stf1 = phaseBRCurrent;
                stf2 = phaseBRAngle;
                break;
            case ButtonWithPicture button1 when button1 == moduleC2Button:
                index = 7;
                stf1 = phaseCRCurrent;
                stf2 = phaseCRAngle;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + button);
        }
        if (flags[index]) {
            button.changePosition(0);
            flags[index] = false;
            stf1.setEditable(false);
            stf2.setEditable(false);
            stf1.clear();
            stf2.clear();
        } else {
            button.changePosition(1);
            flags[index] = true;
            stf1.setEditable(true);
            stf2.setEditable(true);

        }
    }

    public void funUnlockTextField(){

    }
}

