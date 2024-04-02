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

    public View1Controller() {
    }

    public void initialize() {
        this.colorList = FXCollections.observableArrayList(new String[]{"Green", "Red", "Blue", "Yellow"});
        this.fruitList = FXCollections.observableArrayList(new String[]{"Apple", "Orange", "Banana", "Watermelon"});
        this.cmb_color.setItems(this.colorList);
        this.listview.setItems(this.fruitList);
    }

    public void setMainController(MainController controller, Stage stage, Stage primaryStage, Scene primaryScene) {
        this.mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    @FXML
    public void showValue(ActionEvent event) {
        this.value.setText(String.valueOf(this.mainController.getValue()));
    }

    @FXML
    public void displaySelected(ActionEvent event) {
        String selected = (String)this.cmb_color.getSelectionModel().getSelectedItem();
        this.color.setText(selected);
    }

    @FXML
    public void displayFruit(MouseEvent event) {
        int selected = this.listview.getSelectionModel().getSelectedIndex();
        if (this.listview.getItems().size() != 0) {
            Label var10000 = this.color;
            String var10001 = (String)this.listview.getItems().get(selected);
            var10000.setText(var10001 + " is removed.");
            this.fruitList.remove(selected);
        }

    }

    @FXML
    public void removeColor(ActionEvent event) {
        if (this.cmb_color.getItems().size() != 0) {
            Label var10000 = this.color;
            ObservableList var10001 = this.cmb_color.getItems();
            var10000.setText(((String)var10001.get(0)).toString() + " is removed.");
            this.cmb_color.getItems().remove(0);
        }

    }

    @FXML
    public void displayMain() {
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.show();
    }
}
