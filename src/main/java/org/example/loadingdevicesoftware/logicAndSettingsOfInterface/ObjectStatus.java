package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.Node;
import lombok.Getter;
import lombok.Setter;

public class ObjectStatus {

    public enum StatusOfObject {
        NORMAL, LOCKED
    }

    @Getter
    private String status;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private ApplicationConstants.basicColours colour;

    @Getter
    @Setter
    private boolean isDisabled;


    public void setStatus(StatusOfObject status, Node object) {
        int actualPosition;
        if (object instanceof ButtonWithPicture button) {
            actualPosition = button.objectPosition.getActualPosition();
            button.changePosition(actualPosition);
        }
        if (object instanceof SimpleButton button) {
            actualPosition = button.objectPosition.getActualPosition();
            button.changePosition(actualPosition);
        }
        if (object instanceof SimpleImageView imageView) {
            actualPosition = imageView.objectPosition.getActualPosition();
            imageView.changePosition(actualPosition);
        }
        this.status = status.name();
        switch (status) {
            case NORMAL:
                object.setDisable(false);
                break;
            case LOCKED:
                object.setDisable(true);
                break;
        }
    }
}
