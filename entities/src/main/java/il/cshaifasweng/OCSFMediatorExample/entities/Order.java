package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "order_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer orderCustomer;

    @Column(name="receipt_year")
    private int receipt_year;

    @Column(name="receipt_month")
    private int receipt_month;

    @Column(name="receipt_day")
    private int receipt_day;

    @Column(name="receipt_hour")
    private int receipt_hour;

    @Column(name="receipt_minute")
    private int receipt_minute;

    @Column(name="order_year")
    private int order_year;

    @Column(name="order_month")
    private int order_month;

    @Column(name="order_day")
    private int order_day;

    @Column(name="order_hour")
    private int order_hour;

    @Column(name="order_minute")
    private int order_minute;

    @Column(name="order_price")
    private double price;

    @Column(name="pay_method")
    private String pay_method;

    @Column(name="shipping_method")
    private String shipping_method;

    @Column(name="greeting")
    private String greeting;

@ManyToMany(cascade = { CascadeType.ALL })
@JoinTable(
        name = "Order_Item",
        joinColumns = { @JoinColumn(name = "order_id") },
        inverseJoinColumns = { @JoinColumn(name = "item_id") }
)
private List<Item> items = new ArrayList<>();

    public Order(Customer customer,int order_year,int order_month,int order_day,int receipt_year,int receipt_month,int receipt_day,int order_hour,int order_minute,int receipt_hour,int receipt_minute,double price,String pay_method,String shipping_method,String greeting)
    {
        this.greeting=greeting;
        this.shipping_method=shipping_method;
        this.pay_method=pay_method;
        this.price=price;
        this.orderCustomer=customer;
        this.order_year=order_year;
        this.order_month=order_month;
        this.order_day=order_day;
        this.order_minute=order_minute;
        this.order_hour=order_hour;
        this.receipt_year=order_year;
        this.receipt_month=receipt_month;
        this.receipt_day=receipt_day;
        this.receipt_minute=receipt_minute;
        this.receipt_hour=receipt_hour;
    }

    public Order() {

    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.orderCustomer = customer;
    }

    public void setReceipt_year(int receipt_year) {
        this.receipt_year = receipt_year;
    }

    public void setReceipt_month(int receipt_month) {
        this.receipt_month = receipt_month;
    }

    public void setReceipt_day(int receipt_day) {
        this.receipt_day = receipt_day;
    }

    public void setReceipt_hour(int receipt_hour) {
        this.receipt_hour = receipt_hour;
    }

    public void setReceipt_minute(int receipt_minute) {
        this.receipt_minute = receipt_minute;
    }

    public void setOrder_year(int order_year) {
        this.order_year = order_year;
    }

    public void setOrder_month(int order_month) {
        this.order_month = order_month;
    }

    public void setOrder_day(int order_day) {
        this.order_day = order_day;
    }

    public void setOrder_hour(int order_hour) {
        this.order_hour = order_hour;
    }

    public void setOrder_minute(int order_minute) {
        this.order_minute = order_minute;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return orderCustomer;
    }

    public int getReceipt_year() {
        return receipt_year;
    }

    public int getReceipt_month() {
        return receipt_month;
    }

    public int getReceipt_day() {
        return receipt_day;
    }

    public int getReceipt_hour() {
        return receipt_hour;
    }

    public int getReceipt_minute() {
        return receipt_minute;
    }

    public int getOrder_year() {
        return order_year;
    }

    public int getOrder_month() {
        return order_month;
    }

    public int getOrder_day() {
        return order_day;
    }

    public int getOrder_hour() {
        return order_hour;
    }

    public int getOrder_minute() {
        return order_minute;
    }

    public double getPrice() {
        return price;
    }

    public String getPay_method() {
        return pay_method;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public String getGreeting() {
        return greeting;
    }

    public List<Item> getItems() {
        return items;
    }
}
