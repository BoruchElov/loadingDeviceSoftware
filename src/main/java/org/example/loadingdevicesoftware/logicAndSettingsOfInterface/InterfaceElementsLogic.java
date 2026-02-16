package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import org.example.loadingdevicesoftware.ApplicationFile;
import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import java.util.Objects;


public class InterfaceElementsLogic {
    /**
     * Конструктор класса InterfaceElementsLogic. Данный класс содержит методы, используемые для настройки различных
     * функций интерфейса: переход со страницы на страницу.
     */
    private InterfaceElementsLogic() {}

    /**
     * Метод для перехода с одного экрана на другой.
     * @param node графический узел (в любом контроллере в качестве этой переменной должно задаваться
     *             <code>(Node) event.getSource()</code>
     * @param fxmlFilePath имя файла fxml в формате <b>fileName.fxml</b>
     * @throws IOException исключение
     */
    @FXML
    public static void switchScene(Node node, String fxmlFilePath) throws IOException {
        // Получаем текущий Stage из элемента интерфейса
        Stage stage = (Stage) node.getScene().getWindow();

        // Загружаем новый FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(ApplicationFile.class.
                getResource(fxmlFilePath)));

        Scene scene = new Scene(root, ApplicationConstants.APPLICATION_WINDOW_WIDTH,
                ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        scene.getStylesheets().add(ApplicationFile.class.
                getResource("/org/example/loadingdevicesoftware/applicationStyle.css").toExternalForm());
        // Устанавливаем новую сцену
        stage.setScene(scene);
        stage.show();
    }

    public enum Alert_Size{
        SMALL, MEDIUM, LARGE
    }

    @FXML
    public static Alert showAlert(String message, Alert_Size size, boolean isButtonNecessary) {

        int[] sizes = switch (size) {
            case SMALL -> new int[]{500,180};
            case MEDIUM -> new int[]{600,300};
            case LARGE -> new int[]{700,500};
        };

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.initStyle(StageStyle.UNDECORATED);

        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        Label label = new Label(message);
        label.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        label.setTextFill(Color.WHITE);

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("ЗАКРЫТЬ");
        okButton.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.NORMAL));
        okButton.setTextFill(Color.WHITE);
        okButton.setStyle("-fx-border-color: #FFFFFF;\n" +
                "    -fx-pref-width: 130px; /* Желаемая ширина */\n" +
                "    -fx-pref-height: 50px; /* Желаемая высота */\n" +
                "    -fx-min-width: 130px; /* Минимальная ширина */\n" +
                "    -fx-min-height: 50px; /* Минимальная высота */\n" +
                "    -fx-max-width: 130px;\n" +
                "    -fx-max-height: 50px;\n" +
                "    -fx-background-color: #005286;\n" +
                "    -fx-text-fill: #FFFFFF;\n" +
                "    -fx-border-width: 2;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-border-radius: 0;");
        if (!isButtonNecessary) {
            okButton.setVisible(false);
            okButton.setManaged(false);
        }

        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setStyle("-fx-background-color: #005286; -fx-padding: 0;" +
                "-fx-pref-width: " + sizes[0] + "; -fx-pref-height: " + sizes[1] + ";");
        alert.show();
        return alert;
    }

    @FXML
    public static void openFileManager() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите рабочую директорию");

        // Устанавливаем начальную директорию
        File initialDirectory = new File(System.getProperty("user.home"));
        directoryChooser.setInitialDirectory(initialDirectory);

        // Открываем окно выбора директории
        directoryChooser.showDialog(new Stage());
    }

    public static File openFileChooser(Stage ownerStage,
                                String description,
                                String... extensions) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        // Начальная директория
        File initialDirectory = new File(System.getProperty("user.home"));
        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        // Фильтр по типам
        FileChooser.ExtensionFilter filter =
                new FileChooser.ExtensionFilter(description, extensions);

        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);

        return fileChooser.showOpenDialog(ownerStage);
    }


    //тестовые функции для перехода
    /**
     * -- SETTER --
     *  Устанавливает флаг, указывающий, откуда был выполнен переход.
     * -- GETTER --
     *  Возвращает значение флага.
     */
    @Getter
    @Setter
    private static boolean fromCheckingStartConditions = false;
}

