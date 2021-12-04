package com.codecool.controller;

import com.codecool.FxControllers.RootSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestAppController extends Application{


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/RootScene.fxml"));
        Parent root = (Parent) loader.load();
        RootSceneController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setLoader(loader);
        primaryStage.setTitle("sudoku solver");
        primaryStage.setScene(new Scene(root, 960, 600));
        primaryStage.show();
    }
}
