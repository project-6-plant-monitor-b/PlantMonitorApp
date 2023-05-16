/**
 * The AlertController class handles the functionality of the Alerts scene in the Plant Monitor application.
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.atsign.common.AtException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AlertsController {

    // For moving to new scenes
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML Label nameLabel;                    // Greeting in app header
    Plant selectedPlant;                      // Ideal plant being compared to
    Plant realTimePlant;                      // Current real-time plant
    ObservableList<Plant> allPlants;          // Storage of user's plants
    @FXML private ListView<String> alertList; // List of real-time alerts to display

    /**
     * Initializes the AlertsController with the necessary data.
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
        addAlerts();
    }

    /**
     * Adds alerts based on the selected plant's data and real-time ranges.
     *   - Compares real-time data with the range the user set when creating the plant.
     *   - Gives alert for low/high/good depending on the state of the environment.
     *
     * The alerts are displayed in the alertList.
     */
    void addAlerts() {
        String[] selectedData = {selectedPlant.getTemp(),
                selectedPlant.getHumid(),
                selectedPlant.getLight(),
                selectedPlant.getSoil()};
        String[] variable = {"temp", "air humidity", "light", "soil humidity"};
        String[] ranges = realTimePlant.getRealTimeRanges();

        for (int i = 0; i < 4; i++) {
            if (selectedData[i].equals(ranges[i])) {
                alertList.getItems().add(realTimePlant.getName() + "'s " + variable[i] + " is in a good range.\n");
            } else if (selectedData[i].equals("low") && (ranges[i].equals("moderate") || ranges[i].equals("high"))) {
                alertList.getItems().add(realTimePlant.getName() + " needs lower " + variable[i] + ".\n");
            } else if (selectedData[i].equals("high") && (ranges[i].equals("moderate") || ranges[i].equals("low"))) {
                alertList.getItems().add(realTimePlant.getName() + " needs higher " + variable[i] + ".\n");
            } else if (selectedData[i].equals("moderate")) {
                if (ranges[i].equals("low")) {
                    alertList.getItems().add(realTimePlant.getName() + " needs higher " + variable[i] + ".\n");
                }
                if (ranges[i].equals("high")) {
                    alertList.getItems().add(realTimePlant.getName() + " needs lower " + variable[i] + ".\n");
                }
            }
        }
    }


    /**
     * Handles the action when the "Remove Alert" button is clicked.
     *
     * @param event The event representing the button click.
     */
    @FXML
    void removeAlert(ActionEvent event) {
        int selected = alertList.getSelectionModel().getSelectedIndex();
        alertList.getItems().remove(selected);
    }


    /**
     * Handles the action when the "About" button is clicked.
     *   - Loads next scene.
     *   - Initializes variables for next scene.
     *   - Displays the scene stage.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void aboutClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));
        root = loader.load();
        AboutController aboutController = loader.getController();
        aboutController.initialize(allPlants, selectedPlant, realTimePlant);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Clicks on the current scene, nothing happens.
     * @param event The event representing the button click.
     */
    @FXML
    void alertsClicked(ActionEvent event) {
        // leave blank
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

