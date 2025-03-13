package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportGenerator {
    /**
     * Класс по созданию ПДФ отчета. В данном классе лежат основные методы для генерации элементов
     * пдф файла. В дальнейшем этот объект будет использоваться для создания отчета целиком.
     * Все элементы генерируются основываясь на текстовом массиве[][], который должен получаться из
     * буфера данных. Здесь еще остались две integer переменные, необходимые для генерации таблицы.
     * Позже они должны определяться в зависимости от сценария, который пришел в енаме
     */
    //нужное для методов работы
    static int rows = 7;
    static int columns = 6;

    //переменные для шрифтов и настроек
    static Font textFont;

    static Document document = new Document();


    // Центральный метод, который собирает элементы для отчета в пдф
    public static void generateReport(String[][] bufferData) throws DocumentException, IOException {
        Element[] reportElements = new Element[5];  //создание массива элементов для генератора пдф

        reportElements[0] = getHeader(bufferData);
        reportElements[1] = getScenarioInfo(bufferData);
        reportElements[2] = getScenarioTable(bufferData, rows, columns);
        reportElements[3] = getErrors(bufferData);
        reportElements[4] = getDivided();

        createPDF(reportElements, bufferData);
    }

    //Метод для создания пдф отчета
    private static void createPDF(Element[] reportElements, String[][] bufferData) throws DocumentException {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(bufferData[0][0]));
            document.open();
            document.add(reportElements[0]);
            document.add(reportElements[1]);
            document.add(reportElements[2]);
            document.add(reportElements[3]);
            document.add(reportElements[4]);

        } catch (FileNotFoundException | DocumentException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
            System.out.println("Файл создался в reportGenerator. Имя файла - " + bufferData[0][0]);
        }
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getHeader(String[][] bufferData) throws DocumentException, IOException {
        //настройки шрифта заголовка
        int v = 18;
        textFont = ApplicationConstants.EXPORT_FONT;

        Paragraph paragraph = new Paragraph("ПРОТОКОЛ «" + bufferData[1][0] + "»", textFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    //Метод для генерации заголовка в начале отчета.
    private static Paragraph getScenarioInfo(String[][] bufferData) {
        //настройки шрифта времени и даты
        int v = 14;
        textFont = ApplicationConstants.EXPORT_FONT;

        // формируем строку
        String strScenario = """
                Дата: %s Время: %s
                ФИО работника: %s
                Название объекта: %s
                Схема соединения трансформатора: %s
                Вид повреждения: %s
                Таблица 1. Параметры режима
                """.formatted(bufferData[2][0], bufferData[2][1], bufferData[2][2], bufferData[2][3], bufferData[2][4], bufferData[2][5]);

        Paragraph paragraph = new Paragraph(strScenario, textFont);
        return paragraph;
    }

    //Метод для создания таблицы
    private static PdfPTable getScenarioTable(String[][] bufferData, int rows, int columns) throws DocumentException, IOException {
        // Создаем таблицу
        TableCreator infoTable = new TableCreator(rows, columns);
        PdfPTable table = infoTable.getTable();
        //Заполняем ячейки данными
        //первая строчка
        infoTable.setCellContent(0, 0, "Параметры", "");
        infoTable.setCellContent(0, 1, "Значения, А", "");
        infoTable.setCellContent(0, 2, "Параметры", "");
        infoTable.setCellContent(0, 3, "Значения, град", "");
        infoTable.mergeCellsInOneRow(0, 4, 5, "Сработавшие контакты", "");
        //вторая строчка
        infoTable.setCellContent(1, 0, "I", "A1");
        infoTable.setCellContent(1, 1, bufferData[3][0], "");
        infoTable.setCellContent(1, 2, "φ", "A1");
        infoTable.setCellContent(1, 3, bufferData[3][1], "");
        //третья строчка
        infoTable.setCellContent(2, 0, "I", "B1");
        infoTable.setCellContent(2, 1, bufferData[3][2], "");
        infoTable.setCellContent(2, 2, "φ", "B1");
        infoTable.setCellContent(2, 3, bufferData[3][3], "");
        infoTable.mergeCellsInOneColumn(4, 1, 3, "1", "");
        infoTable.mergeCellsInOneColumn(5, 1, 3, "1", "");
        //четвертая строчка
        infoTable.setCellContent(3, 0, "I", "c1");
        infoTable.setCellContent(3, 1, bufferData[3][4], "");
        infoTable.setCellContent(3, 2, "φ", "С1");
        infoTable.setCellContent(3, 3, bufferData[3][5], "");
        //пятая строчка
        infoTable.setCellContent(4, 0, "I", "A2");
        infoTable.setCellContent(4, 1, bufferData[3][6], "");
        infoTable.setCellContent(4, 2, "φ", "A2");
        infoTable.setCellContent(4, 3, bufferData[3][7], "");
        //шестая строчка
        infoTable.setCellContent(5, 0, "I", "B2");
        infoTable.setCellContent(5, 1, bufferData[3][8], "");
        infoTable.setCellContent(5, 2, "φ", "B2");
        infoTable.setCellContent(5, 3, bufferData[3][9], "");
        infoTable.mergeCellsInOneColumn(4, 4, 6, bufferData[3][12], "");
        infoTable.mergeCellsInOneColumn(5, 4, 6, bufferData[3][13], "");
        //седьмая строчка
        infoTable.setCellContent(6, 0, "I", "С2");
        infoTable.setCellContent(6, 1, bufferData[3][10], "");
        infoTable.setCellContent(6, 2, "φ", "С2");
        infoTable.setCellContent(6, 3, bufferData[3][11], "");

        return table;
    }

    //Метод для вывода ошибки
    private static Paragraph getErrors(String[][] bufferData) {
        // Настройка шрифта
        int v = 14;
        textFont = ApplicationConstants.EXPORT_FONT;
        return new Paragraph("Аварийные сообщения: " + bufferData[4][0], textFont);
    }

    //Метод для выведения финальной черты
    private static Paragraph getDivided() {
        return new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
    }
}
