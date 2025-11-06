package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckingManager {

    private CheckingManager() {}

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public enum Scenarios {
        THREE_PHASE_SWITCHER, SINGLE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, DIFFERENTIAL_PROTECTION,
        HAND_CONTROL
    }

    private static Scenarios scenario;

    @Getter
    private static boolean firstCheck = false, secondCheck = false, thirdCheck = false, fourthCheck = false, fifthCheck = false;

    private final HashMap<String, Address> addresses = new HashMap<>();

    /// /////////////////////////////////////////////////////////////////////////
    /// TODO данный фрагмент кода нужен только для реализации сценария ручного ввода. После отладки удалить
    private static final ArrayList<String> variableAddressesStorage = new ArrayList<>();

    public static void addVariableAddress(String module) {
        if (!variableAddressesStorage.contains(module)) {
            variableAddressesStorage.add(module);
        }
    }

    public static void clearVariableAddresses() {
        if (!variableAddressesStorage.isEmpty()) {
            variableAddressesStorage.clear();
        }
    }
    /// /////////////////////////////////////////////////////////////////////////

    public static void startCheckingsThread(Scenarios scenario) {
        executor.submit(() -> startCheck(scenario));
    }

    public static void stopCheckingsThread() {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
    }


    //TODO Доработать тексты сообщений: добавить больше полезной информации (конкретные проблемные адреса, модули)

    /**
     * Метод для запуска последовательности проверок, в зависимости от сценария.
     *
     * @param scenario - заданный сценарий
     */
    private static void startCheck(Scenarios scenario) {
        CheckingManager.scenario = scenario;
        try {
            firstCheck = settingsCheck();
            if (!firstCheck) {
                InterfaceElementsLogic.showAlert("Ошибка настройки!\nКоличество настроенных модулей не соответствует выбранному сценарию.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла адресов!");
        }
        secondCheck = powerCheck();
        if (!secondCheck) {
            InterfaceElementsLogic.showAlert("Ошибка проверки питания!\nНедостаточное количество модулей получают питание");
            return;
        }
        thirdCheck = synchronizationCheck();
        if (!thirdCheck) {
            InterfaceElementsLogic.showAlert("Ошибка проверки синхронизации!\nМодули не синхронизированы");
            return;
        }
        fourthCheck = currentRangeCheck();
        if (!fourthCheck) {
            InterfaceElementsLogic.showAlert("Ошибка проверки диапазона тока!");
            return;
        }
        fifthCheck = resistanceCheck();
        if (!fifthCheck) {
            InterfaceElementsLogic.showAlert("Ошибка проверки сопротивления!");
            return;
        }

    }

    /**
     * Метод для реализации проверки настройки модулей.
     * Для всех сценариев, кроме ручного ввода, она работает следующим образом:
     * <ol>
     *     <li>Считываются адреса из файла настроек.</li>
     *     <li>В зависимости от сценария проверяется, соответствует ли количество считанных адресов
     *     количеству модулей, необходимому для выбранного сценария, и являются ли они нужными модулями</li>
     * </ol>
     * В случае сценария ручного ввода, из формы вызывается статический список variableAddressesStorage, заполняемый
     * именами модулей, выбранных на форме. Их количество переменно и может не равняться 1, 3 или 6, поэтому я вынес
     * его в отдельную проверку. Далее, проверяется, задан ли соответствующий адрес в форме настроек.
     *
     * @return false, если отсутствует настройка хотя бы для одного модуля.
     * @throws IOException
     */

    private static boolean settingsCheck() throws IOException {
        List<String> addressesList = AddressesStorage.readAddresses();
        boolean result = false;
        switch (scenario) {
            case SINGLE_PHASE_SWITCHER:
                if (!addressesList.isEmpty()) {
                    result = !Objects.equals(addressesList.getFirst(), "00:00:00:00");
                }
                break;
                case THREE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE:
                    if (addressesList.size() >= 3) {
                        result = (!Objects.equals(addressesList.getFirst(), "00:00:00:00")) &&
                                 (!Objects.equals(addressesList.get(1), "00:00:00:00")) &&
                                 (!Objects.equals(addressesList.get(2), "00:00:00:00"));
                    }
                    break;
            case DIFFERENTIAL_PROTECTION:
                if (addressesList.size() == 6) {
                    result = !addressesList.contains("00:00:00:00");
                }
                break;
                case HAND_CONTROL:
                    if (!variableAddressesStorage.isEmpty()) {
                        HashMap<String,Integer> map = new HashMap<>();
                        map.put("moduleA1",0);
                        map.put("moduleB1",1);
                        map.put("moduleC1",2);
                        map.put("moduleA2",3);
                        map.put("moduleB2",4);
                        map.put("moduleC2",5);

                        for (String module : variableAddressesStorage) {
                            if (Objects.equals(addressesList.get(map.get(module)), "00:00:00:00")) {
                                result = false;
                            }
                        }
                    }
                    break;
        }
        return result;
    }


    private static boolean powerCheck() {
        return false;
    }

    private static boolean synchronizationCheck() {
        return true;
    }

    private static boolean currentRangeCheck() {
        return false;
    }

    private static boolean resistanceCheck() {
        return false;
    }

}
