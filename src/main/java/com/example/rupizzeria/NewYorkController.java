package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewYorkController implements Initializable {
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

    private final String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build your own"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseType.getItems().addAll(pizzaTypes);
        crustTypeField.setEditable(false);
        chooseType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Pizza selectedPizza = makePizza();
            if (selectedPizza != null) {
                updateCrustType();
            }
        });
    }

    @FXML
    public Pizza makePizza() {
        Pizza newPizza = null;
        NYPizza nypizza = new NYPizza();
        if (chooseType.getValue()!=null) {
            updateCrustType();
        }
        if (getSizeFromToggleGroup()!=null) {
            if (chooseType.getValue().equals("Deluxe")) {
                newPizza = nypizza.createDeluxe();
                newPizza.setSize(getSizeFromToggleGroup());
                return newPizza;
            }
            if (chooseType.getValue().equals("BBQ Chicken")) {
                newPizza = nypizza.createBBQChicken();
                newPizza.setSize(getSizeFromToggleGroup());
                return newPizza;
            }
            if (chooseType.getValue().equals("Meatzza")) {
                newPizza = nypizza.createMeatzza();
                newPizza.setSize(getSizeFromToggleGroup());
                return newPizza;
            }
            if (chooseType.getValue().equals("Build your own")) {
                newPizza = nypizza.createBuildYourOwn();
                newPizza.setSize(getSizeFromToggleGroup());
                return newPizza;
            }
        }
        return newPizza;
    }

    @FXML
    private void updateCrustType() {
        Pizza newPizza = null;
        NYPizza nypizza = new NYPizza();
        if (chooseType.getValue().equals("Deluxe")) {
            newPizza = nypizza.createDeluxe();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
        if (chooseType.getValue().equals("BBQ Chicken")) {
            newPizza = nypizza.createBBQChicken();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
        if (chooseType.getValue().equals("Meatzza")) {
            newPizza = nypizza.createMeatzza();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
        if (chooseType.getValue().equals("Build your own")) {
            newPizza = nypizza.createBuildYourOwn();
            crustTypeField.setText(newPizza.getCrust().toString());
        }
    }

    @FXML
    public void onAddToOrderClick() {
        if (getSizeFromToggleGroup()==null) {
            showAlert("Missing Argument", "Please select a size.");
        }
        pizzaArrayList.add(makePizza());
    }

    public static ArrayList<Pizza> getNYPizzas() {
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
