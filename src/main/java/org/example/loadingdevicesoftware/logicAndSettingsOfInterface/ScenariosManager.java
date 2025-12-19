package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl;
import org.example.loadingdevicesoftware.communicationWithInverters.EventWaiter;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;
import org.example.loadingdevicesoftware.communicationWithInverters.PollingManager;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScenariosManager {

    private ScenariosManager() {
    }

    @Getter
    private static final Map<Address, String[]> responses = new ConcurrentHashMap<>();

    /**
     * Метод для запуска сценария выдачи линейного тока
     *
     * @param modulesParameters
     * @param timeout
     * @return
     */
    public static CompletableFuture<Boolean> handControlScenarioOne(ArrayList<String> modulesParameters, double timeout) {
        responses.clear();
        ArrayList<Address> addresses = CheckingManager.getAvailableAddresses();

        //Отправка сообщений с настройками сценария
        Commands command = Commands.SET_SCENARO_1;
        for (int i = 0; i < addresses.size(); i++) {
            try {
                Inverters.sendCommandToInverter(addresses.get(i), command, modulesParameters.get(i));
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(addresses.get(i),
                        command), ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                if (!response.equals("SET_SCENARO_1(YES)")) {
                    System.err.println("Ошибка! Не получен ответ SET_SCENARO_1(YES) от модуля с адресом "
                            + addresses.get(i).toStringInHexFormat());
                    return CompletableFuture.completedFuture(false);
                }
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды SET_SCENARO_1() модулю с адресом "
                        + addresses.get(i).toStringInHexFormat());
                return CompletableFuture.completedFuture(false);
            }
        }

        //Отправка сообщений с командой на запуск сценария
        command = Commands.START_SCENARO_1;
        //Мапа для хранения ссылок на задания по ожиданию ответа
        Map<Address, CompletableFuture<ByteBuffer>> scenarioResultsFutures = new ConcurrentHashMap<>();
        AtomicBoolean scenarioFailed = new AtomicBoolean(false);
        for (int i = 0; i < addresses.size(); i++) {
            try {
                //Отправка команды на запуск
                Inverters.sendCommandToInverter(addresses.get(i), command, "");
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(addresses.get(i),
                        command), ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                if (!response.equals("START_SCENARO_1(YES)")) {
                    System.err.println("Ошибка! Не получен ответ START_SCENARO_1(YES) от модуля с адресом "
                            + addresses.get(i).toStringInHexFormat());
                    return CompletableFuture.completedFuture(false);
                }
                //Регистрация ожидания
                Address address = addresses.get(i);
                CompletableFuture<ByteBuffer> future =
                        EventWaiter.getInstance().waitForEvent(address, EventWaiter.PossibleResponses.SCENARIO_RESULTS,
                                Duration.ofMillis((long) (timeout * 2000L)));
                        EventWaiter.getInstance().waitForEvent(address, Duration.ofMillis((long) (timeout * 4000L)));
                scenarioResultsFutures.put(address, future);
                PollingManager.start(address, (long) (timeout * 1000L));
                future.whenComplete((buffer, err) -> {
                    if (err != null) {
                        System.err.println("Модуль " + address.toStringInHexFormat()
                                + " не прислал второе сообщение: " + err);
                        scenarioFailed.set(true);
                        PollingManager.stop(address);
                    } else {
                        responses.put(address, analyzeResults(ConnectionControl.
                                analyzeResponse(ConnectionControl.extractBytes(buffer),
                                        ConnectionControl.ExpectedValue.NUMBER)));
                        Inverters.respondToInverter(address, Commands.SC_RES, "YES");
                    }
                });
            } catch (Exception e) {
                PollingManager.stop(addresses.get(i));
                System.err.println("Ошибка при отправке команды START_SCENARO_1() модулю с адресом "
                        + addresses.get(i).toStringInHexFormat());
                return CompletableFuture.completedFuture(false);
            }
        }
        //Создаёт задачу на ожидание выполнения всех задач из списка
        CompletableFuture<Void> all =
                CompletableFuture.allOf(scenarioResultsFutures.values().toArray(new CompletableFuture[0]));
        //Ожидание выполнения всех задач из списка all с последующим возвратом значения scenarioFailed
        return all.thenApply(v -> !scenarioFailed.get());
    }

    /**
     * Метод для запуска сценария выдачи импульсного тока
     *
     * @param modulesParameters
     * @param timeout
     * @return
     */
    public static CompletableFuture<Boolean> handControlScenarioTwo(ArrayList<String> modulesParameters, double timeout) {
        responses.clear();
        ArrayList<Address> addresses = CheckingManager.getAvailableAddresses();

        //Отправка сообщений с настройками сценария
        Commands command = Commands.SET_SCENARO_2;
        for (int i = 0; i < addresses.size(); i++) {
            try {
                Inverters.sendCommandToInverter(addresses.get(i), command, modulesParameters.get(i));
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(addresses.get(i),
                        command), ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                if (!response.equals("SET_SCENARO_2(YES)")) {
                    System.err.println("Ошибка! Не получен ответ SET_SCENARO_2(YES) от модуля с адресом "
                            + addresses.get(i).toStringInHexFormat());
                    return CompletableFuture.completedFuture(false);
                }
            } catch (Exception e) {
                System.err.println("Ошибка при отправке команды SET_SCENARO_2() модулю с адресом "
                        + addresses.get(i).toStringInHexFormat());
                return CompletableFuture.completedFuture(false);
            }
        }

        //Отправка сообщений с командой на запуск сценария
        command = Commands.START_SCENARO_2;
        //Мапа для хранения ссылок на задания по ожиданию ответа
        Map<Address, CompletableFuture<ByteBuffer>> scenarioResultsFutures = new ConcurrentHashMap<>();
        AtomicBoolean scenarioFailed = new AtomicBoolean(false);
        for (int i = 0; i < addresses.size(); i++) {
            try {
                //Отправка команды на запуск
                Inverters.sendCommandToInverter(addresses.get(i), command, modulesParameters.get(i));
                String response = ConnectionControl.analyzeResponse(Inverters.getLastResponse(addresses.get(i),
                        command), ConnectionControl.ExpectedValue.PHRASE).substring(1);
                System.out.println(response);
                if (!response.equals("START_SCENARO_2(YES)")) {
                    System.err.println("Ошибка! Не получен ответ START_SCENARO_2(YES) от модуля с адресом "
                            + addresses.get(i).toStringInHexFormat());
                    return CompletableFuture.completedFuture(false);
                }
                //Регистрация ожидания
                Address address = addresses.get(i);
                CompletableFuture<ByteBuffer> future =
                        EventWaiter.getInstance().waitForEvent(address, EventWaiter.PossibleResponses.SCENARIO_RESULTS,
                                Duration.ofMillis((long) (timeout * 2000L)));
                        EventWaiter.getInstance().waitForEvent(address, Duration.ofMillis((long) (timeout * 4000L)));
                scenarioResultsFutures.put(address, future);
                PollingManager.start(address, (long) (timeout * 1000L));
                future.whenComplete((buffer, err) -> {
                    if (err != null) {
                        System.err.println("Модуль " + address.toStringInHexFormat()
                                + " не прислал второе сообщение: " + err);
                        scenarioFailed.set(true);
                        PollingManager.stop(address);
                    } else {
                        responses.put(address, analyzeResults(ConnectionControl.
                                analyzeResponse(ConnectionControl.extractBytes(buffer),
                                        ConnectionControl.ExpectedValue.NUMBER)));
                        Inverters.respondToInverter(address, Commands.SC_RES, "YES");
                    }
                });
            } catch (Exception e) {
                PollingManager.stop(addresses.get(i));
                System.err.println("Ошибка при отправке команды START_SCENARO_2() модулю с адресом "
                        + addresses.get(i).toStringInHexFormat());
                return CompletableFuture.completedFuture(false);
            }
        }
        //Создаёт задачу на ожидание выполнения всех задач из списка
        CompletableFuture<Void> all =
                CompletableFuture.allOf(scenarioResultsFutures.values().toArray(new CompletableFuture[0]));
        //Ожидание выполнения всех задач из списка all с последующим возвратом значения scenarioFailed
        return all.thenApply(v -> !scenarioFailed.get());
    }

    /**
     * Метод для остановки ожидания сообщений от инверторов через EventWaiter, если такое есть.
     */
    private static void shutdown() {

    }

    /**
     * Метод для получения массива строк из
     * результата сценария
     */
    public static String[] analyzeResults(String input) {
        return input.replace(" ", "").split(",");
    }

}

