package Catalog.Models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Document(collection = "catalog")
public class Catalog {
    @Id
    private ObjectId id;

    @Indexed
    private String catalogName;

    private Catalog parentCatalog;

    public Catalog(){}

    public ObjectId getId() {
        return id;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public Catalog getParentCatalog() {
        return parentCatalog;
    }
}
