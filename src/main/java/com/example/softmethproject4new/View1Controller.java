package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
