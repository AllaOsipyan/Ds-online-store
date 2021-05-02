package Admin.controllers;

import Admin.Models.Category;
import Admin.Models.Product;
import Admin.Producer;
import Admin.Service.CategoryService;
import Admin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
public class PanelController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    Producer producer;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getHelloWorld(){
        return new ResponseEntity<>("HelloWorld", HttpStatus.OK);
    }



    @RequestMapping(path = "/category", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCategory(@RequestBody Category category) {
        try {
            Long categoryId = categoryService.createCategory(category);
            List<Category> childCategories = categoryService.getChildCategories(categoryId);
            producer.sendCategory(category);
            return new ResponseEntity<>(categoryId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "/category", method = RequestMethod.PATCH)
    public ResponseEntity<?> editCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "/category", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategories(){
        try {
            return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        try {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            System.out.println(product.getName());
            Long id =productService.createProduct(product);
            System.out.println(product.getName());
            producer.sendProduct(product);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @RequestMapping(path ="/product", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(){
        try {
            return new ResponseEntity<List<Product>>(productService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "/product", method = RequestMethod.PATCH)
    public ResponseEntity<?> editProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        try {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
