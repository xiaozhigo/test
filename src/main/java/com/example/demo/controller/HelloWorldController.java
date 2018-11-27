package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Groups;
import com.example.demo.exception.CustomException;
import com.example.demo.service.HelloService;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author wulei
 * @date 2018-11-15 15:30
 */
@Validated
@RestController
public class HelloWorldController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/login")
    public String login(){
        return "HelloWorld";
    }

    @RequestMapping("/insert")
    public Boolean insertUser(@RequestParam String id,@RequestParam String username,@RequestParam String password){
           return helloService.insertUser(id,username,password);
    }

    @RequestMapping("/query")
    public PageInfo<Map<String, Object>> queryUser(@RequestParam String id){
        return helloService.queryUserbyId(id);
    }

    @RequestMapping("/queryUserByIds")
    public PageInfo<Map<String,Object>> queryUserByIds(@RequestParam String ids){
           return helloService.queryUserByIds(ids);
    }
    @RequestMapping("/redisTest")
    public void redisTest(@RequestParam String id){
        stringRedisTemplate.opsForValue().set("k1","(。-ω-)zzz");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        System.out.println(id+":"+k1);
    }

    @RequestMapping("/exceptionTest")
    @ResponseBody
    public String ExceptionTest(@RequestParam Integer count){
           if(count == null){
              throw new CustomException("id不能为空",400);
           }
           int num = 10/count;
           return "result:"+num;
    }

    @RequestMapping("/test")
    public PageInfo<Map<String, Object>> test(@NotNull(message = "id不能为空") @Length(min = 1,max = 3,message = "id长度只能在{min}-{max}之间") String id){
        return helloService.queryUserbyId(id);
    }

    @GetMapping("/insert")
    public String insert(@Validated(value = Groups.Default.class) Book book) {
        return "insert";
    }


    @GetMapping("/update")
    public String update(@Validated(value = {Groups.Default.class,Groups.Update.class}) Book book) {
        return "update";
    }
}
