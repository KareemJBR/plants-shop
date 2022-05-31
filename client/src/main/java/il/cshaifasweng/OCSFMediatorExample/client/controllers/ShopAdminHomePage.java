package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;

import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

public class ShopAdminHomePage {

    @FXML
    void showReports(ActionEvent event) throws IOException {
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

}
