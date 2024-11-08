package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChicagoController implements Initializable {
    @FXML
    private RadioButton large;

    @FXML
    private RadioButton medium;

    @FXML
    private ToggleGroup size;

    @FXML
    private RadioButton small;

    @FXML
    private ChoiceBox<String> chooseType;

    @FXML
    private TextField crustTypeField;

    private static ArrayList<Pizza> pizzaArrayList = new ArrayList<>();

    private static ArrayList<Topping> byoToppings = new ArrayList<>();
    
    private final String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build your own"};

    private ObservableList<String> availableToppingsList;

    private ObservableList<String> selectedToppingsList;

    private Pizza selectedPizza;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize2();
        availableToppings.setDisable(true);
        selectedToppings.setDisable(true);
        chooseType.getItems().addAll(pizzaTypes);
        crustTypeField.setEditable(false);
        availableToppingsList = FXCollections.observableArrayList();
        selectedToppingsList = FXCollections.observableArrayList();
        availableToppings.setItems(availableToppingsList);
        selectedToppings.setItems(selectedToppingsList);
        setAvailableToppings();
        availableToppings.setItems(availableToppingsList);
        chooseType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedPizza = makePizza();
            if (selectedPizza != null) {
                updateCrustType();
                if (!chooseType.getValue().equals("Build your own")) {
                    setSelectedToppings();
                }
                else {
                    selectedPizza.setToppings(byoToppings);
                }
            }
        });
    }

    @FXML
    private Button addToOrderButton;

    @FXML
    private Button leftArrowButton;

    @FXML
    private Button rightArrowButton;

    private void initialize2() {
        addToOrderButton.setStyle("-fx-background-color: #f4f4f4;");
        addToOrderButton.setOnMouseEntered(event ->
                addToOrderButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        addToOrderButton.setOnMouseExited(event ->
                addToOrderButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        leftArrowButton.setStyle("-fx-background-color: #f4f4f4;");
        leftArrowButton.setOnMouseEntered(event ->
                leftArrowButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        leftArrowButton.setOnMouseExited(event ->
                leftArrowButton.setStyle("-fx-background-color: #f4f4f4;")
        );
        rightArrowButton.setStyle("-fx-background-color: #f4f4f4;");
        rightArrowButton.setOnMouseEntered(event ->
                rightArrowButton.setStyle("-fx-background-color: #d6b0b0;")
        );
        rightArrowButton.setOnMouseExited(event ->
                rightArrowButton.setStyle("-fx-background-color: #f4f4f4;")
        );
    }

    private void setAvailableToppings() {
        for (Topping topping : Topping.values()) {
            availableToppingsList.add(topping.toString());
        }
    }

    private void setSelectedToppings() {
        selectedToppingsList.clear();
        if (selectedPizza != null) {
            for (Topping topping : selectedPizza.getToppings()) {
                selectedToppingsList.add(topping.toString());
            }
        }
    }

    @FXML
    private Pizza makePizza() { // try to make this method shorter later if time permits
        Pizza newPizza = null;
        ChicagoPizza cpizza = new ChicagoPizza();
        if (chooseType.getValue()!=null) {
            updateCrustType();
        }
        if (chooseType.getValue().equals("Deluxe")) {
            availableToppings.setDisable(true);
            selectedToppings.setDisable(false);
            leftArrowButton.setDisable(true);
            rightArrowButton.setDisable(true);
            newPizza = cpizza.createDeluxe();
            newPizza.setSize(getSizeFromToggleGroup());
            return newPizza;
        }
        if (chooseType.getValue().equals("BBQ Chicken")) {
            availableToppings.setDisable(true);
            selectedToppings.setDisable(false);
            leftArrowButton.setDisable(true);
            rightArrowButton.setDisable(true);
            newPizza = cpizza.createBBQChicken();
            newPizza.setSize(getSizeFromToggleGroup());
            return newPizza;
        }
        if (chooseType.getValue().equals("Meatzza")) {
            availableToppings.setDisable(true);
            selectedToppings.setDisable(false);
            leftArrowButton.setDisable(true);
            rightArrowButton.setDisable(true);
            newPizza = cpizza.createMeatzza();
            newPizza.setSize(getSizeFromToggleGroup());
            return newPizza;
        }
        if (chooseType.getValue().equals("Build your own")) {
            availableToppings.setDisable(false);
            selectedToppings.setDisable(false);
            leftArrowButton.setDisable(false);
            rightArrowButton.setDisable(false);
            newPizza = cpizza.createBuildYourOwn();
            newPizza.setSize(getSizeFromToggleGroup());
            newPizza.setToppings(byoToppings);
            return newPizza;
        }
        return newPizza;
    }

    @FXML
    private void moveItem() {
        String selectedItem = availableToppings.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedToppings.getItems().add(selectedItem);
            if (selectedToppings.getItems().size()>7) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Too Many Toppings");
                alert.setContentText("You cannot select more than 7 toppings.");
                alert.showAndWait();
            }
            byoToppings.add(Topping.stringToTopping(selectedItem));
            availableToppings.getItems().remove(selectedItem);
            availableToppings.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void moveItemBack() {
        String selectedItem = selectedToppings.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            availableToppings.getItems().add(selectedItem);
            byoToppings.remove(Topping.stringToTopping(selectedItem));
            selectedToppings.getItems().remove(selectedItem);
            selectedToppings.getSelectionModel().clearSelection();
        }
    }


    @FXML
    private ListView<String> availableToppings;

    @FXML
    private ListView<String> selectedToppings;

    @FXML
    private void updateCrustType() {
        Pizza newPizza = null;
        ChicagoPizza cpizza = new ChicagoPizza();
        if (chooseType.getValue().equals("Deluxe")) {
            newPizza = cpizza.createDeluxe();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
        if (chooseType.getValue().equals("BBQ Chicken")) {
            newPizza = cpizza.createBBQChicken();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
        if (chooseType.getValue().equals("Meatzza")) {
            newPizza = cpizza.createMeatzza();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
        if (chooseType.getValue().equals("Build your own")) {
            newPizza = cpizza.createBuildYourOwn();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
    }

    @FXML
    private void onAddToOrderClick() {
        if (getSizeFromToggleGroup()==null) {
            showAlert("Missing Argument", "Please select a size.");
        }
        pizzaArrayList.add(makePizza());
    }

    public static ArrayList<Pizza> getChicagoPizzas() {
        return pizzaArrayList;
    }

    @FXML
    private Size getSizeFromToggleGroup() {
        if (size.getSelectedToggle() != null) {
            Toggle selectedSize = size.getSelectedToggle();
            if (selectedSize == small) {
                return Size.SMALL;
            }
            else if (selectedSize == medium) {
                return Size.MEDIUM;
            }
            else if (selectedSize == large) {
                return Size.LARGE;
            }
        }
        return null;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null); // optional, can remove if you want a header
        alert.setContentText(message);
        alert.showAndWait();
    }
}