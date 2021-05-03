package consumer.Repositories;

import consumer.Models.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CatalogRepository extends MongoRepository<Catalog, String> {
    @Query("{ 'categoryName' : {$regex: ?0, $options: 'i' }}")
    Catalog findByCategoryName(final String catalogName);
    @Query("{'childCategories.categoryName': ?0}")
    Catalog getParentCatalog(final String childCatalogName);

}
