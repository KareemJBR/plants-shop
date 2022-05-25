package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;


@Entity
@Table(name = "cartitems")
public class CartItem  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "Item_id")
    private Item item;


    public CartItem(Customer customer,Item item,int amount)
    {
        this.item=item;
        this.customer=customer;
        this.amount=amount;
    }

    public CartItem() {

    }
    public Customer getCustomer(){
        return this.customer;
    }

    public Item getItem(){return this.item;}

    public int getAmount(){return this.amount;}
    public int getId(){return this.id;}

}
