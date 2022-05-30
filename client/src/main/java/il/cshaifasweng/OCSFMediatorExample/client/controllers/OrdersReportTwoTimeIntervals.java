package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class OrdersReportTwoTimeIntervals implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        boolean is_admin = App.getIsAdmin();
        int shop_id = App.getShopID();

        Calendar start_date1 = App.getReport_start_date1();
        Calendar end_date1 = App.getReport_end_date1();
        Calendar start_date2 = App.getReport_start_date2();
        Calendar end_date2 = App.getReport_end_date2();

    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        App.setRoot("controllers/ShowReportsForAdmin");
    }
}
