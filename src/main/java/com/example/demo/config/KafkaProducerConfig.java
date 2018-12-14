package com.example.demo.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wulei
 * @date 2018-11-26 15:32
 * 生产者配置类
 */
/*@Configuration
@EnableKafka*/
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${kafka.producer.retries}")
    private String retries;
    @Value("${kafka.producer.batch.size}")
    private String batchSize;
    @Value("${kafka.producer.linger}")
    private String linger;
    @Value("${kafka.producer.buffer.memory}")
    private String bufferMemory;

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate(producerFactory());
    }

    public ProducerFactory<String,String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    public Map<String,Object> producerConfigs(){
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        map.put(ProducerConfig.RETRIES_CONFIG,retries);
        map.put(ProducerConfig.BATCH_SIZE_CONFIG,batchSize);
        map.put(ProducerConfig.LINGER_MS_CONFIG,linger);
        map.put(ProducerConfig.BUFFER_MEMORY_CONFIG,bufferMemory);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return map;
    }
}
