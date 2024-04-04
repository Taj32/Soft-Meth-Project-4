package com.example.softmethproject4new;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class AllOrdersController {
    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    @FXML
    private ListView<String> orderListView;

    @FXML
    private ComboBox<String> orderNumbers;
    @FXML
    private TextField totalCost;


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
        orderNumbers.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateListView(newValue);
        });
    }

    private void updateListView(String selectedOrderNumber) {
        for (Order order : mainController.getAllOrders()) {
            if (String.valueOf(order.getOrderNumber()).equals(selectedOrderNumber)) {
                ObservableList<String> items = FXCollections.observableArrayList();

                for (MenuItem menuItem : order.getItems()) {
                    items.add(menuItem.toString());
                }

                orderListView.setItems(items);
                double total = order.calculateTotal();
                double tax = total * 0.0625;
                total = tax+total;
                totalCost.setText(String.format("$%.2f", total));
                break;
            }
        }
    }


    @FXML
    private void removeSelectedOrder() {
        String selectedOrderNumberString = orderNumbers.getSelectionModel().getSelectedItem();
        if (selectedOrderNumberString != null && !selectedOrderNumberString.isEmpty()) {
            int selectedOrderNumber = Integer.parseInt(selectedOrderNumberString);
            mainController.removeOrder(selectedOrderNumber);

            populateOrderNumbers();
            orderListView.getItems().clear();
            totalCost.clear();

            // Optionally, show a confirmation dialog or message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Removed");

        } else {
            // No order selected, show an error message or handle appropriately
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");

        }
    }




    public void populateOrderNumbers() {
        ObservableList<String> numbers = FXCollections.observableArrayList();
        for (Order order : mainController.getAllOrders()) {
            numbers.add(String.valueOf(order.getOrderNumber()));
        }
        orderNumbers.setItems(numbers);
    }
    @FXML
    private void exportOrdersToFile() {
        String selectedOrderNumberString = orderNumbers.getSelectionModel().getSelectedItem();
        if (selectedOrderNumberString == null || selectedOrderNumberString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Order Selected");
            return;
        }

        int selectedOrderNumber = Integer.parseInt(selectedOrderNumberString);
        Order selectedOrder = mainController.getAllOrders().stream()
                .filter(order -> order.getOrderNumber() == selectedOrderNumber)
                .findFirst()
                .orElse(null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Selected Order");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(primaryStage); // Use your stage here
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("Order Number: " + selectedOrder.getOrderNumber());
                for (MenuItem item : selectedOrder.getItems()) {
                    writer.println("Item: " + item.toString() + ", Price: $" + String.format("%.2f", item.price()));
                }
                double total = selectedOrder.calculateTotal();
                double tax = total * 0.0625;
                total += tax;
                writer.println("Subtotal: $" + String.format("%.2f", selectedOrder.calculateTotal()));
                writer.println("Tax: $" + String.format("%.2f", tax));
                writer.println("Total: $" + String.format("%.2f", total));
                writer.flush();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Successful");

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
            }
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

            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            MainController newMainController = loader.getController();

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            newMainController.setPrimaryStage(this.primaryStage, this.primaryScene,
                    mainController.getCart(), mainController.getAllOrders());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            System.out.println(e.toString());
            alert.setTitle("ERROR");

        }
    }
}
