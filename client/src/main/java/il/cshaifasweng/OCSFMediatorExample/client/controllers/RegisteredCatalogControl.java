package il.cshaifasweng.OCSFMediatorExample.client.controllers;


import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;

public class RegisteredCatalogControl {
    private boolean allitemsfilter = true;

    private ArrayList<Item> allitems1;
    private ArrayList<Item> saleitems;


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
    void filter(ActionEvent event) throws IOException {
        if (filterSelect.getValue().toString().equals("All Items")) {
            allitemsfilter = true;
        } else {
            allitemsfilter = false;
        }
        loadcatalog();
    }

    @FXML
    void goCart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

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
        filterSelect.getItems().addAll("All Items", "Under Sale");
        filterSelect.getSelectionModel().select(0);
        loadcatalog();
    }

    public void loadcatalog() throws IOException {
        saleitems = getAllitemsUnderSale();
        allitems1 = getAllItems();
        ArrayList<Item> allItems = null;
        if (allitemsfilter) {
            allItems = allitems1;
        } else {
            allItems = saleitems;
        }
        itemscontainer.getChildren().removeAll(itemscontainer.getChildren());
        boolean moveRight = false;
        int j = 0;
        if (allItems != null) {
            if (allItems.size() != 0) {
                itemscontainer.setMinHeight(allItems.size() * 150);      ///the height of the container is related to the amount of the items
                for (int i = 0; i < allItems.size(); i++) {
                    AnchorPane p = new AnchorPane();            //container of each item
                    p.setStyle("-fx-background-color: #243447");
                    p.setMinSize(355, 175);
                    if (i % 2 == 1) {
                        moveRight = true;
                    } else {
                        moveRight = false;
                    }
                    ////////////// img /////////////
                    ImageView imageview = new ImageView();
                    imageview.setFitWidth(165);   //width of img
                    imageview.setFitHeight(165); //height of img
                    System.out.println(i);
                    try {
                        File imageFile = new File(allItems.get(i).getImgURL());
                        String fileLocation = imageFile.toURI().toString();
                        Image fxImage = new Image(fileLocation);
                        imageview.setImage(fxImage);
                    } catch (Exception e) {
                        File imageFile = new File("Images/no_image.jpg");
                        String fileLocation = imageFile.toURI().toString();
                        Image fxImage = new Image(fileLocation);
                        imageview.setImage(fxImage);
                    }
                    imageview.setLayoutX(5);           //x & y coordinate related in the pane
                    imageview.setLayoutY(5);

                    File imageFile = new File("Images/sale_image.jpg");
                    String fileLocation = imageFile.toURI().toString();
                    Image fxImage = new Image(fileLocation);
                    ImageView saleImg = new ImageView();
                    saleImg.setImage(fxImage);

                    if (allItems.get(i).isUnderSale()) {
                        saleImg.setFitWidth(30);   //width of img
                        saleImg.setFitHeight(30); //height of img
                        saleImg.setLayoutX(320);           //x & y coordinate related in the pane
                        saleImg.setLayoutY(140);
                    }

                    //////////////// details of the item //////////////
                    ///////// price textfield ///////////
                    TextField name = new TextField("Name: " + allItems.get(i).getName()  //"\n"
                            //  + "Catalog Number: " + cartItems.get(i).getCatalogNumber() + "\n"
                            //  + "Type: " + cartItems.get(i).getType() + "\n"
                            //  + "Price: " + cartItems.get(i).getPrice() + "\n"
                    );
                    name.setStyle("-fx-background-color: #F2F4F7");
                    name.setLayoutX(175);
                    name.setLayoutY(10);
                    name.setMinWidth(175);
                    name.setEditable(false);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + allItems.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color: #F2F4F7");
                    catologNum.setLayoutX(175);
                    catologNum.setLayoutY(30);
                    catologNum.setMinWidth(175);
                    catologNum.setEditable(false);

                    ///////// type textfield ///////////
                    TextField type = new TextField("Type: " + allItems.get(i).getType());
                    type.setStyle("-fx-background-color: #F2F4F7");
                    type.setLayoutX(175);           //x & y coordinate related in the pane
                    type.setLayoutY(50);
                    type.setMinWidth(175);
                    type.setEditable(false);

                    TextField color = new TextField("Color: " + allItems.get(i).getColor());
                    color.setStyle("-fx-background-color: #F2F4F7");
                    color.setLayoutX(175);           //x & y coordinate related in the pane
                    color.setLayoutY(70);
                    color.setMinWidth(175);
                    color.setEditable(false);


                    ///////// type textfield ///////////
                    TextField price = new TextField();
                    TextField priceAfterSale = new TextField();
                    if (allItems.get(i).isUnderSale()) {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Price After " + allItems.get(i).getSalePercent() * 100 + "% sale:" + allItems.get(i).getPriceAfterSale());
                    } else {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Final Price: " + allItems.get(i).getOriginal_price());
                    }
                    price.setStyle("-fx-background-color: #F2F4F7");
                    price.setLayoutX(175);           //x & y coordinate related in the pane
                    price.setLayoutY(90);
                    price.setMinWidth(175);
                    priceAfterSale.setStyle("-fx-background-color:  #F2F4F7");
                    priceAfterSale.setLayoutX(175);
                    priceAfterSale.setLayoutY(110);
                    priceAfterSale.setMinWidth(175);
                    price.setEditable(false);
                    priceAfterSale.setEditable(false);


                    ////////////////////// buttons ///////////////////
                    Button addToCartbtn = new Button();

                    addToCartbtn.setText("Add To Cart");
                    ArrayList<Item> finalAllItems = allItems;
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
                            CartItem newItem = new CartItem(getCurrentCustomer(), finalAllItems.get(num), 1);
                            MsgClass addCartItm = new MsgClass("#add cartItem", newItem);
                            SimpleClient.getClient().sendToServer(addCartItm);
                            System.out.println("the item you whant is " + num);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    addToCartbtn.setFont(new Font(14));
                    addToCartbtn.setStyle("-fx-background-color:#6366f1; -fx-text-fill: #F2F4F7; -fx-background-radius: 20");
                    addToCartbtn.setLayoutX(210);
                    addToCartbtn.setLayoutY(140);

                    addToCartbtn.setId(String.valueOf(i));

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
                        p.setLayoutY(220 * j);
                        p.setLayoutX(395);
                        j++;
                    } else {
                        p.setLayoutY(220 * j);
                    }
                    itemscontainer.getChildren().add(p);
                }
            }
        }
    }
}
