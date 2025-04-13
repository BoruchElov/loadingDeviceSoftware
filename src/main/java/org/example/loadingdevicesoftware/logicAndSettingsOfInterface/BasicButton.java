package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

public class BasicButton extends Button {

    @Getter
    @Setter
    private String state;

    private int[] measures;

    public enum Status {
        NORMAL, LOCKED, ENABLED, WARNING, DEFAULT
    }

    private enum Size {
        SMALL, MEDIUM, LARGE
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public BasicButton() {
        super();
    }

    public BasicButton(String text, ApplicationConstants.basicColours backgroundColor,
                       int width, int height) {
        super();
        setText(text);
        String color = InterfaceElementsSettings.setColour(backgroundColor);
        setStyle("-fx-background-color: " + backgroundColor);
        setPrefSize(width, height);
    }

    public BasicButton(Presets preset) {
        super();
        switch (preset) {
            case CLEAR:
                InterfaceElementsSettings.buttonSettings(ApplicationConstants.basicColours.GRAY, ApplicationConstants.basicColours.BLACK,
                        1, 0, 0, ApplicationConstants.basicColours.BLACK,
                        12, this);
                setText("ОЧИСТИТЬ");
                setMeasures(Size.LARGE);
                break;
            case SAVE:
            case CONTINUE:
            case FINISH:
                break;
            case MENU:
            case STOP:
            case START:
            case CANCEL:
                setMeasures(Size.MEDIUM);
                break;
        }
        setPrefSize(measures[0], measures[1]);
    }

    private void setMeasures(Size size) {
        measures = switch (size) {
            case SMALL -> new int[]{0, 1};
            case MEDIUM -> new int[]{1, 0};
            case LARGE -> new int[]{210, 64};
        };
    }


}
