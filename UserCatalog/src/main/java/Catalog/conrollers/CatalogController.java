package Catalog.conrollers;

import Catalog.Models.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Catalog.services.CatalogService;

import java.util.List;

@Controller
@CrossOrigin
public class CatalogController {
    @Autowired
    CatalogService service;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getHelloWorld(){
        return new ResponseEntity<>("HelloWorld!", HttpStatus.OK);
    }

    @RequestMapping(path = "/catalog/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByCatalogId(@PathVariable Long id){
        return new ResponseEntity<Catalog>(service.getCatalog(id), HttpStatus.OK);
    }
    @RequestMapping(path = "/category", method = RequestMethod.GET)
    public ResponseEntity<?> getParents(){
        return new ResponseEntity<List<Catalog>>(service.getAllParentCategories(), HttpStatus.OK);
    }

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public ResponseEntity<?> postNewCatalog(@RequestBody Catalog catalog){
        service.saveCatalog(catalog);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCatalogs(){
        return new ResponseEntity<List<Catalog>>(service.allCatalogs(), HttpStatus.OK);
    }

}
