package consumer.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "catalog")
public class Catalog {
    @Id
    private Long id;

    @Indexed
    private String categoryName;

    private List<Catalog> childCategories;

    private List<Product> products;
    private Long parentCategoryId;

    public Catalog(){}

    public Catalog(Long id, String catalogName, List<Catalog> childCatalogs, List<Product> products, Long parentCategoryId) {
        this.id = id;
        this.categoryName = catalogName;
        this.childCategories = childCatalogs;
        this.products = products;
        this.parentCategoryId = parentCategoryId;
    }

    public Long getId() {
        return id;
    }

    public String getCatalogName() {
        return categoryName;
    }

    public List<Catalog> getChildCategories() {
        return childCategories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
