package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;

public class BasicButton extends Button {

    Position objectPosition;

    ObjectStatus objectStatus = new ObjectStatus();

    public void changePosition(int position) {
        objectPosition.setActualPosition(position, this);
    }
}
