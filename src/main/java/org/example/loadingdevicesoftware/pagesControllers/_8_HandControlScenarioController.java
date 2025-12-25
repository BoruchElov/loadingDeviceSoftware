package org.example.loadingdevicesoftware.pagesControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.InverterParams;
import org.example.loadingdevicesoftware.communicationWithInverters.PollingManager;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class _8_HandControlScenarioController extends ScreensController implements Configurable {


    @FXML
    CheckBox symmetricalComponentsCheckBox;

    private HashMap<SimpleTextField, String> parametersBuffer = new HashMap<>();

    //Комбобокс
    @FXML
    SimpleComboBox<String> currentFormComboBox;
    @FXML
    Text currentFormText;
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
    SimpleTextField currentPhase;
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
    Text positiveSequenceCurrent;
    @FXML
    Text positiveSequencePhase;
    @FXML
    Text negativeSequenceCurrent;
    @FXML
    Text negativeSequencePhase;
    @FXML
    Text positiveSequenceCurrentTwo;
    @FXML
    Text positiveSequencePhaseTwo;
    @FXML
    Text negativeSequenceCurrentTwo;
    @FXML
    Text negativeSequencePhaseTwo;
    @FXML
    Text currentTwo;
    @FXML
    Text phaseOne;
    @FXML
    Text phaseTwo;
    @FXML
    Text symmetricalComponents;
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
    Text currentPhaseText;


    @FXML
    public void initialize() {
        super.initialize();

        symmetricalComponentsCheckBox.getStyleClass().add("check-box");
        AnchorPane.setTopAnchor(symmetricalComponentsCheckBox, 597.);
        AnchorPane.setLeftAnchor(symmetricalComponentsCheckBox, 435.);
        symmetricalComponentsCheckBox.setOnAction(event -> {
            checkBoxAction();
        });
        symmetricalComponents.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.NORMAL));
        symmetricalComponents.setText("УЧЁТ СИМ.СОС.");
        AnchorPane.setTopAnchor(symmetricalComponents, 565.);
        AnchorPane.setLeftAnchor(symmetricalComponents, 390.);

        nodesToCheck = new ArrayList<>(List.of(new Node[]{frequencyInput, timeInput, nameTextField, objectTextField,
                currentFormComboBox, phaseALCurrent, phaseALAngle}));

        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case SimpleComboBox combo when combo == currentFormComboBox -> {
                    currentFormComboBox.setup();
                    currentFormComboBox.getItems().add("ИМПУЛЬСНЫЙ");
                    currentFormComboBox.getItems().add("НАРАСТАЮЩИЙ");
                    AnchorPane.setTopAnchor(currentFormComboBox, 177.);
                    AnchorPane.setLeftAnchor(currentFormComboBox, 370.);
                }
                //НАСТРОЙКИ КНОПОК
                case SimpleButton button when button == conditionButton || button == dryWetButton -> {
                    button.setOnAction(this::changeConfiguration);
                    switch (button) {
                        //Настройка внешнего вида и расположения кнопки условия отключения
                        case SimpleButton bt1 when bt1 == conditionButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "t/сраб", "Контакт"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            bt1.setActualStatus(Changeable.Status.LOCKED);
                            bt1.changePosition(1);
                            AnchorPane.setTopAnchor(button, 225.);
                            AnchorPane.setLeftAnchor(button, 1005.);
                        }
                        //Кнопка выбора сухого или мокрого контакта
                        case SimpleButton bt1 when bt1 == dryWetButton -> {
                            bt1.setup(new String[]{"phase-button", "phase-button", "phase-button"}, new String[]{"", "Сухой", "Мокрый"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            bt1.setActualStatus(Changeable.Status.LOCKED);
                            bt1.changePosition(0);
                            AnchorPane.setTopAnchor(button, 400.);
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
                        || textField == timeOutput || textField == Ampermetr || textField == Voltmetr || textField == frequencyInput
                        || textField == currentPhase -> {
                    textField.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                    textField.setAlignment(Pos.CENTER);
                    // Переменные для настройки размеров
                    Double Y1 = 273.;           //Высота первого уровня текстовых полей
                    Double Y2 = Y1 + 90;          //Второй уровень
                    Double Y3 = Y2 + 90;          //Третий уровень

                    Double X1 = 50.;            //Отступ по горизонтали для текстовых полей
                    Double X2 = X1 + 110;
                    Double X3 = 720.;
                    Double X4 = X3 + 110;
                    textField.setEditable(false);   //Блокировка всех текстовых полей
                    switch (textField) {
                        //Текстовые поля модулей слева
                        case SimpleTextField tf1 when tf1 == phaseALCurrent -> {
                            tf1.setup("1.0", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseALAngle -> {
                            tf1.setLimits(-360, 360, SimpleTextField.numberOfDecimals.ONE);
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBLCurrent -> {
                            tf1.setup("1.0", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBLAngle -> {
                            tf1.setLimits(-360, 360, SimpleTextField.numberOfDecimals.ONE);
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCLCurrent -> {
                            tf1.setup("1.0", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X1);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCLAngle -> {
                            tf1.setLimits(-360, 360, SimpleTextField.numberOfDecimals.ONE);
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X2);
                        }
                        //Текстовые поля справа
                        case SimpleTextField tf1 when tf1 == phaseARCurrent -> {
                            tf1.setup("1.0", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseARAngle -> {
                            tf1.setLimits(-360, 360, SimpleTextField.numberOfDecimals.ONE);
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y1);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBRCurrent -> {
                            tf1.setup("1.0", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseBRAngle -> {
                            tf1.setLimits(-360, 360, SimpleTextField.numberOfDecimals.ONE);
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y2);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCRCurrent -> {
                            tf1.setup("1.0", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X3);
                        }
                        case SimpleTextField tf1 when tf1 == phaseCRAngle -> {
                            tf1.setLimits(-360, 360, SimpleTextField.numberOfDecimals.ONE);
                            tf1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, Y3);
                            AnchorPane.setLeftAnchor(tf1, X4);
                        }
                        //Амперметр и вольтметр
                        case SimpleTextField tf1 when tf1 == Ampermetr -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.ORDINARY);
                            AnchorPane.setTopAnchor(tf1, 520.);
                            AnchorPane.setLeftAnchor(tf1, 610.);
                        }
                        case SimpleTextField tf1 when tf1 == currentPhase -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.ORDINARY);
                            AnchorPane.setTopAnchor(tf1, 600.);
                            AnchorPane.setLeftAnchor(tf1, 610.);
                        }
                        case SimpleTextField tf1 when tf1 == Voltmetr -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.ORDINARY);
                            AnchorPane.setTopAnchor(tf1, 560.);
                            AnchorPane.setLeftAnchor(tf1, 805.);
                        }
                        //Настройка внешнего вида и расположения кнопки вывода времени отключения
                        case SimpleTextField tf1 when tf1 == timeOutput -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM_TWO, SimpleTextField.typeOfValue.ORDINARY);
                            AnchorPane.setTopAnchor(tf1, 177.);
                            AnchorPane.setLeftAnchor(tf1, 759.);
                        } // Ввод частоты модулей
                        case SimpleTextField tf1 when tf1 == frequencyInput -> {
                            tf1.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(tf1, 177.);
                            AnchorPane.setLeftAnchor(tf1, 640.);
                            tf1.setEditable(true);
                        }
                        //Настройка внешнего вида и расположения кнопки ввода времени отключения
                        case SimpleTextField tf1 when tf1 == timeInput -> {
                            tf1.setLimits(0.01, 3600., SimpleTextField.numberOfDecimals.THREE);
                            tf1.setup("0.01", SimpleTextField.Sizes.LARGE, SimpleTextField.typeOfValue.DIGIT);
                            tf1.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(tf1, 575.);
                            AnchorPane.setLeftAnchor(tf1, 1005.);
                            tf1.setPrefSize(200., 67.);
                            tf1.setEditable(true);
                        }
                        default -> {
                        }
                    }
                }
                //НАСТРОЙКА ИЗОБРАЖЕНИЙ
                case SimpleImageView imageView when imageView == PicAmpermetr || imageView == PicVoltmetr -> {
                    switch (imageView) {
                        //Картинки вольт и ампер метров
                        case SimpleImageView iv1 when iv1 == PicAmpermetr -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.AMPERMETR}, new double[][]{{60., 60.}});
                            AnchorPane.setTopAnchor(imageView, 555.);
                            AnchorPane.setLeftAnchor(imageView, 533.);
                        }
                        case SimpleImageView iv1 when iv1 == PicVoltmetr -> {
                            imageView.setup(new String[]{""}, new Image[]{ApplicationConstants.VOLTMETR}, new double[][]{{60., 60.}});
                            AnchorPane.setTopAnchor(imageView, 555.);
                            AnchorPane.setLeftAnchor(imageView, 730.);
                        }
                        default -> {
                        }
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
                        case ButtonWithPicture button1 when button1 == contactOneButton -> {        //Кнопка контактора 1
                            AnchorPane.setTopAnchor(button1, 177.);
                            AnchorPane.setLeftAnchor(button1, 200.);
                            button1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                int theFirstPosition = contactOneButton.getObjectPosition().getActualPosition();
                                if (theFirstPosition == 0) {
                                    contactOne.setFill(Color.TRANSPARENT);
                                } else if (theFirstPosition == 1) {
                                    contactOne.setFill(Color.web(ApplicationConstants.Green));
                                } else {
                                    contactOne.setFill(Color.web(ApplicationConstants.Red));
                                }
                                int theSecondPosition = contactTwoButton.getObjectPosition().getActualPosition();
                                if (theFirstPosition == 0 && theSecondPosition == 0) {
                                    conditionButton.changePosition(1);
                                    conditionButton.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(conditionButton);
                                    dryWetButton.changePosition(0);
                                    dryWetButton.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(dryWetButton);
                                } else {
                                    conditionButton.setActualStatus(Changeable.Status.NORMAL);
                                    if (!nodesToCheck.contains(conditionButton)) nodesToCheck.add(conditionButton);
                                    dryWetButton.setActualStatus(Changeable.Status.NORMAL);
                                    if (!nodesToCheck.contains(dryWetButton)) nodesToCheck.add(dryWetButton);
                                }
                            });
                        }
                        case ButtonWithPicture button1 when button1 == contactTwoButton -> {        //Контактор 2
                            AnchorPane.setTopAnchor(button1, 177.);
                            AnchorPane.setLeftAnchor(button1, 300.);
                            button1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                int theFirstPosition = contactOneButton.getObjectPosition().getActualPosition();
                                int theSecondPosition = contactTwoButton.getObjectPosition().getActualPosition();
                                if (theSecondPosition == 0) {
                                    contactTwo.setFill(Color.TRANSPARENT);
                                } else if (theSecondPosition == 1) {
                                    contactTwo.setFill(Color.web(ApplicationConstants.Green));
                                } else {
                                    contactTwo.setFill(Color.web(ApplicationConstants.Red));
                                }
                                if (theFirstPosition == 0 && theSecondPosition == 0) {
                                    conditionButton.changePosition(1);
                                    conditionButton.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(conditionButton);
                                    dryWetButton.changePosition(0);
                                    dryWetButton.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(dryWetButton);
                                } else {
                                    conditionButton.setActualStatus(Changeable.Status.NORMAL);
                                    if (!nodesToCheck.contains(conditionButton)) nodesToCheck.add(conditionButton);
                                    dryWetButton.setActualStatus(Changeable.Status.NORMAL);
                                    if (!nodesToCheck.contains(dryWetButton)) nodesToCheck.add(dryWetButton);
                                }
                            });
                        }
                        default -> {
                        }
                    }
                }

                case ButtonWithPicture button when button == moduleA1Button || button == moduleB1Button || button == moduleC1Button
                        || button == moduleA2Button || button == moduleB2Button || button == moduleC2Button -> {
                    // Переменные для настройки размеров
                    Double k = 110.;            //Размер картинки инверторов

                    Double Y1 = 260.;           //Высота первого уровня текстовых полей
                    Double Y2 = 345.;           //Второй уровень
                    Double Y3 = 430.;           //Третий уровень

                    Double X1 = 380.;           //Отступ по горизонтали для текстовых полей
                    Double X2 = 490.;
                    switch (button) {
                        case ButtonWithPicture bt1 when bt1 == moduleA1Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            bt1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                if (moduleA1Button.getObjectPosition().getActualPosition() == 0) {
                                    phaseALCurrent.clear();
                                    phaseALCurrent.setActualStatus(Changeable.Status.LOCKED);
                                    phaseALAngle.clear();
                                    phaseALAngle.setActualStatus(Changeable.Status.LOCKED);
                                } else {
                                    phaseALCurrent.setActualStatus(Changeable.Status.NORMAL);
                                    phaseALCurrent.setEditable(true);
                                    phaseALAngle.setActualStatus(Changeable.Status.NORMAL);
                                    phaseALAngle.setEditable(true);
                                }
                            });
                            AnchorPane.setTopAnchor(bt1, Y1);
                            AnchorPane.setLeftAnchor(bt1, X1);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleB1Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            bt1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                if (moduleB1Button.getObjectPosition().getActualPosition() == 0) {
                                    phaseBLCurrent.clear();
                                    phaseBLCurrent.setActualStatus(Changeable.Status.LOCKED);
                                    phaseBLAngle.clear();
                                    phaseBLAngle.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(phaseBLCurrent);
                                    nodesToCheck.remove(phaseBLAngle);
                                } else {
                                    phaseBLCurrent.setActualStatus(Changeable.Status.NORMAL);
                                    phaseBLCurrent.setEditable(true);
                                    if (!nodesToCheck.contains(phaseBLCurrent)) nodesToCheck.add(phaseBLCurrent);
                                    phaseBLAngle.setActualStatus(Changeable.Status.NORMAL);
                                    phaseBLAngle.setEditable(true);
                                    if (!nodesToCheck.contains(phaseBLAngle)) nodesToCheck.add(phaseBLAngle);

                                }
                            });
                            AnchorPane.setTopAnchor(bt1, Y2);
                            AnchorPane.setLeftAnchor(bt1, X1);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleC1Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            bt1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                if (moduleC1Button.getObjectPosition().getActualPosition() == 0) {
                                    phaseCLCurrent.clear();
                                    phaseCLCurrent.setActualStatus(Changeable.Status.LOCKED);
                                    phaseCLAngle.clear();
                                    phaseCLAngle.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(phaseCLCurrent);
                                    nodesToCheck.remove(phaseCLAngle);
                                } else {
                                    phaseCLCurrent.setActualStatus(Changeable.Status.NORMAL);
                                    phaseCLCurrent.setEditable(true);
                                    if (!nodesToCheck.contains(phaseCLCurrent)) nodesToCheck.add(phaseCLCurrent);
                                    phaseCLAngle.setActualStatus(Changeable.Status.NORMAL);
                                    phaseCLAngle.setEditable(true);
                                    if (!nodesToCheck.contains(phaseCLAngle)) nodesToCheck.add(phaseCLAngle);
                                }
                            });
                            AnchorPane.setTopAnchor(bt1, Y3);
                            AnchorPane.setLeftAnchor(bt1, X1);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleA2Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            bt1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                if (moduleA2Button.getObjectPosition().getActualPosition() == 0) {
                                    phaseARCurrent.clear();
                                    phaseARCurrent.setActualStatus(Changeable.Status.LOCKED);
                                    phaseARAngle.clear();
                                    phaseARAngle.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(phaseARCurrent);
                                    nodesToCheck.remove(phaseARAngle);
                                } else {
                                    phaseARCurrent.setActualStatus(Changeable.Status.NORMAL);
                                    phaseARCurrent.setEditable(true);
                                    if (!nodesToCheck.contains(phaseARCurrent)) nodesToCheck.add(phaseARCurrent);
                                    phaseARAngle.setActualStatus(Changeable.Status.NORMAL);
                                    phaseARAngle.setEditable(true);
                                    if (!nodesToCheck.contains(phaseARAngle)) nodesToCheck.add(phaseARAngle);
                                }
                            });
                            AnchorPane.setTopAnchor(bt1, Y1);
                            AnchorPane.setLeftAnchor(bt1, X2);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleB2Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            bt1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                if (moduleB2Button.getObjectPosition().getActualPosition() == 0) {
                                    phaseBRCurrent.clear();
                                    phaseBRCurrent.setActualStatus(Changeable.Status.LOCKED);
                                    phaseBRAngle.clear();
                                    phaseBRAngle.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(phaseBRCurrent);
                                    nodesToCheck.remove(phaseBRAngle);
                                } else {
                                    phaseBRCurrent.setActualStatus(Changeable.Status.NORMAL);
                                    phaseBRCurrent.setEditable(true);
                                    if (!nodesToCheck.contains(phaseBRCurrent)) nodesToCheck.add(phaseBRCurrent);
                                    phaseBRAngle.setActualStatus(Changeable.Status.NORMAL);
                                    phaseBRAngle.setEditable(true);
                                    if (!nodesToCheck.contains(phaseBRAngle)) nodesToCheck.add(phaseBRAngle);
                                }
                            });
                            AnchorPane.setTopAnchor(bt1, Y2);
                            AnchorPane.setLeftAnchor(bt1, X2);
                        }
                        case ButtonWithPicture bt1 when bt1 == moduleC2Button -> {
                            bt1.setup(new ImageView(), ButtonWithPicture.ButtonSizes.KEY_MODULE_SIZE, ButtonWithPicture.ImagViewSizes.KEY_MODULE_SIZE,
                                    new String[]{"button-module-off", "button-module-on"},
                                    new Image[]{ApplicationConstants.INVERTER_IMAGE, ApplicationConstants.INVERTER_IMAGE});
                            bt1.setOnAction(this::changeConfiguration);
                            bt1.getStyleClass().addListener((ListChangeListener<String>) change -> {
                                if (moduleC2Button.getObjectPosition().getActualPosition() == 0) {
                                    phaseCRCurrent.clear();
                                    phaseCRCurrent.setActualStatus(Changeable.Status.LOCKED);
                                    phaseCRAngle.clear();
                                    phaseCRAngle.setActualStatus(Changeable.Status.LOCKED);
                                    nodesToCheck.remove(phaseCRCurrent);
                                    nodesToCheck.remove(phaseCRAngle);
                                } else {
                                    phaseCRCurrent.setActualStatus(Changeable.Status.NORMAL);
                                    phaseCRCurrent.setEditable(true);
                                    if (!nodesToCheck.contains(phaseCRCurrent)) nodesToCheck.add(phaseCRCurrent);
                                    phaseCRAngle.setActualStatus(Changeable.Status.NORMAL);
                                    phaseCRAngle.setEditable(true);
                                    if (!nodesToCheck.contains(phaseCRAngle)) nodesToCheck.add(phaseCRAngle);
                                }
                            });
                            AnchorPane.setTopAnchor(bt1, Y3);
                            AnchorPane.setLeftAnchor(bt1, X2);
                        }
                        default -> {
                        }
                    }
                }

                //ТЕКСТОВЫЕ ПОЛЯ НА ФОРМЕ
                case Text text when text == contacts || text == one || text == two || text == currentOne ||
                        text == currentTwo || text == phaseOne || text == phaseTwo || text == aOne ||
                        text == aTwo || text == bOne || text == bTwo || text == cOne || text == cTwo ||
                        text == timerText || text == amperText || text == voltText || text == frequencyText
                        || text == currentFormText || text == currentPhaseText || text == positiveSequenceCurrent ||
                        text == positiveSequencePhase || text == negativeSequenceCurrent || text == negativeSequencePhase ||
                        text == positiveSequenceCurrentTwo || text == positiveSequencePhaseTwo || text == negativeSequenceCurrentTwo
                        || text == negativeSequencePhaseTwo -> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                    text.setFill(Color.BLACK);

                    Double n = 605.;
                    Double k = 110.;

                    Double Y1 = 292.;           //Первый уровень
                    Double Y2 = 380.;           //Второй уровень
                    Double Y3 = 465.;           //Третий уровень

                    Double X1 = 260.;           //Отступ по горизонтали для текста Модуль 1
                    Double X2 = 590.;           //Модуль 2

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
                        case Text text1 when text1 == currentFormText -> {
                            text1.setText("Вид тока");
                            AnchorPane.setTopAnchor(text1, 150.);
                            AnchorPane.setLeftAnchor(text1, 415.);
                        }
                        case Text text1 when text1 == currentOne -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 240.);
                            AnchorPane.setLeftAnchor(text1, 79.);
                        }
                        case Text text1 when text1 == currentTwo -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 240.);
                            AnchorPane.setLeftAnchor(text1, 758.);
                        }
                        case Text text1 when text == positiveSequenceCurrent -> {
                            text1.setText("");
                        }
                        case Text text1 when text == positiveSequencePhase -> {
                            text1.setText("");
                        }
                        case Text text1 when text == negativeSequenceCurrent -> {
                            text1.setText("");
                        }
                        case Text text1 when text == negativeSequencePhase -> {
                            text1.setText("");
                        }
                        case Text text1 when text == positiveSequenceCurrentTwo -> {
                            text1.setText("");
                        }
                        case Text text1 when text == positiveSequencePhaseTwo -> {
                            text1.setText("");
                        }
                        case Text text1 when text == negativeSequenceCurrentTwo -> {
                            text1.setText("");
                        }
                        case Text text1 when text == negativeSequencePhaseTwo -> {
                            text1.setText("");
                        }
                        case Text text1 when text1 == phaseOne -> {
                            text1.setText("φ, °");
                            AnchorPane.setTopAnchor(text1, 240.);
                            AnchorPane.setLeftAnchor(text1, 187.);
                        }
                        case Text text1 when text1 == phaseTwo -> {
                            text1.setText("φ, °");
                            AnchorPane.setTopAnchor(text1, 240.);
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
                            AnchorPane.setTopAnchor(text1, 150.);
                            AnchorPane.setLeftAnchor(text1, 807.);
                        }
                        //Окно frequency.
                        case Text text1 when text1 == frequencyText -> {
                            text1.setText("Частота");
                            AnchorPane.setTopAnchor(text1, 190.);
                            AnchorPane.setLeftAnchor(text1, 555.);
                        }
                        //текст для АМ и ВМ
                        case Text text1 when text1 == amperText -> {
                            text1.setText("I, A");
                            AnchorPane.setTopAnchor(text1, 495.);
                            AnchorPane.setLeftAnchor(text1, 645.);
                        }
                        case Text text1 when text1 == voltText -> {
                            text1.setText("U, В");
                            AnchorPane.setTopAnchor(text1, 530.);
                            AnchorPane.setLeftAnchor(text1, 835.);
                        }
                        case Text text1 when text1 == currentPhaseText -> {
                            text1.setText("φI, гр.");
                            AnchorPane.setTopAnchor(text1, 575.);
                            AnchorPane.setLeftAnchor(text1, 635.);
                        }
                        default -> {
                        }
                    }
                }
                //Текст для меню справа
                case Text text when text == shutUpConditionText || text == CurOutputTimeText
                        || text == dryWetContText -> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    switch (text) {
                        case Text text1 when text1 == shutUpConditionText -> {
                            text1.setText("Условия отключения");
                            text1.setWrappingWidth(160.);
                            text1.setTextAlignment(TextAlignment.CENTER);
                            AnchorPane.setTopAnchor(text1, 143.);
                            AnchorPane.setLeftAnchor(text1, 1025.);
                        }
                        case Text text1 when text1 == dryWetContText -> {
                            text1.setText("Тип контактов");
                            AnchorPane.setTopAnchor(text1, 337.);
                            AnchorPane.setLeftAnchor(text1, 1012.);
                        }
                        case Text text1 when text1 == CurOutputTimeText -> {
                            text1.setText("Время выдачи, с");
                            AnchorPane.setTopAnchor(text1, 512.);
                            AnchorPane.setLeftAnchor(text1, 1000.);
                        }
                        default -> {
                        }
                    }
                }
                default -> {
                }
            }
        }
        AnchorPane.setTopAnchor(time, 260.);
        restoreState();
    }

    private void checkBoxAction() {

        SimpleTextField[] currentFields = new SimpleTextField[]{phaseALCurrent, phaseBLCurrent, phaseCLCurrent,
                phaseARCurrent, phaseBRCurrent, phaseCRCurrent};
        SimpleTextField[] phaseFields = new SimpleTextField[]{phaseALAngle, phaseBLAngle, phaseCLAngle,
                phaseARAngle, phaseBRAngle, phaseCRAngle};

        if (symmetricalComponentsCheckBox.isSelected()) {
            //Настройка положения текстовых полей
            currentOne.setText("I0, A");
            AnchorPane.setTopAnchor(currentOne, 240.);
            AnchorPane.setLeftAnchor(currentOne, 79.);
            currentTwo.setText("I0, A");
            AnchorPane.setTopAnchor(currentTwo, 240.);
            AnchorPane.setLeftAnchor(currentTwo, 758.);
            phaseOne.setText("φ0, °");
            AnchorPane.setTopAnchor(phaseOne, 240.);
            AnchorPane.setLeftAnchor(phaseOne, 187.);
            phaseTwo.setText("φ0, °");
            AnchorPane.setTopAnchor(phaseTwo, 240.);
            AnchorPane.setLeftAnchor(phaseTwo, 855.);
            positiveSequenceCurrent.setText("I1, A");
            AnchorPane.setTopAnchor(positiveSequenceCurrent, 335.);
            AnchorPane.setLeftAnchor(positiveSequenceCurrent, 79.);
            positiveSequencePhase.setText("φ1, °");
            AnchorPane.setTopAnchor(positiveSequencePhase, 335.);
            AnchorPane.setLeftAnchor(positiveSequencePhase, 187.);
            negativeSequenceCurrent.setText("I2, A");
            AnchorPane.setTopAnchor(negativeSequenceCurrent, 425.);
            AnchorPane.setLeftAnchor(negativeSequenceCurrent, 79.);
            negativeSequencePhase.setText("φ2, °");
            AnchorPane.setTopAnchor(negativeSequencePhase, 425.);
            AnchorPane.setLeftAnchor(negativeSequencePhase, 187.);
            positiveSequenceCurrentTwo.setText("I1, A");
            AnchorPane.setTopAnchor(positiveSequenceCurrentTwo, 335.);
            AnchorPane.setLeftAnchor(positiveSequenceCurrentTwo, 758.);
            positiveSequencePhaseTwo.setText("φ1, °");
            AnchorPane.setTopAnchor(positiveSequencePhaseTwo, 335.);
            AnchorPane.setLeftAnchor(positiveSequencePhaseTwo, 855.);
            negativeSequenceCurrentTwo.setText("I2, A");
            AnchorPane.setTopAnchor(negativeSequenceCurrentTwo, 425.);
            AnchorPane.setLeftAnchor(negativeSequenceCurrentTwo, 758.);
            negativeSequencePhaseTwo.setText("φ2, °");
            AnchorPane.setTopAnchor(negativeSequencePhaseTwo, 425.);
            AnchorPane.setLeftAnchor(negativeSequencePhaseTwo, 855.);
            /// ////////////////////////////////////////////////////////////////
        } else {
            //Настройка положения текстовых полей
            currentOne.setText("I, A");
            AnchorPane.setTopAnchor(currentOne, 240.);
            AnchorPane.setLeftAnchor(currentOne, 79.);
            currentTwo.setText("I, A");
            AnchorPane.setTopAnchor(currentTwo, 240.);
            AnchorPane.setLeftAnchor(currentTwo, 758.);
            phaseOne.setText("φ, °");
            AnchorPane.setTopAnchor(phaseOne, 240.);
            AnchorPane.setLeftAnchor(phaseOne, 187.);
            phaseTwo.setText("φ, °");
            AnchorPane.setTopAnchor(phaseTwo, 240.);
            AnchorPane.setLeftAnchor(phaseTwo, 855.);
            positiveSequenceCurrent.setText("");
            positiveSequencePhase.setText("");
            negativeSequenceCurrent.setText("");
            negativeSequencePhase.setText("");
            positiveSequenceCurrentTwo.setText("");
            positiveSequencePhaseTwo.setText("");
            negativeSequenceCurrentTwo.setText("");
            negativeSequencePhaseTwo.setText("");
            /// ////////////////////////////////////////////////////////////////
        }
    }

    //Функция для отображения активности элементов на форме
    @Override
    public void changeConfiguration(Event event) {
        switch (event.getSource()) {
            //Изменение кнопок справа в меню
            case SimpleButton button when button == conditionButton || button == dryWetButton:
                funChangeButtonMenu(button);
                break;
            //Управление контактами
            case ButtonWithPicture button when button == contactOneButton || button == contactTwoButton:
                int i = button.getObjectPosition().getActualPosition();
                button.changePosition(i == 2 ? 0 : (i + 1));
                break;
            case ButtonWithPicture button when button == moduleA1Button || button == moduleB1Button || button == moduleC1Button
                    || button == moduleA2Button || button == moduleB2Button || button == moduleC2Button:
                int k = button.getObjectPosition().getActualPosition();
                button.changePosition(k == 1 ? 0 : (k + 1));
                break;
            //Определение поведения кнопки ПУСК
            case null, default:
                break;
        }
    }

    /**
     * Метод для реализации логики сценария. Переопределяет метод родительского класса ScreensController.
     */
    @Override
    public void launchScenario() {
        //Добавление логики из родительского метода
        super.launchScenario();
        parametersBuffer.clear();
        //Заполнение динамического массива исходных данных. Каждый элемент массива - строка с нужными параметрами сценария
        ButtonWithPicture[] buttons = new ButtonWithPicture[]{moduleA1Button, moduleB1Button, moduleC1Button, moduleA2Button,
                moduleB2Button, moduleC2Button};
        SimpleTextField[] currentFields = new SimpleTextField[]{phaseALCurrent, phaseBLCurrent, phaseCLCurrent,
                phaseARCurrent, phaseBRCurrent, phaseCRCurrent};
        SimpleTextField[] phaseFields = new SimpleTextField[]{phaseALAngle, phaseBLAngle, phaseCLAngle,
                phaseARAngle, phaseBRAngle, phaseCRAngle};
        ArrayList<String> scenarioParameters = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                parametersBuffer.put(currentFields[i], currentFields[i].getText());
                parametersBuffer.put(phaseFields[i], phaseFields[i].getText());
                String data = currentFields[i].getText();
                data += "," + phaseFields[i].getText();
                data += "," + timeInput.getText();
                data += "," + frequencyInput.getText();
                data += "," + (dryWetButton.getObjectPosition().getActualPosition() == 2 ? "1" : "0");
                data += "," + contactOneButton.getObjectPosition().getActualPosition();
                data += "," + contactTwoButton.getObjectPosition().getActualPosition();
                data += "," + (conditionButton.getObjectPosition().getActualPosition() == 2 ? "1" : "0");
                scenarioParameters.add(data);
            }
        }
        double timeout = Double.parseDouble(timeInput.getText());
        //
        setPageState(PageState.IN_PROCESS);
        //Запуск анимации на время выполнения сценария
        startBlinkingAnimation();
        //Асинхронный запуск сценария
        CompletableFuture<Boolean> resultFuture = currentFormComboBox.getSelectionModel().getSelectedIndex() != 0 ?
                ScenariosManager.handControlScenarioOne(scenarioParameters, timeout) :
                ScenariosManager.handControlScenarioOne(scenarioParameters, timeout);

        //Настройка отображения параметров
        setFieldsValues(true);
        //Действие по завершении работы сценария
        resultFuture.thenAccept(success -> {
            // Доступ к UI — только из FX Application Thread
            Platform.runLater(() -> {
                //Остановка анимации
                stopBlinkingAnimation();
                //Показ всплывающего окна с результатами сценария или ошибкой
                if (success) {
                    StringBuilder sb = new StringBuilder("Результаты сценария:\n");
                    ScenariosManager.getResponses().forEach((addr, arr) -> {
                        sb.append(addr.toStringInHexFormat()).append(" : ");
                        sb.append(String.join(", ", arr));
                        sb.append("\n");
                    });
                    String timeOne = ScenariosManager.getResponses().get(CheckingManager.getAvailableAddresses().getFirst())[1].substring(3, 8);
                    if (!timeOne.equals("0.000")) {
                        if (contactOneButton.getObjectPosition().getActualPosition() == 1) {
                            contactOneButton.changePosition(2);
                        } else {
                            contactOneButton.changePosition(1);
                        }
                    }
                    String timeTwo = ScenariosManager.getResponses().get(CheckingManager.getAvailableAddresses().getFirst())[2].substring(3, 8);
                    if (!timeTwo.equals("0.000")) {
                        if (contactTwoButton.getObjectPosition().getActualPosition() == 1) {
                            contactTwoButton.changePosition(2);
                        } else {
                            contactTwoButton.changePosition(1);
                        }
                    }
                    String timeResponse = timeOne + " | " + timeTwo;
                    timeOutput.setText(timeResponse);
                    InterfaceElementsLogic.showAlert(sb.toString(), InterfaceElementsLogic.Alert_Size.MEDIUM);
                    setPageState(PageState.WAITING_FOR_CHOICE);
                } else {
                    InterfaceElementsLogic.showAlert("Ошибка при выполнении сценария!", InterfaceElementsLogic.Alert_Size.SMALL);
                    setPageState(PageState.ALLOWED_TO_START);
                }
            });
        });
    }

    private void setFieldsValues(boolean isDataUpdating) {
        SimpleTextField[] currentFields = new SimpleTextField[]{phaseALCurrent, phaseBLCurrent, phaseCLCurrent,
                phaseARCurrent, phaseBRCurrent, phaseCRCurrent};
        SimpleTextField[] phaseFields = new SimpleTextField[]{phaseALAngle, phaseBLAngle, phaseCLAngle,
                phaseARAngle, phaseBRAngle, phaseCRAngle};
        ButtonWithPicture[] buttons = new ButtonWithPicture[]{moduleA1Button, moduleB1Button, moduleC1Button, moduleA2Button,
                moduleB2Button, moduleC2Button};
        int size = CheckingManager.getAvailableAddresses().size();
        ArrayList<SimpleTextField[]> modulesParameters = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                modulesParameters.add(new SimpleTextField[]{currentFields[i], phaseFields[i]});
            }
        }
        if (isDataUpdating) {
            ArrayList<InverterParams> inverters = new ArrayList<>();
            for (Address address : CheckingManager.getAvailableAddresses()) {
                inverters.add(PollingManager.getParams(address));
            }
            for (int i = 0; i < inverters.size(); i++) {
                if (inverters.get(i) == null) continue;
                modulesParameters.get(i)[0].setLimits(-1.0, 3500.0, SimpleTextField.numberOfDecimals.ONE);
                modulesParameters.get(i)[0].setEditable(false);
                modulesParameters.get(i)[0].textProperty().bind(inverters.get(i).currentRMSProperty());
                modulesParameters.get(i)[1].setEditable(false);
                modulesParameters.get(i)[1].textProperty().bind(inverters.get(i).currentPhaseProperty());
                System.out.println("Параметры обновляются");
            }
            Ampermetr.textProperty().bind(inverters.getFirst().amperemeterCurrentProperty());
            currentPhase.textProperty().bind(inverters.getFirst().amperemeterPhaseProperty());
            Voltmetr.textProperty().bind(inverters.getFirst().voltmeterVoltageProperty());
        } else {
            for (int i = 0; i < size; i++) {
                modulesParameters.get(i)[0].textProperty().unbind();
                modulesParameters.get(i)[0].setLimits(1.0, 3000.0, SimpleTextField.numberOfDecimals.ONE);
                modulesParameters.get(i)[0].clear();
                modulesParameters.get(i)[0].setText(parametersBuffer.get(modulesParameters.get(i)[0]));
                modulesParameters.get(i)[0].setEditable(true);
                modulesParameters.get(i)[1].setEditable(true);
                modulesParameters.get(i)[1].textProperty().unbind();
                modulesParameters.get(i)[1].clear();
                modulesParameters.get(i)[1].setText(parametersBuffer.get(modulesParameters.get(i)[1]));
            }
            Ampermetr.textProperty().unbind();
            Ampermetr.clear();
            currentPhase.textProperty().unbind();
            currentPhase.clear();
            Voltmetr.textProperty().unbind();
            Voltmetr.clear();
            timeOutput.clear();
        }
    }

    @Override
    public void additionalAction() {
        setFieldsValues(false);
    }

    @Override
    public void savePageParameters() {
        super.savePageParameters();
        ArrayList<Double> buffer = CheckingManager.getCurrents();
        ArrayList<Double> resistanceBuffer = CheckingManager.getResistanceCheckParameters();
        ButtonWithPicture[] buttons = new ButtonWithPicture[]{moduleA1Button, moduleB1Button, moduleC1Button, moduleA2Button,
                moduleB2Button, moduleC2Button};
        SimpleTextField[] currentsFields = new SimpleTextField[]{phaseALCurrent, phaseBLCurrent, phaseCLCurrent,
                phaseARCurrent, phaseBRCurrent, phaseCRCurrent};
        SimpleTextField[] phasesFields = new SimpleTextField[]{phaseALAngle, phaseBLAngle, phaseCLAngle,
                phaseARAngle, phaseBRAngle, phaseCRAngle};
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                buffer.add(Double.parseDouble(currentsFields[i].getText()));
                resistanceBuffer.add(Double.parseDouble(currentsFields[i].getText()));
                resistanceBuffer.add(Double.parseDouble(phasesFields[i].getText()));
                resistanceBuffer.add(Double.parseDouble(timeInput.getText()));
            }
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

    @Override
    public boolean isThereSomethingToCheck() {
        boolean flag = super.isThereSomethingToCheck();
        if (!flag) {
            CheckingManager.clearVariableAddresses();
            ArrayList<String> modules = getChosenModules();
            for (String module : modules) {
                CheckingManager.addVariableAddress(module);
            }
        }
        return flag;
    }

    private ArrayList<String> getChosenModules() {
        ArrayList<String> chosenModules = new ArrayList<>();
        String[] moduleNames = new String[]{"moduleA1", "moduleB1", "moduleC1", "moduleA2", "moduleB2", "moduleC2"};
        ButtonWithPicture[] buttons = new ButtonWithPicture[]{moduleA1Button, moduleB1Button, moduleC1Button,
                moduleA2Button, moduleB2Button, moduleC2Button};
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                chosenModules.add(moduleNames[i]);
            }
        }
        return chosenModules;
    }


    //Временное решение - мигание кружков для обозначения работы сценария.
    //TODO удалить после отладки
    private Timeline blinkingTimeline;

    private void startBlinkingAnimation() {
        ButtonWithPicture[] buttons = new ButtonWithPicture[]{moduleA1Button, moduleB1Button, moduleC1Button, moduleA2Button,
                moduleB2Button, moduleC2Button};
        blinkingTimeline = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                            buttons[i].getStyleClass().add("button-module-blink");
                        }
                    }
                }),
                new KeyFrame(Duration.millis(450), e -> {
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                            buttons[i].getStyleClass().remove("button-module-blink");
                        }
                    }
                })
        );
        blinkingTimeline.setCycleCount(Animation.INDEFINITE);
        blinkingTimeline.play();
    }

    private void stopBlinkingAnimation() {
        if (blinkingTimeline != null) {
            blinkingTimeline.stop();
            ButtonWithPicture[] buttons = new ButtonWithPicture[]{moduleA1Button, moduleB1Button, moduleC1Button, moduleA2Button,
                    moduleB2Button, moduleC2Button};
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].getObjectPosition().getActualPosition() != 0) {
                    buttons[i].getStyleClass().remove("button-module-blink");
                }
            }
        }
    }
    /// //////////////////////////////////////////////////
}

