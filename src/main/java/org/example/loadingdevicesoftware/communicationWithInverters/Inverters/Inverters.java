package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Address;
import org.example.loadingdevicesoftware.communicationWithInverters.PacketHandler;
import org.example.loadingdevicesoftware.communicationWithInverters.cMAC;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Класс, реализующий логику работы с инверторами.
 */
public class Inverters implements PacketHandler {

    private final cMAC tabletAddress;
    private static final ConcurrentHashMap<Address, CompletableFuture<ByteBuffer>> pendingResponses = new ConcurrentHashMap<>();
    private byte[] lastResponse;

    private final ConcurrentHashMap<String, byte[]> responses = new ConcurrentHashMap<>();

    /**
     * Класс-конструктор.
     * <p>
     *     TODO Реализовать возможность добавления адреса в качестве константы.
     * @param tabletAddress адрес планшета
     */
    public Inverters(cMAC tabletAddress) {
        this.tabletAddress = tabletAddress;
    }

    /**
     * Метод для отправки управляющей команды на инвертор.
     * <p>
     * TODO реализовать возможность нормальной подгрузки адресов инверторов из файла
     * @param command вид управляющей команды
     * @param arguments аргументы команды
     */
    public void sendCommandToInverter(Address inverterAddress, Commands command, String arguments) throws ExecutionException, InterruptedException {
        saveLastResponse(inverterAddress, command, Commands.callFunction(tabletAddress, inverterAddress, command, arguments));
    }

    public void saveLastResponse(Address inverterAddress, Commands command, byte[] bytes) {
        responses.put(inverterAddress.getValue() + ":" + command.name(), bytes);
    }

    public byte[] getLastResponse(Address inverterAddress, Commands command) {
        return responses.get(inverterAddress.getValue() + ":" + command.name());
    }

    public static void removeResponse(Address deviceAddress) {
        pendingResponses.remove(deviceAddress);
    }

    public static void addResponse(Address deviceAddress, CompletableFuture<ByteBuffer> response) {
        pendingResponses.put(deviceAddress, response);
    }

    public static ByteBuffer getLastResponse(Address deviceAddress) throws ExecutionException, InterruptedException {
        return pendingResponses.get(deviceAddress).get();
    }

    @Override
    public void handlePacket(Address AddressSource, ByteBuffer Buff) {
        CompletableFuture<ByteBuffer> future = pendingResponses.remove(AddressSource);
        if (future != null) {
            future.complete(Buff);                            // Разблокируем ожидающий поток
        } else {
            System.out.println("(Inverters) Ответ от " + AddressSource.toStringInHexFormat() + " не ожидался или уже истёк");
        }
    }

}
