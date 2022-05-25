package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;

import il.cshaifasweng.OCSFMediatorExample.entities.ShopAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ShopAdminHomePage {

    private ShopAdmin shop_admin;

    @FXML
    void showReports(ActionEvent event) {

    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }

    public void start_controller(String admin_id) {

    }

}
