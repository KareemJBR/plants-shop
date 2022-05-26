package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Calendar;

public class OrdersReportTwoTimeIntervals {

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

    @FXML
    private CategoryAxis itemAxes1;

    @FXML
    private CategoryAxis itemAxes2;

    @FXML
    private BarChart<Integer, String> ordersChart1;

    @FXML
    private BarChart<Integer, String> ordersChart2;

    @FXML
    private NumberAxis ordersNumAxes1;

    @FXML
    private NumberAxis ordersNumAxes2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    public void start_charts(boolean is_admin, Calendar start_date1, Calendar end_date1,
                             Calendar start_date2, Calendar end_date2) throws IOException {

    }

}
