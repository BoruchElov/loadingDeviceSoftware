package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import lombok.Setter;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl;
import org.example.loadingdevicesoftware.communicationWithInverters.EventWaiter;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckingManager {

    private CheckingManager() {
    }

    public enum Scenarios {
        THREE_PHASE_SWITCHER, SINGLE_PHASE_SWITCHER, MEASUREMENT_TRANSFORMER, COMTRADE, DIFFERENTIAL_PROTECTION,
        HAND_CONTROL, SINGLE_PHASE_PROTECTION, THREE_PHASE_PROTECTION
    }

    @Setter
    @Getter
    private static ArrayList<Double> formParameters = new ArrayList<>();

    private static final HashMap<String, Address> addressesStorage = new HashMap<>();

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
     * <p>
     * Также в данном методе осуществляется заполнение списка нужных для проверок адресов. Оно срабатывает только при
     * успешном прохождении проверки.
     *
     * @return false, если отсутствует настройка хотя бы для одного модуля.
     * @throws IOException
     */

    public static boolean settingsCheck(Scenarios scenario) throws IOException {
        List<String> addressesList = AddressesStorage.readAddresses();
        boolean result = true;
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
                            Address address = ConnectionControl.getInvertersAddress(map.get(module));
                            for (int i = 0; i < 6; i++) {
                                address = ConnectionControl.getInvertersAddress(i);
                                if (addressesList.get(map.get(module)).equals(address.toStringInHexFormat())) {
                                    break;
                                }
                            }
                            addressesStorage.put(module, address);
                        }
                    }
                }
                break;
        }
        return result;
        //return true;
    }

    /**
     * В данном методе осуществляется последовательная проверка каждого модуля на необходимый уровень напряжения
     * на стороне постоянного тока. Он должен иметь отклонение не более 13% от 380*sqrt(2).
     * TODO добавить анализ ответа для получения значения напряжения
     *
     * @return false, если хотя бы один модуль не прошёл проверку
     */
    public static boolean powerCheck() {
        /*double percent = 13.;
        double lowerReference = (1. - percent / 100.) * 380. * Math.sqrt(2.);
        double upperReference = (1. + percent / 100.) * 380. * Math.sqrt(2.);
        ArrayList<Double> voltages = new ArrayList<>();
        for (Address address : addressesStorage.values()) {
            try {
                Commands command = Commands.MODBUS;
                String arguments = "03,0000,0002";
                Inverters.sendCommandToInverter(address, command, arguments);
                String voltage = ConnectionControl.analyzeResponse(Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.NUMBER);
                voltages.add(Double.parseDouble(voltage));
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды инвертору " + address.toStringInHexFormat());
                return false;
            }
        }
        for (Double voltage : voltages) {
            if (!(voltage >= lowerReference && voltage <= upperReference)) {
                return false;
            }
        }
        return true;*/
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
/*
        if (formParameters.isEmpty()) {
            System.err.println("Ошибка! Не переданы параметры из формы.");
            return false;
        }

        ArrayList<Integer> responses = new ArrayList<>();

        try {
            for (Address address : addressesStorage.values()) {
                Inverters.sendCommandToInverter(address, Commands.CHECK_SWITCH_POS, "");
                int response = Integer.parseInt(ConnectionControl.analyzeResponse(Inverters.getLastResponse(address,
                        Commands.CHECK_SWITCH_POS), ConnectionControl.ExpectedValue.NUMBER));
                responses.add(response);
            }

        } catch (Exception e) {
            System.err.println("Ошибка! Получено некорректное положение выключателя.");
            return false;
        }

        if (!responses.isEmpty()) {
            System.out.println("Пришли непустые сообщения.");
            Integer[] newResponses = responses.toArray(new Integer[0]);
            Double[] newCurrents = formParameters.toArray(new Double[0]);
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
        }*/
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
        Double[] newCurrents = formParameters.toArray(new Double[0]);
        int i = 0;

        //Передача всем настроенным и находящимся в сети модулям команды на настройку сценария
        for (Address address : addressesStorage.values()) {
            Commands command = Commands.SET_RESISTANCE_CHECK;
            try {
                String data = newCurrents[i] + "," + newCurrents[i] + "," + newCurrents[i];
                Inverters.sendCommandToInverter(address, command, data);
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                /*if (!response.equals("SET_RESISTANCE_CHECK(NO)")) {
                    System.err.println("Ошибка! Получен неожиданный ответ от инвертора " + address.toStringInHexFormat());
                    return false;
                }*/
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды инвертору + " + address.toStringInHexFormat());
                return false;
            }
            i += 1;
        }
        i = 0;
        //Передача всем настроенным и находящимся в сети модулям команды на запуск сценария и фиксация запроса на ожидание результатов
        for (Address address : addressesStorage.values()) {
            Commands command = Commands.START_RESISTANCE_CHECK;
            try {
                Inverters.sendCommandToInverter(address, command, "");
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                /*if (!response.equals("START_RESISTANCE_CHECK(NO)")) {
                    System.err.println("Ошибка! Получен неожиданный ответ от инвертора " + address.toStringInHexFormat());
                    return false;
                }*/
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды инвертору + " + address.toStringInHexFormat());
                return false;
            }
            try {
                EventWaiter.getInstance().waitForEvent(address, Duration.ofSeconds(120)).get();
                String result = ConnectionControl.analyzeResponse(EventWaiter.getResponse(address),
                        ConnectionControl.ExpectedValue.NUMBER);
                System.out.println(new String(EventWaiter.getResponse(address), StandardCharsets.UTF_8));
                System.out.println("Ответ на сценарий: \"" + result + "\"");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Ошибка при ожидании результатов сценария от: " + address.toStringInHexFormat());
            }
            i += 1;
        }
        return true;
    }

}
