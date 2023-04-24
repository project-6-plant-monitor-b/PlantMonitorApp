package com.example.plantmonitorapp;
import java.io.IOException;

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



	public void displayName(String user, String monitor, ObservableList<Plant> allPlants, Plant selectedPlant, Plant realTimePlant) {
		userSign = user;
		monitorSign = monitor;
		this.allPlants = allPlants;
		this.selectedPlant = selectedPlant;
		this.realTimePlant = realTimePlant;
		nameLabel.setText("Hello: " + user + "!\t\tConnected to: " + monitor);
		//System.out.println("Real time plant: " + this.realTimePlant.getName());
		//System.out.println("Selected plant: " + this.selectedPlant.getName());
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
		aboutController.displayName(userSign, monitorSign, allPlants, selectedPlant,realTimePlant);
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
    void plantInfoClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlantInfoScene.fxml"));
		root = loader.load();
		PlantInfoController piController = loader.getController();
		piController.displayName(userSign, monitorSign, allPlants, selectedPlant,realTimePlant);
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
		mainController.displayName(userSign, monitorSign, allPlants, selectedPlant,realTimePlant);
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
			if (selectedData[i] > realTimeData[i]) {
				alertList.getItems().add(realTimePlant.getName() + " needs lower " + variable[i] + ".\n");
			} else {
				alertList.getItems().add(realTimePlant.getName() + " needs higher " + variable[i] + ".\n");
			}
		}
	}
}