package org.example.loadingdevicesoftware.pagesControllers;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.Objects;

public class _2_TestOfSwitcher3XScreenController {
    
    ContactObject contactOne;
    ContactObject contactTwo;

    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button contactOneButton;
    @FXML
    private Button contactTwoButton;
    @FXML
    private ToggleButton testOfSwitcher1X;
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
    private ImageView backgroundImageView;
    @FXML
    private ImageView contactOneView;
    @FXML
    private ImageView contactTwoView;
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;

    //Объявление текстового поля для задания названия объекта
    @FXML
    private TextField objectNameTextField;
    @FXML
    private TextField namePerfomerTextField;
    //Объявление текстовых полей для задания токов фаз
    @FXML
    private TextField phaseA1TextField;
    @FXML
    private TextField phaseB1TextField;
    @FXML
    private TextField phaseC1TextField;

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объекты фоновых картинок
    Image backImageOutThree = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/2.проверкаВыключателя1Х3Х/switchCheckBackgroundl(3X).png")).toExternalForm());
    //Объекты фоновых картинок
    Image backImageOutOne = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/2.проверкаВыключателя1Х3Х/switchCheckBackgroundl(1X).png")).toExternalForm());

    public void initialize() {
        //Привязка текстового поля к потоку обновления даты и времени
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());

        //Настройка стилей текстовых полей для ввода
        InterfaceElementsSettings.getObjectNameTextField(objectNameTextField);
        InterfaceElementsSettings.getOperatorTextField(namePerfomerTextField);

        InterfaceElementsSettings.getCurrentTextField(phaseA1TextField, InterfaceElementsSettings.Phases.PhaseA1);
        InterfaceElementsSettings.getCurrentTextField(phaseB1TextField, InterfaceElementsSettings.Phases.PhaseB1);
        InterfaceElementsSettings.getCurrentTextField(phaseC1TextField, InterfaceElementsSettings.Phases.PhaseC1);

        //Задание изображения для статуса инвертора
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutThree);
        //Настройка кнопки смены конфигурации выключателя
        InterfaceElementsSettings.getRightSideButton(testOfSwitcher1X, "3 фазы");

        contactOne = new ContactObject(contactOneButton, contactOneView, ContactObject.ContactPosition.OPENED,
                ContactObject.ContactStatus.DISABLED);
        contactTwo = new ContactObject(contactTwoButton, contactTwoView, ContactObject.ContactPosition.OPENED,
                ContactObject.ContactStatus.DISABLED);

        InterfaceElementsSettings.getWhiteMenuButton(toMenuButton,toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        InterfaceElementsSettings.getWhiteStartButton(startButton,startButtonImageView, InterfaceElementsSettings.Background.BLUE);
    }

    //Метод для перехода в главное меню
    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException, BadElementException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
        InterfaceElementsLogic.writeToPdf("newFile", new Element[]{InterfaceElementsLogic.getTable()});
    }
    //Метод для перехода в старт
    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("2.TestOfSwitcher3X.fxml");
    }

    //Методы для настройки кнопок выбора контактов
    @FXML
    public void setPictureForContactOne() {
        contactOne.setEnabled();
        switch (contactOne.getContactPosition()) {
            case OPENED -> contactOne.setClosed();
            case CLOSED -> contactOne.setOpened();
        }
    }
    
    @FXML
    public void setPictureForContactTwo() {
        contactTwo.setEnabled();
        switch (contactTwo.getContactPosition()) {
            case OPENED -> contactTwo.setClosed();
            case CLOSED -> contactTwo.setOpened();
        }
    }

    @FXML
    public void changeSwitcherConfiguration() {
        if (testOfSwitcher1X.isSelected()) {
            testOfSwitcher1X.setText("1 фаза");
            backgroundImageView.setImage(backImageOutOne);
            phaseA1TextField.setVisible(false);
            phaseC1TextField.setVisible(false);
            InterfaceElementsSettings.getCurrentTextField(phaseB1TextField, InterfaceElementsSettings.Phases.SinglePhase);
        } else {
            testOfSwitcher1X.setText("3 фазы");
            backgroundImageView.setImage(backImageOutThree);
            phaseA1TextField.setVisible(true);
            phaseC1TextField.setVisible(true);
            InterfaceElementsSettings.getCurrentTextField(phaseB1TextField, InterfaceElementsSettings.Phases.PhaseB1);
        }
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

}