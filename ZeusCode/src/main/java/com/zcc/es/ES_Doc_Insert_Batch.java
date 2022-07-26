package com.zcc.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.es
 * @author: zcc
 * @date: 2022/7/25 14:35
 * @version:
 * @Describe:
 */
public class ES_Doc_Insert_Batch {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        BulkRequest bulkRequest = new BulkRequest();
        // 插入数据
        for (int i = 0; i < 6; i++) {
            IndexRequest request = new IndexRequest();
            request.index("user").id(String.valueOf(100+i));
            User user = new User();
            user.setName("lisi"+i);
            user.setAge(20+i);
            user.setSex("男");
            ObjectMapper mapper = new ObjectMapper();
            String userJson = mapper.writeValueAsString(user);
            request.source(userJson, XContentType.JSON);

            bulkRequest.add(request);
        }

        BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.buildFailureMessage());
        System.out.println(bulk.getTook());
        System.out.println(bulk.hasFailures());
        System.out.println(bulk);
        esClient.close();
    }
}
