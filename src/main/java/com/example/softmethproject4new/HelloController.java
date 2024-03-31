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

    // Method to initialize the scenes and stage
    public void initScenesAndStage(Scene scene1, Scene scene2, Stage primaryStage) {
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        // Change the welcome text
        welcomeText.setText("Welcome to JavaFX Application!");
        System.out.println("Button clicked!");

        // Switch scenes
        primaryStage.setScene(scene2);
    }
}
