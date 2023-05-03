package com.example.plantmonitorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.atsign.common.AtException;
import javafx.scene.image.Image;


public class App extends Application {

 @Override
 public void start(Stage stage) {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Plant Monitor B");
   stage.getIcons().add(
           new Image("https://www.nicepng.com/png/detail/334-3345790_rolina-doniczkowa-icon-potted-plant-icon-png.png"));
   stage.show();

  } catch(Exception e) {
   e.printStackTrace();
  }

 } 
 public static void main(String[] args) throws AtException {
  launch(args);
 }
}