package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllShops;


public class ComplaintsReportOneTimeInterval implements Initializable {

    private XYChart.Series<String, Number> series;
    private Calendar start_date;
    private Calendar end_date;

    @FXML
    private LineChart<String, Number> reportsChart;

    @FXML // fx:id="shopAddress"
    private Label shopAddress; // Value injected by FXMLLoader

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // initialize GUI items and get the right date values

        boolean is_admin = App.getIsAdmin();
        start_date = App.getReport_start_date1();
        end_date = App.getReport_end_date1();
        int shop_id = App.getShopID();

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
        series.setName("Complaints Report");

        List<Report> reports_to_show = null;
        try {
            reports_to_show = App.getRelevantReports(is_admin, shop_id, start_date, end_date);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int num_of_days = App.get_num_of_days_in_time_interval(start_date, end_date);
        int[] arr = new int[num_of_days];

        for (int i=0;i<num_of_days;i++)
            arr[i] = 0;

        assert reports_to_show != null;
        for (Report report : reports_to_show) {

            Calendar calendar = Calendar.getInstance();

            calendar.set(report.getYear(), report.getMonth() - 1, report.getDay(),
                    5, 0, 0);

            int col_num = App.get_num_of_days_in_time_interval(start_date, calendar) - 1;

            arr[col_num]++;
        }

        for (int j : arr) {

            String col_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + (start_date.get(Calendar.MONTH) + 1) + "/" +
                    start_date.get(Calendar.YEAR);

            start_date.add(Calendar.DAY_OF_MONTH, 1);

            series.getData().add(new XYChart.Data<>(col_name, j));
        }

        reportsChart.getData().add(series);

        start_date.add(Calendar.DAY_OF_MONTH, -1 * arr.length);
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        if (App.getIsAdmin())
            App.setRoot("controllers/ShowReportsForAdmin");
        else
            App.setRoot("controllers/ShowReportsForShopAdmin");
    }

    public void downloadCSVFile(ActionEvent actionEvent) throws FileNotFoundException {
        // create CSV file of the report
        App.createCSVFile("Complaints", start_date, end_date, "Date, Num of Complaints", series);
    }
}
