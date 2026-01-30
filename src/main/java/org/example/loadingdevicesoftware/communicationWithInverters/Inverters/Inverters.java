package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import org.example.loadingdevicesoftware.communicationWithInverters.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Класс, реализующий логику работы с инверторами.
 */
public class Inverters implements PacketHandler {

    private static cMAC tabletAddress;
    private static final ConcurrentHashMap<ResponseCard, CompletableFuture<byte[]>> pendingResponses = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<ResponseCard, byte[]> responses = new ConcurrentHashMap<>();

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
        responses.put(new ResponseCard(inverterAddress, command), bytes);
    }

    public static byte[] getLastResponse(Address inverterAddress, Messages command) {
        byte[] response = new byte[]{0};
        if (responses.containsKey(new ResponseCard(inverterAddress, command))) {
            response = responses.get(new ResponseCard(inverterAddress, command));
        } else {
            System.err.println("Отсутствует ответ на запрашиваемую команду!");
        }
        return response;
    }

    public static void removeResponse(Address sender, Messages expectedResponse) {
        ResponseCard responseCard = new ResponseCard(sender, expectedResponse);
        if (!pendingResponses.containsKey(responseCard))
            pendingResponses.remove(responseCard);
    }

    public static void addResponse(Address sender, Messages expectedResponse, CompletableFuture<byte[]> response) {
        ResponseCard responseCard = new ResponseCard(sender, expectedResponse);
        if (!pendingResponses.containsKey(responseCard))
            pendingResponses.put(new ResponseCard(sender, expectedResponse), response);
    }

    @Override
    public void handlePacket(Address AddressSource, byte[] Buff) {
        String command = new String(Buff, StandardCharsets.UTF_8);
        command = command.substring(1, command.indexOf('('));
        Messages message = null;
        for (Messages m : Messages.values()) {
            if (command.equals(m.name())) {
                message = m;
                break;
            }
        }
        CompletableFuture<byte[]> future = pendingResponses.remove(new ResponseCard(AddressSource, message));
        if (future != null) {
            future.complete(Buff);
        }
        EventWaiter.getInstance().incoming(AddressSource, Buff);
    }

    /**
     * Карточка для регистрации ответа
     *
     * @param address
     * @param message
     */
    public record ResponseCard(
            Address address,
            Messages message) {

    }

    /**
     * Метод для поиска всех карточек с заданным адресом
     * @param address адрес для поиска
     * @return список адресов
     */
    public static ArrayList<ResponseCard> findCardsByAddress(Address address) {
        ArrayList<ResponseCard> responseCards = new ArrayList<>();
        for (ResponseCard responseCard : pendingResponses.keySet()) {
            if (responseCard.address.equals(address)) {
                responseCards.add(responseCard);
            }
        }
        return responseCards;
    }

    /**
     * Метод для поиска карточки по заданному адресу и сообщению
     * @param address адрес для поиска
     * @param message сообщение для поиска
     * @return карточку, соответствующую условиям поиска
     */
    public static ResponseCard findCard(Address address, Messages message) {
        ResponseCard responseCard = null;
        for (ResponseCard card : pendingResponses.keySet()) {
            if (card.address.equals(address) && card.message.equals(message)) {
                responseCard = card;
                break;
            }
        }
        if (responseCard == null) System.err.println("Не найдена карточка с искомыми параметрами!");
        return responseCard;
    }

}
