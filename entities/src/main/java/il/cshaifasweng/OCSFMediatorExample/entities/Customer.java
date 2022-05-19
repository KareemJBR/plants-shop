package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer  implements Serializable {
    @Id
    private final int id;

    @Column(name = "customer_user_name")
    private String user_name;

    @Column(name = "customer_first_name")
    private String first_name;
    @Column(name = "customer_last_name")
    private String last_name;
    @Column(name = "customer_password")
    private String password;
    @Column(name = "customer_credit_card")
    private String credit_card;
    @Column(name = "customer_budget")
    private double budget;
    @Column(name = "account_type")
    String acount_type;



    public Customer(int id, String first_name, String last_name, String user_name,String password,String credit_card,String account_type){
        budget = 0.0;
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.user_name=user_name;
        this.password=password;
        this.credit_card=credit_card;
        this.acount_type=account_type;
    }

    public Customer() {
        this.id=123456789;
    }


    public void deposit(double amountToAdd){
        budget += amountToAdd;
    }

    public void pay(double amountToPay){
        budget -= amountToPay;
    }

    public double getBudget(){
        return budget;
    }
    public String getAcount_type(){
        return this.acount_type;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public void setAccount_type(String user_name){
        this.user_name = user_name;
    }

    public int getUser_id(){
        return id;
    }

    public String getFirst_name(){
        return first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public String getUser_name(){
        return this.user_name;
    }
    public String getPassword(){
        return this.password;
    }
}
