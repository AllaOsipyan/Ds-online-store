package consumer.services;

import consumer.Models.Catalog;
import consumer.Repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    public Catalog getCatalogByName(String name) {
        return catalogRepository.findByCategoryName(name);
    }

    public void saveCatalog(Catalog catalog){

        catalogRepository.save(catalog);
        System.out.println(catalog.getCatalogName());
    }
    public void deleteCatalog(Catalog catalog) {
        catalogRepository.delete(catalog);
    }
    public Catalog getCatalogByChildName(String name) {
        return  catalogRepository.getParentCatalog(name);
    }

    public List<Catalog> allCatalogs(){
        return catalogRepository.findAll();
    }
}
