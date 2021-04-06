package Admin.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String categoryName;
    @ManyToOne
    @JoinColumn(name="parent_category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parentCategory;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Category(){}

    public Category(Long id, String categoryName, Category parentCategory) {
        this.id = id;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public List<Product> getProducts() {
        return products;
    }
}
