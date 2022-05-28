package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllShops;

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
        Calendar fromC2 = App.localDateToCalendar(fromDate2.getValue());
        Calendar untilC1 = App.localDateToCalendar(untilDate1.getValue());
        Calendar untilC2 = App.localDateToCalendar(untilDate2.getValue());

        if(comparisonOn.isSelected()){  // we need to compare two time intervals
            if(reportTypeComboBox.getValue().equals("Orders")){
                // TODO: we shall compare orders in two time intervals

            }
            else if(reportTypeComboBox.getValue().equals("Complaints")) {

            }
            else {      // incomes

            }
        }

        else{
            if(reportTypeComboBox.getValue().equals("Orders")){

            }
            else if(reportTypeComboBox.getValue().equals("Complaints")) {

            }
            else {      // incomes

            }
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
        ArrayList<Shop> shops = null;
        try {
            shops = getAllShops();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(shops == null)
            return;

        ObservableList<String> shops_ids = FXCollections.observableArrayList();

        for(int i=0;i<shops.size();i++){
            shops_ids.add(shops_ids.get(i));
        }

        ObservableList<String> reports_types = FXCollections.observableArrayList();

        reports_types.add("Orders");
        reports_types.add("Incomes");
        reports_types.add("Complaints");

        reportTypeComboBox.setItems(reports_types);
    }
}
