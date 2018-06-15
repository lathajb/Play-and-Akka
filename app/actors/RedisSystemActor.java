package actors;

import akka.actor.AbstractActor;
import models.Product;
import org.elasticsearch.client.Client;
import utilities.ElasticSearchClient;
import utilities.ElasticSearchUtility;
import utilities.RedisCacheUtility;

import javax.inject.Inject;

/**
 * Created by janlatha on 6/14/2018.
 */
public class RedisSystemActor extends AbstractActor {

    private RedisCacheUtility redisCacheUtility = null;

    @Override
    public AbstractActor.Receive createReceive() {
        redisCacheUtility = new RedisCacheUtility();
        return receiveBuilder()
                .match(Product.class, product -> {
                    String respProduct = redisCacheUtility.getDetails(product);
                    sender().tell(respProduct,self());
                }).build();
    }
}
