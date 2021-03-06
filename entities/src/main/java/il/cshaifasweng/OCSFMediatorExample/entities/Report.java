package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
@Entity
@Table(name = "Reports")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    private String content;
    private boolean handled=false;
    private boolean workingOnIT=false;
    private String answer;
    private String reportDate;
    private Double MoneyBack;

    @Column(name="handled_by_id")
    private String handled_by_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Shop_id")
    private Shop shop;

    @Deprecated
    public Report() {
    }

    @Deprecated
    public Report(Report other) {
        this.id = other.id;
        this.content = other.content;
        this.handled = other.handled;
        this.workingOnIT = other.workingOnIT;
        this.answer = other.answer;
        this.reportDate = other.reportDate;
        this.MoneyBack = other.MoneyBack;
        this.handled_by_id = other.handled_by_id;
        this.customer = other.customer;
        this.shop = other.shop;
    }

    public Report(String content, boolean handled, boolean workingOnIT, String answer, String reportDate,
                  double MoneyBack, String handled_by_id, Customer customer, Shop shop) {
        this.content = content;
        this.handled = handled;
        this.workingOnIT = workingOnIT;
        this.answer = answer;
        this.reportDate = reportDate;
        this.MoneyBack = MoneyBack;
        this.handled_by_id = handled_by_id;
        this.customer = customer;
        this.shop = shop;
    }

    public Report(String content, boolean handled, boolean workingOnIT, String answer) {
        this.content = content;
        this.handled = handled;
        this.workingOnIT = workingOnIT;
        this.answer = answer;
        this.handled_by_id = null;
        setdate();
    }

    public Report(String content, boolean handled, boolean workingOnIT, String answer, Customer customer) {
        this.content = content;
        this.handled = handled;
        this.workingOnIT = workingOnIT;
        this.answer = answer;
        this.customer = customer;
        this.handled_by_id = null;
    }

    public Report(String content, String answer, Customer customer) {
        this.content = content;
        this.answer = answer;
        this.customer = customer;
        this.handled_by_id = null;
    }

    public String getHandled_by_id() {
        return handled_by_id;
    }

    public void setHandled_by_id(String handled_by_id) {
        this.handled_by_id = handled_by_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public boolean isWorkingOnIT() {
        return workingOnIT;
    }

    public void setWorkingOnIT(boolean workingOnIT) {
        this.workingOnIT = workingOnIT;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getMoneyBack() {
        return MoneyBack;
    }

    public void setMoneyBack(Double moneyBack) {
        MoneyBack = moneyBack;
    }

    public void setdate(){
        //Date date=new Date();
        //this.reportDate=date.toString();
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        this.reportDate=timeStamp;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getdate(){
        Date date = new Date(this.reportDate)  ;
        return date;
    }

    public int getDay()
    {
        String[] date=this.reportDate.split("/");
        int day= Integer.parseInt(date[0]);
        return day;
    }

    public int getMonth()
    {
        String[] date=this.reportDate.split("/");
        int month= Integer.parseInt(date[1]);
        return month;
    }

    public int getYear()
    {
        String[] date=this.reportDate.split("/");
        int year= Integer.parseInt(date[2]);
        return year;
    }




}