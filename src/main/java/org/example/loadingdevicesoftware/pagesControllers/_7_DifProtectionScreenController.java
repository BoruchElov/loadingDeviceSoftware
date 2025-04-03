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
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BaseComponents.PhaseLines;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BaseComponents.ShortCircuit;

import java.util.Objects;


public class _7_DifProtectionScreenController {

    private final InterfaceElementsSettings interfaceElementsSettings = new InterfaceElementsSettings();
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
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().getResource("/images/Star1.png")).toExternalForm());
    //Объект фоновой картинки
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().getResource("/screen/7.дифзащита/диф_защита_1форма(без кнопок).png")).toExternalForm());
    //объекты элементов
    Image shortCircuitYellow = new Image(Objects.requireNonNull(getClass().getResource("/screen/BasePictures/молния_желтая.png")).toExternalForm());
    Image shortCircuitRed = new Image(Objects.requireNonNull(getClass().getResource("/screen/BasePictures/молния_красная.png")).toExternalForm());
    Image ground = new Image(Objects.requireNonNull(getClass().getResource("/screen/BasePictures/ground.png")).toExternalForm());
    //картинка стрелочек
    Image arrows = new Image(Objects.requireNonNull(getClass().getResource("/screen/BasePictures/стрелочка.png")).toExternalForm());
    //Объекты контакты
    ContactObject contactOne;
    ContactObject contactTwo;

    @FXML
    private AnchorPane mainPane;
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
    private Circle windingOne;
    @FXML
    private Circle windingTwo;
    //для анимации
    private Timeline timeline;
    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;
    private boolean isLocationPressed = false;
    private int counterOne = 0;
    private int counterTwo = 0;

    private PhaseLines phaseA, phaseB, phaseC;
    private ShortCircuit shortCircuit;


    @FXML
    public void initialize() {
        dateTimeText.textProperty().bind(DateTimeUpdater.getInstance().dateTimeProperty());
// НАСТРОЙКА ВНЕШНЕГО ВИДА
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
        phaseAButton.setText("A");
        setupRightSideButtons(phaseAButton);
        phaseBButton.setText("B");
        setupRightSideButtons(phaseBButton);
        phaseCButton.setText("C");
        setupRightSideButtons(phaseCButton);
        groundButton.setText("G");
        setupRightSideButtons(groundButton);
        setupRightSideButtons(feedingWindingButton);

        //Настройка кнопок снизу
        InterfaceElementsSettings.getWhiteMenuButton(toMenuButton, toMenuButtonImageView, InterfaceElementsSettings.Background.BLUE);
        InterfaceElementsSettings.getWhiteStartButton(startButton, startButtonImageView, InterfaceElementsSettings.Background.BLUE);
        InterfaceElementsSettings.getWhiteClearButton(cleanButton, cleanButtonImageView, InterfaceElementsSettings.Background.BLUE);

        //Настройка кнопок для выбора схемы соединения обмоток трансформатора
        setupConnectionSchemesButtons(windingOneConnection, windingOneView, 55, 55);
        setupConnectionSchemesButtons(windingTwoConnection, windingTwoView, 55, 55);

        //Настройка кнопок для задания положения контактов
        contactOne = new ContactObject(contactOneButton, contactOneView, ContactObject.ContactPosition.OPENED, ContactObject.ContactStatus.DISABLED);
        contactTwo = new ContactObject(contactTwoButton, contactTwoView, ContactObject.ContactPosition.OPENED, ContactObject.ContactStatus.DISABLED);

        //Настройка кнопок для задания номера схемы
        setupConnectionSchemesButtons(windingOneGroupButton);
        setupConnectionSchemesButtons(windingTwoGroupButton);

        //Метод для блокировок кнопок пока не нажата первая кнопка
        disableOrEnablePhaseButtons();

        //Настройка индикаторов контактов
        blinkingIndicator();

        // Проверяем, откуда был выполнен переход
        if (InterfaceElementsLogic.isFromCheckingStartConditions()) {
            workForms();
        }

        //методы по переходу на другие формы
        startButton.setOnAction(this::goToStartScreen);
        toMenuButton.setOnAction(this::goToMenu);
//ТЕСТОВЫЕ ФУНКЦИИ

        //Методы выбора обмоток Трансформатора
        windingOneConnection.setOnAction(event -> setPictureForWindingOne());
        windingTwoConnection.setOnAction(event -> setPictureForWindingTwo());

        // Методы по смене положения контактов
        contactOneButton.setOnAction(event -> setPictureForContactOne());
        contactTwoButton.setOnAction(event -> setPictureForContactTwo());

        // Метод по очистке экрана
        cleanButton.setOnAction(event -> clearAllButton());

        // Метод для кнопки выбора питающей обмотки

        phaseA = new PhaseLines(linePhaseA1, linePhaseA2, phaseA1Image, phaseA2Image, Color.BLACK, Color.RED, 5.0, 8.0);
        phaseB = new PhaseLines(linePhaseB1, linePhaseB2, phaseB1Image, phaseB2Image, Color.BLACK, Color.RED, 5.0, 8.0);
        phaseC = new PhaseLines(linePhaseC1, linePhaseC2, phaseC1Image, phaseC2Image, Color.BLACK, Color.RED, 5.0, 8.0);

        phaseAButton.setOnAction(e -> handlePhaseButton(phaseAButton, linePhaseA1, linePhaseA2, phaseA1Image, phaseA2Image));
        phaseBButton.setOnAction(e -> handlePhaseButton(phaseBButton, linePhaseB1, linePhaseB2, phaseB1Image, phaseB2Image));
        phaseCButton.setOnAction(e -> handlePhaseButton(phaseCButton, linePhaseC1, linePhaseC2, phaseC1Image, phaseC2Image));

        feedingWindingButton.setOnAction(this::changeFeedWind);
        shortCircuitLocationButton.setOnAction(e -> handleShortCircuitLocation(shortCircuitLocationButton, feedingWindingButton));
    }

    //Метод по перемещению монии и земли
    private void handleShortCircuitLocation(ToggleButton TB1, ToggleButton TB2) {
        commonMethodForRightSideButtons(TB1, "ВНУТРЕННЕЕ КЗ", "ВНЕШНЕЕ КЗ");
        imageShortCircuit.setImage(shortCircuitYellow);
        int positionCase = 0;

        if (!TB1.isSelected() && !TB2.isSelected()) positionCase = 0;  // 00 - слева
        if (TB1.isSelected()) positionCase = 1;                       // 01 - центр
        if (!TB1.isSelected() && TB2.isSelected()) positionCase = 2;  // 10 - справа

        switch (positionCase) {
            case 0 -> {
                setShortCircuitPosition(41, 121, 180, 19, 547);             // Слева
                rotateLeftArrows(0);
            }
            case 1 -> {
                setShortCircuitPosition(464, 222, 180, 449, 497);           // Центр
                rotateLeftArrows(180);
                rotateRightArrows(0);
            }
            case 2 -> {
                setShortCircuitPosition(860, 121, 0, 887, 547);             // Справа
                rotateRightArrows(180);
            }
        }
    }

    //--- Вспомогательные методы ---//
    // Устанавливает позицию молнии и земли
    private void setShortCircuitPosition(double scX, double scY, double rotate, double groundX, double groundY) {
        imageShortCircuit.setLayoutX(scX);
        imageShortCircuit.setLayoutY(scY);
        imageShortCircuit.setRotate(rotate);
        imageGround.setLayoutX(groundX);
        imageGround.setLayoutY(groundY);
    }
    //вращает стрелочки которые слева
    private void rotateLeftArrows(double angle) {
        phaseA1Image.setRotate(angle);
        phaseB1Image.setRotate(angle);
        phaseC1Image.setRotate(angle);
    }
    //вращает стрелочки которы справа
    private void rotateRightArrows(double angle) {
        phaseA2Image.setRotate(angle);
        phaseB2Image.setRotate(angle);
        phaseC2Image.setRotate(angle);
    }

    // Обработчик для кнопки фаз
    private void handlePhaseButton(ToggleButton button, Line line1, Line line2, ImageView arrow1, ImageView arrow2) {
        boolean isSelected = button.isSelected();
        commonMethodForRightSideButtons(button);
        // Обновляем линии
        updateLineStyle(line1, isSelected);
        updateLineStyle(line2, isSelected);
        // Обновляем стрелки
        arrow1.setImage(arrows);
        arrow2.setImage(arrows);
        arrow1.setVisible(isSelected);
        arrow2.setVisible(isSelected);
    }

    private void updateLineStyle(Line line, boolean isSelected) {
        line.setStroke(isSelected ? Color.RED : Color.BLACK);
        line.setStrokeWidth(isSelected ? 10.0 : 5.0);
    }








    //Метод для кнопки по смене питающей обмотки
    public void changeFeedWind(ActionEvent event) {
        ToggleButton toggleButton = (ToggleButton) event.getSource();
        if (toggleButton.getUserData() == null) {
            toggleButton.setUserData("activated");
        }
        changeFeedingWinding(feedingWindingButton, windingOne, windingTwo);
    }


    // Методы переходов на другие формы
    // Переход в старт
    public void goToStartScreen(ActionEvent event) {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
        Buffer.setPreviousPage("7.DifProtection.fxml");
    }

    //Переход в меню
    private void goToMenu(ActionEvent event) {
        InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
    }

    //Метод по смене обмоток трансформатора
    public void setPictureForWindingOne() {
        windingOneStatus = commonMethodForPositionPicturesButtons(windingOneView, windingOneStatus, starConnection, deltaConnection);
    }

    //Метод по смене цвета индикатора при наведении мыши
    public void setPictureForWindingTwo() {
        windingTwoStatus = commonMethodForPositionPicturesButtons(windingTwoView, windingTwoStatus, starConnection, deltaConnection);
    }

    public void setPictureForContactOne() {
        contactOne.setEnabled();
        switch (contactOne.getContactPosition()) {
            case OPENED -> contactOne.setClosed();
            case CLOSED -> contactOne.setOpened();
        }
    }

    public void setPictureForContactTwo() {
        contactTwo.setEnabled();
        switch (contactTwo.getContactPosition()) {
            case OPENED -> contactTwo.setClosed();
            case CLOSED -> contactTwo.setOpened();
        }
    }

    public void blinkingIndicator() {
        indicatorContactOne.setOnMouseEntered(_ -> indicatorContactOne.setFill(Color.RED));
        indicatorContactOne.setOnMouseExited(_ -> indicatorContactOne.setFill(Color.GREEN));

        indicatorContactTwo.setOnMouseEntered(_ -> indicatorContactTwo.setFill(Color.RED));
        indicatorContactTwo.setOnMouseExited(_ -> indicatorContactTwo.setFill(Color.GREEN));
    }

    //Метод по очистке всего введенного
    public void clearAllButton() {
        clearAll(mainPane);
    }

    //Метод по очистке окна
    private void clearAll(Node node) {
        //Очищаем текстовые поля TextField
        if (node instanceof TextInputControl) {
            ((TextInputControl) node).clear();
        }

        //Очищаем кнопки (ToggleButton) в дефолтное состояние
        else if (node instanceof ToggleButton toggleButton) {
            toggleButton.setUserData(null);
            toggleButton.setSelected(false);
            changeFeedingWinding(feedingWindingButton, windingOne, windingTwo);
            ground();
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
        } else {
            circle1.setStroke(Color.BLUE);
            circle2.setStroke(Color.GREEN);
        }
    }

    //Метод по отображению значка земли по нажатию кнопки
    public void ground() {
        imageGround.setImage(ground);
        commonMethodForRightSideButtons(groundButton);
        imageGround.setVisible(groundButton.isSelected());
    }

    //метод для настройки кнопок в правой части окна сценария диф.защиты
    public void setupRightSideButtons(ToggleButton button) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK, 3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0, button);
    }

    //метод для изменения выделения кнопок в правой части окна сценария диф.защиты
    public void changeColorRightSideButtons(ToggleButton button) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.ORANGE, 3, 17, 15, ApplicationConstants.colours.BLACK, 26, 0, button);
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        InterfaceElementsSettings.textFieldSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK, 3, 17, 15, ApplicationConstants.colours.BLACK, 20, 0, textField, prompt);
    }

    //Метод для настройки кнопок соединения обмоток и контактов
    public void setupConnectionSchemesButtons(Button button, ImageView imageView, int width, int height) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK, 3, 17, 15, ApplicationConstants.colours.WHITE, 0, imageView, null, button, width, height, false);
    }

    //Метод для настройки кнопок номеров групп
    public void setupConnectionSchemesButtons(Button button) {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.colours.LIGHT_BLUE, ApplicationConstants.colours.BLACK, 3, 17, 15, ApplicationConstants.colours.BLACK, 42, 0, button);
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
    private boolean commonMethodForPositionPicturesButtons(ImageView imageView, boolean status, Image imageIfTrue, Image imageIfFalse) {
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
    private void commonMethodForRightSideButtons(ToggleButton toggleButton, String textIfSelected, String textIfNotSelected) {
        if (toggleButton.isSelected()) {
            toggleButton.setText(textIfSelected);
            setupRightSideButtons(toggleButton);
        } else {
            toggleButton.setText(textIfNotSelected);
            changeColorRightSideButtons(toggleButton);
        }
    }

    /**
     * Блок методов определяющий работу формы после перехода из формы проверок.
     * Здесь на 5 секунд все кнопки будут заблокированы и во время ожидания будет моргать молния.
     * По истечении 5 секунд кнопки будут разблокированы.
     * Это удалось достигнуть в результате изменения общего метода @switchScene. В него просто добавлен флаг, который
     * меняется в зависимости от перехода из формы в форму на true false
     */
    //Метод по блокировке и анимированию
    private void workForms() {
        lockAll(true);
        startLightingAnimation();
        // Создаем задержку на 5 секунд
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(_ -> {
            stopLightningAnimation();
            lockAll(false);
        });
        pause.play();
    }

    //Общий метод по обходу всех элементов и блокировки их.
    private void lockAll(boolean lock) {
        InterfaceElementsLogic.walk(mainPane, lock, (node, shouldDisable) -> {
            if (node instanceof Control) {
                ((Control) node).setDisable(shouldDisable);
            }
        }, node -> "startButton".equals(node.getId()));

        //Метод по изменению названий кнопок
        if (lock) {
            toMenuButton.setText("");
            startButton.setText("СТОП");
            cleanButton.setText("");
            startButton.setOnAction(event -> stopLightningAnimation());
        } else {

            toMenuButton.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            startButton.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            cleanButton.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);

            toMenuButton.setText("ПРОДОЛЖИТЬ");
            startButton.setText("ЗАКОНЧИТЬ");
            cleanButton.setText("СОХРАНИТЬ");
        }
    }

    //Тестовая функция по анимации картинок
    private void startLightingAnimation() {
        // Создаем Timeline для смены картинок каждые 0.5 секунд
        timeline = new Timeline(new KeyFrame(Duration.seconds(0), _ -> imageShortCircuit.setImage(shortCircuitYellow)), new KeyFrame(Duration.seconds(0.3), _ -> imageShortCircuit.setImage(shortCircuitRed)), new KeyFrame(Duration.seconds(0.6), _ -> imageShortCircuit.setImage(shortCircuitYellow)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    //остановка анимации
    private void stopLightningAnimation() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }
}
