package org.example.loadingdevicesoftware.communicationWithInverters;

public interface PacketHandler {
	void handlePacket(Address AddressSource, byte[] Buff);
}

