package com.travelInfo.dao;

import com.travelInfo.domain.User;

/**
 * 用户相关数据库操作 接口
 */
public interface UserDao {
    /**
     * 根据用户名查询数据库是否存在相同的用户
     * @param username
     * @return
     */
    public User findUserByName(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     * 根据激活码查询特定用户
     * @param active_code
     * @return
     */
    User findUserByCode(String active_code);

    /**
     * 根据激活码查询用户状态
     * @param active_code
     */
    void updateStatus(String active_code);

    User findUserByUsernameAndPassword(String username, String password);
}
