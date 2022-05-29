package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SupportWorkerHomePage {

    private String worker_id;   // TODO: assign this when logged in to add it to the reports handled by this worker

    @FXML
    void showReports(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShowReportsForClientService");
    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }

}
