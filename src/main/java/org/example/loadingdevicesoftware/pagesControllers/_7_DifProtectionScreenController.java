package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class _7_DifProtectionScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;

    ContactObject contactOne;
    ContactObject contactTwo;

    private boolean isLocationPressed = false;
    private boolean isFeedingWindingPressed = false;

    private int counterOne = 0;
    private int counterTwo = 0;

    @FXML
    private ToggleButton shortCircuitLocationButton;
    @FXML
    private Button contactOneButton;
    @FXML
    private Button contactTwoButton;
    @FXML
    private ToggleButton phaseAButton;
    @FXML
    private ToggleButton phaseBButton;
    @FXML
    private ToggleButton phaseCButton;
    @FXML
    private ToggleButton groundButton;
    @FXML
    private ToggleButton feedingWindingButton;
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
    @FXML
    private ImageView backgroundImageView;
    @FXML
    ImageView windingOneView;
    @FXML
    ImageView windingTwoView;
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;
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

    //Объявление текстового поля для задания названия объекта
    @FXML
    private TextField objectNameTextField;
    //Объявление текстового поля для задания ФИО работника
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

    //Объекты картинок групп соединения обмоток
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Star1.png")).toExternalForm());
    
    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_1форма(без кнопок).png")).toExternalForm());

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
        feedingWinding();
        interfaceElementsSettings.getWhiteMenuButton(toMenuButton,toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        interfaceElementsSettings.getWhiteStartButton(startButton,startButtonImageView, InterfaceElementsSettings.Background.BLUE);
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
    }

    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen (ActionEvent event) throws IOException {
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

    //метод для настройки кнопок в правой части окна сценария диф.защиты
    public void setupRightSideButtons(ToggleButton button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
    }
    //метод для изменения выделения кнопок в правой части окна сценария диф.защиты
    public void changeColorRightSideButtons(ToggleButton button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.ORANGE,
                3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0,
                button);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        interfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3,17,15, ApplicationConstants.colours.BLACK,20,0,textField,
                prompt);
    }

    //Метод для настройки кнопок соединения обмоток и контактов
    public void setupConnectionSchemesButtons(Button button, ImageView imageView, int width, int height) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.WHITE, 0,
                imageView, null, button, width, height, false);
    }
    //Метод для настройки кнопок номеров групп
    public void setupConnectionSchemesButtons(Button button) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
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
    public void ground() { commonMethodForRightSideButtons(groundButton); }

    //Метод, запускающийся при нажатии на кнопку "Выбор питающей обмотки"
    public void feedingWinding() {
        if (isFeedingWindingPressed) {
            commonMethodForRightSideButtons(feedingWindingButton, "II", "I");
        } else {
            feedingWindingButton.setText("");
            isFeedingWindingPressed = true;
        }
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK,
                3, 17, 15, ApplicationConstants.colours.BLACK, 36, 0,
                feedingWindingButton);
    }

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
     * @param imageView объект для картинки
     * @param status положение кнопки
     * @param imageIfTrue картинка в первом положении
     * @param imageIfFalse картинка во втором положении
     */
    private boolean commonMethodForPositionPicturesButtons(ImageView imageView, boolean status, Image imageIfTrue,
                                                        Image imageIfFalse) {
        imageView.setVisible(true);
        if(status) {
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
     * @param toggleButton кнопка
     */
    private void commonMethodForRightSideButtons(ToggleButton toggleButton) {
        if (toggleButton.isSelected()) {
            changeColorRightSideButtons(toggleButton);
        } else {
            setupRightSideButtons(toggleButton);
        }
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(), phaseCButton.isSelected(), groundButton.isSelected(),
                feedingWindingButton.isSelected()));
    }

    /**
     * Общий метод для изменения цвета и текста кнопок в правой части при нажатии. Запускается при нажатии на кнопку,
     * меняет цвет её границы и текст на ней, определяет, какую картинку поставить на задний план при изменении статуса кнопки.
     * @param toggleButton кнопка
     * @param textIfSelected текст на кнопке при нажатом положении
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
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(), phaseCButton.isSelected(), groundButton.isSelected(),
                feedingWindingButton.isSelected()));
    }

    private Image selectImage(boolean shortCircuitLocation, boolean phaseA, boolean phaseB, boolean phaseC,
                              boolean ground, boolean sendingWinding) {

        return getProperPicture(shortCircuitLocation, phaseA, phaseB, phaseC, ground, sendingWinding);
    }

    private Image getProperPicture(boolean shortCircuitLocation, boolean phaseA, boolean phaseB, boolean phaseC,
                                    boolean ground, boolean sendingWinding) {

        String winding;

        if (sendingWinding) {
            winding = "RS_";
        } else {
            winding = "SR_";
        }

        String phaseCombination = getPhasesCombination(phaseA, phaseB, phaseC, ground);

        String location;

        if (shortCircuitLocation) {
            location = "I";
        } else {
            location = "O";
        }
        String fileName = winding + phaseCombination + location;

        return new Image(Objects.requireNonNull(getClass().
                getResource("/screen/7.дифзащита/" + fileName + ".png")).toExternalForm());
    }

    private String getPhasesCombination (boolean phaseA, boolean phaseB, boolean phaseC,
                                        boolean ground) {

        Map<String,String> hashMap = new HashMap<>();

        hashMap.put("true,false,false,false","A_");
        hashMap.put("true,true,false,false","AB_");
        hashMap.put("true,true,true,false","ABC_");
        hashMap.put("true,true,true,true","ABCG_");
        hashMap.put("false,true,false,false","B_");
        hashMap.put("false,true,true,false","BC_");
        hashMap.put("false,true,true,true","BCG_");
        hashMap.put("false,false,true,false","C_");
        hashMap.put("false,false,true,true","C_");
        hashMap.put("true,false,false,true","A_");
        hashMap.put("false,true,false,true","B_");
        hashMap.put("true,false,true,false","AC_");
        hashMap.put("true,false,true,true","ACG_");
        hashMap.put("true,true,false,true","ABG_");
        hashMap.put("false,false,false,true","G_");
        hashMap.put("false,false,false,false","");

        String key = phaseA + "," + phaseB + "," + phaseC + "," + ground;

        return hashMap.getOrDefault(key, ""); // Возвращаем картинку по умолчанию, если комбинация не найдена

    }



}
