package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckingManager {

    private CheckingManager() {
    }

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public enum Scenarios {
        THREE_PHASE_SWITCHER, SINGLE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, DIFFERENTIAL_PROTECTION,
        HAND_CONTROL, SINGLE_PHASE_PROTECTION,THREE_PHASE_PROTECTION
    }

    private static final HashMap<String, Address> addressesStorage = new HashMap<>();

    private static Scenarios scenario;

    @Getter
    private static boolean firstCheck = false, secondCheck = false, thirdCheck = false, fourthCheck = false, fifthCheck = false;

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
     * Также в данном методе осуществляется заполнение списка нужных для проверок адресов. Оно срабатывает только при
     * успешном прохождении проверки.
     *
     * @return false, если отсутствует настройка хотя бы для одного модуля.
     * @throws IOException
     */

    public static boolean settingsCheck(Scenarios scenario) throws IOException {
        List<String> addressesList = AddressesStorage.readAddresses();
        boolean result = false;
        switch (scenario) {
            case SINGLE_PHASE_SWITCHER, SINGLE_PHASE_PROTECTION:
                if (!addressesList.isEmpty()) {
                    result = !Objects.equals(addressesList.getFirst(), "00:00:00:00");
                    if (result) {
                        if (!addressesStorage.isEmpty()) {
                            addressesStorage.clear();
                        }
                        addressesStorage.put("moduleA1", ConnectionControl.getInvertersAddress(0));
                    }
                }
                break;
            case THREE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, THREE_PHASE_PROTECTION:
                if (addressesList.size() >= 3) {
                    result = (!Objects.equals(addressesList.getFirst(), "00:00:00:00")) &&
                            (!Objects.equals(addressesList.get(1), "00:00:00:00")) &&
                            (!Objects.equals(addressesList.get(2), "00:00:00:00"));
                }
                if (!addressesStorage.isEmpty()) {
                    addressesStorage.clear();
                }
                addressesStorage.put("moduleA1", ConnectionControl.getInvertersAddress(0));
                addressesStorage.put("moduleB1", ConnectionControl.getInvertersAddress(1));
                addressesStorage.put("moduleC1", ConnectionControl.getInvertersAddress(2));
                break;
            case DIFFERENTIAL_PROTECTION:
                if (addressesList.size() == 6) {
                    result = !addressesList.contains("00:00:00:00");
                }
                if (!addressesStorage.isEmpty()) {
                    addressesStorage.clear();
                }
                addressesStorage.put("moduleA1", ConnectionControl.getInvertersAddress(0));
                addressesStorage.put("moduleB1", ConnectionControl.getInvertersAddress(1));
                addressesStorage.put("moduleC1", ConnectionControl.getInvertersAddress(2));
                addressesStorage.put("moduleA2", ConnectionControl.getInvertersAddress(3));
                addressesStorage.put("moduleB2", ConnectionControl.getInvertersAddress(4));
                addressesStorage.put("moduleC2", ConnectionControl.getInvertersAddress(5));
                break;
            case HAND_CONTROL:
                if (!variableAddressesStorage.isEmpty()) {
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("moduleA1", 0);
                    map.put("moduleB1", 1);
                    map.put("moduleC1", 2);
                    map.put("moduleA2", 3);
                    map.put("moduleB2", 4);
                    map.put("moduleC2", 5);

                    if (!addressesStorage.isEmpty()) {
                        addressesStorage.clear();
                    }

                    for (String module : variableAddressesStorage) {
                        if (Objects.equals(addressesList.get(map.get(module)), "00:00:00:00")) {
                            result = false;
                        } else {
                            addressesStorage.put(module, ConnectionControl.getInvertersAddress(map.get(module)));
                        }
                    }
                }
                break;
        }
        return result;
    }

    /** В данном методе осуществляется последовательная проверка каждого модуля на необходимый уровень напряжения
     * на стороне постоянного тока. Он должен иметь отклонение не более 13% от 380*sqrt(2).
     * TODO добавить анализ ответа для получения значения напряжения
     * @return false, если хотя бы один модуль не прошёл проверку
     */
    public static boolean powerCheck() {
        double percent = 13.;
        double lowerReference = (1. - percent / 100.) * 380. * Math.sqrt(2.);
        double upperReference = (1. + percent / 100.) * 380. * Math.sqrt(2.);
        ArrayList<Double> voltages = new ArrayList<>();
        /// //Место под отправку сообщений
        for (Address address : addressesStorage.values()) {
            try {
                Inverters.sendCommandToInverter(address, Commands.MODBUS,"0");
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды инвертору + " + address.toStringInHexFormat());
            }
        }
        for (Double voltage : voltages) {
            if (!(voltage >= lowerReference && voltage <= upperReference)) {
                return false;
            }
        }
        return true;
    }

    public static boolean synchronizationCheck() {
        return true;
    }

    public static boolean currentRangeCheck() {
        return true;
    }

    public static boolean resistanceCheck() {
        return true;
    }

}
