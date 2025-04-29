package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;


import javafx.scene.text.Font;

public class SimpleButton extends BasicButton {

    public enum Status {
        NORMAL, LOCKED
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public SimpleButton() {
        super();
    }

    public void setActualStatus(Status status) {
        ObjectStatus.StatusOfObject newStatus = ObjectStatus.StatusOfObject.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    public void setup (String[] positionsStyles, String[] positionsTexts, Font font) {
        setFont(font);
        setupPositions(positionsStyles, positionsTexts);
        objectPosition.setActualPosition(0, this);
    }

    private void setupPositions(String[] styles, String[] texts) {
        objectPosition = new Position(styles, texts);
    }

    public void setup(Presets preset) {
        Font font;
        String text;
        switch (preset) {
            case CLEAR:
                text = "ОЧИСТИТЬ";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case SAVE:
                text = "СОХРАНИТЬ";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case CONTINUE:
                text = "ПРОДОЛЖИТЬ";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case FINISH:
                text = "ЗАКОНЧИТЬ";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case MENU:
                text = "МЕНЮ";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case STOP:
                text = "ОСТАНОВИТЬ";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case START:
                text = "ПУСК";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
            case CANCEL:
                text = "ОТМЕНА";
                font = FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE);
                setup(new String[]{"button-low-normal", "button-low-enabled", "button-low-warning"},
                        new String[]{text, text, text}, font);
                break;
        }
    }
}
