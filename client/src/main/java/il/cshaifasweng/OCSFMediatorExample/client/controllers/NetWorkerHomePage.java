package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllItems;

public class NetWorkerHomePage {

    @FXML
    void addSale(ActionEvent event) throws IOException {
        getAllItems();
        App.setRoot("controllers/AddSale");
    }

    @FXML
    void addItem(ActionEvent event) throws IOException {
        App.setRoot("controllers/AddItem");
    }

    @FXML
    void openCatalogue(ActionEvent event) throws IOException {
        getAllItems();
        App.setRoot("controllers/NetWorkerCatalog");
    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }



}
