/**
 * The AboutController class handles the functionality of the About scene in the Plant Monitor application.
 */
package com.example.plantmonitorapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.atsign.common.AtException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AboutController {
    // For moving to new scenes
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML Label nameLabel;              // Greeting in app header
    Plant selectedPlant;                // Ideal plant being compared to
    Plant realTimePlant;                // Current real-time plant
    ObservableList<Plant> allPlants;    // Storage of user's plants


    /**
     * Initializes the AboutController with the necessary data.
     *
     * @param allPlants     The list of all plants.
     * @param selectedPlant The selected plant to compare with real-time data.
     * @param realTimePlant The plant with real-time data.
     */
    public void initialize(ObservableList<Plant> allPlants, Plant selectedPlant, Plant realTimePlant) {
        this.allPlants = allPlants;
        this.selectedPlant = selectedPlant;
        this.realTimePlant = realTimePlant;
        nameLabel.setText("Hello @unpleasantwater!");
    }

    /**
     * Clicks on the current scene, nothing happens.
     * @param event The event representing the button click.
     */
    @FXML
    void aboutClicked(ActionEvent event) {
        // leave blank
    }

    /**
     * Handles the action when the "Alerts" button is clicked.
     *   - Loads next scene.
     *   - Initializes variables for next scene.
     *   - Displays the scene stage.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void alertsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertsScene.fxml"));
        root = loader.load();
        AlertsController alertsController = loader.getController();
        alertsController.initialize(allPlants, selectedPlant, realTimePlant);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the action when the "Plant Info" button is clicked.
     *   - Loads next scene.
     *   - Initializes variables for next scene.
     *   - Displays the scene stage.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     * @throws AtException if an AtSign error occurs.
     * @throws ExecutionException if an execution error occurs.
     * @throws InterruptedException if an interruption error occurs.
     */
    @FXML
    void plantInfoClicked(ActionEvent event) throws IOException, AtException, ExecutionException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlantInfoScene.fxml"));
        root = loader.load();
        PlantInfoController piController = loader.getController();
        piController.initialize(allPlants, selectedPlant, realTimePlant);
        piController.setSensorData();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the action when the "Plants" button is clicked.
     *   - Loads next scene.
     *   - Initializes variables for next scene.
     *   - Displays the scene stage.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void plantsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        root = loader.load();
        MainController mainController = loader.getController();
        mainController.initialize(allPlants, selectedPlant, realTimePlant);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}