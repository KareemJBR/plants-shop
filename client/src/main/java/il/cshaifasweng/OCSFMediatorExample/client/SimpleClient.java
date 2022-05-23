package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
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


	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
			if (msg.getClass().equals(MsgClass.class)) {
				MsgClass myMsg = (MsgClass) msg;
				if (myMsg.getMsg().equals("all flowers")) {
					System.out.println("in main client handler to get flowers  ");
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
				if (myMsg.getMsg().equals("your current customer"))
				{
					currentCustomerData = myMsg.getObj();
					System.out.println("in get your current customer");
					System.out.println(myMsg.getObj());
				}
		}
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
