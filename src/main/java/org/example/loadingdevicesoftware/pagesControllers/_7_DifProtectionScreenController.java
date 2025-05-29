package org.example.loadingdevicesoftware.pagesControllers;

import javafx.collections.ObservableList;
import javafx.event.Event;
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

public class _7_DifProtectionScreenController extends ScreensController implements Configurable {

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

    @FXML
    public void initialize() {
        super.initialize();
        for (Node node : anchorPane.getChildren()) {
            switch (node) {
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
                    AnchorPane.setLeftAnchor(line, 195.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseBOneLine -> {
                    line.setStartX(0.);
                    line.setEndX(85.);
                    AnchorPane.setTopAnchor(line, 400.);
                    AnchorPane.setLeftAnchor(line, 195.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseCOneLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 455.);
                    AnchorPane.setLeftAnchor(line, 195.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseATwoLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 345.);
                    AnchorPane.setLeftAnchor(line, 635.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseBTwoLine -> {
                    line.setStartX(0.);
                    line.setEndX(85.);
                    AnchorPane.setTopAnchor(line, 400.);
                    AnchorPane.setLeftAnchor(line, 650.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == phaseCTwoLine -> {
                    line.setStartX(0.);
                    line.setEndX(100.);
                    AnchorPane.setTopAnchor(line, 455.);
                    AnchorPane.setLeftAnchor(line, 635.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == leftBusLine -> {
                    line.setStartY(0.);
                    line.setEndY(170.);
                    AnchorPane.setTopAnchor(line, 315.);
                    AnchorPane.setLeftAnchor(line, 195.);
                    line.getStyleClass().add("transformer");
                }
                case Line line when line == rightBusLine -> {
                    line.setStartY(0.);
                    line.setEndY(170.);
                    AnchorPane.setTopAnchor(line, 315.);
                    AnchorPane.setLeftAnchor(line, 735.);
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
                            AnchorPane.setLeftAnchor(text1, 115.);
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
                    imageView.setup(new String[]{"",""}, new Image[]{null, ApplicationConstants.GROUND},
                            new double[][]{{45.,55.},{45.,55.}});
                    AnchorPane.setTopAnchor(imageView, 457.);
                    AnchorPane.setLeftAnchor(imageView, 444.);
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
                        default -> {
                        }
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
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 20.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseAOneAngle -> {
                            textField1.setup("0", SimpleTextField.Sizes.SMALL);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 120.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBOneCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 20.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBOneAngle -> {
                            textField1.setup("0", SimpleTextField.Sizes.SMALL);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 120.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCOneCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 20.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCOneAngle -> {
                            textField1.setup("0", SimpleTextField.Sizes.SMALL);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 120.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseATwoCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 832.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseATwoAngle -> {
                            textField1.setup("0", SimpleTextField.Sizes.SMALL);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 310.);
                            AnchorPane.setLeftAnchor(textField1, 760.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBTwoCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 832.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseBTwoAngle -> {
                            textField1.setup("0", SimpleTextField.Sizes.SMALL);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 372.);
                            AnchorPane.setLeftAnchor(textField1, 760.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCTwoCurrent -> {
                            textField1.setup("0", SimpleTextField.Sizes.MEDIUM);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 832.);
                        }
                        case SimpleTextField textField1 when textField1 == phaseCTwoAngle -> {
                            textField1.setup("0", SimpleTextField.Sizes.SMALL);
                            textField1.setActualStatus(Changeable.Status.LOCKED);
                            AnchorPane.setTopAnchor(textField1, 434.);
                            AnchorPane.setLeftAnchor(textField1, 760.);
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
        restoreState();
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
            case null, default:
                phaseAOneAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseBOneAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseCOneAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseATwoAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseBTwoAngle.setActualStatus(Changeable.Status.LOCKED);
                phaseCTwoAngle.setActualStatus(Changeable.Status.LOCKED);
                break;
        }
    }
}