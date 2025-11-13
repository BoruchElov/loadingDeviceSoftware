package org.example.loadingdevicesoftware.communicationWithInverters;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.example.loadingdevicesoftware.communicationWithInverters.serial.ComPortEventHandler;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class cMAC implements AutoCloseable, Runnable, SerialPortDataListener {


    public static final int myMACInt = 0x0712ABE1;
    private static Address myMAC = new Address(myMACInt);
    private SerialPort SP;
    private final Map<Integer, PacketHandler> upperLayerHandlers = new ConcurrentHashMap<>();
    private final BlockingQueue<byte[]> sendQueue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;
    private Thread workerThread;

    public cMAC(String COMport_name, int baudRate, int dataBits) {

        SP = SerialPort.getCommPort(COMport_name);
        SP.setBaudRate(baudRate);
        SP.setNumDataBits(dataBits);
        SP.setNumStopBits(SerialPort.ONE_STOP_BIT);
        SP.setParity(SerialPort.NO_PARITY);
        try {
            if (SP.openPort()) {
                System.out.println("(MAC) COM порт открыт");
            } else {
                System.out.println("(MAC) Не удалось открыть порт");
                return;
            }
            Thread.sleep(100);
            SP.flushIOBuffers();
            SP.addDataListener(this);
            SP.setFlowControl(SerialPort.FLOW_CONTROL_RTS_ENABLED | SerialPort.FLOW_CONTROL_CTS_ENABLED);
            workerThread = new Thread(this);
            workerThread.start();

        } catch (Exception e) {
            SP.closePort();
        }
    }

    // Регистрация обработчика для определённого типа пакета
    public void registerHandler(int type, PacketHandler handler) {
        upperLayerHandlers.put(type & 0xFF, handler); // & 0xFF — для безопасности
    }

    public Address getMAC() {
        return new Address(myMAC.getValue());
    }

    // Внешний метод — добавить пакет на отправку
    public void sendPacket(Address addrDes, byte[] packet) {

        ByteBuffer Packet = ByteBuffer.allocate(packet.length + 8);
        Packet.putInt(addrDes.getValue());                            // Адрес получателя
        Packet.putInt(this.getMAC().getValue());                    // Адрес отправителя
        Packet.put(packet);                                            // PayLoad
        //System.out.println("Запрос на отправку " + Packet.array().length + " байт.");
        boolean result = sendQueue.offer(Packet.array());// Отправить пакет без блокировки
        if (!result) {
            System.out.println("Ошибка при отправке пакета! Переполнена очередь на отправку");
        }
    }

    // Остановка потока
    public void stop() {
        running = false;
        if (workerThread != null) {
            workerThread.interrupt();
        }
    }

    @Override
    public void run() {
        System.out.println("(MAC) Поток отправки сообщений MAC запущен");

        while (running) {
            try {
                byte[] packet = sendQueue.take(); // ждет, если очередь пуста
                int written = SP.writeBytes(packet, packet.length);
                if (written != packet.length) {
                    System.err.println("(MAC) Ошибка при отправке пакета");
                    // Можно добавить повторную попытку
                } else {
                    //System.out.println("(MAC) Пакет отправлен: " + bytesToHex(packet));
                    //System.out.println("Отправлено: " + written + " из " + packet.length);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void handlePacket(byte[] Buff) {
        Integer PacketType = Byte.toUnsignedInt(Buff[8]);
        Address adrsrc = new Address(ByteBuffer.wrap(Buff, 4, 4).getInt());
        Address addressRCV = new Address(ByteBuffer.wrap(Buff, 0, 4).getInt());
        ByteBuffer PacketPayload = ByteBuffer.wrap(Buff, 8, Buff.length - 8).slice();
        if (Objects.equals(addressRCV.toStringInHexFormat(), myMAC.toStringInHexFormat())) {
            if (PacketType != 1) {
                return;
            }
            if (Buff[9]  != 1 && Buff[9] != 2) {
                return;
            }
        }
        try {
            PacketHandler handler = upperLayerHandlers.get(PacketType);
            handler.handlePacket(adrsrc, PacketPayload);
        } catch (Exception e) {
            System.out.println("(MAC) Нет обработчика для типа: " + PacketType);
        }
    }


    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02X ", b));
        return sb.toString();
    }

    @Override
    public void close() {
        stop();
        if (workerThread != null) {
            try {
                workerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (SP != null && SP.isOpen()) {
            SP.removeDataListener();
            SP.closePort();
            System.out.println("(MAC) COM-порт закрыт.");
        }
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {

        switch (ComPortEventHandler.fromEvent(event)) {
            case CTS, DSR, BREAK_INTERRUPT, PARITY_ERROR, RING_INDICATOR,
                 CARRIER_DETECT, PORT_DISCONNECTED, UNKNOWN, DATA_WRITTEN, DATA_RECEIVED -> {
                System.out.println(ComPortEventHandler.messageToString(event));
            }
            case DATA_AVAILABLE -> {
                byte[] buffer = new byte[SP.bytesAvailable()];
                SP.readBytes(buffer, buffer.length);
                //System.out.println("(MAC) Получено асинхронно: " + buffer.length + " байт " + bytesToHex(buffer));
                //System.out.println("(MAC) Полученное сообщение: " + Commands.responseAnalyzer(buffer));
                // Здесь обработка полученных данных
                handlePacket(buffer);
            }
        }
    }

}
