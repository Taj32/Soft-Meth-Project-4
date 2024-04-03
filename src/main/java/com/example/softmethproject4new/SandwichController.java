package com.example.softmethproject4new;

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
    private ObservableList<String> fruitList;
    private Sandwich currentSandwich;


    @FXML
    ToggleGroup proteinType;

    @FXML
    ToggleGroup breadType;

    @FXML
    CheckBox lettuceOption;


    @FXML
    private TextArea sandwichPrice;

    public void initialize() {
        System.out.println("starting up!");
        addOns = new ArrayList<>();
    }

    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }


    @FXML
    public void sandwichOrder(ActionEvent event) {
        RadioButton bread = (RadioButton) breadType.getSelectedToggle();
        RadioButton protein = (RadioButton) proteinType.getSelectedToggle();

        if(protein != null) {
            System.out.println("check");
            if(currentSandwich == null) {
                currentSandwich = new Sandwich(bread.getText(), protein.getText(), addOns);
            }
            else {
                currentSandwich = new Sandwich(bread.getText(), protein.getText(), addOns);
            }

            updateSandwichTotal();
        }
    }

    private void updateSandwichTotal() {
        sandwichPrice.setText("$" + currentSandwich.price());
    }

    @FXML
    public void changeAddOns(ActionEvent event) {
        RadioButton bread = (RadioButton) breadType.getSelectedToggle();
        RadioButton protein = (RadioButton) proteinType.getSelectedToggle();
        CheckBox currentCheckBox = (CheckBox) event.getSource();
        if(!addOns.contains(currentCheckBox.getText())) {
            // Add on has not been selected
            addOns.add(currentCheckBox.getText());
            System.out.println("selecting..");
        }
        else {
            // Add on list already contains checkbox
            // so remove it since we're deselecting...
            addOns.remove(currentCheckBox.getText());
            System.out.println("deselecting...");
        }

        currentSandwich = new Sandwich(bread.getText(), protein.getText(), addOns);
        updateSandwichTotal();

    }

    @FXML
    protected void returnToMain() {
        Stage mainView = new Stage();
        VBox root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            root = (VBox) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 500);
            //view1.setScene(scene); //if you want to use the new window to display the new scene
            //view1.setTitle("view1");
            //view1.show();
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            MainController newMainController = loader.getController();

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            newMainController.setPrimaryStage(primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println(e.toString());
            alert.setTitle("ERROR");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setHeaderText("Loading View1.fxml.");
//            alert.setContentText("Couldn't load View1.fxml.");
//            alert.showAndWait();
        }
    }

}
