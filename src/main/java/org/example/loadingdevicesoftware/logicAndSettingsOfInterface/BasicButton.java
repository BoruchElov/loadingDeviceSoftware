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
        SMALL, NORMAL
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public BasicButton() {
        super();
    }

    public void setup (Presets preset) {
        switch (preset) {
            case CLEAR:
                InterfaceElementsSettings.buttonSettings(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE,
                        2, 0, 0, ApplicationConstants.basicColours.WHITE,
                        28, true, this);
                setText("ОЧИСТИТЬ");
                setMeasures(Size.NORMAL);
                setPrefSize(measures[0], measures[1]);
                break;
            case SAVE:
            case CONTINUE:
            case FINISH:
                break;
            case MENU:
                InterfaceElementsSettings.buttonSettings(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE,
                        2, 0, 0, ApplicationConstants.basicColours.WHITE,
                        28, true, this);
                setText("МЕНЮ");
                setMeasures(Size.NORMAL);
                setPrefSize(measures[0], measures[1]);
                break;
            case STOP:
            case START:
                InterfaceElementsSettings.buttonSettings(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE,
                        2, 0, 0, ApplicationConstants.basicColours.WHITE,
                        28, true, this);
                setText("ПУСК");
                setMeasures(Size.NORMAL);
                setPrefSize(measures[0], measures[1]);
                break;
            case CANCEL:
                break;
        }
        setPrefSize(measures[0], measures[1]);
    }

    private void setMeasures(Size size) {
        measures = switch (size) {
            case SMALL -> ApplicationConstants.SMALL_MEASURES;
            case NORMAL -> ApplicationConstants.NORMAL_MEASURES;
        };
    }

    public void setup() {
        InterfaceElementsSettings.buttonSettings(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE,
                2, 0, 0, ApplicationConstants.basicColours.WHITE,
                24, true, this);
        setText("ОЧИСТИТЬ");
        setMeasures(Size.NORMAL);
        setPrefSize(measures[0], measures[1]);
    }
}
