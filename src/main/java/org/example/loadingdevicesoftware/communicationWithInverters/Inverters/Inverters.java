package org.example.loadingdevicesoftware.communicationWithInverters.Inverters;

import org.example.loadingdevicesoftware.communicationWithInverters.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Класс, реализующий логику работы с инверторами.
 */
public class Inverters implements PacketHandler {

    private static cMAC tabletAddress;
    private static final ConcurrentHashMap<ResponseCard, CompletableFuture<byte[]>> pendingResponses = new ConcurrentHashMap<>();
    private final BlockingQueue<RawCard> inputQueue = new LinkedBlockingQueue<>();
    private static final ConcurrentHashMap<ResponseCard, byte[]> responses = new ConcurrentHashMap<>();

    private static Thread analyzerThread;
    private static boolean running = true;

    /**
     * Класс-конструктор.
     * <p>
     *     TODO Реализовать возможность добавления адреса в качестве константы.
     *
     * @param tabletAddress адрес планшета
     */
    public Inverters(cMAC tabletAddress) {
        Inverters.tabletAddress = tabletAddress;

        analyzerThread = new Thread(() -> {
            while (running) {
                try {
                    RawCard card = inputQueue.take();
                    Address address = card.address;
                    byte[] payload = card.message;
                    String command = new String(payload, StandardCharsets.UTF_8);
                    command = command.substring(1, command.indexOf('('));
                    Messages message = null;
                    for (Messages m : Messages.values()) {
                        if (command.equals(m.name())) {
                            message = m;
                            break;
                        }
                    }
                    CompletableFuture<byte[]> future = pendingResponses.remove(new ResponseCard(address, message));
                    if (future != null) {
                        future.complete(payload);
                    }
                    EventWaiter.getInstance().incoming(address, payload);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        analyzerThread.setName("Thread for analysis of inverters responses");
        analyzerThread.setDaemon(true);
        analyzerThread.start();
    }

    public void stop() {
        running = false;
        analyzerThread.interrupt();
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
        if (!pendingResponses.containsKey(responseCard)) pendingResponses.remove(responseCard);
    }

    public static void addResponse(Address sender, Messages expectedResponse, CompletableFuture<byte[]> response) {
        ResponseCard responseCard = new ResponseCard(sender, expectedResponse);
        if (!pendingResponses.containsKey(responseCard))
            pendingResponses.put(new ResponseCard(sender, expectedResponse), response);
    }

    @Override
    public void handlePacket(Address AddressSource, byte[] Buff) {
        boolean result = inputQueue.offer(new RawCard(AddressSource, Buff));
        if (!result) {
            System.out.println("(Inverters) Ошибка при чтении входящего сообщения!");
        }
    }

    /**
     * Карточка для хранения сообщения в очереди
     *
     * @param address
     * @param message
     */
    private record RawCard(Address address, byte[] message) {
    }

    /**
     * Карточка для регистрации ответа
     *
     * @param address
     * @param message
     */
    public record ResponseCard(Address address, Messages message) {

    }

    /**
     * Метод для поиска всех карточек с заданным адресом
     *
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
     *
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
