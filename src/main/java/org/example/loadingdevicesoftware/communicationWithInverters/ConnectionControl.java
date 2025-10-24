package org.example.loadingdevicesoftware.communicationWithInverters;

import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Commands;
import org.example.loadingdevicesoftware.communicationWithInverters.Inverters.Inverters;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.AddressesStorage;

import java.io.IOException;
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
    private static final AtomicReference<Address>[] invertersAddresses = new AtomicReference[]{new AtomicReference<>(new Address(0)),
            new AtomicReference<>(new Address(0)), new AtomicReference<>(new Address(0)),
            new AtomicReference<>(new Address(0)), new AtomicReference<>(new Address(0)),
            new AtomicReference<>(new Address(0))};

    /*static {
        List<String> addresses;
        try {
            addresses = AddressesStorage.readAddresses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < addresses.size(); i++) {
            invertersAddresses[i] = new AtomicReference<>(new Address(toIntFromHexString(addresses.get(i))));
        }
    }*/

    private ConnectionControl() {}

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
    }

    public static void clearInvertersAddresses() {
        for (AtomicReference<Address> address : invertersAddresses) {
            address = new AtomicReference<>(new Address(0));
        }
    }

    public static void fillAddress(List<String>Addresses) {
        for (int i = 0; i < Addresses.size(); i++) {
            if (invertersAddresses[i].get().getValue() == 0) {
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
    
}
