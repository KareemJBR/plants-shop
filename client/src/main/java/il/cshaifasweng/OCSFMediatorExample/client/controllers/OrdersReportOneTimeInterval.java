package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;


public class OrdersReportOneTimeInterval implements Initializable {

    @FXML
    private CategoryAxis itemAxes;

    @FXML
    private BarChart<Integer, String> ordersChart;

    @FXML
    private NumberAxis ordersNumAxes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        boolean is_admin = App.getIsAdmin();
        int shop_id = App.getShopID();
        Calendar start_date = App.getReport_start_date1();
        Calendar end_date = App.getReport_end_date1();

        XYChart.Series<Integer, String> series = new XYChart.Series<>();
        series.setName("Orders Report");


        try {
            List<Order> orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: get all flowers and display the chart!
    }
}
