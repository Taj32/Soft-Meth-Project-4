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

public class coffeeController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ObservableList<String> colorList;
    private ObservableList<String> fruitList;
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
    private ComboBox<String> cb_size;
    @FXML
    private ComboBox<String> cb_quantity;

    @FXML
    private TextArea coffeePrice;

    private ArrayList<String> addins;

    private Coffee currentCoffee;



    public void setMainController (MainController controller,
                                   Stage stage,
                                   Stage primaryStage,
                                   Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }


    public void initialize() {

        cb_size.getItems().addAll("Short", "Tall", "Grande", "Venti");
        cb_quantity.getItems().addAll("1", "2", "3", "4", "5");
        addins = new ArrayList<>();


        //donutOrders.setItems(orderList);

        //cb_donutType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
        //    updateFlavors(newValue);
        //});

    }

    @FXML
    protected void addCoffee() {
        if(currentCoffee != null) {
            mainController.addToCart(currentCoffee);
        }

    }

    public void coffeeOrder(ActionEvent event) {
        String size = cb_size.getSelectionModel().getSelectedItem();
        String quantity = cb_quantity.getSelectionModel().getSelectedItem();

        if(size != null && quantity != null) {
            //calculate the cost
            int amount = Integer.parseInt(quantity);
            currentCoffee = new Coffee(size, amount, addins);
            updateCoffeeTotal();

        }

        System.out.println(size);
    }

    private void updateCoffeeTotal() {
        coffeePrice.setText(String.format("$%.2f",currentCoffee.price()));
    }

    @FXML
    public void changeAddOns(ActionEvent event) {
        //RadioButton bread = (RadioButton) breadType.getSelectedToggle();
        //RadioButton protein = (RadioButton) proteinType.getSelectedToggle();
        CheckBox currentCheckBox = (CheckBox) event.getSource();
        if(!addins.contains(currentCheckBox.getText())) {
            // Add on has not been selected
            addins.add(currentCheckBox.getText());
            System.out.println("selecting..");
        }
        else {
            // Add on list already contains checkbox
            // so remove it since we're deselecting...
            addins.remove(currentCheckBox.getText());
            System.out.println("deselecting...");
        }

        String size = cb_size.getSelectionModel().getSelectedItem();
        String quantity = cb_quantity.getSelectionModel().getSelectedItem();

        if(size != null && quantity != null) {
            int amount = Integer.parseInt(quantity);
            currentCoffee = new Coffee(size, amount, addins);
            updateCoffeeTotal();
        }


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
            newMainController.setPrimaryStage(this.primaryStage, this.primaryScene, mainController.getCart(), mainController.getAllOrders());

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            //newMainController.setPrimaryStage(primaryStage, primaryScene);
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
