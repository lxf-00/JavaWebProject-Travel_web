package com.travelInfo.dao;

import com.travelInfo.domain.Favorite;
import com.travelInfo.domain.Route;
import com.travelInfo.domain.RouteImg;
import com.travelInfo.domain.Seller;

import java.util.List;

/**
 * 线路信息 数据库操作
 */
public interface RouteDao {
    /**
     * 根据cid查询线路数据的总数量
     * @param cid
     * @return
     */
    int findTotal(String cid, String rname);

    /**
     * 查询当前页的全部数据
     * @param current_page
     * @return
     */
    List<Route> findRouteByPage(int index, int rows, String cid, String rname);

    /**
     * 根据rid查询特定线路
     * @param rid
     * @return
     */
    Route findOne(int rid);

    /**
     * 根据rid查询特定卖家
     * @param sid
     * @return
     */
    Seller findSeller(int sid);

    /**
     * 根据rid查询线路图片
     * @param rid
     * @return
     */
    List<RouteImg> findRouteImgs(int rid);

}
