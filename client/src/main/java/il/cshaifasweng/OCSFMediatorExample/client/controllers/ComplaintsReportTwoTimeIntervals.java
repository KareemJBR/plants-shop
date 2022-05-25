package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

import java.util.Date;

public class ComplaintsReportTwoTimeIntervals {

    @FXML
    private BarChart<?, ?> complaintsChart1;

    @FXML
    private BarChart<?, ?> complaintsChart2;

    @FXML
    private NumberAxis complaintsNumAxes1;

    @FXML
    private NumberAxis complaintsNumAxes2;

    @FXML
    private CategoryAxis dayAxes1;

    @FXML
    private CategoryAxis dayAxes2;

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

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
                             Date end_date2) {
        // TODO: get all complaints in the time intervals and plot them in the chart reportsChart
    }

}
