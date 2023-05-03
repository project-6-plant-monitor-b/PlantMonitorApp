package com.example.plantmonitorapp;

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
import org.atsign.common.AtException;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;


public class MainController implements Initializable {

	@FXML Label nameLabel;
	@FXML public TableView<Plant> plantTable;

	//Columns
	@FXML public TableColumn<Plant, String> nameColumn;
	@FXML public TableColumn<Plant, Double> tempColumn;
	@FXML public TableColumn<Plant, Double> humidColumn;
	@FXML public TableColumn<Plant, Double> lightColumn;
	@FXML public TableColumn<Plant, Double> soilColumn;

	//Text input
	@FXML public TextField nameInput;
	@FXML public TextField tempInput;
	@FXML public TextField humidInput;
	@FXML public TextField lightInput;
	@FXML public TextField soilInput;
	@FXML public TextField atSignInput;

	public String userSign;
	public String monitorSign;
	public Stage stage;
	public Scene scene;
	public Parent root;
	ObservableList<Plant> allPlants;
	Plant selectedPlant;
	Plant realTimePlant;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameLabel.setText("Hello @unpleasantwater!");
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
						plantInfo[1],
						Double.parseDouble(plantInfo[2]),
						Double.parseDouble(plantInfo[3]),
						Double.parseDouble(plantInfo[4]),
						Double.parseDouble(plantInfo[5]));
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
		Plant plant = new Plant(atSignInput.getText(),
				nameInput.getText(),
				Double.parseDouble(tempInput.getText()),
				Double.parseDouble(humidInput.getText()),
				Double.parseDouble(lightInput.getText()),
				Double.parseDouble(soilInput.getText()));
		allPlants = plantTable.getItems();
		allPlants.add(plant);
		plantTable.setItems(allPlants);
		atSignInput.clear();
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
			out.print(thisPlant.getAtSign() + "," + thisPlant.getName() + "," + thisPlant.getTemp() + ","
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
			out.print(thisPlant.getAtSign() + "," + thisPlant.getName() + "," + thisPlant.getTemp() + ","
					+ thisPlant.getHumid() + "," + thisPlant.getLight() + ","
					+ thisPlant.getSoil() + "\n");
		}
		out.close();
	}

	public void initialize(String user, String monitor) {
		userSign = user;
		monitorSign = monitor;
		nameLabel.setText("Hello @unpleasantwater!");
	}


	public void initialize(ObservableList<Plant> allPlants, Plant selectedPlant, Plant realTimePlant) {
		this.allPlants = allPlants;
		this.selectedPlant = selectedPlant;
		this.realTimePlant = realTimePlant;
		nameLabel.setText("Hello @unpleasantwater!");
	}

	public void setSelectedPlant() {
		selectedPlant = plantTable.getSelectionModel().getSelectedItem();
	}

	@FXML
    void aboutClicked(ActionEvent event) throws IOException {
		setSelectedPlant();
		if (selectedPlant != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutScene.fxml"));
			root = loader.load();
			AboutController aboutController = loader.getController();
			aboutController.initialize(allPlants, selectedPlant,realTimePlant);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
    }

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

    @FXML
    void plantsClicked(ActionEvent event) {
		// leave blank
    }
}