package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Messages;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.InverterParams;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public final class PollingManager {

    private static final long pollingPeriod = 911; // ms
    private static final ConcurrentHashMap<Address, ScheduledFuture<?>> futures = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Address, InverterParams> paramsMap = new ConcurrentHashMap<>();

    private static ScheduledExecutorService pollingExecutor;
    // новое: флаг активного запроса на адрес
    private static final ConcurrentHashMap<Address, AtomicBoolean> inFlight = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Address, AtomicBoolean> isModulePolled = new ConcurrentHashMap<>();
    private static final AtomicBoolean isPollingRunning = new AtomicBoolean(false);

    private static final String MODBUS_ARGS = "03,1000,1010";

    private PollingManager() {
    }

    public static InverterParams getParams(Address address) {
        return paramsMap.computeIfAbsent(address, a -> new InverterParams());
    }

    public static void setupPolling() {
        pollingExecutor = Executors.newScheduledThreadPool(6);
        inFlight.clear();
        isModulePolled.clear();
        isPollingRunning.set(true);
    }

    public static void startPolling(Address address, long durationMs) {

        if (!isPollingRunning.get()) return;

        paramsMap.computeIfAbsent(address, a -> new InverterParams());
        inFlight.computeIfAbsent(address, a -> new AtomicBoolean(false));

        ScheduledFuture<?> future = pollingExecutor.scheduleAtFixedRate(() -> {
            AtomicBoolean flag = inFlight.get(address);
            if (!flag.compareAndSet(false, true)) {
                return;
            }
            CompletableFuture<byte[]> cf = Inverters.sendCommandToInverterAsync(address, Messages.MODBUS, MODBUS_ARGS);
            cf.orTimeout(7000, TimeUnit.MILLISECONDS) // подберите под вашу линию
                    .whenComplete((data, ex) -> {
                        try {
                            if (ex != null || data == null) {
                                // мягкий лог; polling не должен “убивать” поток
                                // System.out.println("(Polling) fail " + address + ": " + (ex != null ? ex.getMessage() : "null"));
                                return;
                            }

                            String response = (ConnectionControl.analyzeResponse(data, ConnectionControl.ExpectedValue.NUMBER)
                                    == null || ConnectionControl.analyzeResponse(data, ConnectionControl.ExpectedValue.NUMBER)
                                    .isBlank()) ? "0,0,0,0,0" : ConnectionControl.analyzeResponse(data, ConnectionControl
                                    .ExpectedValue.NUMBER);

                            InverterParams params = paramsMap.get(address);
                            if (params == null) return;

                            // Если внутри params обновляются JavaFX properties — обновляйте на FX thread
                            javafx.application.Platform.runLater(() -> params.updateFromResponse(response));

                        } catch (Exception parseEx) {
                            System.out.println("(PollingManager) Ошибка при парсинге ответа от " +
                                    address.toStringInHexFormat() + ": " + parseEx.getMessage());
                        } finally {
                            flag.set(false);
                        }
                    });
        }, 0, pollingPeriod, TimeUnit.MILLISECONDS);
        futures.put(address, future);
        isModulePolled.put(address, new AtomicBoolean(true));

        pollingExecutor.schedule(() -> stopPolling(address), durationMs - pollingPeriod, TimeUnit.MILLISECONDS);
    }

    public static void stopPolling(Address address) {

        if (!isModulePolled.get(address).get()) return;
        isModulePolled.get(address).set(false);

        ScheduledFuture<?> future = futures.get(address);
        if (future != null) {
            future.cancel(true);
        }
        AtomicBoolean flag = inFlight.get(address);
        if (flag != null) {
            flag.set(false);
        }
    }

    public static void stop() {
        if (!isPollingRunning.get()) return;
        isPollingRunning.set(false);

        if (pollingExecutor != null) {
            pollingExecutor.shutdown();
            pollingExecutor = null;
        }
    }
}
