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
        // going back to Support Worker homepage
        ArrayList<Report> all_reports = App.getAllReports();

        for (Report all_report : all_reports)
            if (all_report.getId() == report_id)
                all_report.setWorkingOnIT(false);

        App.setRoot("controllers/ShowReportsForClientService");
    }

    @FXML
    void sendResponse(ActionEvent event) throws IOException {

        // submit worker response to the client

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
                return;
            }

            if (refund_val < 0.0) {
                App.showAlert("Error", "Refund value should be non-negative.");
                return;
            }
        }

        // if we reach this line then the inputs are legal
        int report_index = 0;

        ArrayList<Report> all_reports = App.getAllReports();

        for (int i=0;i<all_reports.size();i++)
            if (all_reports.get(i).getId() == report_id) {
                all_reports.get(i).setHandled(true);
                all_reports.get(i).setAnswer(responseText.getText());
                all_reports.get(i).setMoneyBack(refund_val);
                report_index = i;
            }

        // now we shall update the client's budget
        int customer_index = 0;

        ArrayList<Customer> all_customers = App.getAllCustomers();
        for (int i=0;i<all_customers.size();i++)
            if (all_customers.get(i).getId().equals(clientIDText.getText())) {
                all_customers.get(i).setBudget(all_customers.get(i).getBudget() + refund_val);
                customer_index = i;
                break;
            }

        App.updateReport(all_reports.get(report_index));
        App.updateCustomer(all_customers.get(customer_index));

        App.setRoot("controllers/ShowReportsForClientService");     // going back to the reports table
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

        assert all_reports != null;     // avoiding NullPtr exception
        for (Report all_report : all_reports)
            if (all_report.getId() == report_id) {
                // initialize the text fields
                reportIDText.textProperty().set(Integer.toString(report_id));
                clientIDText.textProperty().set(all_report.getCustomer().getId());
                shopIDText.textProperty().set(Integer.toString(all_report.getShop().getId()));
                reportBody.textProperty().set(all_report.getContent());
                break;
            }

    }
}
