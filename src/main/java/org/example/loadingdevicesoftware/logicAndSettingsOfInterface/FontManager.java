package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;

public class FontManager {

    public enum FontWeight {
        LIGHT, MEDIUM
    }

    public enum FontSize {
        NORMAL, LARGE
    }

    /*private static final Font APP_FONT_LIGHT = Font.loadFont(FontManager.class.
            getResourceAsStream("PFDinTextCondProLight.ttf"), 14);

    private static final Font APP_FONT_MEDIUM = Font.loadFont(FontManager.class.
            getResourceAsStream("PFDinTextCondProMedium.ttf"), 14);*/

    private static final Font APP_FONT_LIGHT;
    private static final Font APP_FONT_MEDIUM;

    static {
        APP_FONT_LIGHT = loadFont("PFDinTextCondProLight.ttf");
        APP_FONT_MEDIUM = loadFont("PFDinTextCondProMedium.ttf");
    }

    private static Font loadFont(String filename) {
        try (InputStream is = FontManager.class.getResourceAsStream(filename)) {
            if (is == null) {
                throw new RuntimeException("Файл шрифта не найден: " + filename);
            }
            Font font = Font.loadFont(is, 14);
            if (font == null) {
                throw new RuntimeException("Не удалось загрузить шрифт: " + filename);
            }
            return font;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки шрифта", e);
        }
    }

    public static Font getFont(FontWeight fontWeight, FontSize fontSize) {
        String family = switch (fontWeight) {
            case LIGHT -> APP_FONT_LIGHT.getFamily();
            case MEDIUM -> APP_FONT_MEDIUM.getFamily();
        };

        double size = switch (fontSize) {
            case NORMAL -> 24;
            case LARGE -> 36;
        };

        return Font.font(family, size);
    }
}
