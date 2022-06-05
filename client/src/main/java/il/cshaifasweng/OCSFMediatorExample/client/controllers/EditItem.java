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
import javafx.scene.text.Font;

import java.io.File;
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
        double sale =Integer.parseInt(saleText.getText());
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
        item.setPriceAfterSale((int)(item.getOriginal_price()*(1-sale)));
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
        saleText.setPromptText("Enter Sale Percent"); //to set the hint text
        saleText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
        saleText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            saleText.setText(newValue.replaceAll("[^\\d]", ""));
        });

        ImageView imageview = new ImageView();
        imageview.setFitWidth(280);   //width of img
        imageview.setFitHeight(280); //height of img
        try {
            File imageFile = new File(item.getImgURL());
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
        imageview.setLayoutY(50);

        //////////////// details of the item //////////////
        ///////// price textfield ///////////
        TextField name = new TextField("Name: " + item.getName()  //"\n"
        );
        name.setFont(new Font(14));
        name.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        name.setLayoutX(300);
        name.setLayoutY(100);
        name.setMinWidth(230);
        name.setEditable(false);

        ///////// type catalog number ///////////
        TextField catologNum = new TextField("Catalog Number: " + item.getCatalogNumber());
        catologNum.setFont(new Font(14));
        catologNum.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        catologNum.setLayoutX(300);
        catologNum.setLayoutY(125);
        catologNum.setMinWidth(230);
        catologNum.setEditable(false);

        ///////// type catalog number ///////////
        TextField color = new TextField("Color: " + item.getColor());
        color.setFont(new Font(14));
        color.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        color.setLayoutX(300);
        color.setLayoutY(150);
        color.setMinWidth(230);
        color.setEditable(false);

        TextField underSale = new TextField("Under Sale: " + item.isUnderSale());
        underSale.setFont(new Font(15));
        underSale.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        underSale.setLayoutX(300);
        underSale.setLayoutY(175);
        underSale.setMinWidth(230);
        underSale.setEditable(false);

        TextField sale = new TextField("Sale Percent: " + item.getSalePercent()*100 + "%");
        sale.setFont(new Font(14));
        sale.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        sale.setLayoutX(300);
        sale.setLayoutY(200);
        sale.setMinWidth(230);
        sale.setEditable(false);

        ///////// type textfield ///////////
        TextField type = new TextField("Type: " + item.getType());
        type.setFont(new Font(14));
        type.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        type.setLayoutX(300);           //x & y coordinate related in the pane
        type.setLayoutY(225);
        type.setMinWidth(230);

        type.setEditable(false);

        ///////// type textfield ///////////
        TextField price = new TextField();
        TextField priceAfterSale = new TextField();
        price.setText("Original Price: " + item.getOriginal_price());
        priceAfterSale.setText("Final Price: " + item.getPriceAfterSale());

        price.setFont(new Font(14));
        price.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        price.setLayoutX(300);           //x & y coordinate related in the pane
        price.setLayoutY(250);
        price.setMinWidth(230);
        priceAfterSale.setFont(new Font(14));
        priceAfterSale.setStyle("-fx-background-color:#EC610A;-fx-text-fill: #F2F4F7 ");
        priceAfterSale.setLayoutX(300);
        priceAfterSale.setLayoutY(275);
        priceAfterSale.setMinWidth(230);
        price.setEditable(false);

        priceAfterSale.setEditable(false);

        itemPane.setStyle("-fx-background-color:  #0c1738");
        itemPane.getChildren().add(imageview);
        itemPane.getChildren().add(price);
        itemPane.getChildren().add(type);
        itemPane.getChildren().add(color);
        itemPane.getChildren().add(underSale);
        itemPane.getChildren().add(sale);
        itemPane.getChildren().add(catologNum);
        itemPane.getChildren().add(priceAfterSale);
        itemPane.getChildren().add(name);

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
