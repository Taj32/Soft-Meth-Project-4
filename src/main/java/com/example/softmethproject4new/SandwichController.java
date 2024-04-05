package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SandwichController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ArrayList<String> addOns;
    private Sandwich currentSandwich;


    @FXML
    private ToggleGroup proteinType;

    @FXML
    private ToggleGroup breadType;

    @FXML
    private TextArea sandwichPrice;

    public void initialize() {
//        System.out.println("starting up!");
        addOns = new ArrayList<>();
//        sandwichOrders.setItems(orderList);
    }

    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
//        System.out.println(mainController.getValue());
        mainController.setValue();
//        System.out.println(mainController.getValue());

    }



    @FXML
    public void sandwichOrder(ActionEvent event) {
        RadioButton bread = (RadioButton) breadType.getSelectedToggle();
        RadioButton protein = (RadioButton) proteinType.getSelectedToggle();


        if(protein != null) {
//            System.out.println("check");
            currentSandwich = new Sandwich(bread.getText(), protein.getText(), addOns);
            updateSandwichTotal();
        }

//
    }

    private void updateSandwichTotal() {
        sandwichPrice.setText(String.format("$%.2f",currentSandwich.price()));
    }

    @FXML
    public void changeAddOns(ActionEvent event) {
        RadioButton bread = (RadioButton) breadType.getSelectedToggle();
        RadioButton protein = (RadioButton) proteinType.getSelectedToggle();
        CheckBox currentCheckBox = (CheckBox) event.getSource();
        if(!addOns.contains(currentCheckBox.getText())) {
            addOns.add(currentCheckBox.getText());
//            System.out.println("selecting..");
        }
        else {

            addOns.remove(currentCheckBox.getText());
//            System.out.println("deselecting...");
        }

        currentSandwich = new Sandwich(bread.getText(), protein.getText(), addOns);
        updateSandwichTotal();

    }

    @FXML
    private Button button1;
    @FXML
    private Button button2;

    @FXML
    private void addSandwich() {
        RadioButton bread = (RadioButton) breadType.getSelectedToggle();
        RadioButton protein = (RadioButton) proteinType.getSelectedToggle();
        if(currentSandwich != null) {
            ArrayList<String> currentAddOns = new ArrayList<>(addOns);

            Sandwich newSandwich = new Sandwich(bread.getText(), protein.getText(), currentAddOns);
            mainController.addToCart(newSandwich);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Items added to cart!");
            alert.showAndWait();
        }


    }

    @FXML
    private void returnToMain() {
        Stage mainView = new Stage();
        VBox root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            root = (VBox) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 500);

//            System.out.println("check..");
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            //mainController.setPrimaryStage(primaryStage, primaryScene);
            MainController newMainController = loader.getController();
            newMainController.setPrimaryStage(this.primaryStage, this.primaryScene, mainController.getCart(), mainController.getAllOrders());
            //newMainController.setPrimaryStage(primaryStage, primaryScene);


            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println(e.toString());
            alert.setTitle("ERROR");

        }
    }

}
