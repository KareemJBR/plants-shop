package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

public class SimpleClient extends AbstractClient {

    private static SimpleClient client = null;
    public static  Object data;
    public static  Object currentCustomerData;
    public static  Object shopsdata;
    public static  Object NetWorkersData;

    public static  Object CartItemsdata;
    public static  Object Itemsdata;
    public static  Object allItemsData;
    public static  Object Customersdata;
    public static  Object OrderItemData;
    public static  Object SupportWorkersData;


    public static Object ReportsData;
    public static Object ShopAdminsData;
    public static Object OrdersData;
    public static Object selectedShopData;
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
                allItemsData = myMsg.getObj();
				/*	try {
						App.setRoot("controllers/CatalogForRegisteredClients");
					}	catch (Exception e){
						System.out.println("get all execption");
						System.out.println(e);
					}*/

			}
			if (myMsg.getMsg().equals("your current customer")) {
				currentCustomerData = myMsg.getObj();
				System.out.println("in get your current customer");
				System.out.println(myMsg.getObj());
				}
            if (myMsg.getMsg().equals("all orderItems")) {
                OrderItemData = myMsg.getObj();
                System.out.println("in get orderItems");
            }
            if (myMsg.getMsg().equals("all shop items that under sale")) {
                System.out.println("in main client handler to get all items");
                allItemsData = myMsg.getObj();
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
            if(myMsg.getMsg().equals("all NetWorkers"))
            {
                System.out.println("in get NetWorkers client side");
                NetWorkersData = myMsg.getObj();
            }
            if(myMsg.getMsg().equals("all SupportWorkers"))
            {
                System.out.println("in get SupportWorkers client side");
                SupportWorkersData = myMsg.getObj();
            }
            else{
                System.out.println("not done yet");

            }
        }
        assert myMsg != null;
        if (myMsg.getMsg().equals("all orders")) {
            System.out.println("in get Orders client side");
            OrdersData = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("all Shops")) {
            System.out.println("in get Shops client side");
            shopsdata = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("all NetWorkers")) {
            System.out.println("in get NetWorkers client side");
            NetWorkersData = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("all SupportWorkers")) {
            System.out.println("in get SupportWorkers client side");
            SupportWorkersData = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("all CartItems")) {
            System.out.println("in get CartItems client side");
            CartItemsdata = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("allItems")) {
            System.out.println("in get Items client side");
            Itemsdata = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("all shopAdmins")){
            System.out.println("in get shopAdmins client side");
            ShopAdminsData = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("all reports")){
            System.out.println("in get reports client side");
            ReportsData = myMsg.getObj();
        }
        if (myMsg.getMsg().equals("selected shop")){
            System.out.println("in get selected shop client side");
            selectedShopData = myMsg.getObj();
        }
    }

    public static SimpleClient getClient() {
        if (client == null) {
            client = new SimpleClient("localhost", 3000);
        }
        return client;
    }
}
