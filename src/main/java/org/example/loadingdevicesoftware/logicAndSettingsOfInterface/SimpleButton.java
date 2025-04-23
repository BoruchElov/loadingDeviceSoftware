package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;


public class SimpleButton extends BasicButton {

    public enum Status {
        NORMAL, LOCKED, ENABLED, WARNING
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public SimpleButton() {
        super();
    }

    public void setActualStatus(Status status) {
        ObjectStatus.StatusOfObjectExtended newStatus = ObjectStatus.StatusOfObjectExtended.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    public void setup (String normalStyle, String lockedStyle, String enabledStyle, String warningStyle,
                       String normalText, String lockedText, String enabledText, String warningText, String defaultText,
                       String[] positionsStyles, String[] positionsTexts) {
        setupPositions(positionsStyles, positionsTexts);
        objectStatus = new ObjectStatus(normalText, lockedText, enabledText, warningText, normalStyle,
                lockedStyle, enabledStyle, warningStyle, basicStyle);
        setText(defaultText);
    }

    private void setupPositions(String[] styles, String[] texts) {
        objectPosition = new Position(basicStyle, styles, texts);
    }

    public void setup(Presets preset) {

        String text;
        switch (preset) {
            case CLEAR:
                text = "ОЧИСТИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case SAVE:
                text = "СОХРАНИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case CONTINUE:
                text = "ПРОДОЛЖИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case FINISH:
                text = "ЗАКОНЧИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case MENU:
                text = "МЕНЮ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case STOP:
                text = "ОСТАНОВИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case START:
                text = "ПУСК";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
            case CANCEL:
                text = "ОТМЕНА";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", text, text, text, text, text, new String[]{"button-low-normal"},
                        new String[]{text});
                break;
        }
    }
}
