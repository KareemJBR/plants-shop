package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.report;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.Client_username;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.currentCustomerData;
public class ReportControl {
    @FXML
    private Button back;

    @FXML
    private TextArea reportText;

    @FXML
    private Button sentBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    }

    @FXML
    void createRoport(ActionEvent event) throws Exception {
        if (reportText.equals(null)) {
            showAlert("error", "are u stupid ? report is empty");
            return;
        }


       /* getCustomer(Client_username);
       Customer customer = (Customer) currentCustomerData;
       System.out.println("the customer is  "+customer);

        customer.addReport(newReport);
        ;*/
        ArrayList<Customer> customers=new ArrayList<Customer>();
        MsgClass msg =new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        customers=(ArrayList<Customer>)data;
        Customer current = new Customer();
        for(int i=0;i<customers.size();i++) {
            if (customers.get(i).getUser_name().equals(Client_username)){
                current=customers.get(i);
            }
        }
        report newReport = new report(reportText.getText(),false,false,"");
        newReport.setCustomer(current);
        MsgClass msg1=new MsgClass("#add report",newReport);
        SimpleClient.getClient().sendToServer(msg1);
        showAlert("done", "report sent");
    }
    private void getCustomer(String userName) throws Exception {
        MsgClass msg1=new MsgClass("#get current customer",Client_username);
        SimpleClient.getClient().sendToServer(msg1);
        System.out.println("custer data"+currentCustomerData);
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
