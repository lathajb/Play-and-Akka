package utilities;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by janlatha on 6/13/2018.
 */
public class ElasticSearchClient {

    TransportClient client = null;
    public Client newClient(String hostName, String clusterName , int port, long timeout) throws Exception {
        if ( null != client ) {
            return client;
        }
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        String t = ( timeout / 1000 ) + "s";
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.ping_timeout", t)
                .build();
        client = new PreBuiltTransportClient(settings);

        client.addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), port));
        return client;
    }

}
