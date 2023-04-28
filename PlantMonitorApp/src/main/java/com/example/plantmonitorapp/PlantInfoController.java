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
import org.atsign.common.AtException;
import org.atsign.common.AtSign;
import org.atsign.common.KeyBuilders;

public class PlantInfoController {

    @FXML public Label nameLabel;
    @FXML public Label humidMeasurement;
    @FXML public Label lightMeasurement;
    @FXML public Label soilMeasurement;
    @FXML public Label tempMeasurement;
    @FXML private Label humidAlert;
    @FXML private Label lightAlert;
    @FXML private Label soilAlert;
    @FXML private Label tempAlert;

    public String userSign;
    public String monitorSign;
    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<Plant> allPlants;
    Plant selectedPlant;
    Plant realTimePlant;


    public void initialize(ObservableList<Plant> allPlants, Plant selectedPlant, Plant realTimePlant) throws AtException, ExecutionException, InterruptedException {
        this.allPlants = allPlants;
        this.selectedPlant = selectedPlant;
        if (realTimePlant == null) {
            setSensorData();
        } else {
            this.realTimePlant = realTimePlant;
        }
        nameLabel.setText("Hello @unpleasantwater!  |  Connected to: @hilariousbaboon");
    }

    void setSensorData() throws AtException, ExecutionException, InterruptedException {
        AtSign esp32 = new AtSign("@hilariousbaboon");
        AtSign java = new AtSign("@unpleasantwater");
        AtClient atClient = AtClient.withRemoteSecondary("root.atsign.org:64", java,  false);
        Keys.SharedKey soilKey = new KeyBuilders.SharedKeyBuilder(esp32, java).key("soil").build();
        Keys.SharedKey tempKey = new KeyBuilders.SharedKeyBuilder(esp32, java).key("temp").build();
        Keys.SharedKey humidKey = new KeyBuilders.SharedKeyBuilder(esp32, java).key("humid").build();
        Keys.SharedKey lightKey = new KeyBuilders.SharedKeyBuilder(esp32, java).key("light").build();
        String soilValue = atClient.get(soilKey).get();
        String tempValue = atClient.get(tempKey).get();
        String humidValue = atClient.get(humidKey).get();
        String lightValue = atClient.get(lightKey).get();
        realTimePlant = new Plant();
        realTimePlant.setName(selectedPlant.getName());
        realTimePlant.setTemp(Double.parseDouble(tempValue) * ((double) 9 / 5) + 32);
        realTimePlant.setHumid(Double.parseDouble(humidValue));
        realTimePlant.setLight(Double.parseDouble(lightValue));
        realTimePlant.setSoil(Double.parseDouble(soilValue));
        tempMeasurement.setText(String.valueOf(realTimePlant.getTemp()));
        humidMeasurement.setText(String.valueOf(realTimePlant.getHumid()));
        lightMeasurement.setText(String.valueOf(realTimePlant.getLight()));
        soilMeasurement.setText(String.valueOf(realTimePlant.getSoil()));

        double[] selectedData = {selectedPlant.getTemp(),
                selectedPlant.getHumid(),
                selectedPlant.getLight(),
                selectedPlant.getSoil()};
        double[] realTimeData = {realTimePlant.getTemp(),
                realTimePlant.getHumid(),
                realTimePlant.getLight(),
                realTimePlant.getSoil()};
        String[] variable = {"temp", "air humidity", "light", "soil humidity"};
        String[] alerts = new String[4];
        for (int i = 0; i < 4; i++) {
            if (selectedData[i] > realTimeData[i] * 1.25) {
                alerts[i] = (realTimePlant.getName() + " needs lower " + variable[i] + ".\n");
            } else if (selectedData[i] < realTimeData[i] * .75){
                alerts[i] = (realTimePlant.getName() + " needs higher " + variable[i] + ".\n");
            } else {
                alerts[i] = (realTimePlant.getName() + "'s " + variable[i] + " is good.\n");
            }
        }

        tempAlert.setText(alerts[0]);
        humidAlert.setText(alerts[1]);
        lightAlert.setText(alerts[2]);
        soilAlert.setText(alerts[3]);

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
    void alertsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertsScene.fxml"));
        root = loader.load();
        AlertsController alertsController = loader.getController();
        alertsController.initialize(allPlants, selectedPlant, realTimePlant);
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
        mainController.initialize(allPlants, selectedPlant,realTimePlant);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void resetClicked(ActionEvent event) throws AtException, ExecutionException, InterruptedException {
        setSensorData();
    }



}