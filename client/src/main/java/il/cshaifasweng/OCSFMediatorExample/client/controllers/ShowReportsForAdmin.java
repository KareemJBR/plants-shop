package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;


public class ShowReportsForAdmin implements Initializable {

    @FXML
    private CheckBox comparisonOn;

    @FXML
    private DatePicker fromDate1;

    @FXML
    private DatePicker fromDate2;

    @FXML
    private ComboBox<String> reportTypeComboBox;

    @FXML
    private DatePicker untilDate1;

    @FXML
    private DatePicker untilDate2;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("controllers/AdministratorHomePage");
    }

    @FXML
    void compareButtonPressed(ActionEvent event) {
        fromDate2.disableProperty().set(!fromDate2.isDisabled());
        untilDate2.disableProperty().set(!untilDate2.isDisabled());
    }

    @FXML
    void showButtonPressed(ActionEvent event) throws IOException {
        if(!legalFields())
            return;

        Calendar fromC1 = App.localDateToCalendar(fromDate1.getValue());
        Calendar untilC1 = App.localDateToCalendar(untilDate1.getValue());

        Calendar fromC2 = null;
        Calendar untilC2 = null;

        App.setShop_id(-1);     // no shop specified, the data to be displayed is for all shops together
        App.setIs_admin(true);

        if (comparisonOn.isSelected()) {
            fromC2 = App.localDateToCalendar(fromDate2.getValue());
            untilC2 = App.localDateToCalendar(untilDate2.getValue());

            fromC2.set(Calendar.HOUR, 0);
            fromC2.set(Calendar.MINUTE, 0);
            untilC2.set(Calendar.HOUR, 23);
            untilC2.set(Calendar.MINUTE, 59);
        }

        fromC1.set(Calendar.HOUR, 0);
        fromC1.set(Calendar.MINUTE, 0);
        untilC1.set(Calendar.HOUR, 23);
        untilC1.set(Calendar.MINUTE, 59);

        App.setReport_start_date1(fromC1);
        App.setReport_end_date1(untilC1);
        App.setReport_start_date2(fromC2);
        App.setReport_end_date2(untilC2);

        if(comparisonOn.isSelected()){  // we need to compare two time intervals
            if(reportTypeComboBox.getValue().equals("Orders"))
                App.setRoot("controllers/OrdersReportTwoTimeIntervals");
            else if(reportTypeComboBox.getValue().equals("Complaints"))
                App.setRoot("controllers/ComplaintsReportTwoTimeIntervals");
            else      // incomes
                App.setRoot("controllers/IncomesReportTwoTimeIntervals");
        }

        else{
            if(reportTypeComboBox.getValue().equals("Orders"))
                App.setRoot("controllers/OrdersReportOneTimeInterval");
            else if(reportTypeComboBox.getValue().equals("Complaints"))
                App.setRoot("controllers/ComplaintsReportOneTimeInterval");
            else      // incomes
                App.setRoot("controllers/IncomesReportOneTimeInterval");
        }
    }

    public boolean legalFields() {
        if(fromDate1.getValue() == null || untilDate1.getValue() == null || reportTypeComboBox.getValue() == null) {
            App.showAlert("Error", "Please fill all needed fields.");
            return false;
        }

        if(comparisonOn.isSelected())
            if(fromDate2.getValue() == null || untilDate2.getValue() == null || reportTypeComboBox.getValue() == null) {
                App.showAlert("Error", "Please fill all needed fields.");
                return false;
            }
            else if(fromDate2.getValue().isAfter(untilDate2.getValue())){
                App.showAlert("Error", "Invalid time interval!");
                return false;
            }

        if(fromDate1.getValue().isAfter(untilDate1.getValue())) {
            App.showAlert("Error", "Invalid time interval!");
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> reports_types = FXCollections.observableArrayList();

        reports_types.add("Orders");
        reports_types.add("Incomes");
        reports_types.add("Complaints");

        reportTypeComboBox.setItems(reports_types);
        reportTypeComboBox.getSelectionModel().select(0);
    }

}
