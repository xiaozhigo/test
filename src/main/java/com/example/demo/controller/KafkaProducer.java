package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wulei
 * @date 2018-11-26 16:10
 */
@Component
public class KafkaProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String message){
       logger.info("message ++ on:",message);
       kafkaTemplate.send("test",message);
    }
}
