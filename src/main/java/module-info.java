module com.example.tallergrafos {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.tallergrafos to javafx.fxml;
    exports com.example.tallergrafos;
}