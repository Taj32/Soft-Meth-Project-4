package com.example.softmethproject4new;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Scene scene1;
    private Scene scene2;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene1 = new Scene(fxmlLoader1.load(), 700, 500);

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("donut-view.fxml"));
        scene2 = new Scene(fxmlLoader2.load(), 700, 500);

        // Set the controller for the loaded FXML file and pass the scenes and stage to it
        HelloController controller = fxmlLoader1.getController();
        controller.initScenesAndStage(scene1, scene2, stage);

        stage.setTitle("Hello!");
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}