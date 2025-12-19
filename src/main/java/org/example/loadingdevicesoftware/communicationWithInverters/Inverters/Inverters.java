package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import org.example.loadingdevicesoftware.communicationWithInverters.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Класс, реализующий логику работы с инверторами.
 */
public class Inverters implements PacketHandler {

    private static cMAC tabletAddress;
    private static final ConcurrentHashMap<String, CompletableFuture<ByteBuffer>> pendingResponses = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, byte[]> responses = new ConcurrentHashMap<>();

    /**
     * Класс-конструктор.
     * <p>
     *     TODO Реализовать возможность добавления адреса в качестве константы.
     * @param tabletAddress адрес планшета
     */
    public Inverters(cMAC tabletAddress) {
        Inverters.tabletAddress = tabletAddress;
    }

    /**
     * Метод для отправки управляющей команды на инвертор.
     * <p>
     * TODO реализовать возможность нормальной подгрузки адресов инверторов из файла
     * @param command вид управляющей команды
     * @param arguments аргументы команды
     */
    public static void sendCommandToInverter(Address inverterAddress, Commands command, String arguments) throws ExecutionException, InterruptedException {
        saveLastResponse(inverterAddress, command, Commands.callFunction(tabletAddress, inverterAddress, command, arguments));
    }

    public static void respondToInverter(Address inverterAddress, Commands command, String arguments) {
        Commands.respondToFunction(tabletAddress, inverterAddress, command, arguments);
    }

    public static void saveLastResponse(Address inverterAddress, Commands command, byte[] bytes) {
        responses.put(inverterAddress.getValue() + "|" + command.name(), bytes);
    }

    public static byte[] getLastResponse(Address inverterAddress, Commands command) {
        return responses.get(inverterAddress.getValue() + "|" + command.name());
    }

    public static void removeResponse(String code) {
        pendingResponses.remove(code);
    }

    public static void addResponse(String code, CompletableFuture<ByteBuffer> response) {
        pendingResponses.put(code, response);
    }

    @Override
    public void handlePacket(Address AddressSource, ByteBuffer Buff) {
        String command = new String(ConnectionControl.extractBytes(Buff), StandardCharsets.UTF_8);
        String code = AddressSource.toStringInHexFormat() + "|" + command.substring(1, command.indexOf('('));
        System.out.println("Получено сообщение с кодом: " + code);
        CompletableFuture<ByteBuffer> future = pendingResponses.remove(code);
        if (future != null) {
            future.complete(Buff);                            // Разблокируем ожидающий поток
        } else {
            System.out.println("(Inverters) Ответ от " + AddressSource.toStringInHexFormat() + " не ожидался или уже истёк");
        }
        //Подключение сервиса для ожидания сообщения
        EventWaiter.getInstance().incoming(AddressSource, Buff);
    }

    private String getBytes(ByteBuffer packet) {
        ByteBuffer copy = packet.asReadOnlyBuffer(); // независимая позиция
        copy.rewind();                                 // позиция -> 0, но только в копии
        byte[] data = new byte[copy.remaining()];
        copy.get(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : data) sb.append(String.format("%02X ", b));
        return sb.toString();
    }
}
