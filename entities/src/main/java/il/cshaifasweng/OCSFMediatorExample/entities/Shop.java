package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Shops")
public class Shop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "shop_address ")
    private String address ;

    @Column(name = "admin_id")
    private String admin_id;

    public Shop(String address, String admin_id) {
        this.address = address;
        this.admin_id = admin_id;
    }

    @Deprecated
    public Shop() {
        id = -1;
        address = null;
        admin_id = null;
    }


//    public void Shop(String address,int admin_id)
//    {
//        this.address=address;
//        this.admin_id=admin_id;
//    }

    public String getAddress(){
        return this.address;
    }

    public String getAdminId(){
        return this.admin_id;
    }
}