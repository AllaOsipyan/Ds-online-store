package Admin.Service;

import Admin.Models.Category;
import Admin.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Long createCategory(Category category){
        return categoryRepository.save(category).getId();
    }

    public List<Category> getAll(){
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        return categories;
    }

    public void delete(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
}
