package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

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
