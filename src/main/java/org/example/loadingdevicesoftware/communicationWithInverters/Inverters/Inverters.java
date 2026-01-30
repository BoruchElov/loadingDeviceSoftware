package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import org.example.loadingdevicesoftware.communicationWithInverters.*;

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
     *
     * @param tabletAddress адрес планшета
     */
    public Inverters(cMAC tabletAddress) {
        Inverters.tabletAddress = tabletAddress;
    }

    /**
     * Метод для синхронной отправки управляющей команды на инвертор.
     * <p>
     *
     * @param command   вид управляющей команды
     * @param arguments аргументы команды
     */
    public static void sendCommandToInverterSync(Address inverterAddress, Messages command, String arguments) throws ExecutionException, InterruptedException {
        saveLastResponse(inverterAddress, command, Messages.callFunction(tabletAddress, inverterAddress, command, arguments));
    }

    /**
     * Метод для асинхронной отправки управляющей команды на инвертор.
     * <p>
     *
     * @param command   вид управляющей команды
     * @param arguments аргументы команды
     */
    public static CompletableFuture<byte[]> sendCommandToInverterAsync(Address inverterAddress, Messages command, String arguments) {
        return Messages.callFunctionAsync(tabletAddress, inverterAddress, command, arguments);
    }

    public static void respondToInverter(Address inverterAddress, Messages command, String arguments) {
        Messages.respondToFunction(tabletAddress, inverterAddress, command, arguments);
    }

    public static void saveLastResponse(Address inverterAddress, Messages command, byte[] bytes) {
        responses.put(Messages.formCode(inverterAddress, command), bytes);
    }

    public static byte[] getLastResponse(Address inverterAddress, Messages command) {
        return responses.get(Messages.formCode(inverterAddress, command));
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
        String cmdName = command.substring(1, command.indexOf('('));
        String code = AddressSource.toStringInHexFormat() + "|" + cmdName;
        CompletableFuture<byte[]> future = pendingResponses.remove(code);
        if (future != null) {
            future.complete(Buff);
        }
        EventWaiter.getInstance().incoming(AddressSource, Buff);
    }

    /**
     * Карточка для регистрации ответа
     * @param address
     * @param command
     */
    public record responseCard(
            Address address,
            Messages command) {

    }

}
