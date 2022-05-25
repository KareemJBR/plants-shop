package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

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

    public void start_controller() {
        // TODO: initialize controller
    }

}
