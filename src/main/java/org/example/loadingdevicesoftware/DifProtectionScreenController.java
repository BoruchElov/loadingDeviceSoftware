package org.example.loadingdevicesoftware;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DifProtectionScreenController {

    private boolean windingOneStatus = false;
    private boolean windingTwoStatus = false;
    private boolean contactOneStatus = false;
    private boolean contactTwoStatus = false;

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;

    private Stage stageForStartScreen;
    private Scene sceneForStartScreen;
    private Parent rootForStartScreen;

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
    //Объявление объекта для получения текущих значений даты и времени
    LocalDateTime currentDateTime;
    //Создание объекта для задания форматирования значений даты и времени
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
    //Объявление объекта для выполнения команды по заданному расписанию
    private ScheduledExecutorService scheduler;

    //Объекты картинок групп соединения обмоток
    Image deltaConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Polygon1.png")).toExternalForm());
    Image starConnection = new Image(Objects.requireNonNull(getClass().
            getResource("/images/Star1.png")).toExternalForm());

    //Объекты картинок контактов
    Image normallyClosedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаНормЗамкКонт.png")).toExternalForm());
    Image normallyOpenedContact = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаНормРазомкКонт.png")).toExternalForm());
    
    //Объекты фоновых картинок
    Image backImageOutSC = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/диф_защита_1форма(без кнопок).png")).toExternalForm());
    
    //Папка RS_I
    Image RSI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_._I.png")).toExternalForm());
    Image RSAI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_A_I.png")).toExternalForm());
    Image RSABI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_AB_I.png")).toExternalForm());
    Image RSABCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_ABC_I.png")).toExternalForm());
    Image RSACI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_AC_I.png")).toExternalForm());
    Image RSBI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_B_I.png")).toExternalForm());
    Image RSBCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_BC_I.png")).toExternalForm());
    Image RSCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_I/RS_C_I.png")).toExternalForm());
    //Папка RS_O
    Image RSO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_._O.png")).toExternalForm());
    Image RSAO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_A_O.png")).toExternalForm());
    Image RSABO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_AB_O.png")).toExternalForm());
    Image RSABCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_ABC_O.png")).toExternalForm());
    Image RSACO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_AC_O.png")).toExternalForm());
    Image RSBO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_B_O.png")).toExternalForm());
    Image RSBCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_BC_O.png")).toExternalForm());
    Image RSCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/RS_O/RS_C_O.png")).toExternalForm());
    //Папка SR_I
    Image SRI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_._I.png")).toExternalForm());
    Image SRAI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_A_I.png")).toExternalForm());
    Image SRABI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_AB_I.png")).toExternalForm());
    Image SRABCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_ABC_I.jpg")).toExternalForm());
    Image SRACI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_AC_I.png")).toExternalForm());
    Image SRBI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_B_I.png")).toExternalForm());
    Image SRBCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_BC_I.png")).toExternalForm());
    Image SRCI = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_I/SR_C_I.png")).toExternalForm());
    //Папка SR_O
    Image SRO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_._O.png")).toExternalForm());
    Image SRAO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_A_O.png")).toExternalForm());
    Image SRABO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_AB_O.png")).toExternalForm());
    Image SRABCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_ABC_O.png")).toExternalForm());
    Image SRACO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_AC_O.png")).toExternalForm());
    Image SRBO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_B_O.png")).toExternalForm());
    Image SRBCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_BC_O.png")).toExternalForm());
    Image SRCO = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/SR_O/SR_C_O.png")).toExternalForm());
    
    
    //Объекты картинок для кнопок и статусов инверторов
    Image lowButtoncImage = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаРамкаПуска.png")).toExternalForm());
    Image statusConnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаЗеленыйКруг.png")).toExternalForm());
    Image statusDisconnected = new Image(Objects.requireNonNull(getClass().
            getResource("/screen/дифзащита/icon_for_DZ/иконкаКрасныйКруг.png")).toExternalForm());

    @FXML
    public void initialize() {
        //Настройка стилей текстовых полей для ввода
        setupObjectNameField(objectNameTextField, "Введите название объекта");
        setupObjectNameField(phaseA1TextField, "Ток А1, А");
        setupObjectNameField(phaseA2TextField, "Ток А2, А");
        setupObjectNameField(phaseB1TextField, "Ток В1, А");
        setupObjectNameField(phaseB2TextField, "Ток В2, А");
        setupObjectNameField(phaseC1TextField, "Ток С1, А");
        setupObjectNameField(phaseC2TextField, "Ток С2, А");
        //Задание изображения для статуса инвертора
        inverterA1Status.setImage(statusConnected);
        inverterA2Status.setImage(statusConnected);
        inverterB1Status.setImage(statusConnected);
        inverterB2Status.setImage(statusConnected);
        inverterC1Status.setImage(statusConnected);
        inverterC2Status.setImage(statusConnected);
        //Инициализация планировщика задач
        scheduler = Executors.newSingleThreadScheduledExecutor();
        //Вызов метода для запуска задачи по обновлению строки даты и времени
        startUpdatingDateAndTime();
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
        stopUpdatingDateAndTime();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("baseWindow.fxml"));
        rootForMainScreen = loader.load();

        MainScreenController mainController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForMainScreen = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForMainScreen = new Scene(rootForMainScreen);
        stageForMainScreen.setScene(sceneForMainScreen);
        stageForMainScreen.show();
    }
    @FXML
    public void goToStartScreen (ActionEvent event) throws IOException {
        stopUpdatingDateAndTime();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DifProtectionStart.fxml"));
        rootForStartScreen = loader.load();

        DifProtectionSecondScreenController StartController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForStartScreen = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForStartScreen = new Scene(rootForStartScreen);
        stageForStartScreen.setScene(sceneForStartScreen);
        stageForStartScreen.show();
    }
    @FXML
    public void setPictureForWindingOne() {
        windingOneView.setVisible(true);
        if(windingOneStatus) {
            windingOneView.setImage(starConnection);
            windingOneStatus = false;
        } else {
            windingOneView.setImage(deltaConnection);
            windingOneStatus = true;
        }
    }
    @FXML
    public void setPictureForWindingTwo() {
        windingTwoView.setVisible(true);
        if(windingTwoStatus) {
            windingTwoView.setImage(starConnection);
            windingTwoStatus = false;
        } else {
            windingTwoView.setImage(deltaConnection);
            windingTwoStatus = true;
        }
    }
    @FXML
    public void setPictureForContactOne() {
        contactOneView.setVisible(true);
        if(contactOneStatus) {
            contactOneView.setImage(normallyClosedContact);
            contactOneStatus = false;
        } else {
            contactOneView.setImage(normallyOpenedContact);
            contactOneStatus = true;
        }
    }
    @FXML
    public void setPictureForContactTwo() {
        contactTwoView.setVisible(true);
        if(contactTwoStatus) {
            contactTwoView.setImage(normallyClosedContact);
            contactTwoStatus = false;
        } else {
            contactTwoView.setImage(normallyOpenedContact);
            contactTwoStatus = true;
        }
    }

    //Метод для выполнения задачи обновления строки даты-времени
    public void startUpdatingDateAndTime() {
        Runnable updateDateTimeTask = () -> {
            currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);

            // Обновляем текстовое поле в JavaFX Application Thread
            Platform.runLater(() -> dateTimeText.setText(formattedDateTime));
            Platform.runLater(() -> System.out.println(1));
        };
        // Запуск задачи с интервалом в 1 минуту
        scheduler.scheduleAtFixedRate(updateDateTimeTask, 0, 1, TimeUnit.SECONDS);
    }
    //Метод для остановки обновления строки даты-времени
    public void stopUpdatingDateAndTime() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    //метод для настройки кнопок в правой части окна сценария диф.защиты
    public void setupRightSideButtons(ToggleButton button) {
        button.setStyle("-fx-background-color: #CFECF8; " + // Голубой фон
                "-fx-border-color: #221E1F; " + // Чёрная граница
                "-fx-border-width: 3px; " +        // Ширина границы
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-border-radius: 15px; " +     // Закругление границы (совпадает с фоном)
                "-fx-text-fill: black; " +        // Цвет текста
                "-fx-font-size: 26px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }
    //метод для изменения выделения кнопок в правой части окна сценария диф.защиты
    public void changeColorRightSideButtons(ToggleButton button) {
        button.setStyle("-fx-background-color: #CFECF8; " + // Голубой фон
                "-fx-border-color: #F9AE40; " + // Чёрная граница
                "-fx-border-width: 3px; " +        // Ширина границы
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-border-radius: 15px; " +     // Закругление границы (совпадает с фоном)
                "-fx-text-fill: black; " +        // Цвет текста
                "-fx-font-size: 26px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }

    //Метод для настройки параметров текстового поля с названием объекта
    public void setupObjectNameField(TextField textField, String prompt) {
        //Настройка стиля текстового поля
        textField.setStyle("-fx-background-color: #CFECF8; " + // Голубой фон
                "-fx-border-color: #221E1F; " + // Чёрная граница
                "-fx-border-width: 3px; " +        // Ширина границы
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-border-radius: 15px; " +     // Закругление границы (совпадает с фоном)
                "-fx-text-fill: black; " +        // Цвет текста
                "-fx-font-size: 20px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
        //Задание текста по умолчанию
        textField.setPromptText(prompt);
        //Выравнивание по центру
        textField.setAlignment(javafx.geometry.Pos.CENTER);
        //Код для отключения мигания каретки при вводе
        textField.setOnAction(event -> {
            textField.getParent().requestFocus();
        });
    }
    //Метод для настройки кнопок соединения обмоток
    public void setupConnectionSchemesButtons(Button button, ImageView imageView, double width, double height) {
        button.setStyle("-fx-background-color: #CFECF8; " + // Голубой фон
                "-fx-border-color: #221E1F; " + // Тёмно-синяя граница
                "-fx-border-width: 3px; " + // Ширина границы
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-border-radius: 15px; " + // Закругление границы
                "-fx-text-fill: white;" +
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; "); // Цвет текста
        imageView.setVisible(false);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    //Метод для настройки кнопок в нижней части окна сценария диф.защиты
    public void setupBottomButtons(Button button, ImageView imageView, Image image, String text) {
        imageView.setImage(image);
        imageView.setFitWidth(138);
        imageView.setFitHeight(64);
        button.setText(text);
        button.setStyle("-fx-background-color: #0F5D9C; " + // Синий фон
                "-fx-background-radius: 17px; " + // Закругление фона
                "-fx-text-fill: white; " +        // Цвет текста
                "-fx-font-size: 26px; " +         // Размер текста
                "-fx-font-family: 'Myriad Pro'; " +    // Шрифт текста
                "-fx-padding: 0; " +              // Убираем отступы
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }
    //Тестовый метод для проверки работы кнопки
    public void testClick() {
        System.out.println("Кнопка работает");
    }
    
    //Метод для поиска нужного изображения
    public Image selectImage(boolean shortCircuitLocation, boolean phaseA, boolean phaseB, boolean phaseC,
                                     boolean sendingWinding) {
        /**
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

    //Метод, запускающийся при нажатии на кнопку "Выбор места повреждения"
    public void shortCircuitLocation() {
        if (shortCircuitLocationButton.isSelected()) {
            shortCircuitLocationButton.setText("ВНУТРЕННЕЕ КЗ");
            changeColorRightSideButtons(shortCircuitLocationButton);
        } else {
            shortCircuitLocationButton.setText("ВНЕШНЕЕ КЗ");
            setupRightSideButtons(shortCircuitLocationButton);
        }
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
    }
    //Метод, запускающийся при нажатии на кнопку "Фаза А"
    public void phaseA() {
        if (phaseAButton.isSelected()) {
            changeColorRightSideButtons(phaseAButton);
        } else {
            setupRightSideButtons(phaseAButton);
        }
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
    }
    //Метод, запускающийся при нажатии на кнопку "Фаза В"
    public void phaseB() {
        if (phaseBButton.isSelected()) {
            changeColorRightSideButtons(phaseBButton);
        } else {
            setupRightSideButtons(phaseBButton);
        }
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
    }
    //Метод, запускающийся при нажатии на кнопку "Фаза С"
    public void phaseC() {
        if (phaseCButton.isSelected()) {
            changeColorRightSideButtons(phaseCButton);
        } else {
            setupRightSideButtons(phaseCButton);
        }
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
    }
    //Метод, запускающийся при нажатии на кнопку "Выбор питающей обмотки"
    public void feedingWinding() {
        if (feedingWindingButton.isSelected()) {
            feedingWindingButton.setText("II");
            changeColorRightSideButtons(feedingWindingButton);
        } else {
            feedingWindingButton.setText("I");
            setupRightSideButtons(feedingWindingButton);
        }
        backgroundImageView.setImage(selectImage(shortCircuitLocationButton.isSelected(), phaseAButton.isSelected(),
                phaseBButton.isSelected(),phaseCButton.isSelected(), feedingWindingButton.isSelected()));
    }

}
