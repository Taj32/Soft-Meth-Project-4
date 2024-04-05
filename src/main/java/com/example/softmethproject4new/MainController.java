package com.example.softmethproject4new;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
    private int value = 10; //sample data to share among the controllers.
    private Stage primaryStage; //reference to the main stage (window)
    private Scene primaryScene; //reference to thore scene associated with the main stage

    //We're keeping these two lists between each page:
    private ArrayList<MenuItem> cart = new ArrayList<>();
    private List<Order> allOrders = new ArrayList<>();


    /**
     * Constructor.
     * To get the references of the stage and scene from the Main class.
     *
     * @param stage the primary stage object created in the Main class.
     * @param scene the scene object associated with the primary stage.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
//        System.out.println("running");
        this.primaryStage = stage;
        this.primaryScene = scene;
    }
    public void setPrimaryStage(Stage stage, Scene scene, ArrayList<MenuItem> cart, List<Order> allOrders) {
//        System.out.println("running");
        this.primaryStage = stage;
        this.primaryScene = scene;
        this.cart = cart;
        this.allOrders = allOrders;
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
    private void displayView1() {
        Stage view1 = new Stage();
        AnchorPane root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 400);
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            View1Controller view1controller = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            view1controller.setMainController(this, view1, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

//            System.out.println(e.toString());
            alert.setTitle("ERROR");

        }
    }

    @FXML
    protected void displayCoffeeView() {
        Stage coffeeView = new Stage();
        AnchorPane root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 400);
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            coffeeController cofController = loader.getController();

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            cofController.setMainController(this, coffeeView, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            System.out.println(e.toString());
            alert.setTitle("ERROR");
        }
    }

    @FXML
    protected void displaySandwichView() {
        Stage sandwichView = new Stage();
        AnchorPane root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sandwich-view.fxml"));
            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 400);
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            SandwichController sandwichController = loader.getController();

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            sandwichController.setMainController(this, sandwichView, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            System.out.println(e.toString());
            alert.setTitle("ERROR");
        }
    }

    @FXML
    protected void displayCurrentOrderView() {
        Stage currentOrderView = new Stage();
        AnchorPane root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order-view.fxml"));
            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 400);
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            CurrentOrderController currentOrderController = loader.getController();

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            currentOrderController.setMainController(this, currentOrderView, primaryStage, primaryScene);
            currentOrderController.populateTable();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            System.out.println(e.toString());
            alert.setTitle("ERROR");

        }
    }

    @FXML
    protected void displayAllOrdersView() {
        Stage allOrdersView = new Stage();
        AnchorPane root;
        try { //it is possible to have an IOException because of the errors in the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("all-orders-view.fxml"));
            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node
            Scene scene = new Scene(root, 700, 400);
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            AllOrdersController allOrdersController = loader.getController();

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            allOrdersController.setMainController(this, allOrdersView, primaryStage, primaryScene);
            allOrdersController.populateOrderNumbers();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //System.out.println(e.toString());
            alert.setTitle("ERROR");
        }
    }

    /**
     * The View1Controller can use this getter method to read the private data.
     *
     * @return
     */
    public void setValue() {
        this.value -= 1;
    }

    public void addToCart(MenuItem currentItem) {
        cart.add(currentItem);
    }

    public ArrayList<MenuItem> getCart() {
        return cart;
    }

//    public List<Order> getOrders() {
//        return allOrders;
//    }


    public void addOrder(Order order) {
//        printAllOrders();
        allOrders.add(order);
//        printAllOrders();
    }

    //Here for testing..
//    public void printAllOrders() {
//        for(Order singleOrder : allOrders) {
////            System.out.println(singleOrder);
//        }
//    }
    public List<Order> getAllOrders() {
        return allOrders;
    }

    public void removeOrder(int orderNumber) {
        allOrders.removeIf(order -> order.getOrderNumber() == orderNumber);
    }






}