package com.example.plantmonitorapp;
import java.io.IOException;

import com.example.plantmonitorapp.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	TextField userSignField;
	@FXML
	TextField monitorSignField;
	@FXML
	Label statusLabel;

	public String userSign;
	public String monitorSign;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void login(ActionEvent event) throws IOException {
		
		getSigns();
		if (areValidSigns() == true) {
			 // Dan and Cristian: make atSigns talk to each other here by
       		 // implementing attemptToConnect() and isConnected() however you want.
        	attemptToConnect();
			// IF they have made initial contact with each other (isConnected == true), 
			if (isConnected()) {
				// then display the next window with this function:
				statusLabel.setText("Connected!"); 
				goToMain(event); // Goes to main app screen
			}			
			// ELSE { } if the connection isn't made (isConnected == false), then display:
			else {
				statusLabel.setText("Did not connect, try again."); 
			}

		} 
		else {
			statusLabel.setText("Invalid sign(s). Make sure they start with \'@\'.");
		}
        
		
	}

	public void getSigns() {
		userSign = userSignField.getText();
		monitorSign = monitorSignField.getText();
	}

	public boolean areValidSigns() {
		/*
		if (userSign.length() <= 1 || monitorSign.length() <= 1) {
			return false;
		} else if (userSign.charAt(0) != '@' || monitorSign.charAt(0) != '@') {
				return false;
		} else {
			return true;
		}
		*/
		return true; // turned off for testing purposes
	}
	
	public boolean isConnected() {
		return true; 		// Dan and Cristian to implement
	}

	public void goToMain(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));	
		root = loader.load();	
		MainController scene2Controller = loader.getController();
		scene2Controller.displayName(userSign, monitorSign);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void attemptToConnect() {
		// Dan and Cristian to implement
	}
}