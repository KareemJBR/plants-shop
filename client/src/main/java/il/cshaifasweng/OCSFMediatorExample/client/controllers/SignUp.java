package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SignUp {

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
    void signUp(ActionEvent event) {
        String creditnumber=creditCardNumber.getText();
        String id=IDNumber.getText();
        String username=userName.getText();
        String password=passwordBtn.getText();
        String passwordconfirm=confirmPasswordBtn.getText();
        ArrayList<String> errors = new ArrayList<String>();

        clean();

        /// check if all form elements are full
        if(password.equals("")||passwordconfirm.equals("")||username.equals("")||id.equals("")||creditnumber.equals(""))
        {
            errors.add("please fill out all required fields");
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



    }

    private void clean() {
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
        App.setRoot("controllers/LogIN");
    }

}

