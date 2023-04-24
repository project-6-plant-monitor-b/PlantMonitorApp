package com.example.plantmonitorapp;
import java.io.IOException;

import com.example.plantmonitorapp.AlertsController;
import com.example.plantmonitorapp.MainController;
import com.example.plantmonitorapp.PlantInfoController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AboutController {

	@FXML
	Label nameLabel;
    public String userSign;
	public String monitorSign;
	private Stage stage;
	private Scene scene;
	private Parent root;
	ObservableList<Plant> allPlants;



	public void displayName(String user, String monitor, ObservableList<Plant> allPlants) {
		userSign = user;
		monitorSign = monitor;
		this.allPlants = allPlants;
		nameLabel.setText("Hello: " + user + "!\t\tConnected to: " + monitor);
	}

	@FXML
    void aboutClicked(ActionEvent event) {

    }

    @FXML
    void alertsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertsScene.fxml"));	
		root = loader.load();	
		AlertsController alertsController = loader.getController();
		alertsController.displayName(userSign, monitorSign, allPlants);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void plantInfoClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlantInfoScene.fxml"));	
		root = loader.load();	
		PlantInfoController piController = loader.getController();
		piController.displayName(userSign, monitorSign, allPlants);
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
		mainController.displayName(userSign, monitorSign, allPlants);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
	
}