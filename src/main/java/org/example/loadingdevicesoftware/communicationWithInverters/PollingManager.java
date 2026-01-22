package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.InverterParams;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl.analyzeResponse;

public final class PollingManager {

    private static final long pollingPeriod = 911; // ms
    private static final ConcurrentHashMap<Address, ScheduledExecutorService> executors = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Address, ScheduledFuture<?>> futures = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Address, InverterParams> paramsMap = new ConcurrentHashMap<>();

    // новое: флаг активного запроса на адрес
    private static final ConcurrentHashMap<Address, AtomicBoolean> inFlight = new ConcurrentHashMap<>();

    // опционально: если хотите уметь cancel текущий запрос при stop()
    private static final ConcurrentHashMap<Address, CompletableFuture<?>> activeRequests = new ConcurrentHashMap<>();

    private static final String MODBUS_ARGS = "03,1000,1010";

    private PollingManager() {
    }

    public static InverterParams getParams(Address address) {
        return paramsMap.computeIfAbsent(address, a -> new InverterParams());
    }

    public static void start(Address address, long durationMs) {

        // Если уже запущено — ничего не делаем (или перезапуск по вашей логике)
        if (futures.containsKey(address)) {
            return;
        }

        if (durationMs < pollingPeriod + 200) return;

        paramsMap.computeIfAbsent(address, a -> new InverterParams());
        inFlight.computeIfAbsent(address, a -> new AtomicBoolean(false));

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "Polling-" + address.toStringInHexFormat());
            t.setDaemon(true);
            return t;
        });

        executors.put(address, executor);

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> tick(address), 0, pollingPeriod, TimeUnit.MILLISECONDS);
        futures.put(address, future);

        executor.schedule(() -> stop(address), durationMs - pollingPeriod, TimeUnit.MILLISECONDS);
    }


    private static void tick(Address address) {

        AtomicBoolean flag = inFlight.get(address);
        if (flag == null) return;

        // если предыдущий запрос ещё не завершён — этот тик пропускаем
        if (!flag.compareAndSet(false, true)) {
            return;
        }

        CompletableFuture<byte[]> cf = Inverters.sendCommandToInverterAsync(address, Commands.MODBUS, MODBUS_ARGS);

        activeRequests.put(address, cf);

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
                        // System.out.println("(Polling) parse error " + address + ": " + parseEx.getMessage());
                    } finally {
                        activeRequests.remove(address);
                        flag.set(false);
                    }
                });
    }


    public static void stop(Address address) {

        ScheduledFuture<?> f = futures.remove(address);
        if (f != null) {
            f.cancel(true);
        }

        ScheduledExecutorService ex = executors.remove(address);
        if (ex != null) {
            ex.shutdownNow();
        }

        // сброс флага inFlight
        AtomicBoolean flag = inFlight.get(address);
        if (flag != null) {
            flag.set(false);
        }

        // опционально: попытка отменить активный запрос
        CompletableFuture<?> req = activeRequests.remove(address);
        if (req != null) {
            req.cancel(true);
        }
    }

    private static boolean isPolled(Address address) {
        return futures.containsKey(address);
    }


}
