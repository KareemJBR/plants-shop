package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowReportsForClientService implements Initializable {

    @FXML
    private TableColumn<?, ?> clientIDColumn;

    @FXML
    private TableColumn<?, ?> reportDateColumn;

    @FXML
    private TableColumn<?, ?> reportIDColumn;

    @FXML
    private TableView<?> reports;

    @FXML
    private TableColumn<?, ?> shopIDColumn;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("controllers/SupportWorkerHomePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: load unsolved reports sorted from the oldest to the newest
    }
}
