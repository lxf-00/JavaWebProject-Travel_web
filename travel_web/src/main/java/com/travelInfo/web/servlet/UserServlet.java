package com.travelInfo.web.servlet;

import com.travelInfo.domain.ResultInfo;
import com.travelInfo.domain.User;
import com.travelInfo.service.UserService;
import com.travelInfo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();
    private ResultInfo info = new ResultInfo();

    /**
     * 用户注册功能
     * @param req
     * @param resp
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 验证码校验
        String check = req.getParameter("check");

        // 获取session中存储的验证码
        HttpSession session = req.getSession();
        String check_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");  // 为了保证验证码只能使用一次

        if(!check.equalsIgnoreCase(check_server)) {
            // 验证不匹配
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
        }else {
            // 1， 获取数据
            Map<String, String[]> map = req.getParameterMap();
            // 2， 封装数据为User对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            info = new ResultInfo();
            // 3， 业务处理：注册
            boolean flag = service.register(user);
            if (flag) {
                // 注册成功
                info.setFlag(true);
            } else {
                // 注册失败
                info.setFlag(false);
                info.setErrorMsg("注册失败！");
            }
        }

        // 4， 将info 序列化为json对象,写回服务器
        jsonSerializedAndOut(info, resp);
       /* resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), info);*/
    }

    /**
     * 用户激活功能
     * @param req
     * @param resp
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1, 获取用户激活码：code
        String active_code = req.getParameter("code");
        // 2, 判断激活码是否存在
        if(active_code == null){
            // 2.1 激活码为空，什么也不做
            resp.getWriter().write("激活码出错了，激活用户失败....");
            return;
        }
        // 2.2 代表激活码发送成功且用户点击了激活邮件
        // 2.2.1 调用UserService active 方法激活用户
        boolean flag = service.active(active_code);
        if(flag) {
            // 2.2.2 激活成功，跳转登录页面
            resp.sendRedirect("/travel_web/login.html");
        }
        // 2.2.3 激活失败，页面提示
        resp.getWriter().write("激活用户失败.....");
    }

    /**
     * 用户登录功能
     * @param req
     * @param resp
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取session中的验证码
        String checkCode_server = (String)req.getSession().getAttribute("CHECKCODE_SERVER");
        // 获取客户端验证码
        String checkCode_client = req.getParameter("check");
//        ResultInfo info = new ResultInfo();
        // 判断验证码是否相同
        if(checkCode_client.equalsIgnoreCase(checkCode_server)) {
            // 验证码通过
            // 1， 获取参数： 用户名 + 密码
            Map<String, String[]> map = req.getParameterMap();
            // 2， 封装为User对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            // 3,  调用service方法，进行业务处理
//            service = new UserServiceImpl();
            User u = service.login(user);   // 传递用户名和密码的用户对象，返回查询后封装后的用户对象
            // 4,  判断处理，返回应答

            // 判断用户是否存在
            if (u == null) {
                // 4.1 用户不存在
                //4.1.1 响应数据给前端
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误！");
            }

            // 判断用户是否激活
            if (u != null && !"Y".equals(u.getStatus())) {
                //4.2.1 用户存在，但未激活
                info.setFlag(false);
                info.setErrorMsg("未激活账户，请先激活！");
            }

            if( u != null && "Y".equals(u.getStatus())) {
                // 4.3说明用户存在且激活
                // 将用户名存入session
                req.getSession().setAttribute("user", u);
                info.setFlag(true);
            }
            // 4.4 序列info为json返回前端
            jsonSerializedAndOut(info, resp);
            // mapper.writeValue(resp.getOutputStream(), info);
        }else{
            info.setFlag(false);
            info.setErrorMsg("验证码不正确");
            jsonSerializedAndOut(info, resp);
//            mapper.writeValue(resp.getOutputStream(), info);
        }
    }

    /**
     * 返回登录用户功能
     * @param req
     * @param resp
     */
    public void getUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object user = req.getSession().getAttribute("user");
        // ObjectMapper mapper = new ObjectMapper();

        /*resp.setContentType("application/json;charset=utf-8;");
        mapper.writeValue(resp.getOutputStream(), user);*/
        jsonSerializedAndOut(user, resp);
    }

    /**
     * 注销用户功能
     * @param req
     * @param resp
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/travel_web/index.html");
    }
}
