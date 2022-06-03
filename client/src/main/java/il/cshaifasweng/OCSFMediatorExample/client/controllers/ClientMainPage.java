package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.*;

import  il.cshaifasweng.OCSFMediatorExample.client.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

import javax.transaction.Transactional;

public class ClientMainPage {

    @FXML // fx:id="Controller"
    private AnchorPane Controller; // Value injected by FXMLLoader

    @FXML // fx:id="LogOutBtn"
    private Button LogOutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="SendReportBtn"
    private Button SendReportBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cartBtn"
    private Button cartBtn; // Value injected by FXMLLoader

    @FXML // fx:id="catalogBtn"
    private Button catalogBtn; // Value injected by FXMLLoader

    @FXML // fx:id="messagetextfield"
    private TextField messagetextfield; // Value injected by FXMLLoader

    @FXML // fx:id="myOrdersBtn"
    private Button myOrdersBtn; // Value injected by FXMLLoader

    @FXML // fx:id="viewReportBtn"
    private Button viewReportBtn; // Value injected by FXMLLoader


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @FXML
    void CreateReport(ActionEvent event) throws IOException {
        App.setRoot("controllers/CreateReport");
    }

    @FXML
    void viewReport(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get current customer",LoginClient_username);
        SimpleClient.getClient().sendToServer(msg);
        System.out.println("msg sent to get current customer");
        System.out.println("no reports for customr"+(Customer)currentCustomerData);
        App.setRoot("controllers/showReportForCustomer");
    }

    @FXML
    void Cart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

    @FXML
    void catalog(ActionEvent event) throws IOException {
        getAllitems();
        System.out.println("msg sent to got shop items");
        App.setRoot("controllers/RegisteredCatalog");
    }


    @FXML
    void myOrders(ActionEvent event) throws IOException {
        App.setRoot("controllers/ClientOrders");
    }

    @FXML
    void initialize() throws IOException {
        ArrayList<Customer> customers=getAllCustomers();
        customers=getAllCustomers();
        messagetextfield.setEditable(false);
        Controller.setStyle("-fx-background-color: #D4F1F4");
//        myOrdersBtn.setStyle("-fx-border-radius: 25;-fx-background-color: rgba(117,230,218,0.8)");
        if(customers!=null)
        {
            for(int i=0;i<customers.size();i++)
            {
                if(customers.get(i).getUser_name().equals(LoginClient_username))
                {
                    messagetextfield.setText("Welcome Back "+customers.get(i).getFirst_name());
                    break;
                }
            }
        }

    }

    @FXML
    void logOutBtn(ActionEvent event) throws IOException {
        Login_customer.setOnline(false);
        updateCustomer(Login_customer);
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
