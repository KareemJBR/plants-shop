package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "net_workers")
public class NetWorker implements Serializable {
    @Id
    @Column(name = "worker_id")
    private final String worker_id;

    @Column(name = "worker_first_name")
    private String first_name;

    @Column(name = "worker_last_name")
    private String last_name;

    @Column(name = "worker_username")
    private String username;

    @Column(name = "worker_password")
    private String password;
    public NetWorker(String worker_id, String first_name, String last_name, String username, String password){
        this.worker_id=worker_id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.username=username;
        this.password=password;
    }

    @Deprecated
    public NetWorker() {
        this.worker_id = null;
    }

    public String getId(){
        return this.worker_id;
    }
    public String getUser_name(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getFirst_Name(){
        return this.first_name;
    }
    public String getLast_name(){
        return this.last_name;
    }
}
