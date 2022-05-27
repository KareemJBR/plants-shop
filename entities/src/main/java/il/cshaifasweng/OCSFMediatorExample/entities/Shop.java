package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Shops")
public class Shop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "shop_name ")
    private String name ;

    @Column(name = "shop_address ")
    private String address ;

    @Column(name = "admin_id")
    private String admin_id;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Report> reports=new ArrayList<Report>();

    public Shop(String address, String admin_id) {
        this.address = address;
        this.admin_id = admin_id;
    }

    public Shop(String name, String address, String admin_id) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
}