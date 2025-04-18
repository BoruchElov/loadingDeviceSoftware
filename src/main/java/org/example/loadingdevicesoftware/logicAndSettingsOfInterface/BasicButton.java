package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

public class BasicButton extends Button {

    @Getter
    @Setter
    private String state;

    ButtonStatus actualStatus;

    private int[] measures;

    public enum Status {
        NORMAL, LOCKED, ENABLED, WARNING, DEFAULT
    }

    public enum Size {
        SMALL, NORMAL
    }

    public enum Presets {
        CLEAR, SAVE, MENU, START, CANCEL, STOP, CONTINUE, FINISH
    }

    public BasicButton() {
        super();
    }

    public void setup(ApplicationConstants.basicColours colourOfBackground, ApplicationConstants.basicColours colourOfBorder,
                      int widthOfBorder, Size buttonSize, ApplicationConstants.basicColours colourOfText, int sizeOfFont,
                      boolean isTextBold, String text, ApplicationConstants.basicColours[] colours, String[] texts) {
        InterfaceElementsSettings.buttonSettings(colourOfBackground, colourOfBorder,
                widthOfBorder, 0, 0, colourOfText,
                sizeOfFont, isTextBold, this);
        setMeasures(buttonSize);
        setPrefSize(measures[0], measures[1]);
        setText(text);
        actualStatus = new ButtonStatus(texts[0], texts[1], texts[2], texts[3], texts[4]);
    }

    public void setup (String normalStyle, String lockedStyle, String enabledStyle, String warningStyle, String defaultStyle,
                       String normalText, String lockedText, String enabledText, String warningText, String defaultText,
                       Size buttonSize) {

        /*setMeasures(buttonSize);
        setPrefSize(measures[0], measures[1]);*/
        setText(defaultText);

    }

    public void setup(Presets preset) {

        String text;
        switch (preset) {
            case CLEAR:
                text = "ОЧИСТИТЬ";

                break;
            case SAVE:
                text = "СОХРАНИТЬ";

                break;
            case CONTINUE:
                text = "ПРОДОЛЖИТЬ";

                break;
            case FINISH:
                text = "ЗАКОНЧИТЬ";

                break;
            case MENU:
                text = "МЕНЮ";

                break;
            case STOP:
                text = "ОСТАНОВИТЬ";

                break;
            case START:
                text = "ПУСК";

                break;
            case CANCEL:
                text = "ОТМЕНА";

                break;
        }
    }

    public void changeActualStatus(Status status) {
        actualStatus.setStatus(status, this);
    }

    private void setMeasures(Size size) {
        measures = switch (size) {
            case SMALL -> ApplicationConstants.SMALL_MEASURES;
            case NORMAL -> ApplicationConstants.NORMAL_MEASURES;
        };
    }
}
