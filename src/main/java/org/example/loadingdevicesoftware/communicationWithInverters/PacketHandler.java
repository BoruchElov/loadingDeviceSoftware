package org.example.loadingdevicesoftware.communicationWithInverters;

import java.nio.ByteBuffer;

public interface PacketHandler {
	void handlePacket(Address AddressSource, byte[] Buff);
}

