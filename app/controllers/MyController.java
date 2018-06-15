package controllers;

import actors.AkkaSystem;
import akka.actor.ActorRef;
import models.Product;
import org.elasticsearch.client.Client;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import utilities.ElasticSearchClient;

import static akka.pattern.Patterns.ask;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

/**
 * Created by janlatha on 6/12/2018.
 */
public class MyController extends play.mvc.Controller {

    private final ActorRef esActorRef = AkkaSystem.startESActorSystem();
    private final ActorRef redisActorRef = AkkaSystem.startRedisActorSystem();
    private final ActorRef guavaActorRef = AkkaSystem.startGuavaActorSystem();

    public void getClient() throws Exception {

        ElasticSearchClient getESClient = new ElasticSearchClient();
        Client client = getESClient.newClient("localhost","my-application",9300,1000);
        System.out.println(client.settings());

    }

    public Result getProducts1() throws Exception{
        getClient();
        return ok("Your new application is ready.");
    }

    public CompletionStage<Result> getProducts() {

        return FutureConverters.toJava(ask(redisActorRef, new Product(12,"ttt"), 50000))
                .thenApply(response -> ok((String) response));
    }

}
