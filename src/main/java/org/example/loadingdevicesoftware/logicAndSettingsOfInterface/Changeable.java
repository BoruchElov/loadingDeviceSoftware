package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

public interface Changeable {

    enum Status {
        NORMAL, LOCKED
    }

    void changePosition(int position);

    void setActualStatus(Status status);

}
