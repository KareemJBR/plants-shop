package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.greenrobot.eventbus.EventBus;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;

    @Override
    public void start(Stage stage) throws IOException {
       // EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();
//        MsgClass msg =new MsgClass("#get customers",null);
//        client.sendToServer(msg);
        scene = new Scene(loadFXML("controllers/Login"));
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
    	EventBus.getDefault().unregister(this);
        client.closeConnection();
		super.stop();
	}


	public static void main(String[] args) {
        launch();
    }

    public static void updateCustomer(Customer customer) throws IOException {
        MsgClass msg = new MsgClass("#customerUpdate", customer);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static void deleteCustomer(Customer customer) throws IOException {
        MsgClass msg = new MsgClass("#customerDelete", customer);
        SimpleClient.getClient().sendToServer(msg);
    }

    public static  ArrayList<Customer> getAllCustomers() throws IOException {
        ArrayList<Customer> customers=new ArrayList<Customer>();
        MsgClass msg =new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        Customersdata = null;
        while(Customersdata==null) {System.out.println("waiting for server1");}
        customers=(ArrayList<Customer>)Customersdata;
        return customers;
    }

    public static  ArrayList<Shop> getAllShops() throws IOException {
        ArrayList<Shop> shops = new ArrayList<Shop>();
        MsgClass msg = new MsgClass("#get Shops", null);
        SimpleClient.getClient().sendToServer(msg);
        shopsdata = null;
        while (shopsdata == null) {System.out.println("waiting for server2");}
        shops = (ArrayList<Shop>) shopsdata;
        return shops;
    }



    public static  ArrayList<NetWorker> getAllWorkers() throws IOException {
        ArrayList<NetWorker> workers = new ArrayList<NetWorker>();
        MsgClass msg = new MsgClass("#get Workers", null);
        SimpleClient.getClient().sendToServer(msg);
        NetWorkersData = null;
        while (NetWorkersData == null) {System.out.println("waiting for server3");}
        workers = (ArrayList<NetWorker>) NetWorkersData;
        return workers;
    }

    public static  ArrayList<CartItem> getAllCartItems() throws IOException {
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        MsgClass msg = new MsgClass("#get CartItems", null);
        SimpleClient.getClient().sendToServer(msg);
        CartItemsdata = null;
        while (CartItemsdata == null) {System.out.println("waiting for server4");}
        cartItems = (ArrayList<CartItem>) CartItemsdata;
        return cartItems;
    }

    public static  ArrayList<Report> getAllReports() throws IOException {
        ArrayList<Report> cartItems = new ArrayList<Report>();
        MsgClass msg = new MsgClass("#get reports", null);
        ReportsData = null;
        SimpleClient.getClient().sendToServer(msg);
        while (ReportsData == null) {System.out.println("waiting for server5");}
        cartItems = (ArrayList<Report>) ReportsData;
        return cartItems;
    }

    public static ArrayList<ShopAdmin> getAllShopAdmins() throws IOException {
        ArrayList<ShopAdmin> shopAdmins = new ArrayList<ShopAdmin>();
        MsgClass msg = new MsgClass("#get shopAdmins", null);
        SimpleClient.getClient().sendToServer(msg);
        ShopAdminsData = null;
        while (CartItemsdata == null) {System.out.println("waiting for server6");}
        shopAdmins = (ArrayList<ShopAdmin>) ShopAdminsData;
        return shopAdmins;
    }

    public static ArrayList<Order> getAllOrders() throws IOException {

        ArrayList<Order> orders = new ArrayList<Order>();
        MsgClass msg = new MsgClass("#get Orders", null);
        SimpleClient.getClient().sendToServer(msg);
        OrdersData = null;
        while (OrdersData == null) {System.out.println("waiting rer for server");}
        orders = (ArrayList<Order>) OrdersData;
        return orders;
    }

    public static void deleteCart(String clientId) throws IOException {
        MsgClass msg = new MsgClass("#delete Cart", null);
        msg.setObj(clientId);
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");

        String str1 = s_day + " " + s_month + " " + s_year;
        String str2 = t_day + " " + t_month + " " + t_year;

        LocalDateTime date1 = LocalDateTime.from(LocalDate.parse(str1, dtf));
        LocalDateTime date2 = LocalDateTime.from(LocalDate.parse(str2, dtf));

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);

        return (int)daysBetween + 1;    // containing the first day
    }

    public static List<Order> getRelevantOrders(boolean is_admin, int shop_id, Calendar start_date, Calendar end_date)
           throws IOException {
/*
        List<Order> all_orders = getAllOrders();
        List<Order> orders_to_show = new ArrayList<>();

        if (is_admin) {
            for (Order all_order : all_orders) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(all_order.getDate());

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()) &&
                        !all_order.gotCancelled())
                    orders_to_show.add(all_order);
            }
        }

        else {
            for (Order all_order : all_orders) {

                if (all_order.getShopID != shop_id || all_order.gotCancelled())
                    continue;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(all_order.getDate());

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
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(report.getdate());

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    reports_to_show.add(report);
            }
        }

        else {
            for (Report report : all_reports) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(report.getdate());

                if (report.getShopID != shop_id)
                    continue;

                if (calendar.getTime().after(start_date.getTime()) && calendar.getTime().before(end_date.getTime()))
                    reports_to_show.add(report);
            }
        }


        return reports_to_show;
        */
     return null;
    }

}