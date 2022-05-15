package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;

public class ShowReportsForAdmin {

    @FXML
    private CheckBox comparisonOn;

    @FXML
    private DatePicker fromDate1;

    @FXML
    private DatePicker fromDate2;

    @FXML
    private ComboBox<?> shopsList1;

    @FXML
    private ComboBox<?> shopsList2;

    @FXML
    private DatePicker untilDate1;

    @FXML
    private DatePicker untilDate2;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("AdministratorHomePage");
    }

    @FXML
    void compareButtonPressed(ActionEvent event) {
        fromDate2.disableProperty().set(!fromDate2.isDisabled());
        untilDate2.disableProperty().set(!untilDate2.isDisabled());
        shopsList2.disableProperty().set(!shopsList2.isDisabled());
    }

    @FXML
    void showButtonPressed(ActionEvent event) {

    }

}
