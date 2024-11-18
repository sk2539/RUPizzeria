package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.Order;
import com.example.rupizzeria.pizzaria.src.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

/**
 * Controller class for managing the current pizza orders.
 * Handles the addition, removal, and placement of orders, along with updating UI elements such as price breakdowns.
 * @author Nithya Konduru, Dhyanashri Raman
 */
public class CurrentOrdersController implements Initializable {

    @FXML
    private TextField orderNumberField;

    @FXML
    private ListView<String> pizzaListView;

    private ObservableList<String> pizzaList;

    private Order currentOrder = new Order(0, (Pizza) null);


    private static ObservableList<Pizza> chicagoPizzas = ChicagoController.getChicagoPizzasList();

    private static ObservableList<Pizza> nyPizzas = NewYorkController.getNYPizzas();

    private static int orderNumber = 0;

    private static ObservableList<Pizza> unifiedPizzaList = FXCollections.observableArrayList();

    /**
     * Initializes the controller when the corresponding FXML is loaded.
     * Sets up event listeners, initializes UI components, and populates the pizza list.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize2();
        orderNumberField.setEditable(false);
        pizzaList = FXCollections.observableArrayList();
        addAllPizzas();
        pizzaListView.setItems(pizzaList);
        unifiedPizzaList.addListener((ListChangeListener<Pizza>) change -> refreshOrderList());
        orderNumberField.setText(String.valueOf(orderNumber));
    }

    /**
     * Refreshes the pizza list view by consolidating pizzas from both Chicago and New York lists.
     * Also updates the pricing details.
     */
    private void refreshOrderList() {
        pizzaList.clear();
        for (Pizza pizza : unifiedPizzaList) {
            String pizzaDescription = String.format("%.2f %s - %s", pizza.price(), pizza.getClass().getSimpleName(), pizza.toString());
            pizzaList.add(pizzaDescription);
        }
        pizzaListView.setItems(pizzaList);
        updatePricing();
    }

    /**
     * Updates the price breakdown for the current order.
     * Calculates the subtotal, sales tax, and total price including tax.
     */
    private void updatePricing() {
        currentPrice = chicagoPizzas.stream().mapToDouble(Pizza::price).sum() + nyPizzas.stream().mapToDouble(Pizza::price).sum();
        subtotal.setText(String.format("%.2f", currentPrice));
        double salesTaxAmount = Math.ceil(currentPrice * SALES_TAX_RATE * 100) / 100;
        salesTax.setText(String.format("%.2f", salesTaxAmount));
        double totalWithTax = Math.ceil((currentPrice + salesTaxAmount) * 100) / 100;
        total.setText(String.format("%.2f", totalWithTax));
    }

    /**
     * Resets the pizza list and clears the pricing details for the current order.
     */
    void loadOrderData() {
        pizzaList.clear();
        currentPrice = 0.0;
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

    /**
     * Initializes button styles and hover effects for interactive UI elements.
     */
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

    /**
     * Handles the click event for the home button.
     * Loads the main page FXML and opens a new window.
     */
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

    /**
     * Adds all pizzas from Chicago and New York lists to the current order.
     * Updates the pizza list and pricing details accordingly.
     */
    private void addAllPizzas() {
        pizzaList.clear();
        currentOrder.getOrder().clear();
        currentPrice = 0.0;
        for (Pizza pizza : CurrentOrdersController.getUnifiedPizzaList()) {
            if (pizza != null && pizza.getSize() != null) {
                double price = pizza.price();
                pizzaList.add(String.format("%.2f %s - %s", price, pizza.getClass().getSimpleName(), pizza.toString()));
                pizzaListView.setItems(pizzaList);
                currentOrder.addPizza(pizza);
                currentPrice += price;
            }
        }
        subtotal.setText(String.format("%.2f", currentPrice));
        double salesTaxAmount = Math.ceil(currentPrice * SALES_TAX_RATE * 100) / 100;
        salesTax.setText(String.format("%.2f", salesTaxAmount));
        double totalWithTax = Math.ceil((currentPrice + salesTaxAmount) * 100) / 100;
        total.setText(String.format("%.2f", totalWithTax));
    }

    /**
     * Clears the current order, resets the pizza list, and updates pricing fields to empty.
     */
    @FXML
    private void onClearOrderClick() {
        CurrentOrdersController.getUnifiedPizzaList().clear();
        currentOrder.getOrder().clear();
        pizzaList.clear();
        pizzaListView.setItems(pizzaList);
        currentPrice = 0.0;
        subtotal.setText("");
        salesTax.setText("");
        total.setText("");
    }

    /**
     * Places the current order by adding it to the list of placed orders.
     * Clears the current order afterward.
     */
    @FXML
    private void onPlaceOrderClick() {
        ObservableList<Pizza> unifiedPizzaList = CurrentOrdersController.getUnifiedPizzaList();
        if (unifiedPizzaList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Orders");
            alert.setHeaderText("No pizzas to place an order.");
            alert.setContentText("Please add pizzas to your order before placing it.");
            alert.showAndWait();
            return;
        }
        orderNumber += 1;
        Order newOrder = new Order(orderNumber, new ArrayList<>(unifiedPizzaList));
        OrdersPlacedController.addOrder(newOrder);
        onClearOrderClick();
        currentOrder = new Order(orderNumber, new ArrayList<>());
        orderNumberField.setText(Integer.toString(orderNumber));
    }

    /**
     * Removes the selected pizza from the current order and updates the pricing fields.
     */
    @FXML
    private void onRemovePizzaClick() {
        String selectedPizza = pizzaListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            unifiedPizzaList.removeIf(pizza -> {
                String pizzaDescription = String.format("%.2f %s - %s", pizza.price(), pizza.getClass().getSimpleName(), pizza.toString());
                return pizzaDescription.equals(selectedPizza);
            });
            refreshOrderList();
        }
    }

    /**
     * Provides access to the unified list of pizzas for the current order.
     * This list consolidates all pizzas added from various controllers (e.g., Chicago and New York),
     * maintaining the order in which they were added.
     *
     * The unified pizza list is used throughout the application to manage the current order,
     * including displaying pizzas, calculating totals, and clearing or placing orders.
     *
     * @return An ObservableList containing all pizzas in the current order.
     */
    public static ObservableList<Pizza> getUnifiedPizzaList() {
        return unifiedPizzaList;
    }
}