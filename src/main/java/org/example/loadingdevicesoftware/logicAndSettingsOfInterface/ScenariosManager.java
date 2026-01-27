package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl;
import org.example.loadingdevicesoftware.communicationWithInverters.EventWaiter;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;
import org.example.loadingdevicesoftware.communicationWithInverters.PollingManager;

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

        // 1) SET_SCENARO_1 всем адресам (async) + барьер
        ArrayList<CompletableFuture<Boolean>> setFutures = new ArrayList<>();
        for (int i = 0; i < addresses.size(); i++) {
            Address addr = addresses.get(i);

            setFutures.add(
                    sendAndExpectPhrase(addr,
                            Commands.SET_SCENARO_1,
                            modulesParameters.get(i),
                            "SET_SCENARO_1(YES)")
            );
        }

        CompletableFuture<Boolean> setAll =
                CompletableFuture.allOf(setFutures.toArray(new CompletableFuture[0]))
                        .thenApply(v -> setFutures.stream().allMatch(CompletableFuture::join));

        // 2) START_SCENARO_1 всем адресам + ожидание SC_RES
        return setAll.thenCompose(setOk -> {
            if (!setOk) {
                System.err.println("Ошибка! Не все модули подтвердили SET_SCENARO_1(YES)");
                return CompletableFuture.completedFuture(false);
            }

            ArrayList<CompletableFuture<Boolean>> resultFutures = new ArrayList<>();
            for (Address addr : addresses) {
                resultFutures.add(
                        startAndWaitScenarioResult(addr, Commands.START_SCENARO_1, "", timeout)
                );
            }

            return CompletableFuture.allOf(resultFutures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> resultFutures.stream().allMatch(CompletableFuture::join));
        });
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

        // 1) SET_SCENARO_2
        ArrayList<CompletableFuture<Boolean>> setFutures = new ArrayList<>();
        for (int i = 0; i < addresses.size(); i++) {
            Address addr = addresses.get(i);

            setFutures.add(
                    sendAndExpectPhrase(addr,
                            Commands.SET_SCENARO_2,
                            modulesParameters.get(i),
                            "SET_SCENARO_2(YES)")
            );
        }

        CompletableFuture<Boolean> setAll =
                CompletableFuture.allOf(setFutures.toArray(new CompletableFuture[0]))
                        .thenApply(v -> setFutures.stream().allMatch(CompletableFuture::join));

        // 2) START_SCENARO_2 + ожидание SC_RES
        return setAll.thenCompose(setOk -> {
            if (!setOk) {
                System.err.println("Ошибка! Не все модули подтвердили SET_SCENARO_2(YES)");
                return CompletableFuture.completedFuture(false);
            }

            ArrayList<CompletableFuture<Boolean>> resultFutures = new ArrayList<>();
            for (int i = 0; i < addresses.size(); i++) {
                Address addr = addresses.get(i);

                // В вашей текущей версии START_SCENARO_2 отправляется с modulesParameters.get(i)
                // Я сохраняю это поведение.
                resultFutures.add(
                        startAndWaitScenarioResult(addr, Commands.START_SCENARO_2, modulesParameters.get(i), timeout)
                );
            }

            return CompletableFuture.allOf(resultFutures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> resultFutures.stream().allMatch(CompletableFuture::join));
        });
    }


    private static CompletableFuture<Boolean> sendAndExpectPhrase(Address address,
                                                                  Commands command,
                                                                  String args,
                                                                  String expectedPhrase) {
        return Inverters.sendCommandToInverterAsync(address, command, args)
                .thenApply(bytes -> {
                    String resp = ConnectionControl
                            .analyzeResponse(bytes, ConnectionControl.ExpectedValue.PHRASE);
                    return expectedPhrase.equals(resp);
                })
                .exceptionally(ex -> false);
    }

    private static CompletableFuture<Boolean> startAndWaitScenarioResult(
            Address address,
            Commands startCommand,
            String startArgs,
            double timeoutSeconds
    ) {
        final String expectedStart = startCommand.name() + "(YES)";
        final long timeoutMs = (long) (timeoutSeconds * 4000L);

        // 1) Отправляем START (async) и проверяем подтверждение
        CompletableFuture<Boolean> scenarioFuture =
                sendAndExpectPhrase(address, startCommand, startArgs, expectedStart)
                        .thenCompose(startOk -> {
                            if (!startOk) {
                                System.err.println("Ошибка! Не получен ответ " + expectedStart + " от модуля "
                                        + address.toStringInHexFormat());
                                // START не подтвердили — считаем сценарий проваленным, но unlock всё равно сделаем ниже
                                return CompletableFuture.completedFuture(false);
                            }

                            // 2) START подтверждён: запускаем polling и ждём SC_RES
                            PollingManager.start(address, (long) (timeoutSeconds * 1000L));

                            return EventWaiter.getInstance()
                                    .waitForEvent(address,
                                            EventWaiter.PossibleResponses.SC_RES,
                                            Duration.ofMillis(timeoutMs))
                                    .handle((buffer, err) -> {
                                        if (err != null || buffer == null) {
                                            System.err.println("Модуль " + address.toStringInHexFormat()
                                                    + " не прислал SC_RES: " + (err != null ? err.getMessage() : "null"));
                                            return false;
                                        }

                                        try {
                                            responses.put(address,
                                                    analyzeResults(ConnectionControl.analyzeResponse(
                                                            buffer, ConnectionControl.ExpectedValue.NUMBER)));

                                            // подтверждаем приём результата сценария
                                            Inverters.respondToInverter(address, Commands.SC_RES, "YES");
                                            return true;

                                        } catch (Exception parseEx) {
                                            System.err.println("Ошибка обработки SC_RES от " + address.toStringInHexFormat()
                                                    + ": " + parseEx.getMessage());
                                            return false;
                                        }
                                    })
                                    // 3) ВАЖНО: polling остановить в любом случае после ожидания SC_RES
                                    .whenComplete((r, e) -> PollingManager.stop(address));
                        })
                        // Если что-то пошло не так на верхнем уровне — считаем сценарий проваленным,
                        // но unlock всё равно будет сделан ниже.
                        .exceptionally(ex -> {
                            System.err.println("Ошибка выполнения сценария на модуле " + address.toStringInHexFormat()
                                    + ": " + ex.getMessage());
                            // stop на всякий случай (если ошибка случилась до whenComplete)
                            PollingManager.stop(address);
                            return false;
                        });

        // 4) BUTTON_UNLOCK отправляется ВСЕГДА, и итог сценария при этом сохраняется.
        return scenarioFuture.thenCompose(success ->
                Inverters.sendCommandToInverterAsync(address, Commands.BUTTON_UNLOCK, "")
                        .thenApply(bytes -> {
                            String resp = ConnectionControl
                                    .analyzeResponse(bytes, ConnectionControl.ExpectedValue.PHRASE);

                            System.out.println("[BUTTON_UNLOCK] " + address.toStringInHexFormat() + " -> " + resp);
                            return success; // возвращаем результат сценария, не результат unlock
                        })
                        .exceptionally(ex -> {
                            System.out.println("[BUTTON_UNLOCK] " + address.toStringInHexFormat()
                                    + " -> ERROR: " + ex.getMessage());
                            return success; // unlock не удался, но результат сценария не меняем
                        })
        );
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

