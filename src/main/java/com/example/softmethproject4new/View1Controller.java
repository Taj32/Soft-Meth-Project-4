package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class View1Controller {
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


    public void initialize() {

        cb_donutType.getItems().addAll("Yeast", "Cake", "Donut Holes");
        cb_quantity.getItems().addAll("1", "2", "3", "4", "5");

        donutOrders.setItems(orderList);

        cb_donutType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateFlavors(newValue);
        });

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

            orderList.add(flavor + order.toString());

            donutOrderList.add(order);
            updateDonutTotal();

        }

    }

    private void updateDonutTotal() {
        double total = 0.0;
        for (Donut donut : donutOrderList) {
            total += donut.price();
        }
        donutTotal.setText("$" + String.valueOf(total));

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

    @FXML
    protected void addDonuts() {
        if(!donutOrderList.isEmpty()) {
            for(Donut individualDonut : donutOrderList) {
                System.out.println(individualDonut.getFlavor());
                mainController.addToCart(individualDonut);
            }
        }

    }
}
