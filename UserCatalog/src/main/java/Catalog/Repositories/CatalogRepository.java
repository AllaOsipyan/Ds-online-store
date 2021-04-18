package Catalog.Repositories;

import Catalog.Models.Catalog;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<Catalog, String> {
    @Query("{ 'catalogName' : {$regex: ?0, $options: 'i' }}")
    Catalog findByCatalogName(final String catalogName);
}
