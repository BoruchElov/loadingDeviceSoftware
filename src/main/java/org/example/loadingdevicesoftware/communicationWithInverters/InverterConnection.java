package org.example.loadingdevicesoftware.communicationWithInverters;

import com.fazecast.jSerialComm.*;

public class InverterConnection {

    private final String COMport = "COM4";
    private final int baudRate = 115200;
    private final int dataBits = 8;
    private final int stopBits = 1;
    private final int parity = SerialPort.NO_PARITY;
    private final int sendingTimeout = 10000; //милисекунды
    private final int receivingTimeout = 10000; //милисекунды

    public SerialPort connectionPort;

    public InverterConnection() {
        connectionPort = getActualPort();
        connectionPort.openPort();

        connectionPort.setBaudRate(baudRate);
        connectionPort.setNumDataBits(dataBits);
        connectionPort.setNumStopBits(stopBits);
        connectionPort.setParity(parity);

        connectionPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, sendingTimeout, receivingTimeout);
    }

    public void sendMessage(CommandsForInverters.messagesForInverter message) {
        String command = CommandsForInverters.constructMessageToInverter("2001",
                message, new String[]{""});
        byte[] buffer = command.getBytes();
        connectionPort.writeBytes(buffer, buffer.length);
        System.out.println("Команда отправлена: " + command);
    }

    public void disconnect() {
        connectionPort.closePort();
    }

    private SerialPort getActualPort() {
        SerialPort actualPort = null;
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (SerialPort serialPort : serialPorts) {
            if (serialPort.getSystemPortName().equals(COMport)) {
                actualPort = serialPort;
            }
        }
        return actualPort;
    }


}
