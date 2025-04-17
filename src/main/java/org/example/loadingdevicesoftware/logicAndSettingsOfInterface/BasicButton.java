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
        actualStatus = new ButtonStatus(colours[0], texts[0], colours[1], texts[1], colours[2], texts[2],
                colours[3], texts[3], colours[4], texts[4]);
    }

    public void setup(Presets preset) {

        String text;
        switch (preset) {
            case CLEAR:
                text = "ОЧИСТИТЬ";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case SAVE:
                text = "СОХРАНИТЬ";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case CONTINUE:
                text = "ПРОДОЛЖИТЬ";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case FINISH:
                text = "ЗАКОНЧИТЬ";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case MENU:
                text = "МЕНЮ";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case STOP:
                text = "ОСТАНОВИТЬ";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case START:
                text = "ПУСК";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
                break;
            case CANCEL:
                text = "ОТМЕНА";
                setup(ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.WHITE, 2, Size.NORMAL,
                        ApplicationConstants.basicColours.WHITE, 28, true, text, new ApplicationConstants.basicColours[]{
                                ApplicationConstants.basicColours.BLUE, ApplicationConstants.basicColours.BLUE,
                                ApplicationConstants.basicColours.GREEN, ApplicationConstants.basicColours.RED,
                                ApplicationConstants.basicColours.BLUE}, new String[]{text, text, text, text, text});
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
