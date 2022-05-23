package com.zcc.case1;

import org.apache.zookeeper.*;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case1
 * @author: zcc
 * @date: 2022/2/22 11:25
 * @version:
 * @Describe:
 */
public class DistributeServer {

    private String connectString = "139.9.94.102:2181,139.9.94.102:2182,139.9.94.102:2183";
    private int sessionTimeout = 2000;
    private ZooKeeper zk;


    public static void main(String[] args) throws Exception{
        // 1.获取zk连接

        DistributeServer distributeServer = new DistributeServer();
        distributeServer.getConnect();

        // 2.注册服务器到zk集群
        distributeServer.register(args[0]);

        // 3.启动业务逻辑
        distributeServer.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void register(String hostName) throws Exception{
        zk.create("/servers/"+hostName,hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostName+": ------------------服务器上线");
    }

    private void getConnect() throws Exception {

        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

}

