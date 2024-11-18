package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.Order;
import com.example.rupizzeria.pizzaria.src.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The OrdersPlacedController class manages the "Orders Placed" view in the RU Pizzeria application.
 * It provides functionality to browse, cancel, and export orders, as well as navigation back to the main page.
 * This class interacts with the TableView to display orders and handle user actions.
 * @author Nithya Konduru, Dhyanashri Raman
 */
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

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TextArea orderDetailsTextArea;

    private static final ObservableList<Order> placedOrders = FXCollections.observableArrayList();

    /**
     * Initializes the "Orders Placed" view, sets up the table columns, and styles the buttons.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize2();
        setColumns();
        ordersTable.setItems(placedOrders);
        ordersTable.setVisible(true);
        ordersTable.setStyle("-fx-border-color: #d6b0b0; -fx-border-width: 1px;");
        ordersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Configures the columns of the orders table.
     * Sets up the order number, total price (subtotal + sales tax), pizza count, and order details columns with appropriate formatting and styles.
     */
    private void setColumns() {
        TableColumn<Order, Integer> orderNumColumn = new TableColumn<>("Order #");
        orderNumColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getOrderNum()).asObject());
        orderNumColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Order, Double> totalColumn = new TableColumn<>("Total ($)");
        totalColumn.setCellValueFactory(data -> {
            Order order = data.getValue();
            double subtotal = order.getOrder().stream().mapToDouble(Pizza::price).sum();
            double totalWithTax = subtotal * 1.06625;
            totalWithTax = Math.ceil(totalWithTax * 100.0) / 100.0;
            return new SimpleDoubleProperty(totalWithTax).asObject();
        });
        totalColumn.setCellFactory(tc -> new TableCell<Order, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", price));
                }
            }
        });
        totalColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumn<Order, Integer> pizzaCountColumn = new TableColumn<>("# of Pizzas");
        pizzaCountColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getOrder().size()).asObject());
        pizzaCountColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Order, String> detailsColumn = new TableColumn<>("Order Details");
        detailsColumn.setCellValueFactory(data -> {
            String details = data.getValue().getOrder().stream()
                    .map(pizza -> pizza.getClass().getSimpleName() + " - " + pizza.toString())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("No pizzas");
            return new SimpleStringProperty(details);
        });
        double tableWidth = ordersTable.getPrefWidth();
        orderNumColumn.setPrefWidth(tableWidth * 0.15);
        totalColumn.setPrefWidth(tableWidth * 0.15);
        pizzaCountColumn.setPrefWidth(tableWidth * 0.15);
        detailsColumn.setPrefWidth(tableWidth * 0.55);
        ordersTable.getColumns().setAll(orderNumColumn, totalColumn, pizzaCountColumn, detailsColumn);
    }

    /**
     * Adds an order to the list of placed orders.
     *
     * @param order The order to be added.
     */
    public static void addOrder(Order order) {
        placedOrders.add(order);
    }

    /**
     * Sets up hover effects for the buttons in the UI.
     * Changes the background color of buttons on mouse hover.
     */
    public void initialize2() {
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

    /**
     * Navigates to the main page when the home button is clicked.
     */
    @FXML
    private void handleHomeButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setTitle("Main Page");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the details of the selected order in the text area.
     * Includes the price of each pizza in the order.
     * If no order is selected, shows a warning dialog.
     */
    @FXML
    private void handleBrowseButtonClick() {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Order Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an order to browse.");
            alert.showAndWait();
            return;
        }
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order Number: ").append(selectedOrder.getOrderNum()).append("\n");
        double totalPrice = selectedOrder.getOrder().stream().mapToDouble(Pizza::price).sum();
        orderDetails.append("Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");
        orderDetails.append("Pizzas Ordered:\n");
        if (selectedOrder.getOrder().isEmpty()) {
            orderDetails.append("  No pizzas in this order.\n");
        } else {
            for (Pizza pizza : selectedOrder.getOrder()) {
                orderDetails.append("  - ")
                        .append(pizza.getClass().getSimpleName())
                        .append(" - ").append(pizza.toString())
                        .append(" ($").append(String.format("%.2f", pizza.price())).append(")\n");
            }
        }
        orderDetailsTextArea.setText(orderDetails.toString());
    }

    /**
     * Removes the selected order from the list of placed orders.
     * If no order is selected, shows a warning dialog.
     */
    @FXML
    private void handleCancelClick() {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            placedOrders.remove(selectedOrder);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Order Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an order to cancel.");
            alert.showAndWait();
        }
    }

    /**
     * Exports the list of placed orders to a text file.
     * Displays an alert if the export is successful or if an error occurs.
     */
    @FXML
    private void handleExportClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders to File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage stage = (Stage) exportButton.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Order Number\tTotal Price\tPizzas\n");
                writer.write("-------------------------------------------------\n");
                for (Order order : placedOrders) {
                    writer.write("Order #" + order.getOrderNum() + "\t");
                    double total = order.getOrder().stream().mapToDouble(Pizza::price).sum();
                    writer.write(String.format("$%.2f\t", total));
                    String pizzas = order.getOrder().stream()
                            .map(pizza -> pizza.getClass().getSimpleName() + " - " + pizza.toString())
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("No pizzas");
                    writer.write(pizzas + "\n\n");
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Successful!");
                alert.setHeaderText(null);
                alert.setContentText("Exported orders can be found here:\n" + file.getAbsolutePath());
                alert.show();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
                alert.setHeaderText(null);
                alert.setContentText("Error exporting orders:\n" + e.getMessage());
                alert.show();
            }
        }
    }

}