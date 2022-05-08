package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;


@Entity
@Table(name = "Flowers")
public class Flower implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    @Column(name="Color")
    private String color;
    private String imgURL;

    public Flower(int price, String color, String imgURL) {
        this.price = price;
        this.color = color;
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Flower() {
    }

    public Flower(int id, int price, String color) {
        super();
        this.id = id;
        this.price = price;
        this.color = color;
    }

    public Flower(int price, String color) {
        this.price = price;
        this.color = color;
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


    @Override
    public String toString() {
        String output="price "+this.price +"\n"
                +"color "+ this.color+"\n";
        return  output;
    }
}
