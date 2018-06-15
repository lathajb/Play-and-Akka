package utilities;

import actors.AkkaSystem;
import akka.actor.ActorRef;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import models.Product;

import java.util.concurrent.ExecutionException;

/**
 * Created by janlatha on 6/14/2018.
 */
public class RedisCacheUtility {

    private RedisCacheClient redisCacheClient = new RedisCacheClient();
    //private RedisAsyncCommands<String, String> asyncCommands = redisCacheClient.getSyncCommandsObj();
    private RedisCommands<String, String> syncCommands = redisCacheClient.getSyncCommandsObj();
    private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    public String getDetails(Product product){
        String productDetails = null;
        try {
            //String resProduct = asyncCommands.get(product.toString()).get();
            productDetails = syncCommands.get("pro"+product.getId());

            if(productDetails == null){
                syncCommands.set("pro"+product.getId(), product.toString());
                productDetails = syncCommands.get("pro"+product.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDetails;
    }

    public String getDetails(String key){
        return  syncCommands.get(key);
    }
}
