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

    //Massive for storing addresses values
    private String[] actualValuesOfAddresses = new String[6];
    private String[] actualValuesOfPhases = new String[6];
    private String[] baseListOfAddreses = {"MAC1", "MAC2", "MAC3", "MAC4", "MAC5", "MAC6"};
    private String[] baseListOfPhases = {"Фаза А1", "Фаза В1", "Фаза С1", "Фаза А2", "Фаза В2", "Фаза С2"};
    String[] Text = new String[2];

    @FXML
    private TextField directoryField; // Поле для отображения выбранной директории
    @FXML
    private Button backToMenuButton;
    @FXML
    private Button choiceOfWorkingDirectory;
    
    //Declaration of addresses ComboBoxes
    @FXML
    private ComboBox<String> addressComboBox1;
    @FXML
    private ComboBox<String> addressComboBox2;
    @FXML
    private ComboBox<String> addressComboBox3;
    @FXML
    private ComboBox<String> addressComboBox4;
    @FXML
    private ComboBox<String> addressComboBox5;
    @FXML
    private ComboBox<String> addressComboBox6;
    //Declaration of phases ComboBoxes
    @FXML
    private ComboBox<String> phaseComboBox1;
    @FXML
    private ComboBox<String> phaseComboBox2;
    @FXML
    private ComboBox<String> phaseComboBox3;
    @FXML
    private ComboBox<String> phaseComboBox4;
    @FXML
    private ComboBox<String> phaseComboBox5;
    @FXML
    private ComboBox<String> phaseComboBox6;

    //Declaration of buttons for saving and deleting settings for all ComboBoxes
    @FXML
    private Button saveAll;
    @FXML
    private Button clearAll;

    private static final String CONFIG_FILE = "directory.config";
    private String settingsFilePath; // Путь к файлу настроек в рабочей директории

    private final int PAIR_COUNT = 6; // Количество пар адрес=фаза

    @FXML
    public void initialize() {
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

        updateActualAddressPhase();
        directoryField.setText(workingDirectory);

        //Adding possible configurations to ComboBoxes
        addressComboBox1.getItems().addAll(baseListOfAddreses);
        addressComboBox2.getItems().addAll(baseListOfAddreses);
        addressComboBox3.getItems().addAll(baseListOfAddreses);
        addressComboBox4.getItems().addAll(baseListOfAddreses);
        addressComboBox5.getItems().addAll(baseListOfAddreses);
        addressComboBox6.getItems().addAll(baseListOfAddreses);

        phaseComboBox1.getItems().addAll(baseListOfPhases);
        phaseComboBox2.getItems().addAll(baseListOfPhases);
        phaseComboBox3.getItems().addAll(baseListOfPhases);
        phaseComboBox4.getItems().addAll(baseListOfPhases);
        phaseComboBox5.getItems().addAll(baseListOfPhases);
        phaseComboBox6.getItems().addAll(baseListOfPhases);

        //Setting values of saved parameters
        addressComboBox1.setValue(actualValuesOfAddresses[0]);
        addressComboBox2.setValue(actualValuesOfAddresses[1]);
        addressComboBox3.setValue(actualValuesOfAddresses[2]);
        addressComboBox4.setValue(actualValuesOfAddresses[3]);
        addressComboBox5.setValue(actualValuesOfAddresses[4]);
        addressComboBox6.setValue(actualValuesOfAddresses[5]);

        phaseComboBox1.setValue(actualValuesOfPhases[0]);
        phaseComboBox2.setValue(actualValuesOfPhases[1]);
        phaseComboBox3.setValue(actualValuesOfPhases[2]);
        phaseComboBox4.setValue(actualValuesOfPhases[3]);
        phaseComboBox5.setValue(actualValuesOfPhases[4]);
        phaseComboBox6.setValue(actualValuesOfPhases[5]);


        image1.setImage(inverterImage);
        image2.setImage(inverterImage);
        image3.setImage(inverterImage);
        image4.setImage(inverterImage);
        image5.setImage(inverterImage);
        image6.setImage(inverterImage);


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
        
        String[] addresses = {addressComboBox1.getValue(), addressComboBox2.getValue(), addressComboBox3.getValue(),
                addressComboBox4.getValue(), addressComboBox5.getValue(), addressComboBox6.getValue()}; 
        String[] phases = {phaseComboBox1.getValue(), phaseComboBox2.getValue(), phaseComboBox3.getValue(),
                phaseComboBox4.getValue(), phaseComboBox5.getValue(), phaseComboBox6.getValue()}; // Пример данных

        SettingsManager.saveAddressPhaseSettings(settingsFilePath, addresses, phases);

        addressComboBox1.setValue(addresses[0]);
        addressComboBox2.setValue(addresses[1]);
        addressComboBox3.setValue(addresses[2]);
        addressComboBox4.setValue(addresses[3]);
        addressComboBox5.setValue(addresses[4]);
        addressComboBox6.setValue(addresses[5]);

        phaseComboBox1.setValue(phases[0]);
        phaseComboBox2.setValue(phases[1]);
        phaseComboBox3.setValue(phases[2]);
        phaseComboBox4.setValue(phases[3]);
        phaseComboBox5.setValue(phases[4]);
        phaseComboBox6.setValue(phases[5]);
    }
    
    @FXML
    public void clearAddressPhaseSettings() {
        String[] addresses = {"Не выбрано", "Не выбрано", "Не выбрано", "Не выбрано", "Не выбрано", "Не выбрано"};
        String[] phases = {"Не выбрано", "Не выбрано", "Не выбрано", "Не выбрано", "Не выбрано", "Не выбрано"};

        SettingsManager.saveAddressPhaseSettings(settingsFilePath, addresses, phases);
        
        addressComboBox1.setValue(addresses[0]);
        addressComboBox2.setValue(addresses[1]);
        addressComboBox3.setValue(addresses[2]);
        addressComboBox4.setValue(addresses[3]);
        addressComboBox5.setValue(addresses[4]);
        addressComboBox6.setValue(addresses[5]);

        phaseComboBox1.setValue(phases[0]);
        phaseComboBox2.setValue(phases[1]);
        phaseComboBox3.setValue(phases[2]);
        phaseComboBox4.setValue(phases[3]);
        phaseComboBox5.setValue(phases[4]);
        phaseComboBox6.setValue(phases[5]);
    }
    
    @FXML
    public void updateActualAddressPhase() {
        for(int i = 0; i < 6; i++) {
            actualValuesOfAddresses[i] = SettingsManager.loadAddressPhaseSettings(settingsFilePath, 6)[0][i];
            actualValuesOfPhases[i] = SettingsManager.loadAddressPhaseSettings(settingsFilePath, 6)[1][i];
        }
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
