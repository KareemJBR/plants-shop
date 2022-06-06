package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.*;


public class ClientMainPage {

    @FXML // fx:id="messagetextfield"
    private TextField messagetextfield; // Value injected by FXMLLoader


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
        App.setRoot("controllers/ShowReportForCustomer");
    }

    @FXML
    void Cart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

    @FXML
    void catalog(ActionEvent event) throws IOException {
        getAllItems();
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
//        myOrdersBtn.setStyle("-fx-border-radius: 25;-fx-background-color: rgba(117,230,218,0.8)");
        if(customers!=null)
        {
            for (Customer customer : customers) {
                if (customer.getUser_name().equals(LoginClient_username)) {
                    messagetextfield.setText("Welcome Back " + customer.getFirst_name());
                    break;
                }
            }
        }

    }

    @FXML
    void logOutBtn(ActionEvent event) throws IOException {
        Login_customer.setOnline(false);
        updateCustomer(Login_customer);
        App.setRoot("controllers/LogIN");
    }
}
