package com.example.softmethproject4new;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CurrentOrderController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ObservableList<String> observableList1;
    private ObservableList<String> observableList2;

    @FXML
    TableView table;

    @FXML
    TableColumn<MenuItem, String> item, price;


    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        System.out.println("running this part..");
        this.mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    public void populateTable() {

        ObservableList<MenuItem> currentOrder = FXCollections.observableArrayList();//write code to read from the file.

        //for(MenuItem element : mainController.getCart()) {
        //    System.out.println(element);
        //}
        if(mainController == null) {
            System.out.println("controller is null");
        }
        else {
            System.out.println(mainController.getCart().get(0).price());
        }

        for(MenuItem element : mainController.getCart()) {
            currentOrder.add(element);
        }

        table.setItems(currentOrder);
        item.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));
        price.setCellValueFactory(cellData -> new SimpleStringProperty(  Double.toString(cellData.getValue().price()  )   ) );





        //currentOrder.setItems(observableList);

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
            newMainController.setPrimaryStage(this.primaryStage, this.primaryScene, mainController.getCart());

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
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
