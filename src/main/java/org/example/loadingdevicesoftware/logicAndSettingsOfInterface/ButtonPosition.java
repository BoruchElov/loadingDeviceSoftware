package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

public class ButtonPosition {

    @Getter @Setter
    private int positions;
    @Getter
    private int actualPosition;
    @Getter @Setter
    private String[] styles;
    @Getter @Setter
    private String[] texts;
    private final String basicStyle;

    public ButtonPosition (int positions, String basicStyle, String[] styles, String[] texts) {
        this.positions = positions;
        this.styles = styles;
        this.texts = texts;
        this.basicStyle = basicStyle;
    }

    public void setActualPosition(int actualPosition, Button button) {
        this.actualPosition = actualPosition;
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().addAll(basicStyle, styles[actualPosition]);
        button.setText(texts[actualPosition]);
    }

}
