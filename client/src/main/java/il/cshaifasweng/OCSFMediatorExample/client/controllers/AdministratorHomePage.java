package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;


public class AdministratorHomePage {

    @FXML
    void showCustomers(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShowAllCustomers");
    }

    @FXML
    void showNetWorkers(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShowNetWorkers");
    }

    @FXML
    void showReports(ActionEvent event) throws IOException {
        App.setIs_admin(true);
        App.setShop_id(-1);
        App.setRoot("controllers/ShowReportsForAdmin");
    }

    @FXML
    void showSupportWorkers(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShowSupportWorkers");
    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }

}
