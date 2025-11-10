package org.example.loadingdevicesoftware.pagesControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class _101_deviceWorkingScreenControl {

    private boolean[] checkFlags = new boolean[5];

    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    @FXML
    Text headerText;
    @FXML
    Text checkOne;
    @FXML
    Text checkTwo;
    @FXML
    Text checkThree;
    @FXML
    Text checkFour;
    @FXML
    Text checkFive;

    @FXML
    AnchorPane anchorPane;

    @FXML
    SimpleButton menuButton;
    @FXML
    SimpleButton cancelButton;
    @FXML
    SimpleButton startButton;

    @FXML
    Text dateTimeText;

    @FXML
    ImageView imageView;

    @FXML
    SimpleImageView checkOneImage;
    @FXML
    SimpleImageView checkTwoImage;
    @FXML
    SimpleImageView checkThreeImage;
    @FXML
    SimpleImageView checkFourImage;
    @FXML
    SimpleImageView checkFiveImage;

    ArrayList<SimpleImageView> checks = new ArrayList<>(5);

    @FXML
    public void initialize() {
        ScreensController.setAllowedToStartScenario(false);
        Arrays.fill(checkFlags, false);
        //Настройка области для расположения элементов и создание ImageView для расположения фонового изображения
        anchorPane.setPrefSize(ApplicationConstants.APPLICATION_WINDOW_WIDTH, ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        imageView.setFitHeight(ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        imageView.setFitWidth(ApplicationConstants.APPLICATION_WINDOW_WIDTH);
        imageView.toBack();
        imageView.setImage(ApplicationConstants.BACKGROUND_BLUE);
        for (Node node : anchorPane.getChildren()) {
            switch (node) {
                case Text text when text == dateTimeText:
                    text.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
                    text.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    AnchorPane.setTopAnchor(text, 714.0);
                    AnchorPane.setLeftAnchor(text, 385.0);
                    break;
                case SimpleButton button when button == cancelButton:
                    AnchorPane.setTopAnchor(button, 695.0);
                    AnchorPane.setLeftAnchor(button, 770.0);
                    button.setup(SimpleButton.Presets.CANCEL);
                    button.setOnAction(event -> {
                        try {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.getFxmlName());
                            onClose();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    button.changePosition(2);
                    break;
                case SimpleButton button when button == startButton:
                    AnchorPane.setTopAnchor(button, 695.0);
                    AnchorPane.setLeftAnchor(button, 1030.0);
                    button.setup(SimpleButton.Presets.START);
                    button.setOnAction(event -> {
                        try {
                            Buffer.setFlagForDifProtection(true);
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.getFxmlName());
                            onClose();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    button.setActualStatus(Changeable.Status.LOCKED);
                    break;
                case SimpleButton button when button == menuButton:
                    AnchorPane.setTopAnchor(button, 695.0);
                    AnchorPane.setLeftAnchor(button, 50.0);
                    button.setup(SimpleButton.Presets.MENU);
                    button.setOnAction(event -> {
                        try {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
                            onClose();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    break;
                case Text text when text == headerText || text == checkOne || text == checkTwo || text == checkThree ||
                        text == checkFour || text == checkFive:
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    Object[] params = switch (text) {
                        case Text textOne when textOne == headerText ->
                                new Object[]{"Проверка условий пуска", 480., 150.};
                        case Text textOne when textOne == checkOne ->
                                new Object[]{"1. Проверка настройки модулей", 80., 215.};
                        case Text textOne when textOne == checkTwo ->
                                new Object[]{"2. Проверка питания модулей", 80., 298.75};
                        case Text textOne when textOne == checkThree ->
                                new Object[]{"3. Проверка синхронизации", 80., 382.5};
                        case Text textOne when textOne == checkFour ->
                                new Object[]{"4. Проверка диапазона тока", 80., 466.25};
                        case Text textOne when textOne == checkFive ->
                                new Object[]{"5. Проверка сопротивления силовых контактов", 80., 550.};
                        default -> null;
                    };
                    text.setText((String) params[0]);
                    AnchorPane.setLeftAnchor(text, (double) params[1]);
                    AnchorPane.setTopAnchor(text, (double) params[2]);
                    break;
                case SimpleImageView imageView when imageView == checkOneImage || imageView == checkTwoImage ||
                        imageView == checkThreeImage || imageView == checkFourImage || imageView == checkFiveImage:
                    imageView.setup(new String[]{"", "", ""}, new Image[]{null, ApplicationConstants.CHECK_SUCCESSFUL,
                            ApplicationConstants.CHECK_FAILED}, new double[][]{{50., 51.}, {50., 51.}, {50., 51.}});
                    double[] position = switch (imageView) {
                        case SimpleImageView imageView1 when imageView1 == checkOneImage -> new double[]{1070., 210.};
                        case SimpleImageView imageView1 when imageView1 == checkTwoImage -> new double[]{1070., 293.75};
                        case SimpleImageView imageView1 when imageView1 == checkThreeImage ->
                                new double[]{1070., 377.5};
                        case SimpleImageView imageView1 when imageView1 == checkFourImage ->
                                new double[]{1070., 461.25};
                        case SimpleImageView imageView1 when imageView1 == checkFiveImage -> new double[]{1070., 545.};
                        default -> null;
                    };
                    AnchorPane.setLeftAnchor(imageView, position[0]);
                    AnchorPane.setTopAnchor(imageView, position[1]);
                    checks.add(imageView);
                    imageView.changePosition(0);
                    break;
                case null, default:
                    break;
            }
        }
        Platform.runLater(this::requestForResults);
    }

    //TODO добавить логику для уточнения числа фаз для сценариев релейной защиты и выключателя
    //TODO Доработать тексты сообщений: добавить больше полезной информации (конкретные проблемные адреса, модули)
    private void requestForResults() {
        CheckingManager.Scenarios actualScenario = switch (PagesBuffer.getFxmlName()) {
            case "2.TestOfSwitcher3X.fxml" -> CheckingManager.Scenarios.THREE_PHASE_SWITCHER;
            case "3.TestOfStageProtection3X.fxml" -> CheckingManager.Scenarios.THREE_PHASE_PROTECTION;
            case "5.TestOfMeasurementTransformerScreen.fxml" -> CheckingManager.Scenarios.MEASUREMENT_TRANSFORMER;
            case "6.ComTradeScreen.fxml" -> CheckingManager.Scenarios.COMTRADE;
            case "7.DifProtection.fxml" -> CheckingManager.Scenarios.DIFFERENTIAL_PROTECTION;
            case "8.HandControl.fxml" -> CheckingManager.Scenarios.HAND_CONTROL;
            default -> throw new IllegalStateException("Unexpected value: " + PagesBuffer.getFxmlName());
        };

        disableButtons(true);// блокируем кнопки

        executor.submit(() -> {
            if (!runCheck(1, () -> {
                        try {
                            return CheckingManager.settingsCheck(actualScenario);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    "Ошибка настройки!\nКоличество настроенных модулей не соответствует выбранному сценарию.")) return;

            if (!runCheck(2, CheckingManager::powerCheck,
                    "Ошибка проверки питания!\nНедостаточное количество модулей получают питание")) return;

            if (!runCheck(3, CheckingManager::synchronizationCheck,
                    "Ошибка проверки синхронизации!\nМодули не синхронизированы")) return;

            if (!runCheck(4, CheckingManager::currentRangeCheck,
                    "Ошибка проверки диапазона тока!")) return;

            runCheck(5, CheckingManager::resistanceCheck,
                    "Ошибка проверки сопротивления!");
            finishChecks();
            ScreensController.setAllowedToStartScenario(true);

        });
    }

    private void disableButtons(boolean disable) {
        Platform.runLater(() -> {
            startButton.setDisable(disable);
            cancelButton.setDisable(disable);
            menuButton.setDisable(disable);
        });
    }

    private void finishChecks() {
        Platform.runLater(() -> {
            disableButtons(false);
        });
    }

    private void updateIndicator(int index, boolean success) {
        SimpleImageView indicator = checks.get(index - 1);
        indicator.setImage(success
                ? ApplicationConstants.CHECK_SUCCESSFUL
                : ApplicationConstants.CHECK_FAILED);
    }

    private void onClose() {
        executor.shutdownNow();
        if (!CheckingManager.getFormParameters().isEmpty()) {
            CheckingManager.setFormParameters(new ArrayList<>());
        }
    }

    private boolean runCheck(int index, Supplier<Boolean> check, String errorMessage) {
        boolean result = check.get();
        Platform.runLater(() -> updateIndicator(index, result));
        if (!result) {
            Platform.runLater(() -> InterfaceElementsLogic.showAlert(errorMessage));
            finishChecks();
        }
        return result;
    }


}
