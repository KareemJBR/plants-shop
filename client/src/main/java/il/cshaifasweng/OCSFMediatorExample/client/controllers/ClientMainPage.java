package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ClientMainPage {

    @FXML
    private Button backBtn;

    @FXML
    private Button cartBtn;

    @FXML
    private Button catalogBtn;

    @FXML
    private Button customizedOrderBtn;

    @FXML
    void Cart(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }

    @FXML
    void catalog(ActionEvent event) throws IOException {
        App.setRoot("controllers/CatalogForRegisteredClients");

    }

    @FXML
    void customizedOrder(ActionEvent event) throws IOException {
        App.setRoot("controllers/CustomizedOrder");
    }
    @FXML
    void myOrders(ActionEvent event) {

    }


}
