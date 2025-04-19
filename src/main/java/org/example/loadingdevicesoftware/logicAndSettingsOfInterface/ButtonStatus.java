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

    private String[] texts;
    private String[] styles;

    public ButtonStatus(String textNormal, String textLocked, String textEnabled, String textWarning,
                        String textDefault, String styleNormal, String styleLocked, String styleEnabled,
                        String styleWarning, String styleDefault) {

        texts = new String[]{textNormal, textLocked, textEnabled, textWarning, textDefault};
        styles = new String[]{styleNormal, styleLocked, styleEnabled, styleWarning, styleDefault};

    }

    public void setStatus(BasicButton.Status status, BasicButton button) {
        this.status = status;
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        switch (status) {
            case NORMAL:
                button.getStyleClass().addAll("button-common", styles[0]);
                button.setText(texts[0]);
                button.setDisable(false);
                break;
            case LOCKED:
                button.getStyleClass().addAll("button-common", styles[1]);
                button.setText(texts[1]);
                button.setDisable(true);
                break;
            case ENABLED:
                button.getStyleClass().addAll("button-common", styles[2]);
                button.setText(texts[2]);
                button.setDisable(false);
                break;
            case WARNING:
                button.getStyleClass().addAll("button-common", styles[3]);
                button.setText(texts[3]);
                button.setDisable(false);
                break;
            case DEFAULT:
                button.getStyleClass().addAll("button-common", styles[4]);
                button.setText(texts[4]);
                button.setDisable(false);
                break;
        }
    }
}
