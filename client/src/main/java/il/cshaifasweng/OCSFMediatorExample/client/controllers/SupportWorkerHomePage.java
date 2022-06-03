package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupportWorkerHomePage implements Initializable {

    public Label welcomeSentence;

    @FXML
    void showReports(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShowReportsForClientService");
    }

    @FXML
    void signOut(ActionEvent event) throws IOException {
        App.setRoot("controllers/LogIN");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeSentence.textProperty().set(welcomeSentence.getText() + " " + LogIN.Login_supportWorker.getFirst_Name() +
                " " + LogIN.Login_supportWorker.getLast_name() + "!");
    }
}
