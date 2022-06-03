package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.fxml.FXML;

import java.io.IOException;


public class AdministratorHomePage {

    @FXML
    void showCustomers() throws IOException {
        App.setRoot("controllers/ShowAllCustomers");
    }

    @FXML
    void showReports() throws IOException {
        // specifying the right values of App.is_admin and App.shop_id for creating the right reports
        App.setIs_admin(true);
        App.setShop_id(-1);
        App.setRoot("controllers/ShowReportsForAdmin");
    }

    @FXML
    void signOut() throws IOException {
        App.setRoot("controllers/LogIN");
    }

}
