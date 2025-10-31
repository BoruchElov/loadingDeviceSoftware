package org.example.loadingdevicesoftware.pagesControllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class StatusStore {

    private final ConcurrentHashMap<String, ModuleState> map = new ConcurrentHashMap<>();

    public void seenOnline(String mac) {
        final long now = System.nanoTime();
        map.compute(mac, (k, v) -> {
            if (v == null) v = new ModuleState();
            v.lastSeenNanos = now;
            v.status = OnlineStatus.ONLINE; // быстро фиксируем состояние
            return v;
        });
    }

    public Map<String, OnlineStatus> snapshotStatuses() {
        // копия для безопасного чтения UI-потоком
        HashMap<String, OnlineStatus> snap = new HashMap<>();
        map.forEach((mac, st) -> snap.put(mac, st.status));
        return snap;
    }

    public ConcurrentHashMap<String, ModuleState> raw() { return map; }

    public static final class ModuleState {
        volatile long lastSeenNanos;
        volatile OnlineStatus status = OnlineStatus.OFFLINE;
    }

    public enum OnlineStatus { ONLINE, OFFLINE }
}
