package com.travelInfo.dao.impl;

import com.travelInfo.dao.UserDao;
import com.travelInfo.domain.User;
import com.travelInfo.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 实现类 -- 用户相关数据库操作
 */
public class UserDaoImpl implements UserDao {
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByName(String username) {
        User user = null;
        try {
            // 定义sql语句
            String sql = "select * from tab_user where username = ?;";
            // 查询，封装
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){

        }
        return user;
    }

    @Override
    public void save(User user) {
        // 定义sql
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, code, status) values" +
                "(?,?,?,?,?,?,?,?,?);";

        // 执行sql
        template.update(sql, user.getUsername(), user.getPassword(),
                user.getName(), user.getBirthday(), user.getSex(),
                user.getTelephone(), user.getEmail(), user.getCode(), user.getStatus());


    }

    @Override
    public User findUserByCode(String active_code) {
        User user = null;
        try {
            // 定义sql语句
            String sql = "select * from tab_user where code = ?;";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), active_code);
        }catch (Exception e){

        }
        return user;
    }

    @Override
    public void updateStatus(String active_code) {
        String sql = "update tab_user set status='Y' where code=?";
        template.update(sql, active_code);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=? and password=?;";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception e){

        }
        return user;
    }
}
