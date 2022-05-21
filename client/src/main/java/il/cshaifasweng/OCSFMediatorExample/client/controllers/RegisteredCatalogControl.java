package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
public class RegisteredCatalogControl {


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
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    void BuyNow(ActionEvent event) throws IOException {

    }

    @FXML
    void addToCart(ActionEvent event) throws IOException {

    }

    @FXML
    void goCart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

    @FXML
    void initialize() {
        try {
            System.out.println("in init for catalog");
           // ArrayList<Flower> flowers = getAllFlowers();
            System.out.println(((ArrayList<Flower>)data));
            text1.setText(((ArrayList<Flower>)data).get(0).toString());
            imgView1.setImage(new Image(((ArrayList<Flower>)data).get(0).getImgURL()));
            text2.setText(((ArrayList<Flower>)data).get(1).toString());
            imgView3.setImage(new Image(((ArrayList<Flower>)data).get(1).getImgURL()));
            text3.setText(((ArrayList<Flower>)data).get(2).toString());
            imgView3.setImage(new Image(((ArrayList<Flower>)data).get(2).getImgURL()));
            text4.setText(((ArrayList<Flower>)data).get(3).toString());
            imgView4.setImage(new Image(((ArrayList<Flower>)data).get(3).getImgURL()));
            text5.setText(((ArrayList<Flower>)data).get(4).toString());
            imgView5.setImage(new Image(((ArrayList<Flower>)data).get(4).getImgURL()));
        } catch (Exception e) {
            System.out.println("init exception for reg catalog");
            System.out.println(e);
        }
    }
}
