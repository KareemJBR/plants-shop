package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.NetWorker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.SignUp.shop;

public class LogIN {
    public static  String LoginClient_username;
    public static  String LoginWorker_username;
    public static  String LoginClient_userId;
    public static  String LoginClient_acount_type;
    String  current;
    String password_status="invisible";
    @FXML // fx:id="Password"
    private PasswordField Password; // Value injected by FXMLLoader

    @FXML // fx:id="catalogBtn"
    private Button catalogBtn; // Value injected by FXMLLoader

    @FXML // fx:id="createAcountBtn"
    private Button createAcountBtn; // Value injected by FXMLLoader

    @FXML // fx:id="logInBtn"
    private Button logInBtn; // Value injected by FXMLLoader

    @FXML // fx:id="notmemberLabel"
    private Label notmemberLabel; // Value injected by FXMLLoader

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
        //SignUp.main(null);
    }
    @FXML
    void logIN(ActionEvent event) throws IOException {
        boolean login_success=false;
        ArrayList<Customer> customers=getAllCustomers();
        ArrayList<NetWorker> workers = getAllWorkers();
        if(customers!=null)
        {
            if(password_status=="visible")
            {
                current=visiblePassword.getText();
            }
            else
            {
                current=Password.getText();
            }
            for(int i=0;i<customers.size();i++)
            {
               if(customers.get(i).getUser_name().equals(userName.getText()) && customers.get(i).getPassword().equals(current))
               {
                   //showAlert("success","login success");
                   login_success=true;
                   LoginClient_username=customers.get(i).getUser_name();
                   LoginClient_userId=customers.get(i).getUser_id();
                   LoginClient_acount_type=customers.get(i).getAcount_type();
                   App.setRoot("controllers/ClientMainPage");
               }
            }
            if(userName.getText().equals("admin")&&current.equals("admin"))
            {
                login_success=true;
                App.setRoot("controllers/AdministratorHomePage");
            }
        }
        if(workers!=null)
        {
            if(password_status=="visible")
            {
                current=visiblePassword.getText();
            }
            else
            {
                current=Password.getText();
            }
            for(int i=0;i<workers.size();i++)
            {
                if(workers.get(i).getUser_name().equals(userName.getText()) && workers.get(i).getPassword().equals(current))
                {
                    //showAlert("success","login success");
                    login_success=true;
                    LoginWorker_username=workers.get(i).getUser_name();
                    App.setRoot("controllers/NetWorkerHomePage");
                }
            }
            if(userName.getText().equals("admin"))
            {
                App.setRoot("controllers/AdministratorHomePage");
            }
        }
        if(!login_success)
        {
            showAlert("error","Username or Password is incorrect");
        }

    }

    @FXML
    void openCatalog(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get Items",null);
        SimpleClient.getClient().sendToServer(msg);
        System.out.println("msg sent to got shop items");
        App.setRoot("controllers/publicCatalog");
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
