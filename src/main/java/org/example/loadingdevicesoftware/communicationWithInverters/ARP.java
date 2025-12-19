package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.pagesControllers.StatusService;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ARP implements PacketHandler {
    private cMAC MAC;
    
    public ARP(cMAC mac) {
        this.MAC = mac;
    }

    @Override
    public void handlePacket(Address AddressSource, ByteBuffer Buff) {
    	ByteBuffer bufferTX;
        // Обработка ARP пакета
    	System.out.println("(ARP) Получен пакет");
        Buff.get();
    	byte TypeMsg = Buff.get();
    	switch(TypeMsg) {
    	case 1:					// Запрос на MAC адрес сервера
            ConnectionControl.addAddress(AddressSource);
            System.out.println("(ARP) Запрос на MAC адрес от: " + AddressSource.toStringInHexFormat());
            bufferTX = ByteBuffer.allocate(1+1+4);
    		Buff.rewind();
    		bufferTX.put(Buff);
    		bufferTX.putInt(MAC.getMAC().getValue());		// Возвращаемый адрес
    		MAC.sendPacket(AddressSource, bufferTX.array());
            System.out.println("(ARP) Отправлен ответ устройству: " + AddressSource.toStringInHexFormat());
            break;
    	case 2:
            System.out.println("(ARP) Запрос на PING от: " + AddressSource.toStringInHexFormat());
            StatusService.getInstance().onStatusMessage(AddressSource.toStringInHexFormat());
    		bufferTX = ByteBuffer.allocate(1+1);
    		Buff.rewind();
    		bufferTX.put(Buff);
    		MAC.sendPacket(AddressSource, bufferTX.array());
            System.out.println("(ARP) Отправлен ответ устройству: " + AddressSource.toStringInHexFormat());
            break;
    	default:
    		System.out.println("(ARP) Пакет не распознан");
    	}
    }
}
