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
    requires com.fazecast.jSerialComm;
    requires com.fasterxml.jackson.databind;
    requires commons.math3;
    requires javafx.graphics;
    requires io.fair_acc.chartfx;
    requires io.fair_acc.dataset;
    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.fontawesome5;

    requires org.slf4j;

    opens org.example.loadingdevicesoftware to javafx.fxml;
    exports org.example.loadingdevicesoftware;
    exports org.example.loadingdevicesoftware.logicAndSettingsOfInterface;
    opens org.example.loadingdevicesoftware.logicAndSettingsOfInterface to javafx.fxml;
    exports org.example.loadingdevicesoftware.pagesControllers;
    opens org.example.loadingdevicesoftware.pagesControllers to javafx.fxml;
}