package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.Buffer;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.DateTimeUpdater;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic;

import java.io.IOException;

public class _6_ComTradeScreenController {

    InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button chooseFileButton;

    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    @FXML
    private ImageView inverterA1Status;
    @FXML
    private ImageView inverterA2Status;
    @FXML
    private ImageView inverterB1Status;
    @FXML
    private ImageView inverterB2Status;
    @FXML
    private ImageView inverterC1Status;
    @FXML
    private ImageView inverterC2Status;



    @FXML
    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        interfaceElementsSettings.getWhiteMenuButton(toMenuButton,toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        interfaceElementsSettings.getWhiteStartButton(startButton,startButtonImageView, InterfaceElementsSettings.Background.BLUE);
        //Настройка кнопки для открытия файла
        setupOpenFileButton(chooseFileButton);
        chooseFileButton.setText("Выберите файл формата COMTRADE");
        //Задание изображений для статусов инверторов
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
    }

    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(),"0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("6.ComTrade.fxml");
    }

    @FXML
    public void chooseFile() {
        InterfaceElementsLogic.openFileManager();
    }
    @FXML
    public void testClick() {
        System.out.println("");
    }


    //Метод для настройки кнопки открытия файла
    private void setupOpenFileButton (Button button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.WHITE, 26, 0,
                button);
    }
}
