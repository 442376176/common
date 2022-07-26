package com.zcc.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.es
 * @author: zcc
 * @date: 2022/7/25 14:35
 * @version:
 * @Describe:
 */
public class ES_Doc_Get {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 查询数据
        GetRequest getRequest = new GetRequest();
        getRequest.index("user").id("1001");
        GetResponse documentFields = esClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSource());
        System.out.println(documentFields.getSourceAsString());
        System.out.println(documentFields);
        esClient.close();
    }
}
