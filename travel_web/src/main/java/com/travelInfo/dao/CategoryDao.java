package com.travelInfo.dao;

import com.travelInfo.domain.Category;

import java.util.List;

/**
 * 分类信息   数据库操作
 */
public interface CategoryDao {
    /**
     * 查询所有的分类信息
     * @return 分类信息的集合
     */
    List<Category> findAll();
}
