package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;


/**
 * @author wulei
 * @date 2019-01-02 10:45
 */
@Repository
public interface TestRepository extends MongoRepository<Map<String,Object>,Long>{
}
