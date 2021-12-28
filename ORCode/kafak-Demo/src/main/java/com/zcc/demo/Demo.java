//package com.zcc.demo;
//
//
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.ConsumerConnector;
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//import kafka.producer.KeyedMessage;
//import kafka.producer.Producer;
//import kafka.producer.ProducerConfig;
//import kafka.serializer.StringDecoder;
//import kafka.utils.VerifiableProperties;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author zcc
// * @version 1.0
// * @date 2021/12/20 13:13
// */
//public class Demo {
//
//
//    static  class KafkaProducer {
//
//        private final Producer<String, String> producer;
//        public final static String TOPIC = "TEST-TOPIC";
//
//        private KafkaProducer(){
//            Properties props = new Properties();
//            //此处配置的是kafka的端口
//            props.put("metadata.broker.list", "192.168.18.140:9092");
//
//            //配置value的序列化类
//            props.put("serializer.class", "kafka.serializer.StringEncoder");
//            //配置key的序列化类
//            props.put("key.serializer.class", "kafka.serializer.StringEncoder");
//
//            //request.required.acks
//            //0, which means that the producer never waits for an acknowledgement from the broker (the same behavior as 0.7). This option provides the lowest latency but the weakest durability guarantees (some data will be lost when a server fails).
//            //1, which means that the producer gets an acknowledgement after the leader replica has received the data. This option provides better durability as the client waits until the server acknowledges the request as successful (only messages that were written to the now-dead leader but not yet replicated will be lost).
//            //-1, which means that the producer gets an acknowledgement after all in-sync replicas have received the data. This option provides the best durability, we guarantee that no messages will be lost as long as at least one in sync replica remains.
//            props.put("request.required.acks","-1");
//
//            producer = new Producer<String, String>(new ProducerConfig(props));
//        }
//
//        void produce() {
//            int messageNo = 1000;
//            final int COUNT = 10000;
//
//            while (messageNo < COUNT) {
//                String key = String.valueOf(messageNo);
//                String data = "hello kafka message " + key;
//                producer.send(new KeyedMessage<String, String>(TOPIC, key ,data));
//                System.out.println(data);
//                messageNo ++;
//            }
//        }
//
//        public static void main( String[] args )
//        {
//            new KafkaProducer().produce();
//        }
//    }
//    package kafka;
//
//
//
//    static class KafkaConsumer {
//
//        private final ConsumerConnector consumer;
//
//        private KafkaConsumer() {
//            Properties props = new Properties();
//            //zookeeper 配置
//            props.put("zookeeper.connect", "192.168.18.140:2181");
//
//            //group 代表一个消费组
//            props.put("group.id", "jd-group");
//
//            //zk连接超时
//            props.put("zookeeper.session.timeout.ms", "4000");
//            props.put("zookeeper.sync.time.ms", "200");
//            props.put("auto.commit.interval.ms", "1000");
//            props.put("auto.offset.reset", "smallest");
//            //序列化类
//            props.put("serializer.class", "kafka.serializer.StringEncoder");
//
//            ConsumerConfig config = new ConsumerConfig(props);
//
//            consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
//        }
//
//        void consume() {
//            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//            topicCountMap.put(KafkaProducer.TOPIC, new Integer(1));
//
//            StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
//            StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
//
//            Map<String, List<KafkaStream<String, String>>> consumerMap =
//                    consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
//            KafkaStream<String, String> stream = consumerMap.get(KafkaProducer.TOPIC).get(0);
//            ConsumerIterator<String, String> it = stream.iterator();
//            while (it.hasNext()) {
//                System.out.println(it.next().message());
//            }
//        }
//
//        public static void main(String[] args) {
//            new KafkaConsumer().consume();
//        }
//    }
//
//
//
//}
