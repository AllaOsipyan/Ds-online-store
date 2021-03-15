package Admin.Models;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String categoryName;
    @ManyToOne
    @JoinColumn(name="parentCategory_id")
    private Category parentCategory;

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
}
