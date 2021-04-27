package Admin.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
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
    @JsonIgnore
    public Category getProductCategory() {
        return productCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    @JsonProperty
    public void setProductCategory(Category category) {
        this.productCategory = category;
    }
}
