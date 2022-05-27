package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.Itemsdata;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;

public class RegisteredCatalogControl {

    @FXML
    private Button goBack;

    @FXML
    private Button goToCartBut;

    @FXML
    private AnchorPane itemscontainer;

    @FXML
    private Label title;


    @FXML
    private AnchorPane itemsContainer;

    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get customers", null);
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

    /*  @FXML
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
      }*/
    public void initialize() throws IOException, InterruptedException {

        ArrayList<Item> cartItems = (ArrayList<Item>) Itemsdata;
        itemscontainer.setStyle("-fx-background-color: #222831");
        goBack.setStyle("-fx-background-color:#EEEEEE");
        goToCartBut.setStyle("-fx-background-color:#EEEEEE");
        title.setStyle("-fx-background-color:#00ADB5");
        itemsContainer.setStyle("-fx-background-color:#222831");
        boolean moveRight = false;
        int j = 0;
        if (cartItems != null) {
            if (cartItems.size() != 0) {
                itemscontainer.setMinHeight(cartItems.size() * 80);      ///the height of the container is related to the amount of the items
                ArrayList<ImageView> arr = new ArrayList<ImageView>();
                for (int i = 0; i < cartItems.size(); i++) {
                    AnchorPane p = new AnchorPane();            //container of each item
                    p.setStyle("-fx-background-color: #393E46");
                    p.setMinSize(295, 140);
                    if (i % 2 == 1) {
                        moveRight = true;
                    } else {
                        moveRight = false;
                    }
                    ////////////// img /////////////
                    ImageView imageview = new ImageView();
                    imageview.setFitWidth(130);   //width of img
                    imageview.setFitHeight(130); //height of img
                    System.out.println(i);
                    imageview.setImage(new Image(cartItems.get(i).getUrl()));
                    imageview.setLayoutX(5);           //x & y coordinate related in the pane
                    imageview.setLayoutY(5);

                    //////////////// details of the item //////////////
                    ///////// price textfield ///////////
                    TextField name = new TextField("Name: " + cartItems.get(i).getName()  //"\n"
                          //  + "Catalog Number: " + cartItems.get(i).getCatalogNumber() + "\n"
                          //  + "Type: " + cartItems.get(i).getType() + "\n"
                          //  + "Price: " + cartItems.get(i).getPrice() + "\n"
                    );
                    name.setStyle("-fx-background-color:#00ADB5");
                    name.setLayoutX(140);
                    name.setLayoutY(5);
                    name.setEditable(false);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + cartItems.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color:#00ADB5");
                    catologNum.setLayoutX(140);
                    catologNum.setLayoutY(25);
                    name.setEditable(false);

                    ///////// type textfield ///////////
                    TextField type = new TextField("type: " + cartItems.get(i).getType());
                    type.setStyle("-fx-background-color:#00ADB5");
                    type.setLayoutX(140);           //x & y coordinate related in the pane
                    type.setLayoutY(45);

                    ///////// type textfield ///////////
                    TextField price = new TextField("type: " + cartItems.get(i).getPrice());
                    price.setStyle("-fx-background-color:#00ADB5");
                    price.setLayoutX(140);           //x & y coordinate related in the pane
                    price.setLayoutY(65);


                    ////////////////////// buttons ///////////////////
                    Button addToCartbtn = new Button();
                    addToCartbtn.setText("Add To Cart");
                    addToCartbtn.setOnAction(e -> {
                        System.out.println("dont press me  pls");

                    });
                    addToCartbtn.setStyle("-fx-background-color:#EEEEEE");
                    addToCartbtn.setLayoutX(140);
                    addToCartbtn.setLayoutY(105);

                    ///////////////////////////////////////////////////////
                    Button buyNow = new Button();
                    buyNow.setText("Buy Now");
                    buyNow.setOnAction(e -> {
                        System.out.println("dont press me  pls");

                    });
                    buyNow.setStyle("-fx-background-color:#EEEEEE");
                    buyNow.setLayoutX(225);
                    buyNow.setLayoutY(105);

                    /////////////// adding components to the pane /////////////
                    p.getChildren().add(imageview);
                    p.getChildren().add(price);
                    p.getChildren().add(type);
                    p.getChildren().add(name);
                    p.getChildren().add(catologNum);
                    p.getChildren().add(buyNow);
                    p.getChildren().add(addToCartbtn);
                    if (moveRight) {
                        p.setLayoutY(170 * j);
                        p.setLayoutX(320);
                        j++;
                    } else {
                        p.setLayoutY(170 * j);
                    }
                    itemscontainer.getChildren().add(p);
                }
            }
        }
    }
}
