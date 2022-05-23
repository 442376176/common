package com.zcc.case1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case1
 * @author: zcc
 * @date: 2022/2/22 11:38
 * @version:
 * @Describe:
 */
public class DistributeClient {
    private String connectString = "139.9.94.102:2181,139.9.94.102:2182,139.9.94.102:2183";
    private int sessionTimeout = 2000;
    private ZooKeeper zk;
    private String rootNode = "/servers";
    private void getConnect() throws Exception {

        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    getServerList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) throws Exception{
        // 1. 获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnect();
        // 2. 监听节点/servers的子节点变化
        client.getServerList();
        // 3.业务逻辑
        client.business();
    }

    private void getServerList() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(rootNode, true);

        ArrayList<String> servers = new ArrayList<>();
        for (String child : children) {
            System.out.println(child+"  is online");
//            byte[] data = zk.getData(rootNode + "/" + child, false, null);
//            servers.add(new String(data));
        }
        System.out.println(servers);
    }
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

}
