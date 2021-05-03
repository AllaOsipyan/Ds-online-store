package Catalog.services;

import Catalog.Models.Catalog;
import Catalog.Repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    public Catalog getCatalogByName(String name) {
        return catalogRepository.findByCategoryName(name);
    }

    public void saveCatalog(Catalog catalog){
        catalogRepository.insert(catalog);
    }

    public Catalog getCatalog(Long id){ return  catalogRepository.findById(id).orElse(null);}
    public List<Catalog> allCatalogs(){
        return catalogRepository.findAll();
    }
    public List<Catalog> getAllParentCategories() {
        List<Catalog> parentCatalogs = new ArrayList();
        for (Catalog catalog: allCatalogs()){
            if(catalog.getParentCategoryId() == null)
                parentCatalogs.add(catalog);
        }
        return parentCatalogs;
    }
}
