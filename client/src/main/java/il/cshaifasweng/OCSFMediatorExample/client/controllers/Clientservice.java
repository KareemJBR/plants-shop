package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Clientservice {

    @FXML // fx:id="page_head"
    private TextField page_head; // Value injected by FXMLLoader

    @FXML // fx:id="showreportsbtn"
    private Button showreportsbtn; // Value injected by FXMLLoader

    @FXML
    void showReports(ActionEvent event) throws IOException {
        App.setRoot("ShowReportsforclientservice");

    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("LogIN");

    }

}

