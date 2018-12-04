package com.example.demo.timer;

import com.example.demo.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2018-12-03 14:44
 */
public class TestQuartz2 extends QuartzJobBean {

    @Autowired
    private HelloService helloService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Object> map = helloService.queryItem();
        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("resultDesc");
        for(int i = 0;i < list.size();i++){
            Map<String, Object> itemMap = list.get(i);
            Long id = (Long) itemMap.get("id");
            if (id == 42){
                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("title","苹果XMax");
                hashMap.put("sell_point","贵");
                hashMap.put("price",1099900);
                hashMap.put("num",1);
                hashMap.put("barcode",1111);
                hashMap.put("image",null);
                hashMap.put("cid",560);
                hashMap.put("status",1);
                hashMap.put("created",new Date());
                hashMap.put("updated",hashMap.get("created"));
                helloService.insertItem(hashMap);
                System.out.println("添加item成功!");
            }
        }
    }
}
