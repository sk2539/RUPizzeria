package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class MainPageController {
    @FXML
    private void handleOpenChicagoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chicago-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ordering a Chicago Style Pizza");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOpenNewYorkView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newyork-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ordering a New York Style Pizza");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOpenCurrentOrdersView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("currentorders-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("My Shopping Cart");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
