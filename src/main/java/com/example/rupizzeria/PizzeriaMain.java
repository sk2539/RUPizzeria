package com.example.rupizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzeriaMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("currentorders-view.fxml"));
        loader.load();
        CurrentOrdersController currentOrdersController = loader.getController();
        currentOrdersController.loadOrderData();
        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaMain.class.getResource("mainpage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 934, 388);
        stage.setTitle("Main Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}