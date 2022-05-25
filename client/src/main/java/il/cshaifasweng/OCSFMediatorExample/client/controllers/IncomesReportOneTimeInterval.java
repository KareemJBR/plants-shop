package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.util.Date;

public class IncomesReportOneTimeInterval {

    @FXML
    private CategoryAxis dayAxes;

    @FXML
    private NumberAxis incomesNumAxes;

    @FXML
    private BarChart<String, Integer> reportsChart;

    public void start_chart(int shop_id, Date start_date, Date end_date){
        // TODO: initialize the chart
    }
}
