package Admin;

import Admin.Models.Category;
import Admin.Models.Product;
import Admin.Service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Producer {
    public String connectionString = System.getenv("RABBIT_CONNECTION");
    private ConnectionFactory factory = new ConnectionFactory();
    @Autowired
    private CategoryService categoryService;

    private Category parentCategory = new Category();

    public void sendCategory(Category category) throws IOException {
        factory.setHost(connectionString);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            JSONObject message = new JSONObject();
            JSONObject JSONCategory = new JSONObject();

            JSONObject jsonString = new JSONObject();
            if (category != null) {

                JSONCategory.put("id", category.getId());
                JSONCategory.put("categoryName", category.getCategoryName());
                JSONCategory.put("childCategories", getSerializedCategory(category));
                JSONCategory.put("products", getSerializedProducts(category.getProducts()));
                if(category.getParentCategoryId() != null) {
                    JSONCategory.put("parentCategoryId", parentCategory.getId());
                    parentCategory = categoryService.getById(category.getParentCategoryId());

                    jsonString.put("categoryName", parentCategory.getCategoryName());
                    jsonString.put("id", parentCategory.getId());
                    jsonString.put("childCategories", getSerializedCategory(parentCategory));
                    jsonString.put("products", getSerializedProducts(parentCategory.getProducts()));
                    System.out.println(jsonString.toString());
                }
                System.out.println(JSONCategory.toString());
                message.put("thisCateg", JSONCategory);
                if(!jsonString.isEmpty()) {
                    message.put("parentCateg", jsonString);
                }
                /*message.put("categoryName", category.getCategoryName());
                message.put("childCategories", JSONChildren);
                message.put("products", JSONProducts);*/
            }
            channel.exchangeDeclare("links", "fanout", true);
            channel.basicPublish( "links", "", null, message.toJSONString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendProduct(Product product) {
        try {
            if (product != null) {
                System.out.println(product.getProductCategoryId());
                sendCategory(categoryService.getById(product.getProductCategoryId()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private JSONArray getSerializedCategory(Category category){
        try {
            JSONArray array = new JSONArray();
            for (Category childCategory : category.getChildCategories()) {
                Map<String, String> serializedChild = new HashMap<>();
                serializedChild.put("id", childCategory.getId().toString());
                serializedChild.put("categoryName", childCategory.getCategoryName());
                array.add(serializedChild);
            }
            return array;
        } catch (Exception e) {}
        return new JSONArray();
    }
    private JSONArray getSerializedProducts(List<Product> products){
        try {
            JSONArray array = new JSONArray();
            if(products!=null) {
                for (Product product : products) {
                    Map<String, String> serializedChild = new HashMap<>();
                    serializedChild.put("id", product.getId().toString());
                    serializedChild.put("name", product.getName());
                    serializedChild.put("price", String.valueOf(product.getPrice()));
                    serializedChild.put("productCategoryId", product.getProductCategoryId().toString());
                    serializedChild.put("imageUrl", product.getImageUrl());
                    array.add(serializedChild);
                }
                return array;
            }

        } catch (Exception e){}
        return new JSONArray();
    }
}
