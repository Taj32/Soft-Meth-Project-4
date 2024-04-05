package com.example.softmethproject4new;

import javafx.beans.property.SimpleStringProperty;
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

public class CurrentOrderController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    @FXML
    TableView table;

    @FXML
    TableColumn<MenuItem, String> item, price;

    @FXML
    private TextArea subTotal;

    @FXML
    private TextArea tax;

    @FXML
    private TextArea total;



    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
//        System.out.println("running this part..");
        this.mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    public void populateTable() {

        ObservableList<MenuItem> currentOrder = FXCollections.observableArrayList();//write code to read from the file.

        if(mainController.getCart() == null) {
            return;
        }


        for(MenuItem element : mainController.getCart()) {
            currentOrder.add(element);

        }

        table.setItems(currentOrder);
        item.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));
        price.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("$%.2f",cellData.getValue().price())));


        //Calculate costs
        updateOrderTotals();



        //currentOrder.setItems(observableList);

    }

    public void removeOrders(ActionEvent event) {
//        System.out.println(table.getSelectionModel().getSelectedItem());
        try{
            MenuItem selectedItem = (MenuItem) table.getSelectionModel().getSelectedItem();
            for(MenuItem element : mainController.getCart()) {
                if(selectedItem.equals(element)) {
                    mainController.getCart().remove(selectedItem);
                    break;
                }
            }

            populateTable();

        }catch (RuntimeException e){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Item was not selected to remove.");
                alert.showAndWait();

        }

    }

    private void updateOrderTotals() {

        double sub = 0.0;
        double taxTotal = 0.0;
        double grandTotal = 0.0;

        for(MenuItem element : mainController.getCart()) {
            sub += element.price();
        }

        taxTotal = sub * 0.0625;
        grandTotal = sub + taxTotal;

        subTotal.setText(String.format("$%.2f",sub));
        tax.setText(String.format("$%.2f",taxTotal));
        total.setText(String.format("$%.2f",grandTotal));

    }

    @FXML
    private void placeOrder(ActionEvent event) {
        if (!mainController.getCart().isEmpty()) {
            Order newOrder = new Order();
            newOrder.addItems(new ArrayList<>(mainController.getCart())); // Clone to avoid reference issues
            mainController.addOrder(newOrder); // Add this order to the main list
            mainController.getCart().clear(); // Clear the current cart
            populateTable(); // Refresh the current order table (it should be empty now)
            // Optionally, switch to the AllOrders view or notify the user
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No items in cart to place!");
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
            //view1.setScene(scene); //if you want to use the new window to display the new scene
            //view1.setTitle("view1");
            //view1.show();
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            MainController newMainController = loader.getController();
            newMainController.setPrimaryStage(this.primaryStage, this.primaryScene, mainController.getCart(), mainController.getAllOrders());

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            System.out.println(e.toString());
            alert.setTitle("ERROR");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setHeaderText("Loading View1.fxml.");
//            alert.setContentText("Couldn't load View1.fxml.");
//            alert.showAndWait();
        }
    }
}
