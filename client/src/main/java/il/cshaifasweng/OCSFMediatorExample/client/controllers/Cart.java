
package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.hibernate.criterion.Order;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllCartItems;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllFlowers;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.*;

public class Cart<T> {
    public static double OrderSubtotal;

    @FXML // fx:id="scrollbar"
    private ScrollPane scrollbar; // Value injected by FXMLLoader

    @FXML // fx:id="itemscontainer"
    private AnchorPane itemscontainer; // Value injected by FXMLLoader

    @FXML // fx:id="CheckoutBtn"
    private Button CheckoutBtn; // Value injected by FXMLLoader

//    @FXML // fx:id="itemsList"
//    private AnchorPane itemsList; // Value injected by FXMLLoader

    @FXML // fx:id="Subtotal"
    private TextField Subtotal; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        ArrayList<Item> cartItems = searchCartItems(LoginClient_userId);
        ArrayList<Integer> amountOfItems= getamount(LoginClient_userId);
        double subtotal=0;
        if (cartItems != null) {
            if (cartItems.size() != 0) {

                    itemscontainer.setMinHeight(cartItems.size()*80);      ///the height of the container is related to the amount of the items
              ArrayList<ImageView> arr=new ArrayList<ImageView>();
                    for(int i=0;i<cartItems.size();i++)
                    {
                        int itemamount=amountOfItems.get(i);
                        Pane p=new Pane();            //container of each item

                        ////////////// img /////////////
                        ImageView imageview=new ImageView();
                        imageview.setFitWidth(72);   //width of img
                        imageview.setFitHeight(72); //height of img
//                        System.out.println(cartItems.get(i).getUrl());
                        imageview.setImage(new Image(cartItems.get(i).getUrl()));
                        imageview.setX(8);           //x & y coordinate related to the pane
                        imageview.setY(8);

                           //////////////// details of the item //////////////
                                       ///////// price textfield ///////////
                        TextField price=new TextField("price: "+ cartItems.get(i).getPrice());
                        price.setStyle("-fx-background-color:none");
                        price.setLayoutX(100);
                        price.setLayoutY(8);

                                     ///////// type textfield ///////////
                        TextField type=new TextField("type: "+ cartItems.get(i).getType());
                        type.setStyle("-fx-background-color:none");
                        type.setLayoutX(100);
                        type.setLayoutY(28);

                                     ///////// amount textfield ///////////
                        TextField amount=new TextField("amount: "+ itemamount);
                        amount.setStyle("-fx-background-color:none");
                        amount.setLayoutX(100);
                        amount.setLayoutY(48);

                                     ///////// delete button ///////////
                        Button btn=new Button();
                        btn.setLayoutX(255);
                        btn.setLayoutY(23);
                        btn.setMaxWidth(40);
                        btn.setText("X");
                        btn.setStyle("-fx-font-size:11;-fx-background-radius:2;");

                        /////////////// adding components to the pane /////////////
                        p.getChildren().add(imageview);
                        p.getChildren().add(price);
                        p.getChildren().add(type);
                        p.getChildren().add(amount);
                        p.getChildren().add(btn);

                        p.setLayoutY(85*i);

                        itemscontainer.getChildren().add(p);

                        subtotal+=(cartItems.get(i).getPrice()*(itemamount));
                    }

            }
            else           /////////   if the cart is empty
            {
                CheckoutBtn.setDisable(true);
            }
        });
    }


    ////////////////// return the Caritems of client whose idnumber=ClientId;
    public ArrayList<Item> searchCartItems(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();
        ArrayList<Item> returnedcartitems=new ArrayList<Item>();

        if(allcartitems !=null)
        {
            for(int i=0;i<allcartitems.size();i++)
            {
                if(allcartitems.get(i).getCustomer().getUser_id().equals(ClientId))
                {
                    returnedcartitems.add(allcartitems.get(i).getItem());
                }
            }
        }
        System.out.println(LoginClient_acount_type);
        if(LoginClient_acount_type.equals("Network account with 10% discount")&&subtotal>50)
        {
            Subtotal.setText("Subtotal after 10% discount: "+ subtotal*0.9);
            Subtotal.setStyle("-fx-font-size:14;-fx-background-color:none;");
            Subtotal.setMinWidth(225);
            OrderSubtotal=  subtotal*0.9;
        }
        else
        {
            Subtotal.setText("Subtotal: "+ subtotal);
            OrderSubtotal=subtotal;
        }
    }
    public CartItem searchCartItem(int ItemId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();

        if(allcartitems !=null)
        {
            for(int i=0;i<allcartitems.size();i++)
            {
                if(allcartitems.get(i).getItem().getId()==ItemId)
                {
                    return allcartitems.get(i);
                }
            }
        }

        return  null;
    }

    public ArrayList<Integer> getamount(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();
        ArrayList<Integer> returnedcartitems=new ArrayList<Integer>();

        if(allcartitems !=null)
        {
            for(int i=0;i<allcartitems.size();i++)
            {
                if(allcartitems.get(i).getCustomer().getUser_id().equals(ClientId))
                {
                    returnedcartitems.add(allcartitems.get(i).getAmount());
                }
            }
        }

    public ArrayList<Integer> getamount(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();
        ArrayList<Integer> returnedcartitems=new ArrayList<Integer>();

        if(allcartitems !=null)
        {
            for(int i=0;i<allcartitems.size();i++)
            {
                if(allcartitems.get(i).getCustomer().getUser_id().equals(ClientId))
                {
                    returnedcartitems.add(allcartitems.get(i).getAmount());
                }
            }
        }
        return  returnedcartitems;
    }

    @FXML
    void Checkout(ActionEvent event) throws IOException {
        App.setRoot("controllers/Checkout");
    }
}