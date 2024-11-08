package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersPlacedController implements Initializable {
    @FXML
    private Button browseButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button exportButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        browseButton.setStyle("-fx-background-color: #f4f4f4;");
        browseButton.setOnMouseEntered(event ->
                browseButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        browseButton.setOnMouseExited(event ->
                browseButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        cancelButton.setStyle("-fx-background-color: #f4f4f4;");
        cancelButton.setOnMouseEntered(event ->
                cancelButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        cancelButton.setOnMouseExited(event ->
                cancelButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        exportButton.setStyle("-fx-background-color: #f4f4f4;");
        exportButton.setOnMouseEntered(event ->
                exportButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        exportButton.setOnMouseExited(event ->
                exportButton.setStyle("-fx-background-color: #f4f4f4;")
        );
    }
}
