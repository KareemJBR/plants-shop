package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ShowReportsForAdmin {

    @FXML
    private TableView<?> reportsTable;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("AdministratorHomePage");
    }

}
