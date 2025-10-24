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
    START_SCENARO_2, SET_SCENARO_3, START_SCENARO_3, CHECK_SWITCH_POS, FAULT, MODBUS, MODBUS_WRITE, FORCED_STOP;

    Commands() {}

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
        return waitForAnswer(address);
    }


    /**
     * Утилитарный метод для остановки потока приёма-передачи сообщений на время ожидания ответа на последнее
     * отправоенное сообщение.
     *
     * @param address адрес получателя
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static byte[] waitForAnswer(Address address) throws ExecutionException, InterruptedException {
        CompletableFuture<ByteBuffer> future = new CompletableFuture<>();
        Inverters.addResponse(address, future);
        scheduler.schedule(() -> {
            if (!future.isDone()) {
                System.out.println("(Inverters) Ответ не получен за 1с от устройства: " + address.toString());

                future.completeExceptionally(new TimeoutException("Ответ от устройства не получен за 1с"));
                Inverters.removeResponse(address);
            }
        }, 1, TimeUnit.SECONDS);
        ByteBuffer result = future.get().flip();
        byte[] responseBytes = new byte[result.remaining()];
        responseBytes = result.get(responseBytes).array();
        //System.out.println("Ответ, полученный от инвертора: " + responseAnalyzer(responseBytes));
        //System.out.println("Длина ответа: " + responseBytes.length + " байт");
        return responseBytes;
    }

    /**
     * Метод для принудительной остановки прерывателя потоков.
     */
    public static void closeScheduler() {
        scheduler.shutdownNow();
    }

    /**
     * Метод для интерпретации полученного ответа в читаемом виде.
     * <p>
     * TODO добавить возможность перевода MAC-адреса в понятное имя инвертора.
     *
     * @param response ответ в виде массива байт
     * @return интерпретацию ответа в виде строки
     */

    public static String responseAnalyzer(byte[] response) {
        StringBuilder senderMAC = new StringBuilder();
        StringBuilder receiverMAC = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            senderMAC.append(String.format("%02X", response[i], StandardCharsets.UTF_8));
            senderMAC.append(i == 3 ? "" : ":");
            receiverMAC.append(String.format("%02X", response[i + 4], StandardCharsets.UTF_8));
            receiverMAC.append((i + 4) == 7 ? "" : ":");
        }
        String message = new String(response, 8, response.length - 8, StandardCharsets.UTF_8);
        return senderMAC + " | " + receiverMAC + " | " + message;
    }



}