package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Flower_Bouquet")
public class FlowerBouquet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    @Column(name="Color")
    private String color;
    private String imgURL;

    public FlowerBouquet(int id, int price, String color, String imgURL) {
        super();
        this.id = id;
        this.price = price;
        this.color = color;
        this.imgURL = imgURL;
    }

    public FlowerBouquet() {
    }

    public FlowerBouquet(int price, String color, String imgURL) {
        this.price = price;
        this.color = color;
        this.imgURL = imgURL;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        String output="type: Flower Bouquet"+"\n"+" price: "+this.price +"\n"
                +"color: "+ this.color+"\n";
        return  output;
    }

}
