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
    ChicagoController c = new ChicagoController();
    NewYorkController ny = new NewYorkController();
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
            pizzaList.add("Chicago Pizza "+ pizza.toString());
            pizzaListView.setItems(pizzaList);
            System.out.println("Chicago Pizza "+ pizza.toString());
        }
        ArrayList<Pizza> nyPizzas = NewYorkController.getNYPizzas();
        for (Pizza pizza : nyPizzas) {
            pizzaList.add("New York Pizza " + pizza.toString());
            pizzaListView.setItems(pizzaList);
            System.out.println(("New York Pizza " + pizza.toString()));
        }
    }

    @FXML
    private void onPlaceOrderClick() {
        orderNumber+=1;
        String orderNumberStr = Integer.toString(orderNumber);
        orderNumberField.setText(orderNumberStr);
    }
}