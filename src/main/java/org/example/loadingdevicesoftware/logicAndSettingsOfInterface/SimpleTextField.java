package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.TextField;

public class SimpleTextField extends TextField implements Changeable {

    @Override
    public void changePosition(int position) {clear();}

    public enum Status {
        NORMAL, LOCKED
    }

    public enum Sizes {
        SMALL, MEDIUM, LARGE
    }
    //measures[0] - ширина, measures[1] - высота
    private int[] measures;
    private final String STYLE = "text-object";
    Sizes size;
    String defaultText;

    ObjectStatus objectStatus = new ObjectStatus();

    public SimpleTextField() {
        super();
    }

    public void setup (String defaultText, Sizes size) {
        getStyleClass().add(STYLE);
        setMeasures(size);
        setPrefWidth(measures[0]);
        setPrefHeight(measures[1]);
        setPromptText(defaultText);
        this.size = size;
        this.defaultText = defaultText;
    }

    public void setActualStatus(Changeable.Status status) {
        ObjectStatus.StatusOfObject newStatus = ObjectStatus.StatusOfObject.values()[status.ordinal()];
        objectStatus.setStatus(newStatus, this);
    }

    private void setMeasures(Sizes size) {
        measures = switch (size) {
            case SMALL -> new int[]{52, 52};
            case MEDIUM -> new int[]{75, 52};
            case LARGE -> new int[]{336, 52};
        };
    }

}
