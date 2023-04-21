package com.example.plantmonitorapp;
import java.io.IOException;

import com.example.plantmonitorapp.PlantInfoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

	@FXML
	Label nameLabel;

	public String userSign;
	public String monitorSign;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void displayName(String user, String monitor) {
		userSign = user;
		monitorSign = monitor;
		nameLabel.setText("Hello: " + userSign + "!\t\tConnected to: " + monitorSign);
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
    void plantInfoClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PlantInfoScene.fxml"));	
		root = loader.load();	
		PlantInfoController piController = loader.getController();
		piController.displayName(userSign, monitorSign);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void plantsClicked(ActionEvent event) {

    }
	
}