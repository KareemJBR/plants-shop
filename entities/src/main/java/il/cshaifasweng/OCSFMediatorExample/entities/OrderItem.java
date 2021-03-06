package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orderitems")
public class OrderItem  implements Serializable {
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="amount")
    private int amount;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false, updatable = false)
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "Item_id")
    private Item item;




    public OrderItem(CartItem cartItem)
    {
        this.item=cartItem.getItem();
        this.customer=cartItem.getCustomer();
        this.amount=cartItem.getAmount();
    }

    public OrderItem(Item item, Customer customer, int amount) {
        this.item = item;
        this.customer = customer;
        this.amount = amount;
    }

    @Deprecated
    public OrderItem() {

    }
    public Customer getCustomer(){
        return this.customer;
    }

    public Item getItem(){return this.item;}

    public int getAmount(){return this.amount;}
    public int getId(){return this.id;}

    public void addAmount(){
        this.amount++;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

