package com.example.demo.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wulei
 * @date 2018-11-22 15:59
 */
@Component
public class SpringTaskDemo {

    private static final Logger log = LoggerFactory.getLogger(SpringTaskDemo.class);

    @Async
    @Scheduled(cron = "59 * * * * *")
    public void scheduled1() throws InterruptedException {
         Thread.sleep(1000);
         log.info("scheduled1 每1分钟执行一次：{}"+ LocalDateTime.now());
    }

    @Async
    @Scheduled(fixedRate = 10000)
    public void scheduled2() throws InterruptedException {
        Thread.sleep(10000);
        log.info("scheduled2 每10秒执行一次：{}"+LocalDateTime.now());
    }

    @Async
    @Scheduled(fixedDelay = 15000)
    public void scheduled3() throws InterruptedException {
        Thread.sleep(15000);
        log.info("scheduled3 上次执行完毕后隔15秒继续执行：{}",LocalDateTime.now());
    }
}
