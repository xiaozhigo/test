package com.example.demo.service;

import com.example.demo.dao.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


/**
 * @author wulei
 * @date 2019-01-02 14:11
 */
@Service
public class MongoService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(Map<String,Object> map) {
        testRepository.save(map);
    }

    public List findAll() {
        List<Map<String, Object>> list = testRepository.findAll();
        return list;
    }

    public Map queryById(Integer id) {
        Map map = mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Map.class);
        return map;
    }

    public List<Map> queryByName(String name) {
        List<Map> list = mongoTemplate.find(new Query(Criteria.where("name").is(name)),Map.class);
        return list;

    }

    public void updateUser(Map<String, Object> map) {
        Update update = new Update();
        update.set("name",map.get("name"));
        update.set("age",map.get("age"));
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(map.get("id"))),update,Map.class);
    }

    public void deleteUser(Integer id) {
        mongoTemplate.findAndRemove(new Query(Criteria.where("id").is(id)),Map.class);
    }
}
