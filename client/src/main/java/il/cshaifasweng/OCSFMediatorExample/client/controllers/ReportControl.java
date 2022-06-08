package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_username;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.Login_customer;

public class ReportControl {

    @FXML
    private TextArea reportText;

    @FXML
    private ChoiceBox<String> selectMe;

    @FXML
    private Label selectText;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get customers", null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    void createRoport(ActionEvent event) throws Exception {
        if (reportText.getText().equals(null)||reportText.getText().equals("")) {
            showAlert("error", "report is empty");
            return;
        }

        String value = (String) selectMe.getValue();
        if(!Login_customer.getAcount_type().equals("Network account")&&!Login_customer.getAcount_type().equals("Network account with 10% discount"))
        {
           value=Login_customer.getAcount_type();
        }
        if(value==null){
            showAlert("error","select a store please ");
            return;
        }

        Report newReport = new Report(reportText.getText(), false, false, null);

        Shop shop=getShop(value);
        Customer customer=getCustomer(Login_customer.getId());
        newReport.setCustomer(customer);
        newReport.setShop(shop);
        MsgClass msg1 = new MsgClass("#add report", newReport);
        SimpleClient.getClient().sendToServer(msg1);
        MsgClass msg3 = new MsgClass("#get customers", null);
        SimpleClient.getClient().sendToServer(msg3);
        App.setRoot("controllers/ClientMainPage");
        showAlert("done", "report sent");
    }

    public void initialize() throws IOException, InterruptedException {
        if(Login_customer.getAcount_type().equals("Network account")||Login_customer.getAcount_type().equals("Network account with 10% discount"))
        {
            ArrayList<Shop> shopArr=new ArrayList<>(getAllShops());
            selectMe.setVisible(true);
            selectMe.setDisable(false);
            selectText.setText("Select Store: ");
            selectText.setStyle("-fx-font-size: 18;");
            ArrayList<String> shops = new ArrayList<>();
            for (Shop shop : shopArr) {
                shops.add(shop.getAddress());
            }
            selectMe.getItems().addAll(shops);
            selectMe.getSelectionModel().select(0);
        }
        else
        {
            selectMe.setVisible(false);
            selectMe.setDisable(true);
            selectText.setText(Login_customer.getAcount_type());
            selectText.setStyle("-fx-font-size: 16;");
        }

    }

}
