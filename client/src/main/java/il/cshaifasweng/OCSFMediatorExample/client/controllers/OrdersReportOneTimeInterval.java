package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public class OrdersReportOneTimeInterval {

    @FXML
    private CategoryAxis itemAxes;

    @FXML
    private BarChart<Integer, String> ordersChart;

    @FXML
    private NumberAxis ordersNumAxes;

    private void start_chart(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date) throws IOException {

        XYChart.Series<Integer, String> series = new XYChart.Series<>();
        series.setName("Orders Report");


//        List<Order> orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
        List<Item> all_items = App.getAllItems();

        // TODO: get all flowers and display the chart!

    }

}
