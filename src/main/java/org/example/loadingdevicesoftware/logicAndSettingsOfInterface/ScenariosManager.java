package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

public class ScenariosManager {

    private ScenariosManager() {}

    public enum Scenarios {
        THREE_PHASE_SWITCHER, SINGLE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, DIFFERENTIAL_PROTECTION,
        HAND_CONTROL, SINGLE_PHASE_PROTECTION, THREE_PHASE_PROTECTION
    }

    public static void launchScenario(Scenarios scenario) {

    }

    private static void handControlScenario() {

    }

    /**
     * Метод для остановки ожидания сообщений от инверторов через EventWaiter, если такое есть.
     */
    private static void shutdown() {

    }

    /**
        Метод для получения массива строк из
        результата сценария
    */
    public static String[] analyzeResults(String input){
        return input.replace(" ", "").split(",");
    }

}

