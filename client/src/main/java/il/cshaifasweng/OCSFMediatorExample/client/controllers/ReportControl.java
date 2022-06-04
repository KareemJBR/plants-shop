package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllShops;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_username;

public class ReportControl {
    @FXML
    private Button back;

    @FXML
    private Label decriprinLabel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextArea reportText;

    @FXML
    private ChoiceBox<String> selectMe;

    @FXML
    private Label selectText;

    @FXML
    private Button sentBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get customers", null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    void createRoport(ActionEvent event) throws Exception {
        if (reportText.equals(null)) {
            showAlert("error", "report is empty");
            return;
        }
        MsgClass msg = new MsgClass("#get current customer", LoginClient_username);
        SimpleClient.getClient().sendToServer(msg);
        while (currentCustomerData == null) {
            System.out.println("waiting for server");
        }
        Customer customer = (Customer) currentCustomerData;
        System.out.println("the customer is  " + customer);


        String value = (String) selectMe.getValue();
        if(value==null){
            showAlert("error","select a store pls ");
            return;
        }
        MsgClass msg2 = new MsgClass("#get selected Shop", value);
        SimpleClient.getClient().sendToServer(msg2);
        while (selectedShopData == null) {
            System.out.println("waiting for server to get shop");
        }
        Report newReport = new Report(reportText.getText(), false, false, null);

        //customer.addReport(newReport);
        newReport.setCustomer(customer);
        newReport.setShop((Shop)selectedShopData);
        MsgClass msg1 = new MsgClass("#add report", newReport);
        SimpleClient.getClient().sendToServer(msg1);
        MsgClass msg3 = new MsgClass("#get customers", null);
        SimpleClient.getClient().sendToServer(msg3);
        App.setRoot("controllers/ClientMainPage");
        currentCustomerData = null;
        selectedShopData=null;
        showAlert("done", "report sent");

       /* ArrayList<Customer> customers=new ArrayList<Customer>();
        MsgClass msg =new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        customers=(ArrayList<Customer>)data;
        Customer current = new Customer();
        for(int i=0;i<customers.size();i++) {
            if (customers.get(i).getUser_name().equals(LoginClient_username)){
                current=customers.get(i);
            }
        }



        current.addReport(newReport);
      */
    }

    private void getCustomer(String userName) throws Exception {
        MsgClass msg1 = new MsgClass("#get current customer", LoginClient_username);
        SimpleClient.getClient().sendToServer(msg1);
        System.out.println("custer data" + currentCustomerData);
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

    public void initialize() throws IOException, InterruptedException {
        ArrayList<Shop> shopArr=new ArrayList<>(getAllShops());
        ArrayList<String> shops = new ArrayList<>();
        for(int i=0;i<shopArr.size();i++){
            shops.add(shopArr.get(i).getName());
        }
        selectMe.getItems().addAll(shops);
        selectMe.getSelectionModel().select(0);
    }

}
