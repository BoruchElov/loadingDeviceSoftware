package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class _7_DifProtectionScenarioController extends ScreensController implements Configurable {

    @FXML
    Circle windingOne;
    @FXML
    Circle windingTwo;
    @FXML
    Line phaseAOneLine;
    @FXML
    Line phaseBOneLine;
    @FXML
    Line phaseCOneLine;
    @FXML
    Line phaseATwoLine;
    @FXML
    Line phaseBTwoLine;
    @FXML
    Line phaseCTwoLine;
    @FXML
    Line leftBusLine;
    @FXML
    Line rightBusLine;
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
    ButtonWithPicture contactOneButton;
    @FXML
    ButtonWithPicture contactTwoButton;
    @FXML
    Circle contactOneOne;
    @FXML
    Circle contactTwoOne;
    @FXML
    SimpleTextField phaseAOneCurrent;
    @FXML
    SimpleTextField phaseAOneAngle;
    @FXML
    SimpleTextField phaseBOneCurrent;
    @FXML
    SimpleTextField phaseBOneAngle;
    @FXML
    SimpleTextField phaseCOneCurrent;
    @FXML
    SimpleTextField phaseCOneAngle;
    @FXML
    SimpleTextField phaseATwoCurrent;
    @FXML
    SimpleTextField phaseATwoAngle;
    @FXML
    SimpleTextField phaseBTwoCurrent;
    @FXML
    SimpleTextField phaseBTwoAngle;
    @FXML
    SimpleTextField phaseCTwoCurrent;
    @FXML
    SimpleTextField phaseCTwoAngle;
    @FXML
    SimpleButton feedingWindingButton;
    @FXML
    SimpleButton faultLocationButton;
    @FXML
    SimpleButton phaseAButton;
    @FXML
    SimpleButton phaseBButton;
    @FXML
    SimpleButton phaseCButton;
    @FXML
    SimpleButton groundButton;
    @FXML
    SimpleButton connectionTypeOne;
    @FXML
    SimpleButton groupTypeOne;
    @FXML
    SimpleButton connectionTypeTwo;
    @FXML
    SimpleButton groupTypeTwo;
    @FXML
    SimpleImageView phaseAOneArrow;
    @FXML
    SimpleImageView phaseBOneArrow;
    @FXML
    SimpleImageView phaseCOneArrow;
    @FXML
    SimpleImageView phaseATwoArrow;
    @FXML
    SimpleImageView phaseBTwoArrow;
    @FXML
    SimpleImageView phaseCTwoArrow;
    @FXML
    SimpleImageView ground;
    @FXML
    SimpleImageView lightning;

    private int[] options = new int[]{0, 0, 0, 0};

    @FXML
    public void initialize() {
        super.initialize();

        nodesToCheck = new ArrayList<>(List.of(new Node[]{contactOneButton, contactTwoButton, connectionTypeOne, groupTypeOne,
                connectionTypeTwo, groupTypeTwo, feedingWindingButton, faultLocationButton, phaseAOneCurrent,
                phaseBOneCurrent, phaseCOneCurrent, phaseATwoCurrent, phaseAButton, phaseBButton, phaseCButton,
                groundButton, phaseBTwoCurrent, phaseCTwoCurrent,phaseAOneAngle,phaseBOneAngle,phaseCOneAngle,
                phaseATwoAngle, phaseBTwoAngle,phaseCTwoAngle,objectTextField,nameTextField}));

        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case SimpleButton button when button == connectionTypeOne || button == connectionTypeTwo ||
                        button == groupTypeOne || button == groupTypeTwo -> {
                    button.setOnAction(this::changeConfiguration);
                    switch (button) {
                        case SimpleButton button1 when button1 == connectionTypeOne -> {
                            button1.setup(new String[]{"button-group", "button-group", "button-group"},
                                    new String[]{"", "d", "Y"},
                                    FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                            AnchorPane.setTopAnchor(button1, 377.);
                            AnchorPane.setLeftAnchor(button1, 305.);
                        }
                        case SimpleButton button1 when button1 == connectionTypeTwo -> {
                            button1.setup(new String[]{"button-group", "button-group", "button-group"},
                                    new String[]{"", "d", "Y"},
                                    FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                            AnchorPane.setTopAnchor(button1, 377.);
                            AnchorPane.setLeftAnchor(button1, 500.);
                        }
                        case SimpleButton button1 when button1 == groupTypeOne -> {
                            button1.setup(new String[]{"button-group", "button-group", "button-group", "button-group",
                                            "button-group", "button-group", "button-group", "button-group", "button-group",
                                            "button-group", "button-group", "button-group", "button-group"},
                                    new String[]{"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"},
                                    FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                            AnchorPane.setTopAnchor(button1, 377.);
                            AnchorPane.setLeftAnchor(button1, 380.);
                        }
                        case SimpleButton button1 when button1 == groupTypeTwo -> {
                            button1.setup(new String[]{"button-group", "button-group", "button-group", "button-group",
                                            "button-group", "button-group", "button-group", "button-group", "button-group",
                                            "button-group", "button-group", "button-group", "button-group"},
                                    new String[]{"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"},
                                    FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                            AnchorPane.setTopAnchor(button1, 377.);
                            AnchorPane.setLeftAnchor(button1, 575.);
                        }
                        default -> {
                        }
                    }
                }
                case Circle c when c == windingOne -> {
                    AnchorPane.setTopAnchor(c, 300.);
                    AnchorPane.setLeftAnchor(c, 280.);
                    c.setRadius(100.);
                    c.getStyleClass().add("transformer");
                }
                case Circle c when c == windingTwo -> {
                    AnchorPane.setTopAnchor(c, 300.);
                    AnchorPane.setLeftAnchor(c, 450.);
                    c.setRadius(100.);
                    c.getStyleClass().add("transformer");
                }
                case Line line when line == phaseAOneLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 345.);
                    AnchorPane.setLeftAnchor(line, 193.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseBOneLine -> {
                    line.setStartX(0.);
                    line.setEndX(85.);
                    AnchorPane.setTopAnchor(line, 400.);
                    AnchorPane.setLeftAnchor(line, 193.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseCOneLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 455.);
                    AnchorPane.setLeftAnchor(line, 193.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseATwoLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 345.);
                    AnchorPane.setLeftAnchor(line, 636.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseBTwoLine -> {
                    line.setStartX(0.);
                    line.setEndX(85.);
                    AnchorPane.setTopAnchor(line, 400.);
                    AnchorPane.setLeftAnchor(line, 651.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseCTwoLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 455.);
                    AnchorPane.setLeftAnchor(line, 636.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == leftBusLine -> {
                    line.setStartY(0.);
                    line.setEndY(170.);
                    AnchorPane.setTopAnchor(line, 315.);
                    AnchorPane.setLeftAnchor(line, 193.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == rightBusLine -> {
                    line.setStartY(0.);
                    line.setEndY(170.);
                    AnchorPane.setTopAnchor(line, 315.);
                    AnchorPane.setLeftAnchor(line, 737.);
                    line.getStyleClass().add("transformer");
                }
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
                            AnchorPane.setLeftAnchor(text1, 855.);
                        }
                        case Text text1 when text1 == phaseOne -> {
                            text1.setText("fi, град");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 111.);
                        }
                        case Text text1 when text1 == phaseTwo -> {
                            text1.setText("fi, град");
                            AnchorPane.setTopAnchor(text1, 280.);
                            AnchorPane.setLeftAnchor(text1, 755.);
                        }
                        case Text text1 when text1 == aOne -> {
                            text1.setText("A1");
                            AnchorPane.setTopAnchor(text1, 320.);
                            AnchorPane.setLeftAnchor(text1, 210.);
                        }
                        case Text text1 when text1 == aTwo -> {
                            text1.setText("A2");
                            AnchorPane.setTopAnchor(text1, 320.);
                            AnchorPane.setLeftAnchor(text1, 705.);
                        }
                        case Text text1 when text1 == bOne -> {
                            text1.setText("B1");
                            AnchorPane.setTopAnchor(text1, 375.);
                            AnchorPane.setLeftAnchor(text1, 210.);
                        }
                        case Text text1 when text1 == bTwo -> {
                            text1.setText("B2");
                            AnchorPane.setTopAnchor(text1, 375.);
                            AnchorPane.setLeftAnchor(text1, 705.);
                        }
                        case Text text1 when text1 == cOne -> {
                            text1.setText("C1");
                            AnchorPane.setTopAnchor(text1, 430.);
                            AnchorPane.setLeftAnchor(text1, 210.);
                        }
                        case Text text1 when text1 == cTwo -> {
                            text1.setText("C2");
                            AnchorPane.setTopAnchor(text1, 430.);
                            AnchorPane.setLeftAnchor(text1, 705.);
                        }
                        default -> {
                        }
                    }
                }
                case SimpleImageView imageView when imageView == ground -> {
                    imageView.setup(new String[]{"", ""}, new Image[]{null, ApplicationConstants.GROUND},
                            new double[][]{{45., 55.}, {45., 55.}});
                    AnchorPane.setTopAnchor(imageView, 457.);
                    AnchorPane.setLeftAnchor(imageView, 444.);
                }
                case SimpleImageView imageView when imageView == lightning -> {
                    imageView.setup(new String[]{"", "", ""}, new Image[]{null, ApplicationConstants.LIGHTNING,
                            ApplicationConstants.LIGHTNING_RED}, new double[][]{{35., 70.}, {35., 70.}, {35., 70.}});
                }
                case SimpleImageView imageView when imageView == phaseAOneArrow || imageView == phaseBOneArrow
                        || imageView == phaseCOneArrow || imageView == phaseATwoArrow || imageView == phaseBTwoArrow
                        || imageView == phaseCTwoArrow -> {
                    imageView.setup(new String[]{"", "", ""}, new Image[]{null, ApplicationConstants.ARROW_LEFT,
                            ApplicationConstants.ARROW_RIGHT}, new double[][]{{40., 20.}, {40., 20.}, {40., 20.}});
                    AnchorPane.setLeftAnchor(phaseAOneArrow, 235.);
                    AnchorPane.setTopAnchor(phaseAOneArrow, 320.);
                    AnchorPane.setLeftAnchor(phaseBOneArrow, 235.);
                    AnchorPane.setTopAnchor(phaseBOneArrow, 375.);
                    AnchorPane.setLeftAnchor(phaseCOneArrow, 235.);
                    AnchorPane.setTopAnchor(phaseCOneArrow, 430.);
                    AnchorPane.setLeftAnchor(phaseATwoArrow, 657.);
                    AnchorPane.setTopAnchor(phaseATwoArrow, 320.);
                    AnchorPane.setLeftAnchor(phaseBTwoArrow, 657.);
                    AnchorPane.setTopAnchor(phaseBTwoArrow, 375.);
                    AnchorPane.setLeftAnchor(phaseCTwoArrow, 657.);
                    AnchorPane.setTopAnchor(phaseCTwoArrow, 430.);
                }
                case Text text when text == feedingWinding || text == faultLocation || text == brokenPhases -> {
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    switch (text) {
                        case Text text1 when text1 == feedingWinding -> {
                            text1.setText("Питающая обмотка");
                            AnchorPane.setTopAnchor(text1, 155.);
                            AnchorPane.setLeftAnchor(text1, 977.);
                        }
                        case Text text1 when text1 == faultLocation -> {
                            text1.setText("Место повреждения");
                            AnchorPane.setTopAnchor(text1, 328.);
                            AnchorPane.setLeftAnchor(text1, 970.);
                        }
                        case Text text1 when text1 == brokenPhases -> {
                            text1.setText("Повреждённые фазы");
                            AnchorPane.setTopAnchor(text1, 501.);
                            AnchorPane.setLeftAnchor(text1, 968.);
                        }
                        default -> {}
                    }
                }
                case Circle c when c == contactOneOne || c == contactTwoOne -> {
                    c.setRadius(10.);
                    c.getStyleClass().add("circles");
                    switch (c) {
                        case Circle c1 when c1 == contactOneOne -> {
                            AnchorPane.setTopAnchor(c1, 189.);
                            AnchorPane.setLeftAnchor(c1, 165.);
                        }
                        case Circle c1 when c1 == contactTwoOne -> {
                            AnchorPane.setTopAnchor(c1, 189.);
                            AnchorPane.setLeftAnchor(c1, 265.);
                        }
                        default -> {
                        }
                    }
                }
                case SimpleTextField textField when textField == phaseAOneCurrent || textField == phaseAOneAngle ||
                        textField == phaseBOneCurrent || textField == phaseBOneAngle || textField == phaseCOneCurrent ||
                        textField == phaseCOneAngle || textField == phaseATwoCurrent || textField == phaseATwoAngle ||
                        textField == phaseBTwoCurrent || textField == phaseBTwoAngle || textField == phaseCTwoCurrent ||
                        textField == phaseCTwoAngle -> {
                    textField.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                    textField.setAlignment(Pos.CENTER);
                    switch (textField) {
                        case SimpleTextField textField1 when textField1 == phaseAOneCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 20.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseAOneAngle -> {
                            textField1.setLimits(-360,360, SimpleTextField.numberOfDecimals.INT);
                            textField1.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 105.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBOneCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 20.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBOneAngle -> {
                            textField1.setLimits(-360,360, SimpleTextField.numberOfDecimals.INT);
                            textField1.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 105.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCOneCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 20.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCOneAngle -> {
                            textField1.setLimits(-360,360, SimpleTextField.numberOfDecimals.INT);
                            textField1.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 105.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseATwoCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 832.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseATwoAngle -> {
                            textField1.setLimits(-360,360, SimpleTextField.numberOfDecimals.INT);
                            textField1.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 750.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBTwoCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 832.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBTwoAngle -> {
                            textField1.setLimits(-360,360, SimpleTextField.numberOfDecimals.INT);
                            textField1.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 750.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCTwoCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 832.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCTwoAngle -> {
                            textField1.setLimits(-360,360, SimpleTextField.numberOfDecimals.INT);
                            textField1.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 750.);
                        }
                        default -> {
                        }
                    }
                }
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
                        default -> {
                        }
                    }
                }

                case SimpleButton button when button == feedingWindingButton || button == faultLocationButton ||
                        button == phaseAButton || button == phaseBButton || button == phaseCButton ||
                        button == groundButton -> {
                    switch (button) {
                        case SimpleButton button1 when button1 == feedingWindingButton -> {
                            button1.setOnAction(this::changeConfiguration);
                            button1.setup(new String[]{"dif-protection-button-one", "dif-protection-button-one",
                                            "dif-protection-button-one"}, new String[]{"", "I", "II"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button1, 215.);
                            AnchorPane.setLeftAnchor(button1, 1005.);
                        }
                        case SimpleButton button1 when button1 == faultLocationButton -> {
                            button1.setOnAction(this::changeConfiguration);
                            button1.setup(new String[]{"dif-protection-button-one", "dif-protection-button-one",
                                            "dif-protection-button-one"}, new String[]{"", "Внутреннее", "Внешнее"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button1, 390.);
                            AnchorPane.setLeftAnchor(button1, 1005.);
                        }
                        case SimpleButton button1 when button1 == phaseAButton -> {
                            button1.setOnAction(this::changeConfiguration);
                            button1.setup(new String[]{"dif-protection-button-two", "dif-protection-button-two-second"},
                                    new String[]{"A", "A"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button1, 565.);
                            AnchorPane.setLeftAnchor(button1, 955.);
                        }
                        case SimpleButton button1 when button1 == phaseBButton -> {
                            button1.setOnAction(this::changeConfiguration);
                            button1.setup(new String[]{"dif-protection-button-two", "dif-protection-button-two-second"},
                                    new String[]{"B", "B"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button1, 565.);
                            AnchorPane.setLeftAnchor(button1, 1035.);
                        }
                        case SimpleButton button1 when button1 == phaseCButton -> {
                            button1.setOnAction(this::changeConfiguration);
                            button1.setup(new String[]{"dif-protection-button-two", "dif-protection-button-two-second"},
                                    new String[]{"C", "C"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button1, 565.);
                            AnchorPane.setLeftAnchor(button1, 1115.);
                        }
                        case SimpleButton button1 when button1 == groundButton -> {
                            button1.setOnAction(this::changeConfiguration);
                            button1.setup(new String[]{"dif-protection-button-two", "dif-protection-button-two-second"},
                                    new String[]{"G", "G"},
                                    FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                            AnchorPane.setTopAnchor(button1, 565.);
                            AnchorPane.setLeftAnchor(button1, 1195.);
                        }

                        default -> {
                        }
                    }
                }
                default -> {
                }
            }
        }
        connectionTypeOne.toFront();
        groupTypeOne.toFront();
        connectionTypeTwo.toFront();
        groupTypeTwo.toFront();
        restoreState();
        changeFigure();

    }

    @Override
    public void restoreState() {
        super.restoreState();
        if (Buffer.isFlagForDifProtection()) {
            startButton.setOnAction(_ -> {
                clearButton.changePosition(0);
                clearButton.setText("СОХРАНИТЬ");
                clearButton.setOnAction(_ -> {
                    InterfaceElementsLogic.openFileManager();
                });
                startButton.changePosition(0);
                startButton.setText("ПРОДОЛЖИТЬ");
                menuButton.setActualStatus(Changeable.Status.LOCKED);
                startButton.setOnAction(_ -> {
                    clearButton.setText("ОЧИСТИТЬ");
                    clearButton.setOnAction(this::clearAll);
                    startButton.setText("ПУСК");
                    menuButton.setActualStatus(Changeable.Status.NORMAL);
                    startButton.setOnAction(event -> {
                        try {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
                            PagesBuffer.savePage(this);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
            });
            clearButton.changePosition(2);
            clearButton.setText("ОТМЕНА");
            startButton.changePosition(1);
        }
    }

    @Override
    public void addElementsListeners() {
        super.addElementsListeners();
        for (Node node : uncheckedNodes) {
            List<String> copy = new ArrayList<>(node.getStyleClass());
            node.getStyleClass().clear();
            node.getStyleClass().add("warning");
            node.getStyleClass().addAll(copy);
            if (node instanceof SimpleButton button && !listeners.containsKey(button)) {
                if (button.equals(phaseAButton) || button.equals(phaseBButton) || button.equals(phaseCButton)
                        || button.equals(groundButton)) {
                    EventHandler<ActionEvent> handler = event -> {

                        this.changeConfiguration(event);
                        SimpleButton[] phaseButtons = new SimpleButton[]{phaseAButton,phaseBButton,phaseCButton,
                                groundButton};
                        int n = 0;
                        for (SimpleButton button1 : phaseButtons) {
                            n += button1.getObjectPosition().getActualPosition() == 0 ? 0 : 1;
                        }
                        if (n > 2) {
                            for (SimpleButton button1 : phaseButtons) {
                                if (button1.getObjectPosition().getActualPosition() == 0) {
                                    node.getStyleClass().setAll("warning");
                                }
                            }
                        } else {
                            node.getStyleClass().setAll(copy);
                        }

                    };
                    button.setOnAction(handler);
                    listeners.put(node, handler);
                }
            }
        }
    }

    @Override
    public void changeConfiguration(Event event) {
        switch (event.getSource()) {
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
            case SimpleButton button when button == feedingWindingButton:
                if (flags[3]) {
                    button.changePosition(2);
                    flags[3] = false;
                } else {
                    button.changePosition(1);
                    flags[3] = true;
                }
                break;
            case SimpleButton button when button == faultLocationButton:
                if (flags[4]) {
                    button.changePosition(2);
                    flags[4] = false;
                } else {
                    button.changePosition(1);
                    flags[4] = true;
                }
                break;
            case SimpleButton button when button == phaseAButton:
                if (flags[5]) {
                    button.changePosition(0);
                    flags[5] = false;
                } else {
                    button.changePosition(1);
                    flags[5] = true;
                }
                break;
            case SimpleButton button when button == phaseBButton:
                if (flags[6]) {
                    button.changePosition(0);
                    flags[6] = false;
                } else {
                    button.changePosition(1);
                    flags[6] = true;
                }
                break;
            case SimpleButton button when button == phaseCButton:
                if (flags[7]) {
                    button.changePosition(0);
                    flags[7] = false;
                } else {
                    button.changePosition(1);
                    flags[7] = true;
                }
                break;
            case SimpleButton button when button == groundButton:
                if (flags[8]) {
                    button.changePosition(0);
                    ground.changePosition(0);
                    flags[8] = false;
                } else {
                    button.changePosition(1);
                    ground.changePosition(1);
                    flags[8] = true;
                }
                break;
            case SimpleButton button when button == connectionTypeOne:
                button.changePosition(options[0] > 1 ? options[0] = 1 : ++options[0]);
                break;
            case SimpleButton button when button == connectionTypeTwo:
                button.changePosition(options[1] > 1 ? options[1] = 1 : ++options[1]);
                break;
            case SimpleButton button when button == groupTypeOne:
                button.changePosition(options[2] > 11 ? options[2] = 1 : ++options[2]);
                break;
            case SimpleButton button when button == groupTypeTwo:
                button.changePosition(options[3] > 11 ? options[3] = 1 : ++options[3]);
                break;
            case null, default:
                phaseAOneAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseAOneAngle.setText("");
                phaseBOneAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseBOneAngle.setText("");
                phaseCOneAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseCOneAngle.setText("");
                phaseATwoAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseATwoAngle.setText("");
                phaseBTwoAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseBTwoAngle.setText("");
                phaseCTwoAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseCTwoAngle.setText("");
                break;
        }
        changeFigure();

        if (feedingWindingButton.getObjectPosition().getActualPosition() != 0 && faultLocationButton.
                getObjectPosition().getActualPosition() != 0 && connectionTypeOne.getObjectPosition().getActualPosition() != 0
                && groupTypeOne.getObjectPosition().getActualPosition() != 0 && connectionTypeTwo.getObjectPosition().getActualPosition() != 0
                && groupTypeTwo.getObjectPosition().getActualPosition() != 0) {
            String[] buffer = PhasesAnalyzer.getPhases(formCode());
            phaseAOneAngle.setText(buffer[0]);
            phaseBOneAngle.setText(buffer[1]);
            phaseCOneAngle.setText(buffer[2]);
            phaseATwoAngle.setText(buffer[3]);
            phaseBTwoAngle.setText(buffer[4]);
            phaseCTwoAngle.setText(buffer[5]);
        }
    }

    private void changeFigure() {
        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case SimpleButton button when button == faultLocationButton:
                    switch (button.getObjectPosition().getActualPosition()) {
                        case 0:
                            lightning.changePosition(0);
                            phaseAOneArrow.changePosition(0);
                            phaseBOneArrow.changePosition(0);
                            phaseCOneArrow.changePosition(0);
                            phaseATwoArrow.changePosition(0);
                            phaseBTwoArrow.changePosition(0);
                            phaseCTwoArrow.changePosition(0);
                            break;
                        case 1:
                            lightning.changePosition(1);
                            AnchorPane.setTopAnchor(lightning, 245.);
                            AnchorPane.setLeftAnchor(lightning, 455.);

                            phaseAOneArrow.changePosition(phaseAButton.getObjectPosition().getActualPosition() > 0 ? 2 : 0);
                            phaseBOneArrow.changePosition(phaseBButton.getObjectPosition().getActualPosition() > 0 ? 2 : 0);
                            phaseCOneArrow.changePosition(phaseCButton.getObjectPosition().getActualPosition() > 0 ? 2 : 0);
                            phaseATwoArrow.changePosition(phaseAButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseBTwoArrow.changePosition(phaseBButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseCTwoArrow.changePosition(phaseCButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            break;
                        case 2:
                            lightning.changePosition(1);
                            AnchorPane.setTopAnchor(lightning, 245.);
                            AnchorPane.setLeftAnchor(lightning, 185.);

                            phaseAOneArrow.changePosition(phaseAButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseBOneArrow.changePosition(phaseBButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseCOneArrow.changePosition(phaseCButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseATwoArrow.changePosition(phaseAButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseBTwoArrow.changePosition(phaseBButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            phaseCTwoArrow.changePosition(phaseCButton.getObjectPosition().getActualPosition() > 0 ? 1 : 0);
                            break;
                    }
                    break;
                case SimpleButton button when button == feedingWindingButton:
                    windingOne.getStyleClass().clear();
                    windingTwo.getStyleClass().clear();
                    switch (button.getObjectPosition().getActualPosition()) {
                        case 0:
                            windingOne.getStyleClass().add("transformer");
                            windingTwo.getStyleClass().add("transformer");
                            break;
                        case 1:
                            windingOne.getStyleClass().add("transformer-green");
                            windingTwo.getStyleClass().add("transformer");
                            break;
                        case 2:
                            windingOne.getStyleClass().add("transformer");
                            windingTwo.getStyleClass().add("transformer-green");
                            break;
                    }
                    break;
                case SimpleButton button when button == phaseAButton:
                    phaseAOneLine.getStyleClass().clear();
                    phaseATwoLine.getStyleClass().clear();
                    switch (button.getObjectPosition().getActualPosition()) {
                        case 0:
                            phaseAOneLine.getStyleClass().add("transformer");
                            phaseATwoLine.getStyleClass().add("transformer");
                            break;
                        case 1:
                            phaseAOneLine.getStyleClass().add("transformer-red");
                            phaseATwoLine.getStyleClass().add("transformer-red");
                            break;
                    }
                    break;
                case SimpleButton button when button == phaseBButton:
                    phaseBOneLine.getStyleClass().clear();
                    phaseBTwoLine.getStyleClass().clear();
                    switch (button.getObjectPosition().getActualPosition()) {
                        case 0:
                            phaseBOneLine.getStyleClass().add("transformer");
                            phaseBTwoLine.getStyleClass().add("transformer");
                            break;
                        case 1:
                            phaseBOneLine.getStyleClass().add("transformer-red");
                            phaseBTwoLine.getStyleClass().add("transformer-red");
                            break;
                    }
                    break;
                case SimpleButton button when button == phaseCButton:
                    phaseCOneLine.getStyleClass().clear();
                    phaseCTwoLine.getStyleClass().clear();
                    switch (button.getObjectPosition().getActualPosition()) {
                        case 0:
                            phaseCOneLine.getStyleClass().add("transformer");
                            phaseCTwoLine.getStyleClass().add("transformer");
                            break;
                        case 1:
                            phaseCOneLine.getStyleClass().add("transformer-red");
                            phaseCTwoLine.getStyleClass().add("transformer-red");
                            break;
                    }
                    break;
                case null, default:
                    break;
            }
        }
        switch (feedingWindingButton.getObjectPosition().getActualPosition()) {
            case 0:
            default:
                break;
            case 1:
                windingOne.toFront();
                break;
            case 2:
                windingTwo.toFront();
                break;
        }
        connectionTypeOne.toFront();
        groupTypeOne.toFront();
        connectionTypeTwo.toFront();
        groupTypeTwo.toFront();
    }

    private int[] formCode() {
        int elementZero = connectionTypeOne.getObjectPosition().getActualPosition();
        int elementOne = groupTypeOne.getObjectPosition().getActualPosition();
        int elementTwo = connectionTypeTwo.getObjectPosition().getActualPosition();
        int elementThree = groupTypeTwo.getObjectPosition().getActualPosition();
        int elementFour = phaseAButton.getObjectPosition().getActualPosition();
        int elementFive = phaseBButton.getObjectPosition().getActualPosition();
        int elementSix = phaseCButton.getObjectPosition().getActualPosition();
        int elementSeven = ground.getObjectPosition().getActualPosition();
        int elementEight = feedingWindingButton.getObjectPosition().getActualPosition();
        int elementNine = faultLocationButton.getObjectPosition().getActualPosition();
        return new int[]{elementZero, elementOne, elementTwo, elementThree, elementFour,
                elementFive, elementSix, elementSeven, elementEight, elementNine};
    }

}