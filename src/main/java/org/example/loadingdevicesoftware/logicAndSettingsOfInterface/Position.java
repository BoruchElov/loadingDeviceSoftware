package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

public class Position {
    @Getter
    private int positions;
    @Getter
    private int actualPosition = 0;
    private String[] styles;
    private String[] texts;
    @Getter
    private Image[] images;

    private final String basicStyle;

    public Position(String basicStyle, String[] styles, String[] texts) {
        positions = styles.length;
        this.styles = styles;
        this.texts = texts;
        this.basicStyle = basicStyle;
    }

    public Position(String basicStyle, String[] styles, Image[] images) {
        positions = styles.length;
        this.styles = styles;
        this.images = images;
        this.basicStyle = basicStyle;
    }

    public void setActualPosition(int actualPosition, Button button) {
        this.actualPosition = actualPosition;
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().addAll(basicStyle, styles[actualPosition]);
        button.setText(texts[actualPosition]);
    }

    public void setActualPosition(int actualPosition, Button button, ImageView imageView) {
        this.actualPosition = actualPosition;
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().addAll(basicStyle, styles[actualPosition]);
        imageView.setImage(images[actualPosition]);
    }

}
