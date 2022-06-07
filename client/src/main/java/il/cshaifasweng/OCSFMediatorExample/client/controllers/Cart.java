
package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.*;

public class Cart<T> {
    public static double OrderSubtotal;
    public static String OrderShop;

    @FXML // fx:id="AccountTypeLabel"
    private Label AccountTypeLabel; // Value injected by FXMLLoader

    @FXML // fx:id="CheckoutBtn"
    private Button CheckoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="Controller"
    private AnchorPane Controller; // Value injected by FXMLLoader

    @FXML // fx:id="EmptyCartLabel"
    private Label EmptyCartLabel; // Value injected by FXMLLoader

    @FXML // fx:id="Subtotal"
    private Label Subtotal; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="itemscontainer"
    private AnchorPane itemscontainer; // Value injected by FXMLLoader

    @FXML // fx:id="stores"
    private ComboBox<String> stores; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        if(LoginClient_acount_type.equals("Network account")||LoginClient_acount_type.equals("Network account with 10% discount"))
        {
            stores.setDisable(false);
            stores.setVisible(true);
            stores.getItems().removeAll(stores.getItems());
            ArrayList<Shop> shops=getAllShops();

            if(shops!=null)
            {
                //   System.out.println("notnull");
                for (Shop shop : shops) {
                    stores.getItems().addAll(shop.getAddress());
                }
            }
            stores.getSelectionModel().select(0);
            AccountTypeLabel.setText("Choose Store");
            OrderShop=stores.getValue();
        }
        else
        {
            AccountTypeLabel.setText(LoginClient_acount_type);
            OrderShop=LoginClient_acount_type;
        }
        loadPage();
    }


    ////////////////// return the Caritems of client whose idnumber=ClientId;
    public ArrayList<CartItem> searchCartItems(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();
        ArrayList<CartItem> returnedcartitems=new ArrayList<CartItem>();

        if(allcartitems !=null)
        {
            for (CartItem allcartitem : allcartitems) {
                if (allcartitem.getCustomer().getUser_id().equals(ClientId)) {
                    returnedcartitems.add(allcartitem);
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

    void loadPage() throws IOException {
        itemscontainer.getChildren().removeAll(itemscontainer.getChildren());
        EmptyCartLabel.setVisible(false);
        ArrayList<CartItem> cartItems = searchCartItems(LoginClient_userId);
        ArrayList<Integer> amountOfItems= getamount(LoginClient_userId);
        double subtotal=0;
        if (cartItems != null) {
            if (cartItems.size() != 0) {
                itemscontainer.setMinHeight(cartItems.size()*127);      ///the height of the container is related to the amount of the items
                ArrayList<ImageView> arr=new ArrayList<ImageView>();
                for(int i=0;i<cartItems.size();i++)
                {
                    int itemamount=amountOfItems.get(i);
                    Pane p=new Pane();            //container of each item
                    p.setMinHeight(114);
                    p.setMinWidth(334);
                    ////////////// img /////////////
                    ImageView imageview=new ImageView();
                    imageview.setFitWidth(87);   //width of img
                    imageview.setFitHeight(95); //height of img
//                        System.out.println(cartItems.get(i).getUrl());
                    try{
                        File imageFile = new File(cartItems.get(i).getItem().getImgURL());
                        String fileLocation = imageFile.toURI().toString();
                        Image fxImage = new Image(fileLocation);
                        imageview.setImage(fxImage);
                    } catch (Exception e) {
//                        File imageFile = new File("Images/no_image.jpg");
//                        String fileLocation = imageFile.toURI().toString();
                        Image fxImage = new Image("https://www.dreamstime.com/no-image-available-icon-flat-vector-no-image-available-icon-flat-vector-illustration-image132482953");
                        imageview.setImage(fxImage);
                    }
                    imageview.setX(10);           //x & y coordinate related to the pane
                    imageview.setY(9);

                    //////////////// details of the item //////////////

                    ///////// name textfield ///////////
                    TextField name=new TextField("Name: "+ cartItems.get(i).getItem().getName());
                    name.setStyle("-fx-background-color:none");
                    name.setEditable(false);
                    name.setLayoutX(101);
                    name.setLayoutY(10);
                    ///////// price textfield ///////////
                    TextField price=new TextField("Type: "+ cartItems.get(i).getItem().getType());
                    price.setStyle("-fx-background-color:none;-fx-font-size: 12.3");
                    price.setEditable(false);
                    price.setLayoutX(101);
                    price.setLayoutY(32);


                    ///////// type textfield ///////////
                    TextField type=new TextField("Price: "+ cartItems.get(i).getItem().getPrice()+"₪");
                    if(cartItems.get(i).getItem().isUnderSale())
                    {
                        type.setText("Price after discount: " +cartItems.get(i).getItem().getPrice()+"₪");
                    }
                    type.setStyle("-fx-background-color:none;-fx-font-size: 12.3");
                    type.setLayoutX(101);
                    type.setLayoutY(54);

                    ///////// amount textfield ///////////
                    TextField amount=new TextField("Amount: "+ itemamount);
                    amount.setStyle("-fx-background-color:none;-fx-font-size: 12.3");
                    amount.setEditable(false);
                    amount.setLayoutX(101);
                    amount.setLayoutY(76);

                    ///////// delete button ///////////
                    Button btn=new Button();
                    btn.setLayoutX(300);
                    btn.setLayoutY(15);
                    btn.setText("x");
                    btn.setStyle("-fx-font-size:12.2;-fx-min-width: 23;-fx-min-height: 23;-fx-max-height: 23;-fx-max-height: 23;");
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
                    btn2.setLayoutX(300);
                    btn2.setLayoutY(75);
                    btn2.setText("-");
                    btn2.setId(Integer.toString(cartItems.get(i).getId()));
                    btn2.setStyle("-fx-font-size:13;-fx-min-width: 23;-fx-min-height: 23;-fx-max-height: 23;-fx-max-height: 23;");
                    btn2.setOnAction(e->{
                        int num= Integer.parseInt(((Button) e.getTarget()).getId());
                        try {
                            decrementAmountofCartItem(num);
                            loadPage();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    ///////// increment button ///////////
                    Button btn3=new Button();
                    btn3.setLayoutX(300);
                    btn3.setLayoutY(44);
                    btn3.setText("+");
                    btn3.setId(Integer.toString(cartItems.get(i).getId()));
                    btn3.setStyle("-fx-font-size:11;-fx-min-width: 23;-fx-min-height: 23;-fx-max-height: 23;-fx-max-height: 23;");
                    btn3.setOnAction(e->{
                        int num= Integer.parseInt(((Button) e.getTarget()).getId());
                        try {
                            incrementAmountofCartItem(num);
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
                    p.getChildren().add(btn3);

                    p.setStyle("-fx-border-color: #111F4D;-fx-border-radius: 15");

                    p.setLayoutX(10);
                    p.setLayoutY(124*i+10);

                    itemscontainer.getChildren().add(p);

                    Subtotal.setStyle("-fx-font-size: 17");
                    subtotal+=(cartItems.get(i).getItem().getPrice()*(itemamount));
                    if(LoginClient_acount_type.equals("Network account with 10% discount")&&subtotal>50)
                    {
                        Subtotal.setText("Subtotal after 10% discount: "+subtotal *0.9+" ₪");
                        Subtotal.setStyle("-fx-font-size:14;-fx-background-color:none;");
                        Subtotal.setMinWidth(225);
                        OrderSubtotal=  subtotal*0.9;
                    }
                    else
                    {
                        Subtotal.setText("Subtotal: "+ subtotal+" ₪");
                        OrderSubtotal=subtotal;
                    }
                }

            }
            else           /////////   if the cart is empty
            {
                CheckoutBtn.setDisable(true);
                EmptyCartLabel.setVisible(true);
                stores.setDisable(true);
                Subtotal.setText("Please add some item in your cart first");
                Subtotal.setStyle("-fx-font-size: 14");
            }
        }
    }

    @FXML
    void ComboChange(ActionEvent event) {
        OrderShop=stores.getValue();
    }


}
