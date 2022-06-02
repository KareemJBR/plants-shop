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
    void createAcount(ActionEvent event) throws IOException {
        shop=true;
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/SignUp");
        //SignUp.main(null);
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        ArrayList<Item> allItems = App.getAllItems();
        itemscontainer.setStyle("-fx-background-color: #404B69");
        backBtn.setStyle("-fx-background-color:#DBEDF3");
        singUp.setStyle("-fx-background-color:#DBEDF3");
        title.setStyle("-fx-background-color:#DBEDF3");
        itemsContainer.setStyle("-fx-background-color:#283149");
        textFilter.setStyle("-fx-background-color:#DBEDF3");
        filterBTN.setStyle("-fx-background-color:#DBEDF3");
        filterSelect.setStyle("-fx-background-color:#DBEDF3");
        filterSelect.getItems().addAll("All Items","Under Sale");
        boolean moveRight = false;
        int j = 0;
        if (allItems != null) {
            if (allItems.size() != 0) {
                itemscontainer.setMinHeight(allItems.size() * 80);      ///the height of the container is related to the amount of the items
                for (int i = 0; i < allItems.size(); i++) {
                    AnchorPane p = new AnchorPane();            //container of each item
                    p.setStyle("-fx-background-color: #283149");
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
                    imageview.setImage(new Image(allItems.get(i).getUrl()));
                    imageview.setLayoutX(5);           //x & y coordinate related in the pane
                    imageview.setLayoutY(5);
                    ImageView saleImg = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5_sSCL4v_OTxw8XXoGNcWeV0rYEV0e76Nsw&usqp=CAU");

                    if(allItems.get(i).isUnderSale()){
                        saleImg.setFitWidth(20);   //width of img
                        saleImg.setFitHeight(20); //height of img
                        saleImg.setLayoutX(270);           //x & y coordinate related in the pane
                        saleImg.setLayoutY(5);
                    }

                    //////////////// details of the item //////////////
                    ///////// price textfield ///////////
                    TextField name = new TextField("Name: " + allItems.get(i).getName());
                    name.setStyle("-fx-background-color:#EC610A");
                    name.setLayoutX(140);
                    name.setLayoutY(30);
                    name.setEditable(false);

                    ///////// type catalog number ///////////
                    TextField catologNum = new TextField("Catalog Number: " + allItems.get(i).getCatalogNumber());
                    catologNum.setStyle("-fx-background-color:#EC610A");
                    catologNum.setLayoutX(140);
                    catologNum.setLayoutY(50);
                    catologNum.setEditable(false);

                    ///////// type textfield ///////////
                    TextField type = new TextField("Type: " + allItems.get(i).getType());
                    type.setStyle("-fx-background-color:#EC610A");
                    type.setLayoutX(140);           //x & y coordinate related in the pane
                    type.setLayoutY(70);
                    type.setEditable(false);

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
                    price.setStyle("-fx-background-color:#EC610A");
                    price.setLayoutX(140);           //x & y coordinate related in the pane
                    price.setLayoutY(90);
                    priceAfterSale.setStyle("-fx-background-color:#EC610A");
                    priceAfterSale.setLayoutX(140);
                    priceAfterSale.setLayoutY(110);
                    price.setEditable(false);
                    priceAfterSale.setEditable(false);


                    /////////////// adding components to the pane /////////////
                    p.getChildren().add(imageview);
                    p.getChildren().add(price);
                    p.getChildren().add(type);
                    p.getChildren().add(name);
                    p.getChildren().add(catologNum);
                    if(allItems.get(i).isUnderSale()){
                        p.getChildren().add(saleImg);
                        p.getChildren().add(priceAfterSale);
                    }
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
