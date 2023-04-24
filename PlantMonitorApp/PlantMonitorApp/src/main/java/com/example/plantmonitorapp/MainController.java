package com.example.plantmonitorapp;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MainController implements Initializable {

	@FXML Label nameLabel;
	@FXML private TableView<Plant> plantTable;

	//Columns
	@FXML private TableColumn<Plant, String> nameColumn;
	@FXML private TableColumn<Plant, Double> tempColumn;
	@FXML private TableColumn<Plant, Double> humidColumn;
	@FXML private TableColumn<Plant, Double> lightColumn;
	@FXML private TableColumn<Plant, Double> soilColumn;

	//Text input
	@FXML private TextField nameInput;
	@FXML private TextField tempInput;
	@FXML private TextField humidInput;
	@FXML private TextField lightInput;
	@FXML private TextField soilInput;

	public String userSign;
	public String monitorSign;
	private Stage stage;
	private Scene scene;
	private Parent root;
	ObservableList<Plant> allPlants;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("name"));
		tempColumn.setCellValueFactory(new PropertyValueFactory<Plant, Double>("temp"));
		humidColumn.setCellValueFactory(new PropertyValueFactory<Plant, Double>("humid"));
		lightColumn.setCellValueFactory(new PropertyValueFactory<Plant, Double>("light"));
		soilColumn.setCellValueFactory(new PropertyValueFactory<Plant, Double>("soil"));
		allPlants = plantTable.getItems();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("plants.txt"));
			String line = reader.readLine();

			while (line != null) {
				String[] plantInfo = line.split(",");
				Plant plant = new Plant(plantInfo[0],
						Double.parseDouble(plantInfo[1]),
						Double.parseDouble(plantInfo[2]),
						Double.parseDouble(plantInfo[2]),
						Double.parseDouble(plantInfo[2]));
				allPlants.add(plant);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void addPlant(ActionEvent event) throws IOException {
		Plant plant = new Plant(nameInput.getText(),
				Double.parseDouble(tempInput.getText()),
				Double.parseDouble(humidInput.getText()),
				Double.parseDouble(lightInput.getText()),
				Double.parseDouble(soilInput.getText()));
		allPlants = plantTable.getItems();
		allPlants.add(plant);
		plantTable.setItems(allPlants);
		nameInput.clear();
		tempInput.clear();
		humidInput.clear();
		lightInput.clear();
		soilInput.clear();

		// Writing to a file for storage
		FileWriter fw = new FileWriter("plants.txt",false);
		PrintWriter out = new PrintWriter(fw);
		Iterator<Plant> plantIterator = allPlants.iterator();
		while (plantIterator.hasNext()) {
			Plant thisPlant = plantIterator.next();
			out.print(thisPlant.getName() + "," + thisPlant.getTemp() + ","
			         + thisPlant.getHumid() + "," + thisPlant.getLight() + ","
			         + thisPlant.getSoil() + "\n");
		}
		out.close();

	}

	@FXML
	void removePlant(ActionEvent event) throws IOException {
		int selectedID = plantTable.getSelectionModel().getSelectedIndex();
		plantTable.getItems().remove(selectedID);
		// Writing to a file for storage
		FileWriter fw = new FileWriter("plants.txt",false);
		PrintWriter out = new PrintWriter(fw);
		Iterator<Plant> plantIterator = allPlants.iterator();
		while (plantIterator.hasNext()) {
			Plant thisPlant = plantIterator.next();
			out.print(thisPlant.getName() + "," + thisPlant.getTemp() + ","
					+ thisPlant.getHumid() + "," + thisPlant.getLight() + ","
					+ thisPlant.getSoil() + "\n");
		}
		out.close();
	}

	public void displayName(String user, String monitor) {
		userSign = user;
		monitorSign = monitor;
		nameLabel.setText("Hello: " + userSign + "\t\tConnected to: " + monitorSign);
	}


	public void displayName(String user, String monitor, ObservableList<Plant> allPlants) {
		userSign = user;
		monitorSign = monitor;
		this.allPlants = allPlants;
		plantTable.setItems(allPlants);
		nameLabel.setText("Hello: " + user + "!\t\tConnected to: " + monitor);
	}

	@FXML
    void aboutClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));	
		root = loader.load();	
		AboutController aboutController = loader.getController();
		aboutController.displayName(userSign, monitorSign, allPlants);
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
    void plantsClicked(ActionEvent event) {
		// leave blank
    }



}