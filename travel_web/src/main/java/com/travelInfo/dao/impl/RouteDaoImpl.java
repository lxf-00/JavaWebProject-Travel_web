package com.travelInfo.dao.impl;

import com.travelInfo.dao.RouteDao;
import com.travelInfo.domain.Favorite;
import com.travelInfo.domain.Route;
import com.travelInfo.domain.RouteImg;
import com.travelInfo.domain.Seller;
import com.travelInfo.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    
    @Override
    public int findTotal(String cid, String rname) {
        String sql = "select count(*) from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        // 校验参数：
        List params = new ArrayList();
        if(!cid.equals("null") && cid.length() > 0 && cid != null){
            // 有传递路径id,保存进list
            params.add(cid);
            sb.append(" and cid= ?");
        }

        if(rname != null && rname.length() > 0 && !rname.equals("null")){
            // 有传递搜索标题，存进list
            params.add("%"+rname+"%");
            sb.append(" and rname like ?");
        }
        sb.append(";");
        Integer total = template.queryForObject(sb.toString(), Integer.class, params.toArray());
        System.out.println("dao中sql1:" + sb.toString() );
        System.out.println("dao中查询到总数:" + total);
        return total;
    }


    @Override
    public List<Route> findRouteByPage(int index, int rows, String cid, String rname) {
        String sql = "select * from tab_route where  1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        // 判断参数
        if(cid != null && cid.length() > 0 && !cid.equals("null")){
            params.add(cid);
            sb.append(" and cid=?");
        }

        if(rname != null && rname.length() > 0 && !rname.equals("null")){
            params.add("%"+rname+"%");
            sb.append(" and rname like ?");
        }
        sb.append(" limit ?,?;");
        params.add(index);
        params.add(rows);
        List<Route> list = template.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        System.out.println("dao中sql2:" + sb.toString() );
        System.out.println("道中route总数:" + list.size());
        return list;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid=?;";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }

    @Override
    public Seller findSeller(int sid) {
        String sql = "select * from tab_seller where sid=?;";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
    }

    @Override
    public List<RouteImg> findRouteImgs(int rid) {
        String sql = "select * from tab_route_img where rid=?;";
        return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
    }

}
