module com.example.baze_podataka {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.baze_podataka to javafx.fxml;
    exports com.example.baze_podataka;
    exports com.example.baze_podataka.pages;
    opens com.example.baze_podataka.pages to javafx.fxml;
}