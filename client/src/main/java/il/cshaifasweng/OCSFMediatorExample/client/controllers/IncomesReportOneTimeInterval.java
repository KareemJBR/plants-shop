package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllOrders;

public class IncomesReportOneTimeInterval {

    @FXML
    private CategoryAxis dayAxes;

    @FXML
    private NumberAxis incomesNumAxes;

    @FXML
    private BarChart<Double, String> reportsChart;

    public void start_chart(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date) throws IOException {

        XYChart.Series<Double, String> series = new XYChart.Series<>();
        series.setName("Incomes Report");

        List<Order> all_orders = getAllOrders();

//        List<Order> orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
//
//        int num_of_days = App.get_num_of_days_in_time_interval(start_date, end_date);

//        Double[] arr = new Double[num_of_days];
//
//        for (int i=0;i<num_of_days;i++)
//            arr[i] = 0.0;
//
//        for (Order order : orders_to_show) {
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(order.getDate());
//
//            int col_num = App.get_num_of_days_in_time_interval(start_date, calendar);
//
//            arr[col_num] += order.getPrice();
//        }
//
//        start_date.add(Calendar.DAY_OF_MONTH, -1);
//
//        for (int i=0;i<num_of_days;i++) {
//
//            String c_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + start_date.get(Calendar.MONTH) + "/" +
//                    start_date.get(Calendar.YEAR);
//
//            series.getData().add(new XYChart.Data<>(arr[i], c_name));
//            reportsChart.getData().add(series);
//        }

//        List<Order> orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
//
//        int num_of_days = App.get_num_of_days_in_time_interval(start_date, end_date);
//
//        Double[] arr = new Double[num_of_days];
//
//        for (int i=0;i<num_of_days;i++)
//            arr[i] = 0.0;
//
//        for (Order order : orders_to_show) {
//
//            Calendar calendar = Calendar.getInstance();
//           // calendar.setTime(order.getDate());
//
//            int col_num = App.get_num_of_days_in_time_interval(start_date, calendar);
//
//            arr[col_num] += order.getPrice();
//        }
//
//        start_date.add(Calendar.DAY_OF_MONTH, -1);
//
//        for (int i=0;i<num_of_days;i++) {
//
//            String c_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + start_date.get(Calendar.MONTH) + "/" +
//                    start_date.get(Calendar.YEAR);
//
//            series.getData().add(new XYChart.Data<>(arr[i], c_name));
//            reportsChart.getData().add(series);
//        }

    }
}
