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

        // Отправка сообщений с настройками сценария
        Commands command = Commands.SET_SCENARO_1;
        for (int i = 0; i < addresses.size(); i++) {
            try {
                Inverters.sendCommandToInverter(addresses.get(i), command, modulesParameters.get(i));
                String response = ConnectionControl.analyzeResponse(
                        Inverters.getLastResponse(addresses.get(i), command),
                        ConnectionControl.ExpectedValue.PHRASE
                ).substring(1);

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

        // Отправка сообщений с командой на запуск сценария
        command = Commands.START_SCENARO_1;

        // ВАЖНО: обработку SC_RES выполняем не в RX-thread, чтобы не ловить deadlock
        // Лучше вынести executor в поле класса, но здесь оставляю локально, чтобы было самодостаточно.
        final java.util.concurrent.ExecutorService scenarioExec =
                java.util.concurrent.Executors.newFixedThreadPool(
                        Math.max(2, Runtime.getRuntime().availableProcessors() / 2)
                );

        // Мапа для хранения ссылок на задания по ожиданию ответа (но теперь это будут задачи "полной обработки")
        Map<Address, CompletableFuture<Void>> scenarioResultsTasks = new ConcurrentHashMap<>();
        AtomicBoolean scenarioFailed = new AtomicBoolean(false);

        for (int i = 0; i < addresses.size(); i++) {
            Address address = addresses.get(i);

            try {
                // Отправка команды на запуск
                Inverters.sendCommandToInverter(address, command, "");
                String response = ConnectionControl.analyzeResponse(
                        Inverters.getLastResponse(address, command),
                        ConnectionControl.ExpectedValue.PHRASE
                ).substring(1);

                System.out.println(response);

                if (!response.equals("START_SCENARO_1(YES)")) {
                    System.err.println("Ошибка! Не получен ответ START_SCENARO_1(YES) от модуля с адресом "
                            + address.toStringInHexFormat());
                    return CompletableFuture.completedFuture(false);
                }

                // Регистрация ожидания SC_RES
                CompletableFuture<ByteBuffer> scResFuture =
                        EventWaiter.getInstance().waitForEvent(
                                address,
                                EventWaiter.PossibleResponses.SC_RES,
                                Duration.ofMillis((long) (timeout * 4000L))
                        );

                PollingManager.start(address, (long) (timeout * 1000L));

                // КЛЮЧЕВОЕ ИЗМЕНЕНИЕ:
                // вместо whenComplete(...) -> handleAsync(..., scenarioExec)
                // чтобы блокирующие/тяжёлые действия не выполнялись в RX-thread.
                CompletableFuture<Void> task = scResFuture.handleAsync((buffer, err) -> {
                    try {
                        PollingManager.stop(address);

                        if (err != null) {
                            System.err.println("Модуль " + address.toStringInHexFormat()
                                    + " не прислал второе сообщение: " + err);
                            scenarioFailed.set(true);
                            return null;
                        }

                        responses.put(
                                address,
                                analyzeResults(ConnectionControl.analyzeResponse(
                                        ConnectionControl.extractBytes(buffer),
                                        ConnectionControl.ExpectedValue.NUMBER
                                ))
                        );

                        Inverters.respondToInverter(address, Commands.SC_RES, "YES");

                        // Если тебе нужно ДОБАВИТЬ отправку сообщения в конце сценария —
                        // делай это здесь, и это уже НЕ заблокирует поток приёма:
                        try {
                            Inverters.sendCommandToInverter(address, Commands.BUTTON_UNLOCK, "");
                        } catch (Exception e) {
                            scenarioFailed.set(true);
                            System.err.println("Ошибка BUTTON_UNLOCK для " + address.toStringInHexFormat() + ": " + e);
                        }

                    } catch (Exception e) {
                        scenarioFailed.set(true);
                        System.err.println("Ошибка пост-обработки SC_RES для " + address.toStringInHexFormat()
                                + ": " + e);
                    }
                    return null;
                }, scenarioExec);

                scenarioResultsTasks.put(address, task);

            } catch (Exception e) {
                PollingManager.stop(address);
                System.err.println("Ошибка при отправке команды START_SCENARO_1() модулю с адресом "
                        + address.toStringInHexFormat());
                return CompletableFuture.completedFuture(false);
            }
        }

        // Ждём выполнения всех задач обработки (а не только "прихода SC_RES")
        CompletableFuture<Void> all =
                CompletableFuture.allOf(scenarioResultsTasks.values().toArray(new CompletableFuture[0]));

        // Гарантированно гасим executor после завершения сценария
        return all.whenComplete((v, err) -> scenarioExec.shutdown())
                .thenApply(v -> !scenarioFailed.get());
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

