package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

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
    private BarChart<?, ?> ordersChart1;

    @FXML
    private BarChart<?, ?> ordersChart2;

    @FXML
    private NumberAxis ordersNumAxes1;

    @FXML
    private NumberAxis ordersNumAxes2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

}
