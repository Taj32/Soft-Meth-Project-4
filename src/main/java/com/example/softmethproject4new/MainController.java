package com.example.softmethproject4new;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
    private int value = 10;
    private Stage primaryStage;
    private Scene primaryScene;

    public MainController() {
    }

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    protected void displayView1() {
        Stage view1 = new Stage();
        BorderPane root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            root = (BorderPane)loader.load();
            Scene scene = new Scene(root, 500.0, 400.0);
            primaryStage.setScene(scene);
            View1Controller view1controller = (View1Controller)loader.getController();
            view1controller.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }

    }

    public int getValue() {
        return this.value;
    }
}
