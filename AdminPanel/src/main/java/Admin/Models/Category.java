package Admin.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> childCategories;
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent_category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parentCategory;
    /*@Column(name="parent_category_id" )
    private Long parentCategoryId;*/


    public Category(){}

    public Category(Long id, String categoryName, Category parentCategory, List<Category> childCategories, List<Product> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategories = childCategories;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getParentCategoryId() {
        if(parentCategory!=null) {
            return this.parentCategory.getId();
        }
        return null;
    }
    @JsonIgnore
    public Category getParentCategory() {
        return parentCategory;
    }


    public List<Product> getProducts() {
        return products;
    }

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
        this.childCategories = childCategories;
    }

    /*public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
*/
    @JsonProperty
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
