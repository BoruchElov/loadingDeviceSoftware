package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.loadingdevicesoftware.ApplicationFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(ApplicationFile.class.
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


    //я так понимаю это нам не нужно
//    public static Table getTable() throws BadElementException {
//        Table tableParametersOne;
//        tableParametersOne = new Table(2, 4);
//        tableParametersOne.setPadding(2);
//        tableParametersOne.setWidth(100);
//
//        Cell[] cellsArray = new Cell[8];
//
//        String textAlfa = "Альфа";
//
//        cellsArray[0] = new Cell(new Phrase(textAlfa, ApplicationConstants.EXPORT_FONT));
//        cellsArray[0].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[0]);
//
//
//        cellsArray[1] = new Cell(new Phrase("1", ApplicationConstants.EXPORT_FONT));
//        cellsArray[1].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[1]);
//
//        String textBeta = "Бета" ;
//
//        cellsArray[2] = new Cell(new Phrase(textBeta, ApplicationConstants.EXPORT_FONT));
//        cellsArray[2].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[2]);
//
//        cellsArray[3] = new Cell(new Phrase("2", ApplicationConstants.EXPORT_FONT));
//        cellsArray[3].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[3]);
//
//        String textGamma = "Gamma" ;
//
//        cellsArray[4] = new Cell(new Phrase(textGamma, ApplicationConstants.EXPORT_FONT));
//        cellsArray[4].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[4]);
//
//        cellsArray[5] = new Cell(new Phrase("3", ApplicationConstants.EXPORT_FONT));
//        cellsArray[5].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[5]);
//
//        String textShtrih = "Штрих" ;
//
//        cellsArray[6] = new Cell(new Phrase(textShtrih, ApplicationConstants.EXPORT_FONT));
//        cellsArray[6].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[6]);
//
//        cellsArray[7] = new Cell(new Phrase("4", ApplicationConstants.EXPORT_FONT));
//        cellsArray[7].setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableParametersOne.addCell(cellsArray[7]);
//
//        return tableParametersOne;
//    }
//
//    // это тоже
//    public static void writeToPdf (String fileName, Element[] elements) {
//        try {
//            Document document = new Document();
//            //создание файла
//            PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
//            document.open();
//            //добавление заглавия ("Дата: 'dataText'    Время: 'timeText'")
//            String dateAndTime = "Дата:   " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
//                    "      Время:   " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
//            document.add(new Paragraph(dateAndTime, ApplicationConstants.EXPORT_FONT));
//            for (int i = 0; i < elements.length; i++) {
//                document.add(elements[i]);
//            }
//            //закрываем файл, пишем об удачном создании
//            document.close();
//            System.out.println("Файл создан!");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
