package com.example.plantmonitorapp;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    public String tempData;
	public String humidData;
	public String soilData;
	public String lightData;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void displayName(String user, String monitor) {
        userSign = user;
		monitorSign = monitor;
		nameLabel.setText("Hello: " + user + "!\t\tConnected to: " + monitor);
	}
	@FXML
    void aboutClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));	
		root = loader.load();	
		AboutController aboutController = loader.getController();
		aboutController.displayName(userSign, monitorSign);
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
		alertsController.displayName(userSign, monitorSign);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void plantInfoClicked(ActionEvent event) {

    }

    @FXML
    void plantsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));	
		root = loader.load();	
		MainController mainController = loader.getController();
		mainController.displayName(userSign, monitorSign);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void resetClicked(ActionEvent event) {
        setSensorData();
        tempMeasurement.setText(tempData);
        humidMeasurement.setText(humidData);
        lightMeasurement.setText(lightData);
        soilMeasurement.setText(soilData);
    }

    // Dan and Cristian: implement setSensorData() to update the four 
    // sensor data variables with atSign-encrypted data. 
    void setSensorData() {
        tempData = "here's some data";  // Replace with real data
        humidData = "more data";        // Replace with real data
        lightData = "even more data";   // Replace with real data
        soilData = "yep, that's data";  // Replace with real data
        tempMeasurement.setText(tempData);
        humidMeasurement.setText(humidData);
        lightMeasurement.setText(lightData);
        soilMeasurement.setText(soilData);
    }
}