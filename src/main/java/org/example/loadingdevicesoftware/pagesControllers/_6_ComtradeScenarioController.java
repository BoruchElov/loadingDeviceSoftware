package org.example.loadingdevicesoftware.pagesControllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import io.fair_acc.chartfx.XYChart;
import io.fair_acc.chartfx.axes.AxisLabelOverlapPolicy;
import io.fair_acc.chartfx.axes.spi.DefaultNumericAxis;
import io.fair_acc.chartfx.plugins.DataPointTooltip;
import io.fair_acc.chartfx.plugins.EditAxis;
import io.fair_acc.chartfx.plugins.Zoomer;
import io.fair_acc.dataset.spi.DefaultErrorDataSet;
import io.fair_acc.dataset.utils.ProcessingProfiler;


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
                    setupRightPane();
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


    /**
     * Утилитарный класс для настройки ячеек в списке выбора сигналов.
     */
    public class RowItem {
        private BooleanProperty selected = new SimpleBooleanProperty(false);
        private StringProperty label = new SimpleStringProperty();
        private StringProperty value = new SimpleStringProperty();

        public RowItem(String label) {
            this.label.set(label);
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }

        public StringProperty labelProperty() {
            return label;
        }

        public StringProperty valueProperty() {
            return value;
        }
    }

    /**
     * Метод для настройки внешнего вида и функционала элементов панели выбора сигналов
     */
    private void setupLeftPane() {

        double yPosition = 425.;
        SimpleButton applyButton = new SimpleButton();
        applyButton.setup(new String[]{"apply-button"}, new String[]{"Применить"}, FontManager.
                getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.SMALL));
        leftPane.getChildren().add(applyButton);
        applyButton.setLayoutX(30.);
        applyButton.setLayoutY(yPosition);

        Label startText = new Label("tначальный");
        Label endText = new Label("tконечный");
        Label[] labels = new Label[]{startText, endText};
        for (Label label : labels) {
            label.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.SMALL));
            label.setAlignment(Pos.CENTER);
            leftPane.getChildren().add(label);
            label.setLayoutY(yPosition - 30.);
            double xPosition = (label == startText) ? 180. : 295.;
            label.setLayoutX(xPosition);
        }

        SimpleTextField startTF = new SimpleTextField();
        SimpleTextField endTF = new SimpleTextField();
        SimpleTextField[] texts = new SimpleTextField[]{startTF, endTF};
        for (SimpleTextField tf : texts) {
            leftPane.getChildren().add(tf);
            tf.setLimits(0., 100000., SimpleTextField.numberOfDecimals.TWO);
            tf.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
            tf.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
            tf.setAlignment(Pos.BOTTOM_CENTER);
            double tfWidth = 95.;
            double tfHeight = 42.;
            tf.setPrefSize(tfWidth, tfHeight);
            tf.setMinSize(tfWidth, tfHeight);
            tf.setMaxSize(tfWidth, tfHeight);
            tf.setLayoutX(0.);
            double xPosition = (tf == startTF) ? 180. : 290.;
            tf.setLayoutX(xPosition);
            tf.setLayoutY(yPosition);
        }


        ObservableList<RowItem> rowItems = FXCollections.observableArrayList();
        for (int i = 0; i < 12; i++) {
            rowItems.add(new RowItem("Тестовое свойство №" + i));
        }

        ListView<RowItem> listView = new ListView<>();
        listView.setItems(rowItems); // ObservableList<RowItem>

        double height = 320.;
        double width = 380.;
        listView.setPrefSize(width, height);
        listView.setMinSize(width, height);
        listView.setMaxSize(width, height);
        listView.setSelectionModel(null);

        listView.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox cb = new CheckBox();
            private final Label text = new Label();
            private final SimpleTextField tf = new SimpleTextField();
            private final HBox root = new HBox(10, cb, text, tf);

            private RowItem boundItem; // <-- ВАЖНО

            {
                HBox.setHgrow(tf, Priority.ALWAYS);
                root.setAlignment(Pos.CENTER);
                cb.getStyleClass().clear();
                cb.getStyleClass().add("check-box");
                cb.getStyleClass().add("check-box-new");

                text.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.NORMAL));
                double labelWidth = 150.;
                double labelHeight = 64.;
                text.setPrefSize(labelWidth, labelHeight);
                text.setMinSize(labelWidth, labelHeight);
                text.setMaxSize(labelWidth, labelHeight);
                text.setWrapText(true);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setAlignment(Pos.CENTER);

                tf.setLimits(-100000., 100000., SimpleTextField.numberOfDecimals.THREE);
                tf.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
                tf.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
                tf.setAlignment(Pos.BOTTOM_CENTER);
                double tfWidth = 95.;
                double tfHeight = 42.;
                tf.setPrefSize(tfWidth, tfHeight);
                tf.setMinSize(tfWidth, tfHeight);
                tf.setMaxSize(tfWidth, tfHeight);
            }

            @Override
            protected void updateItem(RowItem item, boolean empty) {
                super.updateItem(item, empty);

                // 1) отвязаться от старого item
                if (boundItem != null) {
                    cb.selectedProperty().unbindBidirectional(boundItem.selectedProperty());
                    text.textProperty().unbind();
                    tf.textProperty().unbindBidirectional(boundItem.valueProperty());
                    boundItem = null;
                }

                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                // 2) привязаться к новому item
                boundItem = item;
                cb.selectedProperty().bindBidirectional(item.selectedProperty());
                text.textProperty().bind(item.labelProperty());
                tf.textProperty().bindBidirectional(item.valueProperty());

                setGraphic(root);
            }
        });

        leftPane.getChildren().add(listView);
        listView.setLayoutX(10.);
        listView.setLayoutY(52.);

        Label status = new Label("Статус");
        Label name = new Label("Сигнал");
        Label scale = new Label("Масштаб");
        Label[] labels1 = new Label[]{status, name, scale};
        for (Label l : labels1) {
            l.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.NORMAL));
            l.setAlignment(Pos.CENTER);
            leftPane.getChildren().add(l);
            l.setLayoutY(10.);
            if (l == status) {
                l.setLayoutX(20.);
            } else if (l == name) {
                l.setLayoutX(140.);
            } else {
                l.setLayoutX(260.);
            }
        }

        Line lineUp = new Line();
        Line lineDown = new Line();
        Line[] lines = new Line[]{lineUp, lineDown};
        for (Line l : lines) {
            leftPane.getChildren().add(l);
            double y = (l == lineUp) ? 47. : 383.;
            l.setLayoutY(y);
            l.setLayoutX(10.);
            l.setStartX(10.);
            l.setEndX(375.);
            l.setStrokeWidth(3.);
        }
    }

    /**
     * Метод для настройки внешнего вида и функционала панели отображения графиков
     */
    private void setupRightPane() {
        ProcessingProfiler.setVerboseOutputState(true);
        ProcessingProfiler.setLoggerOutputState(true);
        ProcessingProfiler.setDebugState(false);

        final DefaultNumericAxis xAxis1 = new DefaultNumericAxis();
        xAxis1.setOverlapPolicy(AxisLabelOverlapPolicy.SKIP_ALT);
        final DefaultNumericAxis yAxis1 = new DefaultNumericAxis();

        final XYChart chart = new XYChart(xAxis1, yAxis1);
        chart.legendVisibleProperty().set(false);
        chart.getPlugins().add(new Zoomer());
        chart.getPlugins().add(new EditAxis());
        chart.getPlugins().add(new DataPointTooltip());
        // set them false to make the plot faster
        chart.setAnimated(false);

        xAxis1.setAutoRangeRounding(false);
        // xAxis1.invertAxis(true); TODO: bug inverted time axis crashes when zooming
        xAxis1.setTimeAxis(true);
        yAxis1.setAutoRangeRounding(true);

        chart.setStyle("""
                -fx-background-color: #6e9cdf;              /* фон вокруг plot-area */
                -chart-plot-background-color: #b5368f;      /* фон области построения */
                -fx-font-family: "Consolas";
                    -fx-font-size: 40px;
                """);


        final DefaultErrorDataSet dataSet = new DefaultErrorDataSet("TestData");

        generateData(dataSet);

        long startTime = ProcessingProfiler.getTimeStamp();
        chart.getDatasets().add(dataSet);
        ProcessingProfiler.getTimeDiff(startTime, "adding data to chart");

        startTime = ProcessingProfiler.getTimeStamp();
        rightPane.getChildren().add(chart);
        ProcessingProfiler.getTimeDiff(startTime, "adding chart into StackPane");
    }

    private static final int N_SAMPLES = 10_000; // default: 10000

    private static void generateData(final DefaultErrorDataSet dataSet) {
        final long startTime = ProcessingProfiler.getTimeStamp();

        dataSet.clearData();
        final double now = System.currentTimeMillis() / 1000.0 + 1; // N.B. '+1'
        // to check
        // for
        // resolution
        for (int n = 0; n < N_SAMPLES; n++) {
            double t = now + n * 10;
            t *= +1;
            final double y = 100 * Math.cos(Math.PI * t * 0.0005) + 0 * 0.001 * (t - now) + 0 * 1e4;
            final double ex = 0.1;
            final double ey = 10;
            dataSet.add(t, y, ex, ey);
        }

        ProcessingProfiler.getTimeDiff(startTime, "adding data into DataSet");
    }
}
