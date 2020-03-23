package com.travelInfo.service;

import com.travelInfo.domain.Category;

import java.util.List;

/**
 * 分类信息 业务操作
 */
public interface CategoryService {
    /**
     * 分类信息查询
     */
    List<Category> findAll();

}
