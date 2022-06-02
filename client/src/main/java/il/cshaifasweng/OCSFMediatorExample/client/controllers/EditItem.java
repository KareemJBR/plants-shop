package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EditItem {

    @FXML
    private Button addSale;


    @FXML
    private TextField saleText;

    @FXML
    private Button back;

    @FXML
    private Button editSale;

    @FXML
    private AnchorPane itemPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button removeSale;

    @FXML
    void DelteSale(ActionEvent event) throws IOException {
        Item item = App.getEdit_item();
        if(item.isUnderSale()==false){
            showAlert("Error","item isn't under sale ");
            return;
        }
        item.setSalePercent(0);
        item.setUnderSale(false);
        item.setPriceAfterSale(item.getOriginal_price());
        MsgClass msg = new MsgClass("#update Item",item);
        SimpleClient.getClient().sendToServer(msg);
        showAlert("successes","sale have been removed");
        App.setRoot("controllers/addSale");
    }

    @FXML
    void addSale(ActionEvent event) throws IOException {
        if(saleText.toString().equals("")||saleText.toString()==(null)){
            showAlert("Error","please enter the sale percent");
            return;
        }
        double sale =(Integer.parseInt(saleText.getText()));
        Item item = App.getEdit_item();
        if(sale<1||sale>99){
            showAlert("Error","sale percent should be between 1-99%");
            return;
        }
        if(item.isUnderSale()==true){
            showAlert("Error","item is already under sale");
            return;
        }
        item.setUnderSale(true);
        sale=sale/100;
        item.setSalePercent(sale);
        item.setPriceAfterSale((int)(item.getOriginal_price()*(1-sale)));
        MsgClass msg = new MsgClass("#update Item",item);
        SimpleClient.getClient().sendToServer(msg);
        showAlert("successes","sale  have been added");
        App.setRoot("controllers/addSale");

    }

    @FXML
    void editSalePercente(ActionEvent event) throws IOException {
        if(saleText.toString().equals("")||saleText.getText().equals(null)){
            showAlert("Eror","please enter the sale percent");
            return;
        }
        int sale =Integer.parseInt(saleText.getText());
        if(sale<1||sale>99){
            showAlert("Eror","sale percent should be between 1-99%");
            return;
        }

        Item item = App.getEdit_item();

        if(item.isUnderSale()==false){
            showAlert("Eror","the item isnt under sale");
            return;
        }
        sale=sale/100;
        item.setSalePercent(sale);
        item.setPriceAfterSale(item.getOriginal_price()*(1-sale));
        MsgClass msg = new MsgClass("#update Item",item);
        SimpleClient.getClient().sendToServer(msg);
        showAlert("successes","sale percent have been edited");
        App.setRoot("controllers/addSale");
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("controllers/addSale");
    }

    public void initialize() {
        Item item = App.getEdit_item();
        saleText.setPromptText("Enter Sale Percent between 1-99%"); //to set the hint text
        saleText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
        saleText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            saleText.setText(newValue.replaceAll("[^\\d]", ""));
        });

        ImageView imageview = new ImageView();
        imageview.setFitWidth(210);   //width of img
        imageview.setFitHeight(210); //height of img
        imageview.setImage(new Image(item.getUrl()));
        imageview.setLayoutX(5);           //x & y coordinate related in the pane
        imageview.setLayoutY(5);

        //////////////// details of the item //////////////
        ///////// price textfield ///////////
        TextField name = new TextField("Name: " + item.getName()  //"\n"
        );
        name.setStyle("-fx-background-color:#EC610A");
        name.setLayoutX(5);
        name.setLayoutY(220);
        name.setEditable(false);

        ///////// type catalog number ///////////
        TextField catologNum = new TextField("Catalog Number: " + item.getCatalogNumber());
        catologNum.setStyle("-fx-background-color:#EC610A");
        catologNum.setLayoutX(5);
        catologNum.setLayoutY(240);
        catologNum.setEditable(false);

        ///////// type catalog number ///////////
        TextField color = new TextField("Color: " + item.getColor());
        color.setStyle("-fx-background-color:#EC610A");
        color.setLayoutX(5);
        color.setLayoutY(260);
        color.setEditable(false);

        TextField underSale = new TextField("Under Sale: " + item.isUnderSale());
        underSale.setStyle("-fx-background-color:#EC610A");
        underSale.setLayoutX(5);
        underSale.setLayoutY(280);
        underSale.setEditable(false);

        TextField sale = new TextField("Sale Percent: " + item.getSalePercent()*100 + "%");
        sale.setStyle("-fx-background-color:#EC610A");
        sale.setLayoutX(5);
        sale.setLayoutY(300);
        sale.setEditable(false);

        ///////// type textfield ///////////
        TextField type = new TextField("Type: " + item.getType());
        type.setStyle("-fx-background-color:#EC610A");
        type.setLayoutX(5);           //x & y coordinate related in the pane
        type.setLayoutY(320);
        type.setEditable(false);

        ///////// type textfield ///////////
        TextField price = new TextField();
        TextField priceAfterSale = new TextField();
        price.setText("Original Price: " + item.getOriginal_price());
        priceAfterSale.setText("Final Price: " + item.getPriceAfterSale());

        price.setStyle("-fx-background-color:#EC610A");
        price.setLayoutX(5);           //x & y coordinate related in the pane
        price.setLayoutY(340);
        priceAfterSale.setStyle("-fx-background-color:#EC610A");
        priceAfterSale.setLayoutX(5);
        priceAfterSale.setLayoutY(360);
        price.setEditable(false);

        priceAfterSale.setEditable(false);

        itemPane.getChildren().add(imageview);
        itemPane.getChildren().add(price);
        itemPane.getChildren().add(type);
        itemPane.getChildren().add(color);
        itemPane.getChildren().add(underSale);
        itemPane.getChildren().add(sale);
        itemPane.getChildren().add(catologNum);
        itemPane.getChildren().add(priceAfterSale);
        itemPane.setMaxWidth(400);
        itemPane.setMaxHeight(375);

        if (item.isUnderSale()){
            addSale.setDisable(true);
        }
        else{
            addSale.setDisable(false);
        }
        if(item.isUnderSale()==false){
            removeSale.setDisable(true);
            editSale.setDisable(true);
        }
        else{
            removeSale.setDisable(false);
            editSale.setDisable(false);
        }
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
