module com.example.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.rupizzeria to javafx.fxml;
    exports com.example.rupizzeria;
}