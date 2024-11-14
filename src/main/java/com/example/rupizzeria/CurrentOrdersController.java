package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.Order;
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
import java.util.concurrent.atomic.AtomicReference;

public class CurrentOrdersController implements Initializable {

    @FXML
    private TextField orderNumberField;

    @FXML
    private ListView<String> pizzaListView;

    private ObservableList<String> pizzaList;

    private Order currentOrder = new Order(0, null);

    private static ArrayList<Pizza> chicagoPizzas = ChicagoController.getChicagoPizzas();

    private static ArrayList<Pizza> nyPizzas = NewYorkController.getNYPizzas();

    private static CurrentOrdersController instance;

    public static CurrentOrdersController getInstance() {
        if (instance == null) {
            try {
                FXMLLoader loader = new FXMLLoader(CurrentOrdersController.class.getResource("currentorders-view.fxml"));
                loader.load();
                instance = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;  // Ensure instance is set on initialize
        initialize2();
        orderNumberField.setEditable(false);
        pizzaList = FXCollections.observableArrayList();
        addAllPizzas();
        pizzaListView.setItems(pizzaList);
        if (!pizzaList.isEmpty()) {
            currentOrder.setOrderNum(0);
            orderNumberField.setText("0");
        }
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

    private double currentPrice;

    @FXML
    private TextField subtotal;

    private static final double SALES_TAX_RATE = 0.06625;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField total;

    public void addPizzaToCurrentOrder(Pizza pizza, String style) {
        if (currentOrder == null) {
            currentOrder = new Order(currentOrder.getOrderNum(), pizza);  // Initialize if needed
        } else {
            currentOrder.addPizza(pizza);  // Add pizza to existing order
        }

        String pizzaDescription = pizza.price() + " - " + style + " Pizza " + pizza.toString();
        pizzaList.add(pizzaDescription);  // Update pizza list
        pizzaListView.setItems(pizzaList);

        // Calculate and update pricing
        currentPrice += pizza.price();
        subtotal.setText(String.format("%.2f", currentPrice));
        double salesTaxAmount = Math.ceil(currentPrice * SALES_TAX_RATE * 100) / 100;
        salesTax.setText(String.format("%.2f", salesTaxAmount));
        double totalWithTax = Math.ceil((currentPrice + salesTaxAmount) * 100) / 100;
        total.setText(String.format("%.2f", totalWithTax));
    }

    public void refreshOrderList() {
        pizzaList.clear();
        for (Pizza pizza : currentOrder.getOrder()) {
            String style = chicagoPizzas.contains(pizza) ? "Chicago" : nyPizzas.contains(pizza) ? "New York" : "Unknown";
            String pizzaDescription = pizza.price() + " - " + style + " Pizza " + pizza.toString();
            pizzaList.add(pizzaDescription);
        }
        pizzaListView.setItems(pizzaList);
    }


    private void addAllPizzas() {
        if (pizzaList.isEmpty()) {
            currentOrder.setOrderNum(currentOrder.getOrderNum() + 1);
            orderNumberField.setText(Integer.toString(currentOrder.getOrderNum()));
            currentOrder = new Order(currentOrder.getOrderNum(), null); // Create a new Order
        }
        double price=0;
        for (Pizza pizza : chicagoPizzas) {
            if (pizza!=null && pizza.getSize()!=null) {
                price = pizza.price();
                pizzaList.add(price + " - Chicago Pizza "+ pizza.toString());
                pizzaListView.setItems(pizzaList);
            }
            assert pizza != null;
            currentPrice+=pizza.price();
        }
        for (Pizza pizza : nyPizzas) {
            if (pizza!=null && pizza.getSize()!=null) {
                price = pizza.price();
                pizzaList.add(price + " - New York Pizza " + pizza.toString());
                pizzaListView.setItems(pizzaList);
            }
            assert pizza != null;
            currentPrice+=Math.ceil(price * 100) / 100;
        }
        subtotal.setText(String.format("%.2f", currentPrice));
        double salesTaxAmount = Math.ceil(currentPrice * SALES_TAX_RATE * 100) / 100;
        salesTax.setText(String.format("%.2f", salesTaxAmount));
        double totalWithTax = Math.ceil((currentPrice + salesTaxAmount) * 100) / 100;
        total.setText(String.format("%.2f", totalWithTax));
    }

    @FXML
    private void onClearOrderClick() {
        chicagoPizzas.clear();
        nyPizzas.clear();
        pizzaList.clear();
        pizzaListView.setItems(pizzaList);
        currentPrice = 0.0;
        subtotal.setText("");
        salesTax.setText("");
        total.setText("");
        orderNumberField.setText("");
    }

    @FXML
    private double setSubtotalForSelectedPizza() {
        AtomicReference<Double> subtotalValue = new AtomicReference<>(0.0);
        pizzaListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            double price = 0.0;
            boolean found = false;
            if (newValue != null) {
                // Check for Chicago Pizza
                for (Pizza pizza : chicagoPizzas) {
                    price = Math.ceil(pizza.price() * 100) / 100;
                    if ((price + " - Chicago Pizza " + pizza.toString()).equals(newValue)) {
                        found = true;
                        subtotal.setText(String.format("%.2f", price));
                        subtotalValue.set(price);
                        break;
                    }
                }
                if (!found) {
                    for (Pizza pizza : nyPizzas) {
                        price = Math.ceil(pizza.price() * 100) / 100;
                        if ((price + " - New York Pizza " + pizza.toString()).equals(newValue)) {
                            found = true;
                            subtotal.setText(String.format("%.2f", price));
                            subtotalValue.set(price);
                            break;
                        }
                    }
                }
            }
        });
        return subtotalValue.get();
    }



    @FXML
    private void onPlaceOrderClick() {
        onClearOrderClick();  // Clear the UI
        currentOrder = new Order(currentOrder.getOrderNum() + 1, null);  // Reset current order
        orderNumberField.setText(Integer.toString(currentOrder.getOrderNum()));
    }


    @FXML
    private void onRemovePizzaClick(){
        String selectedPizza = pizzaListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            pizzaList.remove(selectedPizza);
            double priceToRemove = setSubtotalForSelectedPizza();
            if (selectedPizza.contains("Chicago Pizza")) {
                for (Pizza pizza : chicagoPizzas) {
                    if (selectedPizza.equals("Chicago Pizza " + pizza.toString())) {
                        chicagoPizzas.remove(pizza);
                        break;
                    }
                }
            } else if (selectedPizza.contains("New York Pizza")) {
                for (Pizza pizza : nyPizzas) {
                    if (selectedPizza.equals("New York Pizza " + pizza.toString())) {
                        nyPizzas.remove(pizza);
                        break;
                    }
                }
            }
            currentPrice -= priceToRemove;
            subtotal.setText(String.format("%.2f", currentPrice));
            double salesTaxAmount = Math.ceil(currentPrice * SALES_TAX_RATE * 100) / 100;
            salesTax.setText(String.format("%.2f", salesTaxAmount));
            double totalWithTax = Math.ceil((currentPrice + salesTaxAmount) * 100) / 100;
            total.setText(String.format("%.2f", totalWithTax));
        }
    }
}