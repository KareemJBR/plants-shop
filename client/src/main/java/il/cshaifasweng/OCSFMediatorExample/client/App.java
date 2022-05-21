package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Shop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.shopsdata;

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
        MsgClass msg =new MsgClass("#get customers",null);
        client.sendToServer(msg);
        scene = new Scene(loadFXML("controllers/LogIN"), 640, 480);
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

    public static  ArrayList<Customer> getAllCustomers() throws IOException {
        ArrayList<Customer> customers=new ArrayList<Customer>();
        MsgClass msg =new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        while(data==null) {System.out.println("waiting for server");}
        customers=(ArrayList<Customer>)data;
        return customers;
    }
    public static  ArrayList<Shop> getAllShops() throws IOException {
        ArrayList<Shop> shops = new ArrayList<Shop>();
        MsgClass msg = new MsgClass("#get Shops", null);
        SimpleClient.getClient().sendToServer(msg);
        while (shopsdata == null) {
            System.out.println("waiting for server");
        }
        shops = (ArrayList<Shop>) shopsdata;
        //        System.out.println(shops.size());
        return shops;
    }
        public static  ArrayList<Flower> getAllFlowers() throws IOException {
        ArrayList<Flower> Flowers=new ArrayList<Flower>();
        MsgClass msg =new MsgClass("#get phots URL",null);
        SimpleClient.getClient().sendToServer(msg);
        Flowers=(ArrayList<Flower>)data;
        return Flowers;
    }

}