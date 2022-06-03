package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShowReportsForShopAdmin implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private ComboBox<String> reportTypeComboBox;

    @FXML
    private DatePicker untilDate;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShopAdminHomePage");
    }

    @FXML
    void showButtonPressed(ActionEvent event) throws IOException {
        if (!fromDate.getValue().isBefore(untilDate.getValue())) {
            App.showAlert("Error", "Invalid time interval.");
            return;
        }

        if (reportTypeComboBox.getValue() == null) {
            App.showAlert("Error", "Please enter a report type!");
            return;
        }

        App.setIs_admin(false);
        App.setReport_start_date1(App.localDateToCalendar(fromDate.getValue()));
        App.setReport_end_date1(App.localDateToCalendar(untilDate.getValue()));

        if (Objects.equals(reportTypeComboBox.getValue(), "Orders"))
            App.setRoot("controllers/OrdersReportOneTimeInterval");
        else if (Objects.equals(reportTypeComboBox.getValue(), "Complaints"))
            App.setRoot("controllers/ComplaintsReportOneTimeInterval");
        else
            App.setRoot("controllers/IncomesReportOneTimeInterval");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the report types ComboBox
        ObservableList<String> reports_types = FXCollections.observableArrayList();

        reports_types.add("Orders");
        reports_types.add("Incomes");
        reports_types.add("Complaints");
        reportTypeComboBox.setItems(reports_types);
    }
}
