package com.example.demo.controller;

import com.example.demo.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-02 14:09
 */
@RestController
public class MongoController {

    @Autowired
    private MongoService mongoService;


    @RequestMapping("/saveUser")
    public String saveUser(@RequestBody Map<String,Object> map){
        mongoService.saveUser(map);
        return map.toString();
    }

    @RequestMapping("/pushMap")
    public String pushMap(@RequestBody Map<String,Object> arrays){
         mongoService.pushMap(arrays);
         return arrays.toString();
    }


    @RequestMapping("/findAll")
    public Object findAll(){
        List list = mongoService.findAll();
        return list;
    }


    @RequestMapping("/queryById/{id}")
    public Map<String,Object> queryById(@PathVariable Integer id){
         return mongoService.queryById(id);
    }


    @RequestMapping("/query/{name}")
    public List<Map> queryByName(@PathVariable String name){
         return mongoService.queryByName(name);
    }


    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody Map<String,Object> map){
        mongoService.updateUser(map);
    }


    @RequestMapping("/deleteUser")
    public void deleteUser(@RequestParam Integer id){
        mongoService.deleteUser(id);
    }
}
