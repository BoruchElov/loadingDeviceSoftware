package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import lombok.Setter;

public class ButtonStatus {

    @Getter
    @Setter
    private BasicButton.Status status;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private ApplicationConstants.basicColours colour;

    @Getter
    @Setter
    private boolean isDisabled;

    private ApplicationConstants.basicColours[] colours;
    private String[] texts;

    public ButtonStatus(String textNormal, String textLocked, String textEnabled, String textWarning,
                        String textDefault) {

        texts = new String[]{textNormal, textLocked, textEnabled, textWarning, textDefault};

    }

    public void setStatus(BasicButton.Status status, BasicButton button) {
        this.status = status;
        switch (status) {
            case NORMAL:
                button.getStyleClass().addAll("button-common", "button-low-normal");
                button.setText(texts[0]);
                button.setDisable(false);
                break;
            case LOCKED:
                button.getStyleClass().addAll("button-common", "button-low-locked");
                button.setText(texts[1]);
                button.setDisable(true);
                break;
            case ENABLED:
                button.getStyleClass().addAll("button-common", "button-low-enabled");
                button.setText(texts[2]);
                button.setDisable(false);
                break;
            case WARNING:
                button.getStyleClass().addAll("button-common", "button-low-warning");
                button.setText(texts[3]);
                button.setDisable(false);
                break;
            case DEFAULT:
                button.getStyleClass().addAll("button-common", "button-low-default");
                button.setText(texts[4]);
                button.setDisable(false);
                break;
        }
    }
}
