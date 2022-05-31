package il.cshaifasweng.OCSFMediatorExample.entities;

import javafx.scene.Parent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
@Table(name = "Item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="Color")
    private String color;
    private int catalogNumber;
    private boolean underSale=false;
    private double salePercent;
    private int price;//Price after the sale
    private int Original_price;

    @Column(name="Item_Type")
    private String type;
    private String imgURL;
    @Column(name="Item_name")
    private String name;




    public Item(String color, boolean underSale, double salePercent, int original_price, String type, String imgURL, String name) {
        this.color = color;
        Random rand =new Random();
        this.catalogNumber= rand.nextInt(10000);
        this.underSale = underSale;
        this.salePercent = salePercent;
        this.Original_price = original_price;
        if(underSale){
            this.price=(int)(original_price*(1-salePercent));
        }
        else {
            this.price = original_price;
        }

        this.type = type;
        this.imgURL = imgURL;
        this.name = name;
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
        if(underSale==false){
            this.price=this.Original_price;
        }
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

    public double getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
        this.price=this.Original_price*(1-salePercent);
    }

    public int getPriceAfterSale() {
        return price;
    }

    public void setPriceAfterSale(int priceAfterSale) {
        this.price = priceAfterSale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOriginal_price() {
        return Original_price;
    }

    public void setOriginal_price(int original_price) {
        Original_price = original_price;
    }


    @Override
    public String toString() {
        String output="type: "+this.type+"\n"+" price: "+"\n"
                +"color: "+ this.color+"\n";
        return  output;
    }
}