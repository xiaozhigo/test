package com.example.demo.service;

import com.example.demo.dao.TestMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2018-11-19 17:03
 */
@Service
public class HelloService {

    @Autowired
    private TestMapper testMapper;

    public Boolean insertUser(String id, String username,String password) {
        Boolean flag = false;
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("userName",username);
        map.put("password",password);
        map.put("created",new Date());
        map.put("updated",map.get("created"));
        if(testMapper.insert(map) > 0){
            flag = true;
        }
        return flag;
    }

    public PageInfo<Map<String, Object>> queryUserbyId(String id) {
        List<Map<String, Object>> list = testMapper.selectById(id);
        PageHelper.startPage(1,10);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public PageInfo<Map<String,Object>> queryUserByIds(String ids) {
           String[] idArray = ids.split(",");
           PageHelper.startPage(1,10);
           List<Map<String,Object>> list = testMapper.queryUserByIds(idArray);
           PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
           return pageInfo;
    }
}
