package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2018-11-15 16:09
 */
@Mapper
public interface TestMapper {

    Integer insert(Map<String,Object> map);
    Integer deleteById(String id);
    Integer updateById(Map<String,Object> map);
    List<Map<String,Object>> selectById(String id);
    List<Map<String,Object>> queryUserByIds(String[] idArray);
    List<Map<String,Object>> queryItem();
    void insertItem(Map<String, Object> hashMap);
}
