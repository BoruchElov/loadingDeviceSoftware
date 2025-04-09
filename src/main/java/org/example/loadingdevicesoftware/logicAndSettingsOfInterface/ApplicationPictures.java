package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.layout.Pane;
import lombok.Getter;

public class ApplicationPictures {

    @Getter
    private final Pane picturePane;

    public enum Pictures {
        Three_Phase_Transformer, Three_Phase_Switcher, Single_Phase_Switcher
    }

    public ApplicationPictures(Pane picturePane, Pictures picture) {
        this.picturePane = picturePane;
        switch (picture) {
            case Three_Phase_Transformer:
                showThreePhaseTransformer();
                break;
            case Three_Phase_Switcher:
                showThreePhaseSwitcher();
                break;
            case Single_Phase_Switcher:
                showSinglePhaseSwitcher();
                break;
        }
    }

    private void showThreePhaseTransformer() {
        picturePane.getChildren().clear();

    }

    private void showThreePhaseSwitcher() {}

    private void showSinglePhaseSwitcher() {}

}
