package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import lombok.Getter;
import org.example.loadingdevicesoftware.pagesControllers.*;

import java.util.ArrayList;

public class PagesBuffer {

    public static _2_SwitcherScenarioController switcherPage;
    public static _3_RelayProtectionScenarioController protectionPage;
    public static _5_TestOfMeasurementTransformerScenarioController transformerPage;
    public static _6_ComtradeScenarioController comTradePage;
    public static _7_DifProtectionScenarioController difPage;
    public static _9_DebuggerScenarioController deBugPage;

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
        if (source instanceof _2_SwitcherScenarioController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _3_RelayProtectionScenarioController controller) {
            protectionPage = (_3_RelayProtectionScenarioController) source;
        }
        if (source instanceof _5_TestOfMeasurementTransformerScenarioController controller) {
            transformerPage = (_5_TestOfMeasurementTransformerScenarioController) source;
        }
        if (source instanceof _6_ComtradeScenarioController controller) {
            comTradePage = (_6_ComtradeScenarioController) source;
        }
        if (source instanceof _7_DifProtectionScenarioController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _8_HandControlScenarioController controller) {
            saveStates(controller.getAnchorPane().getChildren());
        }
        if (source instanceof _9_DebuggerScenarioController controller) {
            deBugPage = (_9_DebuggerScenarioController) source;
        }
        controllerName = source.getClass().getSimpleName();
        fxmlName = getProperName(controllerName);
    }

    public static ObservableList<Node> getChildren() {
        return switch (controllerName) {
            case "_2_SwitcherScenarioController" -> switcherPage.getAnchorPane().getChildren();
            case "_7_DifProtectionScenarioController" -> difPage.getAnchorPane().getChildren();
            case "_8_HandControlScenarioController" -> difPage.getAnchorPane().getChildren();
            case null, default -> throw new IllegalStateException("Unexpected value: ");
        };
    }

    private static String getProperName(String className) {
        return switch (className) {
            case "_2_SwitcherScenarioController" -> "2.TestOfSwitcher3X.fxml";
            case "_3_RelayProtectionScenarioController" -> "3.TestOfStageProtection3X.fxml";
            case "_5_TestOfMeasurementTransformerScenarioController" -> "5.TestOfMeasurementTransformerScreen.fxml";
            case "_6_ComtradeScenarioController" -> "6.ComTradeScreen.fxml";
            case "_7_DifProtectionScenarioController" -> "7.DifProtection.fxml";
            case "_8_HandControlScenarioController" -> "8.HandControl.fxml";
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
