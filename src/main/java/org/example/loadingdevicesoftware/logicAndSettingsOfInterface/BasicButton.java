package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;

public class BasicButton extends Button {

    Position objectPosition;

    ObjectStatus objectStatus;

    public final String basicStyle = "button-common";

    public void changePosition(int position) {
        objectPosition.setActualPosition(position, this);
    }
}
