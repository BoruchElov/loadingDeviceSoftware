package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

/**
 * Класс, содержащий константы приложения: используемые цвета, название шрифта, размеры окна и т.д.
 */
public class ApplicationConstants {

    /**
     * <p>Данные константы обозначают разрешённые в приложении цвета,
     * соответствующие брендбуку Россетей.</p>
     * <p>Каждый цвет соответствует следующему коду в формате HEX:</p>
     * <ul>
     * <li><b>BLACK</b> (чёрный) </li>
     * <li><b>BLUE</b> (синий) </li>
     * <li><b>DARK_BLUE</b> (тёмно-синий) </li>
     * <li><b>LIGHT_BLUE</b> (голубой) </li>
     * <li><b>WHITE</b> (белый)</li>
     * <ul>
     */
    //TODO Убрать после рефакторинга
    public enum colours {
        BLACK, BLUE, LIGHT_BLUE, WHITE, ORANGE
    }

    public enum basicColours {
        WHITE, BLACK, GREEN, GRAY, BLUE, RED
    }
    public static final String White = "#FFFFFF";
    public static String Black = "#000000";
    public static String Green = "#6AB74D";
    public static String Gray = "#CBCCCB";
    public static String Blue = "#005286";
    public static String Red = "#D9232A";

    //Размеры окна приложения
    public static final int APPLICATION_WINDOW_LENGTH = 1280;
    public static final int APPLICATION_WINDOW_HEIGHT = 800;

    //Размеры кнопок {ширина, высота}
    public static final int[] SMALL_MEASURES = new int[]{60, 70};
    public static final int[] NORMAL_MEASURES = new int[]{200, 70};

    //TODO Убрать после рефакторинга
    //Константы для цветов интерфейса
    public static final String BLACK_HEX = "#221E1F";
    public static final String BLUE_HEX = "#0F5D9C";
    public static final String LIGHT_BLUE_HEX = "#CFECF8";
    public static final String WHITE_HEX = "#FFFFFF";
    public static final String ORANGE_HEX = "#F9AE40";
    public static final String BLACK_WORD = "black";
    public static final String WHITE_WORD = "white";
    //Константы для текста
    public static final String FONT_NAME = "Myriad Pro";
    //Текст на новых кнопках
    public static final String NEW_FONT_NAME_REGULAR = "PF Din Text Cond Pro";
    //Шрифт для сохранения файла
    public static final Font EXPORT_FONT;
    static {
        try {
            EXPORT_FONT = new Font(BaseFont.createFont("ofont.ru_Myriad Pro.ttf", BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED), 12, Font.NORMAL);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image STATUS_CONNECTED = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/иконкаЗеленыйКруг.png")).toExternalForm());
    public static final Image STATUS_DISCONNECTED = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/иконкаКрасныйКруг.png")).toExternalForm());
    public static final Image WHITE_BUTTON = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/Кнопка(белая).png")).toExternalForm());
    public static final Image WHITE_BUTTON_LONG = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/Кнопка(белая)_длинная.png")).toExternalForm());
    public static final Image BLACK_BUTTON = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/Кнопка(черная).png")).toExternalForm());
    public static final Image BLACK_BUTTON_LONG = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/Кнопка(черная)_длинная.png")).toExternalForm());
    public static final Image NORMALLY_CLOSED_CONTACT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/иконкаНормЗамкКонт.png")).toExternalForm());
    public static final Image NORMALLY_OPENED_CONTACT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/иконкаНормРазомкКонт.png")).toExternalForm());

    public static final Image NEW_BACKGROUND = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/New_Background.png")).toExternalForm());
}
