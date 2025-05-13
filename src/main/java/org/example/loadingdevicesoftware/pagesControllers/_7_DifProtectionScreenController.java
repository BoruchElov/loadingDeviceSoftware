package org.example.loadingdevicesoftware.pagesControllers;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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
    public void initialize() {
        super.initialize();

        windingOne.setRadius(100.);
        windingTwo.setRadius(100.);

        AnchorPane.setTopAnchor(windingOne, 300.);
        AnchorPane.setTopAnchor(windingTwo, 300.);
        AnchorPane.setLeftAnchor(windingOne, 280.);
        AnchorPane.setLeftAnchor(windingTwo, 450.);
        windingOne.getStyleClass().add("transformer");
        windingTwo.getStyleClass().add("transformer");

        phaseAOneLine.setStartX(0.);
        phaseAOneLine.setEndX(100.);
        AnchorPane.setTopAnchor(phaseAOneLine, 345.);
        AnchorPane.setLeftAnchor(phaseAOneLine, 195.);
        phaseAOneLine.getStyleClass().add("transformer");

        phaseBOneLine.setStartX(0.);
        phaseBOneLine.setEndX(85.);
        AnchorPane.setTopAnchor(phaseBOneLine, 400.);
        AnchorPane.setLeftAnchor(phaseBOneLine, 195.);
        phaseBOneLine.getStyleClass().add("transformer");

        phaseCOneLine.setStartX(0.);
        phaseCOneLine.setEndX(100.);
        AnchorPane.setTopAnchor(phaseCOneLine, 455.);
        AnchorPane.setLeftAnchor(phaseCOneLine, 195.);
        phaseCOneLine.getStyleClass().add("transformer");

        phaseATwoLine.setStartX(0.);
        phaseATwoLine.setEndX(100.);
        AnchorPane.setTopAnchor(phaseATwoLine, 345.);
        AnchorPane.setLeftAnchor(phaseATwoLine, 635.);
        phaseATwoLine.getStyleClass().add("transformer");

        phaseBTwoLine.setStartX(0.);
        phaseBTwoLine.setEndX(85.);
        AnchorPane.setTopAnchor(phaseBTwoLine, 400.);
        AnchorPane.setLeftAnchor(phaseBTwoLine, 650.);
        phaseBTwoLine.getStyleClass().add("transformer");

        phaseCTwoLine.setStartX(0.);
        phaseCTwoLine.setEndX(100.);
        AnchorPane.setTopAnchor(phaseCTwoLine, 455.);
        AnchorPane.setLeftAnchor(phaseCTwoLine, 635.);
        phaseCTwoLine.getStyleClass().add("transformer");

    }

    @Override
    public void changeConfiguration(Event event) {

    }
}