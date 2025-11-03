package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonWithPicture extends BasicButton implements Changeable {

    ImageView imageView;

    public enum Status {
        NORMAL, LOCKED
    }

    public enum ButtonSizes {
        SMALL, LARGE, KEY_MODULE_SIZE
    }

    public enum ImagViewSizes {
        SMALLEST, SMALL, MEDIUM, LARGE, KEY_MODULE_SIZE
    }
    //sizes[0] - ширина, sizes[1] - высота
    private int[] sizes;

    //sizes[0] - ширина, sizes[1] - высота
    private int[] imageViewSizes;

    public ButtonWithPicture() {
        super();
    }

    private ButtonSizes backup;
    private ImagViewSizes backupIV;

    public void setup (ImageView imageView, ButtonSizes size, ImagViewSizes ivSize, String[] positionsStyles,
                       Image[] positionsImages) {
        this.imageView = imageView;
        objectPosition = new Position(positionsStyles, positionsImages);
        setupButton(imageView, positionsImages[0], size, ivSize);
        backup = size;
        backupIV = ivSize;
        changePosition(0);
    }

    @Override
    public void changePosition (int actualPosition) {
        objectPosition.setActualPosition(actualPosition, this, imageView);
        setupButton(imageView, objectPosition.getImages()[actualPosition], backup, backupIV);
    }

    @Override
    public void setActualStatus(Changeable.Status status) {
        ObjectStatus.StatusOfObject newStatus = ObjectStatus.StatusOfObject.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    private void setupButton(ImageView imageView, Image image, ButtonSizes size, ImagViewSizes ivSize) {
        imageView.setImage(image);
        setSizes(size);
        setSizes(ivSize);
        this.setPrefWidth(sizes[0]);
        this.setPrefHeight(sizes[1]);
        imageView.setFitWidth(imageViewSizes[0]);
        imageView.setFitHeight(imageViewSizes[1]);
        setGraphic(imageView);
    }

    private void setSizes(ButtonSizes size) {
        sizes = switch (size) {
            case SMALL -> new int[] { 50, 50 };
            case LARGE -> new int[] { 200, 215 };
            case KEY_MODULE_SIZE -> new int[] {90, 90};     //Размер для кнопки модуля
        };
    }

    private void setSizes(ImagViewSizes size) {
        imageViewSizes = switch (size) {
            case SMALLEST -> new int[] { 50, 50 };
            case SMALL -> new int[] { 105, 105 };
            case MEDIUM -> new int[] { 110, 110 };
            case LARGE -> new int[] { 120, 120 };
            case KEY_MODULE_SIZE -> new int[] {90, 90};     //Размер для картинки модуля
        };
    }
}
