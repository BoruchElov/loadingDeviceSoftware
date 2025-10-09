package org.example.loadingdevicesoftware.communicationWithInverters;

import java.util.Objects;

public class Address {

    private int value; // 4 байта

    public Address(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Метод для записи адреса в читаемом формате.
     *
     * @return MAC в формате B1:B2:B3:B4
     */
    public String toStringInHexFormat() {
        String hex = String.format("%08X", value);
        StringBuilder hexOutput = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            hexOutput.append(hex, i * 2, (i + 1) * 2);
            hexOutput.append(((i +1) * 2 == 8) ? "" : ":");
        }
        return hexOutput.toString();
    }


    // Для удобства: вывод как IP-адрес
    @Override
    public String toString() {
        return String.format("%d:%d:%d:%d",
                (byte) value,
                (byte) (value >> 8),
                (byte) (value >> 16),
                (byte) (value >> 24));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return value == address.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

