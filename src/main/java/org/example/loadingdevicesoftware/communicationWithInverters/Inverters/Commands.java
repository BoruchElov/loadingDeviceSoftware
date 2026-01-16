package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.cMAC;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * Класс типа enum для читаемой и структурированной отправки команд инверторам.
 * <p>
 * TODO сделать этот класс default и настроить доступ только через класс Inverters
 */

public enum Commands {

    BLINK_LED_START, BLINK_LED_STOP, SET_RESISTANCE_CHECK, START_RESISTANCE_CHECK, SET_SCENARO_1, START_SCENARO_1, SET_SCENARO_2,
    START_SCENARO_2, SET_SCENARO_3, START_SCENARO_3, CHECK_SWITCH_POS, FAULT, MODBUS, MODBUS_WRITE, FORCED_STOP, SC_RES,
    SCENARIO_RESULTS, BUTTON_LOCK, BUTTON_UNLOCK;

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Константа для задания времени ожидания (в секундах) ответа от модулей
     */
    private static final long delayTime = 4;

    /**
     * Константа для задания количества попыток ожидания ответа.
     */
    private static final int numberOfRetries = 3;

    /**
     * Метод для отправки выбранной команды. Формирует пакет для отправки.
     *
     * @param MAC       MAC-адрес отправителя
     * @param address   адрес получателя
     * @param command   команда для отправки
     * @param arguments параметры команды
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static byte[] callFunction(cMAC MAC, Address address, Commands command, String arguments) throws ExecutionException, InterruptedException {
        ExecutionException lastException = null;
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                System.out.println(
                        "Попытка " + attempt + "/" + numberOfRetries +
                                " отправки команды " + command
                );

                respondToFunction(MAC, address, command, arguments);
                return waitForAnswer(address, command);

            } catch (ExecutionException e) {
                lastException = e;
                System.out.println(
                        "Попытка " + attempt + " завершилась неудачно: " +
                                e.getCause().getMessage()
                );
            }
        }

        throw new ExecutionException(
                "Не удалось получить ответ после " + numberOfRetries + " попыток" + lastException.getMessage(),
                lastException
        );
    }

    static void respondToFunction(cMAC MAC, Address address, Commands command, String arguments) {
        String textCommand = command.toString() + "(" + arguments + ")";
        byte[] fullCommandInBytes = textCommand.getBytes(StandardCharsets.UTF_8);
        byte[] commandForInverter = new byte[fullCommandInBytes.length + 1];
        commandForInverter[0] = 0x02;
        System.arraycopy(fullCommandInBytes, 0, commandForInverter, 1, fullCommandInBytes.length);
        try {
            MAC.sendPacket(address, commandForInverter);
        } catch (Exception e) {
            System.out.println("Ошибка! Невозможно отправить пакет: проверьте соединение, адрес или сообщение.");
        }
    }


    /**
     * Утилитарный метод для остановки потока приёма-передачи сообщений на время ожидания ответа на последнее
     * отправоенное сообщение.
     *
     * @param address адрес получателя
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static byte[] waitForAnswer(Address address, Commands command) throws ExecutionException, InterruptedException {
        CompletableFuture<ByteBuffer> future = new CompletableFuture<>();
        String code = address.toStringInHexFormat() + "|" + command.toString();
        Inverters.addResponse(code, future);
        System.out.println("Отправлено сообщение с кодом: " + code);
        scheduler.schedule(() -> {
            if (!future.isDone()) {
                System.out.println("(Inverters) Ответ не получен за 1с от устройства: " + address.toStringInHexFormat());
                future.completeExceptionally(new TimeoutException("Ответ от устройства не получен за 1с"));
                Inverters.removeResponse(code);
            }
        }, delayTime, TimeUnit.SECONDS);
        ByteBuffer result = future.get().flip();
        byte[] responseBytes = new byte[result.remaining()];
        responseBytes = result.get(responseBytes).array();
        return responseBytes;
    }

    /**
     * Метод для принудительной остановки прерывателя потоков.
     */
    public static void closeScheduler() {
        scheduler.shutdownNow();
    }




}