package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;

public class SimpleImageView extends ImageView implements Changeable {

    @Getter
    Position objectPosition;
    @Getter
    ObjectStatus objectStatus = new ObjectStatus();

    public enum Status {
        NORMAL, LOCKED
    }

    public SimpleImageView() {super();}

    public void setup (String[] positionsStyles, Image[] positionsImages, double[][] sizes) {
        objectPosition = new Position(positionsStyles, positionsImages, sizes);
        changePosition(0);
    }

    public void setActualStatus(Changeable.Status status) {
        ObjectStatus.StatusOfObject newStatus = ObjectStatus.StatusOfObject.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    public void changePosition(int position) {
        objectPosition.setActualPosition(position, this);
    }
}
