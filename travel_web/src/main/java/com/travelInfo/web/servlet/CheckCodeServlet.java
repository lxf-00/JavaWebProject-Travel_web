package com.travelInfo.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 */
// @WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1， 服务器通知浏览器不要缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setHeader("expires", "0");

        // 2, 在内存中创建长 80 宽 30 的图片， 默认黑色背景
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 3, 获取画笔
        Graphics g = image.getGraphics();
        // 4, 设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        // 5，填充图片
        g.fillRect(0, 0, width, height);

        // 6，生成4个随机验证码
        String checkCode = getChecCode();
        // 7, 将验证码放入HttpSession中
        req.getSession().setAttribute("CHECKCODE_SERVER", checkCode);
        // 8, 设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        // 9， 设置字体大小
        g.setFont(new Font("黑体", Font.BOLD, 24));
        // 10, 向图片上写验证码
        g.drawString(checkCode, 10, 25);

        // 11, 将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", resp.getOutputStream());
    }

    /**
     * 生成四个随机验证码
     * @return
     */
    private static String getChecCode(){
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdfghjklmzqwertyuio1234567890";
        int size = base.length();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            // 随机坐标
            int index = r.nextInt(size);
            sb.append(base.charAt(index));
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
