package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonWithPicture extends BasicButton {

    ImageView imageView;

    public enum Status {
        NORMAL, LOCKED
    }

    public enum Sizes {
        SMALL, LARGE
    }
    //sizes[0] - ширина, sizes[1] - высота
    private int[] sizes;

    public ButtonWithPicture() {
        super();
    }

    private Sizes backup;

    public void setup (ImageView imageView, Sizes size, String[] positionsStyles,
                       Image[] positionsImages) {
        this.imageView = imageView;
        setupPositions(positionsStyles, positionsImages);
        setupButton(imageView, positionsImages[0], size);
        backup = size;
    }

    @Override
    public void changePosition (int actualPosition) {
        objectPosition.setActualPosition(actualPosition, this, imageView);
        setupButton(imageView, objectPosition.getImages()[actualPosition], backup);
    }

    public void setActualStatus(Status status) {
        ObjectStatus.StatusOfObject newStatus = ObjectStatus.StatusOfObject.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    private void setupButton(ImageView imageView, Image image, Sizes size) {
        imageView.setImage(image);
        setSizes(size);
        imageView.setFitWidth(sizes[0]);
        imageView.setFitHeight(sizes[1]);
        setGraphic(imageView);
    }

    private void setupPositions(String[] styles, Image[] images) {
        objectPosition = new Position(styles, images);
    }

    private void setSizes(Sizes size) {
        sizes = switch (size) {
            case SMALL -> new int[] { 50, 50 };
            case LARGE -> new int[] { 200, 215 };
        };
    }




}
