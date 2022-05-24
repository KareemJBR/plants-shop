package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import java.util.List;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllCustomers;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllWorkers;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllShops;

public class SignUp {
    public static boolean shop;
    @FXML // fx:id="EmailTextBox"
    private TextField EmailTextBox; // Value injected by FXMLLoader

    @FXML // fx:id="AcountTypeCombo"
    private ComboBox<String> AcountTypeCombo; // Value injected by FXMLLoader
    @FXML // fx:id="ShopsCombo"
    private ComboBox<String> ShopsCombo; // Value injected by FXMLLoader
    @FXML // fx:id="IDNumber"
    private TextField IDNumber; // Value injected by FXMLLoader

    @FXML // fx:id="SignUpBtn"
    private Button SignUpBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordBtn"
    private TextField confirmPasswordBtn; // Value injected by FXMLLoader

    @FXML // fx:id="creditCardNumber"
    private TextField creditCardNumber; // Value injected by FXMLLoader

    @FXML // fx:id="passwordBtn"
    private TextField passwordBtn; // Value injected by FXMLLoader

    @FXML // fx:id="passwordLabel"
    private Label passwordLabel; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private TextField title; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField lastNameTextBox;


    @FXML
    void signUp(ActionEvent event) throws Exception {
        String creditnumber=creditCardNumber.getText();
        String id=IDNumber.getText();
        String username=userName.getText();
        String password=passwordBtn.getText();
        String passwordconfirm=confirmPasswordBtn.getText();
        String firstname=firstNameTextBox.getText();
        String lastname=lastNameTextBox.getText();
        String email=EmailTextBox.getText();
        ArrayList<String> errors = new ArrayList<String>();
        List<Customer> customers=getAllCustomers();
        List<Worker> workers=getAllWorkers();
        clear();
        MsgClass msg;

        ///////////////////////////////// Input validation ///////////////////////////

        /// check if all required fields are filled ///
        if(firstname.equals("") || lastname.equals("") || password.equals("")||passwordconfirm.equals("")||username.equals("")||id.equals("")||creditnumber.equals("") ||email.equals(""))
        {
            errors.add("please fill out all required fields");
            if(firstname.equals(""))
            {
                firstNameTextBox.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(lastname.equals(""))
            {
                lastNameTextBox.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(password.equals(""))
            {
                passwordBtn.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(passwordconfirm.equals(""))
            {
                confirmPasswordBtn.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(id.equals(""))
            {
                IDNumber.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(creditnumber.equals(""))
            {
                creditCardNumber.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(username.equals(""))
            {
                userName.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(username.equals(""))
            {
                userName.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
            if(email.equals(""))
            {
                EmailTextBox.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
            }
        }

        if(customers!=null)
        {
            for(int i=0;i<customers.size();i++)
            {
                if(username.equals(customers.get(i).getUser_name()))
                {
                    errors.add("User Name already exists please try with another one");
                    userName.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                    break;
                }
            }
            for(int i=0;i<customers.size();i++)
            {

                if(id.equals((customers.get(i).getUser_id())))
                {
                    errors.add("Id Numer already exists please try with another one");
                    IDNumber.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                    break;
                }
            }

        }
        if(workers!=null)
        {
            for(int i=0;i<workers.size();i++)
            {
                if(username.equals(workers.get(i).getUser_name()))
                {
                    errors.add("User Name already exists please try with another one");
                    userName.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                    break;
                }
            }
            for(int i=0;i<workers.size();i++)
            {

                if(id.equals(Integer.toString(workers.get(i).getId())))
                {
                    errors.add("Id Numer already exists please try with another one");
                    IDNumber.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                    break;
                }
            }

        }

      if(id.length()!=9 && id.length()>0)
      {
          errors.add("Id number must contain 9 digits");
          IDNumber.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
      }

      if(creditnumber.length()!=16 && creditnumber.length()>0)
      {
          errors.add("Card number  must contain 16 digits");
          creditCardNumber.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
      }

      if(!password.equals(passwordconfirm) &&  !password.equals("") && !passwordconfirm.equals(""))
      {
          errors.add("Passwords must be the same ");
          confirmPasswordBtn.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
      }

      ///////////////////////////////// end of Input validation ///////////////////////////

      String errormessage="";
      for(int i=0;i<errors.size();i++)
      {
          errormessage+=errors.get(i);
          errormessage+="\n";
      }

      if(!errormessage.equals(""))
      {
          showAlert("Invalid inputs",errormessage);
      }
      else      //no errors detected
      {
          msg = new MsgClass("#add customer");
          msg.setObj(new Customer(id,firstname,lastname,username,password,creditnumber,"network_acount",email));
          msg.setObj(new Customer(id,firstname,lastname,username,password,creditnumber,"network_acount",email));

          SimpleClient.getClient().sendToServer(msg);
           msg =new MsgClass("#get customers",null);
          if(AcountTypeCombo.getValue().equals("Account for a particular store"))
          {

              msg.setObj(new Customer(id,firstname,lastname,username,password,creditnumber,ShopsCombo.getValue(),email));
          }
          else
          {
              msg.setObj(new Customer(id,firstname,lastname,username,password,creditnumber,AcountTypeCombo.getValue(),email));
          }

          SimpleClient.getClient().sendToServer(msg);
           customers =getAllCustomers();
          showAlert("success","Congratulations, your account has been successfully created");
          App.setRoot("controllers/LogIN");
      }




    }

    private void clear() {
        EmailTextBox.setStyle("-fx-background-radius:15;");
        ShopsCombo.setStyle("-fx-background-radius:15;");
        userName.setStyle("-fx-background-radius:15;");
        lastNameTextBox.setStyle("-fx-background-radius:15;");
        firstNameTextBox.setStyle("-fx-background-radius:15;");
        confirmPasswordBtn.setStyle("-fx-background-radius:15;");
        passwordBtn.setStyle("-fx-background-radius:15;");
        confirmPasswordBtn.setStyle("-fx-background-radius:15;");
        IDNumber.setStyle("-fx-background-radius:15;");
        creditCardNumber.setStyle("-fx-background-radius:15;");
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


    @FXML
    void Back(ActionEvent event) throws IOException {
        if(shop==false) {
            App.setRoot("controllers/LogIN");
        }
        else{
            App.setRoot("controllers/publicCatalog");
        }
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
       AcountTypeCombo.getItems().removeAll(AcountTypeCombo.getItems());
        AcountTypeCombo.getItems().removeAll(AcountTypeCombo.getItems());
        AcountTypeCombo.getItems().addAll("Network account", "Network account with 10% discount","Account for a particular store");
        AcountTypeCombo.getSelectionModel().select(0);


        ArrayList<Shop> shops=getAllShops();

        if(shops!=null)
        {
         //   System.out.println("notnull");
            for(int i=0;i<shops.size();i++)
            {
                ShopsCombo.getItems().addAll(shops.get(i).getAddress());
            }
        }
    }
    @FXML
    void CheckComboStatus(ActionEvent event) {
        if(AcountTypeCombo.getValue().equals("Account for a particular store"))
        {
            ShopsCombo.setVisible(true);
            ShopsCombo.setDisable(false);
            ShopsCombo.getSelectionModel().select(0);
        }
        else
        {
            ShopsCombo.setVisible(false);
            ShopsCombo.setDisable(true);
        }
    }

    @Subscribe
    public void onMessageEvent(MsgClass msg) {

    }


}

