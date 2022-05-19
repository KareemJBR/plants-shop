package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;

public class LogIN {
    boolean show_password=false;
    @FXML // fx:id="showPassword"
    private CheckBox showPassword; // Value injected by FXMLLoader
    @FXML // fx:id="visiblePassword"
    private TextField visiblePassword; // Value injected by FXMLLoader
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

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML
    void createAcount(ActionEvent event) throws IOException {
       App.setRoot("controllers/SignUp");
        //SignUp.main(null);
    }
    @FXML
    void logIN(ActionEvent event) throws IOException {
        boolean login_success=false;
        List<Customer> customers=new ArrayList<Customer>();
        MsgClass msg =new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        customers=(ArrayList<Customer>)data;
        if(customers!=null)
        {
            for(int i=0;i<customers.size();i++)
            {
               if(customers.get(i).getUser_name().equals(userName.getText()) && customers.get(i).getPassword().equals(Password.getText()))
               {
                   showAlert("success","login success");
                   login_success=true;
               }
            }
        }
        if(!login_success)
        {
            showAlert("error","Username or Password is incorrect");
        }

    }

    @FXML
    void openCatalog(ActionEvent event) throws IOException {
        App.setRoot("controllers/CatalogForNoneRegisteredClients");
    }

    @FXML
    void Show(ActionEvent event) {          ////////show checkbox //////////
        if(showPassword.isSelected())
        {
            show_password=true;
            visiblePassword.setVisible(true);
            visiblePassword.setDisable(false);
            Password.setDisable(true);
            Password.setVisible(false);
            visiblePassword.setText(Password.getText());
        }
        else
        {
            show_password=false;
            visiblePassword.setVisible(false);
            visiblePassword.setDisable(true);
            Password.setDisable(false);
            Password.setVisible(true);
            visiblePassword.setText(visiblePassword.getText());
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
