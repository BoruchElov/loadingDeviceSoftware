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

    public ButtonStatus(ApplicationConstants.basicColours colourNormal, String textNormal,
                        ApplicationConstants.basicColours colourLocked, String textLocked,
                        ApplicationConstants.basicColours colourEnabled, String textEnabled,
                        ApplicationConstants.basicColours colourWarning, String textWarning,
                        ApplicationConstants.basicColours colourDefault, String textDefault) {

        colours = new ApplicationConstants.basicColours[]{colourNormal, colourLocked, colourEnabled,
                colourWarning, colourDefault};
        texts = new String[]{textNormal, textLocked, textEnabled, textWarning, textDefault};

    }

    public void setStatus(BasicButton.Status status, BasicButton button) {
        this.status = status;
        switch (status) {
            case NORMAL:
                InterfaceElementsSettings.buttonSettings(colours[0], button, texts[0], false);
                break;
            case LOCKED:
                InterfaceElementsSettings.buttonSettings(colours[1], button, texts[1], true);
                break;
            case ENABLED:
                InterfaceElementsSettings.buttonSettings(colours[2], button, texts[2], false);
                break;
            case WARNING:
                InterfaceElementsSettings.buttonSettings(colours[3], button, texts[3], false);
                break;
            case DEFAULT:
                InterfaceElementsSettings.buttonSettings(colours[4], button, texts[4], false);
                break;
        }
    }
}
