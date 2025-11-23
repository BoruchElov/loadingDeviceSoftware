package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import lombok.Getter;
import org.example.loadingdevicesoftware.pagesControllers.*;

import java.util.ArrayList;

public class PagesBuffer {

    public static _2_TestOfSwitcher3XScreenController switcherPage;
    public static _3_TestOfStageProtection3XScreenController protectionPage;
    public static _5_TestOfMeasurementTransformerScreenController transformerPage;
    public static _6_ComTradeScreenController comTradePage;
    public static _7_DifProtectionScreenController difPage;
    public static _9_DeBuggerScreenController deBugPage;

    @Getter
    private static String fxmlName;
    private static String controllerName;

    public static ArrayList<String> buffer = new ArrayList<>();


    public static void clear() {
        fxmlName = null;
        controllerName = null;
        if (buffer != null )buffer.clear();
    }

    public static void savePage (Object source) {
        if (source instanceof _2_TestOfSwitcher3XScreenController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _3_TestOfStageProtection3XScreenController controller) {
            protectionPage = (_3_TestOfStageProtection3XScreenController) source;
        }
        if (source instanceof _5_TestOfMeasurementTransformerScreenController controller) {
            transformerPage = (_5_TestOfMeasurementTransformerScreenController) source;
        }
        if (source instanceof _6_ComTradeScreenController controller) {
            comTradePage = (_6_ComTradeScreenController) source;
        }
        if (source instanceof _7_DifProtectionScreenController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _8_HandControlScreenController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _9_DeBuggerScreenController controller) {
            deBugPage = (_9_DeBuggerScreenController) source;
        }
        controllerName = source.getClass().getSimpleName();
        fxmlName = getProperName(controllerName);
    }

    public static ObservableList<Node> getChildren() {
        return switch (controllerName) {
            case "_2_TestOfSwitcher3XScreenController" -> switcherPage.getAnchorPane().getChildren();
            case "_7_DifProtectionScreenController" -> difPage.getAnchorPane().getChildren();
            case "_8_HandControlScreenController" -> difPage.getAnchorPane().getChildren();
            case null, default -> throw new IllegalStateException("Unexpected value: ");
        };
    }

    private static String getProperName(String className) {
        return switch (className) {
            case "_2_TestOfSwitcher3XScreenController" -> "2.TestOfSwitcher3X.fxml";
            case "_3_TestOfStageProtection3XScreenController" -> "3.TestOfStageProtection3X.fxml";
            case "_5_TestOfMeasurementTransformerScreenController" -> "5.TestOfMeasurementTransformerScreen.fxml";
            case "_6_ComTradeScreenController" -> "6.ComTradeScreen.fxml";
            case "_7_DifProtectionScreenController" -> "7.DifProtection.fxml";
            case "_8_HandControlScreenController" -> "8.HandControl.fxml";
            case null, default -> throw new IllegalStateException("Unexpected value: ");
        };
    }

    private static void saveStates(ObservableList<Node> list) {
        for (Node node : list) {
            if (node instanceof SimpleTextField textField) buffer.add(textField.getText());
            if (node instanceof SimpleButton button) buffer.add(String.valueOf(button.getObjectPosition().getActualPosition()));
            if (node instanceof ButtonWithPicture button) buffer.add(String.valueOf(button.getObjectPosition().getActualPosition()));
            if (node instanceof SimpleImageView imageView) buffer.add(String.valueOf(imageView.getObjectPosition().getActualPosition()));
            if (node instanceof SimpleComboBox<?> comboBox) buffer.add(String.valueOf(comboBox.getSelectionModel().getSelectedIndex()));
        }
    }
}
