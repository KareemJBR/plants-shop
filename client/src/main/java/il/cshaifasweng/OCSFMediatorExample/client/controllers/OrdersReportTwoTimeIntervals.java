package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

import java.util.Date;

public class OrdersReportTwoTimeIntervals {

    @FXML
    private CategoryAxis dayAxes1;

    @FXML
    private CategoryAxis dayAxes2;

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

    @FXML
    private BarChart<String, Integer> ordersChart1;

    @FXML
    private BarChart<String, Integer> ordersChart2;

    @FXML
    private NumberAxis ordersNumAxes1;

    @FXML
    private NumberAxis ordersNumAxes2;

    @FXML
    private TextArea shopAddress1;

    @FXML
    private TextArea shopAddress2;

    @FXML
    private TextArea shopIDText1;

    @FXML
    private TextArea shopIDText2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    public void start_charts(int shop_id1, Date start_date1, Date end_date1, int shop_id2, Date start_date2,
                             Date end_date2){
        // TODO: initialize charts
    }

}
