package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sale_id;

    @Column(name = "sale_title")
    private String sale_title;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "price")
    private Double price;

    // TODO: add two lists, one for the items, and the other for counting the items

    public Sale(String sale_title, LocalDate start_date, LocalDate end_date, Double price) {
        this.sale_title = sale_title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
    }

    @Deprecated
    public Sale() {

    }

    public int getSale_id() {
        return sale_id;
    }

    public String getSale_title() {
        return sale_title;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public Double getPrice() {
        return price;
    }

}
