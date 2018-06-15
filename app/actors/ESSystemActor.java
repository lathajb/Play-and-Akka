package actors;

import akka.actor.AbstractActor;
import models.Product;
import org.elasticsearch.client.Client;
import utilities.ElasticSearchClient;
import utilities.ElasticSearchUtility;


/**
 * Created by janlatha on 6/13/2018.
 */
public class ESSystemActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Product.class, identity -> {

                    ElasticSearchClient getESClient = new ElasticSearchClient();
                    ElasticSearchUtility utility = new ElasticSearchUtility();
                    Client client = getESClient.newClient("localhost","my-application",9300,5000);
                    System.out.println(client.settings());

                    Product product = utility.performQuery(client,identity);

                    if(product == null){
                        utility.CreateDocument(client,identity);
                        sender().tell(identity.toString(),self());
                    }else{
                        sender().tell(product.toString(),self());
                    }

                }).build();


    }
}