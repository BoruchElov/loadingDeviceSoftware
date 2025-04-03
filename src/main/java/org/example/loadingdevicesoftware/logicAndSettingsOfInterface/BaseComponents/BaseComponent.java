package org.example.loadingdevicesoftware.logicAndSettingsOfInterface.BaseComponents;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.image.ImageView;

/**
 * Базовый класс для всех элементов схемы (линии, молнии, земля).
 * Реализует общую логику управления цветом, видимостью и состоянием.
 */
public abstract class BaseComponent {
    protected Color defaultColor;
    protected Color selectedColor;
    protected double defaultWidth;
    protected double selectedWidth;

    public BaseComponent(Color defaultColor, Color selectedColor,
                         double defaultWidth, double selectedWidth) {
        this.defaultColor = defaultColor;
        this.selectedColor = selectedColor;
        this.defaultWidth = defaultWidth;
        this.selectedWidth = selectedWidth;
    }

    /**
     * Устанавливает состояние элемента (выбран/не выбран).
     * @param isSelected - true для выделенного состояния.
     */
    public abstract void setSelected(boolean isSelected);

    /**
     * Обновляет цвет и толщину линии (общий метод для фаз).
     */
    protected void updateLineStyle(Shape line, boolean isSelected) {
        line.setStroke(isSelected ? selectedColor : defaultColor);
        line.setStrokeWidth(isSelected ? selectedWidth : defaultWidth);
    }

    /**
     * Обновляет видимость ImageView (стрелки или молнии).
     */
    protected void updateImageView(ImageView imageView, boolean isVisible) {
        imageView.setVisible(isVisible);
    }
}

