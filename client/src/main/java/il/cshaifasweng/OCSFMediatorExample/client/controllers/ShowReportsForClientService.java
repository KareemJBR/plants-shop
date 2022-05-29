package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ShowReportsForClientService implements Initializable {

    @FXML
    private TableColumn<Report, String> clientIDColumn;

    @FXML
    private TableColumn<Report, String> reportDateColumn;

    @FXML
    private TableColumn<Report, Integer> reportIDColumn;

    @FXML
    private TableView<Report> reports;

    @FXML
    private TableColumn<Report, Integer> shopIDColumn;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("controllers/SupportWorkerHomePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Report> reportsToShow = null;

        try {
            ArrayList<Report> all_reports = App.getAllReports();

            // removing handled reports
            for (int i=all_reports.size() - 1;i>-1;i--)
                if (all_reports.get(i).isHandled())
                    all_reports.remove(i);

            // now we shall sort the reports' list from the oldest to the newest to ensure the fastest answers

            for(int i=0;i<reportsToShow.size();i++){
                for(int j=i+1;j<reportsToShow.size();j++){
                    if(reportsToShow.get(i).getdate().after(reportsToShow.get(j).getdate())){
                        Report temp;
                        temp = reportsToShow.get(i);
                        reportsToShow.set(i, reportsToShow.get(j));
                        reportsToShow.set(j, temp);
                    }
                }
            }

            reportsToShow = FXCollections.observableArrayList(all_reports);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (reportsToShow == null)
            return;

        reportIDColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("id"));
        clientIDColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customer_id"));
        reportDateColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("report_date"));
        shopIDColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("shop_id"));

        reports.setItems(reportsToShow);

    }

    @FXML
    void openReport(MouseEvent event) throws IOException {
        if (event.getClickCount() != 2)
            return;

        int index = reports.getSelectionModel().selectedIndexProperty().get();
        int report_id = reports.getItems().get(index).getId();

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
