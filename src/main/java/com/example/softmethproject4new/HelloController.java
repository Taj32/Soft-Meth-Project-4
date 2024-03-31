package com.example.softmethproject4new;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    private Scene scene1;
    private Scene scene2;
    private Stage primaryStage;
    private Scene coffeeScene;

    // Method to initialize the scenes and stage
    public void initScenesAndStage(Scene scene1, Scene scene2, Scene coffeeScene, Stage primaryStage) {
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.coffeeScene = coffeeScene;
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(scene2);
    }

    @FXML
    protected void moveToCoffeeView() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(coffeeScene);
    }

    @FXML
    protected void backToMain() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(scene1);
    }
}
