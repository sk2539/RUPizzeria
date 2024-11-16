package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
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

    @FXML
    private void handleOpenMenuView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("RU Pizzeria Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOpenOrdersPlacedView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersplaced-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Your Orders Placed");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button chicagoPizzaButton;

    @FXML
    private Button nyPizzaButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button ordersPlacedButton;

    @FXML
    private ImageView shoppingCart;

    @FXML
    private Rectangle shoppingCartRec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chicagoPizzaButton.setStyle("-fx-background-color: #f4f4f4;");
        chicagoPizzaButton.setOnMouseEntered(event ->
                chicagoPizzaButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        chicagoPizzaButton.setOnMouseExited(event ->
                chicagoPizzaButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        nyPizzaButton.setStyle("-fx-background-color: #f4f4f4;");
        nyPizzaButton.setOnMouseEntered(event ->
                nyPizzaButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        nyPizzaButton.setOnMouseExited(event ->
                nyPizzaButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        menuButton.setStyle("-fx-background-color: #f4f4f4;");
        menuButton.setOnMouseEntered(event ->
                menuButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        menuButton.setOnMouseExited(event ->
                menuButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        ordersPlacedButton.setStyle("-fx-background-color: #f4f4f4;");
        ordersPlacedButton.setOnMouseEntered(event ->
                ordersPlacedButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        ordersPlacedButton.setOnMouseExited(event ->
                ordersPlacedButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        shoppingCartRec.setVisible(false);
        shoppingCart.setOnMouseEntered(event -> shoppingCartRec.setVisible(true));
        shoppingCart.setOnMouseExited(event -> shoppingCartRec.setVisible(false));
    }

    @FXML
    private void handleChicagoClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chicago-view.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setTitle("Select a Chicago Style Pizza");
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNYClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newyork-view.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setTitle("Select a Chicago Style Pizza");
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
