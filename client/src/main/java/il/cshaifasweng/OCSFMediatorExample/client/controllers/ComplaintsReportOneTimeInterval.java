package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllReports;

public class ComplaintsReportOneTimeInterval {

    @FXML
    private NumberAxis complaintsNumAxes;

    @FXML
    private CategoryAxis dayAxes;

    @FXML
    private BarChart<Integer, Integer> reportsChart;

    public void start_chart(boolean is_admin, int shop_id, Date start_date, Date end_date) throws IOException {
        // TODO: get all complaints in the time interval and plot them in the chart reportsChart
        List<Report> reports = getAllReports();
        
    }

}
