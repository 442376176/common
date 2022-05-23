# Spring **cloud**

![image-20220210113206406](C:\Users\86151\Desktop\zcc\notes\pic\image-20220210113206406.png)![image-20220216094644189](C:\Users\86151\Desktop\zcc\notes\pic\技术.png)

![image-20220216094528015](C:\Users\86151\Desktop\zcc\notes\pic\相关技术.png)



![image-20220216094853127](C:\Users\86151\Desktop\zcc\notes\pic\cloud相关技术.png)

## 是什么？

分布式微服务架构的一站式解决方案，是多种微服务架构落地技术的集合体，俗称微服务全家桶。



版本选择：

springboot 2.0以后版本

springcloud H以后版本

## 什么是服务治理？

 spring cloud 封装了Netflix 公司开发的Eureka模块来实现服务治理

在传统的rpc远程调用框架中，管理每个服务于服务之间的依赖关系比较复杂，管理比较复杂，所以需要服务治理，管理服务与服务之间的依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。

## 什么是服务注册与发现？

Eureka采用cs的设计架构，Eureka Server 作为服务注册功能的服务器，他是服务注册中心。而系统的其他微服务，使用Eureka的客户端连接到Eureka Server并维持心跳连接。这样系统的维护人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行。

在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息 比如 服务地址通讯地址等以别名方式注册到注册中心上。另一方（消费者|服务提供者），以别名的方式去注册中心上获取到实际的服务通讯地址，然后在实现本地RPC调用。RPC远程调用框架核心设计思想：在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系（服务治理概念）。在任何rpc远程调用框架中，都会有一个注册中心（存放服务地址相关信息（接口地址））

![eureka与dubbo](C:\Users\86151\Desktop\zcc\notes\eureka与dubbo.png)

服务注册中心为了避免单点故障一般都是集群形式



### Eureka包含两个组件

Eureka Server 和 Eureka Client

Eureka Server的配置文件

```yaml
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com #eureka服务端的实例名称
  client:
    # false表示不向注册中心注册自己
    register-with-eureka: false
    # false表示自己就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
#      defaultZone: http://eureka7002.com:7002/eureka/
      # 单机版
      defaultZone: http://eureka7001.com:7001/eureka/

  server:
    # 自我保护模式
    enable-self-preservation: false
    # 心跳周期
    eviction-interval-timer-in-ms: 2000
```

Eureka Client的配置文件

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/project?userUnicode=true&characterEncoding=utf-8&userSSL=false
    username: root
    password: 123456

eureka:
  client:
    #表示是否将自己注册进Eureka Server默认为true
    register-with-eureka: true
    #表示是否从Eureka Server抓取已有的注册信息 默认为true 单节点无所谓 集群必须设置为true才能配合ribbon使用负载均衡
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka/
#      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka #集群版
  instance:
    instance-id: payment8001
    prefer-ip-address: true #访问路径可以显示IP地址
    #Eureka Client向服务端发送心跳的时间间隔，单位为秒（默认30秒）
    lease-renewal-interval-in-seconds: 1
    #Eureka Server 在收到最后一次心跳后等待时间上限，单位为秒（默认是90秒），超时将剔除服务
    lease-expiration-duration-in-seconds: 2
mybatis:
  mapperLocations: classpath:mapper/*.xml #mapper.xml包路径
  type-aliases-package: com.zcc.springcloud.entities #实体类包路径
```

#### Eureka Server 提供服务注册服务

各个微服务节点通过配置启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到

####  Eureka Client  通过注册中心进行访问

是一个java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询（round-robin）负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳（默认周期为30秒）。如果Eureka Server 在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从注册表中把服务注册表中把这个服务节点移除（默认90秒）



服务注册：

将服务信息注册进注册中心

服务发现：

从注册中心上获取服务信息

实质：存key命令 取value地址



demo：

1.先启动eureka注册中心

2.启动服务提供者payment支付服务

3.支付服务启动后会把自身信息 注册进eureka

4.消费者order服务在需要调用接口是，使用服务别名去注册中心获取实际的RPC远程调用地址

5.消费者获得调用地址后，底层实际是利用HttpClient技术实现远程调用

6.消费者获得服务地址后会缓存在本地JVM内存中，默认每间隔30s刷新一次服务调用地址





#### 微服务RPC远程服务调用最核心的是什么？

 高可用，试想你的注册中心只有一个，如果仅有的一个注册中心出现故障，系统直接瘫痪

解决办法：搭建Eureka注册中心集群，实现负载均衡+故障容错

#### 服务发现Discovery

#### Eureka自我保护

保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护器服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务

默认情况下，如果Eureka Server在一定时间内没有接收到某个服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区发生故障时，微服务与Eureka Server无法正常通信，以上行为可能变得非常危险了--因为微服务本身其实是健康的，此时本不应该注销这个微服务。Eureka通过“自我保护模式”来解决这个问题--当Eureka Server节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。

在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。

它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话：好死不如赖活着

##### 1.故障现象

如果在Eureka Server首页看到以下这段提示，则说明Eureka进入了保护模式：

EMERGENCY！EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. 

RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE

##### 2.导致原因

总结：某时刻某一微服务不可用了，Eureka不会立即清理，依旧会对该微服务的信息进行保存

属于CAP里面的AP分支

为了防止Eureka Client可以正常运行，但是与Eureka Server网络通信出现问题，Eureka Server不会立刻将Eureka Client服务剔除

##### 3.怎么禁止自我保护

```properties
server:
  # 自我保护模式
  enable-self-preservation: false
  # 心跳周期
  eviction-interval-timer-in-ms: 2000
```



#### @EnableDiscoveryClient注解和@EnableEurekaClient

共同点就是：都是能够让注册中心能够发现，扫描到改服务。

不同点：`@`EnableEurekaClient只适用于Eureka作为注册中心，`@EnableDiscoveryClient` 可以是其他注册中心。

### Zookeeper 

zookeeper服务器取代Eureka服务器，zk作为服务注册中心

zookeeper详见

[zookeeper笔记]: C:\Users\86151\Desktop\zcc\notes\Zookeeper.md	"笔记"

zookeeper注册服务使用的是临时节点

服务下线 立即清空该znode

### Consul

#### 简介：

Consul是一套开源的分布式服务发现与配置管理系统，由HashiCorp公司使用Go语言开发。

提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方位的服务网络，总之Consul提供了一种完整的服务网格解决方案。

它具有很多优点。包括：基于raft协议，比较简洁；支持健康检查，同时支持HTTP和DNS协议，支持跨数据中心的WAN集群提供图形化界面跨平台，支持Linux、Mac、Windows

#### 功能：

服务发现  提供HTTP和DNS两种发现方式

健康监测  支持多种方式，HTTP、TCP、Docker、Shell脚本定制化

KV存储     key-value 键值对的存储方式

多数据中心  Consul支持多数据中心

可视化Web界面

<table>
    <tr>
    	<td>组件名</td>
        <td>语言</td>
        <td>CAP</td>
        <td>服务健康检查</td>
        <td>对外暴露接口</td>
        <td>Spring Cloud集成</td>
    </tr>
     <tr>
    	<td>Eureka</td>
        <td>Java</td>
        <td>AP</td>
        <td>可配支持</td>
        <td>HTTP</td>
        <td>已集成</td>
    </tr>
     <tr>
    	<td>Consul</td>
        <td>Go</td>
        <td>CP</td>
        <td>支持</td>
        <td>HTTP/DNS</td>
        <td>已集成</td>
    </tr> <tr>
    	<td>Zookeeper</td>
        <td>Java</td>
        <td>CP</td>
        <td>支持</td>
        <td>客户端</td>
        <td>已集成</td>
    </tr>
</table>

![image-20220223172748452](C:\Users\86151\Desktop\zcc\notes\pic\CAP官方架构图.png)

C:Consistency 强一致性

A:Availability 可用性

P:Partition tolerance 分区容错性

CAP理论关注粒度是数据，而不是整体系统设计的策略



AP架构：

当网络分区出现后，为了保证可用性，系统B可以返回旧值，保证系统可用性。

结论：违背了一致性Consistency的要求，只满足可用性和分区容错性，即AP

![image-20220224090951783](C:\Users\86151\Desktop\zcc\notes\pic\AP.png)

CP架构：

当网络分区出现后，为了保证一致性，就必须拒接请求，否则无法保证一致性

结论：违背了可用性Availability ，只满足一致性和分区容错，即CP

![image-20220224091156928](C:\Users\86151\Desktop\zcc\notes\pic\CP.png)

## Ribbon

### 简介：

Spring cloud Ribbon是基于NetFlix Ribbon实现的一套客户端 负载均衡的工具

简单的说，Ribbon是NetFlix发布的开源项目，主要功能是提供客户端的软件负载均衡算法和服务调用。Ribbon客户端组件提供一系列完善的配置项，比如：连接超时，重试等。大概意思就是在配置文件汇总列出Load Balance（简称LB）后面所有的机器，Ribbon会自动帮助使用者基于某种规则（简单轮询，随机连接等）去连接这些机器。使用者很容易使用Ribbon实现自定义的负载均衡算法。

### LB负载均衡（Load Balance）是什么？

简单的说就是将用户的请求平摊的分配到多个服务商，从而达到系统的HA（高可用）。常见的负载均衡有软件Nginx，LVS，硬件F5等。

Ribbon本地负载均衡客户端和Nginx服务端负载均衡区别

Nginx是服务器负载均衡，客户端所有请求都会交给Nginx，然后由Nginx实现转发请求。即负载均衡是由服务端实现的。

Ribbon本地负载均衡，在调用微服务接口的时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术。

#### 集中式LB：

即在服务的消费方和提供方之间使用独立的LB设施（可以是硬件如F5，也可以是软件如Nginx），有该设施负责把访问请求通过某种策略转发至服务的提供方；

#### 进程内LB：

将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后在从这些地址中选择出一个合适的服务器。

Ribbon就属于进程内LB，它只是一个类库，继承与消费方进程，消费方通过它来获取服务提供方的地址。



作用：负载均衡+RestTemplate调用

![image-20220224110802087](C:\Users\86151\Desktop\zcc\notes\pic\Ribbon负载均衡结构.png)

#### Ribbon在工作时分成两步：

第一步先选择EurekaServer，它优先选择在同一地区内负载较少的server。第二步在根据用户指定策略在server取到的服务注册列表中选择一个地址。其中Ribbon提供了多种策略：简单轮询，随机和根据响应时间加权等等。

### RestTemplate

#### getForObject方法

返回对象为响应体中数据转化成的对象，基本上可以理解为Json

#### getForEntity方法

返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等



### IRule

根据特定算法，从服务列表中选取一个要访问的服务

![image-20220224135152628](C:\Users\86151\Desktop\zcc\notes\pic\IRule实现.png)

#### Ribbon主要常用的负载均衡算法

1.com.netflix.loadbalancer.RoundRobinRule    轮询

2.com.netflix.loadbalancer.RandomRule    随机

3.com.netflix.loadbalancer.RetryRule    先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务

4.WeightedResponseTimeRule    		对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择

5.BestAvailableRule							会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务

6.AvailabilityFilteringRule				先过滤掉故障实例，在选择并发较小的实例

7.ZoneAvoidanceRule					默认规则，复合判断server所在区域的性能和server的可用性选择服务器

#### 如何替换？

官方文档明确给出了警告：

这个自定义配置类不能放在@ComponentScan所扫描的当前包以及子包下，否则我们的自定义配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的了。

简单来说就是不能和boot启动类同包

#### Ribbon默认负载均衡算法原理

##### 负载均衡算法：

公式：

Rest接口第几次请求数%服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始。

```java
List<ServiceInstance> instances = discoveryClient.getInstances("ClOUND-PAYMENT-SERVICE")

instances .get(0) = 127.0.0.1:8001;

instances .get(1) = 127.0.0.1:8002;
```

8001+8002 组合成为集群，它们共计两台机器，集群总数为2，按照轮询算法原理：

当请求总数为1时：1%2 = 1 对应下标位置为1，则获得服务地址127.0.0.1:8002;

当请求总数为2时：2%2 = 0 对应下标位置为0，则获得服务地址127.0.0.1:8001;

当请求总数为3时：3%2 = 1 对应下标位置为1，则获得服务地址127.0.0.1:8002;

当请求总数为4时：4%2 = 0 对应下标位置为0，则获得服务地址127.0.0.1:8001;

以此类推......

