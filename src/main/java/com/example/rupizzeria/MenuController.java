package com.example.rupizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The MenuController class handles the interactions and behaviors for the menu view in the RU Pizzeria application.
 * It manages the functionality for navigating back to the main page and controls the visibility of UI elements.
 * @author Nithya Konduru, Dhyanashri Raman
 */
public class MenuController implements Initializable {
    @FXML
    private ImageView homeButton;

    @FXML
    private Rectangle homeButtonRec;

    /**
     * Navigates back to the main page when the home button is clicked.
     * Loads the main page view from FXML and displays it in a new window.
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

    /**
     * Initializes the controller and sets up the event handlers for the home button.
     * Handles mouse hover events to show or hide the rectangle behind the home button.
     *
     * @param url The location of the FXML file.
     * @param resourceBundle The resources used for the UI.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButtonRec.setVisible(false);
        homeButton.setOnMouseEntered(event -> homeButtonRec.setVisible(true));
        homeButton.setOnMouseExited(event -> homeButtonRec.setVisible(false));
    }
}
