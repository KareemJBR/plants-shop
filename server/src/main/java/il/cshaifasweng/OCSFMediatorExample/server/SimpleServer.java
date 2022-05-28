package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//my imports
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

public class SimpleServer extends AbstractServer {

    private static Session session;
    private static SessionFactory sessionFactory = getSessionFactory();

    private static List<CartItem> getAllCartIetms() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CartItem> query = builder.createQuery(CartItem.class);
        query.from(CartItem.class);
        List<CartItem> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Shop> getAllShops() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Shop> query = builder.createQuery(Shop.class);
        query.from(Shop.class);
        List<Shop> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<NetWorker> getAllNetWorkers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NetWorker> query = builder.createQuery(NetWorker.class);
        query.from(NetWorker.class);
        List<NetWorker> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Item> getAllItems() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        query.from(Item.class);
        List<Item> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Customer> getAllCustomers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        query.from(Customer.class);
        List<Customer> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<ShopAdmin> getAllShopAdmins() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ShopAdmin> query = builder.createQuery(ShopAdmin.class);
        query.from(ShopAdmin.class);
        List<ShopAdmin> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Report> getAllReports() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Report> query = builder.createQuery(Report.class);
        query.from(Report.class);
        List<Report> data = session.createQuery(query).getResultList();
        return data;
    }


    private static List<Order> getAllOrders() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        query.from(Order.class);
        List<Order> data = session.createQuery(query).getResultList();
        return data;
    }

    private static void generateShops() {
        /* ---------- Saving Shops To Data Base ---------- */
        Shop shop1 = new Shop("bad shop","Abba Houshi 199, Haifa","211406343");
        session.save(shop1);
        Shop shop2 = new Shop("good shop","Hanamal 500, Haifa","123456789");
        session.save(shop2);
        session.flush();
    }

    private static void generateItems() throws Exception {
        /* ---------- Saving Items To Data Base ---------- */
        Item item1 = new Item(30,"blue","Flower","https://www.ikea.cn/cn/en/images/products/smycka-artificial-flower-rose-red__0903311_pe596728_s5.jpg","item1");
        session.save(item1);
        Item item2 = new Item(25,"blue","FlowerBouquet","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__480.jpg","good item");
        session.save(item2);
        Item item3 = new Item(20,"red","EmptyFlowerPot","https://bulkquotesnow.com/wp-content/uploads/2021/08/The-Worlds-Most-Beautiful-and-Popular-Flowers.jpg","bad item");
        session.save(item3);
        session.flush();
        Item item4 = new Item(20,"yellow","EmptyFlowerPot","https://5.imimg.com/data5/KJ/MG/KC/SELLER-38773420/red-rose-flower-500x500.jpg","expensive");
        session.save(item4);
        session.flush();
    }

    private static void generateNetWorkers() {
        /* ---------- Saving Shops To Data Base ---------- */
        NetWorker worker1 = new NetWorker("211406343","kareem","jabareen","kareem_jb","kareem123");
        session.save(worker1);
        NetWorker worker2 = new NetWorker("206384919","mostafa","egbaria","mostafa_eg","mostafa123");
        session.save(worker2);
        session.flush();
    }

    private static void generateCustomers() {
        /* ---------- Saving Customers To Data Base ---------- */
        Customer customer1 = new Customer("123456789", "saeed", "mahameed", "saeed_mahamed20", "saeed123", "1234123412341234", "Network account","saeed@gmail.com");
        session.save(customer1);
        Customer customer2 = new Customer("208101458", "ons", "jijini", "ons_jijini", "ons123123", "0000111100001111", "Network account","ons@gmail.com");
        session.save(customer2);
        Customer customer3 = new Customer("206522435", "bayan", "swetat", "bayan123", "bayanswetat123", "0000000011111111", "Network account","bayan@gmail.com");
        session.save(customer3);
        session.flush();
        Customer customer4 = new Customer("12312333", "bayann", "swetatn", "1", "1", "0000000011111111", "Network account with 10% discount","bayann@gmail.com");
        session.save(customer4);
        Customer customer5 = new Customer("12332312", "sewy", "sew", "2", "2", "0000000011141111", "Network account","email@gmail.com");
        session.save(customer5);
        session.flush();
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        SimpleServer.session = session;
    }


    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Shop.class);
       configuration.addAnnotatedClass(NetWorker.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Report.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(CartItem.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(OrderItem.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void addDataToDB() {
        try {
            generateShops();
            generateNetWorkers();
            generateCustomers();
            generateItems();
            session.getTransaction().commit();
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        }
    }


    public SimpleServer(int port) {
        super(port);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        addDataToDB();
    }

    private Customer getCustomer(String userName) throws Exception {
        List<Customer> customers = getAllCustomers();
        Customer out=null;
        for (Customer customer : customers) {
            if(customer.getUser_name().equals(userName)){
                out=customer;
            }
        }
        return out;
    }

    private Shop findShop(String shopName) throws Exception {
        List<Shop> shops = getAllShops();
        Shop out=null;
        for (Shop s : shops) {
            if(s.getName().equals(shopName)){
                out=s;
            }
        }
        return out;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        String msgString = msg.toString();
        if (msg.getClass().equals(MsgClass.class)) {
            MsgClass myMsg = (MsgClass) msg;
            String msgtext = myMsg.getMsg();
            try {
                System.out.println(msgtext);

                if (msgtext.equals("#get Orders")) {
                    try {
                        MsgClass myMSg = new MsgClass("all orders");
                        myMSg.setObj(null);
                        myMSg.setObj(getAllOrders());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#customerUpdate")) {
                    try {
                        Customer temp = (Customer) (((MsgClass) msg).getObj());
                        update_customer(temp);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#update cartIrem")) {
                    try {
                        updateCartIrem((CartItem) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#customerDelete")) {
                    try {
                        Customer temp = (Customer) (((MsgClass) msg).getObj());
                        delete_customer(temp);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#get reports")) {
                    try {
                        MsgClass myMSg = new MsgClass("all reports");
                        myMSg.setObj(null);
                        myMSg.setObj(getAllReports());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#get shopAdmins")) {
                    try {
                        MsgClass myMSg = new MsgClass("all shopAdmins");
                        myMSg.setObj(null);
                        myMSg.setObj(getAllShopAdmins());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#get allItems")) {
                    try {
                        MsgClass myMSg = new MsgClass("allItems");
                        myMSg.setObj(null);
                        myMSg.setObj(getAllItems());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#get shop items")) {
                    try {
                        MsgClass myMSg = new MsgClass("all shop items");
                        myMSg.setObj(null);
                        myMSg.setObj(getAllItems());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("eror hapend");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#get customers")) {
                    try {
                        MsgClass myMSg = new MsgClass("all customers");
                        myMSg.setObj(getAllCustomers());
                        System.out.println("all customers");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened2");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#get Shops")) {
                    try {
                        MsgClass myMSg = new MsgClass("all Shops");
                        myMSg.setObj(getAllShops());
                        System.out.println("all Shops");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened3");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#get selected Shop")) {
                    try {
                        MsgClass myMSg = new MsgClass("selected shop");
                        myMSg.setObj(findShop((String) (((MsgClass) msg).getObj())));
                        System.out.println("all Shops");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened3");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#get NetWorkers")) {
                    try {
                        MsgClass myMSg = new MsgClass("all NetWorkers");
                        myMSg.setObj(getAllNetWorkers());
                        System.out.println("all NetWorkers");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened4");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#get orderItems")) {
                    try {
                        MsgClass myMSg = new MsgClass("all orderItems");
                        myMSg.setObj(getorderitems((Integer) ((MsgClass) msg).getObj()));
                        System.out.println("in get orderItems");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened in get orderItems");
                    }
                }
                if (msgtext.equals("#get CartItems")) {
                    try {
                        MsgClass myMSg = new MsgClass("all CartItems");
                        myMSg.setObj(getAllCartIetms());
                        System.out.println("all CartItems");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened6");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#add customer")) {
                    try {
                        System.out.println("in add customer");
                        AddCustomer((Customer) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened3");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#add order")) {
                    try {
                        System.out.println("in add order");
                        AddOrder((Order) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened10");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#add report")) {
                    try {
                        System.out.println("in add report");
                        AddReport((Report) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened5");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#add cartItem")) {
                    try {
                        System.out.println("in add cartItem");
                        AddCartItem((CartItem) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened12");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#add orderitem")) {
                    try {
                        System.out.println("in add orderitem");
                        AddOrderItem((OrderItem) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in add orderitem");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#delete Cart")) {
                    try {
                        System.out.println("in delete Cart");
                        deleteCart((String) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in delete Cart");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#get current customer")) {
                    try {
                        System.out.println("in get current customer");
                        String user = (String) myMsg.getObj();
                        System.out.println("the user is" + user);
                        System.out.println(getCustomer(user));
                        MsgClass myMSg = new MsgClass("your current customer", getCustomer(user));
                        client.sendToClient(myMSg);
                        System.out.println("customer sent");
                    } catch (Exception e) {
                        System.out.println("error happened4");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#delete Report")) {
                    try {
                        System.out.println("in delete report");
                        deleteReport((Report) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in delete report");
                        System.out.println(e.getMessage());
                    }
                }

                if (msgtext.equals("#delete CartItem")) {
                    try {
                        System.out.println("in delete CartItem");
                        deleteCartitem((Integer) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in delete CartItem");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#decrement amount")) {
                    try {
                        System.out.println("in decrement amount");
                        decrementAmountofCartItem((Integer) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in decrement amount");
                    }

                }
                if (msgString.startsWith("#close")) {
                    session.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (msgString.startsWith("#close")) {
            session.close();
        }
    }




    private static void AddCustomer(Customer p) {
        session.clear();
        session.beginTransaction();
        session.save(p);
        session.flush();
        session.getTransaction().commit();
    }

    private static void AddOrder(Order o) {
        session.clear();
        session.beginTransaction();
        session.save(o);
        session.flush();
        session.getTransaction().commit();
    }

    private static void AddReport(Report R) {
        session.beginTransaction();
        session.clear();
        session.save(R);
        session.flush();
        session.getTransaction().commit();

    }
    private static void AddCartItem(CartItem I) {
        session.beginTransaction();
        session.clear();
        session.save(I);
        session.flush();
        session.getTransaction().commit();

    }

    private static void AddOrderItem(OrderItem I) {
        session.beginTransaction();
        session.clear();
        session.save(I);
        session.flush();
        session.getTransaction().commit();

    }

    public static void deleteCart(String clientId) throws Exception {
        ArrayList<CartItem> cartItems= (ArrayList<CartItem>) getAllCartIetms();
        session.beginTransaction();
        if(cartItems!=null)
        {
            if(cartItems.size()!=0)
            {
                for(int i=0;i<cartItems.size();i++)
                {
                    if(cartItems.get(i).getCustomer().getUser_id().equals(clientId))
                    {
                        session.delete(cartItems.get(i));
                    }
                }
            }
        }
        session.flush();
        session.getTransaction().commit();
    }

    public static void deleteCartitem(int cartitemId) throws Exception {
        ArrayList<CartItem> cartItems= (ArrayList<CartItem>) getAllCartIetms();
        session.beginTransaction();
        if(cartItems!=null)
        {
            if(cartItems.size()!=0)
            {
                for(int i=0;i<cartItems.size();i++)
                {
                    if(cartItems.get(i).getId()==cartitemId)
                    {
                        session.delete(cartItems.get(i));
                        break;
                    }
                }
            }
        }
        session.flush();
        session.getTransaction().commit();
    }

    public static void decrementAmountofCartItem(int cartitemId) throws Exception {
        ArrayList<CartItem> cartItems= (ArrayList<CartItem>) getAllCartIetms();
        session.beginTransaction();
        if(cartItems!=null)
        {
            if(cartItems.size()!=0)
            {
                for(int i=0;i<cartItems.size();i++)
                {
                    if(cartItems.get(i).getId()==cartitemId)
                    {
                        int amount= cartItems.get(i).getAmount();
                        if(amount==1)
                        {
                            session.delete(cartItems.get(i));
                        }
                        else
                        {
                            cartItems.get(i).setAmount(amount-1);
                            session.update(cartItems.get(i));
                        }
                        break;
                    }
                }
            }
        }
        session.flush();
        session.getTransaction().commit();

    }

    private static void update_customer(Customer customer) {
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
    }

    private static void updateCartIrem(CartItem item) {
        session.beginTransaction();
        session.clear();
        session.update(item);
        session.getTransaction().commit();
    }


    private static void delete_customer(Customer customer) {
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
    }
    private static void deleteReport(Report report) {
        session.beginTransaction();
        session.clear();
        session.delete(report);
        session.getTransaction().commit();
    }

        @Transactional
        private static List<OrderItem> getorderitems(int orderId) throws Exception {
//        session.beginTransaction();
        ArrayList<Order> orders= (ArrayList<Order>) getAllOrders();
        if(orders!=null)
        {
            if(orders.size()!=0)
            {
                for(int i=0;i<orders.size();i++)
                {
                    if(orders.get(i).getId()==orderId)
                    {
                         //System.out.println(orders.get(i).getOrderitems().get(0).getAmount());
                        System.out.println(orders.get(i).getOrderitems().size());
                         return  orders.get(i).getOrderitems();
                    }
                }
            }
        }
        return null;
    }


}
