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


	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
			if (msg.getClass().equals(MsgClass.class)) {
				MsgClass myMsg = (MsgClass) msg;
				if (myMsg.getMsg().equals("all flowers")) {
					data = myMsg.getObj();
					try {
						App.setRoot("primary");
					}
					catch (Exception e){
						System.out.println("get all execption");
						System.out.println(e);
					}
				}
				if (myMsg.getMsg().equals("all customers"))
				{
					data = myMsg.getObj();
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
