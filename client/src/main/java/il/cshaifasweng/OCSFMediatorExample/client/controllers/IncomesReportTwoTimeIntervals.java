package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;


public class IncomesReportTwoTimeIntervals implements Initializable {

    private Calendar start_date1;
    private Calendar start_date2;
    private Calendar end_date1;
    private Calendar end_date2;

    private XYChart.Series<String, Number> series1;
    private XYChart.Series<String, Number> series2;

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

    @FXML
    private LineChart<String, Number> incomesChart1;

    @FXML
    private LineChart<String, Number> incomesChart2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean is_admin = App.getIsAdmin();

        Calendar start_date;
        Calendar end_date;

        start_date1 = App.getReport_start_date1();
        end_date1 = App.getReport_end_date1();
        start_date2 = App.getReport_start_date2();
        end_date2 = App.getReport_end_date2();

        startDate1.textProperty().set(start_date1.get(Calendar.DAY_OF_MONTH) + "/" + (start_date1.get(
                Calendar.MONTH) + 1) + "/" + start_date1.get(Calendar.YEAR));

        startDate2.textProperty().set(start_date2.get(Calendar.DAY_OF_MONTH) + "/" + (start_date2.get(
                Calendar.MONTH) + 1) + "/" + start_date2.get(Calendar.YEAR));

        endDate1.textProperty().set(end_date1.get(Calendar.DAY_OF_MONTH) + "/" + (end_date1.get(
                Calendar.MONTH) + 1) + "/" + end_date1.get(Calendar.YEAR));

        endDate2.textProperty().set(end_date2.get(Calendar.DAY_OF_MONTH) + "/" + (end_date2.get(
                Calendar.MONTH) + 1) + "/" + end_date2.get(Calendar.YEAR));

        series1 = new XYChart.Series<>();
        series2 = new XYChart.Series<>();

        series1.setName("Incomes Report");
        series2.setName("Incomes Report");

        int len1 = 0, len2 = 0;

        for (int i=0;i<2;i++) {

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

            if (i==0)
                len1 = App.get_num_of_days_in_time_interval(start_date, end_date);
            else
                len2 = App.get_num_of_days_in_time_interval(start_date, end_date);

            Double[] arr = new Double[App.get_num_of_days_in_time_interval(start_date, end_date)];

            Arrays.fill(arr, 0.0);

            assert orders_to_show != null;
            for (Order order : orders_to_show) {

                Calendar calendar = App.createCalendar(order.getOrder_year(), order.getOrder_month(),
                        order.getOrder_day(), order.getOrder_hour(), order.getOrder_minute(), 0, 0);

                int col_num = App.get_num_of_days_in_time_interval(start_date, calendar) - 1;

                arr[col_num] += order.getPrice();

                // if the customer got refunded, we should subtract the refund from the total incomes
                if (order.isGot_cancelled())
                    arr[col_num] -= order.getRefund();
            }

            for (Double aDouble : arr) {
                if (i==0)
                    series1.getData().add(new XYChart.Data<>(start_date.get(Calendar.DAY_OF_MONTH) + "/" +
                        (start_date.get(Calendar.MONTH) + 1) + "/" + start_date.get(Calendar.YEAR), aDouble));
                else
                    series2.getData().add(new XYChart.Data<>(start_date.get(Calendar.DAY_OF_MONTH) + "/" +
                            (start_date.get(Calendar.MONTH) + 1) + "/" + start_date.get(Calendar.YEAR), aDouble));

                start_date.add(Calendar.DAY_OF_MONTH, 1);
            }

            if (i == 0)
                incomesChart1.getData().add(series1);
            else
                incomesChart2.getData().add(series2);
        }

        start_date1.add(Calendar.DAY_OF_MONTH, -1 * len1);
        start_date2.add(Calendar.DAY_OF_MONTH, -1 * len2);

    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        App.setRoot("controllers/ShowReportsForAdmin");
    }

    public void downloadCSVFile1(ActionEvent actionEvent) throws FileNotFoundException {
        App.createCSVFile("Incomes", start_date1, end_date1, "Date, Incomes", series1);
    }

    public void downloadCSVFile2(ActionEvent actionEvent) throws FileNotFoundException {
        App.createCSVFile("Incomes", start_date2, end_date2, "Date, Incomes", series2);
    }
}
