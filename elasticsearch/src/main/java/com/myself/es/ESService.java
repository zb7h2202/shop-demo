package com.myself.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ESService {



    @Autowired
    private ElasticsearchClient client;

    public void createIndex(String index) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder().index(index).build();
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest);
        System.out.println("是否成功：" + createIndexResponse.acknowledged());
    }

    public void getIndexById(String index,String id) throws IOException {
        GetRequest getRequest = new GetRequest.Builder().index(index)
                .id(id)
                .build();
        GetResponse<Producter> response = client.get(getRequest, Producter.class);
        if (response.found()) {
            Producter product = response.source();
            System.out.println("Product name " + product.getNumber());
            System.out.println("Product price " + product.getPrice());
        } else {
            System.out.println("Product not found");
        }
    }


    public void bulkIndex(String index) throws IOException{
        List<Producter> products = new ArrayList<Producter>();
        products.add(new Producter("香烟",135,1));
        products.add(new Producter("瓜子",154,2));
        products.add(new Producter("矿泉水",613,3));
        products.add(new Producter("酱油",72,4));
        products.add(new Producter("大米",771,5));
        BulkRequest.Builder bk = new BulkRequest.Builder();
        int indexId = 4;
        for (Producter product:products) {
            bk.operations(op->op.index(i->i.index(index)
                    .id(UUID.randomUUID().toString())
                    .document(product)));
        }
        BulkResponse response = client.bulk(bk.build());
        if (response.errors()) {
            System.out.println("Bulk had errors");
            for (BulkResponseItem item: response.items()) {
                if (item.error() != null) {
                    System.out.println(item.error().reason());
                }
            }
        }
    }



}
