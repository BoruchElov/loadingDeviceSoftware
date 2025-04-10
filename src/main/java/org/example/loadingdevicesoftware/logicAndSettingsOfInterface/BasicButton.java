package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

public class BasicButton extends Button  {

    @Getter
    @Setter
    private String state;

    public enum Status {
        NORMAL, LOCKED, ENABLED, WARNING
    }

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public BasicButton (String text, String color, int width, int height) {
        super();
        this.setText(text);
        this.setStyle("-fx-background-color: " + color + ";");
        this.setPrefSize(width, height);
    }



}
