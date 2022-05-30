package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowSelectedReportForSupportWorker implements Initializable {

    private int report_id;

    @FXML
    private TextField clientIDText;

    @FXML
    private CheckBox refundCheckBox;

    @FXML
    private TextField refundValueText;

    @FXML
    private TextArea reportBody;

    @FXML
    private TextField reportIDText;

    @FXML
    private TextArea responseText;

    @FXML
    private TextField shopIDText;

    @FXML
    void checkBoxClicked(ActionEvent event) {
        refundValueText.disableProperty().set(!refundCheckBox.isSelected());
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        ArrayList<Report> all_reports = App.getAllReports();

        for (Report all_report : all_reports)
            if (all_report.getId() == report_id)
                all_report.setWorkingOnIT(false);

        App.setRoot("controllers/ShowReportsForClientService");
    }

    @FXML
    void sendResponse(ActionEvent event) throws IOException {

        if (responseText.getText() == null){
            App.showAlert("Error", "Please add a response to the client.");
            return;
        }

        double refund_val = 0.0;

        if (refundCheckBox.isSelected()){
            if (refundValueText.getText() == null) {
                App.showAlert("Error", "Please set the refund value!");
                return;
            }

            try {
                refund_val += Double.parseDouble(refundValueText.getText());
            } catch (NumberFormatException e){
                App.showAlert("Error", "Please enter a valid refund value.");
                System.out.println(e.getMessage());
            }
        }

        Report new_report = null;

        ArrayList<Report> all_reports = App.getAllReports();
        for (Report all_report : all_reports)
            if (all_report.getId() == report_id)
                new_report = new Report(all_report);

        assert new_report != null;
        new_report.setHandled(true);
        new_report.setAnswer(responseText.getText());
        new_report.setMoneyBack(refund_val);

        // now we shall update the client's budget
        Customer new_customer = null;

        ArrayList<Customer> all_customers = App.getAllCustomers();
        for (Customer all_customer : all_customers)
            if (all_customer.getId().equals(clientIDText.getText()))
                new_customer = new Customer(all_customer);

        assert new_customer != null;
        new_customer.setBudget(new_customer.getBudget() + refund_val);

        App.updateReport(new_report);
        App.updateCustomer(new_customer);

        App.setRoot("controllers/ShowReportsForClientService");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        report_id = App.getReport_id_for_client_service();

        ArrayList<Report> all_reports = null;

        try {
            all_reports = App.getAllReports();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert all_reports != null;
        for (Report all_report : all_reports)
            if (all_report.getId() == report_id) {
                reportIDText.textProperty().set(Integer.toString(report_id));
                clientIDText.textProperty().set(all_report.getCustomer().getId());
                shopIDText.textProperty().set(Integer.toString(all_report.getShop().getId()));
                reportBody.textProperty().set(all_report.getContent());
                break;
            }

    }
}
