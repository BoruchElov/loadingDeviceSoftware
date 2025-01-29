package org.example.loadingdevicesoftware;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _101_deviceWorkingScreenControl {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //прогресс индикатор
    @FXML
    private ProgressIndicator progressIndicator;

    //кнопки старта/отмены и перехода в протокол
    @FXML
    private Button startButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button protocolButton;

    //Объявление областей изображений для кнопок
    @FXML
    private ImageView toProtocolButtonImageView;
    @FXML
    private ImageView startButtonImageView;
    @FXML
    private ImageView cancelButtonImageView;

    //Объявление области изображения для фона
    @FXML
    private ImageView backgroundImageView;

    //Объект картинки для фона
    private Image background = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_3форма_предупреждение(без кнопок).png")).toExternalForm());
    //Объекты картинок для кнопок
    private Image lowButtonImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/Кнопка(белая)_длинная.png")).toExternalForm());

    @FXML
    public void initialize() {
        //прогресс индикатор
        progressIndicator.setVisible(false);

        //Привязка текстового поля к потоку обновления даты и времени
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());

        //Установка изображения на фон
        backgroundImageView.setImage(background);

        //Настройка кнопки "Меню"
        setupBottomButtons(cancelButton, cancelButtonImageView, ApplicationConstants.WHITE_BUTTON_LONG, "ОТМЕНА", 182, 70);
        //Настройка кнопки "Пуск"
        setupBottomButtons(protocolButton, toProtocolButtonImageView, lowButtonImage, "ПРОТОКОЛ", 182, 70);
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtonImage, "ПУСК", 182, 70);
    }

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text,
                                   int width, int height) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.BLUE, ApplicationConstants.colours.BLUE,
                0, 17, 0, ApplicationConstants.colours.WHITE, 26, 0,
                imageView, image, button, width, height, true, text);

    }


    //Метод для перехода на экран главного меню
    @FXML
    public void goToProtocolScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "102.protocol.fxml");
    }



    @FXML
    //Метод для перехода на экран сценария диф.защиты
    public void goToPreviousPage (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), Buffer.getPreviousPage());
    }


    @FXML
    private void handleStartButtonAction() {
        startButton.setDisable(true);
        progressIndicator.setVisible(true);
        progressIndicator.setProgress(0);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(this::simulateProgress);
        executor.shutdown();
    }

    private void simulateProgress() {
        for (int i = 0; i <= 100; i++) {
            final int progress = i;

            // Обновляем интерфейс на JavaFX потоке
            Platform.runLater(() -> progressIndicator.setProgress(progress / 100.0));

            // Ждем 50 мс для визуализации
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
}
