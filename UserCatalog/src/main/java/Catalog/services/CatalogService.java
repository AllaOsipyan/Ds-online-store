package Catalog.services;

import Catalog.Models.Catalog;
import Catalog.Repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    public Catalog getCatalogByName(String name) {
        return catalogRepository.findByCatalogName(name);
    }

    public void saveCatalog(Catalog catalog){
        catalogRepository.save(catalog);
    }

    public List<Catalog> allCatalogs(){
        return catalogRepository.findAll();
    }
}
