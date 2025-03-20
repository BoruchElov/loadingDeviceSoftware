package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.Objects;


public class _7_DifProtectionScreenController {

    //Кнопки
    @FXML
    private Button contactOneButton;
    @FXML
    private Button contactTwoButton;
    @FXML
    private Button windingOneConnection;
    @FXML
    private Button windingTwoConnection;
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button windingOneGroupButton;
    @FXML
    private Button windingTwoGroupButton;

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();
    ContactObject contactOne;
    ContactObject contactTwo;
    @FXML
    ImageView windingOneView;
    @FXML
    ImageView windingTwoView;
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;
    //Объекты картинок групп соединения обмоток
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Star1.png")).toExternalForm());
    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_1форма(без кнопок).png")).toExternalForm());

    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;
    private boolean isLocationPressed = false;
    private boolean isFeedingWindingPressed = false;
    private int counterOne = 0;
    private int counterTwo = 0;
    //Картинки
    @FXML
    private ImageView backgroundImageView;
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
    private ImageView contactOneView;
    @FXML
    private ImageView contactTwoView;

    //Текстовые поля для ввода данных
    @FXML
    private ToggleButton phaseAButton;
    @FXML
    private ToggleButton phaseBButton;
    @FXML
    private ToggleButton phaseCButton;
    @FXML
    private ToggleButton groundButton;
    @FXML
    private ToggleButton shortCircuitLocationButton;
    @FXML
    private ToggleButton feedingWindingButton;
    //Объявление текстового поля для задания названия объекта и ФИО работника
    @FXML
    private TextField objectNameTextField;
    @FXML
    private TextField userNameTextField;
    //Объявление текстовых полей для задания токов фаз
    @FXML
    private TextField phaseA1TextField;
    @FXML
    private TextField phaseA2TextField;
    @FXML
    private TextField phaseB1TextField;
    @FXML
    private TextField phaseB2TextField;
    @FXML
    private TextField phaseC1TextField;
    @FXML
    private TextField phaseC2TextField;
    @FXML
    private TextField phaseA1AngleTextField;
    @FXML
    private TextField phaseA2AngleTextField;
    @FXML
    private TextField phaseB1AngleTextField;
    @FXML
    private TextField phaseB2AngleTextField;
    @FXML
    private TextField phaseC1AngleTextField;
    @FXML
    private TextField phaseC2AngleTextField;
    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;






    //Объекты элементы
    // индикаторы элементов
    @FXML
    private Circle indicatorContactOne;
    @FXML
    private Circle indicatorContactTwo;
    // они закомичены по причине ненужности, но они работают
    @FXML
    private Line linePhaseA1;
    @FXML
    private Line linePhaseB1;
    @FXML
    private Line linePhaseC1;
    @FXML
    private Line linePhaseA2;
    @FXML
    private Line linePhaseB2;
    @FXML
    private Line linePhaseC2;

    @FXML
    private Circle feedingOne;
    @FXML
    private Circle feedingTwo;


    @FXML
    private ImageView imageShortCircuit;
    @FXML
    private ImageView imageGround;











    @FXML
    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");
        setupObjectNameField(userNameTextField, "Введите ФИО исполнителя");

        setupObjectNameField(phaseA1TextField, "Ток");
        setupObjectNameField(phaseA2TextField, "Ток");
        setupObjectNameField(phaseB1TextField, "Ток");
        setupObjectNameField(phaseB2TextField, "Ток");
        setupObjectNameField(phaseC1TextField, "Ток");
        setupObjectNameField(phaseC2TextField, "Ток");

        setupObjectNameField(phaseA1AngleTextField, "Угол");
        setupObjectNameField(phaseA2AngleTextField, "Угол");
        setupObjectNameField(phaseB1AngleTextField, "Угол");
        setupObjectNameField(phaseB2AngleTextField, "Угол");
        setupObjectNameField(phaseC1AngleTextField, "Угол");
        setupObjectNameField(phaseC2AngleTextField, "Угол");

        //Задание изображений для статусов инверторов
        inverterA1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterA2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterB2Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC1Status.setImage(ApplicationConstants.STATUS_CONNECTED);
        inverterC2Status.setImage(ApplicationConstants.STATUS_CONNECTED);

        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);

        //Настройка кнопки "Выбор места повреждения"
        shortCircuitLocationButton.setText("");
        setupRightSideButtons(shortCircuitLocationButton);
        //Настройка кнопки "Выбор фазы А"
        phaseAButton.setText("A");
        setupRightSideButtons(phaseAButton);
        //Настройка кнопки "Выбор фазы В"
        phaseBButton.setText("B");
        setupRightSideButtons(phaseBButton);
        //Настройка кнопки "Выбор фазы С"
        phaseCButton.setText("C");
        setupRightSideButtons(phaseCButton);
        //Настройка кнопки "Выбор кз на землю"
        groundButton.setText("G");
        setupRightSideButtons(groundButton);
        //Настройка кнопки "Выбор питающей обмотки"
        setupRightSideButtons(feedingWindingButton);

//        feedingWinding();

        InterfaceElementsSettings.getWhiteMenuButton(toMenuButton, toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        InterfaceElementsSettings.getWhiteStartButton(startButton, startButtonImageView, InterfaceElementsSettings.Background.BLUE);

        //Настройка кнопок для выбора схемы соединения обмоток трансформатора
        setupConnectionSchemesButtons(windingOneConnection, windingOneView, 55, 55);
        setupConnectionSchemesButtons(windingTwoConnection, windingTwoView, 55, 55);

        //Настройка кнопок для задания положения контактов
        contactOne = new ContactObject(contactOneButton, contactOneView, ContactObject.ContactPosition.OPENED,
                ContactObject.ContactStatus.DISABLED);
        contactTwo = new ContactObject(contactTwoButton, contactTwoView, ContactObject.ContactPosition.OPENED,
                ContactObject.ContactStatus.DISABLED);

        //Настройка кнопок для задания номера схемы
        setupConnectionSchemesButtons(windingOneGroupButton);
        setupConnectionSchemesButtons(windingTwoGroupButton);
        disableOrEnablePhaseButtons();



//ТЕСТОВЫЕ ФУНКЦИИ
        //Настройка индикаторов контактов
        blinkingIndicator();

        //Метод по изменению цвета питающей обмотки
        feedingWindingButton.setOnAction(event -> changeFeedingWinding(feedingWindingButton,feedingOne, feedingTwo));

        //метод по изменению цвета линии
        phaseAButton.setOnAction(event -> changeColorPhaseLine(phaseAButton, linePhaseA1, linePhaseA2));
        phaseBButton.setOnAction(event -> changeColorPhaseLine(phaseBButton, linePhaseB1, linePhaseB2));
        phaseCButton.setOnAction(event -> changeColorPhaseLine(phaseCButton, linePhaseC1, linePhaseC2));


        shortCircuitLocationButton.setOnAction(event -> changeShortCircuitLocation(shortCircuitLocationButton, imageShortCircuit, imageGround));


    }






    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("7.DifProtection.fxml");
    }

    @FXML
    public void setPictureForWindingOne() {
        windingOneStatus = commonMethodForPositionPicturesButtons(windingOneView, windingOneStatus, starConnection, deltaConnection);
    }

    @FXML
    public void setPictureForWindingTwo() {
        windingTwoStatus = commonMethodForPositionPicturesButtons(windingTwoView, windingTwoStatus, starConnection, deltaConnection);
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

    //Метод по смене цвета индикатора. По идеи его нужно просто переписать в случае срабатывания контакта 1 или 2
    @FXML
    public void blinkingIndicator() {
        indicatorContactOne.setOnMouseEntered(event -> indicatorContactOne.setFill(Color.RED));
        indicatorContactOne.setOnMouseExited(event -> indicatorContactOne.setFill(Color.GREEN));

        indicatorContactTwo.setOnMouseEntered(event -> indicatorContactTwo.setFill(Color.RED));
        indicatorContactTwo.setOnMouseExited(event -> indicatorContactTwo.setFill(Color.GREEN));
    }






    //Метод изменения питающей обмотки.
    private void changeFeedingWinding(ToggleButton toggleButton, Circle circle1, Circle circle2) {
        commonMethodForRightSideButtons(toggleButton, "I", "II");
        if (toggleButton.isSelected()) {
            circle1.setStroke(Color.GREEN);
            circle2.setStroke(Color.BLUE);
        } else {
            circle1.setStroke(Color.BLUE);
            circle2.setStroke(Color.GREEN);
        }
    }

    //Метод выделяющий цветом выбранную линию.
    private void changeColorPhaseLine(ToggleButton toggleButton, Line line1, Line line2) {
        commonMethodForRightSideButtons(toggleButton);
        if (toggleButton.isSelected()) {
                line1.setStroke(Color.RED);
                line1.setStrokeWidth(8.0);
                line2.setStroke(Color.RED);
                line2.setStrokeWidth(8.0);
            } else {
                line1.setStroke(Color.BLACK);
                line1.setStrokeWidth(5.0);
                line2.setStroke(Color.BLACK);
                line2.setStrokeWidth(5.0);
            }
    }

    //метод для выбора КЗ.
    private void changeShortCircuitLocation(ToggleButton toggleButton, ImageView imageView1, ImageView imageView2) {
        isLocationPressed = true;
        disableOrEnablePhaseButtons();
        imageView1.

        if (feedingWindingButton.isSelected()) {        //питающая обмотка 1
            commonMethodForRightSideButtons(toggleButton, "ВНУТРЕННЕЕ КЗ", "ВНЕШНЕЕ КЗ");
            if (toggleButton.isSelected()) {
                imageView1.setLayoutX(464);                 //расположение по центру
                imageView1.setLayoutY(222);
                imageView1.setRotate(180);

                imageView2.setLayoutX(449);
                imageView2.setLayoutY(497);
            } else {
                imageView1.setLayoutX(860);                 //справа
                imageView1.setLayoutY(121);
                imageView1.setRotate(0);

                imageView2.setLayoutX(887);
                imageView2.setLayoutY(547);
            }
        } else {//питающая обмотка 2
            commonMethodForRightSideButtons(toggleButton, "ВНУТРЕННЕЕ КЗ", "ВНЕШНЕЕ КЗ");
            if (toggleButton.isSelected()) {
                imageView1.setLayoutX(464);                 //центр
                imageView1.setLayoutY(222);
                imageView1.setRotate(180);

                imageView2.setLayoutX(449);
                imageView2.setLayoutY(497);
            } else {
                imageView1.setLayoutX(41);                  //слева
                imageView1.setLayoutY(121);
                imageView1.setRotate(180);

                imageView2.setLayoutX(19);
                imageView2.setLayoutY(547);
            }
        }
    }







    //метод для настройки кнопок в правой части окна сценария диф.защиты
    public void setupRightSideButtons(ToggleButton button) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
    }

    //метод для изменения выделения кнопок в правой части окна сценария диф.защиты
    public void changeColorRightSideButtons(ToggleButton button) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.ORANGE,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        InterfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 20, 0, textField,
                prompt);
    }

    //Метод для настройки кнопок соединения обмоток и контактов
    public void setupConnectionSchemesButtons(Button button, ImageView imageView, int width, int height) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.WHITE, 0,
                imageView, null, button, width, height, false);
    }

    //Метод для настройки кнопок номеров групп
    public void setupConnectionSchemesButtons(Button button) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 42,
                0, button);
    }

    private void disableOrEnablePhaseButtons() {
        if (isLocationPressed) {
            phaseAButton.setDisable(false);
            phaseBButton.setDisable(false);
            phaseCButton.setDisable(false);
            groundButton.setDisable(false);
            feedingWindingButton.setDisable(false);
            feedingWindingButton.setText("I");
        } else {
            phaseAButton.setDisable(true);
            phaseBButton.setDisable(true);
            phaseCButton.setDisable(true);
            groundButton.setDisable(true);
            feedingWindingButton.setDisable(true);
        }
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

    //Метод, запускающийся при нажатии на кнопку "Фаза А"
    public void phaseA() {
        commonMethodForRightSideButtons(phaseAButton);
    }

    //Метод, запускающийся при нажатии на кнопку "Фаза В"
    public void phaseB() {
        commonMethodForRightSideButtons(phaseBButton);
    }

    //Метод, запускающийся при нажатии на кнопку "Фаза С"
    public void phaseC() {
        commonMethodForRightSideButtons(phaseCButton);
    }

    public void ground() {
        commonMethodForRightSideButtons(groundButton);
    }

//    //Метод, запускающийся при нажатии на кнопку "Выбор питающей обмотки"
//    public void feedingWinding() {
//        if (isFeedingWindingPressed) {
//            commonMethodForRightSideButtons(feedingWindingButton, "II", "I");
//        } else {
//            feedingWindingButton.setText("");
//            isFeedingWindingPressed = true;
//        }
//        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
//                3, 17, 15, ApplicationConstants.colours.BLACK, 36, 0,
//                feedingWindingButton);
//    }

    //Метод, запускающийся при нажатии на кнопку "Выбор места повреждения"
    public void shortCircuitLocation() {
        if (!isLocationPressed) {
            isLocationPressed = true;
            disableOrEnablePhaseButtons();
        }
        commonMethodForRightSideButtons(shortCircuitLocationButton, "ВНУТРЕННЕЕ КЗ",
                "ВНЕШНЕЕ КЗ");
    }

    public void firstScheme() {
        windingOneGroupButton.setText(Integer.toString(counterOne));
        if (counterOne >= 11) {
            counterOne = 0;
        } else {
            counterOne++;
        }
    }

    public void secondScheme() {
        windingTwoGroupButton.setText(Integer.toString(counterTwo));
        if (counterTwo >= 11) {
            counterTwo = 0;
        } else {
            counterTwo++;
        }
    }

    /**
     * Метод для изменения картинки на кнопках контактов и схем соединения обмоток.
     *
     * @param imageView    объект для картинки
     * @param status       положение кнопки
     * @param imageIfTrue  картинка в первом положении
     * @param imageIfFalse картинка во втором положении
     */
    private boolean commonMethodForPositionPicturesButtons(ImageView imageView, boolean status, Image imageIfTrue,
                                                           Image imageIfFalse) {
        imageView.setVisible(true);
        if (status) {
            imageView.setImage(imageIfTrue);
            status = false;
        } else {
            imageView.setImage(imageIfFalse);
            status = true;
        }
        return status;
    }

    /**
     * Общий метод для изменения цвета кнопок в правой части при нажатии. Запускается при нажатии на кнопку,
     * меняет цвет её границы и определяет, какую картинку поставить на задний план при изменении статуса кнопки.
     *
     * @param toggleButton кнопка
     */
    private void commonMethodForRightSideButtons(ToggleButton toggleButton) {
        if (toggleButton.isSelected()) {
            changeColorRightSideButtons(toggleButton);
        } else {
            setupRightSideButtons(toggleButton);
        }
    }

    /**
     * Общий метод для изменения цвета и текста кнопок в правой части при нажатии. Запускается при нажатии на кнопку,
     * меняет цвет её границы и текст на ней, определяет, какую картинку поставить на задний план при изменении статуса кнопки.
     *
     * @param toggleButton      кнопка
     * @param textIfSelected    текст на кнопке при нажатом положении
     * @param textIfNotSelected текст на кнопке при отжатом положении
     */

    private void commonMethodForRightSideButtons(ToggleButton toggleButton, String textIfSelected,
                                                 String textIfNotSelected) {
        if (toggleButton.isSelected()) {
            toggleButton.setText(textIfSelected);
            changeColorRightSideButtons(toggleButton);
        } else {
            toggleButton.setText(textIfNotSelected);
            setupRightSideButtons(toggleButton);
        }
    }
}
