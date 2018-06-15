package utilities;


import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * Created by janlatha on 6/13/2018.
 */
public class RedisCacheClient {

    private String redisURI = "redis://localhost:6379/0";
    private RedisClient redisClient = RedisClient.create(redisURI);
    private StatefulRedisConnection<String, String> connection = null;
    private RedisAsyncCommands<String, String> asyncCommands = null;
    private RedisCommands<String, String> syncCommands = null;

    public RedisCacheClient(){

    }


    public RedisAsyncCommands<String, String> getASyncCommandsObj() {

        try{
             RedisClient.create(redisURI);
             connection = redisClient.connect();
             asyncCommands = connection.async();
            //syncCommands.set("key", "Hello, Redis!");

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(connection != null && redisClient != null) {
               // connection.close();
               // redisClient.shutdown();
            }
        }
        return asyncCommands;
    }

    public RedisCommands<String, String> getSyncCommandsObj() {

        try{
            RedisClient.create(redisURI);
            connection = redisClient.connect();
            syncCommands = connection.sync();
            //syncCommands.set("key", "Hello, Redis!");

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(connection != null && redisClient != null) {
                //connection.close();
               // redisClient.shutdown();
            }
        }
        return syncCommands;
    }

}
