package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import com.mysql.cj.xdevapi.Client;
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
import il.cshaifasweng.OCSFMediatorExample.client.controllers.Cart.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;

public class RegisteredCatalogControl {

    @FXML
    private Button filterBTN;

    @FXML
    private ChoiceBox<String> filterSelect;

    @FXML
    private Label textFilter;

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
    void filter(ActionEvent event) throws IOException {
        if (filterSelect.getValue().toString().equals("All Items")) {
            App.getAllItems();
        } else {
            getAllitemsUnderSale();
        }
        App.setRoot("controllers/RegisteredCatalog");
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
    public ArrayList<CartItem> searchCartItems(String ClientId) throws IOException {
        CartItemsdata = null;
        ArrayList<CartItem> allcartitems = getAllCartItems();
        ArrayList<CartItem> returnedcartitems = new ArrayList<CartItem>();

        if (allcartitems != null) {
            for (int i = 0; i < allcartitems.size(); i++) {
                if (allcartitems.get(i).getCustomer().getUser_id().equals(ClientId)) {
                    returnedcartitems.add(allcartitems.get(i));
                }
            }
        }

        return returnedcartitems;
    }

    public void initialize() throws IOException {

        ArrayList<Item> allItems = (ArrayList<Item>) allItemsData;
        filterSelect.getItems().addAll("     ","All Items", "Under Sale");
        filterSelect.setValue("     ");
        filterSelect.getSelectionModel().select(0);
        boolean moveRight = false;
        int j = 0;
        if (allItems != null) {
            if (allItems.size() != 0) {
                itemscontainer.setMinHeight(allItems.size() * 120);      ///the height of the container is related to the amount of the items
//                ArrayList<Button> addtocartBTNS = new ArrayList<>();
//                for (int i = 0; i < allItems.size(); i++) {
//                    addtocartBTNS.add(new Button());
//                }
                for (int i = 0; i < allItems.size(); i++) {
                    AnchorPane p = new AnchorPane();            //container of each item
                    p.setStyle("-fx-background-color: #E43A19");
                    p.setMinSize(355, 200);
                    if (i % 2 == 1) {
                        moveRight = true;
                    } else {
                        moveRight = false;
                    }
                    ////////////// img /////////////
                    ImageView imageview = new ImageView();
                    imageview.setFitWidth(180);   //width of img
                    imageview.setFitHeight(180); //height of img
                    System.out.println(i);
                    imageview.setImage(new Image(allItems.get(i).getUrl()));
                    imageview.setLayoutX(10);           //x & y coordinate related in the pane
                    imageview.setLayoutY(10);
                    ImageView saleImg = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5_sSCL4v_OTxw8XXoGNcWeV0rYEV0e76Nsw&usqp=CAU");

                    if (allItems.get(i).isUnderSale()) {
                        saleImg.setFitWidth(25);   //width of img
                        saleImg.setFitHeight(25); //height of img
                        saleImg.setLayoutX(325);           //x & y coordinate related in the pane
                        saleImg.setLayoutY(2);
                    }

                    //////////////// details of the item //////////////
                    ///////// price textfield ///////////
                    TextField name = new TextField("Name: " + allItems.get(i).getName()  //"\n"
                            //  + "Catalog Number: " + cartItems.get(i).getCatalogNumber() + "\n"
                            //  + "Type: " + cartItems.get(i).getType() + "\n"
                            //  + "Price: " + cartItems.get(i).getPrice() + "\n"
                    );
                    name.setStyle("-fx-background-color:#F2F4F7");
                    name.setLayoutX(200);
                    name.setLayoutY(30);
                    name.setEditable(false);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + allItems.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color:#F2F4F7");
                    catologNum.setLayoutX(200);
                    catologNum.setLayoutY(50);
                    catologNum.setEditable(false);

                    ///////// type textfield ///////////
                    TextField type = new TextField("Type: " + allItems.get(i).getType());
                    type.setStyle("-fx-background-color:#F2F4F7");
                    type.setLayoutX(200);           //x & y coordinate related in the pane
                    type.setLayoutY(70);
                    type.setEditable(false);

                    TextField color = new TextField("Color: " + allItems.get(i).getColor());
                    color.setStyle("-fx-background-color:#F2F4F7");
                    color.setLayoutX(200);           //x & y coordinate related in the pane
                    color.setLayoutY(90);
                    color.setEditable(false);


                    ///////// type textfield ///////////
                    TextField price = new TextField();
                    TextField priceAfterSale = new TextField();
                    if (allItems.get(i).isUnderSale()) {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Price After " + allItems.get(i).getSalePercent()*100 + "% sale:" + allItems.get(i).getPriceAfterSale());
                    } else {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Final Price: " + allItems.get(i).getOriginal_price());
                    }
                    price.setStyle("-fx-background-color:#F2F4F7");
                    price.setLayoutX(200);           //x & y coordinate related in the pane
                    price.setLayoutY(110);
                    priceAfterSale.setStyle("-fx-background-color:#F2F4F7");
                    priceAfterSale.setLayoutX(200);
                    priceAfterSale.setLayoutY(130);
                    price.setEditable(false);
                    priceAfterSale.setEditable(false);


                    ////////////////////// buttons ///////////////////
                    Button addToCartbtn = new Button();

                    addToCartbtn.setText("Add To Cart");
                    addToCartbtn.setOnAction(e -> {
                        try {
                            //  boolean add=false;
                            getCurrentCustomer();
                            int num = Integer.parseInt(((Button) e.getTarget()).getId());
                            ArrayList<CartItem> incart = searchCartItems(((Customer) currentCustomerData).getId());
                            for (int k = 0; k < incart.size(); k++) {
                                if (incart.get(k).getItem().getId() == (num + 1)) {
                                    int increase = (incart.get(k).getAmount() + 1);
                                    incart.get(k).setAmount(increase);
                                    MsgClass update = new MsgClass("#update cartIrem", incart.get(k));
                                    SimpleClient.getClient().sendToServer(update);
                                    System.out.println("the amount of shit" + incart.get(k).getAmount());
                                    return;
                                }
                            }
                            CartItem newItem = new CartItem(getCurrentCustomer(), allItems.get(num), 1);
                            MsgClass addCartItm = new MsgClass("#add cartItem", newItem);
                            SimpleClient.getClient().sendToServer(addCartItm);
                            System.out.println("the item you whant is " + num);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    addToCartbtn.setFont(new Font(14));
                    addToCartbtn.setStyle("-fx-background-color: #243447; -fx-text-fill: #F2F4F7; -fx-background-radius: 20");
                    addToCartbtn.setLayoutX(225);
                    addToCartbtn.setLayoutY(160);

                    addToCartbtn.setId(String.valueOf(i));

                    ///////////////////////////////////////////////////////
//                    Button buyNow = new Button();
//                    buyNow.setText("Buy Now");
//                    buyNow.setOnAction(e -> {
//
//                    });
//                    buyNow.setStyle("-fx-background-color:#EEEEEE");
//                    buyNow.setLayoutX(225);
//                    buyNow.setLayoutY(105);

                    /////////////// adding components to the pane /////////////
                    p.getChildren().add(imageview);
                    p.getChildren().add(price);
                    p.getChildren().add(type);
                    p.getChildren().add(name);
                    p.getChildren().add(color);
                    p.getChildren().add(catologNum);
                    p.getChildren().add(priceAfterSale);
                    if (allItems.get(i).isUnderSale()) {
                        p.getChildren().add(saleImg);
                    }
                    // p.getChildren().add(buyNow);
                    p.getChildren().add(addToCartbtn);
                    if (moveRight) {
                        p.setLayoutY(240 * j);
                        p.setLayoutX(395);
                        j++;
                    } else {
                        p.setLayoutY(240 * j);
                    }
                    itemscontainer.getChildren().add(p);
                }
            }
        }
    }
}
