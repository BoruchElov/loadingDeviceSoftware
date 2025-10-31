package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.example.loadingdevicesoftware.pagesControllers.*;

import java.util.ArrayList;

public class PagesBuffer {

    public static _1_TestOfSwitcher3XScreenController switcherPage;
    public static _2_TestOfStageProtection3XScreenController protectionPage;
    public static _3_TestOfMeasurementTransformerScreenController transformerPage;
    public static _4_DifProtectionScreenController difPage;
    public static _5_ComTradeScreenController comTradePage;
    public static _6_HandControlScreenController handControlPage;
    public static _9_DeBuggerScreenController deBugPage;

    public static String fxmlName;
    public static String controllerName;

    public static ArrayList<String> buffer = new ArrayList<>();


    public static void clear() {
        fxmlName = null;
        controllerName = null;
        if (buffer != null )buffer.clear();
    }

    public static void savePage (Object source) {
        if (source instanceof _1_TestOfSwitcher3XScreenController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _2_TestOfStageProtection3XScreenController controller) {
            protectionPage = (_2_TestOfStageProtection3XScreenController) source;
        }
        if (source instanceof _3_TestOfMeasurementTransformerScreenController controller) {
            transformerPage = (_3_TestOfMeasurementTransformerScreenController) source;
        }
        if (source instanceof _5_ComTradeScreenController controller) {
            comTradePage = (_5_ComTradeScreenController) source;
        }
        if (source instanceof _4_DifProtectionScreenController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _6_HandControlScreenController controller) {
            handControlPage = (_6_HandControlScreenController) source;
        }
        if (source instanceof _9_DeBuggerScreenController controller) {
            deBugPage = (_9_DeBuggerScreenController) source;
        }
        controllerName = source.getClass().getSimpleName();
        fxmlName = getProperName(controllerName);
    }

    public static ObservableList<Node> getChildren() {
        return switch (controllerName) {
            case "_1_TestOfSwitcher3XScreenController" -> switcherPage.getAnchorPane().getChildren();
            case "_4_DifProtectionScreenController" -> difPage.getAnchorPane().getChildren();
            case "_6_HandControlScreenController" -> handControlPage.getAnchorPane().getChildren();
            case null, default -> throw new IllegalStateException("Unexpected value: ");
        };
    }

    private static String getProperName(String className) {
        return switch (className) {
            case "_1_TestOfSwitcher3XScreenController" -> "1.TestOfSwitcher3X.fxml";
            case "_2_TestOfStageProtection3XScreenController" -> "2.TestOfStageProtection3X.fxml";
            case "_3_TestOfMeasurementTransformerScreenController" -> "3.TestOfMeasurementTransformer.fxml";
            case "_4_DifProtectionScreenController" -> "4.DifProtection.fxml";
            case "_5_ComTradeScreenController" -> "5.ComTrade.fxml";
            case "_6_HandControlScreenController" -> "6.HandControl.fxml";
            case null, default -> throw new IllegalStateException("Unexpected value: ");
        };
    }

    private static void saveStates(ObservableList<Node> list) {
        for (Node node : list) {
            if (node instanceof SimpleTextField textField) buffer.add(textField.getText());
            if (node instanceof SimpleButton button) buffer.add(String.valueOf(button.getObjectPosition().getActualPosition()));
            if (node instanceof ButtonWithPicture button) buffer.add(String.valueOf(button.getObjectPosition().getActualPosition()));
            if (node instanceof SimpleImageView imageView) buffer.add(String.valueOf(imageView.getObjectPosition().getActualPosition()));
        }
    }
}
