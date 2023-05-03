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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.atsign.common.AtException;

public class AlertsController {

	@FXML
	Label nameLabel;
	@FXML
	private ListView<String> alertList;
    public String userSign;
	public String monitorSign;
	private Stage stage;
	private Scene scene;
	private Parent root;
	ObservableList<Plant> allPlants;
	Plant selectedPlant;
	Plant realTimePlant;



	public void initialize(ObservableList<Plant> allPlants, Plant selectedPlant, Plant realTimePlant) {
		this.allPlants = allPlants;
		this.selectedPlant = selectedPlant;
		this.realTimePlant = realTimePlant;
		nameLabel.setText("Hello @unpleasantwater!");
		addAlerts();
	}




	@FXML
	void removeAlert(ActionEvent event) {
		int selected = alertList.getSelectionModel().getSelectedIndex();
		alertList.getItems().remove(selected);
	}


	@FXML
    void aboutClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));
		root = loader.load();
		AboutController aboutController = loader.getController();
		aboutController.initialize(allPlants, selectedPlant,realTimePlant);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void alertsClicked(ActionEvent event) {
		// leave blank
    }

    @FXML
    void plantInfoClicked(ActionEvent event) throws IOException, AtException, ExecutionException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlantInfoScene.fxml"));
		root = loader.load();
		PlantInfoController piController = loader.getController();
		piController.initialize(allPlants, selectedPlant,realTimePlant);
		piController.setSensorData();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void plantsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
		root = loader.load();
		MainController mainController = loader.getController();
		mainController.initialize(allPlants, selectedPlant,realTimePlant);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	void addAlerts() {
		double[] selectedData = {selectedPlant.getTemp(),
				selectedPlant.getHumid(),
				selectedPlant.getLight(),
				selectedPlant.getSoil()};
		double[] realTimeData = {realTimePlant.getTemp(),
				realTimePlant.getHumid(),
				realTimePlant.getLight(),
				realTimePlant.getSoil()};
		String[] variable = {"temp", "air humidity", "light", "soil humidity"};

		for (int i = 0; i < 4; i++) {
			if (selectedData[i] > realTimeData[i] * 1.25) {
				alertList.getItems().add(realTimePlant.getName() + " needs lower " + variable[i] + ".\n");
			} else if (selectedData[i] < realTimeData[i] * .75){
				alertList.getItems().add(realTimePlant.getName() + " needs higher " + variable[i] + ".\n");
			} else {
				alertList.getItems().add(realTimePlant.getName() + "'s " + variable[i] + " is good.\n");
			}
		}
	}
}