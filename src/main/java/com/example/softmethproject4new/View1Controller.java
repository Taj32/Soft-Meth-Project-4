package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    public void updateFlavors(String type){
        List<String> flavors = Donut.typeOfFlavors(type);

        ObservableList<String> obsFlavors = FXCollections.observableArrayList(flavors);
        differentFlavors.setItems(obsFlavors);
    }

    public void donutOrder(ActionEvent event){
        String flavor = differentFlavors.getSelectionModel().getSelectedItem();
        String quantity = cb_quantity.getSelectionModel().getSelectedItem();
        String type = cb_donutType.getSelectionModel().getSelectedItem();

        if(flavor != null && quantity != null & type != null){
            int amount = Integer.parseInt(quantity);
            Donut order = new Donut(type,flavor,amount);

            orderList.add(flavor+order.toString());

            donutOrderList.add(order);
            updateDonutTotal();

        }

    }

    private void updateDonutTotal(){
        double total = 0.0;
        for(Donut donut: donutOrderList){
            total += donut.price();
        }
        donutTotal.setText("$"+String.valueOf(total));

    }
}
