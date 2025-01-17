package com.example.rupizzeria;

import com.example.rupizzeria.pizzaria.src.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for managing New York style pizza creation and ordering.
 * Provides functionality for selecting pizza type, size, toppings, and crust.
 * Handles interactions with the UI components such as buttons, text fields, and combo boxes.
 * @author Nithya Konduru, Dhyanashri Raman
 */
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
    private ComboBox<String> chooseType;

    @FXML
    private TextField crustTypeField;

    private static ArrayList<Topping> byoToppings = new ArrayList<>();

    private final String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build your own"};

    private ObservableList<String> availableToppingsList;

    private ObservableList<String> selectedToppingsList;

    private Pizza selectedPizza;

    @FXML
    private TextField price;

    private static ObservableList<Pizza> pizzaArrayList = FXCollections.observableArrayList();

    /**
     * Initializes the controller. Sets up event listeners and configures UI components.
     * This method is automatically called after the FXML file has been loaded.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
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
                } else {
                    selectedPizza.setToppings(byoToppings);
                    selectedToppingsList.clear();
                    byoToppings.clear();
                    selectedToppings.setItems(selectedToppingsList);
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

    @FXML
    private ImageView homeButton;

    @FXML
    private Rectangle homeButtonRec;

    /**
     * Handles the initialization of UI components with custom styles and animations.
     * Configures mouse hover effects for buttons and other interactive elements.
     */
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
        homeButtonRec.setVisible(false);
        homeButton.setOnMouseEntered(event -> homeButtonRec.setVisible(true));
        homeButton.setOnMouseExited(event -> homeButtonRec.setVisible(false));
        setPrice();
    }

    /**
     * Updates the price of the pizza based on the selected size.
     * Listens for changes in the size toggle group and recalculates the price accordingly.
     */
    private void setPrice() {
        size.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (selectedPizza != null) {
                selectedPizza.setSize(getSizeFromToggleGroup());
                if (selectedPizza.getSize() != null) {
                    price.setText(String.valueOf(selectedPizza.price()));
                }
            }
        });
    }

    /**
     * Populates the list of available toppings from the Topping enumeration.
     */
    private void setAvailableToppings() {
        for (Topping topping : Topping.values()) {
            availableToppingsList.add(topping.toString());
        }
    }

    /**
     * Updates the list of selected toppings based on the currently selected pizza.
     */
    private void setSelectedToppings() {
        selectedToppingsList.clear();
        if (selectedPizza != null) {
            for (Topping topping : selectedPizza.getToppings()) {
                selectedToppingsList.add(topping.toString());
            }
        }
    }

    /**
     * Handles the transition to the main page when the home button is clicked.
     * Loads the main page FXML and displays it in a new stage.
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

    @FXML
    private ImageView dynamicImage;

    /**
     * Creates and returns a Pizza object based on the user's selections.
     * Handles different pizza types and updates the UI accordingly.
     *
     * @return The newly created Pizza object, or null if no type is selected.
     */
    @FXML
    private Pizza makePizza() {
        Pizza newPizza = null;
        PizzaFactory nyPizza = new NYPizza();
        if (chooseType.getValue() != null) {
            updateCrustType();
            if (chooseType.getValue().equals("Deluxe")) {
                Image image = new Image("file:src/main/resources/images/nydeluxe.jpg");
                dynamicImage.setImage(image);
                return makePizzaHelper(newPizza, nyPizza);
            }
            if (chooseType.getValue().equals("BBQ Chicken")) {
                Image image = new Image("file:src/main/resources/images/nybbqchicken.jpg");
                dynamicImage.setImage(image);
                return makePizzaHelper(newPizza, nyPizza);
            }
            if (chooseType.getValue().equals("Meatzza")) {
                Image image = new Image("file:src/main/resources/images/nymeattza.jpg");
                dynamicImage.setImage(image);
                return makePizzaHelper(newPizza, nyPizza);
            }
            if (chooseType.getValue().equals("Build your own")) {
                Image image = new Image("file:src/main/resources/images/buildyourownpizza.png");
                dynamicImage.setImage(image);
                availableToppings.setDisable(false);
                selectedToppings.setDisable(false);
                leftArrowButton.setDisable(false);
                rightArrowButton.setDisable(false);
                newPizza = nyPizza.createBuildYourOwn();
                newPizza.setSize(getSizeFromToggleGroup());
                if (newPizza.getSize() != null) {
                    price.setText(String.valueOf(newPizza.price()));
                }
                newPizza.setToppings(new ArrayList<>(byoToppings));
                return newPizza;
            }
        }
        return newPizza;
    }

    /**
     * Creates and returns a pizza based on the selected type, size, and available NY-style options.
     * Adjusts UI elements such as toppings and arrow buttons based on the chosen pizza type.
     *
     * @param newPizza An existing Pizza object to be updated.
     * @param nypizza   A NYPizza object used to create specific pizza types.
     * @return The updated Pizza object reflecting the user's choices.
     */
    @FXML
    private Pizza makePizzaHelper(Pizza newPizza, PizzaFactory nypizza) {
        availableToppings.setDisable(true);
        selectedToppings.setDisable(false);
        leftArrowButton.setDisable(true);
        rightArrowButton.setDisable(true);
        if (chooseType.getValue().equals("Deluxe")) {
            newPizza = nypizza.createDeluxe();
        }
        if (chooseType.getValue().equals("BBQ Chicken")) {
            newPizza = nypizza.createBBQChicken();
        }
        if (chooseType.getValue().equals("Meatzza")) {
            newPizza = nypizza.createMeatzza();
        }
        newPizza.setSize(getSizeFromToggleGroup());
        if (newPizza.getSize() != null) {
            price.setText(String.valueOf(newPizza.price()));
        }
        newPizza.setSize(getSizeFromToggleGroup());
        if (newPizza.getSize() != null) {
            price.setText(String.valueOf(newPizza.price()));
        }
        return newPizza;
    }

    /**
     * Moves a topping from the available list to the selected list.
     * Disables additional selection if the maximum number of toppings is reached.
     */
    @FXML
    private void moveItem() {
        String selectedItem = availableToppings.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedToppings.getItems().size() == 7) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Too Many Toppings");
                alert.setContentText("You cannot select more than 7 toppings.");
                alert.showAndWait();
                availableToppings.setDisable(true);
                rightArrowButton.setDisable(true);
                return;
            }
            selectedToppings.getItems().add(selectedItem);
            byoToppings.add(Topping.stringToTopping(selectedItem));
            availableToppings.getItems().remove(selectedItem);
            availableToppings.getSelectionModel().clearSelection();
            addBYOToppingImages();
            if (selectedPizza != null) {
                selectedPizza.setSize(getSizeFromToggleGroup());
                if (selectedPizza.getSize() != null) {
                    price.setText(String.valueOf(selectedPizza.price()));
                }
            }
        }
    }

    @FXML
    private ImageView onionLayer;

    @FXML
    private ImageView mushroomLayer;

    @FXML
    private ImageView pepperoniLayer;

    @FXML
    private ImageView cheddarLayer;

    @FXML
    private ImageView provoloneLayer;

    @FXML
    private ImageView beefLayer;

    @FXML
    private ImageView hamLayer;

    @FXML
    private ImageView broccoliLayer;

    @FXML
    private ImageView spinachLayer;

    @FXML
    private ImageView jalapenoLayer;

    @FXML
    private ImageView greenpepperLayer;

    @FXML
    private ImageView sausageLayer;

    @FXML
    private ImageView bbqchickenLayer;

    /**
     * Adds images of selected toppings to the pizza visualization.
     * Updates the display with the appropriate images based on the current Build-Your-Own toppings.
     */
    private void addBYOToppingImages() {
        Image image = null;
        for (Topping topping: byoToppings) {
            switch (topping.toString().toUpperCase()) {
                case "SAUSAGE":
                    image = new Image("file:src/main/resources/images/sausageTopping.png");
                    sausageLayer.setImage(image);
                    break;
                case "GREENPEPPER":
                    image = new Image("file:src/main/resources/images/greenpepperTopping.png");
                    greenpepperLayer.setImage(image);
                    break;
                case "ONION":
                    image = new Image("file:src/main/resources/images/onionTopping.png");
                    onionLayer.setImage(image);
                    break;
                case "PEPPERONI":
                    image = new Image("file:src/main/resources/images/pepperoniTopping.png");
                    pepperoniLayer.setImage(image);
                    break;
                case "MUSHROOM":
                    image = new Image("file:src/main/resources/images/mushroomTopping.png");
                    mushroomLayer.setImage(image);
                    break;
                case "BBQCHICKEN":
                    image = new Image("file:src/main/resources/images/chickenTopping.png");
                    bbqchickenLayer.setImage(image);
                    break;
                case "PROVOLONE":
                    image = new Image("file:src/main/resources/images/provoloneTopping.png");
                    provoloneLayer.setImage(image);
                    break;
                case "CHEDDAR":
                    image = new Image("file:src/main/resources/images/cheddarTopping.png");
                    cheddarLayer.setImage(image);
                    break;
                case "BEEF":
                    image = new Image("file:src/main/resources/images/beefTopping.png");
                    beefLayer.setImage(image);
                    break;
                case "HAM":
                    image = new Image("file:src/main/resources/images/hamTopping.png");
                    hamLayer.setImage(image);
                    break;
                case "BROCCOLI":
                    image = new Image("file:src/main/resources/images/broccoliTopping.png");
                    broccoliLayer.setImage(image);
                    break;
                case "SPINACH":
                    image = new Image("file:src/main/resources/images/spinachTopping.png");
                    spinachLayer.setImage(image);
                    break;
                case "JALAPENO":
                    image = new Image("file:src/main/resources/images/jalapenoTopping.png");
                    jalapenoLayer.setImage(image);
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * Removes a topping from the selected list and moves it back to the available list.
     * Re-enables selection if the maximum number of toppings is no longer reached.
     */
    @FXML
    private void moveItemBack() {
        String selectedItem = selectedToppings.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            availableToppings.getItems().add(selectedItem);
            byoToppings.remove(Topping.stringToTopping(selectedItem));
            selectedToppings.getItems().remove(selectedItem);
            selectedToppings.getSelectionModel().clearSelection();
            removeBYOToppingImage(selectedItem);
            if (selectedToppings.getItems().size() < 7) {
                availableToppings.setDisable(false);
                rightArrowButton.setDisable(false);
            }
            if (selectedPizza != null) {
                selectedPizza.setSize(getSizeFromToggleGroup());
                if (selectedPizza.getSize() != null) {
                    price.setText(String.valueOf(selectedPizza.price()));
                }
            }
        }
    }

    /**
     * Removes a topping image from the pizza visualization.
     * Clears the image associated with the specified topping.
     *
     * @param selecteditem The name of the topping to remove.
     */
    private void removeBYOToppingImage(String selecteditem) {
        switch (selecteditem.toUpperCase()) {
            case "SAUSAGE":
                sausageLayer.setImage(null);
                break;
            case "GREENPEPPER":
                greenpepperLayer.setImage(null);
                break;
            case "ONION":
                onionLayer.setImage(null);
                break;
            case "PEPPERONI":
                pepperoniLayer.setImage(null);
                break;
            case "MUSHROOM":
                mushroomLayer.setImage(null);
                break;
            case "BBQCHICKEN":
                bbqchickenLayer.setImage(null);
                break;
            case "PROVOLONE":
                provoloneLayer.setImage(null);
                break;
            case "CHEDDAR":
                cheddarLayer.setImage(null);
                break;
            case "BEEF":
                beefLayer.setImage(null);
                break;
            case "HAM":
                hamLayer.setImage(null);
                break;
            case "BROCCOLI":
                broccoliLayer.setImage(null);
                break;
            case "SPINACH":
                spinachLayer.setImage(null);
                break;
            case "JALAPENO":
                jalapenoLayer.setImage(null);
                break;
            default:
                return;
        }
    }

    @FXML
    private ListView<String> availableToppings;

    @FXML
    private ListView<String> selectedToppings;

    /**
     * Updates the crust type displayed on the interface based on the selected pizza type.
     * Fetches the appropriate New York pizza object and adjusts the crust type field accordingly.
     */
    @FXML
    private void updateCrustType() {
        Pizza newPizza = null;
        PizzaFactory cpizza = new NYPizza();
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

    /**
     * Returns Observable list of New York Pizzas with Pizza type
     */
    public static ObservableList<Pizza> getNYPizzas() {
        return pizzaArrayList;
    }

    /**
     * Adds the currently selected pizza to the New York pizzas order list.
     * Validates the pizza size and type before adding and displays a confirmation message.
     */
    @FXML
    private void onAddToOrderClick() {
        boolean isValidOrder = true;
        if (getSizeFromToggleGroup() == null) {
            showAlert("Missing Argument", "Please select a size.");
            isValidOrder = false;
        }
        if (chooseType.getValue() == null) {
            showAlert("Missing Argument", "Please select a type from the dropdown.");
            isValidOrder = false;
        }
        if (isValidOrder) {
            Pizza pizza = makePizza();
            if (pizza != null) {
                CurrentOrdersController.getUnifiedPizzaList().add(pizza);
                price.setText(String.valueOf(pizza.price()));
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Order Confirmation");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Your " + getSizeFromToggleGroup().toString() + " " + chooseType.getValue() + " New York pizza has been successfully added to your order!");
                confirmationAlert.showAndWait();
            }
        }

    }

    /**
     * Retrieves the pizza size selected by the user from the toggle group.
     *
     * @return The selected size, or null if no size is selected.
     */
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

    /**
     * Displays an alert dialog with the specified title and message.
     *
     * @param title   The title of the alert dialog.
     * @param message The message to display in the alert dialog.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}