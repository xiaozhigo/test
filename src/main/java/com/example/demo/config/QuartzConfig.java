package com.example.demo.config;

import com.example.demo.timer.TestQuartz;
import com.example.demo.timer.TestQuartz2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wulei
 * @date 2018-11-22 16:46
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(TestQuartz.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        //SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail()).withIdentity("testQuartz").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail teatQuartzDetail2(){
        return JobBuilder.newJob(TestQuartz2.class).withIdentity("testQuartz2").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2(){
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 */1 * * ?");
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail2()).withIdentity("testQuartz2").withSchedule(scheduleBuilder).build();
    }
}
