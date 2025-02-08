package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import lombok.Getter;

public class ContactObject {

    public enum ContactPosition {
        OPENED, CLOSED
    }

    public enum ContactStatus {
        ENABLED, DISABLED
    }

    ContactStatus contactStatus;
    @Getter
    ContactPosition contactPosition;

    private final ImageView contactImageView;

    public ContactObject (Button contactButton, ImageView contactImageView, ContactPosition contactPosition,
                          ContactStatus contactStatus) {

        this.contactImageView = contactImageView;

        InterfaceElementsSettings.getContactButton(contactButton, contactImageView, InterfaceElementsSettings.Background.LIGHT_BLUE);

        switch (contactStatus) {
            case DISABLED -> setDisabled();
            case ENABLED -> setEnabled();
        }

        switch (contactPosition) {
            case OPENED -> setOpened();
            case CLOSED -> setClosed();
        }

    }

    public void setEnabled() {
        contactStatus = ContactStatus.ENABLED;
        contactImageView.setVisible(true);
    }

    public void setDisabled() {
        contactStatus = ContactStatus.DISABLED;
        contactImageView.setVisible(false);
    }

    public void setOpened() {
        contactPosition = ContactPosition.OPENED;
        contactImageView.setImage(ApplicationConstants.NORMALLY_OPENED_CONTACT);
    }

    public void setClosed() {
        contactPosition = ContactPosition.CLOSED;
        contactImageView.setImage(ApplicationConstants.NORMALLY_CLOSED_CONTACT);
    }



}
