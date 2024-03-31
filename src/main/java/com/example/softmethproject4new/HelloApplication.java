package com.example.softmethproject4new;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Scene scene1;
    private Scene scene2;
    private Scene coffeeScene;
    private Scene sandwichScene;
    private Scene currentScene;
    private Scene ordersPlacedScene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene1 = new Scene(fxmlLoader1.load(), 700, 500);

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("donut-view.fxml"));
        scene2 = new Scene(fxmlLoader2.load(), 700, 500);

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("coffee-view.fxml"));
        coffeeScene = new Scene(fxmlLoader3.load(), 700, 500);

        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("sandwich-view.fxml"));
        sandwichScene = new Scene(fxmlLoader4.load(), 700, 500);

        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("current-order-view.fxml"));
        currentScene = new Scene(fxmlLoader5.load(), 700, 500);

        FXMLLoader fxmlLoader6 = new FXMLLoader(HelloApplication.class.getResource("all-orders-view.fxml"));
        ordersPlacedScene = new Scene(fxmlLoader6.load(), 700, 500);

        // Set the controller for the loaded FXML file and pass the scenes and stage to it
        HelloController controller = fxmlLoader1.getController();
        controller.initScenesAndStage(scene1, scene2, coffeeScene, sandwichScene, currentScene, ordersPlacedScene, stage);

        HelloController controller2 = fxmlLoader2.getController();
        controller2.initScenesAndStage(scene1, scene2, coffeeScene, sandwichScene, currentScene, ordersPlacedScene,stage);

        HelloController controller3 = fxmlLoader3.getController();
        controller3.initScenesAndStage(scene1, scene2, coffeeScene, sandwichScene, currentScene, ordersPlacedScene, stage);

        HelloController controller4 = fxmlLoader4.getController();
        controller4.initScenesAndStage(scene1, scene2, coffeeScene, sandwichScene, currentScene, ordersPlacedScene, stage);

        HelloController controller5 = fxmlLoader5.getController();
        controller5.initScenesAndStage(scene1, scene2, coffeeScene, sandwichScene, currentScene, ordersPlacedScene, stage);

        HelloController controller6 = fxmlLoader6.getController();
        controller6.initScenesAndStage(scene1, scene2, coffeeScene, sandwichScene, currentScene, ordersPlacedScene, stage);



        stage.setTitle("Hello!");
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}