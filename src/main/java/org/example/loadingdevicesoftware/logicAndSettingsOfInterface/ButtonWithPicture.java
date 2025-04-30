package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonWithPicture extends BasicButton {

    ImageView imageView;

    public enum Status {
        NORMAL, LOCKED
    }

    public enum ButtonSizes {
        SMALL, LARGE
    }

    public enum ImagViewSizes {
        SMALL, MEDIUM, LARGE
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
        setupPositions(positionsStyles, positionsImages);
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

    public void setActualStatus(Status status) {
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

    private void setupPositions(String[] styles, Image[] images) {
        objectPosition = new Position(styles, images);
    }

    private void setSizes(ButtonSizes size) {
        sizes = switch (size) {
            case SMALL -> new int[] { 50, 50 };
            case LARGE -> new int[] { 200, 215 };
        };
    }

    private void setSizes(ImagViewSizes size) {
        imageViewSizes = switch (size) {
            case SMALL -> new int[] { 105, 105 };
            case MEDIUM -> new int[] { 110, 110 };
            case LARGE -> new int[] { 120, 120 };
        };
    }




}
