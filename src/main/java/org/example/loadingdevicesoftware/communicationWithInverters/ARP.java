package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.pagesControllers.StatusService;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ARP implements PacketHandler {
    private cMAC MAC;
    
    public ARP(cMAC mac) {
        this.MAC = mac;
    }

    @Override
    public void handlePacket(Address AddressSource, byte[] Buff) {

        System.out.println("(ARP) Получен пакет");
        System.out.printf(Arrays.toString(Buff));
        byte[] message = Arrays.copyOf(Buff, Buff.length);
        byte typeMsg = message[message.length - 1];
        byte[] payload;
    	switch(typeMsg) {
    	case 1:					// Запрос на MAC адрес сервера
            ConnectionControl.addAddress(AddressSource);
            System.out.println("(ARP) Запрос на MAC адрес от: " + AddressSource.toStringInHexFormat());
            payload = new byte[6];
            payload[0] = 1;
            payload[1] = 1;
            System.arraycopy(MAC.getMAC().getBytes(),0,payload,2,4);
            MAC.sendPacket(AddressSource, payload);
            System.out.println("(ARP) Отправлен ответ устройству: " + AddressSource.toStringInHexFormat());
            break;
    	case 2:
            System.out.println("(ARP) Запрос на PING от: " + AddressSource.toStringInHexFormat());
            StatusService.getInstance().onStatusMessage(AddressSource.toStringInHexFormat());
            payload = new byte[]{1,2};
            MAC.sendPacket(AddressSource, payload);
            System.out.println("(ARP) Отправлен ответ устройству: " + AddressSource.toStringInHexFormat());
            break;
    	default:
    		System.out.println("(ARP) Пакет не распознан");
    	}
    }
}
