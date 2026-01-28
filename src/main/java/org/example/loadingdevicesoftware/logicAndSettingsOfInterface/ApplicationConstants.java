package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.HashMap;
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

    public static final String ACTUAL_VERSION = "1.0.0";

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
    public static final int APPLICATION_WINDOW_WIDTH = 1280;
    public static final int APPLICATION_WINDOW_HEIGHT = 800;

    //TODO Убрать после рефакторинга
    public static boolean expertStatus = false;
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

    public static final Image NEW_BACKGROUND = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/New_Background.png")).toExternalForm());
    public static final Image NEW_BASE_BACKGROUND = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Base_Background.png")).toExternalForm());
    public static final Image SINGLE_PHASE_SWITCH = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Single_Phase_Switch.png")).toExternalForm());
    public static final Image THREE_PHASE_SWITCH = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Three_Phase_Switch.png")).toExternalForm());
    public static final Image OPENED_CONTACT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Opened_Contact.png")).toExternalForm());
    public static final Image CLOSED_CONTACT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Closed_Contact.png")).toExternalForm());
    public static final Image INVERTER_IMAGE = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Inverter_new.png")).toExternalForm());
    public static final Image BACKGROUND_BLUE = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Background_Blue.png")).toExternalForm());
    public static final Image CHECK_SUCCESSFUL = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/CHECK_SUCCESS.png")).toExternalForm());
    public static final Image CHECK_FAILED = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/CHECK_FAILED.png")).toExternalForm());
    public static final Image ARROW_LEFT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Arrow_to_Left.png")).toExternalForm());
    public static final Image ARROW_RIGHT = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Arrow_to_Right.png")).toExternalForm());
    public static final Image GROUND = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Ground_Picture.png")).toExternalForm());
    public static final Image LIGHTNING = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Lightning.png")).toExternalForm());
    public static final Image LIGHTNING_RED = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/Lightning_Red.png")).toExternalForm());
    public static final Image CURRENT_TRANSFRMER = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/currentTransformer.png")).toExternalForm());

    //иконка вольтметра и амперметра
    public static final Image AMPERMETR = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/DeviceAV.png")).toExternalForm());
    public static final Image VOLTMETR = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/DeviceAV.png")).toExternalForm());

    //Иконки главного экрана
    public static final Image SETTINGS = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/settings.png")).toExternalForm());
    public static final Image SWITCHER_TEST = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/switcher.png")).toExternalForm());
    public static final Image RELAY_PROTECTION = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/relay.png")).toExternalForm());
    public static final Image EVENT_LOGGER = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/event.png")).toExternalForm());
    public static final Image MEASUREMENT_TRANSFORMER = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/transformer.png")).toExternalForm());
    public static final Image COMTRADE = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/comtrade.png")).toExternalForm());
    public static final Image DIFFERENTIAL_PROTECTION = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/differential.png")).toExternalForm());
    public static final Image HAND_CONTROL = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/hand.png")).toExternalForm());
    public static final Image DEBUGGER = new Image(Objects.requireNonNull(ApplicationConstants.class.
            getResource("/BasePictures/debugger.png")).toExternalForm());


}
