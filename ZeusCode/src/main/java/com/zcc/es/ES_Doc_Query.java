package com.zcc.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;


/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.es
 * @author: zcc
 * @date: 2022/7/25 14:35
 * @version:
 * @Describe:
 */
public class ES_Doc_Query {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 全量查询
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(query);
//
//
//        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(search.getAggregations());
//        System.out.println(search.getHits().getTotalHits());
//        for (SearchHit hit : search.getHits()) {
//            System.out.println(hit.getSourceAsString());
//        }


        // 条件查询 : termQuery
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.termQuery("age","82"));
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(query);
//
//
//        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(search.getAggregations());
//        System.out.println(search.getHits().getTotalHits());
//        for (SearchHit hit : search.getHits()) {
//            System.out.println(hit.getSourceAsString());
//        }
        // 分页查询
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        // 分页参数
//        query.from(1).size(10);
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(query);
//
//
//        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(search.getAggregations());
//        System.out.println(search.getHits().getTotalHits());
//        for (SearchHit hit : search.getHits()) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 查询排序
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        // 分页参数
//        query.from(9).size(10).sort("age", SortOrder.ASC);
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(query);
//
//
//        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(search.getAggregations());
//        System.out.println(search.getHits().getTotalHits());
//        for (SearchHit hit : search.getHits()) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 过滤字段
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        // 查询 字段 排除字段
//        String[] includes = {},excludes = {"age"};
//        query.fetchSource(includes,excludes).from(9).size(10).sort("age", SortOrder.ASC);
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(query);
//
//
//        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(search.getAggregations());
//        System.out.println(search.getHits().getTotalHits());
//        for (SearchHit hit : search.getHits()) {
//            System.out.println(hit.getSourceAsString());
//        }
//        // 组合查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
////        boolQueryBuilder.must(QueryBuilders.matchQuery("age",100));
//        boolQueryBuilder.must(QueryBuilders.matchQuery("sex","男"));
//        SearchSourceBuilder query = new SearchSourceBuilder().query(boolQueryBuilder);
//        // 查询字段 and 排除字段
//        String[] includes = {"name","age","sex"},excludes = {};
//        query.fetchSource(includes,excludes).from(9).size(10).sort("age", SortOrder.ASC);
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(query);
//
//
//        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(search.getAggregations());
//        System.out.println(search.getHits().getTotalHits());
//        for (SearchHit hit : search.getHits()) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 范围查询
        RangeQueryBuilder age = QueryBuilders.rangeQuery("age");

        age.gte(22).lte(30);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(age);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        searchRequest.source(searchSourceBuilder);


        SearchResponse search = esClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search.getAggregations());
        System.out.println(search.getHits().getTotalHits());
        for (SearchHit hit : search.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        esClient.close();
    }
}
