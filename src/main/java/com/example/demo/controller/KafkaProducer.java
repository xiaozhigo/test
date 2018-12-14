package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author wulei
 * @date 2018-11-26 16:10
 */
/*@Component*/
@RestController
public class KafkaProducer{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/send")
    public Map<String, Object> send(String message){
        Map<String, Object> map = new HashMap<>();
        logger.info("message:",message);
        ListenableFuture test = kafkaTemplate.send("test1",message);
        SuccessCallback success = new SuccessCallback() {
            @Override
            public void onSuccess(Object o) {
                System.out.println(o + "成功了");
                map.put("resultDesc","成功了");
            }
        };
        FailureCallback fail = new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("失败原因:" + throwable);
                map.put("resultDesc","失败原因:" + throwable);
            }
        };
        test.addCallback(success,fail);
        return map;
    }

}
