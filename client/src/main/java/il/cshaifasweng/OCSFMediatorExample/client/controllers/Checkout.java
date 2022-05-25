package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static il.cshaifasweng.OCSFMediatorExample.client.controllers.Cart.OrderSubtotal;

public class Checkout {

    @FXML // fx:id="AddressTextFeild"
    private TextField AddressTextFeild; // Value injected by FXMLLoader

    @FXML // fx:id="CashRadioBtn"
    private RadioButton CashRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="CheckoutBtn"
    private Button CheckoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="CreditcardRadioBtn"
    private RadioButton CreditcardRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="DeliveryRadioBtn"
    private RadioButton DeliveryRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="NameTextFeild"
    private TextField NameTextFeild; // Value injected by FXMLLoader

    @FXML // fx:id="PhoneTextFeild"
    private TextField PhoneTextFeild; // Value injected by FXMLLoader

    @FXML // fx:id="PickupRadioBtn"
    private RadioButton PickupRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ReceiverDetailsLabel"
    private Label ReceiverDetailsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="SubtotalLabel"
    private Label SubtotalLabel; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="datew"
    private DatePicker datew; // Value injected by FXMLLoader


    @FXML
    public void initialize() throws IOException, InterruptedException {
        datew.setValue(LocalDate.now());
      SubtotalLabel.setText("Subtotal: "+ OrderSubtotal);
    }

    @FXML
    void Checkdate(ActionEvent event) {
       if(LocalDate.now().isAfter(datew.getValue()))    //if the client entered and old date
       {
           datew.setValue(LocalDate.now());
       }
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }


    @FXML
    void CashRadioBtnChange(ActionEvent event) {
           if(CashRadioBtn.isSelected())
           {
               CashRadioBtn.setSelected(true);
               CreditcardRadioBtn.setSelected(false);
           }
           else
           {
               CashRadioBtn.setSelected(false);
               CreditcardRadioBtn.setSelected(true);
           }
    }

    @FXML
    void Checkout(ActionEvent event) {
        if(DeliveryRadioBtn.isSelected())
        {
            if(NameTextFeild.getText().equals("") || PhoneTextFeild.getText().equals("") || AddressTextFeild.getText().equals(""))
            {
                showAlert("error","please fill out all Receiver details");
                if(NameTextFeild.getText().equals(""))
                {
                    NameTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if(PhoneTextFeild.getText().equals(""))
                {
                    PhoneTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if(AddressTextFeild.getText().equals(""))
                {
                    AddressTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if(datew.getValue().equals(""))
                {
                    datew.setStyle("-fx-background-color:#f5c0c0;");
                }
            }
            else
            {

            }

        }
        else
        {

        }
    }

    @FXML
    void CreditcardRadioBtnChange(ActionEvent event) {
        if(CreditcardRadioBtn.isSelected())
        {
            CashRadioBtn.setSelected(false);
            CreditcardRadioBtn.setSelected(true);
        }
        else
        {
            CashRadioBtn.setSelected(true);
            CreditcardRadioBtn.setSelected(false);
        }
    }

    @FXML
    void DeliveryRadioBtnChange(ActionEvent event) {
        if(DeliveryRadioBtn.isSelected())
        {
            DeliveryRadioBtn.setSelected(true);
            PickupRadioBtn.setSelected(false);
            ReceiverDetailsLabel.setVisible(true);
            PhoneTextFeild.setVisible(true);
            NameTextFeild.setVisible(true);
            AddressTextFeild.setVisible(true);
            datew.setVisible(true);
        }
        else
        {
            DeliveryRadioBtn.setSelected(false);
            PickupRadioBtn.setSelected(true);
            ReceiverDetailsLabel.setVisible(false);
            PhoneTextFeild.setVisible(false);
            NameTextFeild.setVisible(false);
            AddressTextFeild.setVisible(false);
            datew.setVisible(false);
        }
    }

    @FXML
    void PickupRadioBtnChange(ActionEvent event) {
        if(PickupRadioBtn.isSelected())
        {
            DeliveryRadioBtn.setSelected(false);
            PickupRadioBtn.setSelected(true);
            ReceiverDetailsLabel.setVisible(false);
            PhoneTextFeild.setVisible(false);
            NameTextFeild.setVisible(false);
            AddressTextFeild.setVisible(false);
            datew.setVisible(false);
        }
        else
        {
            DeliveryRadioBtn.setSelected(true);
            PickupRadioBtn.setSelected(false);
            ReceiverDetailsLabel.setVisible(true);
            PhoneTextFeild.setVisible(true);
            NameTextFeild.setVisible(true);
            AddressTextFeild.setVisible(true);
            datew.setVisible(true);
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