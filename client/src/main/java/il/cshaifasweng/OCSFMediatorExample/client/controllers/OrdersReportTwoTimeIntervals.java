package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OrdersReportTwoTimeIntervals implements Initializable {

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
    private LineChart<String, Number> ordersChart1;

    @FXML
    private LineChart<String, Number> ordersChart2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Calendar start_date;
        Calendar end_date;

        // getting parameters from App

        start_date1 = App.getReport_start_date1();
        end_date1 = App.getReport_end_date1();
        start_date2 = App.getReport_start_date2();
        end_date2 = App.getReport_end_date2();

        boolean is_admin = App.getIsAdmin();
        int shop_id = App.getShopID();

        // initialize GUI items

        endDate1.textProperty().set(end_date1.get(Calendar.DAY_OF_MONTH) + "/" + (end_date1.get(
                Calendar.MONTH) + 1) + "/" + end_date1.get(Calendar.YEAR));

        series1 = new XYChart.Series<>();
        series2 = new XYChart.Series<>();

        series1.setName("Orders Report");
        series2.setName("Orders Report");

        endDate2.textProperty().set(end_date2.get(Calendar.DAY_OF_MONTH) + "/" + (end_date2.get(
                Calendar.MONTH) + 1) + "/" + end_date2.get(Calendar.YEAR));

        ArrayList<Item> all_items = null;

        startDate1.textProperty().set(start_date1.get(Calendar.DAY_OF_MONTH) + "/" + (start_date1.get(
                Calendar.MONTH) + 1) + "/" + start_date1.get(Calendar.YEAR));

        startDate2.textProperty().set(start_date2.get(Calendar.DAY_OF_MONTH) + "/" + (start_date2.get(
                Calendar.MONTH) + 1) + "/" + start_date2.get(Calendar.YEAR));


        try {
            all_items = App.getAllItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int a=0;a<2;a++) {

            if (a==0) {
                start_date = start_date1;
                end_date = end_date1;
            }
            else {
                start_date = start_date2;
                end_date = end_date2;
            }

            List<Order> orders_to_show = null;

            try {
                orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // we shall drop the cancelled orders

            assert orders_to_show != null;
            for (int i = orders_to_show.size() - 1; i > -1; i--)
                if (orders_to_show.get(i).isGot_cancelled())
                    orders_to_show.remove(i);

            assert all_items != null;
            int[] arr = new int[all_items.size()];
            Arrays.fill(arr, 0);

            // search the appearance of each item in all relevant orders
            for (int i=0;i<=all_items.size()-1;i++)
                for (Order order : orders_to_show)
                    for (int k = 0; k < order.getOrderitems().size(); k++)
                        if (order.getOrderitems().get(k).getItem().getId() == all_items.get(i).getId())
                            arr[i] += order.getOrderitems().get(k).getAmount();


            for (int i=0;i<arr.length;i++)
                if (a==0)
                    series1.getData().add(new XYChart.Data<>(all_items.get(i).getName(), arr[i]));
                else
                    series2.getData().add(new XYChart.Data<>(all_items.get(i).getName(), arr[i]));

            if (a==0)
                ordersChart1.getData().add(series1);
            else
                ordersChart2.getData().add(series2);
        }

    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        App.setRoot("controllers/ShowReportsForAdmin");
    }

    public void downloadCSVFile1(ActionEvent actionEvent) throws FileNotFoundException {
        // create a CSV file of the report 1
        App.createCSVFile("Orders", start_date1, end_date1, "Item, Sold Amount", series1);
    }

    public void downloadCSVFile2(ActionEvent actionEvent) throws FileNotFoundException {
        // create a CSV file of the report 2
        App.createCSVFile("Orders", start_date2, end_date2, "Item, Sold Amount", series2);
    }
}
