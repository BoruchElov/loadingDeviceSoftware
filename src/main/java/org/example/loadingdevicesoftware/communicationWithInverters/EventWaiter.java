package org.example.loadingdevicesoftware.communicationWithInverters;

import lombok.Getter;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class EventWaiter {
    /**
     * Статический объект обработчика ожидания ответа. Позволяет обратиться к единственному варианту данного
     * класса.
     */
    @Getter
    private static final EventWaiter instance = new EventWaiter();

    private EventWaiter() {
    }

    private final List<PendingEvent> pendingEvents = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final ConcurrentHashMap<Address, byte[]> responsesStorage = new ConcurrentHashMap<>();

    /**
     * Метод для регистрации ожидания сообщения от заданного модуля
     * @param address адрес модуля
     * @param timeout время ожидания в секундах
     * @return
     */
    public synchronized CompletableFuture<ByteBuffer> waitForEvent(
            Address address,
            Duration timeout
    ) {
        CompletableFuture<ByteBuffer> future = new CompletableFuture<>();

        PendingEvent pending = new PendingEvent(address, future);
        pendingEvents.add(pending);

        System.out.println("[EventWaiter] → Зарегистрировано ожидание события от " + address +
                ", timeout = " + timeout.getSeconds() + " секунд");

        // Таймаут
        scheduler.schedule(() -> {
            synchronized (EventWaiter.this) {
                if (!future.isDone()) {
                    System.out.println("[EventWaiter] ⚠ Таймаут события для " + address);
                    future.completeExceptionally(
                            new TimeoutException("Событие не получено за " + timeout.getSeconds() + " секунд")
                    );
                    pendingEvents.remove(pending);
                }
            }
        }, timeout.getSeconds(), TimeUnit.SECONDS);

        return future;
    }

    /**
     * Метод для "прослушивания" входящих сообщений от модулей.
     * Вызывается из Inverters.handlePacket()
     */
    public synchronized void incoming(Address source, ByteBuffer data) {
        System.out.println("[EventWaiter] ← Входящее сообщение от " + source.toStringInHexFormat() +
                ", pending = " + pendingEvents.size());
        Iterator<PendingEvent> it = pendingEvents.iterator();

        while (it.hasNext()) {
            PendingEvent p = it.next();
            //saveResponse(source, info.array());

            System.out.println("[EventWaiter]   • Проверка PendingEvent: ожидаем от " + p.address());

            if (!p.address().equals(source)) {
                System.out.println("[EventWaiter]     → адрес не совпадает");
                continue;
            }

            System.out.println("[EventWaiter]     ✔ СОБЫТИЕ ПОДХОДИТ — завершаем future");
            saveResponse(source, ConnectionControl.extractBytes(data));
            p.future().complete(data);
            it.remove();
        }
    }

    public static void shutdown() {
        instance.scheduler.shutdownNow();
    }

    public static byte[] getResponse(Address address) {
        return responsesStorage.get(address);
    }

    private void saveResponse(Address address, byte[] response) {
        responsesStorage.put(address, response);
    }

    private record PendingEvent(
            Address address,
            CompletableFuture<ByteBuffer> future
    ) {
    }
}

