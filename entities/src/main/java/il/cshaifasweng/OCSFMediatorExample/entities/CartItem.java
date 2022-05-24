package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;


@Entity
@Table(name = "CartItem")
public class CartItem  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "Item_id")
    private Item item;


    public CartItem(Customer customer,Item item)
    {
        this.item=item;
        this.customer=customer;
    }

    public CartItem() {

    }
    public Customer getCustomer(){
        return this.customer;
    }

    public Item getItem(){return this.item;}

}
