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
    int orderNumber;

    @FXML
    private TextField orderNumberField;

    @FXML
    private ListView<String> pizzaListView;

    private ObservableList<String> pizzaList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderNumberField.setEditable(false);
        pizzaList = FXCollections.observableArrayList();
        addAllPizzas();
        pizzaListView.setItems(pizzaList);
    }

    private void addAllPizzas() {
        ArrayList<Pizza> chicagoPizzas = ChicagoController.getChicagoPizzas();
        for (Pizza pizza : chicagoPizzas) {
            if (pizza.getSize()!=null) {
                pizzaList.add("Chicago Pizza "+ pizza.toString());
                pizzaListView.setItems(pizzaList);
            }
        }
        ArrayList<Pizza> nyPizzas = NewYorkController.getNYPizzas();
        for (Pizza pizza : nyPizzas) {
            if (pizza.getSize()!=null) {
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
}