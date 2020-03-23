package com.travelInfo.dao.impl;

import com.travelInfo.dao.FavoriteDao;
import com.travelInfo.domain.Favorite;
import com.travelInfo.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByUidAndRid(int uid, int rid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where uid=? and rid=?;";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, rid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int addFavorite(int uid, int rid) {
        String sql = "insert into tab_favorite(uid,date,rid) values(?,?,?);";
        int count = template.update(sql, uid, new Date(), rid);
        return count;
    }

    @Override
    public int findTotal(int rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        Integer count = template.queryForObject(sql, Integer.class, rid);
        return count;
    }
}
