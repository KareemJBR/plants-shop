package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllReports;

public class ComplaintsReportOneTimeInterval {

    @FXML
    private NumberAxis complaintsNumAxes;

    @FXML
    private CategoryAxis dayAxes;

    @FXML
    private BarChart<Integer, String> reportsChart;

    public void start_chart(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date) throws IOException {

        XYChart.Series<Integer, String> series = new XYChart.Series<>();
        series.setName("Complaints Report");

        List<Report> all_reports = getAllReports();
        List<Report> reports_to_show = new ArrayList<>();
        int num_of_days = 0;

        if (is_admin) {
            for (int i=0;i<reports_to_show.size();i++){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(reports_to_show.get(i).getdate());

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    reports_to_show.add(all_reports.get(i));
            }
        }

        else {
            for (int i=0;i<reports_to_show.size();i++) {

                if (reports_to_show.get(i).getShopID == shop_id)     // TODO: check what shop had the complaint
                    continue;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(reports_to_show.get(i).getdate());

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    reports_to_show.add(all_reports.get(i));
            }
        }

        int number_of_days = get_num_of_days_in_time_interval(start_date, end_date);

        int[] arr = new int[number_of_days];
        for (int i=0;i<num_of_days;i++)
            arr[i] = 0;

        for (Report report : reports_to_show) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(report.getdate());

            int col_num = get_num_of_days_in_time_interval(start_date, calendar);

            arr[col_num]++;
        }

        for (int i=0;i<arr.length;i++) {

            if (i != 0)
                start_date.add(Calendar.DAY_OF_MONTH, 1);

            String col_name = start_date.get(Calendar.DAY_OF_MONTH) + "/" + start_date.get(Calendar.MONTH) + "/" +
                    start_date.get(Calendar.YEAR);

            series.getData().add(new XYChart.Data<>(arr[i], col_name));
            reportsChart.getData().add(series);
        }
    }

    private static int get_num_of_days_in_time_interval(Calendar start_date, Calendar end_date) {
        // interval must be valid

        int s_day = start_date.get(Calendar.DAY_OF_MONTH);
        int s_month = start_date.get(Calendar.MONTH);
        int s_year = start_date.get(Calendar.YEAR);

        int t_day = end_date.get(Calendar.DAY_OF_MONTH);
        int t_month = end_date.get(Calendar.MONTH);
        int t_year = end_date.get(Calendar.YEAR);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");

        String str1 = s_day + " " + s_month + " " + s_year;
        String str2 = t_day + " " + t_month + " " + t_year;

        LocalDateTime date1 = LocalDateTime.from(LocalDate.parse(str1, dtf));
        LocalDateTime date2 = LocalDateTime.from(LocalDate.parse(str2, dtf));

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);

        return (int)daysBetween + 1;    // containing the first day
    }

}
