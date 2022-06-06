package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;


public class IncomesReportOneTimeInterval implements Initializable {

    private Calendar start_date;
    private Calendar end_date;

    private XYChart.Series<String, Number> series;

    @FXML
    private LineChart<String, Number> reportsChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // update the values of the dates and initialize the GUI items

        boolean is_admin = App.getIsAdmin();
        int shop_id = App.getShopID();
        start_date = App.getReport_start_date1();
        end_date = App.getReport_end_date1();

        series = new XYChart.Series<>();
        series.setName("Incomes Report");

        List<Order> orders_to_show = null;
        try {
            orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int num_of_days = App.get_num_of_days_in_time_interval(start_date, end_date);
        Double[] arr = new Double[num_of_days];

        for (int i=0;i<num_of_days;i++)
            arr[i] = 0.0;

        assert orders_to_show != null;
        for (Order order : orders_to_show) {

            int order_day = order.getOrder_day();
            int order_month = order.getOrder_month();
            int order_year = order.getOrder_year();
            int order_hour = order.getOrder_hour();
            int order_minute = order.getOrder_minute();

            Calendar calendar = App.createCalendar(order_year, order_month, order_day, order_hour,
                    order_minute, 0, 0);

            int col_num = App.get_num_of_days_in_time_interval(start_date, calendar) - 1;

            arr[col_num] += order.getPrice();

            // if the customer got refunded, we should subtract the refund from the total incomes
            if (order.isGot_cancelled())
                arr[col_num] -= order.getRefund();
        }

        for (int i=0;i<num_of_days;i++) {

            String c_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + (start_date.get(Calendar.MONTH) + 1) + "/" +
                    start_date.get(Calendar.YEAR);

            start_date.add(Calendar.DAY_OF_MONTH, 1);

            series.getData().add(new XYChart.Data<>(c_name, arr[i]));
        }

        reportsChart.getData().add(series);

        start_date.add(Calendar.DAY_OF_MONTH, -1 * arr.length);
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        // shopAdmin and main admin shall have different controllers for creating reports since shopAdmins cannot
        // compare two reports at the same time
        if (App.getIsAdmin())
            App.setRoot("controllers/ShowReportsForAdmin");
        else
            App.setRoot("controllers/ShowReportsForShopAdmin");
    }

    public void downloadCSVFile(ActionEvent actionEvent) throws FileNotFoundException {
        App.createCSVFile("Incomes", start_date, end_date, "Date, Incomes", series);
    }
}
