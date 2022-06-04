package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.util.ArrayList;
import java.util.List;

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

    private static List<SupportWorker> getAllSupportWorkers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SupportWorker> query = builder.createQuery(SupportWorker.class);
        query.from(SupportWorker.class);
        List<SupportWorker> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Item> getAllItems() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        query.from(Item.class);
        List<Item> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Item> getItemsUnderSale() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        query.from(Item.class);
        List<Item> temp = session.createQuery(query).getResultList();
        List<Item> data =new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).isUnderSale()){
                data.add(temp.get(i));
            }
        }
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

    private static void generateShopsData() {

        /* ---------- Saving Items To Data Base ---------- */
        Item item1 = new Item("red",true,0.3,30,"flower",
                "https://www.ikea.cn/cn/en/images/products/smycka-artificial-flower-rose-red__0903311_pe596728_s5.jpg","beautiful flower");//(30,"blue","Flower","https://www.ikea.cn/cn/en/images/products/smycka-artificial-flower-rose-red__0903311_pe596728_s5.jpg","item1");
        session.save(item1);
        session.flush();

        Item item2 = new Item("blue",false,0,30,"FlowerBouquet",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__480.jpg","good item");//(25,"blue","FlowerBouquet","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__480.jpg","good item");
        session.save(item2);
        session.flush();

        Item item3 = new Item("red",false,0,30,"FlowerBouquet",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__480.jpg","item");
        session.save(item3);
        session.flush();

        Item item4 = new Item("yellow",true,0.50,30,"FlowerBouquet",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__480.jpg","bad item");
        session.save(item4);
        session.flush();

        Item item5 = new Item("pink", false, 0, 50, "FlowerBouquet", "https://images.pexels.com/photos/736230/pexels-photo-736230.jpeg?cs=srgb&dl=pexels-jonas-kakaroto-736230.jpg&fm=jpg",
                "pink flower");
        session.save(item5);
        session.flush();

        Item item6 = new Item("purple", false, 0, 80, "FlowerBouquet", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/surprising-flower-meanings-balloon-flowers-1650767465.jpg?crop=1xw:1xh;center,top&resize=480:*",
                "purple flower");
        session.save(item6);
        session.flush();

        /* ---------- Saving Shops To Data Base ---------- */
        Shop shop1 = new Shop("bad shop","Abba Houshi 199, Haifa","212955587");
        session.save(shop1);
        Shop shop2 = new Shop("good shop","Hanamal 500, Haifa","888333755");
        session.save(shop2);
        session.flush();

        // adding admins to the shops
        ShopAdmin admin1 = new ShopAdmin("mostafa1234", "mostafa1234",
                "212955587", "mostafa", "jabareen");
        session.save(admin1);

        ShopAdmin admin2 = new ShopAdmin("mortada", "mortada",
                "888333755", "mortada", "mahameed");
        session.save(admin2);
        session.flush();

        // adding customers
        Customer customer1 = new Customer("123456789", "saeed", "mahameed",
                "saeed_mahamed20", "saeed123", "1234123412341234",
                "Network account","saeed@gmail.com");

        session.save(customer1);

        Customer customer2 = new Customer("208101458", "ons", "jijini",
                "ons_jijini", "ons123123", "0000111100001111",
                "Network account","ons@gmail.com");

        session.save(customer2);

        Customer customer3 = new Customer("206522435", "bayan", "swetat",
                "bayan123", "bayanswetat123", "0000000011111111",
                "Network account","bayan@gmail.com");

        session.save(customer3);
        session.flush();

        Customer customer4 = new Customer("12312333", "bayann", "swetatn",
                "1", "1", "0000000011111111", "Network account with 10% discount",
                "bayann@gmail.com");

        session.save(customer4);
        session.flush();

        Customer customer5 = new Customer("12332312", "sewy", "sew", "2",
                "2", "0000000011141111", "Network account","email@gmail.com");

        session.save(customer5);
        session.flush();

        // adding orders
        double total_price = 0.0;

        List<OrderItem> order_items1 = new ArrayList<>();

        OrderItem temp1 = new OrderItem(item1, customer1, 2);
        total_price += 2.0 * item1.getPrice();

        OrderItem temp2 = new OrderItem(item2, customer1, 1);
        total_price += item2.getPrice();

        OrderItem temp3 = new OrderItem(item3, customer1, 1);
        total_price += item3.getPrice();

        order_items1.add(temp1);
        order_items1.add(temp2);
        order_items1.add(temp3);

        Order order1 = new Order(shop1, customer1, 2022, 4, 4, 2022,
                4, 20, 15, 0, 20, 0, total_price,
                "Cash", "Delivery", "May you have a happy birthday!\n\nFrom Saeed",
                true, "Abba Houshi 30, Haifa", false, 0.0);

        order1.setOrderitems(order_items1);
        total_price = 0.0;
        session.save(order1);
        session.flush();

        List<OrderItem> order_items3 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer3, 2);
        total_price += 2.0 * item1.getPrice();

        temp2 = new OrderItem(item2, customer3, 1);
        total_price += item2.getPrice();

        temp3 = new OrderItem(item3, customer3, 1);
        total_price += item3.getPrice();

        OrderItem temp4 = new OrderItem(item4, customer3, 1);
        total_price += item4.getPrice();

        order_items3.add(temp1);
        order_items3.add(temp2);
        order_items3.add(temp3);
        order_items3.add(temp4);

        Order order3 = new Order(shop1, customer3, 2022, 4, 1, 2022,
                4, 10, 20, 14, 7, 30, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order3.setOrderitems(order_items3);
        total_price = 0.0;
        session.save(order3);
        session.flush();

        List<OrderItem> order_items4 = new ArrayList<>();

        temp2 = new OrderItem(item2, customer4, 2);
        total_price += 2.0 * item2.getPrice();

        temp3 = new OrderItem(item3, customer4, 3);
        total_price += 3.0 * item3.getPrice();

        order_items4.add(temp2);
        order_items4.add(temp3);

        Order order4 = new Order(shop1, customer4, 2022, 4, 23, 2022,
                5, 1, 8, 14, 9, 30, total_price,
                "CreditCard", "Delivery", "", true,
                "Al-Quds 1, Umm Al-Fahem", false, 0.0);

        order4.setOrderitems(order_items4);
        total_price = 0.0;
        session.save(order4);
        session.flush();

        List<OrderItem> order_items5 = new ArrayList<>();

        temp2 = new OrderItem(item2, customer5, 4);
        total_price += 4.0 * item2.getPrice();

        order_items5.add(temp2);

        Order order5 = new Order(shop1, customer5, 2022, 4, 25, 2022,
                4, 30, 16, 13, 12, 30, total_price,
                "CreditCard", "Delivery", "May you have a happy birthday!\n\nFrom Sewy",
                true, "Hanamal 1, Haifa", false, 0.0);

        order5.setOrderitems(order_items5);
        total_price = 0.0;
        session.save(order5);
        session.flush();

        List<OrderItem> order_items6 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer1, 2);
        total_price += 2.0 * item1.getPrice();

        temp2 = new OrderItem(item2, customer1, 2);
        total_price += 2.0 * item2.getPrice();

        temp3 = new OrderItem(item3, customer1, 2);
        total_price += 2.0 * item3.getPrice();

        temp4 = new OrderItem(item4, customer1, 2);
        total_price += 2.0 * item4.getPrice();

        order_items6.add(temp1);
        order_items6.add(temp2);
        order_items6.add(temp3);
        order_items6.add(temp4);

        Order order6 = new Order(shop2, customer1, 2022, 4, 26, 2022,
                5, 15, 12, 11, 13, 0, total_price,
                "CreditCard", "Delivery", "",
                true, "Moriyya 102, Haifa", false, 0.0);

        order6.setOrderitems(order_items6);
        total_price = 0.0;
        session.save(order6);
        session.flush();

        List<OrderItem> order_items7 = new ArrayList<>();

        temp2 = new OrderItem(item2, customer2, 3);
        total_price += 3.0 * item2.getPrice();

        temp4 = new OrderItem(item4, customer2, 1);
        total_price += item4.getPrice();

        order_items7.add(temp2);
        order_items7.add(temp4);

        Order order7 = new Order(shop2, customer2, 2022, 5, 14, 2022,
                5, 30, 23, 54, 17, 0, total_price,
                "CreditCard", "Delivery", "",
                true, "Moriyya 121, Haifa", false, 0.0);

        order7.setOrderitems(order_items7);
        total_price = 0.0;
        session.save(order7);
        session.flush();

        List<OrderItem> order_items9 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer4, 4);
        total_price += 4.0 * item1.getPrice();

        temp2 = new OrderItem(item2, customer4, 4);
        total_price += 4.0 * item2.getPrice();

        temp3 = new OrderItem(item3, customer4, 4);
        total_price += 4.0 * item3.getPrice();

        temp4 = new OrderItem(item4, customer4, 4);
        total_price += 4.0 * item4.getPrice();

        order_items9.add(temp1);
        order_items9.add(temp2);
        order_items9.add(temp3);
        order_items9.add(temp4);

        Order order9 = new Order(shop2, customer4, 2022, 5, 30, 2022,
                6, 12, 16, 13, 7, 30, total_price,
                "CreditCard", "Delivery", "",
                true, "Hanamal 51, Haifa", false, 0.0);

        order9.setOrderitems(order_items9);
        total_price = 0.0;
        session.save(order9);
        session.flush();

        List<OrderItem> order_items10 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer5, 6);
        total_price += 6.0 * item1.getPrice();

        temp3 = new OrderItem(item3, customer5, 3);
        total_price += 3.0 * item3.getPrice();

        temp4 = new OrderItem(item4, customer5, 1);
        total_price += item4.getPrice();

        order_items10.add(temp1);
        order_items10.add(temp3);
        order_items10.add(temp4);

        Order order10 = new Order(shop2, customer5, 2022, 6, 4, 2022,
                6, 20, 11, 57, 12, 0, total_price,
                "CreditCard", "Delivery", "",
                true, "Hanamal 33, Haifa", false, 0.0);

        order10.setOrderitems(order_items10);
        total_price = 0.0;
        session.save(order10);
        session.flush();

        List<OrderItem> order_items11 = new ArrayList<>();

        temp1 = new OrderItem(item5, customer5, 5);
        total_price += 5.0 * item5.getPrice();

        temp2 = new OrderItem(item6, customer5, 2);
        total_price += 2.0 * item6.getPrice();

        temp3 = new OrderItem(item3, customer5, 4);
        total_price += 4.0 * item3.getPrice();

        temp4 = new OrderItem(item4, customer5, 4);
        total_price += 4.0 * item4.getPrice();

        order_items11.add(temp1);
        order_items11.add(temp2);
        order_items11.add(temp3);
        order_items11.add(temp4);

        Order order11 = new Order(shop1, customer5, 2022, 6, 4, 2022,
                6, 14, 22, 12, 10, 0, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order11.setOrderitems(order_items11);
        session.save(order11);
        session.flush();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<OrderItem> order_items12 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer2, 1);
        temp2 = new OrderItem(item2, customer2, 1);
        temp3 = new OrderItem(item3, customer2, 1);
        temp4 = new OrderItem(item4, customer2, 1);
        OrderItem temp5 = new OrderItem(item5, customer2, 1);
        OrderItem temp6 = new OrderItem(item6, customer2, 1);
        total_price = item1.getPrice() + item2.getPrice() + item3.getPrice() + item4.getPrice() + item5.getPrice() +
                item6.getPrice();

        order_items12.add(temp1);
        order_items12.add(temp2);
        order_items12.add(temp3);
        order_items12.add(temp4);
        order_items12.add(temp5);
        order_items12.add(temp6);

        Order order12 = new Order(shop2, customer2, 2022, 5, 22, 2022,
                6, 1, 22, 12, 10, 0, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order12.setOrderitems(order_items12);
        session.save(order12);
        session.flush();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<OrderItem> order_items13 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer3, 11);
        total_price = 11.0 * item1.getPrice();

        order_items13.add(temp1);

        Order order13 = new Order(shop1, customer2, 2022, 4, 30, 2022,
                5, 12, 22, 12, 15, 0, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order13.setOrderitems(order_items13);
        total_price = 0.0;
        session.save(order13);
        session.flush();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<OrderItem> order_items14 = new ArrayList<>();

        temp1 = new OrderItem(item5, customer4, 1);
        total_price += item5.getPrice();

        temp2 = new OrderItem(item6, customer4, 12);
        total_price += 12.0 * item6.getPrice();

        temp3 = new OrderItem(item3, customer4, 2);
        total_price += 2.0 * item3.getPrice();

        temp4 = new OrderItem(item4, customer4, 1);
        total_price += item4.getPrice();

        temp5 = new OrderItem(item2, customer4, 1);
        total_price += item2.getPrice();

        order_items14.add(temp1);
        order_items14.add(temp2);
        order_items14.add(temp3);
        order_items14.add(temp4);
        order_items14.add(temp5);

        Order order14 = new Order(shop2, customer4, 2022, 5, 13, 2022,
                5, 30, 12, 53, 18, 0, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order14.setOrderitems(order_items14);
        total_price = 0.0;
        session.save(order14);
        session.flush();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<OrderItem> order_items15 = new ArrayList<>();

        temp1 = new OrderItem(item1, customer2, 2);
        total_price += 2.0 * item1.getPrice();

        temp2 = new OrderItem(item6, customer2, 3);
        total_price += 3.0 * item6.getPrice();

        order_items15.add(temp1);
        order_items15.add(temp2);

        Order order15 = new Order(shop2, customer2, 2022, 6, 1, 2022,
                6, 7, 21, 44, 14, 30, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order15.setOrderitems(order_items15);
        total_price = 0.0;
        session.save(order15);
        session.flush();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<OrderItem> order_items16 = new ArrayList<>();

        temp1 = new OrderItem(item6, customer4, 9);
        total_price += 9.0 * item6.getPrice();

        order_items11.add(temp1);

        Order order16 = new Order(shop1, customer4, 2022, 4, 16, 2022,
                4, 20, 22, 12, 10, 0, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order16.setOrderitems(order_items16);
        total_price = 0.0;
        session.save(order16);
        session.flush();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<OrderItem> order_items17 = new ArrayList<>();

        temp1 = new OrderItem(item5, customer5, 5);
        total_price += 5.0 * item5.getPrice();

        temp2 = new OrderItem(item6, customer5, 2);
        total_price += 2.0 * item6.getPrice();

        order_items11.add(temp1);
        order_items11.add(temp2);

        Order order17 = new Order(shop1, customer2, 2022, 4, 18, 2022,
                5, 1, 21, 23, 13, 30, total_price,
                "CreditCard", "Pickup", "", false, "",
                false, 0.0);

        order17.setOrderitems(order_items17);
        session.save(order17);
        session.flush();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // add reports

        Report report1 = new Report("The shop is closed before time.", true,
                false, "Problem solved. Thanks for contacting us!", "30/5/2022",
                0.0, "2836582283", customer1, shop1);

        session.save(report1);
        session.flush();

        Report report2 = new Report("The shop is messy.", true,
                false, "Problem solved. Thanks for contacting us!", "31/5/2022",
                0.0, "2836582283", customer2, shop1);

        session.save(report2);
        session.flush();

        Report report3 = new Report("The delivery was 20 minutes late.", true,
                false, "We are sorry to hear that, there was a traffic jam.", "16/5/2022",
                0.0, "284449200", customer4, shop2);

        session.save(report3);
        session.flush();

        Report report4 = new Report("The shop is closed before time.", true,
                false, "Problem solved. Thanks for contacting us!", "20/5/2022",
                0.0, "284449200", customer5, shop2);

        session.save(report4);
        session.flush();

        Report report5 = new Report("The shop is closed before time.", true,
                false, "Problem solved. Thanks for contacting us!", "1/6/2022",
                0.0, "284449200", customer1, shop1);

        session.save(report5);
        session.flush();

        Report report6 = new Report("The shop is closed before time.", true,
                false, "Problem solved. Thanks for contacting us!", "3/6/2022",
                0.0, "2836582283", customer2, shop1);

        session.save(report6);
        session.flush();

        Report report7 = new Report("The shop is closed before time.", true,
                false, "Problem solved. Thanks for contacting us!", "3/6/2022",
                0.0, "284449200", customer3, shop2);

        session.save(report7);
        session.flush();

        Report report8 = new Report("Bad service.", true,
                false, "You have got a refund.", "3/6/2022",
                0.0, "2836582283", customer5, shop2);

        session.save(report8);
        session.flush();

        Report report9 = new Report("Please fix the software bugs.", true,
                false, "Done!", "26/4/2022",
                0.0, "284449200", customer1, shop1);

        session.save(report9);
        session.flush();

        Report report10 = new Report("Terrible service.", true,
                false, "You have got a refund.", "2/5/2022",
                0.0, "2836582283", customer5, shop2);

        session.save(report10);
        session.flush();

        Report report11 = new Report("Bad service.", true,
                false, "You have got a refund.", "8/5/2022",
                0.0, "2836582283", customer2, shop2);

        session.save(report11);
        session.flush();

        Report report12 = new Report("Bad service.", true,
                false, "You have got a refund.", "7/6/2022",
                0.0, "284449200", customer3, shop2);

        session.save(report12);
        session.flush();

        Report report13 = new Report("Bad service.", true,
                false, "Problem solved.", "28/5/2022",
                0.0, "284449200", customer4, shop1);

        session.save(report13);
        session.flush();

        Report report14 = new Report("Bad service.", true,
                false, "You have got a refund.", "8/6/2022",
                0.0, "2836582283", customer5, shop2);

        session.save(report14);
        session.flush();

    }

    private static void generateNetWorkers() {
        /* ---------- Saving Shops To Data Base ---------- */
        NetWorker worker1 = new NetWorker("211406343","kareem","jabareen",
                "net1","net1");

        session.save(worker1);
        NetWorker worker2 = new NetWorker("206384919","mostafa","egbaria",
                "net2","net2");

        session.save(worker2);
        session.flush();
        NetWorker worker3 = new NetWorker("212186613","sameer","najjar","net3",
                "net3");

        session.save(worker3);
        session.flush();
    }

    private static void generateSupportWorkers() {
        SupportWorker worker1 = new SupportWorker("2836582283", "ahmad",
                "jabareen", "support1", "support1");
        session.save(worker1);
        SupportWorker worker2 = new SupportWorker("284449200", "mohammad", "mahameed",
                "support2", "support2");
        session.save(worker2);
        session.flush();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Shop.class);
        configuration.addAnnotatedClass(ShopAdmin.class);
        configuration.addAnnotatedClass(NetWorker.class);
        configuration.addAnnotatedClass(SupportWorker.class);
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
            generateShopsData();
            generateNetWorkers();
            generateSupportWorkers();
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

    private static void updateItem(Item item) {
        session.beginTransaction();
        session.clear();
        session.update(item);
        session.getTransaction().commit();
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

                if (msgtext.equals("#update Item")) {
                    try {
                         updateItem((Item)((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error occurred");
                    }
                }

                if (msgtext.equals("#cancel order")) {
                    try {
                        System.out.println("in add cartItem");
                        Object ob=((MsgClass) msg).getObj();
                        ArrayList<Double> ob1= (ArrayList<Double>) ob;
                        cancelOrder(ob1.get(0),ob1.get(1));
                    } catch (Exception e) {
                        System.out.println("error happened in cancel order");

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

                if (msgtext.equals("#update report")) {
                    try {
                        UpdateReport((Report) ((MsgClass) msg).getObj());
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

                if (msgtext.equals("#reload for all clients")) {
                    try {
                        MsgClass myMSg = new MsgClass("reload");
                        myMSg.setObj(null);
                        sendToAllClients(myMSg);
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
                        MsgClass myMSg = new MsgClass("all shop items");
                        myMSg.setObj(getAllItems());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error occurred");
                        System.out.println(e.getMessage());
                    }
                }
                if (msgtext.equals("#get shop items that under sale")) {
                    try {
                        MsgClass myMSg = new MsgClass("all shop items that under sale");
                        myMSg.setObj(getItemsUnderSale());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("eror hapend");
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
                if (msgtext.equals("#get SupportWorkers")) {
                    try {
                        MsgClass myMSg = new MsgClass("all SupportWorkers");
                        myMSg.setObj(getAllSupportWorkers());
                        System.out.println("all SupportWorkers");
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
                if (msgtext.equals("#increment amount")) {
                    try {
                        System.out.println("in increment amount");
                        incrementAmountofCartItem((Integer) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in increment amount");
                    }

                }
                if(msgtext.equals("#add new Item")){
                    try {
                        System.out.println("in add item");
                        AddItem((Item) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened in add orderitem");
                        System.out.println(e.getMessage());
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
    private static void AddItem(Item i) {
        session.beginTransaction();
        session.clear();
        session.save(i);
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

    public static void incrementAmountofCartItem(int cartitemId) throws Exception {
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
                            cartItems.get(i).setAmount(amount+1);
                            session.update(cartItems.get(i));
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
        session.clear();
        session.update(customer);
        session.getTransaction().commit();
    }

    private static void updateCartIrem(CartItem item) {
        session.beginTransaction();
        session.clear();
        session.update(item);
        session.getTransaction().commit();
    }

    private static void UpdateReport(Report report) {
        session.beginTransaction();
        session.clear();
        session.update(report);
        session.getTransaction().commit();
    }

    private static void delete_customer(Customer customer) {
        session.beginTransaction();
        session.clear();
        session.delete(session.get(Customer.class, customer.getId()));
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
        ArrayList<Order> orders= (ArrayList<Order>) getAllOrders();
        if(orders!=null)
        {
            if(orders.size()!=0)
            {
                for(int i=0;i<orders.size();i++)
                {
                    if(orders.get(i).getId()==orderId)
                    {
                         return  orders.get(i).getOrderitems();
                    }
                }
            }
        }
        return null;
    }

    public static void cancelOrder(Double id, double refund) throws Exception {
       ArrayList<Order> orders= (ArrayList<Order>) getAllOrders();
        session.beginTransaction();
        session.clear();
       if(orders!=null)
        {
            if(orders.size()!=0)
            {
                for(int i=0;i<orders.size();i++)
                {
                    if(orders.get(i).getId()==id)
                    {
                        orders.get(i).cancel_order();
                        orders.get(i).setRefund(refund);
                        customerRefund(orders.get(i).getCustomer().getId(),refund);
                        session.update(orders.get(i));
                    }
                }
            }
        }
        session.getTransaction().commit();
    }

    public static void customerRefund(String id, double refund) throws Exception {
        ArrayList<Customer> customers= (ArrayList<Customer>) getAllCustomers();
        if(customers!=null)
        {
            if(customers.size()!=0)
            {
                for(int i=0;i<customers.size();i++)
                {
                    if(customers.get(i).getId().equals(id))
                    {
                        customers.get(i).deposit(refund);
                        session.update(customers.get(i));
                    }
                }
            }
        }
    }
    @Override
    public void sendToAllClients(Object msg)
    {
        Thread[] clientThreadList = getClientConnections();

        for (int i=0; i<clientThreadList.length; i++)
        {
            try
            {
                ((ConnectionToClient)clientThreadList[i]).sendToClient(msg);
            }
            catch (Exception ex) {}
        }
    }

}
