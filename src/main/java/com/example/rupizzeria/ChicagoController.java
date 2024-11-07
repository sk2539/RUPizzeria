package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.*;
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

    ArrayList<Pizza> pizzaArrayList = new ArrayList<>();
    
    private final String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build your own"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseType.getItems().addAll(pizzaTypes);
        crustTypeField.setEditable(false);
    }

    @FXML
    public Pizza makePizza() {
        Pizza newPizza = null;
        ChicagoPizza cpizza = new ChicagoPizza();
        if (getSizeFromToggleGroup()!=null) {
            if (chooseType.getValue().equals("Deluxe")) {
                newPizza = new Deluxe();
                newPizza.setSize(getSizeFromToggleGroup());
                newPizza = cpizza.createDeluxe();
                crustTypeField.setText(newPizza.getCrust().toString());
                return newPizza;
            }
            if (chooseType.getValue().equals("BBQ Chicken")) {
                newPizza = new BBQChicken();
                newPizza.setSize(getSizeFromToggleGroup());
                crustTypeField.setText(newPizza.getCrust().toString());
                return cpizza.createBBQChicken();
            }
            if (chooseType.getValue().equals("Meatzza")) {
                newPizza = new Meatzza();
                newPizza.setSize(getSizeFromToggleGroup());
                crustTypeField.setText(newPizza.getCrust().toString());
                return cpizza.createMeatzza();
            }
            if (chooseType.getValue().equals("Build your own")) {
                newPizza = new BuildYourOwn();
                newPizza.setSize(getSizeFromToggleGroup());
                crustTypeField.setText(newPizza.getCrust().toString());
                return cpizza.createBuildYourOwn();
            }
        }
        return newPizza;
    }

    @FXML
    public void onAddToOrderClick() {
        pizzaArrayList.add(makePizza());
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
        showAlert("Missing Argument", "Please select a size.");
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