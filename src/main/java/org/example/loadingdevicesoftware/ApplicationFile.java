package org.example.loadingdevicesoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ApplicationFile extends Application {

    private _0_MainScreenController a0MainScreenController;
    private _7_DifProtectionScreenController a7DifProtectionScreenController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationFile.class.
                getResource("0.baseWindow.fxml"));
        FXMLLoader fxmlDPLoader = new FXMLLoader(ApplicationFile.class.
                getResource("DifProtectionScreen.fxml"));
        a0MainScreenController = fxmlLoader.getController();
        a7DifProtectionScreenController = fxmlDPLoader.getController();
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
        if (a0MainScreenController != null) {
            a0MainScreenController.stopUpdatingDateAndTime();
        }
        // Остановка потока при завершении приложения
        if (a7DifProtectionScreenController != null) {
            a7DifProtectionScreenController.stopUpdatingDateAndTime();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}