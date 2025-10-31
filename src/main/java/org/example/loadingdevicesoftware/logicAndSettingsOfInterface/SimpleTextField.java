package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.util.converter.DoubleStringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SimpleTextField extends TextField implements Changeable {


    /**
     * Класс для задания типа ожидаемого значения: буквенного или числового
     */
    public enum typeOfValue {
        ALPHABETIC, DIGIT, ORDINARY
    }

    enum numberOfDecimals {
        TWO, THREE
    }

    typeOfValue type;
    numberOfDecimals decimals = numberOfDecimals.TWO;

    private double MAX = 3000.;
    private double MIN = 1.;

    public void setLimits(double MIN, double MAX, numberOfDecimals decimals) {
        this.MIN = MIN;
        this.MAX = MAX;
        this.decimals = decimals;
    }

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

    public void setup (String defaultText, Sizes size, typeOfValue typeOfValue) {
        type = typeOfValue;
        Tooltip.install(this, createTooltip());
        switch (type) {
            case ALPHABETIC:
                this.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("[a-zA-Zа-яА-Я]*")) { // только буквы латиницы и кириллицы
                        this.setText(newValue.replaceAll("[^a-zA-Zа-яА-Я]", ""));
                    }
                });
                break;
                case DIGIT:
                    Pattern decimalPattern = switch (decimals) {
                        case TWO -> Pattern.compile("\\d*(\\.\\d{0,2})?");
                        case THREE -> Pattern.compile("\\d+(\\.\\d{0,3})?");
                    };
                    UnaryOperator<TextFormatter.Change> filter = change -> {
                        String newText = change.getControlNewText();
                        if (decimalPattern.matcher(newText).matches()) {
                            return change;
                        }
                        return null;
                    };
                    TextFormatter<Double> formatter = new TextFormatter<>(
                            new DoubleStringConverter(),
                            0.0,
                            filter
                    );
                    this.setTextFormatter(formatter);
                    formatter.valueProperty().addListener((obs, oldVal, newVal) -> {
                        if (newVal == null) return;
                        if (newVal < MIN || newVal > MAX) {
                            this.setText(oldVal.toString());
                            //this.setStyle("-fx-border-color: red;");
                        }
                    });
                    break;
            case ORDINARY:
                break;

        }
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

    private Tooltip createTooltip() {
        String tooltipText = switch (type) {
            case ALPHABETIC -> "Данное поле принимает только текст";
            case DIGIT -> "Данное поле принимает только числа в диапазоне от " + MIN + " до " + MAX;
            case ORDINARY -> "Данное поле принимает как текст, так и числа, спецсимволы";
        };
        Tooltip tooltip = new Tooltip(tooltipText);
        tooltip.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        tooltip.setStyle("-fx-background-color: #CBCCCB; -fx-text-fill: black;");
        return tooltip;
    }

}
