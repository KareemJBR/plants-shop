package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class OrdersReportOneTimeInterval {

    @FXML
    private CategoryAxis itemAxes;

    @FXML
    private BarChart<?, ?> ordersChart;

    @FXML
    private NumberAxis ordersNumAxes;

}
