package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Basic
    @Column(name="receiving_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date receiving_date;

    @Basic
    @Column(name="order_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Basic
    @Column(name="order_time")
    @Temporal(TemporalType.TIME)
    private Time time;

    @Column(name="order_time")
    private int price;

    @Column(name="pay_method")
    private String pay_method;

    @Column(name="receiving_method")
    private String receiving_method;

    public Order(Customer customer,Date date,Date receiving_date,Time time,int price,String pay_method,String receiving_method)
    {
        this.receiving_method=receiving_method;
        this.pay_method=pay_method;
        this.price=price;
        this.time=time;
        this.customer=customer;
        this.date=date;
        this.receiving_date=receiving_date;
    }

    public Order() {

    }

}
