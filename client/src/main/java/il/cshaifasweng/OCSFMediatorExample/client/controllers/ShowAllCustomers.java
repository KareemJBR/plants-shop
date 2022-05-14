package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ShowAllCustomers {

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("AdministratorHomePage");
    }

}
