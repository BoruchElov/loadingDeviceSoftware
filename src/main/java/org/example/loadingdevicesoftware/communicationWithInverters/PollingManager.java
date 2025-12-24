package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.InverterParams;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;

import java.util.Map;
import java.util.concurrent.*;

import static org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl.analyzeResponse;

public class PollingManager {

    private static final Map<Address, ScheduledExecutorService> executors =
            new ConcurrentHashMap<>();

    private static final Map<Address, ScheduledFuture<?>> futures =
            new ConcurrentHashMap<>();

    private static final Map<Address, InverterParams> paramsMap =
            new ConcurrentHashMap<>();

    private PollingManager() {
    }

    public static InverterParams getParams(Address address) {
        return paramsMap.get(address);
    }

    public static void start(Address address, long timeoutMs) {

        if (executors.containsKey(address)) return;
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executors.put(address, executor);

        InverterParams params = new InverterParams();
        paramsMap.putIfAbsent(address, params);

        long pollingPeriod = 971;

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
                    try {
                        Inverters.sendCommandToInverter(
                                address,
                                Commands.MODBUS,
                                "03,0000,000A"
                        );

                        String response = analyzeResponse(
                                Inverters.getLastResponse(
                                        address,
                                        Commands.MODBUS),
                                ConnectionControl.ExpectedValue.NUMBER
                        );

                        if (response == null || response.isBlank()) response = "0,0,0,0,0";
                        params.updateFromResponse(response);

                    } catch (Exception e) {
                        System.err.println("Ошибка опроса: " + e.getMessage());
                    }
                },
                0,
                pollingPeriod,
                TimeUnit.MILLISECONDS
        );

        futures.put(address, future);

        if (timeoutMs < pollingPeriod + 200) return;
        executor.schedule(() -> stop(address),
                timeoutMs - pollingPeriod, TimeUnit.MILLISECONDS);
    }

    public static void stop(Address address) {
        if (isPolled(address)) {
            ScheduledFuture<?> future = futures.remove(address);
            if (future != null) {
                future.cancel(true);
            }

            ScheduledExecutorService executor = executors.remove(address);
            if (executor != null) {
                executor.shutdownNow();
            }
            System.out.println("Опрос остановлен: " + address);
        }
    }

    private static boolean isPolled(Address address) {
        return futures.containsKey(address);
    }


}
