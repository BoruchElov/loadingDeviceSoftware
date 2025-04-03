package org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BaseComponents;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Управляет двумя линиями фазы (например, A1/A2) и их стрелками.
 */
public class PhaseLines extends BaseComponent {
    private final Line line1;
    private final Line line2;
    private final ImageView arrow1;
    private final ImageView arrow2;

    public PhaseLines(Line line1, Line line2, ImageView arrow1, ImageView arrow2,
                      Color defaultColor, Color selectedColor,
                      double defaultWidth, double selectedWidth) {
        super(defaultColor, selectedColor, defaultWidth, selectedWidth);
        this.line1 = line1;
        this.line2 = line2;
        this.arrow1 = arrow1;
        this.arrow2 = arrow2;
    }

    @Override
    public void setSelected(boolean isSelected) {
        updateLineStyle(line1, isSelected);
        updateLineStyle(line2, isSelected);
        updateImageView(arrow1, isSelected);
        updateImageView(arrow2, isSelected);
    }
}
