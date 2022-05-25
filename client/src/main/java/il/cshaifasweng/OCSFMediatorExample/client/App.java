package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


import org.greenrobot.eventbus.EventBus;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.*;

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
        while(Customersdata==null) {System.out.println("waiting for server");}
        customers=(ArrayList<Customer>)Customersdata;
        return customers;
    }

    public static  ArrayList<Shop> getAllShops() throws IOException {
        ArrayList<Shop> shops = new ArrayList<Shop>();
        MsgClass msg = new MsgClass("#get Shops", null);
        SimpleClient.getClient().sendToServer(msg);
        while (shopsdata == null) {System.out.println("waiting for server");}
        shops = (ArrayList<Shop>) shopsdata;
        return shops;
    }

     public static  ArrayList<Flower> getAllFlowers() throws IOException {

        ArrayList<Flower> Flowers=new ArrayList<Flower>();
        MsgClass msg =new MsgClass("#get phots URL",null);
        SimpleClient.getClient().sendToServer(msg);
        Flowers=(ArrayList<Flower>)data;
        return Flowers;
    }

    public static  ArrayList<NetWorker> getAllWorkers() throws IOException {
        ArrayList<NetWorker> workers = new ArrayList<NetWorker>();
        MsgClass msg = new MsgClass("#get Workers", null);
        SimpleClient.getClient().sendToServer(msg);
        while (NetWorkersData == null) {System.out.println("waiting for server");}
        workers = (ArrayList<NetWorker>) NetWorkersData;
        return workers;
    }

    public static  ArrayList<CartItem> getAllCartItems() throws IOException {
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        MsgClass msg = new MsgClass("#get CartItems", null);
        SimpleClient.getClient().sendToServer(msg);
        while (CartItemsdata == null) {System.out.println("waiting for server");}
        cartItems = (ArrayList<CartItem>) CartItemsdata;
        return cartItems;
    }

    public static  ArrayList<Report> getAllReports() throws IOException {
        ArrayList<Report> cartItems = new ArrayList<Report>();
        MsgClass msg = new MsgClass("#get Reports", null);
        SimpleClient.getClient().sendToServer(msg);
        while (CartItemsdata == null) {System.out.println("waiting for server");}
        cartItems = (ArrayList<Report>) ReportsData;
        return cartItems;
    }

    public static ArrayList<ShopAdmin> getAllShopAdmins() throws IOException {
        ArrayList<ShopAdmin> shopAdmins = new ArrayList<ShopAdmin>();
        MsgClass msg = new MsgClass("#get shopAdmins", null);
        SimpleClient.getClient().sendToServer(msg);
        while (CartItemsdata == null) {System.out.println("waiting for server");}
        shopAdmins = (ArrayList<ShopAdmin>) ShopAdminsData;
        return shopAdmins;
    }
}