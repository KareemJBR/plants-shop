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

    @Column(name = "admin_user_name")
    private String user_name;

    @Column(name = "customer_password")
    private String password;

    public ShopAdmin(String user_name, String password, String admin_id, String admin_first_name, String admin_last_name) {
        this.admin_id = admin_id;
        this.admin_first_name = admin_first_name;
        this.admin_last_name = admin_last_name;
        this.user_name=user_name;
        this.password=password;
    }

    @Deprecated
    public ShopAdmin() {
        admin_id = null;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
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

}