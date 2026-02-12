package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.pagesControllers.StatusService;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ARP implements PacketHandler {

    private cMAC MAC;

    private static boolean running = true;

    private static final BlockingQueue<ModulePing> pingQueue = new LinkedBlockingQueue<>();
    private static final CopyOnWriteArrayList<Address> availableAddresses = new CopyOnWriteArrayList<>();

    public record ModulePing(Address moduleAddress, long lastSeenTime) {}

    private static Thread pingThread;

    public void stop() {
        running = false;
        if (pingThread != null) {
            pingThread.interrupt();
        }
    }

    public ARP(cMAC mac) {
        this.MAC = mac;

        pingThread = new Thread(() -> {
            System.out.println("(ARP) Поток хранения ping запущен");
            while (running) {
                try {
                    ModulePing card = pingQueue.take();
                    Address moduleAddress = card.moduleAddress;
                    StatusService.getInstance().onStatusMessage(moduleAddress.toStringInHexFormat());
                    MAC.sendPacket(moduleAddress, new byte[]{1, 2});
                    System.out.println("(ARP) Отправлен ответ устройству: " + moduleAddress.toStringInHexFormat());

                } catch (InterruptedException e) {
                    System.out.println("Поток " + pingThread.getName() + " остановлен.");
                }
            }
        });
        pingThread.setName("Thread for pings");
        pingThread.setDaemon(true);
        pingThread.start();
    }

    @Override
    public void handlePacket(Address AddressSource, byte[] Buff) {
        System.out.println("(ARP) Получен пакет");
        //System.out.printf(Arrays.toString(Buff));
        byte[] message = Arrays.copyOf(Buff, Buff.length);
        byte typeMsg = message[message.length - 1];
    	switch(typeMsg) {
    	case 1:					// Запрос на MAC адрес сервера
            availableAddresses.add(AddressSource);
            System.out.println("(ARP) Запрос на MAC адрес от: " + AddressSource.toStringInHexFormat());
            byte[] payload = new byte[6];
            payload[0] = 1;
            payload[1] = 1;
            System.arraycopy(MAC.getMAC().getBytes(),0,payload,2,4);
            MAC.sendPacket(AddressSource, payload);
            System.out.println("(ARP) Отправлен ответ устройству: " + AddressSource.toStringInHexFormat());
            break;
    	case 2:
            System.out.println("(ARP) Запрос на PING от: " + AddressSource.toStringInHexFormat());
            boolean result = pingQueue.offer(new ModulePing(AddressSource, System.currentTimeMillis()));
            if (!result) {
                System.out.println("(ARP) Ошибка при сохранении информации о пинге!");
            }
            break;
    	default:
    		System.out.println("(ARP) Пакет не распознан");
    	}
    }

    public static CopyOnWriteArrayList<Address> getAvailableAddresses() {
        return availableAddresses;
    }
}
