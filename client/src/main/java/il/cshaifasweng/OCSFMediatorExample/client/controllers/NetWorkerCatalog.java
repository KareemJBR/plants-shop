package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CartItem;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.allItemsData;

public class NetWorkerCatalog {

    @FXML
    private Button BackBTN;

    @FXML
    private AnchorPane Container;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("controllers/NetWorkerHomePage");
    }

    public void initialize() throws IOException {

        ArrayList<Item> allItems = (ArrayList<Item>) allItemsData;
        boolean moveRight = false;
        int j = 0;
        if (allItems != null) {
            if (allItems.size() != 0) {
                Container.setMinHeight(allItems.size() * 150);      ///the height of the container is related to the amount of the items
                ArrayList<TextField> newPrices = new ArrayList<>();
                for (int i = 0; i < allItems.size(); i++) {
                    AnchorPane p = new AnchorPane();            //container of each item
                    p.setStyle("-fx-background-color: #1e2850");
                    p.setMinSize(360, 200);
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

                    ///////// type textfield ///////////
                    TextField price = new TextField();
                    TextField priceAfterSale = new TextField();
                    if (allItems.get(i).isUnderSale()) {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Price After " + allItems.get(i).getSalePercent() * 100 + "% sale is:" + allItems.get(i).getPriceAfterSale());
                    } else {
                        price.setText("Original Price: " + allItems.get(i).getOriginal_price());
                        priceAfterSale.setText("Final Price: " + allItems.get(i).getOriginal_price());
                    }
                    price.setStyle("-fx-background-color:#F2F4F7");
                    price.setLayoutX(200);           //x & y coordinate related in the pane
                    price.setLayoutY(90);
                    priceAfterSale.setStyle("-fx-background-color:#F2F4F7");
                    priceAfterSale.setLayoutX(200);
                    priceAfterSale.setLayoutY(110);
                    price.setEditable(false);
                    priceAfterSale.setEditable(false);


                    TextField newPrice = new TextField();
                    newPrice.setLayoutX(200);           //x & y coordinate related in the pane
                    newPrice.setLayoutY(155);
                    newPrice.setEditable(true);
                    newPrice.setMaxWidth(80);
                    newPrice.setId(String.valueOf(i));
                    p.getChildren().add(newPrice);
                    newPrice.setPromptText("New Price"); //to set the hint text
                    newPrice.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
                    newPrices.add(newPrice);
                    newPrice.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.matches("\\d*")) return;
                        newPrice.setText(newValue.replaceAll("[^\\d]", ""));
                    });
                    ////////////////////// buttons ///////////////////
                    Button updatePrice = new Button();
                    updatePrice.setText("Update Price");
                    updatePrice.setOnAction(e -> {
                        try {
                            int num = Integer.parseInt(((Button) e.getTarget()).getId());
                            if (newPrices.get(num).getText().equals("")) {
                                showAlert("Eror", "Please enter new price");
                                return;
                            }

                            updatePrice(allItems.get(num), Integer.parseInt(newPrices.get(num).getText()));
                            MsgClass newMsg = new MsgClass("#update Item",allItems.get(num));
                            SimpleClient.getClient().sendToServer(newMsg);
//                            getAllitems();
//                            MsgClass reload = new MsgClass("#reload for all clients");
//                            SimpleClient.getClient().sendToServer(reload);
                            App.setRoot("controllers/NetWorkerCatalog");
                            showAlert("Success", "Item price updated");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    updatePrice.setFont(new Font(12));
                    updatePrice.setStyle("-fx-background-color:#E43A19;-fx-background-radius: 20;-fx-text-fill: #F2F4F7");
                    updatePrice.setLayoutX(280);
                    updatePrice.setLayoutY(155);

                    updatePrice.setId(String.valueOf(i));

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
                    p.getChildren().add(catologNum);
                    p.getChildren().add(priceAfterSale);
                    if (allItems.get(i).isUnderSale()) {
                        p.getChildren().add(saleImg);
                    }
                    p.getChildren().add(updatePrice);
                    if (moveRight) {
                        p.setLayoutY(240 * j);
                        p.setLayoutX(390);
                        j++;
                    } else {
                        p.setLayoutY(240 * j);
                    }
                    Container.getChildren().add(p);
                }
            }
        }
    }

    private static void updatePrice(Item flower, int price) {
        System.out.println(price);
        System.out.println(flower);
        flower.setOriginal_price(price);
        if (flower.isUnderSale()) {
            flower.setPriceAfterSale((int) (flower.getOriginal_price() * (1 - flower.getSalePercent())));
        }
        System.out.println(flower);
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
}