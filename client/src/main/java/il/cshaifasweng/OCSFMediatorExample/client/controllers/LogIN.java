package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogIN {

    @FXML // fx:id="Password"
    private TextField Password; // Value injected by FXMLLoader

    @FXML // fx:id="catalogBtn"
    private Button catalogBtn; // Value injected by FXMLLoader

    @FXML // fx:id="createAcountBtn"
    private Button createAcountBtn; // Value injected by FXMLLoader

    @FXML // fx:id="logInBtn"
    private Button logInBtn; // Value injected by FXMLLoader

    @FXML // fx:id="notmemberLabel"
    private Label notmemberLabel; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML
    void createAcount(ActionEvent event) throws IOException {
       App.setRoot("controllers/SignUp");
        //SignUp.main(null);
    }
    @FXML
    void logIN(ActionEvent event) {

    }
    @FXML
    void openCatalog(ActionEvent event) throws IOException {
        App.setRoot("controllers/CatalogForNoneRegisteredClients");
    }
}
