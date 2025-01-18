package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class _7_DifProtectionScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;
    private boolean contactOneStatus = false;
    private boolean contactTwoStatus = false;

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

    //Объявление текстового поля для вывода даты-времени
    @FXML
    private Text dateTimeText;

    //Объекты картинок групп соединения обмоток
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Star1.png")).toExternalForm());

    //Объекты картинок контактов
    Image normallyClosedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаНормЗамкКонт.png")).toExternalForm());
    Image normallyOpenedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаНормРазомкКонт.png")).toExternalForm());
    
    //Объекты фоновых картинок
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_1форма(без кнопок).png")).toExternalForm());
    
    //Папка RS_I
    Image RSI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_._I.png")).toExternalForm());
    Image RSAI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_A_I.png")).toExternalForm());
    Image RSABI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_AB_I.png")).toExternalForm());
    Image RSABCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_ABC_I.png")).toExternalForm());
    Image RSACI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_AC_I.png")).toExternalForm());
    Image RSBI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_B_I.png")).toExternalForm());
    Image RSBCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_BC_I.png")).toExternalForm());
    Image RSCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_I/RS_C_I.png")).toExternalForm());
    //Папка RS_O
    Image RSO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_._O.png")).toExternalForm());
    Image RSAO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_A_O.png")).toExternalForm());
    Image RSABO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_AB_O.png")).toExternalForm());
    Image RSABCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_ABC_O.png")).toExternalForm());
    Image RSACO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_AC_O.png")).toExternalForm());
    Image RSBO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_B_O.png")).toExternalForm());
    Image RSBCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_BC_O.png")).toExternalForm());
    Image RSCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/RS_O/RS_C_O.png")).toExternalForm());
    //Папка SR_I
    Image SRI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_._I.png")).toExternalForm());
    Image SRAI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_A_I.png")).toExternalForm());
    Image SRABI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_AB_I.png")).toExternalForm());
    Image SRABCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_ABC_I.png")).toExternalForm());
    Image SRACI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_AC_I.png")).toExternalForm());
    Image SRBI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_B_I.png")).toExternalForm());
    Image SRBCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_BC_I.png")).toExternalForm());
    Image SRCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_I/SR_C_I.png")).toExternalForm());
    //Папка SR_O
    Image SRO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_._O.png")).toExternalForm());
    Image SRAO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_A_O.png")).toExternalForm());
    Image SRABO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_AB_O.png")).toExternalForm());
    Image SRABCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_ABC_O.png")).toExternalForm());
    Image SRACO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_AC_O.png")).toExternalForm());
    Image SRBO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_B_O.png")).toExternalForm());
    Image SRBCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_BC_O.png")).toExternalForm());
    Image SRCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/SR_O/SR_C_O.png")).toExternalForm());
    
    
    //Объекты картинок для кнопок и статусов инверторов
    Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаРамкаПуска.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());

    @FXML
    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");

        setupObjectNameField(phaseA1TextField, "Ток А1, А");
        setupObjectNameField(phaseA2TextField, "Ток А2, А");
        setupObjectNameField(phaseB1TextField, "Ток В1, А");
        setupObjectNameField(phaseB2TextField, "Ток В2, А");
        setupObjectNameField(phaseC1TextField, "Ток С1, А");
        setupObjectNameField(phaseC2TextField, "Ток С2, А");

        //Задание изображений для статусов инверторов
        inverterA1Status.setImage(statusConnected);
        inverterA2Status.setImage(statusConnected);
        inverterB1Status.setImage(statusConnected);
        inverterB2Status.setImage(statusConnected);
        inverterC1Status.setImage(statusConnected);
        inverterC2Status.setImage(statusConnected);
        //Установка картинки на фон
        backgroundImageView.setImage(backImageOutSC);
        //Настройка кнопки "Выбор места повреждения"
        shortCircuitLocationButton.setText("ВНЕШНЕЕ КЗ");
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
        //Настройка кнопки "Выбор питающей обмотки"
        feedingWindingButton.setText("I");
        setupRightSideButtons(feedingWindingButton);
        //Настройка кнопки "Меню"
        setupBottomButtons(toMenuButton, toMenuButtonImageView, lowButtoncImage, "МЕНЮ");
        //Настройка кнопки "Пуск"
        setupBottomButtons(startButton, startButtonImageView, lowButtoncImage, "ПУСК");
        //Настройка кнопок для выбора схемы соединения обмоток трансформатора
        setupConnectionSchemesButtons(windingOneConnection, windingOneView, 55, 55);
        setupConnectionSchemesButtons(windingTwoConnection, windingTwoView, 55, 55);
        //Настройка кнопок для задания положения контактов
        setupConnectionSchemesButtons(contactOneButton, contactOneView, 45, 30);
        setupConnectionSchemesButtons(contactTwoButton, contactTwoView, 45, 30);
    }

    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen (ActionEvent event) throws IOException {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "DifProtectionStart.fxml");
    }

    @FXML
    public void setPictureForWindingOne() {
        windingOneStatus = commonMethodForPositionPicturesButtons(windingOneView, windingOneStatus, starConnection, deltaConnection);
    }
    @FXML
    public void setPictureForWindingTwo() {
        windingTwoStatus = commonMethodForPositionPicturesButtons(windingTwoView, windingTwoStatus, starConnection, deltaConnection);
    }
    @FXML
    public void setPictureForContactOne() {
        contactOneStatus = commonMethodForPositionPicturesButtons(contactOneView, contactOneStatus, normallyClosedContact,
                normallyOpenedContact);
    }
    @FXML
    public void setPictureForContactTwo() {
        contactTwoStatus = commonMethodForPositionPicturesButtons(contactTwoView, contactTwoStatus, normallyClosedContact,
                normallyOpenedContact);
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

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text) {
        interfaceElementsSettings.buttonSettings(ApplicationConstants.colours.BLUE, ApplicationConstants.colours.BLUE,
                0, 17, 0, ApplicationConstants.colours.WHITE, 26, 0,
                imageView, image, button, 138, 64, true, text);
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }

    //Метод, запускающийся при нажатии на кнопку "Фаза А"
    public void phaseA() {
        commonMethodForRightSideButtons(phaseBButton);
    }

    //Метод, запускающийся при нажатии на кнопку "Фаза В"
    public void phaseB() {
        commonMethodForRightSideButtons(phaseBButton);
    }

    //Метод, запускающийся при нажатии на кнопку "Фаза С"
    public void phaseC() {
        commonMethodForRightSideButtons(phaseCButton);
    }

    //Метод, запускающийся при нажатии на кнопку "Выбор питающей обмотки"
    public void feedingWinding() {
        commonMethodForRightSideButtons(feedingWindingButton, "II", "I");
    }

    //Метод, запускающийся при нажатии на кнопку "Выбор места повреждения"
    public void shortCircuitLocation() {
        commonMethodForRightSideButtons(shortCircuitLocationButton, "ВНУТРЕННЕЕ КЗ",
                "ВНЕШНЕЕ КЗ");
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
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
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
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
    }

    /**
     * Метод для поиска нужной картинки заднего плана при текущем положении кнопок в правой части страницы.
     * @param shortCircuitLocation статус кнопки "Выбор места повреждения"
     * @param phaseA статус кнопки "Фаза А"
     * @param phaseB статус кнопки "Фаза В"
     * @param phaseC статус кнопки "Фаза С"
     * @param sendingWinding статус кнопки "Выбор питающей обмотки"
     * @return нужная картинка
     */

    private Image selectImage(boolean shortCircuitLocation, boolean phaseA, boolean phaseB, boolean phaseC,
                             boolean sendingWinding) {
        /*
         * shortCircuitLocation = false - Внешнее КЗ, true - Внутреннее
         * phaseA = true - КЗ фазы А
         * phaseB = true - КЗ фазы В
         * phaseC = true - КЗ фазы С
         * sendingWinding = false - Питающая обмотка 1, true - 2
         */
        // Карта для сопоставления комбинаций логических переменных и имен картинок
        Map<String, Image> imageMap = new HashMap<>();

        //Пары значения-картинка для случая RS_I
        imageMap.put("true,false,false,false,true", RSI);
        imageMap.put("true,true,false,false,true", RSAI);
        imageMap.put("true,true,true,false,true", RSABI);
        imageMap.put("true,true,true,true,true", RSABCI);
        imageMap.put("true,true,false,true,true", RSACI);
        imageMap.put("true,false,true,false,true", RSBI);
        imageMap.put("true,false,true,true,true", RSBCI);
        imageMap.put("true,false,false,true,true", RSCI);
        //Пары значения-картинка для случая RS_O
        imageMap.put("false,false,false,false,true", RSO);
        imageMap.put("false,true,false,false,true", RSAO);
        imageMap.put("false,true,true,false,true", RSABO);
        imageMap.put("false,true,true,true,true", RSABCO);
        imageMap.put("false,true,false,true,true", RSACO);
        imageMap.put("false,false,true,false,true", RSBO);
        imageMap.put("false,false,true,true,true", RSBCO);
        imageMap.put("false,false,false,true,true", RSCO);
        //Пары значения-картинка для случая SR_I
        imageMap.put("true,false,false,false,false", SRI);
        imageMap.put("true,true,false,false,false", SRAI);
        imageMap.put("true,true,true,false,false", SRABI);
        imageMap.put("true,true,true,true,false", SRABCI);
        imageMap.put("true,true,false,true,false", SRACI);
        imageMap.put("true,false,true,false,false", SRBI);
        imageMap.put("true,false,true,true,false", SRBCI);
        imageMap.put("true,false,false,true,false", SRCI);
        //Пары значения-картинка для случая SR_O
        imageMap.put("false,false,false,false,false", SRO);
        imageMap.put("false,true,false,false,false", SRAO);
        imageMap.put("false,true,true,false,false", SRABO);
        imageMap.put("false,true,true,true,false", SRABCO);
        imageMap.put("false,true,false,true,false", SRACO);
        imageMap.put("false,false,true,false,false", SRBO);
        imageMap.put("false,false,true,true,false", SRBCO);
        imageMap.put("false,false,false,true,false", SRCO);

        // Создаем строку ключа на основе значений переменных
        String key = shortCircuitLocation + "," + phaseA + "," + phaseB + "," + phaseC + "," + sendingWinding;

        // Ищем соответствующую картинку
        return imageMap.getOrDefault(key, backImageOutSC); // Возвращаем картинку по умолчанию, если комбинация не найдена
    }



}
