package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.SignUp.shop;

public class LogIN {
    public static  String LoginClient_username;
    public static  String LoginWorker_username;
    public static  String LoginClient_userId;
    public static  String LoginClient_acount_type;

    public static Customer Login_customer;

    public static NetWorker Login_netWorker;
    public static SupportWorker Login_supportWorker;

    public static ShopAdmin Login_shopAdmin;
    String  current;
    String password_status="invisible";

    @FXML // fx:id="Password"
    private PasswordField Password; // Value injected by FXMLLoader

    @FXML // fx:id="showPassword"
    private CheckBox showPassword; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML // fx:id="visiblePassword"
    private TextField visiblePassword; // Value injected by FXMLLoader


    @FXML
    void createAcount(ActionEvent event) throws IOException {
        shop=false;
       App.setRoot("controllers/SignUp");
    }
    @FXML
    void logIN(ActionEvent event) throws IOException {
        ArrayList<Customer> customers=getAllCustomers();
        ArrayList<NetWorker> net_workers = getAllNetWorkers();
        ArrayList<SupportWorker> support_workers =getAllSupportWorkers();
        ArrayList<ShopAdmin> shopAdmins = getAllShopAdmins();

        if(Objects.equals(password_status, "visible"))
            current=visiblePassword.getText();
        else
            current=Password.getText();

        if(userName.getText().equals("admin")&&current.equals("admin"))
        {
            App.setRoot("controllers/AdministratorHomePage");
            return;
        }
        if(customers!=null)
            for (Customer customer : customers)
                if (customer.getUser_name().equals(userName.getText()) && customer.getPassword().equals(current)) {
                    if(!customer.isOnline())
                    {
                        LoginClient_username = customer.getUser_name();
                        LoginClient_userId = customer.getUser_id();
                        LoginClient_acount_type = customer.getAcount_type();
                        Login_customer = customer;
                        customer.setOnline(true);
                        updateCustomer(customer);
                        App.setRoot("controllers/ClientMainPage");
                    }
                    else
                    {
                        App.showAlert("ERROR","Your account is currently logged onto another device. Please log out of the other device or contact your administrator");
                    }
                    return;
                }

        if(net_workers!=null)
            for (NetWorker net_worker : net_workers)
                if (net_worker.getUser_name().equals(userName.getText()) && net_worker.getPassword().equals(current)) {
                    LoginWorker_username = net_worker.getUser_name();
                    Login_netWorker = net_worker;
                    App.setRoot("controllers/NetWorkerHomePage");
                    return;
                }

        if(support_workers!=null)
            for (SupportWorker support_worker : support_workers)
                if (support_worker.getUser_name().equals(userName.getText()) &&
                        support_worker.getPassword().equals(current)) {

                    LoginWorker_username = support_worker.getUser_name();
                    Login_supportWorker = support_worker;
                    App.setRoot("controllers/SupportWorkerHomePage");
                    return;
                }

        if(shopAdmins!=null)
            for (ShopAdmin shopAdmin : shopAdmins)
                if (shopAdmin.getUser_name().equals(userName.getText()) && shopAdmin.getPassword().equals(current)) {
                    Login_shopAdmin = shopAdmin;
                    App.setRoot("controllers/ShopAdminHomePage");
                    return;
                }

        showAlert("error","Username or Password is incorrect");

    }

    @FXML
    void openCatalog(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get Items",null);
        SimpleClient.getClient().sendToServer(msg);
        getAllItems();
        System.out.println("msg sent to got shop items");
        App.setRoot("controllers/PublicCatalogControl");
    }


    @FXML
    void Show(ActionEvent event) {          ////////show checkbox //////////
        if(showPassword.isSelected())
        {
           // show_password=true;
            password_status="visible";
            current=Password.getText();
            Password.setDisable(true);
            Password.setVisible(false);
            visiblePassword.setVisible(true);
            visiblePassword.setDisable(false);
            visiblePassword.setText(current);
        }
        else
        {
           // show_password=false;
            password_status="invisible";
            current=visiblePassword.getText();
            visiblePassword.setDisable(true);
            visiblePassword.setVisible(false);
            Password.setDisable(false);
            Password.setVisible(true);
            Password.setText(current);
        }
    }

}
