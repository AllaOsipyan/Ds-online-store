package Admin.Service;

import Admin.Models.Product;
import Admin.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Long createProduct(Product product){
        return productRepository.save(product).getId();
    }
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public List<Product> getAll(){
        return (List<Product>) productRepository.findAll();
    }
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
    
    @Transactional
    public int update(Product product){
        return productRepository.updateProduct(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}
