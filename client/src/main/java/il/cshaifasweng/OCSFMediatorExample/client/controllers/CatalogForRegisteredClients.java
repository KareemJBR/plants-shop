package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CatalogForRegisteredClients {


    @FXML
    private Button addToCartButt1;

    @FXML
    private Button addToCartButt2;

    @FXML
    private Button addToCartButt3;

    @FXML
    private Button addToCartButt4;

    @FXML
    private Button addToCartButt5;

    @FXML
    private Button addToCartButt6;

    @FXML
    private Button buyButton1;

    @FXML
    private Button buyButton2;

    @FXML
    private Button buyButton3;

    @FXML
    private Button buyButton4;

    @FXML
    private Button buyButton5;

    @FXML
    private Button goBack;

    @FXML
    private Button goToCartBut;

    @FXML
    private ImageView imgView1;

    @FXML
    private ImageView imgView2;

    @FXML
    private ImageView imgView3;

    @FXML
    private ImageView imgView4;

    @FXML
    private ImageView imgView5;

    @FXML
    private ImageView imgView6;

    @FXML
    private AnchorPane itemsContainer;

    @FXML
    private TextArea text1;

    @FXML
    private TextArea text2;

    @FXML
    private TextArea text3;

    @FXML
    private TextArea text4;

    @FXML
    private TextArea text5;

    @FXML
    private TextArea text6;

    @FXML
    private TextField title;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    void BuyNow(ActionEvent event)throws IOException {

    }

    @FXML
    void addToCart(ActionEvent event)throws IOException{

    }

    @FXML
    void goCart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

}
