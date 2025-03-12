module org.example.loadingdevicesoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires itext;
    requires java.compiler;

    opens org.example.loadingdevicesoftware to javafx.fxml;
    exports org.example.loadingdevicesoftware;
    exports org.example.loadingdevicesoftware.logicAndSettingsOfInterface;
    opens org.example.loadingdevicesoftware.logicAndSettingsOfInterface to javafx.fxml;
    exports org.example.loadingdevicesoftware.pagesControllers;
    opens org.example.loadingdevicesoftware.pagesControllers to javafx.fxml;
    exports org.example.loadingdevicesoftware.communicationWithInverters;
    opens org.example.loadingdevicesoftware.communicationWithInverters to javafx.fxml;
    exports org.example;
    opens org.example to javafx.fxml;
}