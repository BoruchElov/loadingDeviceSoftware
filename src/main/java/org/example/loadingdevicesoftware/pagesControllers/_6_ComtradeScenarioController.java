package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;


public class _6_ComtradeScenarioController extends ScreensController implements Configurable {

    @FXML
    SimpleButton comtradeButton;

    @FXML
    public void initialize() {
        super.initialize();
        imageView.setImage(ApplicationConstants.NEW_BASE_BACKGROUND);

        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case SimpleButton button when button == comtradeButton:
                    button.setup(new String[]{"comtrade-button"}, new String[]{"COMTRADE"}, FontManager.getFont(FontManager.FontWeight.MEDIUM,
                            FontManager.FontSize.LARGE));
                    AnchorPane.setTopAnchor(button, 365.0);
                    AnchorPane.setLeftAnchor(button, 500.0);
                    button.setOnAction(_ -> {InterfaceElementsLogic.openFileManager();});
                    break;
                default:
                    break;
            }
        }

    }


    @Override
    public void changeConfiguration(Event event) {

    }
}
