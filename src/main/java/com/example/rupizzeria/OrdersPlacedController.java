package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrdersPlacedController implements Initializable {
    @FXML
    private Button browseButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button exportButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private Rectangle homeButtonRec;

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
        homeButtonRec.setVisible(false);
        homeButton.setOnMouseEntered(event -> homeButtonRec.setVisible(true));
        homeButton.setOnMouseExited(event -> homeButtonRec.setVisible(false));
    }

    @FXML
    private void handleHomeButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Main Page");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
