package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;

import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopAdminHomePage implements Initializable {

    public Label welcomeSentence;

    @FXML
    void showReports(ActionEvent event) throws IOException {
        // passing parameters for the reports controllers
        App.setIs_admin(false);

        ArrayList<Shop> all_shops = App.getAllShops();
        for (Shop all_shop : all_shops)
            if (all_shop.getAdmin_id().equals(LogIN.Login_shopAdmin.getAdmin_id())) {
                App.setShop_id(all_shop.getId());
                break;
            }

        App.setRoot("controllers/ShowReportsForShopAdmin");
    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adding the admin name to the greeting sentence
        welcomeSentence.textProperty().set(welcomeSentence.getText() + " " + LogIN.Login_shopAdmin.getAdmin_first_name() +
                " " + LogIN.Login_shopAdmin.getAdmin_last_name() + "!");
    }
}
