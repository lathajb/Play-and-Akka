package actors;

import akka.actor.AbstractActor;
import models.Product;
import utilities.CacheManager;
import utilities.RedisCacheUtility;

/**
 * Created by janlatha on 6/14/2018.
 */
public class GuavaSystemActor extends AbstractActor{


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Product.class, product -> {



                    CacheManager cacheManager = new CacheManager();
                    // By using callable call method to load to in memory
                    Object searchQueryResponse = cacheManager.getCachedKeyValue("pro"+product.getId(),
                            () -> {
                                return product;
                            });
                    System.out.println("Printing Guava Cache System Response :: " + searchQueryResponse);

                    // By using CacheLoader
                    Object resultProduct = cacheManager.get("pro"+product.getId());

                    sender().tell(searchQueryResponse.toString(),self());

                    //sender().tell(resultProduct.toString(),self());

                }).build();
    }

}
