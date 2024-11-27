package org.example.loadingdevicesoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class SettingsScreenController {

    private Stage stageForMainScreen;
    private Scene sceneForMainScreen;
    private Parent rootForMainScreen;

    String workingDirectory;

    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
    @FXML
    ImageView image5;
    @FXML
    ImageView image6;

    Image inverterImage = new Image(Objects.requireNonNull(getClass().getResource("/images/icons8-dynamo-96.png")).toExternalForm());

    String[] Text = new String[2];

    @FXML
    private TextField directoryField; // Поле для отображения выбранной директории
    @FXML
    private Button backToMenuButton;
    @FXML
    private Button choiceOfWorkingDirectory;

    private static final String CONFIG_FILE = "directory.config";
    private String settingsFilePath; // Путь к файлу настроек в рабочей директории

    private final int PAIR_COUNT = 6; // Количество пар адрес=фаза

    @FXML
    public void initialize() {

        image1.setImage(inverterImage);
        image2.setImage(inverterImage);
        image3.setImage(inverterImage);
        image4.setImage(inverterImage);
        image5.setImage(inverterImage);
        image6.setImage(inverterImage);

        // Проверяем или создаем путь к файлу directory.config
        File configFile = new File(CONFIG_FILE);

        // Если файл отсутствует, создаём его с значением по умолчанию
        if (!configFile.exists()) {
            SettingsManager.saveWorkingDirectory(CONFIG_FILE, System.getProperty("user.home"));
        }

        // Загружаем рабочую директорию
        workingDirectory = SettingsManager.loadWorkingDirectory(CONFIG_FILE);

        // Установка пути к файлу настроек
        settingsFilePath = workingDirectory + File.separator + "settings.properties";

        // Пример загрузки настроек
        String[][] loadedSettings = SettingsManager.loadAddressPhaseSettings(settingsFilePath, PAIR_COUNT);
        for (int i = 0; i < PAIR_COUNT; i++) {
            System.out.println("Address: " + loadedSettings[0][i] + ", Phase: " + loadedSettings[1][i]);
        }
    }


    // Метод выбора рабочей директории
    @FXML
    public void chooseWorkingDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите рабочую директорию");

        // Устанавливаем начальную директорию
        File initialDirectory = new File(System.getProperty("user.home"));
        directoryChooser.setInitialDirectory(initialDirectory);

        // Открываем окно выбора директории
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            String selectedPath = selectedDirectory.getAbsolutePath();
            directoryField.setText(selectedPath);

            // Сохраняем путь рабочей директории
            SettingsManager.saveWorkingDirectory(CONFIG_FILE, selectedPath);

            // Обновляем путь к файлу настроек
            settingsFilePath = selectedPath + File.separator + "settings.properties";
        }
    }

    // Метод для сохранения адресов и фаз
    @FXML
    public void saveAddressPhaseSettings() {
        String[] addresses = {"addr1", "addr2", "addr3", "addr4", "addr5", "addr6"}; // Пример данных
        String[] phases = {"phase1", "phase2", "phase3", "phase4", "phase5", "phase6"}; // Пример данных

        SettingsManager.saveAddressPhaseSettings(settingsFilePath, addresses, phases);
    }
    @FXML
    public void goToMainScreen (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("baseWindow.fxml"));
        rootForMainScreen = loader.load();

        MainScreenController mainController = loader.getController();

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stageForMainScreen = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneForMainScreen = new Scene(rootForMainScreen);
        stageForMainScreen.setScene(sceneForMainScreen);
        stageForMainScreen.show();
    }

    }
