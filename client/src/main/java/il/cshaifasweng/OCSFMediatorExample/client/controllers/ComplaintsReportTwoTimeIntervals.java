package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ComplaintsReportTwoTimeIntervals implements Initializable {

    @FXML
    private BarChart<Integer, String> complaintsChart1;

    @FXML
    private BarChart<Integer, String> complaintsChart2;

    @FXML
    private NumberAxis complaintsNumAxes1;

    @FXML
    private NumberAxis complaintsNumAxes2;

    @FXML
    private CategoryAxis dayAxes1;

    @FXML
    private CategoryAxis dayAxes2;

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

        Calendar start_date1 = App.getReport_start_date1();
        Calendar end_date1 = App.getReport_end_date1();
        Calendar start_date2 = App.getReport_start_date2();
        Calendar end_date2 = App.getReport_end_date2();

        for (int i=0; i<2; i++) {
            XYChart.Series<Integer, String> series = new XYChart.Series<>();
            series.setName("Complaints Report");
            Calendar start_date, end_date;

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

            int[] arr = new int[App.get_num_of_days_in_time_interval(start_date, end_date)];

            assert reports_to_show != null;
            for (Report report : reports_to_show) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(report.getdate());

                int col_num = App.get_num_of_days_in_time_interval(start_date, calendar);

                arr[col_num]++;
            }

            start_date.add(Calendar.DAY_OF_MONTH, -1);

            for (int k : arr) {

                String col_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + start_date.get(Calendar.MONTH) + "/" +
                        start_date.get(Calendar.YEAR);

                series.getData().add(new XYChart.Data<>(k, col_name));

                if (i == 0)
                    complaintsChart1.getData().add(series);
                else
                    complaintsChart2.getData().add(series);
            }
        }

    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        App.setRoot("controllers/ShowReportsForAdmin");
    }
}
