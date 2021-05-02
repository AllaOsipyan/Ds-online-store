package Catalog.Models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "catalog")
public class Catalog {
    @Id
    private Long id;

    @Indexed
    private String categoryName;

    private List<Catalog> childCategories;

    private List<Product> products;

    public Catalog(){}

    public Catalog(Long id, String categoryName, List<Catalog> childCatalogs, List<Product> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.childCategories = childCatalogs;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Catalog> getChildCatalogs() {
        return childCategories;
    }

    public List<Product> getProducts() {
        return products;
    }
}
