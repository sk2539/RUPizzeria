package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CurrentOrdersController implements Initializable {
    // Instances of other controllers
    ChicagoController c = new ChicagoController();
    NewYorkController ny = new NewYorkController();
    int orderNumber;

    @FXML
    private TextField orderNumberField;

    @FXML
    private ListView<Pizza> pizzaListView;

    private ObservableList<Pizza> pizzaList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderNumberField.setEditable(false);
        pizzaList = FXCollections.observableArrayList();
        pizzaListView.setItems(pizzaList);
        addAllPizzas();
    }

    // Method to add pizzas from Chicago and New York controllers
    private void addAllPizzas() {
        // Get pizzas from Chicago controller
        ArrayList<Pizza> chicagoPizzas = c.getChicagoPizzas();
        for (Pizza pizza : chicagoPizzas) {
            pizzaList.add(pizza);  // Add each pizza to the ObservableList
        }

        // Get pizzas from New York controller
        ArrayList<Pizza> nyPizzas = ny.getNYPizzas();
        for (Pizza pizza : nyPizzas) {
            pizzaList.add(pizza);  // Add each pizza to the ObservableList
        }
    }

    @FXML
    private void onPlaceOrderClick() {
        orderNumber+=1;
        String orderNumberStr = Integer.toString(orderNumber);
        orderNumberField.setText(orderNumberStr);
    }
}