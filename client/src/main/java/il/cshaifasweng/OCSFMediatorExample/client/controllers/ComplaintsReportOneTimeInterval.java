package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllReports;

public class ComplaintsReportOneTimeInterval {

    @FXML
    private NumberAxis complaintsNumAxes;

    @FXML
    private CategoryAxis dayAxes;

    @FXML
    private BarChart<Integer, String> reportsChart;

    public void start_chart(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date) throws IOException {

        XYChart.Series<Integer, String> series = new XYChart.Series<>();
        series.setName("Complaints Report");

        List<Report> all_reports = getAllReports();
        List<Report> reports_to_show = new ArrayList<>();
        int num_of_days = 0;

        // TODO: specify which reports we should show + num_of_days between the start adn end date

        for (int i=0;i<reports_to_show.size();i++){

        }

    }

}
