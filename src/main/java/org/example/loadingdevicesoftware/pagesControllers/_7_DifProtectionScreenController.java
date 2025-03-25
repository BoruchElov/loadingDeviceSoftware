package org.example.loadingdevicesoftware.pagesControllers;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;
import static org.example.loadingdevicesoftware.logicAndSettingsOfInterface.InterfaceElementsLogic.*;

import java.io.IOException;

import java.util.Objects;




public class _7_DifProtectionScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();

    @FXML
    private AnchorPane mainPane;

    @FXML
    ImageView windingOneView;
    @FXML
    ImageView windingTwoView;
    @FXML
    ImageView toMenuButtonImageView;
    @FXML
    ImageView startButtonImageView;
    @FXML
    ImageView cleanButtonImageView;
    //Объекты картинок групп соединения обмоток
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Star1.png")).toExternalForm());
    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/7.дифзащита/диф_защита_1форма(без кнопок).png")).toExternalForm());

    //объекты элементов
    Image shortCircuitYellow = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/молния_желтая.png")).toExternalForm());
    Image shortCircuitRed = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/молния_красная.png")).toExternalForm());
    Image ground = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/ground.png")).toExternalForm());

    //картинка стрелочек
    Image arrows = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/BasePictures/стрелочка.png")).toExternalForm());


//Кнопки
    //Кнопки контактов
    @FXML
    private Button contactOneButton;
    @FXML
    private Button contactTwoButton;
    //Кнопки выбора обмоток (звезда/треугольник)
    @FXML
    private Button windingOneConnection;
    @FXML
    private Button windingTwoConnection;
    //Выбор номера схемы (1-11)
    @FXML
    private Button windingOneGroupButton;
    @FXML
    private Button windingTwoGroupButton;
    //кнопки переходов снизу
    @FXML
    private Button toMenuButton;
    @FXML
    private Button startButton;
    @FXML
    private Button cleanButton;


//Кнопки переключатели справа
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
    //картинки фаз (стрелочки)
    @FXML
    private ImageView phaseA1Image;
    @FXML
    private ImageView phaseA2Image;
    @FXML
    private ImageView phaseB1Image;
    @FXML
    private ImageView phaseB2Image;
    @FXML
    private ImageView phaseC1Image;
    @FXML
    private ImageView phaseC2Image;
    //картинки земли и молнии
    @FXML
    private ImageView imageShortCircuit;
    @FXML
    private ImageView imageGround;


//Текстовые поля
    //Названия объекта и ФИО работника
    @FXML
    private TextField objectNameTextField;
    @FXML
    private TextField userNameTextField;
    //Текстовые поля для задания токов фаз
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

//Текстовые поля
    //Вывода даты-времени
    @FXML
    private Text dateTimeText;

//ТЕСТОВЫЕ ЭЛЕМЕНТЫ
    // Индикаторы контактов
    @FXML
    private Circle indicatorContactOne;
    @FXML
    private Circle indicatorContactTwo;

    //Линии
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

    //Трансформатор
    @FXML
    private Circle feedingOne;
    @FXML
    private Circle feedingTwo;

//Объекты контакты
    ContactObject contactOne;
    ContactObject contactTwo;

//для анимации
    private Timeline timeline;

    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;
    private boolean isLocationPressed = false;

    private int counterOne = 0;
    private int counterTwo = 0;

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

        //Настройка кнопок снизу
        InterfaceElementsSettings.getWhiteMenuButton(toMenuButton, toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        InterfaceElementsSettings.getWhiteStartButton(startButton, startButtonImageView, InterfaceElementsSettings.Background.BLUE);
        InterfaceElementsSettings.getWhiteClearButton(cleanButton, cleanButtonImageView, InterfaceElementsSettings.Background.BLUE);

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

        //Метод для блокировок кнопок пока не нажата первая кнопка
        disableOrEnablePhaseButtons();


//ТЕСТОВЫЕ ФУНКЦИИ
        //Настройка индикаторов контактов
        blinkingIndicator();

        //метод по изменению цвета линии
        phaseAButton.setOnAction(_ -> changeColorPhaseLine(phaseAButton, phaseA1Image, phaseA2Image, linePhaseA1, linePhaseA2));
        phaseBButton.setOnAction(_ -> changeColorPhaseLine(phaseBButton, phaseB1Image, phaseB2Image, linePhaseB1, linePhaseB2));
        phaseCButton.setOnAction(_ -> changeColorPhaseLine(phaseCButton, phaseC1Image, phaseC2Image, linePhaseC1, linePhaseC2));

        //Метод по изменению положения КЗ и земли
        shortCircuitLocationButton.setOnAction(_ -> changeShortCircuitLocation(feedingWindingButton, shortCircuitLocationButton,
                imageShortCircuit, imageGround));

//тестовая функция по блокировке кнопок при возвращении из 100 формы
        // Проверяем, откуда был выполнен переход
        if (isFromCheckingStartConditions()) {
            lockAllButtonsAndStartAnimation();
        }
    }

    @FXML
    public void goToMainScreen(ActionEvent event) throws IOException {
        switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    @FXML
    public void goToStartScreen(ActionEvent event) throws IOException {
        switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
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

    //Метод по смене цвета индикатора при наведении мыши
    @FXML
    public void blinkingIndicator() {
        indicatorContactOne.setOnMouseEntered(_ -> indicatorContactOne.setFill(Color.RED));
        indicatorContactOne.setOnMouseExited(_ -> indicatorContactOne.setFill(Color.GREEN));

        indicatorContactTwo.setOnMouseEntered(_ -> indicatorContactTwo.setFill(Color.RED));
        indicatorContactTwo.setOnMouseExited(_ -> indicatorContactTwo.setFill(Color.GREEN));
    }

    //Метод для кнопки по смене питающей обмотки
    @FXML
    public void changeFeedWind(ActionEvent event){
        ToggleButton toggleButton = (ToggleButton) event.getSource();
        if (toggleButton.getUserData() == null) {
            toggleButton.setUserData("activated");
        }
        changeFeedingWinding(feedingWindingButton, feedingOne, feedingTwo);
    }

    //Метод по очистке всего введенного
    @FXML
    public void clearAllButton(){
        clearAll(mainPane);
    }

    //Метод по очистке окна
    private void clearAll(Node node){
        //Очищаем текстовые поля TextField
        if (node instanceof TextInputControl) {
            ((TextInputControl) node).clear();
        }

        //Очищаем кнопки (ToggleButton) в дефолтное состояние
        else if (node instanceof ToggleButton toggleButton){
            toggleButton.setUserData(null);
            toggleButton.setSelected(false);
            changeFeedingWinding(feedingWindingButton, feedingOne, feedingTwo);
            changeColorPhaseLine(phaseAButton, phaseA1Image, phaseA2Image, linePhaseA1, linePhaseA2);
            changeColorPhaseLine(phaseBButton, phaseB1Image, phaseB2Image, linePhaseB1, linePhaseB2);
            changeColorPhaseLine(phaseCButton, phaseC1Image, phaseC2Image, linePhaseC1, linePhaseC2);
            ground();
            changeShortCircuitLocation(feedingWindingButton, shortCircuitLocationButton, imageShortCircuit, imageGround);
        }

        //рекурсивный метод для очистки
        else if (node instanceof Pane) {
            for (Node child : ((Pane) node).getChildren()) {
                clearAll(child);
            }
        }

        //Метод по очистке кнопок (Button) до дефолтного состояния (не все кнопки)
        contactOne.setDisabled();
        contactTwo.setDisabled();
        windingOneGroupButton.setText("");
        windingTwoGroupButton.setText("");
        setupConnectionSchemesButtons(windingOneConnection, windingOneView, 55, 55);
        setupConnectionSchemesButtons(windingTwoConnection, windingTwoView, 55, 55);
    }

    /**
     * Функции по смене параметров объектов на форме в зависимости от нажатий кнопок.
     */
    //Метод изменения питающей обмотки.
    private void changeFeedingWinding(ToggleButton toggleButton, Circle circle1, Circle circle2) {
        //Кнопка никогда не нажималась (дефолтное состояние)
        if (toggleButton.getUserData() == null) {
            circle1.setStroke(Color.BLACK);
            circle2.setStroke(Color.BLACK);
            isLocationPressed = false;
            disableOrEnablePhaseButtons();
            setupRightSideButtons(toggleButton);
            return;
        }
        isLocationPressed = true;
        disableOrEnablePhaseButtons();
        commonMethodForRightSideButtons(toggleButton, "I", "II");
        if (toggleButton.isSelected()) {
            circle1.setStroke(Color.GREEN);
            circle2.setStroke(Color.BLUE);
        } else{
            circle1.setStroke(Color.BLUE);
            circle2.setStroke(Color.GREEN);
       }
    }

    //Метод выделяющий цветом выбранную линию.
    private void changeColorPhaseLine(ToggleButton toggleButton, ImageView imageView1, ImageView imageView2, Line line1, Line line2) {
        commonMethodForRightSideButtons(toggleButton);
        imageView1.setImage(arrows);
        imageView2.setImage(arrows);
        if (toggleButton.isSelected()) {
            imageView1.setVisible(true);
            imageView2.setVisible(true);

            line1.setStroke(Color.RED);
            line1.setStrokeWidth(8.0);
            line2.setStroke(Color.RED);
            line2.setStrokeWidth(8.0);
        } else{
            imageView1.setVisible(false);
            imageView2.setVisible(false);

            line1.setStroke(Color.BLACK);
            line1.setStrokeWidth(5.0);
            line2.setStroke(Color.BLACK);
            line2.setStrokeWidth(5.0);
        }
    }

    //метод для выбора КЗ.
    private void changeShortCircuitLocation(ToggleButton button1, ToggleButton button2, ImageView imageView1, ImageView imageView2) {
        commonMethodForRightSideButtons(button2, "ВНУТРЕННЕЕ КЗ", "ВНЕШНЕЕ КЗ");
        imageView1.setVisible(true);
        if (button1.getUserData() == null){
            imageView1.setVisible(false);
            button2.setText("");
            setupRightSideButtons(button2);
        }
        if (!button1.isSelected() && !button2.isSelected()) {
            // Слева: F1, F2
            //Смена расположения молнии
            imageView1.setImage(shortCircuitYellow);
            imageView1.setLayoutX(41);
            imageView1.setLayoutY(121);
            imageView1.setRotate(180);
            //смена расположения земли
            imageView2.setLayoutX(19);
            imageView2.setLayoutY(547);
            //смена направления стрелочек
            phaseA1Image.setRotate(0);
            phaseB1Image.setRotate(0);
            phaseC1Image.setRotate(0);
        } else if (button1.isSelected() && !button2.isSelected()) {
            // Справа: T1, F2
            //Смена расположения молнии
            imageView1.setImage(shortCircuitYellow);
            imageView1.setLayoutX(860);
            imageView1.setLayoutY(121);
            imageView1.setRotate(0);
            //смена направления стрелочек
            imageView2.setLayoutX(887);
            imageView2.setLayoutY(547);
            //смена направления стрелочек
            phaseA2Image.setRotate(180);
            phaseB2Image.setRotate(180);
            phaseC2Image.setRotate(180);
        } else {
            // По центру: T1, T2 или F1, T2
            imageView1.setImage(shortCircuitYellow);
            imageView1.setLayoutX(464);
            imageView1.setLayoutY(222);
            imageView1.setRotate(180);
            //смена расположения земли
            imageView2.setLayoutX(449);
            imageView2.setLayoutY(497);
            //смена направления стрелочек
            phaseA1Image.setRotate(180);
            phaseB1Image.setRotate(180);
            phaseC1Image.setRotate(180);
            phaseA2Image.setRotate(0);
            phaseB2Image.setRotate(0);
            phaseC2Image.setRotate(0);
        }
    }

    //Метод по отображению значка земли по нажатию кнопки
    public void ground() {
        imageGround.setImage(ground);
        commonMethodForRightSideButtons(groundButton);
        imageGround.setVisible(groundButton.isSelected());
    }

    /**
     * Блок методов определяющий работу формы после перехода из формы проверок.
     * Здесь на 5 секунд все кнопки будут заблокированы и во время ожидания будет моргать молния.
     * По истечении 5 секунд кнопки будут разблокированы.
     * Это удалось достигнуть в результате изменения общего метода @switchScene. В него просто добавлен флаг, который
     * меняется в зависимости от перехода из формы в форму на true false
     */
    //Метод по блокировке и анимированию
    private void lockAllButtonsAndStartAnimation() {
        lockAll(true);
        startLightingAnimation();
        // Создаем задержку на 5 секунд
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(_ -> {
            stopLightningAnimation();
            lockAll(false);});
        pause.play();
    }

    //Общий метод по обходу всех элементов и блокировки их.
    private void lockAll(boolean lock){
        InterfaceElementsLogic.walk(
                mainPane,
                (node, key) -> node.setDisable(lock),
                lock,
                node -> node.getId() != null && node.getId().equals("startButton"));
    }

    //Тестовая функция по анимации картинок
    private void startLightingAnimation() {
        // Создаем Timeline для смены картинок каждые 0.5 секунд
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), _ -> imageShortCircuit.setImage(shortCircuitYellow)),
                new KeyFrame(Duration.seconds(0.3), _ -> imageShortCircuit.setImage(shortCircuitRed)),
                new KeyFrame(Duration.seconds(0.6), _ -> imageShortCircuit.setImage(shortCircuitYellow)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    //остановка анимации
    private void stopLightningAnimation() {
        if (timeline != null) {
            timeline.stop();
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

    //Метод для блокировок кнопок в начале работы
    private void disableOrEnablePhaseButtons() {
        if (isLocationPressed) {
            phaseAButton.setDisable(false);
            phaseBButton.setDisable(false);
            phaseCButton.setDisable(false);
            groundButton.setDisable(false);
            shortCircuitLocationButton.setDisable(false);
            shortCircuitLocationButton.setText("ВНУТРЕННЕЕ КЗ");
        } else {
            phaseAButton.setDisable(true);
            phaseBButton.setDisable(true);
            phaseCButton.setDisable(true);
            groundButton.setDisable(true);
            shortCircuitLocationButton.setDisable(true);
        }
    }

    //Метод для изменения схемы обмоток 1
    public void firstScheme() {
        windingOneGroupButton.setText(Integer.toString(counterOne));
        if (counterOne >= 11) {
            counterOne = 0;
        } else {
            counterOne++;
        }
    }

    //Метод для изменения схемы обмоток 2
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
            setupRightSideButtons(toggleButton);
        } else {
            toggleButton.setText(textIfNotSelected);
            changeColorRightSideButtons(toggleButton);
        }
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }
}
