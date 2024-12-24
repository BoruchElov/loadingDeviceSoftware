package org.example.loadingdevicesoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ApplicationFile extends Application {

    private MainScreenController mainScreenController;
    private DifProtectionScreenController difProtectionScreenController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationFile.class.
                getResource("baseWindow.fxml"));
        FXMLLoader fxmlDPLoader = new FXMLLoader(ApplicationFile.class.
                getResource("DifProtectionScreen.fxml"));
        mainScreenController = fxmlLoader.getController();
        difProtectionScreenController = fxmlDPLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().
                getResource("/images/control-system.png")).toExternalForm()));
        stage.setResizable(false);
        stage.setTitle("Контроллер прогрузочного устройства");
        stage.setScene(scene);
        stage.show();
    }

    //Метод для остановки потока при завершении приложения
    @Override
    public void stop() throws Exception {
        // Остановка потока при завершении приложения
        if (mainScreenController != null) {
            mainScreenController.stopUpdatingDateAndTime();
        }
        // Остановка потока при завершении приложения
        if (difProtectionScreenController != null) {
            difProtectionScreenController.stopUpdatingDateAndTime();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}