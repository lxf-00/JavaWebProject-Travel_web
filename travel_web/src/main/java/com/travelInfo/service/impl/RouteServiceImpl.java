package com.travelInfo.service.impl;

import com.travelInfo.dao.FavoriteDao;
import com.travelInfo.dao.RouteDao;
import com.travelInfo.dao.impl.FavoriteDaoImpl;
import com.travelInfo.dao.impl.RouteDaoImpl;
import com.travelInfo.domain.*;
import com.travelInfo.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();
    private FavoriteDao fdao = new FavoriteDaoImpl();
    private static int rows = 5;
    @Override
    public PageBean<Route> findRouteByPage(String cid, String current_page, String rname) {
        PageBean<Route> pb = new PageBean<>();
        // 1， 查询该种类的总数目
        int total = dao.findTotal(cid, rname);
        pb.setTotal(total);
        // 2， 确定总页数：每页默认显示10
        int total_page = 0;
        if(total % rows == 0){
            total_page = total / rows;
        }else{
            total_page = total / rows + 1;
        }
        pb.setTotal_page(total_page);
        // 3,  查询该页的所有的数据集合
        int i = Integer.parseInt(current_page);
        int index = (i - 1) * rows;
        List<Route> list = dao.findRouteByPage(index, rows, cid, rname);
        pb.setList(list);
        pb.setCurrent_page(i);
        pb.setRows(rows);
        // 4,  封装为PageBean类返回
        return pb;
    }

    @Override
    public Route findRouteDetails(int rid) {
        // 1， 根据rid查询相应线路
        Route routeDetail = dao.findOne(rid);

        // 2, 根据sid查询相应卖家
        Seller seller = dao.findSeller(routeDetail.getSid());
        // 3, 根据查询线路图片
        List<RouteImg> routeImg = dao.findRouteImgs(rid);
        // 4，封装Route对象
        routeDetail.setSeller(seller);
        routeDetail.setRouteImgList(routeImg);
        // 5, 查询收藏总数
        int count = fdao.findTotal(rid);
        routeDetail.setCount(count);
        // 6， 返回Route对象
        return routeDetail;
    }

}
