package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer  implements Serializable {
    @Id
    @Column(name = "customer_id")
    private final String id;


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
    private String acount_type;

    @Column(name = "customer_email")
    private String customer_email;

    @Column(name = "online")
    private boolean online=false;


    @OneToMany(mappedBy = "customer")
    private List<Order> customerOrders =new ArrayList<>();
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Report> customerReports =new ArrayList<>();




    public Customer(String id, String first_name, String last_name, String user_name, String password,
                    String credit_card, String account_type, String customer_email){
        budget = 0.0;
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.user_name=user_name;
        this.password=password;
        this.credit_card=credit_card;
        this.acount_type=account_type;
        this.customer_email=customer_email;
        online=false;
    }

    @Deprecated
    public Customer(Customer customer) {
        this.id = customer.id;
        this.first_name = customer.first_name;
        this.last_name = customer.last_name;
        this.budget = customer.budget;
        this.user_name = customer.user_name;
        this.password = customer.password;
        this.credit_card = customer.credit_card;
        this.acount_type = customer.acount_type;
        this.customer_email = customer.customer_email;
        this.customerOrders = customer.customerOrders;
        this.customerReports = customer.customerReports;
    }

    @Deprecated
    public Customer() {
        this.id="123456789";
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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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

    public void setEmail(String email){
        this.customer_email = email;
    }

    public String getUser_id(){
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

    public String getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setAcount_type(String acount_type) {
        this.acount_type = acount_type;
    }

    public List<Report> getCustomerReports() {
        return this.customerReports;
    }

    public void addReport(Report report){
        this.customerReports.add(report);
    }
    public void removeReport(Report report){
        this.customerReports.remove(report);
    }

    public String getEmail(){
        return this.customer_email;
    }

    public void addItem(Report report){
        this.customerReports.add(report);
    }

    public String getCredit_card() { return credit_card; }
}
