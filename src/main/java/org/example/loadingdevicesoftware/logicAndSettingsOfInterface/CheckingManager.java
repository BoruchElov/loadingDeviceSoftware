package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import lombok.Setter;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl;
import org.example.loadingdevicesoftware.communicationWithInverters.EventWaiter;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;
import org.example.loadingdevicesoftware.pagesControllers.StatusService;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class CheckingManager {

    private CheckingManager() {
    }

    public enum Scenarios {
        THREE_PHASE_SWITCHER, SINGLE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, DIFFERENTIAL_PROTECTION,
        HAND_CONTROL, SINGLE_PHASE_PROTECTION, THREE_PHASE_PROTECTION
    }

    @Setter
    @Getter
    private static ArrayList<Double> currents = new ArrayList<>();

    @Getter
    private static ArrayList<Double> resistanceCheckParameters = new ArrayList<>();

    private static final HashMap<String, Address> addressesStorage = new HashMap<>();
    @Getter
    private static final ArrayList<Address> availableAddresses = new ArrayList<>();

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
    //TODO доработать проверки для остальных сценариев
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
     * <p>
     * Также в данном методе осуществляется заполнение списка нужных для проверок адресов. Оно срабатывает только при
     * успешном прохождении проверки.
     *
     * @return false, если отсутствует настройка хотя бы для одного модуля.
     * @throws IOException
     */

    public static boolean settingsCheck(Scenarios scenario) throws IOException {
        ArrayList<String> addressesList = AddressesStorage.getListOfSavedAddresses();
        if (!availableAddresses.isEmpty()) {
            availableAddresses.clear();
        }
        boolean result = true;
        switch (scenario) {
            case SINGLE_PHASE_SWITCHER, SINGLE_PHASE_PROTECTION:
                result = !Objects.equals(addressesList.getFirst(), "00:00:00:00");
                if (!result) System.out.println("Ошибка! Не настроено достаточное количество модулей.");
                result &= StatusService.getInstance().isAddressOnline(addressesList.getFirst());
                if (result) {
                    if (!addressesStorage.isEmpty()) {
                        addressesStorage.clear();
                    }
                    addressesStorage.put("moduleA1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.getFirst())));
                } else {
                    System.out.println("Ошибка! Нужный модуль не находится в сети.");
                }
                break;
            case THREE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, THREE_PHASE_PROTECTION:
                result = (!Objects.equals(addressesList.getFirst(), "00:00:00:00")) &&
                        (!Objects.equals(addressesList.get(1), "00:00:00:00")) &&
                        (!Objects.equals(addressesList.get(2), "00:00:00:00"));
                if (!result) System.out.println("Ошибка! Не настроено достаточное количество модулей.");
                result &= (StatusService.getInstance().isAddressOnline(addressesList.getFirst()) &&
                        StatusService.getInstance().isAddressOnline(addressesList.get(1)) &&
                        StatusService.getInstance().isAddressOnline(addressesList.get(2)));
                if (result) {
                    if (!addressesStorage.isEmpty()) {
                        addressesStorage.clear();
                    }
                    addressesStorage.put("moduleA1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.getFirst())));
                    addressesStorage.put("moduleB1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(1))));
                    addressesStorage.put("moduleC1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(2))));
                } else {
                    System.out.println("Ошибка! Нужное количество модулей не находится в сети.");
                }
                break;
            case DIFFERENTIAL_PROTECTION:
                result = !addressesList.contains("00:00:00:00");
                if (!result) System.out.println("Ошибка! Не настроено достаточное количество модулей.");
                result &= (StatusService.getInstance().isAddressOnline(addressesList.getFirst()) &&
                StatusService.getInstance().isAddressOnline(addressesList.get(1)) &&
                StatusService.getInstance().isAddressOnline(addressesList.get(2)) &&
                StatusService.getInstance().isAddressOnline(addressesList.get(3)) &&
                StatusService.getInstance().isAddressOnline(addressesList.get(4)) &&
                StatusService.getInstance().isAddressOnline(addressesList.get(5)));
                if (result) {
                    if (!addressesStorage.isEmpty()) {
                        addressesStorage.clear();
                    }
                    addressesStorage.put("moduleA1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.getFirst())));
                    addressesStorage.put("moduleB1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(1))));
                    addressesStorage.put("moduleC1", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(2))));
                    addressesStorage.put("moduleA2", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(3))));
                    addressesStorage.put("moduleB2", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(4))));
                    addressesStorage.put("moduleC2", new Address(ConnectionControl.
                            toIntFromHexString(addressesList.get(5))));
                } else {
                    System.out.println("Ошибка! Нужное количество модулей не находится в сети.");
                }

                break;
            case HAND_CONTROL:
                //variableAddressesStorage - это хранилище адресов, которые выбрал пользователь в сценарии ручного ввода
                if (!variableAddressesStorage.isEmpty()) {
                    HashMap<String,String> addresses = AddressesStorage.readAddresses();
                    if (!addressesStorage.isEmpty()) {
                        addressesStorage.clear();
                    }
                    for (String module : variableAddressesStorage) {
                        if (Objects.equals(addresses.get(module), "00:00:00:00")) {
                            System.out.println("Ошибка! Модуль " + module + " не настроен.");
                            return false;
                        }
                        if (!StatusService.getInstance().isAddressOnline(addresses.get(module))) {
                            System.out.println("Ошибка! Модуль " + module + " с адресом " + addresses.get(module) +
                                    " не в сети.");
                            return false;
                        }
                        addressesStorage.put(module, new Address(ConnectionControl.toIntFromHexString(addresses.get(module))));
                    }

                }
                break;
        }
        if (!addressesStorage.isEmpty()) {
            for (String module : addressesStorage.keySet()) {
                availableAddresses.add(addressesStorage.get(module));
            }
        }
        return result;
    }

    /**
     * В данном методе осуществляется последовательная проверка каждого модуля на необходимый уровень напряжения
     * на стороне постоянного тока. Он должен иметь отклонение не более 13% от 380*sqrt(2).
     * TODO добавить анализ ответа для получения значения напряжения
     *
     * @return false, если хотя бы один модуль не прошёл проверку
     */
    public static boolean powerCheck() {
        double percent = 13.;
        double lowerReference = (1. - percent / 100.) * 380. * Math.sqrt(2.);
        double upperReference = (1. + percent / 100.) * 380. * Math.sqrt(2.);
        ArrayList<Double> voltages = new ArrayList<>();
        Address address = null;
        for (String module : addressesStorage.keySet()) {
            try {
                address = addressesStorage.get(module);
                Commands command = Commands.MODBUS;
                String arguments = "03,0000,0002";
                Inverters.sendCommandToInverter(address, command, arguments);
                String voltage = ConnectionControl.analyzeResponse(Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.NUMBER);
                System.out.println("Модуль " + module + ", Адрес " + address.toStringInHexFormat() +
                        ", Напряжение " + voltage + "В, диапазон: [" + Math.round(lowerReference) + ";" + Math.round(upperReference) + "]");
                voltages.add(Double.parseDouble(voltage));
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды инвертору " + address.toStringInHexFormat());
                return false;
            }
        }
        for (
                Double voltage : voltages) {
            if (!(voltage >= lowerReference && voltage <= upperReference)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Метод для реализации проверки синхронизации. Пока что заглушка.
     * TODO дополнить, как только появится работающая реализация алгоритма синхронизации.
     *
     * @return
     */
    public static boolean synchronizationCheck() {
        return true;
    }


    /**
     * Метод для реализации проверки диапазона тока. Если заданный пользователем ток не попадает в диапазон, определённый
     * выключателем, проверка не проходит.
     * TODO продумать реализацию функционала интерпретации параметров формы в зависимости от сценария
     *
     * @return
     */
    public static boolean currentRangeCheck() {
        //Проверка наличия параметров из формы, необходимых для проверки
        if (currents.isEmpty()) {
            System.err.println("Ошибка! Не переданы параметры из формы.");
            return false;
        }
        ArrayList<Integer> responses = new ArrayList<>();
        //Последовательный запрос положения переключателя у каждого модуля
        Address address = null;
        int j = 0;
        try {
            for (String module : addressesStorage.keySet()) {
                address = addressesStorage.get(module);
                Inverters.sendCommandToInverter(address, Commands.CHECK_SWITCH_POS, "");
                int response = Integer.parseInt(ConnectionControl.analyzeResponse(Inverters.getLastResponse(address,
                        Commands.CHECK_SWITCH_POS), ConnectionControl.ExpectedValue.NUMBER));
                responses.add(response);
                System.out.println("Модуль " + module + ", Адрес " + address.toStringInHexFormat() +
                        ", Положение переключателя " + response + ", Уставка по току " + currents.get(j) + "А");
                j += 1;
            }
        } catch (Exception e) {
            System.err.println("Ошибка! Получено некорректное положение выключателя.");
            return false;
        }
        //Оценка полученных положений переключателей на соответствие заданному току
        if (!responses.isEmpty()) {
            Integer[] newResponses = responses.toArray(new Integer[0]);
            Double[] newCurrents = currents.toArray(new Double[0]);
            for (int i = 0; i < newResponses.length; i++) {
                System.out.println("Положение переключателя: " + newResponses[i] + "; Уставка по току: " + newCurrents[i]);
                switch (newResponses[i].intValue()) {
                    case 1:
                        if (newCurrents[2 * i] < 1. || newCurrents[2 * i] > 40.) {
                            return false;
                        }
                        break;
                    case 2:
                        if (newCurrents[2 * i] < 40. || newCurrents[2 * i] > 400.) {
                            return false;
                        }
                        break;
                    case 3:
                        if (newCurrents[2 * i] < 400. || newCurrents[2 * i] > 3000.) {
                            return false;
                        }
                        break;
                    default:
                        System.err.println("Некорректный диапазон. Положение переключателя: " + newResponses[i]);
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод для реализации проверки сопротивления.
     * TODO реализовать логику ожидания ответа о результатах выполнения сценария
     * TODO продумать реализацию функционала интерпретации параметров формы в зависимости от сценария
     *
     * @return
     */
    public static boolean resistanceCheck() {
        Double[] parameters = resistanceCheckParameters.toArray(new Double[0]);
        int i = 0;
        //Передача всем настроенным и находящимся в сети модулям команды на настройку сценария
        Address address = null;
        for (String module : addressesStorage.keySet()) {
            address = addressesStorage.get(module);
            Commands command = Commands.SET_RESISTANCE_CHECK;
            try {
                String time = "6.0";
                String data = parameters[3 * i] + "," + parameters[3 * i + 1] + "," + time;
                Inverters.sendCommandToInverter(address, command, data);
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                if (!response.equals("SET_RESISTANCE_CHECK(YES)")) {
                    System.err.println("Ошибка! Не получен ответ SET_RESISTANCE_CHECK(YES) от модуля " + module
                            + " с адресом " + address.toStringInHexFormat());
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды SET_RESISTANCE_CHECK() модулю " + module
                        + " с адресом " + address.toStringInHexFormat());
                return false;
            }
            i += 1;
        }
        i = 0;
        //Передача всем настроенным и находящимся в сети модулям команды на запуск сценария, фиксация запроса на ожидание результатов
        //и анализ полученного ответа
        for (String module : addressesStorage.keySet()) {
            address = addressesStorage.get(module);
            Commands command = Commands.START_RESISTANCE_CHECK;
            try {
                Inverters.sendCommandToInverter(address, command, "");
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                if (!response.equals("START_RESISTANCE_CHECK(YES)")) {
                    System.err.println("Ошибка! Не получен ответ START_RESISTANCE_CHECK(YES) от модуля " + module
                            + " с адресом " + address.toStringInHexFormat());
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды START_RESISTANCE_CHECK() модулю " + module
                        + " с адресом " + address.toStringInHexFormat());
                return false;
            }
            try {
                EventWaiter.getInstance().waitForEvent(address, Duration.ofSeconds(120)).get();
                String result = ConnectionControl.analyzeResponse(EventWaiter.getResponse(address),
                        ConnectionControl.ExpectedValue.NUMBER);
                System.out.println(result);
                result = result.substring(result.indexOf("(") + 1, result.indexOf(","));
                System.out.println(result);
                System.out.println("Ответ на сценарий: \"" + result + "\"");
                if (!result.equals("S")) {
                    System.err.println("Ошибка! Не выполнена проверка сопротивления модулем " + module
                            + " с адресом " + address.toStringInHexFormat());
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Ошибка при ожидании результатов сценария от модуля " + module + " с адресом: "
                        + address.toStringInHexFormat());
            }
            i += 1;
        }
        return true;
    }

}
