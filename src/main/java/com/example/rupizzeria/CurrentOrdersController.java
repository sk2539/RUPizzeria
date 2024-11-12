package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CurrentOrdersController implements Initializable {
    int orderNumber;

    @FXML
    private TextField orderNumberField;

    @FXML
    private ListView<String> pizzaListView;

    private ObservableList<String> pizzaList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize2();
        orderNumberField.setEditable(false);
        pizzaList = FXCollections.observableArrayList();
        addAllPizzas();
        pizzaListView.setItems(pizzaList);
    }

    @FXML
    private Button clearOrderButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button removePizzaButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private Rectangle homeButtonRec;

    private void initialize2() {
        clearOrderButton.setStyle("-fx-background-color: #f4f4f4;");
        clearOrderButton.setOnMouseEntered(event ->
                clearOrderButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        clearOrderButton.setOnMouseExited(event ->
                clearOrderButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        placeOrderButton.setStyle("-fx-background-color: #f4f4f4;");
        placeOrderButton.setOnMouseEntered(event ->
                placeOrderButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        placeOrderButton.setOnMouseExited(event ->
                placeOrderButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        removePizzaButton.setStyle("-fx-background-color: #f4f4f4;");
        removePizzaButton.setOnMouseEntered(event ->
                removePizzaButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        removePizzaButton.setOnMouseExited(event ->
                removePizzaButton.setStyle("-fx-background-color: #f4f4f4;")
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

    private void addAllPizzas() {
        ArrayList<Pizza> chicagoPizzas = ChicagoController.getChicagoPizzas();
        for (Pizza pizza : chicagoPizzas) {
            if (pizza!=null && pizza.getSize()!=null) {
                pizzaList.add("Chicago Pizza "+ pizza.toString());
                pizzaListView.setItems(pizzaList);
            }
        }
        ArrayList<Pizza> nyPizzas = NewYorkController.getNYPizzas();
        for (Pizza pizza : nyPizzas) {
            if (pizza!=null && pizza.getSize()!=null) {
                pizzaList.add("New York Pizza " + pizza.toString());
                pizzaListView.setItems(pizzaList);
            }
        }
    }

    @FXML
    private void onPlaceOrderClick() {
        orderNumber+=1;
        String orderNumberStr = Integer.toString(orderNumber);
        orderNumberField.setText(orderNumberStr);
    }

    @FXML
    private void onRemovePizzaClick(){
        String selectedPizza = pizzaListView.getSelectionModel().getSelectedItem();

        if (selectedPizza != null) {
            pizzaList.remove(selectedPizza);

            if (selectedPizza.startsWith("Chicago Pizza")) {
                ArrayList<Pizza> chicagoPizzas = ChicagoController.getChicagoPizzas();
                chicagoPizzas.removeIf(pizza -> selectedPizza.equals("Chicago Pizza " + pizza.toString()));
            } else if (selectedPizza.startsWith("New York Pizza")) {
                ArrayList<Pizza> nyPizzas = NewYorkController.getNYPizzas();
                nyPizzas.removeIf(pizza -> selectedPizza.equals("New York Pizza " + pizza.toString()));
            }
        }
    }
}