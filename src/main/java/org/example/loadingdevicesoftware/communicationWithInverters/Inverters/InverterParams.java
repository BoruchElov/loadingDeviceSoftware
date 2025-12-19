package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class InverterParams {

    private final StringProperty currentRMS = new SimpleStringProperty();
    private final StringProperty currentPhase = new SimpleStringProperty();
    private final StringProperty voltmeterVoltage = new SimpleStringProperty();
    private final StringProperty amperemeterCurrent = new SimpleStringProperty();
    private final StringProperty amperemeterPhase = new SimpleStringProperty();

    public StringProperty currentRMSProperty() { return currentRMS; }
    public StringProperty currentPhaseProperty() { return currentPhase; }
    public StringProperty voltmeterVoltageProperty()   { return voltmeterVoltage; }
    public StringProperty amperemeterCurrentProperty()    { return amperemeterCurrent; }
    public StringProperty amperemeterPhaseProperty()    { return amperemeterCurrent; }

    public void updateFromResponse(String response) {
        Platform.runLater(() -> {
            try {
                ArrayList<String> list = parse(response);
                if (list.isEmpty()) return;
                if (list.size() < 5) list = new ArrayList<>(Arrays.asList("0,0,0,0,0".split(",")));
                currentRMS.set(list.get(0));
                currentPhase.set(list.get(1));
                voltmeterVoltage.set(list.get(2));
                amperemeterCurrent.set(list.get(3));
                amperemeterPhase.set(list.get(4));
                System.out.println("Параметры обновились");

            } catch (Exception e) {
                System.err.println("Ошибка парсинга: " + e.getMessage());
            }
        });
    }

    private ArrayList<String> parse(String response) {
        System.out.println("Результат парсинга:" + Arrays.toString(response.split(",")));
        return new ArrayList<>(Arrays.asList(response.split(",")));
    }

}
