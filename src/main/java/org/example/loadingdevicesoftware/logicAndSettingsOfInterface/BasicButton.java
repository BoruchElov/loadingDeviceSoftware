package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

public class BasicButton extends Button {

    @Getter
    @Setter
    private String state;

    ButtonStatus actualStatus;
    ButtonPosition position;

    public enum Status {
        NORMAL, LOCKED, ENABLED, WARNING, DEFAULT
    }

    public enum Size {
        SMALL, NORMAL
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public BasicButton() {
        super();
    }

    public void setup (String normalStyle, String lockedStyle, String enabledStyle, String warningStyle, String defaultStyle,
                       String normalText, String lockedText, String enabledText, String warningText, String defaultText) {
        actualStatus = new ButtonStatus(normalText, lockedText, enabledText, warningText, defaultText, normalStyle,
                lockedStyle, enabledStyle, warningStyle, defaultStyle);
        setText(defaultText);
        changeActualStatus(Status.DEFAULT);
    }

    public void setup(Presets preset) {

        String text;
        switch (preset) {
            case CLEAR:
                text = "ОЧИСТИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case SAVE:
                text = "СОХРАНИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case CONTINUE:
                text = "ПРОДОЛЖИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case FINISH:
                text = "ЗАКОНЧИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case MENU:
                text = "МЕНЮ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case STOP:
                text = "ОСТАНОВИТЬ";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case START:
                text = "ПУСК";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
            case CANCEL:
                text = "ОТМЕНА";
                setup("button-low-normal", "button-low-locked", "button-low-enabled",
                        "button-low-warning", "button-low-default", text, text, text, text, text);
                break;
        }
    }

    public void changeActualStatus(Status status) {
        actualStatus.setStatus(status, this);
    }

    public void setupPositions(int positions, String basicStyle, String[] styles, String[] texts) {
        position = new ButtonPosition(positions, basicStyle, styles, texts);
    }

    public void changePosition(int position) {
        this.position.setActualPosition(position, this);
    }
}
