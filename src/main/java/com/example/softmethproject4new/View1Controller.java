package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class View1Controller {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    @FXML
    private Label value;
    @FXML
    private Label color;
    @FXML
    private ComboBox<String> cmb_color;
    @FXML
    private ListView<String> listview;
    @FXML
    private Button menuButton;

    @FXML
    ImageView donutImage;


    public void setMainController(MainController controller,
                                  Stage stage,
                                  Stage primaryStage,
                                  Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    @FXML
    private ObservableList<String> donutTypes;
    @FXML
    private ComboBox<String> cb_donutType;
    @FXML
    private ComboBox<String> cb_quantity;
    @FXML
    ListView<String> differentFlavors;
    @FXML
    ListView<String> donutOrders;
    private ObservableList<String> orderList = FXCollections.observableArrayList();

    @FXML
    private TextArea donutTotal;
    private ObservableList<Donut> donutOrderList = FXCollections.observableArrayList();

    private Image yeast = new Image("file:src/main/resources/com/example/softmethproject4new/yeast.jpg");
    private Image cake = new Image("file:src/main/resources/com/example/softmethproject4new/donut.jpg");

    private Image holes = new Image("file:src/main/resources/com/example/softmethproject4new/holes.jpg");



    public void initialize() {

        cb_donutType.getItems().addAll("Yeast", "Cake", "Donut Holes");
        cb_quantity.getItems().addAll("1", "2", "3", "4", "5");

        donutOrders.setItems(orderList);

        cb_donutType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateFlavors(newValue);
            updateImage(cb_donutType.getSelectionModel().getSelectedItem());

        });


    }

    public void updateImage(String type){
        switch (type){
            case "Yeast":
                donutImage.setImage(yeast);
                break;
            case "Cake":
                donutImage.setImage(cake);
                break;
            case "Donut Holes":
                donutImage.setImage(holes);
                break;
        }
    }

    public void updateFlavors(String type) {
        List<String> flavors = Donut.typeOfFlavors(type);

        ObservableList<String> obsFlavors = FXCollections.observableArrayList(flavors);
        differentFlavors.setItems(obsFlavors);
    }

    public void donutOrder(ActionEvent event) {
        String flavor = differentFlavors.getSelectionModel().getSelectedItem();
        String quantity = cb_quantity.getSelectionModel().getSelectedItem();
        String type = cb_donutType.getSelectionModel().getSelectedItem();

        if (flavor != null && quantity != null && type != null) {
            int amount = Integer.parseInt(quantity);
            Donut order = new Donut(type, flavor, amount);

            orderList.add(flavor + "("+amount+")");

            donutOrderList.add(order);
            updateDonutTotal();



        }

    }
    public void removeDonutOrder(ActionEvent event) {
        int selectedIndex = donutOrders.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Remove the selected order from both lists
            donutOrderList.remove(selectedIndex);
            orderList.remove(selectedIndex);

            // Update the total
            updateDonutTotal();
        } else {
            // Optionally, display a message or handle the case when no order is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an order to remove.");
            alert.showAndWait();
        }

    }

    private void updateDonutTotal() {
        double total = 0.0;
        for (Donut donut : donutOrderList) {
            total += donut.price();
        }
        donutTotal.setText(String.format("$%.2f",total));

    }

    @FXML
    protected void returnToMain() {
        Stage mainView = new Stage();
        VBox root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            root = (VBox) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 500);
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
        }
    }

    @FXML
    protected void addDonuts() {
        if(!donutOrderList.isEmpty()) {
            for(Donut individualDonut : donutOrderList) {
                mainController.addToCart(individualDonut);

            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Items added to cart!");
            alert.showAndWait();

            reset();
        }

    }

    private void reset() {
        // Reset the ComboBox selections to the first item or null


        // Reset the TextArea for the total amount
        donutTotal.setText("$0.00");

        // Clear the order lists if necessary
        orderList.clear();
        donutOrderList.clear();
    }

}
