package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shop_admins")
public class ShopAdmin  implements Serializable {
    @Id
    @Column(name = "admin_id")
    private final String admin_id;

    @Column(name = "admin_first_name")
    private String admin_first_name;

    @Column(name = "admin_last_name")
    private String admin_last_name;

    @Column(name = "shop_id")
    private final int shop_id;

    public ShopAdmin(String admin_id, String admin_first_name, String admin_last_name, int shop_id) {
        this.admin_id = admin_id;
        this.admin_first_name = admin_first_name;
        this.admin_last_name = admin_last_name;
        this.shop_id = shop_id;
    }

    @Deprecated
    public ShopAdmin() {
        shop_id = -1;
        admin_id = null;
    }

    public void setAdmin_first_name(String admin_first_name){
        this.admin_first_name = admin_first_name;
    }

    public void setAdmin_last_name(String admin_last_name1){
        this.admin_last_name = admin_last_name1;
    }

    public String getAdmin_id(){
        return admin_id;
    }

    public String getAdmin_first_name(){
        return admin_first_name;
    }

    public String getAdmin_last_name(){
        return admin_last_name;
    }

    public int getShop_id(){
        return shop_id;
    }

}