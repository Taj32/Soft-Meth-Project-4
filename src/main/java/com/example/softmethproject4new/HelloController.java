package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.List;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    private Scene scene1;
    private Scene scene2;
    private Stage primaryStage;
    private Scene coffeeScene;
    private Scene sandwichScene;
    private Scene cartScene;
    private Scene ordersPlacedScene;

    // Method to initialize the scenes and stage
    public void initScenesAndStage(Scene scene1, Scene scene2, Scene coffeeScene,
                                   Scene sandwichScene, Scene cartScene, Scene ordersPlacedScene,
                                   Stage primaryStage) {
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.coffeeScene = coffeeScene;
        this.primaryStage = primaryStage;
        this.sandwichScene = sandwichScene;
        this.cartScene = cartScene;
        this.ordersPlacedScene = ordersPlacedScene;
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
    protected void moveToCartScene() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(cartScene);
    }

    @FXML
    protected void moveToOrdersScene() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(ordersPlacedScene);
    }


    @FXML
    protected void moveToSandwichView() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(sandwichScene);
    }

    @FXML
    protected void backToMain() throws IOException {
        // Change the welcome text
        //welcomeText.setText("Welcome to JavaFX Application!");

        // Switch scenes
        primaryStage.setScene(scene1);
    }

    //Donut View
    @FXML
    private ObservableList<String> donutTypes;
    @FXML
    private ComboBox<String> cb_donutType;
//    @FXML
//    ListView<String> differentFlavors;

    public void initialize(){

//        donutTypes = FXCollections.observableArrayList("Yeast","Cake","Donut Holes");
//        cb_donutType.setItems(donutTypes);

        cb_donutType.getItems().addAll("Yeast","Cake","Donut Holes");
        //donutType.valueProperty().addListener((obs, oldVal, newVal) -> updateFlavors(newVal));
    }

    public void displaySelected(ActionEvent event){
        String selected = cb_donutType.getSelectionModel().getSelectedItem();

    }



}
