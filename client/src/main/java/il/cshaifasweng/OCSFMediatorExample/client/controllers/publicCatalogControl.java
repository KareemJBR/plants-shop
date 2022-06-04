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

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllItems;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllitemsUnderSale;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.allItemsData;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.SignUp.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class publicCatalogControl {
    private boolean allitemsfilter=true;

    private ArrayList<Item> allitems;
    private ArrayList<Item> saleitems;

    @FXML
    private Button backBtn;

    @FXML
    private Button singUp;

    @FXML
    private Button filterBTN;

    @FXML
    private ChoiceBox<String> filterSelect;

    @FXML
    private TableView table=new TableView<Item>();

    @FXML
    private AnchorPane itemscontainer;

    @FXML
    private Label title;

    @FXML
    private Label textFilter;

    @FXML
    private AnchorPane itemsContainer;
    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/LogIN");
    }

    @FXML
    void filter(ActionEvent event) throws IOException {
        if (filterSelect.getValue().toString().equals("All Items")) {
            allitemsfilter=true;
        } else {
            allitemsfilter=false;
        }
        loadcatalog();
    }



    @FXML
    void createAcount(ActionEvent event) throws IOException {
        shop=true;
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/SignUp");
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        filterSelect.getItems().addAll("All Items","Under Sale");
        filterSelect.getSelectionModel().select(0);
       loadcatalog();
    }

    public void loadcatalog() throws IOException {
        saleitems=getAllitemsUnderSale();
        allitems=getAllItems();
        itemscontainer.getChildren().removeAll(itemscontainer.getChildren());
        ArrayList<Item> items=null;
        if(allitemsfilter)
        {
            items = allitems;
        }
        else
        {
            items = saleitems;
        }
        boolean moveRight = false;
        int j = 0;
        if (items != null) {
            if (items.size() != 0) {
                itemscontainer.setMinHeight(items.size() * 150);      ///the height of the container is related to the amount of the items
                for (int i = 0; i < items.size(); i++) {
                    AnchorPane p = new AnchorPane();            //container of each item
                    p.setStyle("-fx-background-color: #E43A19");
                    p.setMinSize(350, 190);
                    if (i % 2 == 1) {
                        moveRight = true;
                    } else {
                        moveRight = false;
                    }
                    p.setLayoutX(5);
                    p.setLayoutY(5);
                    ////////////// img /////////////
                    ImageView imageview = new ImageView();
                    imageview.setFitWidth(180);   //width of img
                    imageview.setFitHeight(180); //height of img
                    System.out.println(i);
                    try{
                        File imageFile = new File(items.get(i).getImgURL());
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

                    if(items.get(i).isUnderSale()){
                        saleImg.setFitWidth(40);   //width of img
                        saleImg.setFitHeight(40); //height of img
                        saleImg.setLayoutX(305);           //x & y coordinate related in the pane
                        saleImg.setLayoutY(5);
                    }

                    //////////////// details of the item //////////////
                    ///////// price textfield ///////////
                    TextField name = new TextField("Name: " + items.get(i).getName());
                    name.setStyle("-fx-background-color:#F2F4F7");
                    name.setLayoutX(190);
                    name.setLayoutY(50);
                    name.setEditable(false);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + items.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color:#F2F4F7");
                    catologNum.setLayoutX(190);
                    catologNum.setLayoutY(70);
                    catologNum.setEditable(false);

                    ///////// type textfield ///////////
                    TextField type = new TextField("Type: " + items.get(i).getType());
                    type.setStyle("-fx-background-color:#F2F4F7");
                    type.setLayoutX(190);           //x & y coordinate related in the pane
                    type.setLayoutY(90);
                    type.setEditable(false);

                    TextField color = new TextField("Color: " + items.get(i).getColor());
                    color.setStyle("-fx-background-color:#F2F4F7");
                    color.setLayoutX(190);           //x & y coordinate related in the pane
                    color.setLayoutY(110);
                    color.setEditable(false);


                    ///////// type textfield ///////////
                    TextField price = new TextField();
                    TextField priceAfterSale = new TextField();
                    if(items.get(i).isUnderSale()) {
                        price.setText("Original Price: " + items.get(i).getOriginal_price());
                        priceAfterSale.setText("Price After "+items.get(i).getSalePercent()*100 +"% sale is:"+items.get(i).getPriceAfterSale());
                    }
                    else{
                        price.setText("Price :"+items.get(i).getOriginal_price());
                    }
                    price.setStyle("-fx-background-color:#F2F4F7");
                    price.setLayoutX(190);           //x & y coordinate related in the pane
                    price.setLayoutY(130);
                    priceAfterSale.setStyle("-fx-background-color:#F2F4F7");
                    priceAfterSale.setLayoutX(190);
                    priceAfterSale.setLayoutY(150);
                    price.setEditable(false);
                    priceAfterSale.setEditable(false);


                    /////////////// adding components to the pane /////////////
                    p.getChildren().add(imageview);
                    p.getChildren().add(price);
                    p.getChildren().add(type);
                    p.getChildren().add(name);
                    p.getChildren().add(color);
                    p.getChildren().add(catologNum);
                    if(items.get(i).isUnderSale()){
                        p.getChildren().add(saleImg);
                        p.getChildren().add(priceAfterSale);
                    }
                    if (moveRight) {
                        p.setLayoutY(240 * j);
                        p.setLayoutX(400);
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
