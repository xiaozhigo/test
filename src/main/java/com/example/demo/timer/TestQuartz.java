package com.example.demo.timer;

import com.example.demo.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @author wulei
 * @date 2018-11-22 16:42
 */
public class TestQuartz extends QuartzJobBean {

    @Autowired
    private HelloService helloService;
    /**
     * 执行定时任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(helloService.queryUserbyId(null));
    }
}
