package org.example.loadingdevicesoftware.communicationWithInverters;

import lombok.Getter;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.AddressesStorage;
import org.example.loadingdevicesoftware.pagesControllers.StatusService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ConnectionControl {

    private static cMAC MAC;
    private static ARP arp;
    private static Inverters invs;

    private static final int BAUDE_RATE = 9600;
    private static final int NUM_BITS = 8;
    private static final String COM = "COM5";

    //Порядок адресов: А1, В1, С1, А2, В2, С2
    /**
     * Структура для хранения адресов инверторов, подключенных к сети на данный момент.
     */
    private static final AtomicReference<Address>[] invertersAddresses = new AtomicReference[]{new AtomicReference<>(new Address(0)),
            new AtomicReference<>(new Address(0)), new AtomicReference<>(new Address(0)),
            new AtomicReference<>(new Address(0)), new AtomicReference<>(new Address(0)),
            new AtomicReference<>(new Address(0))};

    public static Address getInvertersAddress(int index) {
        return invertersAddresses[index].get();
    }

    private ConnectionControl() {}

    public static void openConnection() {
        MAC = new cMAC(COM, BAUDE_RATE, NUM_BITS);
        arp = new ARP(MAC);
        invs = new Inverters(MAC);
        MAC.registerHandler(0x01, arp);
        MAC.registerHandler(0x02, invs);

        ArrayList<String> values = new ArrayList<>();
    }

    public static void closeConnection() {
        if (MAC != null) {
            MAC.stop();
            MAC.close();
        }
        Commands.closeScheduler();
        StatusService.getInstance().stop();
    }

    public static void clearInvertersAddresses() {
        for (int i = 0; i < invertersAddresses.length; i++) {
            invertersAddresses[i].set(new Address(0));
        }
    }

    public static void deleteAddress(int address) {

    }

    public static byte[] extractBytes(ByteBuffer buffer) {
        if (buffer == null) {
            return new byte[0];
        }
        ByteBuffer copy = buffer.asReadOnlyBuffer();
        copy.rewind();                       // читаем с начала
        byte[] bytes = new byte[copy.remaining()];
        copy.get(bytes);
        return bytes;
    }

    public static void fillAddress(List<String>Addresses) {
        for (int i = 0; i < Addresses.size(); i++) {
            if (Addresses.get(i).equals("00:00:00:00")) {
                continue;
            }
            invertersAddresses[i] = new AtomicReference<>(new Address(toIntFromHexString(Addresses.get(i))));
        }
    }

    public static List<String> getSavedAddresses() {
        List<String> addresses = new ArrayList<>();
        for (AtomicReference<Address> invertersAddress : invertersAddresses) {
            addresses.add(invertersAddress.get().toStringInHexFormat());
        }
        return addresses;
    }

    public static boolean isAddressKnown(Address address) {
        for (AtomicReference<Address> addr : invertersAddresses) {
            if (addr.get().equals(address)) {
                return true;
            }
        }
        return false;
    }

    public static int toIntFromHexString(String hexString) {
        hexString = hexString.replace(":","");
        return Integer.parseInt(hexString, 16);
    }


    public static void addAddress(Address address) {
        for (AtomicReference<Address> invertersAddress : invertersAddresses) {
            if (invertersAddress.get().getValue() == 0) {
                invertersAddress.set(address);
                break;
            }
        }
    }

    public enum ExpectedValue {
        NUMBER, PHRASE
    }

    public static String analyzeResponse(byte[] input, ExpectedValue expectedValue) {
        String message = new String(input, 8, input.length - 8, StandardCharsets.UTF_8);
        return switch (expectedValue) {
            case NUMBER -> message.substring(message.indexOf('(') + 1, message.indexOf(')'));
            case PHRASE -> message;
        };
    }
    
}
