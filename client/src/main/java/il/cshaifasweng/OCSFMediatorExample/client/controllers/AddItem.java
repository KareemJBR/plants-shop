package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

public class AddItem {

    @FXML
    private CheckBox SaleCheck;

    @FXML
    private TextField SaleText;

    @FXML
    private Label colorLabel;

    @FXML
    private TextField colorText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField picText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField typeText;

    @FXML
    void addItem(ActionEvent event) throws IOException {
        if (Objects.equals(colorText.getText(), "")) {
            showAlert("Error", "please enter the item color");
            return;
        }
        if (Objects.equals(priceText.getText(), "")) {
            showAlert("Error", "please enter the item price");
            return;
        }
        if (Objects.equals(nameText.getText(), "")) {
            showAlert("Error", "please enter the item name");
            return;
        }

        if (SaleCheck.isSelected() && Objects.equals(SaleText.getText(), "")) {
            showAlert("Error", "please enter the item sale percent");
            return;
        }
        if (Objects.equals(typeText.getText(), "")) {
            showAlert("Error", "please enter the item picture");
            return;
        }
        Item newitem = new Item();
        newitem.setUnderSale(SaleCheck.isSelected());
        newitem.setOriginal_price(Integer.parseInt(priceText.getText()));
        newitem.setImgURL(picText.getText());
        newitem.setName(nameText.getText());
        newitem.setType(typeText.getText());
        if(SaleCheck.isSelected()){
            newitem.setSalePercent(Double.parseDouble(SaleText.getText())/100);
            newitem.setPrice((int)(newitem.getOriginal_price()* (1-newitem.getSalePercent())));
        }
        else{
            newitem.setSalePercent(0);
            newitem.setPrice(Integer.parseInt(priceText.getText()));
        }
        MsgClass msg = new MsgClass("#add new Item",newitem);
        SimpleClient.getClient().sendToServer(msg);
        showAlert("Success","Item Added");
        App.setRoot("controllers/NetWorkerHomePage");
    }


    @FXML
    void undableSale(ActionEvent event) {
        SaleText.setDisable(!SaleCheck.isSelected());
    }

    @FXML
    void goback(ActionEvent event) throws IOException {
        App.setRoot("controllers/NetWorkerHomePage");
    }

    public void initialize() {
        SaleText.setPromptText("Enter Sale Percent between 1-99%"); //to set the hint text
        SaleText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
        SaleText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            SaleText.setText(newValue.replaceAll("[^\\d]", ""));
        });

        priceText.setPromptText("Enter The Price"); //to set the hint text
        priceText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
        priceText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            priceText.setText(newValue.replaceAll("[^\\d]", ""));
        });

        nameText.setPromptText("Enter Item Name"); //to set the hint text
        nameText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately

        colorText.setPromptText("Enter The Color"); //to set the hint text
        colorLabel.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately

        priceText.setPromptText("Enter Item Price"); //to set the hint text
        priceText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately

        picText.setPromptText("Enter Image link");
        typeText.setPromptText("Enter Item Type");


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
