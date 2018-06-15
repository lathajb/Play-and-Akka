package utilities;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Product;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by janlatha on 6/12/2018.
 */
public class ElasticSearchUtility {

    public Product performQuery(Client esClient,Product product){

        BoolQueryBuilder qb = boolQuery().must(termQuery("id", product.getId()));

        SearchResponse response = esClient.prepareSearch("products").setTypes("product").setQuery(qb).execute().actionGet();

        System.out.println(response);

        if(response.getHits().getHits().length > 0){
            String responseStr = response.getHits().getHits()[0].getSourceAsString();
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            product = gson.fromJson(responseStr,Product.class);
        }else {
            product = null;
        }
        return product;
    }

    /**
     * This method Create the Index and insert the document(s)
     */
    public void CreateDocument(Client client,Product product) {

        try {

            IndexResponse response = client.prepareIndex("products", "product", "prd"+product.getId())
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("id", product.getId())
                            .field("name", product.getName())
                            .endObject()
                    ).get();
            if (response != null) {
                String _index = response.getIndex();
                String _type = response.getType();
                String _id = response.getId();
                long _version = response.getVersion();
                RestStatus status = response.status();
                System.out.println("Index has been created successfully with Index: " + _index + " / Type: " + _type + "ID: " + _id);

            }
        } catch (IOException ex) {
            System.out.println("Exception occurred while Insert Index : " + ex);

        }
    }



}
