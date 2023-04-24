package com.example.plantmonitorapp;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.atsign.client.api.AtClient;
import org.atsign.common.Keys;
import org.atsign.common.Keys.SharedKey;
import org.atsign.common.AtException;
import org.atsign.common.AtSign;
import org.atsign.common.KeyBuilders;

public class PlantInfoController {

	@FXML
	public Label nameLabel;
    @FXML
    public Label humidMeasurement;
    @FXML
    public Label lightMeasurement;
    @FXML
    public Label soilMeasurement;
    @FXML
    public Label tempMeasurement;

    public String userSign;
	public String monitorSign;
	private Stage stage;
	private Scene scene;
	private Parent root;

    ObservableList<Plant> allPlants;
    Plant selectedPlant;
    Plant realTimePlant;


    public void displayName(String user, String monitor, ObservableList<Plant> allPlants, Plant selectedPlant, Plant realTimePlant) throws AtException, ExecutionException, InterruptedException {
        userSign = user;
        monitorSign = monitor;
        this.allPlants = allPlants;
        this.selectedPlant = selectedPlant;
        if (realTimePlant == null) {
            setSensorData();
        } else {
            this.realTimePlant = realTimePlant;
        }
        nameLabel.setText("Hello: " + user + "!\t\tConnected to: " + monitor);
    }


	@FXML
    void aboutClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));	
		root = loader.load();	
		AboutController aboutController = loader.getController();
		aboutController.displayName(userSign, monitorSign, allPlants, selectedPlant,realTimePlant);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void alertsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertsScene.fxml"));	
		root = loader.load();	
		AlertsController alertsController = loader.getController();
		alertsController.displayName(userSign, monitorSign, allPlants, selectedPlant, realTimePlant);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void plantInfoClicked(ActionEvent event) {
        // leave blank
    }

    @FXML
    void plantsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));	
		root = loader.load();	
		MainController mainController = loader.getController();
		mainController.displayName(userSign, monitorSign, allPlants, selectedPlant,realTimePlant);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void resetClicked(ActionEvent event) throws AtException, ExecutionException, InterruptedException {
        setSensorData();
        tempMeasurement.setText(String.valueOf(realTimePlant.getTemp()));
        humidMeasurement.setText(String.valueOf(realTimePlant.getHumid()));
        lightMeasurement.setText(String.valueOf(realTimePlant.getLight()));
        soilMeasurement.setText(String.valueOf(realTimePlant.getSoil()));
    }

    // Dan and Cristian: implement setSensorData() to update the four 
    // sensor data variables with atSign-encrypted data. 
    void setSensorData() throws AtException, ExecutionException, InterruptedException {
        AtSign esp32 = new AtSign("@hilariousbaboon");
        AtSign java = new AtSign("@unpleasantwater");
        AtClient atClient = AtClient.withRemoteSecondary("root.atsign.org:64", java,  false);
        Keys.SharedKey sharedKey1 = new KeyBuilders.SharedKeyBuilder(esp32, java).key("test").build();
        String value = atClient.get(sharedKey1).get();
        System.out.println("Value is: " + value);
        realTimePlant = new Plant();
        realTimePlant.setName(selectedPlant.getName());
        realTimePlant.setTemp(24.26);   // Replace with real data
        realTimePlant.setHumid(22.22);  // Replace with real data
        realTimePlant.setLight(104);    // Replace with real data
        realTimePlant.setSoil(Double.parseDouble(value));   // Replace with real data
    }
}