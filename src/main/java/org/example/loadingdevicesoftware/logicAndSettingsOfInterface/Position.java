package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private double[][] sizes;
    private double[][] location;

    public Position(String[] styles, String[] texts) {
        positions = styles.length;
        this.styles = styles;
        this.texts = texts;
    }

    public Position(String[] styles, Image[] images) {
        positions = styles.length;
        this.styles = styles;
        this.images = images;
    }

    public Position(String[] styles, Image[] images, double[][] sizes) {
        positions = styles.length;
        this.styles = styles;
        this.images = images;
        this.sizes = sizes;
    }
    public Position(String[] styles, Image[] images, double[][] sizes, double[][] location) {
        positions = styles.length;
        this.styles = styles;
        this.images = images;
        this.sizes = sizes;
        this.location = location;
    }

    public void setActualPosition(int actualPosition, Button button) {
        this.actualPosition = actualPosition;
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add(styles[actualPosition]);
        button.setText(texts[actualPosition]);
    }

    public void setActualPosition(int actualPosition, Button button, ImageView imageView) {
        this.actualPosition = actualPosition;
        button.getStyleClass().clear();
        imageView.getStyleClass().clear();
        imageView.getStyleClass().add("image");
        button.getStyleClass().add("button");
        button.getStyleClass().add(styles[actualPosition]);
        imageView.setImage(images[actualPosition]);
    }

    public void setActualPosition(int actualPosition, SimpleImageView imageView) {
        this.actualPosition = actualPosition;
        imageView.getStyleClass().clear();
        imageView.getStyleClass().add("image");
        imageView.getStyleClass().add(styles[actualPosition]);
        imageView.setFitWidth(sizes[actualPosition][0]);
        imageView.setFitHeight(sizes[actualPosition][1]);
        imageView.setImage(images[actualPosition]);
        /*if (location != null) {
            AnchorPane.setLeftAnchor(imageView, location[actualPosition][0]);
            AnchorPane.setTopAnchor(imageView, location[actualPosition][1]);
        }*/
    }
}
