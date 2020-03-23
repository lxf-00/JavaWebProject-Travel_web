package com.travelInfo.service.impl;

import com.travelInfo.dao.UserDao;
import com.travelInfo.dao.impl.UserDaoImpl;
import com.travelInfo.domain.User;
import com.travelInfo.service.UserService;
import com.travelInfo.util.MailUtils;
import com.travelInfo.util.UuidUtil;

/**
 * UserService 实现类
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        // 判断用户名是否存在
        User u = dao.findUserByName(user.getUsername());
        if(u != null) {
            // 用户名存在
            return false;
        }
        // 用户名不重复
        // 1，保存用户信息
        // 1.1 设置用户名的激活码，唯一：uuid
        user.setCode(UuidUtil.getUuid());
        // 1.2 设置用户的激活状态
        user.setStatus("N");
        // 1.3 发送激活邮件
        String content = "<a href='http://localhost:8888/travel_web/user/active?code="+user.getCode()+"'>点击激活【travel_web】</a>";
        MailUtils.sendMail(user.getEmail(), content, "travel_web激活邮件");
        dao.save(user);
        return true;
    }

    @Override
    public boolean active(String active_code) {
        // 1，根据激活码查找用户
        User u = dao.findUserByCode(active_code);
        if(u == null){
            // 未查到相关用户,激活失败
            return false;
        }
        // 2， 修改用户激活状态
        dao.updateStatus(active_code);
        return true;
    }

    @Override
    public User login(User user) {
        // 根据用户名和密码查找用户
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
