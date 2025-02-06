package org.example.loadingdevicesoftware;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class InterfaceElementsLogic {


    /**
     * Конструктор класса InterfaceElementsLogic. Данный класс содержит методы, используемые для настройки различных
     * функций интерфейса: переход со страницы на страницу.
     */
    public InterfaceElementsLogic() {}

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(InterfaceElementsLogic.class.
                getResource(fxmlFilePath)));

        // Устанавливаем новую сцену
        stage.setScene(new Scene(root, ApplicationConstants.APPLICATION_WINDOW_LENGTH,
                ApplicationConstants.APPLICATION_WINDOW_HEIGHT));
        stage.show();
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

    public static void writeToPdf (String text) {
        try {
            Document document = new Document();
            //создание файла
            PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
            document.open();
            //добавление заглавия ("Дата: 'dataText'    Время: 'timeText'")
            document.add(new Paragraph(text, ApplicationConstants.EXPORT_FONT));
            //закрываем файл, пишем об удачном создании
            document.close();
            System.out.println("Файл создан!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
