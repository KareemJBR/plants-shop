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

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllitemsUnderSale;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.allItemsData;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.SignUp.shop;
import java.io.IOException;
import java.util.ArrayList;


public class publicCatalogControl {

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
            App.getAllItems();
        } else {
            getAllitemsUnderSale();
        }
        App.setRoot("controllers/publicCatalog");
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
        ArrayList<Item> allItems = (ArrayList<Item>) allItemsData;
        filterSelect.getItems().addAll("All Items","Under Sale");
        filterSelect.getSelectionModel().select(0);
        boolean moveRight = false;
        int j = 0;
        if (allItems != null) {
            if (allItems.size() != 0) {
                itemscontainer.setMinHeight(allItems.size() * 150);      ///the height of the container is related to the amount of the items
                for (int i = 0; i < allItems.size(); i++) {
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
                        imageview.setImage(new Image(allItems.get(i).getUrl()));
                    } catch (Exception e) {
                        imageview.setImage(new Image("https://us.123rf.com/450wm/pavelstasevich/pavelstasevich1811/pavelstasevich181101027/112815900-no-image-available-icon-flat-vector.jpg?ver=6"));
                    }
                    imageview.setLayoutX(5);           //x & y coordinate related in the pane
                    imageview.setLayoutY(5);
                    ImageView saleImg = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5_sSCL4v_OTxw8XXoGNcWeV0rYEV0e76Nsw&usqp=CAU");

                    if(allItems.get(i).isUnderSale()){
                        saleImg.setFitWidth(40);   //width of img
                        saleImg.setFitHeight(40); //height of img
                        saleImg.setLayoutX(305);           //x & y coordinate related in the pane
                        saleImg.setLayoutY(5);
                    }

                    //////////////// details of the item //////////////
                    ///////// price textfield ///////////
                    TextField name = new TextField("Name: " + allItems.get(i).getName());
                    name.setStyle("-fx-background-color:#F2F4F7");
                    name.setLayoutX(190);
                    name.setLayoutY(50);
                    name.setEditable(false);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + allItems.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color:#F2F4F7");
                    catologNum.setLayoutX(190);
                    catologNum.setLayoutY(70);
                    catologNum.setEditable(false);

                    ///////// type textfield ///////////
                    TextField type = new TextField("Type: " + allItems.get(i).getType());
                    type.setStyle("-fx-background-color:#F2F4F7");
                    type.setLayoutX(190);           //x & y coordinate related in the pane
                    type.setLayoutY(90);
                    type.setEditable(false);

                    TextField color = new TextField("Color: " + allItems.get(i).getColor());
                    color.setStyle("-fx-background-color:#F2F4F7");
                    color.setLayoutX(190);           //x & y coordinate related in the pane
                    color.setLayoutY(110);
                    color.setEditable(false);


                    ///////// type textfield ///////////
                    TextField price = new TextField();
                    TextField priceAfterSale = new TextField();
                    if(allItems.get(i).isUnderSale()) {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Price After "+allItems.get(i).getSalePercent()*100 +"% sale is:"+allItems.get(i).getPriceAfterSale());
                    }
                    else{
                        price.setText("Price :"+allItems.get(i).getOriginal_price());
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
                    if(allItems.get(i).isUnderSale()){
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
