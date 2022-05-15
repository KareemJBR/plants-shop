
package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Cart {

    @FXML // fx:id="CheckoutBtn"
    private Button CheckoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="itemsList"
    private AnchorPane itemsList; // Value injected by FXMLLoader

    @FXML // fx:id="subtotal"
    private TextField subtotal; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/ClientMainPage");
    }
}