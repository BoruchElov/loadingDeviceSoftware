package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.Changeable;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.SimpleButton;

import java.io.IOException;

public class _4_EventLoggerController extends BasicController {

    @FXML
    SimpleButton menuButton;

    @FXML
    public void initialize() {
        super.initialize();

        imageView.setImage(ApplicationConstants.NEW_BASE_BACKGROUND);

        AnchorPane.setTopAnchor(dateTimeText, 714.0);
        AnchorPane.setLeftAnchor(dateTimeText, 490.0);

        AnchorPane.setTopAnchor(menuButton, 695.0);
        AnchorPane.setLeftAnchor(menuButton, 50.0);
        menuButton.setup(SimpleButton.Presets.MENU);
        menuButton.setActualStatus(Changeable.Status.NORMAL);
        menuButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}