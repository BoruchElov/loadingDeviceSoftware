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
    private static final ConcurrentHashMap<String, CompletableFuture<byte[]>> pendingResponses = new ConcurrentHashMap<>();

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

    public static CompletableFuture<byte[]> sendCommandToInverterAsync(Address inverterAddress, Commands command, String arguments) {
        return Commands.callFunctionAsync(tabletAddress, inverterAddress, command, arguments);
    }

    public static void respondToInverter(Address inverterAddress, Commands command, String arguments) {
        Commands.respondToFunction(tabletAddress, inverterAddress, command, arguments);
    }

    public static void saveLastResponse(Address inverterAddress, Commands command, byte[] bytes) {
        responses.put(Commands.formCode(inverterAddress,command), bytes);
    }

    public static byte[] getLastResponse(Address inverterAddress, Commands command) {
        return responses.get(inverterAddress.getValue() + "|" + command.name());
    }

    public static void removeResponse(String code) {
        pendingResponses.remove(code);
    }

    public static void addResponse(String code, CompletableFuture<byte[]> response) {
        pendingResponses.put(code, response);
    }

    @Override
    public void handlePacket(Address AddressSource, byte[] Buff) {
        String command = new String(Buff, StandardCharsets.UTF_8);
        String code = AddressSource.toStringInHexFormat() + "|" + command.substring(1, command.indexOf('('));
        System.out.println("Получено сообщение с кодом: " + code);
        CompletableFuture<byte[]> future = pendingResponses.remove(code);
        if (future != null) {
            future.complete(Buff);                            // Разблокируем ожидающий поток
        } else {
            System.out.println("(Inverters) Ответ от " + AddressSource.toStringInHexFormat() + " не ожидался или уже истёк");
        }
        //Подключение сервиса для ожидания сообщения
        EventWaiter.getInstance().incoming(AddressSource, Buff);
    }
}
