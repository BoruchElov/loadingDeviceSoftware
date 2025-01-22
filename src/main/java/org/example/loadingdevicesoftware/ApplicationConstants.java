package org.example.loadingdevicesoftware;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Класс, содержащий константы приложения: используемые цвета, название шрифта, размеры окна и т.д.
 */
public class ApplicationConstants {

    //Размеры окна приложения
    public static final int APPLICATION_WINDOW_LENGTH = 1280;
    public static final int APPLICATION_WINDOW_HEIGHT = 800;

    //Константы для цветов интерфейса
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
    public enum colours {
        BLACK, BLUE, LIGHT_BLUE, WHITE, ORANGE
    }

    public static final String BLACK_HEX = "#221E1F";
    public static final String BLUE_HEX = "#0F5D9C";
    public static final String LIGHT_BLUE_HEX = "#CFECF8";
    public static final String WHITE_HEX = "#FFFFFF";
    public static final String ORANGE_HEX = "#F9AE40";

    public static final String BLACK_WORD = "black";
    public static final String WHITE_WORD = "white";

    //Константы для текста
    public static final String FONT_NAME = "Myriad Pro";

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
    public static final Image NORMALLY_CLOSED_CONTACT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/иконкаНормЗамкКонт.png")).toExternalForm());
    public static final Image NORMALLY_OPENED_CONTACT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/screen/BasePictures/иконкаНормРазомкКонт.png")).toExternalForm());

}
