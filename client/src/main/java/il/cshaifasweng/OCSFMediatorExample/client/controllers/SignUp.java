package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUp {

    @FXML // fx:id="SignUpBtn"
    private Button SignUpBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordBtn"
    private TextField confirmPasswordBtn; // Value injected by FXMLLoader

    @FXML // fx:id="passwordBtn"
    private TextField passwordBtn; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumber"
    private TextField phoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumber1"
    private TextField phoneNumber1; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader


    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }
}
