package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllCustomers;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.Client_username;
import  il.cshaifasweng.OCSFMediatorExample.client.*;
public class ClientMainPage {

    @FXML // fx:id="LogOutBtn"
    private Button LogOutBtn; // Value injected by FXMLLoader
    @FXML
    private Button cartBtn;

    @FXML
    private Button catalogBtn;

    @FXML
    private Button customizedOrderBtn;
    @FXML
    private TextField messagetextfield;

    @FXML
    void Cart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

    @FXML
    void catalog(ActionEvent event) throws IOException {
         MsgClass msg=new MsgClass("#get shop items",null);
         SimpleClient.getClient().sendToServer(msg);
        System.out.println("msg sent to got flowers");
        App.setRoot("controllers/RegisteredCatalog");
    }

    @FXML
    void customizedOrder(ActionEvent event) throws IOException {
        App.setRoot("controllers/CustomizedOrder");
    }
    @FXML
    void myOrders(ActionEvent event) {
        //showAlert("username","username: "+ Client_username);
    }

    @FXML
    void initialize() throws IOException {
        ArrayList<Customer> customers=getAllCustomers();
        customers=getAllCustomers();
        if(customers!=null)
        {
            for(int i=0;i<customers.size();i++)
            {
                if(customers.get(i).getUser_name().equals(Client_username))
                {
                    messagetextfield.setText("Welcome Back "+customers.get(i).getFirst_name());
                    break;
                }
            }
        }

    }

    @FXML
    void logOutBtn(ActionEvent event) throws IOException {
        App.setRoot("controllers/Login");
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
