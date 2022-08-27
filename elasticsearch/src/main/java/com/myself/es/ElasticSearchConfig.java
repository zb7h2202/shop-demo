package com.myself.es;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private int port;

//
//    @Resource
//    EsConfig esConfig;


    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        ElasticsearchTransport elasticsearchTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(elasticsearchTransport);
        return elasticsearchClient;

    }


    @Bean
    public ElasticsearchAsyncClient elasticsearchAsyncClient() {
        RestClient restClient = RestClient.builder(new HttpHost(host, port)).build();
        ElasticsearchTransport elasticsearchTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        ElasticsearchAsyncClient elasticsearchAsyncClient = new ElasticsearchAsyncClient(elasticsearchTransport);
        return elasticsearchAsyncClient;

    }
}
