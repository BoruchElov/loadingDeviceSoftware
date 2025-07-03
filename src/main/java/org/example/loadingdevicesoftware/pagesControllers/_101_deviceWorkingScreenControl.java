package org.example.loadingdevicesoftware.pagesControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class _101_deviceWorkingScreenControl {

    private boolean[] checkFlags = new boolean[6];

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
    Text checkSix;

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
    @FXML
    SimpleImageView checkSixImage;
    
    ArrayList<SimpleImageView> checks = new ArrayList<>(6);

    @FXML
    public void initialize() {
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
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.fxmlName);
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
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), PagesBuffer.fxmlName);
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
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    break;
                case Text text when text == headerText || text == checkOne || text == checkTwo || text == checkThree ||
                        text == checkFour || text == checkFive || text == checkSix:
                    text.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
                    text.setFill(Color.WHITE);
                    Object[] params = switch (text) {
                        case Text textOne when textOne == headerText ->
                                new Object[]{"Проверка условий пуска", 480., 150.};
                        case Text textOne when textOne == checkOne ->
                                new Object[]{"1. Проверка параметров формы", 80., 230.};
                        case Text textOne when textOne == checkTwo ->
                                new Object[]{"2. Проверка настройки модулей", 80., 296.};
                        case Text textOne when textOne == checkThree ->
                                new Object[]{"3. Проверка питания модулей", 80., 362.};
                        case Text textOne when textOne == checkFour ->
                                new Object[]{"4. Проверка синхронизации", 80., 428.};
                        case Text textOne when textOne == checkFive ->
                                new Object[]{"5. Проверка диапазона тока", 80., 494.};
                        case Text textOne when textOne == checkSix ->
                                new Object[]{"6. Проверка сопротивления силовых контактов", 80., 560.};
                        default -> null;
                    };
                    text.setText((String) params[0]);
                    AnchorPane.setLeftAnchor(text, (double) params[1]);
                    AnchorPane.setTopAnchor(text, (double) params[2]);
                    break;
                case SimpleImageView imageView when imageView == checkOneImage || imageView == checkTwoImage ||
                        imageView == checkThreeImage || imageView == checkFourImage || imageView == checkFiveImage ||
                        imageView == checkSixImage:
                    imageView.setup(new String[]{"", "", ""}, new Image[]{null, ApplicationConstants.CHECK_SUCCESSFUL,
                            ApplicationConstants.CHECK_FAILED}, new double[][]{{50., 51.}, {50., 51.},{50., 51.}});
                    double[] position = switch (imageView) {
                        case SimpleImageView imageView1 when imageView1 == checkOneImage -> new double[]{1070., 210.};
                        case SimpleImageView imageView1 when imageView1 == checkTwoImage -> new double[]{1070., 276.};
                        case SimpleImageView imageView1 when imageView1 == checkThreeImage -> new double[]{1070., 342.};
                        case SimpleImageView imageView1 when imageView1 == checkFourImage -> new double[]{1070., 408.};
                        case SimpleImageView imageView1 when imageView1 == checkFiveImage -> new double[]{1070., 474.};
                        case SimpleImageView imageView1 when imageView1 == checkSixImage -> new double[]{1070., 540.};
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

    private void requestForResults() {
        for (int i = 0; i < checkFlags.length; i++) {
            ChoiceDialog<Boolean> dialog = new ChoiceDialog<>(true, true, false);
            dialog.setTitle("Результат проверки");
            dialog.setHeaderText("Проверка " + (i + 1));
            dialog.setContentText("Выберите результат:");

            // Ожидаем выбора пользователя
            Optional<Boolean> result = dialog.showAndWait();

            // Обработка результата
            if (result.isPresent()) {
                checkFlags[i] = result.get();
                checks.get(i).changePosition(checkFlags[i] ? 1 : 2);
            } else {
                // Если пользователь отменил ввод
                checkFlags[i] = false; // Значение по умолчанию
                checks.get(i).changePosition(2);
            }
        }
        boolean isAllowedToContinue = true;
        for (boolean element : checkFlags) {
            if (!element) {isAllowedToContinue = false; break;}
        }
        if (isAllowedToContinue) {
            cancelButton.setActualStatus(Changeable.Status.NORMAL);
            cancelButton.changePosition(0);
            startButton.setActualStatus(Changeable.Status.NORMAL);
            startButton.changePosition(1);
        }
    }

}
