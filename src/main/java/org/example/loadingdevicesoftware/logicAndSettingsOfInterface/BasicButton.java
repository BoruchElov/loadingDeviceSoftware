package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import lombok.Getter;

public class BasicButton extends Button {
    @Getter
    Position objectPosition;

    ObjectStatus objectStatus = new ObjectStatus();
}
