package org.example.loadingdevicesoftware.pagesControllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class _6_ComtradeScenarioController extends ScreensController implements Configurable {

    private final AtomicBoolean formState = new AtomicBoolean(false);

    @FXML
    Pane leftPane;
    @FXML
    Pane rightPane;

    @FXML
    SimpleButton comtradeButton;

    @FXML
    public void initialize() {
        super.initialize();
        imageView.setImage(ApplicationConstants.NEW_BASE_BACKGROUND);
        
        changeConfiguration(new ActionEvent());
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
                PauseTransition pauseTransition = new PauseTransition(javafx.util.Duration.seconds(5));
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
        Platform.runLater(() -> {
            PauseTransition pauseTransition = new PauseTransition(javafx.util.Duration.seconds(1));
            pauseTransition.play();

            pauseTransition.setOnFinished(event -> {
                if (!formState.get()) {
                    changeConfiguration(new ActionEvent());
                } else {
                    comtradeButton.setVisible(false);
                    comtradeButton.setManaged(false);

                    leftPane.setVisible(true);
                    leftPane.setManaged(true);

                    rightPane.setVisible(true);
                    rightPane.setManaged(true);

                    setupLeftPane();
                }
            });
        });
    }


    @Override
    public void changeConfiguration(Event event) {
        formState.set(false);
        leftPane.setPrefWidth(400.);
        leftPane.setPrefHeight(480.);
        rightPane.setPrefWidth(810.);
        rightPane.setPrefHeight(480.);
        AnchorPane.setTopAnchor(rightPane, 160.);
        AnchorPane.setLeftAnchor(rightPane, 450.);
        AnchorPane.setTopAnchor(leftPane, 160.);
        AnchorPane.setLeftAnchor(leftPane, 20.);

        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case Pane pane when pane == leftPane || pane == rightPane:
                    pane.setBorder(Border.stroke(Paint.valueOf("#000000")));
                    pane.backgroundProperty().set(Background.fill(Paint.valueOf("#FFFFFF")));
                    pane.setVisible(false);
                    pane.setManaged(false);
                    break;

                case SimpleButton button when button == comtradeButton:
                    button.setVisible(true);
                    button.setManaged(true);
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

    public class RowItem {
        private final BooleanProperty selected = new SimpleBooleanProperty(false);
        private final StringProperty label = new SimpleStringProperty("");

        public RowItem(String label) {
            this.label.set(label);}

        public BooleanProperty selectedProperty() { return selected; }
        public StringProperty labelProperty() { return label; }
    }


    private void setupLeftPane() {
        ObservableList<RowItem> rowItems = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++) {
            rowItems.add(new RowItem("U" + i));
        }

        ListView<RowItem> listView = new ListView<>();
        listView.setItems(rowItems); // ObservableList<RowItem>

        listView.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox cb = new CheckBox();
            private final Label text = new Label();
            private final SimpleTextField tf = new SimpleTextField();
            private final HBox root = new HBox(10, cb, text, tf);

            // чтобы не “тянуло” строку
            {
                HBox.setHgrow(tf, Priority.ALWAYS);
                text.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                tf.setLimits(-100000.,100000., SimpleTextField.numberOfDecimals.THREE);
                tf.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                tf.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
            }

            @Override
            protected void updateItem(RowItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                // важно: отвязать старые биндинги
                cb.selectedProperty().unbindBidirectional(item.selectedProperty());

                // привязки
                cb.selectedProperty().bindBidirectional(item.selectedProperty());
                text.textProperty().bind(item.labelProperty());

                setGraphic(root);
            }
        });
        leftPane.getChildren().setAll(listView);
        AnchorPane.setTopAnchor(listView, 0.0);
        AnchorPane.setBottomAnchor(listView, 0.0);
        AnchorPane.setLeftAnchor(listView, 0.0);
        AnchorPane.setRightAnchor(listView, 0.0);


    }
}
