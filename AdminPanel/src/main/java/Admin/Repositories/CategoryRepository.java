package Admin.Repositories;

import Admin.Models.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value="SELECT * FROM category where parent_category_id = :parentCategoryId", nativeQuery = true)
    public List<Category> findChildrenCategories(@Param("parentCategoryId") Long parentCategoryId);
    @Modifying
    @Query(value="update category set name = :name where id = :id",  nativeQuery = true)
    int update( @Param("id") Long id,
                       @Param("name") String name);
}
