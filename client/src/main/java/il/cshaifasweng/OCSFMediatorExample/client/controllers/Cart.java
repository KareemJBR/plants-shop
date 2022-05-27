
package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import javafx.scene.image.Image;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.CartItemsdata;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.*;

public class Cart<T> {
    public static double OrderSubtotal;

    @FXML // fx:id="CheckoutBtn"
    private Button CheckoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="EmptyCartLabel"
    private Label EmptyCartLabel; // Value injected by FXMLLoader

    @FXML // fx:id="Subtotal"
    private TextField Subtotal; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="itemscontainer"
    private AnchorPane itemscontainer; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        loadPage();
    }


    ////////////////// return the Caritems of client whose idnumber=ClientId;
    public ArrayList<CartItem> searchCartItems(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();
        ArrayList<CartItem> returnedcartitems=new ArrayList<CartItem>();

        if(allcartitems !=null)
        {
            for(int i=0;i<allcartitems.size();i++)
            {
                if(allcartitems.get(i).getCustomer().getUser_id().equals(ClientId))
                {
                    returnedcartitems.add(allcartitems.get(i));
                }
            }
        }

        return returnedcartitems;
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

    public void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(head);
                alert.showAndWait();
            }
        });

    }

    void loadPage() throws IOException {
        itemscontainer.getChildren().removeAll(itemscontainer.getChildren());
        EmptyCartLabel.setVisible(false);
        ArrayList<CartItem> cartItems = searchCartItems(LoginClient_userId);
        ArrayList<Integer> amountOfItems= getamount(LoginClient_userId);
        double subtotal=0;
        if (cartItems != null) {
            if (cartItems.size() != 0) {
                itemscontainer.setMinHeight(cartItems.size()*90);      ///the height of the container is related to the amount of the items
                ArrayList<ImageView> arr=new ArrayList<ImageView>();
                for(int i=0;i<cartItems.size();i++)
                {
                    int itemamount=amountOfItems.get(i);
                    Pane p=new Pane();            //container of each item

                    ////////////// img /////////////
                    ImageView imageview=new ImageView();
                    imageview.setFitWidth(78);   //width of img
                    imageview.setFitHeight(78); //height of img
//                        System.out.println(cartItems.get(i).getUrl());
                    imageview.setImage(new Image(cartItems.get(i).getItem().getUrl()));
                    imageview.setX(8);           //x & y coordinate related to the pane
                    imageview.setY(8);

                    //////////////// details of the item //////////////

                    ///////// name textfield ///////////
                    TextField name=new TextField("Name: "+ cartItems.get(i).getItem().getName());
                    name.setStyle("-fx-background-color:none");
                    name.setLayoutX(100);
                    name.setLayoutY(8);
                    ///////// price textfield ///////////
                    TextField price=new TextField("Type: "+ cartItems.get(i).getItem().getType());
                    price.setStyle("-fx-background-color:none");
                    price.setLayoutX(100);
                    price.setLayoutY(28);

                    ///////// type textfield ///////////
                    TextField type=new TextField("Price: "+ cartItems.get(i).getItem().getPrice());
                    type.setStyle("-fx-background-color:none");
                    type.setLayoutX(100);
                    type.setLayoutY(48);

                    ///////// amount textfield ///////////
                    TextField amount=new TextField("Amount: "+ itemamount);
                    amount.setStyle("-fx-background-color:none");
                    amount.setLayoutX(100);
                    amount.setLayoutY(68);

                    ///////// delete button ///////////
                    Button btn=new Button();
                    btn.setLayoutX(255);
                    btn.setLayoutY(21);
                    btn.setText("X");
                    btn.setStyle("-fx-font-size:11;-fx-background-radius:2;");
                    btn.setId(Integer.toString(cartItems.get(i).getId()));
                    btn.setOnAction(e->{
                        int num= Integer.parseInt(((Button) e.getTarget()).getId());
                        try {
                            deleteCartitem(num);
                            loadPage();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    ///////// decrement button ///////////
                    Button btn2=new Button();
                    btn2.setLayoutX(255);
                    btn2.setLayoutY(52);
                    btn2.setText("-");
                    btn2.setId(Integer.toString(cartItems.get(i).getId()));
                    btn2.setStyle("-fx-font-size:12.1;-fx-background-radius:2;");
                    btn2.setOnAction(e->{
                        int num= Integer.parseInt(((Button) e.getTarget()).getId());
                        try {
                            decrementAmountofCartItem(num);
                            loadPage();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });


                    /////////////// adding components to the pane /////////////
                    p.getChildren().add(imageview);
                    p.getChildren().add(name);
                    p.getChildren().add(price);
                    p.getChildren().add(type);
                    p.getChildren().add(amount);
                    p.getChildren().add(btn);
                    p.getChildren().add(btn2);

                    p.setLayoutY(90*i);

                    itemscontainer.getChildren().add(p);

                    subtotal+=(cartItems.get(i).getItem().getPrice()*(itemamount));
                    if(LoginClient_acount_type.equals("Network account with 10% discount")&&subtotal>50)
                    {
                        Subtotal.setText("Subtotal after 10% discount: "+subtotal *0.9);
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

            }
            else           /////////   if the cart is empty
            {
                CheckoutBtn.setDisable(true);
                EmptyCartLabel.setVisible(true);
                Subtotal.setText("Please add some item in your cart first");
            }
        }
    }



}
