package consumer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import consumer.Models.Catalog;
import consumer.services.CatalogService;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private CatalogService catalogService;
    @RabbitListener(queues = "link1")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    public void receive(String in, int receiver) {
        try {
            System.out.println("instance " + receiver + " [x] Received '" + in + "'");
            in = in.replace("\\", "");
            System.out.println(in);
            String d = in.substring(in.indexOf("\"thisCateg\":") + 12, in.length() - 1);
            System.out.println(d);
            Gson gson = new Gson();
            Catalog read = gson.fromJson(d, Catalog.class);
            System.out.println(read.getId());
        /*Catalog s = catalogService.getCatalogByChildName(read.getCatalogName());
        System.out.println(s.getId());*/
            
            try {
                String m = in.substring(in.lastIndexOf("\"parentCateg\":") + 14, in.indexOf("\"thisCateg\":") - 1);
                System.out.println(m);
                Catalog par = gson.fromJson(m, Catalog.class);
                read.setParentCategoryId(par.getId());
                System.out.println(par.getId());
        /*Catalog s = catalogService.getCatalogByChildName(read.getCatalogName());
        System.out.println(s.getId());*/
                catalogService.saveCatalog(par);
            } catch (Exception e) {
            }
            catalogService.saveCatalog(read);
        }catch (Exception e){

        }
    }
}
