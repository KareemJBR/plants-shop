package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

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

public class SimpleServer extends AbstractServer {

    private static Session session;
    private static SessionFactory sessionFactory = getSessionFactory();
    private static List<Shop> getAllShops() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Shop> query = builder.createQuery(Shop.class);
        query.from(Shop.class);
        List<Shop> data = session.createQuery(query).getResultList();
        return data;
    }
  
    private static List<Flower> getAllFlowers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flower> query = builder.createQuery(Flower.class);
        query.from(Flower.class);
        List<Flower> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Worker> getAllWorkers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
        query.from(Worker.class);
        List<Worker> data = session.createQuery(query).getResultList();
        return data;
    }

    private static List<Customer> getAllCustomers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        query.from(Customer.class);
        List<Customer> data = session.createQuery(query).getResultList();
        return data;
    }

    private static void generateShops() {
        /* ---------- Saving Shops To Data Base ---------- */
        Shop shop1 = new Shop("Abba Houshi 199, Haifa",211406343);
        session.save(shop1);
        Shop shop2 = new Shop("Hanamal 500, Haifa",123456789);
        session.save(shop2);
        session.flush();
    }

    private static void generateWorkers() {
        /* ---------- Saving Shops To Data Base ---------- */
        Worker worker1 = new Worker(211406343,"kareem","jabareen","kareem_jb","kareem123");
        session.save(worker1);
        Worker worker2 = new Worker(206384919,"mostafa","egbaria","mostafa_eg","mostafa123");
        session.save(worker2);
        session.flush();
    }

    private static void generateCustomers() {
        /* ---------- Saving Customers To Data Base ---------- */
        Customer customer1 = new Customer("123456789", "saeed", "mahameed", "saeed_mahamed20", "saeed123", "1234123412341234", "network_account");
        session.save(customer1);
        Customer customer2 = new Customer("208101458", "ons", "jijini", "ons_jijini", "ons123123", "0000111100001111", "network_account");
        session.save(customer2);
        Customer customer3 = new Customer("206522435", "bayan", "swetat", "bayan123", "bayanswetat123", "0000000011111111", "network_account");
        session.save(customer3);
        session.flush();
        Customer customer4 = new Customer("12312333", "bayann", "swetatn", "1", "1", "0000000011111111", "network_account");
        session.save(customer4);
        session.flush();
<<<<<<< HEAD
        Customer customer5 = new Customer("12332312", "sewy", "sew", "2", "2", "0000000011141111", "network_account");
        session.save(customer5);
        session.flush();
=======
>>>>>>> main
    }

    private static void generateFlowers() {
        Flower flower1 = new Flower(50, "red", "https://images.unsplash.com/photo-1569101315919-dafea4df33ff?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cmVkJTIwZmxvd2VyfGVufDB8fDB8fA%3D%3D&w=1000&q=80");
        session.save(flower1);
        session.flush();
        Flower flower2 = new Flower(45, "green", "https://www.allaboutgardening.com/wp-content/uploads/2021/10/Carnation.jpg");
        session.save(flower2);
        session.flush();
        Flower flower3 = new Flower(30, "blue", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzjZZ9dDZzh6zb3fbq8g4MpK8ybBNNQ9TzEg&usqp=CAU");
        session.save(flower3);
        session.flush();
        Flower flower4 = new Flower(55, "purple", "https://upload.wikimedia.org/wikipedia/commons/9/9c/Purple_Flower_%22Pensamiento%22_Viola_%C3%97_wittrockiana.JPG");
        session.save(flower4);
        session.flush();
        Flower flower5 = new Flower(20, "yellow", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/yellow-flower-dahlia-1587061007.jpg?crop=0.557xw:1.00xh;0.0569xw,0&resize=480:*");
        session.save(flower5);
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
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Flower.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Report.class);
        configuration.addAnnotatedClass(FlowerPotWithFlower.class);
        configuration.addAnnotatedClass(FlowerBouquet.class);
        configuration.addAnnotatedClass(EmptyFlowerPot.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void addDataToDB() {
        try {
            generateShops();
            generateWorkers();
            generateFlowers();
            generateCustomers();
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

    private static void updatePrice(Flower flower, int price) {
        System.out.println(price);
        System.out.println(flower);
        session.beginTransaction();
        flower.setPrice(price);
        session.update(flower);
        System.out.println(flower);
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

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        String msgString = msg.toString();
        if (msg.getClass().equals(MsgClass.class)) {
            MsgClass myMsg = (MsgClass) msg;
            String msgtext = myMsg.getMsg();
            try {
                System.out.println(msgtext);
                if (msgtext.equals("#get shop items")) {
                    try {
                        MsgClass myMSg = new MsgClass("all flowers");
                        myMSg.setObj(null);
                        myMSg.setObj(getAllFlowers());
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("eror hapend");
                        System.out.println(e);
                    }
                }
                if (msgtext.startsWith("#update")) {
                    try {
                        System.out.println("in update");
                        int id = Integer.parseInt(String.valueOf(msgtext.charAt(9)));
                        int price = Integer.parseInt(msgtext.substring(11));
                        updatePrice(session.get(Flower.class, id), price);
                        myMsg.setObj(getAllFlowers());
                        myMsg.setMsg("all flowers");
                        client.sendToClient(myMsg);
                    } catch (Exception e) {
                        System.out.println("in updete exception");
                        System.out.println(e);
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
                        System.out.println(e);
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
                        System.out.println(e);
                    }
                }
                if (msgtext.equals("#get Workers")) {
                    try {
                        MsgClass myMSg = new MsgClass("all Workers");
//                        List<Worker> y=getAllWorkers();
//                        System.out.println(y.size());
                        myMSg.setObj(getAllWorkers());
                        System.out.println("all Workers");
                        client.sendToClient(myMSg);
                    } catch (Exception e) {
                        System.out.println("error happened4");
                        System.out.println(e);
                    }
                }
                if (msgtext.equals("#add customer")) {
                    try {
                        System.out.println("in add customer");
                        AddCustomer((Customer) ((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened3");
                        System.out.println(e);
                    }
                }
                if (msgtext.equals("#add report")) {
                    try {
                        System.out.println("in add report");
                        AddReport((report)((MsgClass) msg).getObj());
                    } catch (Exception e) {
                        System.out.println("error happened5");
                        System.out.println(e);
                    }
                }
                if (msgtext.equals("#get current customer")) {
                    try {
                        System.out.println("in get current customer");
                        String user=(String)myMsg.getObj();
                        System.out.println( "the user is" + user);
                        System.out.println(getCustomer(user));
                        MsgClass myMSg = new MsgClass("your current customer",getCustomer(user));
                        client.sendToClient(myMSg);
                        System.out.println("customer sent");
                    } catch (Exception e) {
                        System.out.println("error happened4");
                        System.out.println(e);
                    }
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
        session.beginTransaction();
        session.save(p);
        session.flush();
        session.getTransaction().commit();
        session.clear();
    }

    private static void AddReport(report R) {
        session.beginTransaction();
        session.clear();
        session.save(R);
        session.flush();
        session.getTransaction().commit();

    }


}



