package org.example.loadingdevicesoftware.pagesControllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class _6_ComtradeScenarioController extends ScreensController implements Configurable {

    private final AtomicBoolean formState = new AtomicBoolean(false);

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
        AtomicBoolean parsingResult = new AtomicBoolean(false);
        AtomicInteger parsingCount = new AtomicInteger(1);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File newFile = InterfaceElementsLogic.openFileChooser(stage, "Comtrade files .cff", "*.cff");
        if (newFile != null) {
            Platform.runLater(() -> {
                Alert alert = InterfaceElementsLogic.showAlert("Выполняется анализ .cff файла", InterfaceElementsLogic.Alert_Size.SMALL,
                        false);
                PauseTransition pauseTransition = new PauseTransition(javafx.util.Duration.seconds(10));
                pauseTransition.play();
                pauseTransition.setOnFinished(event -> {
                    if (parsingResult.get()) {
                        alert.close();
                        if (parsingResult.get()) {
                            InterfaceElementsLogic.showAlert("Анализ выполнен успешно!", InterfaceElementsLogic.Alert_Size.SMALL,
                                    true);
                        } else {
                            InterfaceElementsLogic.showAlert("Ошибка при анализе .cff файла!", InterfaceElementsLogic.Alert_Size.SMALL,
                                    true);
                        }
                        changeFormState();
                    } else {
                        if (parsingCount.incrementAndGet() > 3) {
                            alert.close();
                            InterfaceElementsLogic.showAlert("Ошибка при анализе .cff файла!", InterfaceElementsLogic.Alert_Size.SMALL,
                                    true);
                            formState.set(false);
                        } else {
                            pauseTransition.play();
                        }
                    }
                });
            });
            CompletableFuture<Boolean> future = ComtradeParser.parseCFF(newFile);
            future.thenAccept(result -> {
                parsingResult.set(result);
                formState.set(result);
            });
        }
    }

    private void changeFormState() {
        if (formState.get()) {

        } else {

        }
    }


    @Override
    public void changeConfiguration(Event event) {

    }
}
