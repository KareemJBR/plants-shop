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

public class addItem {
    @FXML
    private Button Add;

    @FXML
    private Button Back;

    @FXML
    private CheckBox SaleCheck;

    @FXML
    private TextField SaleText;

    @FXML
    private Label colorLabel;

    @FXML
    private TextField colorText;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameText;

    @FXML
    private Label picLabel;

    @FXML
    private TextField picText;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField priceText;

    @FXML
    private Label typeLabel;

    @FXML
    private TextField typeText;

    @FXML
    private Label saleLabel;

    @FXML
    void addItem(ActionEvent event) throws IOException {
        if (colorText.getText() == null || colorText.getText().trim().isEmpty()) {
            showAlert("Error", "please enter the item color");
            return;
        }
        if (priceText.getText() == null || priceText.getText().trim().isEmpty()) {
            showAlert("Error", "please enter the item price");
            return;
        }
        if (nameText.getText() == null || nameText.getText().trim().isEmpty()) {
            showAlert("Error", "please enter the item name");
            return;
        }

        if ((SaleCheck.isSelected() && SaleText.getText() == null) || (SaleCheck.isSelected() && SaleText.getText().trim().isEmpty())) {
            showAlert("Error", "please enter the item sale percent");
            return;
        }
        if (typeText.getText() == null || typeText.getText().trim().isEmpty()) {
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
        if (SaleCheck.isSelected()) {
            SaleText.setDisable(false);
        } else {
            SaleText.setDisable(true);
        }
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

        priceText.setPromptText("Enter Picture Link"); //to set the hint text
        priceText.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately

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
