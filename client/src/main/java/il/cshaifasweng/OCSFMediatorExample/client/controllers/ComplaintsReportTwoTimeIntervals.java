package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ComplaintsReportTwoTimeIntervals implements Initializable {

    private Calendar start_date1;
    private Calendar end_date1;

    private Calendar start_date2;
    private Calendar end_date2;

    private XYChart.Series<String, Number> series1;
    private XYChart.Series<String, Number> series2;

    @FXML
    private LineChart<String, Number> complaintsChart1;

    @FXML
    private LineChart<String, Number> complaintsChart2;

    @FXML
    private TextArea endDate1;

    @FXML
    private TextArea endDate2;

    @FXML
    private TextArea startDate1;

    @FXML
    private TextArea startDate2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        boolean is_admin = App.getIsAdmin();

        start_date1 = App.getReport_start_date1();
        end_date1 = App.getReport_end_date1();
        start_date2 = App.getReport_start_date2();
        end_date2 = App.getReport_end_date2();

        Calendar start_date;
        Calendar end_date;

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

        series1.setName("Complaints Report");
        series2.setName("Complaints Report");

        int len1 = 0, len2 = 0;

        for (int i=0; i<2; i++) {
            if (i==0){
                start_date = start_date1;
                end_date = end_date1;
            }

            else{
                start_date = start_date2;
                end_date = end_date2;
            }

            List<Report> reports_to_show = null;

            try {
                reports_to_show = App.getRelevantReports(is_admin, -1, start_date, end_date);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (i == 0)
                len1 = App.get_num_of_days_in_time_interval(start_date, end_date);
            else
                len2 = App.get_num_of_days_in_time_interval(start_date, end_date);

            int[] arr = new int[App.get_num_of_days_in_time_interval(start_date, end_date)];
            assert reports_to_show != null;
            for (Report report : reports_to_show) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(report.getYear(), report.getMonth() - 1, report.getDay(),
                        5, 0, 0);

                int col_num = App.get_num_of_days_in_time_interval(start_date, calendar) - 1;

                arr[col_num]++;
            }

            for (int k : arr) {

                String col_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + (start_date.get(Calendar.MONTH) + 1) +
                        "/" + start_date.get(Calendar.YEAR);

                start_date.add(Calendar.DAY_OF_MONTH, 1);

                if (i==0)
                    series1.getData().add(new XYChart.Data<>(col_name, k));
                else
                    series2.getData().add(new XYChart.Data<>(col_name, k));
            }

            if (i == 0)
                complaintsChart1.getData().add(series1);
            else
                complaintsChart2.getData().add(series2);
        }

        start_date1.add(Calendar.DAY_OF_MONTH, -1 * len1);
        start_date2.add(Calendar.DAY_OF_MONTH, -1 * len2);

    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        App.setRoot("controllers/ShowReportsForAdmin");
    }

    public void downloadCSVFile1(ActionEvent actionEvent) throws FileNotFoundException {
        App.createCSVFile("Complaints", start_date1, end_date1, "Date, Num of Complaints", series1);
    }

    public void downloadCSVFile2(ActionEvent actionEvent) throws FileNotFoundException {
        App.createCSVFile("Complaints", start_date2, end_date2, "Date, Num of Complaints", series2);
    }
}
