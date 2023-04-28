package com.example.plantmonitorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.atsign.common.AtException;

public class App extends Application {
 
 @Override
 public void start(Stage stage) {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
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