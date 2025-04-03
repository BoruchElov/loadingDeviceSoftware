package org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BaseComponents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ShortCircuit extends BaseComponent {
    private final ImageView lightning;
    private final ImageView ground;
    private final Image lightningImage;

    // Текущие координаты
    private double lightningX, lightningY, lightningRotate;
    private double groundX, groundY;

    public ShortCircuit(ImageView lightning, ImageView ground, Image lightningImage,
                        double defaultLightningX, double defaultLightningY, double defaultRotate,
                        double defaultGroundX, double defaultGroundY) {
        super(Color.BLACK, Color.RED, 0, 0); // Ширина не используется
        this.lightning = lightning;
        this.ground = ground;
        this.lightningImage = lightningImage;

        // Устанавливаем дефолтные позиции
        updatePosition(defaultLightningX, defaultLightningY, defaultRotate,
                defaultGroundX, defaultGroundY);
    }

    // Обновляет позиции и применяет их
    public void updatePosition(double lightningX, double lightningY, double rotate,
                               double groundX, double groundY) {
        this.lightningX = lightningX;
        this.lightningY = lightningY;
        this.lightningRotate = rotate;
        this.groundX = groundX;
        this.groundY = groundY;

        applyCurrentPosition();
    }

    // Применяет текущие позиции к элементам
    private void applyCurrentPosition() {
        lightning.setImage(lightningImage);
        lightning.setLayoutX(lightningX);
        lightning.setLayoutY(lightningY);
        lightning.setRotate(lightningRotate);
        ground.setLayoutX(groundX);
        ground.setLayoutY(groundY);
    }

    @Override
    public void setSelected(boolean isSelected) {
        if (isSelected) {
            applyCurrentPosition();
        }
        // Можно добавить логику для isSelected = false
    }
}