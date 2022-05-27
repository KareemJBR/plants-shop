package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


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
    private BarChart<Double, String> incomesChart1;

    @FXML
    private BarChart<Double, String> incomesChart2;

    @FXML
    private NumberAxis incomesNumAxes1;

    @FXML
    private NumberAxis incomesNumAxes2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    public void start_controller(boolean is_admin, Calendar start_date1, Calendar end_date1, Calendar start_date2,
                                 Calendar end_date2) throws IOException {
/*
        for (int i=0;i<2;i++) {
            XYChart.Series<Double, String> series = new XYChart.Series<>();
            series.setName("Incomes Report");

            Calendar start_date, end_date;

            if (i==0) {
                start_date = start_date1;
                end_date = end_date1;
            }

            else {
                start_date = start_date2;
                end_date = end_date2;
            }

//            List<Order> orders_to_show = App.getRelevantOrders(is_admin, -1, start_date, end_date);
//
//            Double[] arr = new Double[App.get_num_of_days_in_time_interval(start_date, end_date)];
//
//            Arrays.fill(arr, 0.0);
//
//            for (Order order : orders_to_show) {
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(order.getDate());
//                arr[App.get_num_of_days_in_time_interval(start_date, calendar)] += order.getPrice();
//            }

            start_date.add(Calendar.DAY_OF_MONTH, -1);

//            for (Double aDouble : arr) {
//                series.getData().add(new XYChart.Data<>(aDouble, start_date.get(Calendar.DAY_OF_MONTH) + "/" +
//                        start_date.get(Calendar.MONTH) + "/" + start_date.get(Calendar.YEAR)));
//
//                if (i == 0)
//                    incomesChart1.getData().add(series);
//                else
//                    incomesChart2.getData().add(series);
//            }
        }
*/
    }

}
