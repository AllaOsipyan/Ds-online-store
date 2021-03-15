package Admin.Models;

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
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false) //??Сделать отдельную сущность
    private Category productCategory;
    @Column(name="image")
    private String imageUrl;

    public Product(){

    }

    public Product(Long id, String name, double price, Category category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productCategory = category;
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
