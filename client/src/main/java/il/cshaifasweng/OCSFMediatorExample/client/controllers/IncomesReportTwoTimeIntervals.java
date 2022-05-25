package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

import java.util.Date;

public class IncomesReportTwoTimeIntervals {

    @FXML
    private CategoryAxis dayAxes1;

    @FXML
    private CategoryAxis dayAxes2;

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

    @FXML
    private BarChart<?, ?> incomesChart1;

    @FXML
    private BarChart<?, ?> incomesChart2;

    @FXML
    private NumberAxis incomesNumAxes1;

    @FXML
    private NumberAxis incomesNumAxes2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    public void start_controller(boolean is_admin, int shop_id, Date start_date1, Date end_date1, Date start_date2,
                                 Date end_date2) {
        // TODO: initialize controller
    }

}
