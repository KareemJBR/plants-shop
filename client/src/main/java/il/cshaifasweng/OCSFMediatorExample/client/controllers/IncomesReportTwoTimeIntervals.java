package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;


public class IncomesReportTwoTimeIntervals implements Initializable {

    @FXML
    private CategoryAxis dayAxes1;

    @FXML
    private CategoryAxis dayAxes2;

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

    @FXML
    private LineChart<String, Double> incomesChart1;

    @FXML
    private LineChart<String, Double> incomesChart2;

    @FXML
    private NumberAxis incomesNumAxes1;

    @FXML
    private NumberAxis incomesNumAxes2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean is_admin = App.getIsAdmin();

        Calendar start_date1 = App.getReport_start_date1();
        Calendar end_date1 = App.getReport_end_date1();
        Calendar start_date2 = App.getReport_start_date2();
        Calendar end_date2 = App.getReport_end_date2();

        startDate1.textProperty().set(start_date1.get(Calendar.DAY_OF_MONTH) + "/" + (start_date1.get(
                Calendar.MONTH) + 1) + "/" + start_date1.get(Calendar.YEAR));

        startDate2.textProperty().set(start_date2.get(Calendar.DAY_OF_MONTH) + "/" + (start_date2.get(
                Calendar.MONTH) + 1) + "/" + start_date2.get(Calendar.YEAR));

        endDate1.textProperty().set(end_date1.get(Calendar.DAY_OF_MONTH) + "/" + (end_date1.get(
                Calendar.MONTH) + 1) + "/" + end_date1.get(Calendar.YEAR));

        endDate2.textProperty().set(end_date2.get(Calendar.DAY_OF_MONTH) + "/" + (end_date2.get(
                Calendar.MONTH) + 1) + "/" + end_date2.get(Calendar.YEAR));

        for (int i=0;i<2;i++) {

            XYChart.Series<String, Double> series = new XYChart.Series<>();
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

            List<Order> orders_to_show = null;
            try {
                orders_to_show = App.getRelevantOrders(is_admin, -1, start_date, end_date);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Double[] arr = new Double[App.get_num_of_days_in_time_interval(start_date, end_date)];

            Arrays.fill(arr, 0.0);

            assert orders_to_show != null;
            for (Order order : orders_to_show) {

                Calendar calendar = App.getCalendarOfOrder(order.getOrder_year(), order.getOrder_month(),
                        order.getOrder_day(), order.getOrder_hour(), order.getOrder_minute(), 0, 0);

                int col_num = App.get_num_of_days_in_time_interval(start_date, calendar) - 1;

                arr[col_num] += order.getPrice();

                // if the customer got refunded, we should subtract the refund from the total incomes
                if (order.isGot_cancelled())
                    arr[col_num] -= order.getRefund();
            }

            for (Double aDouble : arr) {
                series.getData().add(new XYChart.Data<>(start_date.get(Calendar.DAY_OF_MONTH) + "/" +
                        (start_date.get(Calendar.MONTH) + 1) + "/" + start_date.get(Calendar.YEAR), aDouble));

                start_date.add(Calendar.DAY_OF_MONTH, 1);
            }

            if (i == 0)
                incomesChart1.getData().add(series);
            else
                incomesChart2.getData().add(series);
        }
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        App.setRoot("controllers/ShowReportsForAdmin");
    }
}
