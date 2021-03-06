package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.greenrobot.eventbus.EventBus;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_username;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.Login_customer;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;

    private static String customer_id_for_admin_view;
    private static Calendar report_start_date1;
    private static Calendar report_start_date2;
    private static Calendar report_end_date1;
    private static Calendar report_end_date2;
    private static boolean is_admin;
    private static int shop_id;
    private static int report_id_for_client_service;
    private static Item edit_item;


    @Override
    public void start(Stage stage) throws IOException {
        client = SimpleClient.getClient();
        client.openConnection();
        scene = new Scene(loadFXML("controllers/LogIN"),780,600);
        stage.setTitle("Lilac");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
        if(Login_customer!=null)
        {
            Customer customer=getCustomer(Login_customer.getId());
            customer.setOnline(false);
            updateCustomer(customer);
        }
        client.closeConnection();
		super.stop();
	}

	public static void main(String[] args) {
        launch();
    }

    public static Customer getCustomer(String id) throws IOException {
        ArrayList<Customer> customers=getAllCustomers();
        if(customers!=null)
        {
            for(Customer customer:customers)
            {
                if(customer.getUser_id().equals(id))
                {
                    return customer;
                }
            }
        }
        return null;
    }

    public static Shop getShop(String address) throws IOException {
        ArrayList<Shop> shops=getAllShops();
        if(shops!=null)
        {
            for(Shop shop:shops)
            {
                if(shop.getAddress().equals(address))
                {
                    return shop;
                }
            }
        }
        return null;
    }

    public static void updateCustomer(Customer customer) throws IOException {
        MsgClass msg = new MsgClass("#customerUpdate", customer);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void updateReport(Report report) throws IOException {
        MsgClass msg = new MsgClass("#update report", report);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void deleteCustomer(Customer customer) throws IOException {
        MsgClass msg = new MsgClass("#customerDelete", customer);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static  Customer getCurrentCustomer() throws IOException {
        MsgClass msg = new MsgClass("#get current customer", LoginClient_username);
        currentCustomerData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (currentCustomerData == null) {System.out.println("waiting for current customer");}
        return (Customer)currentCustomerData;
    }

    public static ArrayList<Item> getAllItems() throws IOException {
        ArrayList<Item> items = new ArrayList<Item>();
        MsgClass msg = new MsgClass("#get shop items", null);
        allItemsData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (allItemsData == null) {System.out.println("waiting for server8");}
        items = (ArrayList<Item>) allItemsData;
        return items;
    }

    public static  ArrayList<Item> getAllitemsUnderSale() throws IOException {
        ArrayList<Item> items=new ArrayList<Item>();
        MsgClass msg =new MsgClass("#get shop items that under sale",null);
        allItemsData = null;
        SimpleClient.getClient().sendToServer(msg);
        while(allItemsData==null) {System.out.println("waiting for server15");}
        items=(ArrayList<Item>)allItemsData;
        return items;
    }

    public static  ArrayList<Customer> getAllCustomers() throws IOException {
        ArrayList<Customer> customers=new ArrayList<Customer>();
        MsgClass msg =new MsgClass("#get customers",null);
        Customersdata = null;
        SimpleClient.getClient().sendToServer(msg);
        while(Customersdata==null) {System.out.println("waiting for server1");}
        customers=(ArrayList<Customer>)Customersdata;
        return customers;
    }

    public static  ArrayList<Shop> getAllShops() throws IOException {
        ArrayList<Shop> shops = new ArrayList<Shop>();
        MsgClass msg = new MsgClass("#get Shops", null);
        shopsdata = null;
        SimpleClient.getClient().sendToServer(msg);
        while (shopsdata == null) {System.out.println("waiting for server2");}
        shops = (ArrayList<Shop>) shopsdata;
        return shops;
    }

    public static  ArrayList<NetWorker> getAllNetWorkers() throws IOException {
        ArrayList<NetWorker> workers = new ArrayList<NetWorker>();
        MsgClass msg = new MsgClass("#get NetWorkers", null);
        NetWorkersData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (NetWorkersData == null) {System.out.println("waiting for server3");}
        workers = (ArrayList<NetWorker>) NetWorkersData;
        return workers;
    }

    public static  ArrayList<CartItem> getAllCartItems() throws IOException {
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        MsgClass msg = new MsgClass("#get CartItems", null);
        CartItemsdata = null;
        SimpleClient.getClient().sendToServer(msg);
        while (CartItemsdata == null) {System.out.println("waiting for server4");}
        cartItems = (ArrayList<CartItem>) CartItemsdata;
        return cartItems;
    }

    public static  ArrayList<Report> getAllReports() throws IOException {
        ArrayList<Report> allReports = new ArrayList<Report>();
        ReportsData = null;
        MsgClass msg = new MsgClass("#get reports", null);
        SimpleClient.getClient().sendToServer(msg);
        while (ReportsData == null) {System.out.println("waiting for server5");}
        allReports = (ArrayList<Report>) ReportsData;
        return allReports;
    }

    public static ArrayList<ShopAdmin> getAllShopAdmins() throws IOException {
        ArrayList<ShopAdmin> shopAdmins = new ArrayList<ShopAdmin>();
        MsgClass msg = new MsgClass("#get shopAdmins", null);
        ShopAdminsData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (ShopAdminsData == null) {System.out.println("waiting for server6");}
        shopAdmins = (ArrayList<ShopAdmin>) ShopAdminsData;
        return shopAdmins;
    }

    public static ArrayList<Order> getAllOrders() throws IOException {

        ArrayList<Order> orders = new ArrayList<Order>();
        MsgClass msg = new MsgClass("#get Orders", null);
        OrdersData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (OrdersData == null) {System.out.println("waiting for server7");}
        orders = (ArrayList<Order>) OrdersData;
        return orders;
    }

    public static ArrayList<SupportWorker> getAllSupportWorkers() throws IOException {
        ArrayList<SupportWorker> support_workers = new ArrayList<SupportWorker>();
        MsgClass msg = new MsgClass("#get SupportWorkers", null);
        SupportWorkersData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (SupportWorkersData == null) {System.out.println("waiting for server10");}
        support_workers = (ArrayList<SupportWorker>) SupportWorkersData;
        return support_workers;
    }

    public static ArrayList<Order> getAllClientOrders(String clientId) throws IOException {

        ArrayList<Order> orders =getAllOrders();
        ArrayList<Order> clientorders =new ArrayList<Order>();
        if(orders!=null)
        {
            if(orders.size()!=0)
            {
                for(int i=0;i< orders.size();i++)
                {
                    if(orders.get(i).getCustomer().getUser_id().equals(clientId))
                    {
                        clientorders.add(orders.get(i));
                    }
                }
            }
        }
      return clientorders;
    }

    public static void AddOrderIem(OrderItem orderItem) throws IOException {
        MsgClass msg = new MsgClass("#add orderitem", null);
        msg.setObj(orderItem);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void deleteCart(String clientId) throws IOException {
        MsgClass msg = new MsgClass("#delete Cart", null);
        msg.setObj(clientId);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void deleteCartitem(int cartitemId) throws IOException {
        MsgClass msg = new MsgClass("#delete CartItem", null);
        msg.setObj(cartitemId);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void decrementAmountofCartItem(int cartitemId) throws IOException {
        MsgClass msg = new MsgClass("#decrement amount", null);
        msg.setObj(cartitemId);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void incrementAmountofCartItem(int cartitemId) throws IOException {
        MsgClass msg = new MsgClass("#increment amount", null);
        msg.setObj(cartitemId);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void cancelOrder(int id,double refund) throws IOException {
        MsgClass msg = new MsgClass("#cancel order", null);
        ArrayList<Double> ob=new ArrayList<Double>();
        ob.add((double) id);
        ob.add(refund);
        msg.setObj(ob);
        SimpleClient.getClient().sendToServer(msg);
    }
        
    public static int get_num_of_days_in_time_interval(Calendar start_date, Calendar end_date) {
        // interval must be valid

        int s_day = start_date.get(Calendar.DAY_OF_MONTH);
        int s_month = start_date.get(Calendar.MONTH);
        int s_year = start_date.get(Calendar.YEAR);

        int t_day = end_date.get(Calendar.DAY_OF_MONTH);
        int t_month = end_date.get(Calendar.MONTH);
        int t_year = end_date.get(Calendar.YEAR);

        // month is saved in [0, 11] in Calendar unlike in LocalDate, so we have to add 1 to the month value
        LocalDateTime date1 = LocalDateTime.of(s_year, s_month + 1, s_day, 1, 0);
        LocalDateTime date2 = LocalDateTime.of(t_year, t_month + 1, t_day, 1, 0);

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);

        return (int)daysBetween + 1;    // containing the first day
    }

    public static List<Order> getRelevantOrders(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date)
           throws IOException {

        List<Order> all_orders = getAllOrders();
        List<Order> orders_to_show = new ArrayList<>();

        if (is_admin) {
            for (Order all_order : all_orders) {
                Calendar calendar = App.createCalendar(all_order.getOrder_year(), all_order.getOrder_month(),
                        all_order.getOrder_day(), all_order.getOrder_hour(),
                        all_order.getOrder_minute(), 0, 0);

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime())
                        &&!all_order.isGot_cancelled())
                    orders_to_show.add(all_order);
            }
        }

        else {
            for (Order all_order : all_orders) {

                if (all_order.getShop().getId() != shop_id || all_order.isGot_cancelled())
                    continue;

                Calendar calendar = App.createCalendar(all_order.getOrder_year(), all_order.getOrder_month(),
                        all_order.getOrder_day(), all_order.getOrder_hour(),
                        all_order.getOrder_minute(), 0, 0);

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    orders_to_show.add(all_order);
            }
        }

        return orders_to_show;
    }

    public static List<Report> getRelevantReports(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date)
            throws IOException{

        List<Report> all_reports = getAllReports();

        List<Report> reports_to_show = new ArrayList<>();

        if (is_admin) {
            for (Report report : all_reports) {
                Calendar calendar = App.createCalendar(report.getYear(), report.getMonth(),
                        report.getDay(), 2, 0, 0, 0);

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    reports_to_show.add(report);
            }
        }

        else {
            for (Report report : all_reports) {
                Calendar calendar = App.createCalendar(report.getYear(), report.getMonth(),
                        report.getDay(), 2, 0, 0, 0);

                if (report.getShop().getId() != shop_id)
                    continue;

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    reports_to_show.add(report);
            }
        }

        return reports_to_show;
    }

    public static void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(head);
                alert.showAndWait();
            }
        });
    }

    public static Calendar localDateToCalendar(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static void setCustomerIDForAdminView(String customer_id) {
        customer_id_for_admin_view = customer_id;
    }

    public static String getCustomer_id_for_admin_view() {
        return customer_id_for_admin_view;
    }

    public static void setReport_start_date1(Calendar start_date1) {
        report_start_date1 = start_date1;
    }

    public static void setReport_start_date2(Calendar start_date2) {
        report_start_date2 = start_date2;
    }

    public static void setReport_end_date1(Calendar end_date1) {
        report_end_date1 = end_date1;
    }

    public static void setReport_end_date2(Calendar end_date2) {
        report_end_date2 = end_date2;
    }

    public static void setIs_admin(boolean admin_v) {
        is_admin = admin_v;
    }

    public static void setShop_id(int p_shop_id) {
        shop_id = p_shop_id;
    }

    public static Calendar getReport_start_date1() {
        return report_start_date1;
    }

    public static Calendar getReport_start_date2() {
        return report_start_date2;
    }

    public static Calendar getReport_end_date1() {
        return report_end_date1;
    }

    public static Calendar getReport_end_date2() {
        return report_end_date2;
    }

    public static boolean getIsAdmin() {
        return is_admin;
    }

    public static int getShopID() {
        return shop_id;
    }

    public static int getReport_id_for_client_service() {
        return report_id_for_client_service;
    }

    public static void setReport_id_for_client_service(int report_id) {
        report_id_for_client_service = report_id;
    }

    public static Item getEdit_item() {
        return edit_item;
    }

    public static void setEdit_item(Item edit_item) {
        App.edit_item = edit_item;
    }

    public static Calendar createCalendar(int year, int month, int day, int hour, int minute, int second,
                                          int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return calendar;
    }

    public static void createCSVFile(String name_prefix, Calendar start_date, Calendar end_date, String first_line,
                                     XYChart.Series<String, Number> data) throws FileNotFoundException {
        String file_name = App.getCSVFileName(name_prefix, start_date, end_date);

        File csv_file = new File(file_name);

        File temp = new File("Reports/");
        temp.mkdir();

        PrintWriter out = new PrintWriter(csv_file);

        out.println(first_line);

        for (int i=0;i<data.getData().size();i++)
            out.println(data.getData().get(i).XValueProperty().get() + ", " +
                    data.getData().get(i).YValueProperty().get());

        out.close();
        showAlert("Success", file_name + " successfully created.");
    }

    public static String getCSVFileName(String prefix, Calendar start_date, Calendar end_date) {
        String reports_dir = "Reports/";
        String res = reports_dir + prefix;

        if (start_date.get(Calendar.DAY_OF_MONTH) < 10)
            res += "0";
        res += start_date.get(Calendar.DAY_OF_MONTH);
        if (start_date.get(Calendar.MONTH) < 9)
            res += "0";
        res += Integer.toString(start_date.get(Calendar.MONTH) + 1);    // months in Calendar start from 0 not 1
        res += start_date.get(Calendar.YEAR);
        res += "To";

        if (end_date.get(Calendar.DAY_OF_MONTH) < 10)
            res += "0";
        res += end_date.get(Calendar.DAY_OF_MONTH);
        if (end_date.get(Calendar.MONTH) < 9)
            res += "0";
        res += Integer.toString(end_date.get(Calendar.MONTH) + 1);
        res += end_date.get(Calendar.YEAR);
        return res + ".csv";
    }

}