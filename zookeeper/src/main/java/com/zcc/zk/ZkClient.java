package com.zcc.zk;

import io.netty.util.concurrent.FastThreadLocalThread;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.zk
 * @author: zcc
 * @date: 2022/2/22 9:54
 * @version:
 * @Describe:
 */
public class ZkClient {
    // 注意逗号左右不能有空格
    private String connectString = "139.9.94.102:2181,139.9.94.102:2182,139.9.94.102:2183";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;
    private List<String> children;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("-----------------------------------------");
                try {
                    children = zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String child : children) {
                    System.out.println(child);
                }
                System.out.println("-----------------------------------------");
            }
        });
    }


    @Test
    public void create() throws Exception {
        String nodeCreate = zkClient.create("/alibaba", "taobao.com".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);

        children.forEach(item -> System.out.println(item));

        // 延时
        Thread.sleep(30000);

    }

    @Test
    public void isExist() throws Exception {
        Stat sg = zkClient.exists("/alibaba", false);
        Stat zk = zkClient.exists("/zookeeper", false);
        System.out.println(sg);
        System.out.println(zk);
    }

}
