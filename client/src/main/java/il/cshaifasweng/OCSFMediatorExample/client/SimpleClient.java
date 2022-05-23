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
<<<<<<< HEAD
	public static  Object currentCustomerData;
=======
	public static  Object shopsdata;
	public static  Object workersdata;
>>>>>>> main


	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
			if (msg.getClass().equals(MsgClass.class)) {
				MsgClass myMsg = (MsgClass) msg;
				if (myMsg.getMsg().equals("all flowers")) {
					System.out.println("in main client handler to get flowers");
					data = myMsg.getObj();
				/*	try {
						App.setRoot("controllers/CatalogForRegisteredClients");
					}	catch (Exception e){
						System.out.println("get all execption");
						System.out.println(e);
					}*/
				}
				if (myMsg.getMsg().equals("all customers"))
				{
					System.out.println("in get customers client side");
					data = myMsg.getObj();
				}
<<<<<<< HEAD
				if (myMsg.getMsg().equals("your current customer"))
				{
					currentCustomerData = myMsg.getObj();
					System.out.println("in get your current customer");
					System.out.println(myMsg.getObj());
				}
		}
=======
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
			}
>>>>>>> main
			else{
				System.out.println("not done yet");
			}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
}
