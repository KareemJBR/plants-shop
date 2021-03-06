package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllShops;


public class OrdersReportOneTimeInterval implements Initializable {

    private Calendar start_date;
    private Calendar end_date;

    private XYChart.Series<String, Number> series;

    @FXML
    private BarChart<String, Number> ordersChart;

    @FXML // fx:id="shopAddress"
    private Label shopAddress; // Value injected by FXMLLoader

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // get relevant fields values

        boolean is_admin = App.getIsAdmin();
        int shop_id = App.getShopID();
        start_date = App.getReport_start_date1();
        end_date = App.getReport_end_date1();

        if(!is_admin)
        {
            try {
                ArrayList<Shop> shops=getAllShops();
                if(shops!=null)
                {
                   for(Shop shop:shops)
                   {
                       if(shop.getId()==shop_id)
                       {
                           shopAddress.setVisible(true);
                           shopAddress.setDisable(false);
                           shopAddress.setText(shop.getAddress());
                           break;
                       }
                   }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        series = new XYChart.Series<>();
        series.setName("Orders Report");

        List<Order> orders_to_show = null;
        ArrayList<Item> all_items = null;

        try {
            orders_to_show = App.getRelevantOrders(is_admin, shop_id, start_date, end_date);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // we shall drop the cancelled orders

        assert orders_to_show != null;
        for (int i = orders_to_show.size() - 1; i >= 0; i--)
            if (orders_to_show.get(i).isGot_cancelled())
                orders_to_show.remove(i);

        try {
            all_items = App.getAllItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert all_items != null;
        int[] arr = new int[all_items.size()];
        Arrays.fill(arr, 0);
        for (int i=0;i<all_items.size();i++)
            for (Order order : orders_to_show)
                for (int k = 0; k < order.getOrderitems().size(); k++)
                    if (order.getOrderitems().get(k).getItem().getId() == all_items.get(i).getId())
                        arr[i] += order.getOrderitems().get(k).getAmount();

        for (int i=0;i<arr.length;i++)
            series.getData().add(new XYChart.Data<>(all_items.get(i).getName(), arr[i]));

        ordersChart.getData().add(series);

    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        if (App.getIsAdmin())
            App.setRoot("controllers/ShowReportsForAdmin");
        else
            App.setRoot("controllers/ShowReportsForShopAdmin");
    }

    public void downloadCSVFile(ActionEvent actionEvent) throws FileNotFoundException {
        // create CSV file for the report and save it to the directory `Reports`
        App.createCSVFile("Orders", start_date, end_date, "Item, Sold Amount", series);
    }
}
