package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    Line leftBusLine;
    @FXML
    Line rightBusLine;


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
                default -> {}
            }
        }
    }

    @Override
    public void changeConfiguration(Event event) {

    }
}