package Admin.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private double price;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="category_id", nullable=false)
    private Category productCategory;
    @Column(name="image")
    private String imageUrl;

    public Product(){

    }

    public Product(Long id, String name, double price, Category productCategory, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
