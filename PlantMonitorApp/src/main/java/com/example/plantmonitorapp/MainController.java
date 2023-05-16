/**
 * The MainController class is the controller for the main scene of the Plant Monitor application.
 * It handles user interactions and manages the display and manipulation of plant data.
 */
package com.example.plantmonitorapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.atsign.common.AtException;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;


public class MainController {

    // For moving to new scenes
    public Stage stage;
    public Scene scene;
    public Parent root;

    @FXML Label nameLabel;              // Greeting in app header
    Plant selectedPlant;                // Ideal plant being compared to
    Plant realTimePlant;                // Current real-time plant
    ObservableList<Plant> allPlants;    // Storage of user's plants

    // Table of user's plants
    @FXML public TableView<Plant> plantTable;
    // Table columns
    @FXML public TableColumn<Plant, String> nameColumn;
    @FXML public TableColumn<Plant, String> tempColumn;
    @FXML public TableColumn<Plant, String> humidColumn;
    @FXML public TableColumn<Plant, String> lightColumn;
    @FXML public TableColumn<Plant, String> soilColumn;
    //Selection choices for new plant
    @FXML public TextField nameInput;
    @FXML public TextField atSignInput;
    @FXML private ChoiceBox<String> tempChoiceBox;
    @FXML private ChoiceBox<String> humidChoiceBox;
    @FXML private ChoiceBox<String> lightChoiceBox;
    @FXML private ChoiceBox<String> soilChoiceBox;


    /**
     * Initializes the controller.
     *   - Sets header greeting
     *   - Sets table column labels
     *   - Sets new plant options
     *   - Loads user plants
     */
    public void initialize() {
        nameLabel.setText("Hello @unpleasantwater!");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("name"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("temp"));
        humidColumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("humid"));
        lightColumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("light"));
        soilColumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("soil"));
        allPlants = plantTable.getItems();

        String[] options = {"low", "moderate", "high"};
        tempChoiceBox.getItems().addAll(options);
        humidChoiceBox.getItems().addAll(options);
        lightChoiceBox.getItems().addAll(options);
        soilChoiceBox.getItems().addAll(options);

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("plants.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] plantInfo = line.split(",");
                Plant plant = new Plant(plantInfo[0], plantInfo[1], plantInfo[2], plantInfo[3], plantInfo[4], plantInfo[5]);
                allPlants.add(plant);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds a new plant to the user's list.
     *   - Gets values from user for AtSign, name, and environmental variables.
     *   - Updates table to include new plant.
     *   - Stores data in a .txt with the other plants.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void addPlant(ActionEvent event) throws IOException {
        Plant plant = new Plant(atSignInput.getText(), nameInput.getText(), tempChoiceBox.getValue(), humidChoiceBox.getValue(), lightChoiceBox.getValue(), soilChoiceBox.getValue());
        allPlants = plantTable.getItems();
        allPlants.add(plant);
        plantTable.setItems(allPlants);
        atSignInput.clear();
        nameInput.clear();
        tempChoiceBox.setValue(null);
        humidChoiceBox.setValue(null);
        lightChoiceBox.setValue(null);
        soilChoiceBox.setValue(null);

        // Writing to a file for storage
        FileWriter fw = new FileWriter("plants.txt", false);
        PrintWriter out = new PrintWriter(fw);
        Iterator<Plant> plantIterator = allPlants.iterator();
        while (plantIterator.hasNext()) {
            Plant thisPlant = plantIterator.next();
            out.print(thisPlant.getAtSign() + "," + thisPlant.getName() + "," + thisPlant.getTemp() + "," + thisPlant.getHumid() + "," + thisPlant.getLight() + "," + thisPlant.getSoil() + "\n");
        }
        out.close();

    }

    /**
     * Removes selected plant from table.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void removePlant(ActionEvent event) throws IOException {
        int selectedID = plantTable.getSelectionModel().getSelectedIndex();
        plantTable.getItems().remove(selectedID);
        // Writing to a file for storage
        FileWriter fw = new FileWriter("plants.txt", false);
        PrintWriter out = new PrintWriter(fw);
        Iterator<Plant> plantIterator = allPlants.iterator();
        while (plantIterator.hasNext()) {
            Plant thisPlant = plantIterator.next();
            out.print(thisPlant.getAtSign() + "," + thisPlant.getName() + "," + thisPlant.getTemp() + "," + thisPlant.getHumid() + "," + thisPlant.getLight() + "," + thisPlant.getSoil() + "\n");
        }
        out.close();
    }


    /**
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
     * Selects plant for which the user can compare real-time data with.
     */
    public void setSelectedPlant() {
        selectedPlant = plantTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Handles the action when the "About" button is clicked.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void aboutClicked(ActionEvent event) throws IOException {
        setSelectedPlant();
        if (selectedPlant != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));
            root = loader.load();
            AboutController aboutController = loader.getController();
            aboutController.initialize(allPlants, selectedPlant, realTimePlant);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Handles the action when the "Alerts" button is clicked.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void alertsClicked(ActionEvent event) throws IOException {
        setSelectedPlant();
        if (selectedPlant != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertsScene.fxml"));
            root = loader.load();
            AlertsController alertsController = loader.getController();
            alertsController.initialize(allPlants, selectedPlant, realTimePlant);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Handles the action when the "Plant Info" button is clicked.
     *
     * @param event The event representing the button click.
     * @throws IOException if an I/O error occurs.
     * @throws AtException if an AtSign error occurs.
     * @throws ExecutionException if an execution error occurs.
     * @throws InterruptedException if an interruption error occurs.
     */
    @FXML
    void plantInfoClicked(ActionEvent event) throws IOException, AtException, ExecutionException, InterruptedException {
        setSelectedPlant();
        if (selectedPlant != null) {
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
    }

    /**
     * Clicks on the current scene, nothing happens.
     * @param event The event representing the button click.
     */
    @FXML
    void plantsClicked(ActionEvent event) {
        // leave blank
    }
}