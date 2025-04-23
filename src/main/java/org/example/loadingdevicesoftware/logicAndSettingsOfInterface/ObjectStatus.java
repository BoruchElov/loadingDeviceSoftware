package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

public class ObjectStatus {

    public enum StatusOfObject {
        NORMAL, LOCKED
    }
    public enum StatusOfObjectExtended {
        NORMAL, LOCKED, ENABLED, WARNING
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

    private String basicStyle;

    private String[] texts;
    private String[] styles;

    public ObjectStatus(String textNormal, String textLocked, String textEnabled, String textWarning,
                        String styleNormal, String styleLocked, String styleEnabled,
                        String styleWarning, String basicStyle) {
        this.basicStyle = basicStyle;
        texts = new String[]{textNormal, textLocked, textEnabled, textWarning};
        styles = new String[]{styleNormal, styleLocked, styleEnabled, styleWarning};
    }

    public ObjectStatus(String styleNormal, String styleLocked, String basicStyle) {
        this.basicStyle = basicStyle;
        styles = new String[]{styleNormal, styleLocked};
    }

    public void setStatus(StatusOfObjectExtended status, SimpleButton object) {
        object.getStyleClass().clear();
        object.getStyleClass().add("button");
        int actualPosition = object.objectPosition.getActualPosition();
        object.changePosition(actualPosition);
        this.status = status.name();
        switch (status) {
            case NORMAL:
                object.getStyleClass().addAll(basicStyle, styles[0]);
                object.setText(texts[0]);
                object.setDisable(false);
                break;
            case LOCKED:
                object.getStyleClass().addAll(basicStyle, styles[1]);
                object.setText(texts[1]);
                object.setDisable(true);
                break;
            case ENABLED:
                object.getStyleClass().addAll(basicStyle, styles[2]);
                object.setText(texts[2]);
                object.setDisable(false);
                break;
            case WARNING:
                object.getStyleClass().addAll(basicStyle, styles[3]);
                object.setText(texts[3]);
                object.setDisable(false);
                break;
        }
    }

    public void setStatus(StatusOfObject status, Node object) {
        int actualPosition;
        if (object instanceof ButtonWithPicture button) {
            actualPosition = button.objectPosition.getActualPosition();
            button.changePosition(actualPosition);
        } else if (object instanceof TextField) {
            object.getStyleClass().addAll("text-field", "text-input");
        }
        this.status = status.name();
        switch (status) {
            case NORMAL:
                object.getStyleClass().addAll(basicStyle, styles[0]);
                object.setDisable(false);
                break;
            case LOCKED:
                object.getStyleClass().addAll(basicStyle, styles[1]);
                object.setDisable(true);
                break;
        }
    }
}
