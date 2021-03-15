package Admin.Repositories;

import Admin.Models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Modifying
    @Query(value="update product set name = :name, price = :price, image = :imageUrl where id = :id",  nativeQuery = true)
    int updateProduct( @Param("id") Long id,
                       @Param("name") String name,
                       @Param("price") double price,
                       @Param("imageUrl") String imageUrl );
}
