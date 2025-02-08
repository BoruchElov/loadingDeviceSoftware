package org.example.loadingdevicesoftware;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUpdater implements Runnable {

    private static DateTimeUpdater instance;

    private final StringProperty dateTimeProperty = new SimpleStringProperty();
    private volatile boolean running = true;

    private DateTimeUpdater() {
        updateDateTime(); // Устанавливаем начальное значение
    }

    public static DateTimeUpdater getInstance() {
        if (instance == null) {
            synchronized (DateTimeUpdater.class) {
                if (instance == null) {
                    instance = new DateTimeUpdater();
                    Thread thread = new Thread(instance);
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }
        return instance;
    }

    public StringProperty dateTimeProperty() {
        return dateTimeProperty;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(60000); // Обновление каждую минуту
                Platform.runLater(this::updateDateTime);
            } catch (Throwable e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void updateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm");
        dateTimeProperty.set(LocalDateTime.now().format(formatter));
    }
}

