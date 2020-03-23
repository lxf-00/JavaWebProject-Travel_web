package com.travelInfo.service.impl;


import com.travelInfo.dao.CategoryDao;
import com.travelInfo.dao.impl.CategoryDaoImp;
import com.travelInfo.domain.Category;
import com.travelInfo.service.CategoryService;
import com.travelInfo.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImp();

    @Override
    public List<Category> findAll() {
        // 从redis中查询数据:hash
        String cate_key = "cate_name";
        Jedis jedis = JedisPoolUtils.getJedis();

        // Set<String> categories = jedis.zrange(cate_key, 0, -1);
        Set<Tuple> categories = jedis.zrangeWithScores(cate_key, 0, -1);
        List<Category> list = null;
        // 判断数据是否存在
        if(categories == null || categories.size() == 0){
            System.out.println("2");
            // redis没有相关数据
            // 从数据中获取
            list = dao.findAll();
            // 将数据存储到redis中
            for(int i = 0; i < list.size(); i++){
                jedis.zadd(cate_key, list.get(i).getCid(), list.get(i).getCname());
            }
        }else{
            list = new ArrayList<Category>();
            for(Tuple t: categories){
                Category category = new Category();
                category.setCname(t.getElement());
                category.setCid((int) t.getScore());
                list.add(category);
            }
        }
        // 返回数据
        return  list;
    }

}
