package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.ReportToDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ShowReportsForClientService implements Initializable {

    @FXML
    private TableColumn<ReportToDisplay, String> clientIDColumn;

    @FXML
    private TableColumn<ReportToDisplay, String> reportDateColumn;

    @FXML
    private TableColumn<ReportToDisplay, Integer> reportIDColumn;

    @FXML
    private TableView<ReportToDisplay> reports;

    @FXML
    private TableColumn<ReportToDisplay, Integer> shopIDColumn;

    @FXML
    void backToHomePage() throws IOException {
        App.setRoot("controllers/SupportWorkerHomePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Report> reportsToShow = null;

        try {
            ArrayList<Report> all_reports = App.getAllReports();

            if (all_reports == null)
                return;     // no reports to show

            // removing handled reports
            for (int i=all_reports.size() - 1;i>-1;i--)
                if (all_reports.get(i).isHandled())
                    all_reports.remove(i);

            // now we shall sort the reports' list from the oldest to the newest to ensure the fastest answers

            for(int i=0;i<all_reports.size();i++){
                for(int j=i+1;j<all_reports.size();j++){
                    if(all_reports.get(i).getdate().after(all_reports.get(j).getdate())){
                        Report temp;
                        temp = all_reports.get(i);
                        all_reports.set(i, all_reports.get(j));
                        all_reports.set(j, temp);
                    }
                }
            }

            reportsToShow = FXCollections.observableArrayList(all_reports);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (reportsToShow == null)
            return;

        ArrayList<ReportToDisplay> temp = new ArrayList<>();

        for (Report value : reportsToShow) {
            ReportToDisplay report = new ReportToDisplay(value.getId(), value.getDay() + "/" +
                    value.getMonth() + "/" + value.getYear(), value.getCustomer().getId(), value.getShop().getId());

            temp.add(report);
        }

        ObservableList<ReportToDisplay> final_reports = FXCollections.observableArrayList(temp);

        reportIDColumn.setCellValueFactory(new PropertyValueFactory<>("report_id"));
        clientIDColumn.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        reportDateColumn.setCellValueFactory(new PropertyValueFactory<>("report_date"));
        shopIDColumn.setCellValueFactory(new PropertyValueFactory<>("shop_id"));

        reports.setItems(final_reports);

    }

    public void openReport(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() != 2)
            return;

        int index, report_id;

        try {

            index = reports.getSelectionModel().selectedIndexProperty().get();
            report_id = reports.getItems().get(index).getReport_id();
        } catch (Exception e) {
            return;
            // double-clicked an empty row
        }

        ArrayList<Report> all_reports = App.getAllReports();

        for (Report all_report : all_reports)
            if (all_report.getId() == report_id) {
                if (all_report.isWorkingOnIT()) {
                    App.showAlert("Error", "Complaint is already being handled by another worker.");
                    return;
                }
                all_report.setWorkingOnIT(true);
            }

        App.setReport_id_for_client_service(report_id);

        // we need to set workingOnIt to true making the process of responding to a report atomic, this way we avoid
        // refunding the same report more than one time

        App.setRoot("controllers/ShowSelectedReportForSupportWorker");
    }
}
