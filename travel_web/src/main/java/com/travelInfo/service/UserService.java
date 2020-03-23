package com.travelInfo.service;

import com.travelInfo.domain.User;

/**
 * 处理用户请求 接口
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 激活用户
     * @param active_code
     */
    public boolean active(String active_code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
