package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllOrders;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllCustomers;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_username;
import  il.cshaifasweng.OCSFMediatorExample.client.*;
import javafx.stage.Stage;

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
    private Button SendReportBtn;

    @FXML
    private Button viewReportBtn;

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @FXML
    void CreateReport(ActionEvent event) throws IOException {
        App.setRoot("controllers/CreateReport");
    }

    @FXML
    void viewReport(ActionEvent event) {

    }

    @FXML
    void Cart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

    @FXML
    void catalog(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get Items",null);
        SimpleClient.getClient().sendToServer(msg);
        System.out.println("msg sent to got shop items");
        App.setRoot("controllers/RegisteredCatalog");
    }

    @FXML
    void customizedOrder(ActionEvent event) throws IOException {
        App.setRoot("controllers/CustomizedOrder");
    }
    @FXML
    void myOrders(ActionEvent event) throws IOException {
        //showAlert("username","username: "+ Client_username);

        ArrayList<Order> orders=getAllOrders();
        if(orders!=null)
        {
            if(orders.size()!=0)
            {

                for(int i=0;i<orders.size();i++)
                {
                    System.out.println(orders.get(i).getItems().size());
                }
            }
        }
    }

    @FXML
    void initialize() throws IOException {
        ArrayList<Customer> customers=getAllCustomers();
        customers=getAllCustomers();
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
