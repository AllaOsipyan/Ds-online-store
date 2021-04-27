package Admin;

import Admin.Models.Category;
import Admin.Models.Product;
import Admin.Service.CategoryService;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Producer {
    public String connectionString = System.getenv("RABBIT_CONNECTION");
    private ConnectionFactory factory = new ConnectionFactory();
    @Autowired
    private CategoryService categoryService;

    public void sendCategory(Category category) throws IOException {
        factory.setHost(connectionString);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            JSONObject message = new JSONObject();
            Gson gson = new Gson();
            System.out.println( gson.toJson(category));
            if (category != null) {
                while(category.getParentCategoryId() != null){

                    category = categoryService.getById(category.getParentCategoryId());

                    //parentCategory = category.getParentCategory();
                }
                System.out.println( gson.toJson(category));


                //String JSONChildren = gson.toJson(category.getChildCategories());
                //String JSONProducts = gson.toJson(category.getProducts());

                message.put("category", gson.toJson(category));
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
                System.out.println(product.getProductCategory().getCategoryName());
                sendCategory(product.getProductCategory());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
