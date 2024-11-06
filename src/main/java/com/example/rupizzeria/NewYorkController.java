package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
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

    private final String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build your own"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseType.getItems().addAll(pizzaTypes);
    }
}
