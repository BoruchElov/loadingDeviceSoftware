package org.example.loadingdevicesoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ApplicationFile extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationFile.class.getResource("baseWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 395, 297);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/images/control-system.png")).toExternalForm()));
        stage.setResizable(false);
        stage.setTitle("Контроллер прогрузочного устройства");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}