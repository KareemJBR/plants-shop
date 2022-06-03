package il.cshaifasweng.OCSFMediatorExample.entities;

public class ReportToDisplay {
    private final int report_id;
    private final String report_date;
    private final String client_id;
    private final int shop_id;

    public ReportToDisplay(int report_id, String report_date, String client_id, int shop_id) {
        this.report_id = report_id;
        this.report_date = report_date;
        this.client_id = client_id;
        this.shop_id = shop_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public String getReport_date() {
        return report_date;
    }

    public String getClient_id() {
        return client_id;
    }

    public int getShop_id() {
        return shop_id;
    }

}
