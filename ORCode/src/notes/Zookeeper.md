# Zookeeper

## 工作机制：

​		Zookeeper从设计模式角度来理解：是一个基

于观察者模式设计的分布式服务管理框架，它负责

存储和管理大家都关心的数据，然后接受观察者的

注册，一旦这些数据的状态发生变化，Zookeeper

就将负责通知已经在Zookeeper上注册的那些观察

者做出相应的反应

![image-20220216140847772](C:\Users\86151\Desktop\zcc\notes\pic\zookeeper工作机制.png)

## 特点：

1）Zookeeper：一个领导者（Leader），多个跟随者（Follower）组成的集群。 

2）集群中只要有**半数以上**节点存活，Zookeeper集群就能正常服务。所以Zookeeper适合安装奇数台服务器。 

3）全局数据一致：每个Server保存一份相同的数据副本，Client无论连接到哪个Server，数据都是一致的。 

4）更新请求顺序执行，来自同一个Client的更新请求按其发送顺序依次执行。 

5）数据更新原子性，一次数据更新要么成功，要么失败。 

6）实时性，在一定时间范围内，Client能读到最新数据。 

![image-20220216141033913](C:\Users\86151\Desktop\zcc\notes\pic\zookeeper特点.png)

## 数据结构

ZooKeeper 数据模型的结构与 Unix 文件系统很类似，整体上可以看作是一棵树，每个节点称做一个 ZNode。每一个 ZNode 默认能够存储 1MB 的数据，每个 ZNode 都可以通过其路径唯一标识。

![image-20220216141647849](C:\Users\86151\Desktop\zcc\notes\pic\zookeeper数据结构.png)

## 应用场景

提供的服务包括：统一命名服务、统一配置管理、统一集群管理、服务器节点动态上下线、软负载均衡等

### 统一命名服务：

在分布式环境下，经常需要对应用/服务进行统一命名，便于识别。

例如：IP不容易记住，而域名容易记住。

![image-20220216141915000](C:\Users\86151\Desktop\zcc\notes\pic\统一命名服务.png)

### 统一配置管理

1）分布式环境下，配置文件同步非常常见

​	（1）一般要求一个集群，所有的配置信息是一致的，比如Kafka集群；

​	（2）对配置文件修改后，希望能够快速同步到各个节点上。

2）配置管理可交由zookeeper实现

​	（1）可将配置信息写入Zookeeper上的一个Znode；

​	（2）更客户端服务器监听这个Znode；

​	（3）一旦Znode中的数据被修改，Zookeeper将通知各个客户端服务器。

![image-20220216142437258](C:\Users\86151\Desktop\zcc\notes\pic\统一配置管理.png)

### 统一集群管理

1）分布式环境当中，实时掌握每个节点的状态是必要的

​	（1）可根据节点实时状态做出一些调整

2）Zookeeper可以实现监控节点状态变化

​	（1）可将节点信息写入Zookeeper上的一个ZNode

​	（2）监听这个ZNode可获取它的实时状态变化

![image-20220216142844753](C:\Users\86151\Desktop\zcc\notes\pic\统一集群管理.png)

### 服务器节点动态上下线

![image-20220216142947922](C:\Users\86151\Desktop\zcc\notes\pic\动态上下线.png)

### 软负载均衡

在Zookeeper中记录每台服务器的访问数，让访问数最少的服务器去处理最新的客户端请求。

![image-20220216143121879](C:\Users\86151\Desktop\zcc\notes\pic\软负载均衡.png)

## **Zookeeper** **本地安装**

### **安装前准备**

#### 1）安装 JDK

1.下载linux jdk8的tar包上传至服务器

​	使用xftp将jdk源码包，上传到/usr/local（软件一般安装到这个目录）

2.解压tar包

```shell
tar -zxvf jdk-8u271-linux-x64.tar.gz
```

删除tar包

```shell
rm -f jdk-8u181-linux-x64.tar.gz
```

3.配置环境变量

/etc/profile文件的改变会涉及到系统的环境，也就是有关Linux环境变量的东西

所以，我们要将jdk配置到/etc/profile，才可以在任何一个目录访问jdk

```shell
vim /etc/profile
```

在profile文件末尾添加如下配置：

```properties
export JAVA_HOME=/usr/local/jdk1.8.0_217  #jdk安装目录
 
export JRE_HOME=${JAVA_HOME}/jre
 
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
 
export JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
 
export PATH=$PATH:${JAVA_PATH}
```

保存并退出

4.验证是否安装成功

javac

![image-20220217112014657](C:\Users\86151\Desktop\zcc\notes\验证javac命令.png)

java -version

![image-20220217112045460](C:\Users\86151\Desktop\zcc\notes\验证java版本.png)

至此安装配置jdk8结束

#### 2）拷贝 Zookeepertar包到服务器

拷贝 apache-zookeeper-3.5.7-bin.tar.gz 安装包到 Linux 系统下

#### 3）指定解压目录

```shell
tar -zxvf apache-zookeeper-3.5.7-bin.tar.gz -C /opt/module/
```

若opt目录下没有则先新建module文件夹

```shell
mkdir /opt/module
```

#### 4）修改名称

```shell
mv apache-zookeeper-3.5.7-bin zookeeper-3.5.7
```

### 配置修改

（1）将/opt/module/zookeeper-3.5.7/conf 这个路径下的 zoo_sample.cfg 修改为 zoo.cfg；

```shell
 mv zoo_sample.cfg zoo.cfg
```

（2）打开 zoo.cfg 文件，修改 dataDir 路径

```shell
 vim zoo.cfg
```

修改如下内容：

```shell
dataDir=/opt/module/zookeeper-3.5.7/zkData
```

（3）在/opt/module/zookeeper-3.5.7/这个目录上创建 zkData 文件夹

```shell
 mkdir zkData
```

### 操作Zookeeper

#### 1.启动Zookeeper

```shell
 bin/zkServer.sh start
```

#### 2.查看进程是否启动

```shell
 jps -l
```

#### 3.查看状态

```shell
 bin/zkServer.sh status
```

#### 4.启动客户端

```shell
 bin/zkCli.sh
```

#### 5.退出客户端

```
 quit
```

#### 6.停止Zookeeper

```shell
 bin/zkServer.sh stop
```

### **配置参数解读**

Zookeeper中的配置文件zoo.cfg中参数含义解读如下： 

**1）**tickTime = 2000**：通信心跳时间，****Zookeeper****服务器与客户端心跳时间，单位毫秒**

![image-20220218091256934](C:\Users\86151\Desktop\zcc\notes\zookeeper心跳时间)

**2****）**initLimit = 10**：**LF初始通信时限**

![image-20220218091449480](C:\Users\86151\Desktop\zcc\notes\初始通信时限.png)

Leader和Follower初始连接时能容忍的最多心跳数（tickTime的数量）

**3**）**syncLimit = 5**：**LF同步通信时限**

![image-20220218091557276](C:\Users\86151\Desktop\zcc\notes\同步通信)

Leader和Follower之间通信时间如果超过syncLimit * tickTime，Leader认为Follwer死

掉，从服务器列表中删除Follwer。 

**4**）**dataDir**：**保存Zookeeper中的数据**

注意：默认的tmp目录，容易被Linux系统定期删除，所以一般不用默认的tmp目录。 

**5**）**clientPort = 2181**：**客户端连接端口，通常不做修改。**

## 集群操作

### **选举机制**（面试必问）

假定Zookeeper集群有五台服务器：



#### 第一次启动：

![image-20220218135321350](C:\Users\86151\Desktop\zcc\notes\选举机制-第一次.png)

1）服务器1启动，发起第一次选举，投自己一票。此时服务器1票数为1，不够半数以上（3票），选举无法完成，服务器状态保持LOOKING；

2）服务器2启动，再发起一次选举。服务器1和2分别投自己一票并交换选票信息：此时服务器1发现服务器2的myid比自己目前投票选举的（服务器1）大，更改选票为推举服务器2.此时服务器1票数0票，服务器2票数为2，没有板书以上结果，选举无法完成，2状态保持LOOKING；

3）服务器3启动，发起一次选举，1和2都会将选票更改为服务器3.此次投票结果：1为0票，2为0票，3为3票超过半数，服务器3当选Leader。1和2状态改为FOLLOWING，服务器3改为LEADING；

4）服务器4启动，发起一次选举。此时1,2,3已经不是LOOKING状态，不会更改选票信息。交换选票信息结果3为3票，4为1票。此时服务器4服从多数，更改选票信息为服务器3，并更改状态为FOLLOWING；

5）服务器5同4一致；

**SID**：服务器ID。用来唯一标识一台Zookeeper集群中的机器，每台机器不能重复，和myid一致。

**ZXID**：事务ID。ZXID是一个事务id，用来表示一次服务器状态的变更。在某一时刻，集群中的每台机器的ZXID值不一定完全一致，这和Zookeeper服务器对于客户端“更新请求”的处理逻辑有关。

**Epoch**：每个leader任期的代号。没有Leader时同一轮投票过程中的逻辑时钟值是相同的。每投完一次票这个数据就会增加。

#### 非第一次启动：

![image-20220218140107797](C:\Users\86151\Desktop\zcc\notes\非第一次启动.png)

1）当Zookeeper集群中的一台服务器出现以下两种情况之一时，就会开始进入Leader选举：

​	（1）服务器初始化启动；

​	（2）服务器运行期间无法和Leader保持连接。

2）而当一台机器进入Leader选举流程时，当前集群也可能会处于以下两种状态：

​	（1）集群中本来就已经存在一个Leader

​				对于第一种已经存在Leader的情况，机器试图去选举Leader时，会被告知当前服务器的信息，对于该机器来说仅仅需要和			Leader机器	建立连接，并进行状态同步即可。

​	（2）集群中确实不存在Leader

​		例：

![image-20220218141421727](C:\Users\86151\Desktop\zcc\notes\非第一次选举demo.png)

​				选举Leader规则：

​									1.EPOCH大的直接胜出；

​									2.EPOCH相同，事务ID（ZXID）大的胜出；

​									3.事务ID相同，服务器ID（SID）大的胜出；

### 集群启动停止脚本

#### 1.编写脚本 

```shell
vim zk.sh
```

将以下内容贴到zk.sh

```shell
#!/bin/bash
case $1 in
"start"){
for i in hadoop102 hadoop103 hadoop104
do
 echo ---------- zookeeper $i 启动 ------------
ssh $i "/opt/module/zookeeper-3.5.7/bin/zkServer.sh 
start"
done
};;
"stop"){
for i in hadoop102 hadoop103 hadoop104
do
 echo ---------- zookeeper $i 停止 ------------ 
ssh $i "/opt/module/zookeeper-3.5.7/bin/zkServer.sh 
stop"
done
};;
"status"){
for i in hadoop102 hadoop103 hadoop104
do
 echo ---------- zookeeper $i 状态 ------------ 
ssh $i "/opt/module/zookeeper-3.5.7/bin/zkServer.sh 
status"
done
};;
esac
```

#### 2.增加脚本执行权限

```shell
 chmod u+x zk.sh
```

#### 3.Zookeeper 集群启动脚本

```shell
zk.sh start
```

#### 4.Zookeeper 集群停止脚本

```shell
 zk.sh stop
```

### 客户端命令行操作

#### 1.命令行语法

<table>
    <tr>
        <td style="text-align:center">命令行基本语法</td>
        <td style="text-align:center">功能描述</td>
    </tr>
    <tr>
        <td style="text-align:center">help</td>
        <td style="text-align:center">显示所有操作命令</td>
    </tr>
    <tr>
          <td style="text-align:center">ls path</td>
        <td style="text-align:center">使用ls命令查看当前znode的子节点【可监听】<br>-w  监听子节点变化<br>-s  附加次级信息</td>
    </tr>
    <tr>
          <td style="text-align:center">create</td>
        <td style="text-align:center">普通创建<br>-s  含有序列<br>-e  临时（重启或者超时消失）</td>
    </tr>
    <tr>
         <td style="text-align:center">get path</td>
        <td style="text-align:center">获取节点的值【可监听】<br>-w  监听节点内容变化<br>-s  附加次级节点信息</td>
    </tr>
    <tr>
           <td style="text-align:center">set</td>
        <td style="text-align:center">设置节点的具体值</td>
    </tr>
    <tr>
          <td style="text-align:center">stat</td>
        <td style="text-align:center">查看节点状态</td>
    </tr>
    <tr>
          <td style="text-align:center">delete</td>
        <td style="text-align:center">删除节点</td>
    </tr>
    <tr>
          <td style="text-align:center">deleteall</td>
        <td style="text-align:center">递归删除节点</td>
    </tr>
</table>



1）启动客户端

```shell
bin/zkCli.sh -server clientName:2181
```

2)显示所有操作命令

```shell
help
```

####  2.znode 节点数据信息

**1**）**查看当前znode中所包含的内容**

```shell
[zk: hadoop102:2181(CONNECTED) 0] ls /

[zookeeper]
```



**2**）**查看当前节点详细数据**

```shell
[zk: hadoop102:2181(CONNECTED) 5] ls -s /

[zookeeper]cZxid = 0x0

ctime = Thu Jan 01 08:00:00 CST 1970

mZxid = 0x0

mtime = Thu Jan 01 08:00:00 CST 1970

pZxid = 0x0

cversion = -1

dataVersion = 0

aclVersion = 0

ephemeralOwner = 0x0

dataLength = 0

numChildren = 1
```

（1）czxid：创建节点的事务 zxid

每次修改 ZooKeeper 状态都会产生一个 ZooKeeper 事务 ID。事务 ID 是 ZooKeeper 中所

有修改总的次序。每次修改都有唯一的 zxid，如果 zxid1 小于 zxid2，那么 zxid1 在 zxid2 之

前发生。

（2）ctime：znode 被创建的毫秒数（从 1970 年开始）

（3）mzxid：znode 最后更新的事务 zxid

（4）mtime：znode 最后修改的毫秒数（从 1970 年开始）

（5）pZxid：znode 最后更新的子节点 zxid

（6）cversion：znode 子节点变化号，znode 子节点修改次数

（7）dataversion：znode 数据变化号

（8）aclVersion：znode 访问控制列表的变化号

（9）ephemeralOwner：如果是临时节点，这个是 znode 拥有者的 session id。如果不是

临时节点则是 0。 

（10）dataLength：znode 的数据长度

（11）numChildren：znode 子节点数量

#### 3.节点类型（持久/短暂/有序号/无序号）

![image-20220218150851219](C:\Users\86151\Desktop\zcc\notes\节点类型.png)

持久（Persistent）：客户端和服务器断开连接后，创建的节点不删除；

短暂（Ephemeral）:客户端和服务器断开连接后，创建的节点自己删除；

**PS**：在分布式系统中，顺序号可以被用于为所有的事件进行全局排序，这样客户端可以通过顺序号推断事件的顺序

1）持久化目录节点

客户端和Zookeeper断开连接后，该节点依旧存在

2）持久化顺序编号目录节点

客户端和Zookeeoer断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号。

3）临时目录节点

客户端与Zookeeper断开连接之后，该节点删除

4）临时顺序编号目录节点

客户端与Zookeeper断开连接后，该节点被删除，只是Zookeeper该节点名称进行顺序编号。

### 监听器原理

#### 1）监听原理详解

1.首先要有一个main（）线程。

2.在main线程中创建Zookeeper客户端，这是就会创建两个线程，一个负责网络连接通信（connet），一个负责监听（listener）。

3.通过connect线程将注册的监听事件发送给zookeeper。

4.在Zookeeper的注册监听器列表中将注册的监听事件添加到列表中。

5.Zookeeper监听到有数据或路径的变化，就会将这个消息发送给listener线程。

6.listener线程内部调用了process（）方法。

#### 2）常见的监听

1. 监听节点数据的变化

   get path [watch]

   2.监听子节点增减的变化

​		ls path [watch]

监听节点值的变化 ： get -w + nodePath （注册一次监听）

监听子节点增减变化：	ls -w path +nodePath

**想多次监听就要多次注册**

删除节点： 

​	单个：delete path

​	多级：deleteall path



### 写数据原理

#### 写入请求发送给leader节点

![image-20220222110602371](C:\Users\86151\Desktop\zcc\notes\pic\写流程-写入请求发给leader.png)



客户端发送写入请求到leader节点，leader发送写入命令通知followers，一旦超过半数就会返回给客户端一个ack码，告知客户端写入结果。

#### 写入请求发送给follower节点

![image-20220222110747565](C:\Users\86151\Desktop\zcc\notes\pic\写入请求发给follower.png)

客户端发送写入请求到follower，follower转发请求到leader，leafer发送写入命令到followers，一旦超过半数的follower返回ack相应码，leader发送一个ack到与客户端建立联系的follower，该follower则会发送相应ack到客户端，告知写入结果。leader继续向其他未发送写入请求的follower发送写入命令，follower完成向leader发送相应ack

## 服务器动态上下线

### 需求分析

![image-20220222112013101](C:\Users\86151\Desktop\zcc\notes\pic\服务器动态上下线.png)

### 具体实现

1.创建永久根目录节点services

2.通过代码实现服务上下线

```java
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
 * @Describe:客户端
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
```

```java
package com.zcc.case1;

import org.apache.zookeeper.*;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case1
 * @author: zcc
 * @date: 2022/2/22 11:25
 * @version:
 * @Describe:服务端
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
```

## Zookeeper分布式锁案例

### 什么叫分布式锁

​		比如说"进程 1"在使用该资源的时候，会先去获得锁，"进程 1"获得锁以后会对该资源保持独占，这样其他进程就无法访问该资源，"进程 1"用完该资源以后就将锁释放掉，让其他进程来获得锁，那么通过这个锁机制，我们就能保证了分布式系统中多个进程能够有序的访问该临界资源。那么我们把这个分布式环境下的这个锁叫作分布式锁

![image-20220222153444011](C:\Users\86151\Desktop\zcc\notes\pic\分布式锁实现案例.png)

#### 原生实现案例

```java
package com.zcc.case2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case2
 * @author: zcc
 * @date: 2022/2/22 15:39
 * @version:
 * @Describe:
 */

public class DistributedLock {
    // zookeeper server 列表
    private String connectString =
            "139.9.94.102:2181,139.9.94.102:2182,139.9.94.102:2183";
    // 超时时间
    private int sessionTimeout = 2000;
    private ZooKeeper zk;
    private String rootNode = "locks";
    private String subNode = "seq-";
    // 当前 client 等待的子节点
    private String waitPath;
    //ZooKeeper 连接
    private CountDownLatch connectLatch = new CountDownLatch(1);
    //ZooKeeper 节点等待
    private CountDownLatch waitLatch = new CountDownLatch(1);
    // 当前 client 创建的子节点
    private String currentNode;

    // 和 zk 服务建立连接，并创建根节点
    public DistributedLock() throws IOException,
            InterruptedException, KeeperException {
        zk = new ZooKeeper(connectString, sessionTimeout, new
                Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        // 连接建立时, 打开 latch, 唤醒 wait 在该 latch 上的线程
                        if (event.getState() ==
                                Event.KeeperState.SyncConnected) {
                            connectLatch.countDown();
                        }
                        // 发生了 waitPath 的删除事件
                        if (event.getType() ==
                                Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
                            waitLatch.countDown();
                        }
                    }
                });
        // 等待连接建立
        connectLatch.await();
        //获取根节点状态
        Stat stat = zk.exists("/" + rootNode, false);
        //如果根节点不存在，则创建根节点，根节点类型为永久节点
        if (stat == null) {
            System.out.println("根节点不存在");
            zk.create("/" + rootNode, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    // 加锁方法
    public void zkLock() {
        try {
            //在根节点下创建临时顺序节点，返回值为创建的节点路径
            currentNode = zk.create("/" + rootNode + "/" + subNode,
                    null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            // wait 一小会, 让结果更清晰一些
            Thread.sleep(10);
            // 注意, 没有必要监听"/locks"的子节点的变化情况
            List<String> childrenNodes = zk.getChildren("/" +
                    rootNode, false);
            // 列表中只有一个子节点, 那肯定就是 currentNode , 说明client 获得锁
            if (childrenNodes.size() == 1) {
                return;
            } else {
                //对根节点下的所有临时顺序节点进行从小到大排序
                Collections.sort(childrenNodes);
                //当前节点名称
                String thisNode = currentNode.substring(("/" +
                        rootNode + "/").length());
                //获取当前节点的位置
                int index = childrenNodes.indexOf(thisNode);
                if (index == -1) {
                    System.out.println("数据异常");
                } else if (index == 0) {
                    // index == 0, 说明 thisNode 在列表中最小, 当前client 获得锁
                    return;
                } else {
                    // 获得排名比 currentNode 前 1 位的节点
                    this.waitPath = "/" + rootNode + "/" +
                            childrenNodes.get(index - 1);
                    // 在 waitPath 上注册监听器, 当 waitPath 被删除时,zookeeper 会回调监听器的 process 方法
                    zk.getData(waitPath, true, new Stat());
                    //进入等待锁状态
                    waitLatch.await();
                    return;
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 解锁方法
    public void zkUnlock() {
        try {
            zk.delete(this.currentNode, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}


```

测试类

```java
package com.zcc.case2;

import java.util.concurrent.CompletableFuture;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case2
 * @author: zcc
 * @date: 2022/2/23 9:28
 * @version:
 * @Describe:
 */
public class DistributeLockTest {

    public static void main(String[] args) throws Exception {
        final DistributedLock lock1 = new DistributedLock();
        final DistributedLock lock2 = new DistributedLock();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.zkLock();
                    System.out.println("线程1 启动，获取到锁");
                    Thread.sleep(5 * 1000);
                    lock1.zkUnlock();
                    System.out.println("线程1 释放锁");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.zkLock();
                    System.out.println("线程2 启动，获取到锁");
                    Thread.sleep(5 * 1000);
                    lock2.zkUnlock();
                    System.out.println("线程2 释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

```

#### Curator框架实现分布式锁案例

##### 1.原生Java API开发存在的问题

​	1）会话连接是异步的，需要自己去处理。比如使用CountDownLatch

​	2）Watch需要重复注册，不然不能生效

​	3）开发的复杂性还是比较高的

​	4）不支持多节点删除和创建。需要自己去递归

##### 2.Curator是一个专门解决分布式锁的框架，解决了原生Java API开发分布式遇到的问题

​	详情见官网：https://curator.apache.org/index.html

##### 3.Curator案例实操

​	1）添加依赖

```xml
<dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>4.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>4.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-client</artifactId>
            <version>4.3.0</version>
        </dependency>
```

​	2）代码实现

```java
package com.zcc.case3;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.*;

/**
 * @ProjectName: zookeeper
 * @ClassName: com.zcc.case3
 * @author: zcc
 * @date: 2022/2/23 10:22
 * @version:
 * @Describe:
 */
public class CuratorLockTest {

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 10, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        // 创建分布式锁1
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), "/locks");

        // 创建分布式锁2
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), "/locks");

        executorService.execute(()->{
            try {
                lock1.acquire();
                System.out.println("线程1 获取到锁");
                lock1.acquire();
                System.out.println("线程1 再次获取到锁");
				// 业务
                Thread.sleep(5000);

                lock1.release();
                System.out.println("线程1 释放锁");
                lock1.release();
                System.out.println("线程1 再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        });
        executorService.execute(()->{
            try {
                lock2.acquire();
                System.out.println("线程2 获取到锁");
                lock2.acquire();
                System.out.println("线程2 再次获取到锁");
				// 业务
                Thread.sleep(5000);

                lock2.release();
                System.out.println("线程2 释放锁");

                lock2.release();
                System.out.println("线程2 再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        });
        executorService.shutdown();

    }

    private static CuratorFramework getCuratorFramework() {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("139.9.94.102:2181,139.9.94.102:2182,139.9.94.102:2183")
                .connectionTimeoutMs(2000)
                .sessionTimeoutMs(2000)
                .retryPolicy(exponentialBackoffRetry)
                .build();

        // 启动客户端
        client.start();

        System.out.println("zookeeper 启动成功");

        return client;
    }
}
```

