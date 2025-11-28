package org.example.loadingdevicesoftware.pagesControllers;

import javafx.application.Platform;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

public final class StatusService {

    private static StatusService INSTANCE;

    public static StatusService getInstance() {
        if (INSTANCE == null) INSTANCE = new StatusService();
        return INSTANCE;
    }

    private final StatusStore store = new StatusStore();
    private final ScheduledExecutorService scheduler;
    private final List<Consumer<Map<String, StatusStore.OnlineStatus>>> listeners =
            new CopyOnWriteArrayList<>();

    private static final long TIMEOUT_NANOS = Duration.ofSeconds(2).toNanos();
    private static final long SWEEP_PERIOD_MS = 200;
    private static final long UI_PERIOD_MS = 500;

    private StatusService() {
        scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "StatusServiceThread");
            t.setDaemon(true);
            return t;
        });
        start();
    }

    // Вызывается при получении статуса от устройства
    public void onStatusMessage(String mac) {
        store.seenOnline(mac);
    }

    public boolean isAddressOnline(String mac) {
        StatusStore.ModuleState st = store.raw().get(mac);
        return st.status == StatusStore.OnlineStatus.ONLINE;
    }

    // Добавить контроллер в список слушателей UI
    public void addListener(Consumer<Map<String, StatusStore.OnlineStatus>> listener) {
        listeners.add(listener);
    }

    // Удалить слушателя (например, при закрытии окна)
    public void removeListener(Consumer<Map<String, StatusStore.OnlineStatus>> listener) {
        listeners.remove(listener);
    }

    private void start() {
        scheduler.scheduleAtFixedRate(this::sweepForTimeouts, 0, SWEEP_PERIOD_MS, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(this::notifyListeners, 0, UI_PERIOD_MS, TimeUnit.MILLISECONDS);
    }

    private void sweepForTimeouts() {
        final long now = System.nanoTime();

        for (String address : store.raw().keySet()) {
            StatusStore.ModuleState st = store.raw().get(address);
            if (now - st.lastSeenNanos > TIMEOUT_NANOS && st.status != StatusStore.OnlineStatus.OFFLINE) {
                st.status = StatusStore.OnlineStatus.OFFLINE;
            }
        }

    }

    private void notifyListeners() {
        Map<String, StatusStore.OnlineStatus> snapshot = store.snapshotStatuses();
        Platform.runLater(() -> {
            for (var listener : listeners) {
                listener.accept(snapshot);
            }
        });
    }

    public void stop() {
        scheduler.shutdownNow();
    }
}
