package com.example.plantmonitorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.atsign.client.api.AtClient;
import org.atsign.common.Keys;
import org.atsign.common.Keys.SharedKey;
import org.atsign.common.AtException;
import org.atsign.common.AtSign;
import org.atsign.common.KeyBuilders;

public class App extends Application {
 
 @Override
 public void start(Stage stage) {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();

   
  } catch(Exception e) {
   e.printStackTrace();
  }

 } 
 public static void main(String[] args) throws AtException {
  launch(args);
 }
}