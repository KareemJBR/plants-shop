package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddSale implements Initializable {

    private ArrayList<Item> items;
    private ArrayList<Integer> counters;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField priceText;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField titleText;

    @FXML
    void addItemsToSale(ActionEvent event) {
        // TODO: show catalogue and enable adding items to the sale
    }

    @FXML
    void cancelSale(ActionEvent event) throws IOException {
        App.setRoot("controllers/NetWorkerHomePage");
    }

    @FXML
    void publishSale(ActionEvent event) throws IOException {
        if (!startDatePicker.getValue().isBefore(endDatePicker.getValue()))
            App.showAlert("Error", "Invalid Time Interval");

        else if (priceText.getText() == null || titleText.getText() == null)
            App.showAlert("Error", "Please fill all the fields");

        else {
            Sale new_sale = new Sale(titleText.getText(), startDatePicker.getValue(), endDatePicker.getValue(),
                    Double.parseDouble(priceText.getText()));

            // TODO: add the sale to the database
            App.setRoot("controllers/NetWorkerHomePage");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        items = new ArrayList<>();
        counters = new ArrayList<>();
    }
}
