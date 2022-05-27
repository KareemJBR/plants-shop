package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.Itemsdata;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.SignUp.shop;
import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;

public class publicCatalogControl {

    @FXML
    private Button backBtn;

    @FXML
    private Button singUp;



    @FXML
    private TableView table=new TableView<Item>();

    @FXML
    private AnchorPane itemscontainer;

    @FXML
    private TextField title;


    @FXML
    private AnchorPane itemsContainer;
    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/LogIN");
    }

    @FXML
    void createAcount(ActionEvent event) throws IOException {
        shop=true;
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/SignUp");
        //SignUp.main(null);
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        ArrayList<Item> cartItems = (ArrayList<Item>) Itemsdata;
        itemscontainer.setStyle("-fx-background-color: #222831");
        backBtn.setStyle("-fx-background-color:#EEEEEE");
        singUp.setStyle("-fx-background-color:#EEEEEE");
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
                    TextField name = new TextField("Name: " + cartItems.get(i).getName());
                    name.setStyle("-fx-background-color:#00ADB5");
                    name.setLayoutX(140);
                    name.setLayoutY(30);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + cartItems.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color:#00ADB5");
                    catologNum.setLayoutX(140);
                    catologNum.setLayoutY(50);

                    ///////// type textfield ///////////
                    TextField type = new TextField("type: " + cartItems.get(i).getType());
                    type.setStyle("-fx-background-color:#00ADB5");
                    type.setLayoutX(140);           //x & y coordinate related in the pane
                    type.setLayoutY(70);

                    ///////// type textfield ///////////
                    TextField price = new TextField("type: " + cartItems.get(i).getPrice());
                    price.setStyle("-fx-background-color:#00ADB5");
                    price.setLayoutX(140);           //x & y coordinate related in the pane
                    price.setLayoutY(90);


                    /////////////// adding components to the pane /////////////
                    p.getChildren().add(imageview);
                    p.getChildren().add(price);
                    p.getChildren().add(type);
                    p.getChildren().add(name);
                    p.getChildren().add(catologNum);
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
