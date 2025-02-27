package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    /**
     * Класс для генерации таблиц в Пдф. Класс содержит следующие поля:
     * private PdfPTable table;
     * private List<List<PdfPCell>> cellGrid;
     * private int columns;
     * private int rows;
     */

    // Поля класса
    private PdfPTable table;
    private List<List<PdfPCell>> cellGrid;
    private int columns;
    private int rows;
    private PdfPTable table1;

    private BaseFont baseFont = BaseFont.createFont("C:\\Users\\ing8\\IdeaProjects\\test\\ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    private Font font = new Font(baseFont, 12, Font.NORMAL);

    //конструктор класса
    public TableCreator(int rows, int columns) throws DocumentException, IOException {
        this.columns = columns;
        this.rows = rows;
        table1 = createTable(rows, columns);
    }

    public PdfPTable getTable(){
        return table1;
    }

    // Основной метод для создания таблицы
    private PdfPTable createTable(int rows, int columns){
        table = new PdfPTable(columns);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Инициализация сетки ячеек
        cellGrid = new ArrayList<>();

        // Создание строк и ячеек
        for (int i = 0; i < rows; i++) {
            List<PdfPCell> rowCells = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                PdfPCell cell = createCell("", "");
                rowCells.add(cell);
                table.addCell(cell);
            }
            cellGrid.add(rowCells);
        }
        return table;
    }

    // Метод для создания ячейки с подстрочными символами
    private PdfPCell createCell(String base, String interLinear) {

        Chunk baseText = new Chunk(base, font);
        Chunk subscriptText = new Chunk(interLinear, font);

        subscriptText.setTextRise(-3); // Смещение вниз для нижнего индекса
        Phrase phrase = new Phrase();
        phrase.add(baseText);
        phrase.add(subscriptText);
        PdfPCell cell = new PdfPCell(phrase);

        cell.setMinimumHeight(35);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);

        return cell;
    }

    // Метод для установки текста в ячейку
    public void setCellContent(int row, int column, String base, String interLinear) {
        if (row >= 0 && row < cellGrid.size() && column >= 0 && column < cellGrid.get(row).size()) {
            // Получаем ячейку из сетки
            PdfPCell cell = cellGrid.get(row).get(column);
            // Удаляем старую ячейку из таблицы
            table.getRow(row).getCells()[column] = null;
            // Создаем новую ячейку с обновленным содержимым
            PdfPCell newCell = createCell(base, interLinear);
            // Заменяем ячейку в сетке
            cellGrid.get(row).set(column, newCell);
            // Добавляем новую ячейку в таблицу
            table.getRow(row).getCells()[column] = newCell;
        } else {
            throw new IllegalArgumentException("Недопустимые индексы строки или столбца");
        }
    }

    //метод для объединения двух ячеек в одной строке
    public void mergeCellsInOneRow(int row, int startColumn, int endColumn, String content, String interLinear) {
        // Проверяем, что startColumn и endColumn находятся в пределах таблицы
        if (startColumn >= endColumn || endColumn >= table.getNumberOfColumns()) {
            throw new IllegalArgumentException("Недопустимые индексы столбцов");
        }
        // Удаляем ячейки, которые будут объединены
        for (int i = startColumn; i <= endColumn; i++) {
            table.getRow(row).getCells()[i] = null;
        }
        // Создаем новую ячейку с объединением
        PdfPCell mergedCell = createCell(content, interLinear);
        mergedCell.setColspan(endColumn - startColumn + 1);
        // Добавляем объединенную ячейку в таблицу
        table.getRow(row).getCells()[startColumn] = mergedCell;
    }

    //метод для объединения двух ячеек в одном столбце
    public void mergeCellsInOneColumn(int column, int startRow, int endRow, String base, String interLinear) {
        // Проверяем, что startRow и endRow находятся в пределах таблицы
        if (startRow >= endRow || endRow >= table.getRows().size()) {
            throw new IllegalArgumentException("Недопустимые индексы строк");
        }
        // Удаляем ячейки, которые будут объединены
        for (int i = startRow; i <= endRow; i++) {
            table.getRow(i).getCells()[column] = null;
        }
        // Создаем новую ячейку с объединением
        PdfPCell mergedCell = createCell(base, interLinear);
        mergedCell.setRowspan(endRow - startRow + 1);
        // Добавляем объединенную ячейку в таблицу
        table.getRow(startRow).getCells()[column] = mergedCell;
    }
}


