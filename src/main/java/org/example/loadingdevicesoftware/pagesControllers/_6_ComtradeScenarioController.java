package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.File;
import java.io.IOException;


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
                    button.setup(new String[]{"comtrade-button"}, new String[]{"Выберите файл COMTRADE"}, FontManager.getFont(FontManager.FontWeight.MEDIUM,
                            FontManager.FontSize.LARGE));
                    AnchorPane.setTopAnchor(button, 365.0);
                    AnchorPane.setLeftAnchor(button, 400.0);
                    button.setOnAction(_ -> {
                        testFile();
                        //InterfaceElementsLogic.openFileManager();
                    });
                    break;
                case SimpleTextField text when text == objectTextField || text == nameTextField:
                    text.setVisible(false);
                    text.setManaged(false);
                    break;
                default:
                    break;
            }
        }

    }

    public void testFile() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File newFile = InterfaceElementsLogic.openFileChooser(stage, "Comtrade files .cff", "*.cff");
        try {
            ComtradeParser.parseCFF(newFile);
        } catch (IOException e) {
            System.err.println("Ошибка при парсинге файла!\n" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(newFile.getName() + " | " + newFile.getAbsolutePath());
    }


    @Override
    public void changeConfiguration(Event event) {

    }
}
