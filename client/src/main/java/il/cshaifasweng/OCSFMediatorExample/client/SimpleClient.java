package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import org.hibernate.cache.spi.support.CacheUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;
	public static  Object data;
	public static  Object currentCustomerData;
	public static  Object shopsdata;
	public static  Object workersdata;

	public static  Object CartItemsdata;
	public static  Object Itemsdata;
	public static  Object allItemsData;
	public static  Object Customersdata;

	public static Object ShopAdminsData;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		MsgClass myMsg = null;
		if (msg.getClass().equals(MsgClass.class)) {
			myMsg = (MsgClass) msg;
			if (myMsg.getMsg().equals("all shop items")) {
				System.out.println("in main client handler to get all items");
				data = myMsg.getObj();
				/*	try {
						App.setRoot("controllers/CatalogForRegisteredClients");
					}	catch (Exception e){
						System.out.println("get all execption");
						System.out.println(e);
					}*/

			}
			if (myMsg.getMsg().equals("all customers")) {
				System.out.println("in get customers client side");
				Customersdata = myMsg.getObj();
			}
			if (myMsg.getMsg().equals("your current customer")) {
				currentCustomerData = myMsg.getObj();
				System.out.println("in get your current customer");
				System.out.println(myMsg.getObj());
				}
				if (myMsg.getMsg().equals("all customers"))
				{
					System.out.println("in get customers client side");
					data = myMsg.getObj();
				}
				if (myMsg.getMsg().equals("your current customer"))
				{
					currentCustomerData = myMsg.getObj();
					System.out.println("in get your current customer");
					System.out.println(myMsg.getObj());
				}

				if(myMsg.getMsg().equals("all Shops"))
				{
					System.out.println("in get Shops client side");
					shopsdata = myMsg.getObj();
				}
				if(myMsg.getMsg().equals("all Workers"))
				{
					System.out.println("in get Workers client side");
					workersdata = myMsg.getObj();
				}
			else{
				System.out.println("not done yet");

			}
		}
		if (myMsg.getMsg().equals("all Shops")) {
			System.out.println("in get Shops client side");
			shopsdata = myMsg.getObj();
		}
		if (myMsg.getMsg().equals("all Workers")) {
			System.out.println("in get Workers client side");
			workersdata = myMsg.getObj();
		}
		if (myMsg.getMsg().equals("all CartItems")) {
			System.out.println("in get CartItems client side");
			CartItemsdata = myMsg.getObj();
		}
		if (myMsg.getMsg().equals("Items")) {
			System.out.println("in get Items client side");
			Itemsdata = myMsg.getObj();
		}
		if (myMsg.getMsg().equals("all shopAdmins")){
			System.out.println("in get shopAdmins client side");
			shopsdata = myMsg.getObj();
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
}
