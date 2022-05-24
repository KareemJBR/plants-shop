package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    @Column(name="Color")
    private String color;

    @Column(name="Item_Type")
    private String type;
    private String imgURL;

    public Item(int price, String color, String imgURL,String type) {
        this.price = price;
        this.color = color;
        this.imgURL = imgURL;
        this.type=type;
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


    @Override
    public String toString() {
        String output="type: "+this.type+"\n"+" price: "+this.price +"\n"
                +"color: "+ this.color+"\n";
        return  output;
    }
}