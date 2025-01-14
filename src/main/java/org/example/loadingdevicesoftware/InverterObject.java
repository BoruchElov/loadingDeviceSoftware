package org.example.loadingdevicesoftware;

public class InverterObject {

    private String MACAddress;
    private String status;

    private enum Status {
        IS_ON, IS_OFF
    }

    public InverterObject() {}

    public void setAdress(String MACAddress) {
        this.MACAddress = MACAddress;
    }

    public String getAdress() {
        return MACAddress;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }

    public String getStatus() {
        return status;
    }

    public String[] getInverter() {
        return new String[] { MACAddress, status };
    }

}
