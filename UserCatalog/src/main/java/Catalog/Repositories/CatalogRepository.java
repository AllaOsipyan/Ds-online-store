package Catalog.Repositories;

import Catalog.Models.Catalog;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogRepository extends MongoRepository<Catalog, String> {
    @Query("{ 'categoryName' : {$regex: ?0, $options: 'i' }}")
    Catalog findByCategoryName(final String catalogName);

}
