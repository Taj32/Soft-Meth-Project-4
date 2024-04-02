package com.example.softmethproject4new;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
    private int value = 10; //sample data to share among the controllers.
    private Stage primaryStage; //reference to the main stage (window)
    private Scene primaryScene; //reference to the scene associated with the main stage

    /**
     * Constructor.
     * To get the references of the stage and scene from the Main class.
     *
     * @param stage the primary stage object created in the Main class.
     * @param scene the scene object associated with the primary stage.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    /**
     * Event handler for the image button.
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the new window is View1.fxml, in which the
     * fx:controller attribute defines the controller as View1Controller.
     * When the fxml file is loading, an instance of View1Controller will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    protected void displayView1() {
        Stage view1 = new Stage();
        AnchorPane root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 500, 400);
            //view1.setScene(scene); //if you want to use the new window to display the new scene
            //view1.setTitle("view1");
            //view1.show();
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            View1Controller view1controller = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            view1controller.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            System.out.println(e.toString());
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setHeaderText("Loading View1.fxml.");
//            alert.setContentText("Couldn't load View1.fxml.");
//            alert.showAndWait();
        }
    }

    /**
     * The View1Controller can use this getter method to read the private data.
     *
     * @return
     */
    public int getValue() {
        return value;
    }
}