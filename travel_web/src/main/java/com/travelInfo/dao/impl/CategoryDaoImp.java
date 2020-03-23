package com.travelInfo.dao.impl;

import com.travelInfo.dao.CategoryDao;
import com.travelInfo.domain.Category;
import com.travelInfo.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * CategoryDao 实现类
 */
public class CategoryDaoImp implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category order by cid;";
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

        return list;
    }
}
