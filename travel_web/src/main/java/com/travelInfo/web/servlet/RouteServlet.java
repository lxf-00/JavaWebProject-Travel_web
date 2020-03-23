package com.travelInfo.web.servlet;

import com.travelInfo.domain.*;
import com.travelInfo.service.FavoriteService;
import com.travelInfo.service.RouteService;
import com.travelInfo.service.impl.FavoriteServiceImpl;
import com.travelInfo.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{
    private RouteService service = new RouteServiceImpl();
    private FavoriteService fservice = new FavoriteServiceImpl();

    /**
     * 分页分类展示线路信息功能
     * @param req
     * @param resp
     */
    public void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1，获取参数： cid(类别名称）  current_page(当前页面)  rname: 搜索标题
        String cid = req.getParameter("cid");
        String current_page = req.getParameter("current_page");
        String rname = req.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        System.out.println("获取到的参数：cid:" + cid + "； current_page:" + current_page + "; rname:" + rname);
        // 2， 校验参数
        // 如果没有携带当前页码，默认请求第一页
        if(current_page == null || current_page.length() == 0 || Integer.parseInt(current_page) <= 0){
            current_page = "1";
        }

        // 调用service的findRouteByPage()
        PageBean<Route> pb = service.findRouteByPage(cid, current_page, rname);
        System.out.println(pb);
        // 序列化输出
        jsonSerializedAndOut(pb, resp);
    }

    /**
     * 线路详情页展示功能
     * @param req
     * @param resp
     */
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1，获取参数
        // 查询登录用户
        System.out.println("用户："+ req.getSession().getAttribute("user"));
        int rid = Integer.parseInt(req.getParameter("rid"));
        System.out.println("/route/detail获取到的线路id为：" + rid);
        // 2, 调用service方法，查询数据
        Route details = service.findRouteDetails(rid);
        System.out.println(details);
        // 3， 返回数据
        jsonSerializedAndOut(details, resp);
    }

    /**
     * 收藏标识页面显示
     * @param req
     * @param resp
     */
    public void isFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1， 获取用户： 判断用户是否登录
        User user = (User) req.getSession().getAttribute("user");
        int uid = 0;
        ResultInfo info = new ResultInfo();
        if (user == null) {
            // 1.1.1 未登录状态直接显示灰色
            info.setFlag(false);
            info.setErrorMsg("未登录，请先登录.....");
            // 1.1.2 直接返回： info;
            System.out.println("用户未登录（route/favorite)");
        } else {
            // 1.2 登录状态中
            // 1.2.1 获取参数： rid 线路id 和用户
            int rid = Integer.parseInt(req.getParameter("rid"));
            uid = user.getUid();
            // 2， 调用方法完成查询
            Favorite favorite = fservice.findByUidAndRid(uid, rid);
            // 3， 返回应答
            if (favorite != null) {
                // 有收藏过
                info.setFlag(true);
            } else {
                // 未收藏过
                info.setFlag(false);
            }
        }
        jsonSerializedAndOut(info, resp);
    }

    /**
     * 添加收藏
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ResultInfo info = new ResultInfo();
        // 1,获取登录的用户
        User user = (User) req.getSession().getAttribute("user");
        // 2, 判读用户是否登录
        if(user == null){
            // 2.1 用户未登录
            info.setFlag(false);
            info.setErrorMsg("未登录，请先登录。。。");
        }else {
            // 2.2 用户登录
            // 2.2.1 获取线路 和用户id
            int uid = user.getUid();
            int rid = Integer.parseInt(req.getParameter("rid"));
            // 2.2.2 调用fservice方法进行操作
            Boolean flag = fservice.addFavorite(uid, rid);
            if(flag){
                // 添加成功
                info.setFlag(flag);
                info.setErrorMsg("收藏成功!");
            }else{
                // 添加失败
                info.setFlag(flag);
                info.setErrorMsg("添加失败!");
            }
        }
        jsonSerializedAndOut(info, resp);

    }

}
