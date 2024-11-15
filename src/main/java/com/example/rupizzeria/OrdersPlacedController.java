package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.Order;
import com.example.rupizzeria.pizzaria.src.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private static final ObservableList<Order> placedOrders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize2();
        setupTableColumns();

        // Make sure table is visible and has data
        ordersTable.setItems(placedOrders);
        ordersTable.setVisible(true);

        // Add some basic styling
        ordersTable.setStyle("-fx-border-color: #d6b0b0; -fx-border-width: 1px;");

        // Enable row selection
        ordersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Debug print to check if orders are being added
        System.out.println("Number of orders in table: " + placedOrders.size());
    }

    private void setupTableColumns() {
        // Order Number Column
        TableColumn<Order, Integer> orderNumColumn = new TableColumn<>("Order #");
        orderNumColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getOrderNum()).asObject());
        orderNumColumn.setStyle("-fx-alignment: CENTER;");

        // Order Total Column
        TableColumn<Order, Double> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(data -> {
            Order order = data.getValue();
            double total = order.getOrder().stream().mapToDouble(Pizza::price).sum();
            return new SimpleDoubleProperty(total).asObject();
        });
        totalColumn.setCellFactory(tc -> new TableCell<Order, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText(empty ? null : String.format("$%.2f", price));
            }
        });
        totalColumn.setStyle("-fx-alignment: CENTER-RIGHT;");

        // Pizza Count Column
        TableColumn<Order, Integer> pizzaCountColumn = new TableColumn<>("# of Pizzas");
        pizzaCountColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getOrder().size()).asObject());
        pizzaCountColumn.setStyle("-fx-alignment: CENTER;");

        // Order Details Column
        TableColumn<Order, String> detailsColumn = new TableColumn<>("Order Details");
        detailsColumn.setCellValueFactory(data -> {
            String details = data.getValue().getOrder().stream()
                    .map(Pizza::toString)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("No pizzas");
            return new SimpleStringProperty(details);
        });

        // Set column widths
        double tableWidth = ordersTable.getPrefWidth();
        orderNumColumn.setPrefWidth(tableWidth * 0.15);
        totalColumn.setPrefWidth(tableWidth * 0.15);
        pizzaCountColumn.setPrefWidth(tableWidth * 0.15);
        detailsColumn.setPrefWidth(tableWidth * 0.55);

        // Add columns to table
        ordersTable.getColumns().setAll(orderNumColumn, totalColumn, pizzaCountColumn, detailsColumn);
    }



    // Static method to add a new order
    public static void addOrder(Order order) {
        placedOrders.add(order);
    }

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

    @FXML
    private void handleBrowseButtonClick() {
        // Implement file browsing functionality if needed
    }

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

    @FXML
    private void handleExportClick() {
        // Implement export functionality if needed
    }
}