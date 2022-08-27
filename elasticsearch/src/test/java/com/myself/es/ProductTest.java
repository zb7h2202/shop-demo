//package com.myself.es;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//
//
//@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ProductTest {
//
//    @Autowired
//    private ElasticsearchClient client;
//
//
//    @Test
//    void createIndex() throws IOException {
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder().index("ouldindex").build();
//        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest);
//        System.out.println("是否成功：" + createIndexResponse.acknowledged());
//    }
//
//
//}