package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Created by janlatha on 6/13/2018.
 */
public class AkkaSystem {


    public static ActorRef  startESActorSystem() {

        final ActorSystem esSystem = ActorSystem.create("ESSystem",
                ConfigFactory.load("common"));

        final ActorRef esActor = esSystem.actorOf(Props.create(ESSystemActor.class),
                "ESActor");

        System.out.println("Started ESSystem");
        return esActor;
    }

    public static ActorRef  startRedisActorSystem() {

        final ActorSystem redisSystem = ActorSystem.create("RedisActorSystem",
                ConfigFactory.load("common"));

        final ActorRef redisActor = redisSystem.actorOf(Props.create(RedisSystemActor.class),
                "RedisSystemActor");

        System.out.println("Started Redis Actor System");
        return redisActor;
    }

    public static ActorRef  startGuavaActorSystem() {

        final ActorSystem guavaSystem = ActorSystem.create("GuavaActorSystem",
                ConfigFactory.load("common"));

        final ActorRef guavaActor = guavaSystem.actorOf(Props.create(GuavaSystemActor.class),
                "GuavaSystemActor");

        System.out.println("Started Guava Actor System");
        return guavaActor;
    }


}

