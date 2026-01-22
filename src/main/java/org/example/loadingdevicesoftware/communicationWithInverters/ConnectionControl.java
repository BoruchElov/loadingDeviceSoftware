package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;
import org.example.loadingdevicesoftware.pagesControllers.StatusService;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class ConnectionControl {

    private static cMAC MAC;
    private static ARP arp;
    private static Inverters invs;

    private static final int BAUDE_RATE = 9600;
    private static final int NUM_BITS = 8;
    private static final String COM = "COM5";

    //Порядок адресов: А1, В1, С1, А2, В2, С2
    /**
     * Защищённый список для хранения адресов всех инверторов, запрашивавших адрес планшета за время сессии.
     */
    private static final CopyOnWriteArrayList<Address> invertersAddresses = new CopyOnWriteArrayList<>();


    private ConnectionControl() {
    }

    public static void openConnection() {
        MAC = new cMAC(COM, BAUDE_RATE, NUM_BITS);
        arp = new ARP(MAC);
        invs = new Inverters(MAC);
        MAC.registerHandler(0x01, arp);
        MAC.registerHandler(0x02, invs);
    }

    public static void closeConnection() {
        if (MAC != null) {
            MAC.stop();
            MAC.close();
        }
        Commands.closeScheduler();
        StatusService.getInstance().stop();
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

    public static List<String> getSavedAddresses() {
        List<String> addresses = new ArrayList<>();
        for (Address invertersAddress : invertersAddresses) {
            addresses.add(invertersAddress.toStringInHexFormat());
        }
        return addresses;
    }

    public static int toIntFromHexString(String hexString) {
        hexString = hexString.replace(":", "");
        return Integer.parseInt(hexString, 16);
    }


    public static void addAddress(Address address) {
        for (Address invertersAddress : invertersAddresses) {
            if (invertersAddress.equals(address)) return;
        }
        invertersAddresses.add(address);
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
