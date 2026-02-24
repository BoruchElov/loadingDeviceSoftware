package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.text.Font;

public class FontManager {

    public enum FontWeight {
        LIGHT, MEDIUM
    }

    public enum FontSize {
        SMALL_TWELVE, SMALL_FOURTEEN, SMALL_EIGHTEEN, SMALL, NORMAL, LARGE
    }

    public static final Font APP_FONT_LIGHT = Font.loadFont(FontManager.class.
            getResourceAsStream("/PFDinTextCondProLight.ttf"), 14);

    public static final Font APP_FONT_MEDIUM = Font.loadFont(FontManager.class.
            getResourceAsStream("/PFDinTextCondProMedium.ttf"), 14);


    public static Font getFont(FontWeight fontWeight, FontSize fontSize) {
        String family = switch (fontWeight) {
            case LIGHT -> APP_FONT_LIGHT.getFamily();
            case MEDIUM -> APP_FONT_MEDIUM.getFamily();
        };

        double size = switch (fontSize) {
            case SMALL_TWELVE -> 12;
            case SMALL_FOURTEEN -> 14;
            case SMALL_EIGHTEEN -> 18;
            case SMALL -> 20;
            case NORMAL -> 24;
            case LARGE -> 36;
        };

        return Font.font(family, size);
    }
}
