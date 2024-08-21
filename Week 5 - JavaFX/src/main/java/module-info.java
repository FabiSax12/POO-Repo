module com.example.week5javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.week5javafx to javafx.fxml;
    exports com.example.week5javafx;
}