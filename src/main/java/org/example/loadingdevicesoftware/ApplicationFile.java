package org.example.loadingdevicesoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.DateTimeUpdater;

import java.io.IOException;
import java.util.Objects;

public class ApplicationFile extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationFile.class.
                getResource("0.baseWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), ApplicationConstants.APPLICATION_WINDOW_WIDTH,
                ApplicationConstants.APPLICATION_WINDOW_HEIGHT);
        scene.getStylesheets().add(ApplicationFile.class.getResource("applicationStyle.css").toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().
                getResource("/images/control-system.png")).toExternalForm()));
        stage.setResizable(false);
        stage.setTitle("Контроллер прогрузочного устройства");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        // Завершаем поток даты и времени
        DateTimeUpdater.getInstance().stop();
        super.stop();
    }

}