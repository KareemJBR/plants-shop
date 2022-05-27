/**
 * Sample Skeleton for 'ShowReportsForClientService.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ShowReportsForClientService {

    @FXML // fx:id="reports"
    private TableView<Report> reports; // Value injected by FXMLLoader

    @FXML
    void gotClientServicePage(ActionEvent event) throws IOException {
        App.setRoot("Clientservicehomepage");
    }

}
