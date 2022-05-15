package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomizedOrder {

    @FXML // fx:id="ItemTypeLabel"
    private Label ItemTypeLabel; // Value injected by FXMLLoader

    @FXML // fx:id="addToCartBtn"
    private Button addToCartBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cartBtn"
    private Button cartBtn; // Value injected by FXMLLoader

    @FXML // fx:id="colorCombo"
    private ComboBox<?> colorCombo; // Value injected by FXMLLoader

    @FXML // fx:id="colorLabel"
    private Label colorLabel; // Value injected by FXMLLoader

    @FXML // fx:id="itemTypeCombo"
    private ComboBox<?> itemTypeCombo; // Value injected by FXMLLoader

    @FXML // fx:id="priceRangeLabel"
    private Label priceRangeLabel; // Value injected by FXMLLoader

    @FXML // fx:id="rangeSlider"
    private Slider rangeSlider; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/ClientMainPage");
    }
    @FXML
    void Cart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }
}
