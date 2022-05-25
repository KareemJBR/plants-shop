package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;
@Entity
@Table(name = "Item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    @Column(name="Color")
    private String color;
    private int catalogNumber;
    boolean underSale=false;

    @Column(name="Item_Type")
    private String type;
    private String imgURL;
    @Column(name="Item_name")
    private String name;


    public Item(int price, String color, String type, String imgURL, String name) {
        this.price = price;
        this.color = color;
        this.type = type;
        this.imgURL = imgURL;
        this.name = name;
        Random rand =new Random();
        this.catalogNumber= rand.nextInt(10000);
    }

    public Item(int price, String color, String imgURL, String type) {
        this.price = price;
        this.color = color;
        this.imgURL = imgURL;
        this.type=type;
        Random rand =new Random();
        this.catalogNumber= rand.nextInt(10000);
    }

    public String getImgURL() {
        return this.imgURL;
    }

    public String getType() {
        return this.type;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getUrl() {
        return this.imgURL;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(int catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public boolean isUnderSale() {
        return underSale;
    }

    public void setUnderSale(boolean underSale) {
        this.underSale = underSale;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String output="type: "+this.type+"\n"+" price: "+this.price +"\n"
                +"color: "+ this.color+"\n";
        return  output;
    }
}