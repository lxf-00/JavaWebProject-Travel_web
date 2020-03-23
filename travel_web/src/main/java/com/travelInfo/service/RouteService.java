package com.travelInfo.service;

import com.travelInfo.domain.Favorite;
import com.travelInfo.domain.PageBean;
import com.travelInfo.domain.Route;

import java.util.List;

/**
 * 线路信息  业务处理
 */
public interface RouteService {
    /**
     * 线路分类分页展示
     * @param cid
     * @param current_page
     * @param rname
     * @return
     */
    PageBean<Route> findRouteByPage(String cid, String current_page, String rname);

    /**
     * 线路详情
     * @param rid
     * @return
     */
    Route findRouteDetails(int rid);
}
