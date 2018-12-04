package com.example.demo.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author wulei
 * @date 2018-11-26 16:13
 */
@Component
public class VideoCosConsumer {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"test1"})
    public void consumerMessage(ConsumerRecord<?, ?> record){
        logger.info("on message:{}", record.value());
    }
}
