package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.TextField;

public class SimpleTextField extends TextField {

    public enum Status {
        NORMAL, LOCKED
    }

    public enum Sizes {
        SMALL, MEDIUM, LARGE
    }
    //measures[0] - ширина, measures[1]
    private int[] measures;
    private final String STYLE = "text";
    Sizes size;
    String defaultText;

    ObjectStatus objectStatus;

    public SimpleTextField() {
        super();
    }

    public void setup (String defaultText, Sizes size) {
        getStyleClass().add(STYLE);
        setMeasures(size);
        setWidth(measures[0]);
        setHeight(measures[1]);
        setPromptText(defaultText);
        this.size = size;
        this.defaultText = defaultText;
    }

    public void setActualStatus(Status status) {
        ObjectStatus.StatusOfObject newStatus = ObjectStatus.StatusOfObject.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    private void setMeasures(Sizes size) {
        measures = switch (size) {
            case SMALL -> new int[]{50, 50};
            case MEDIUM -> new int[]{80, 50};
            case LARGE -> new int[]{336, 50};
        };
    }

}
