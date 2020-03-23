package com.travelInfo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码，处理所有请求
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        // 将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;

        // 获取请求的方法
        String method = request.getMethod();
        // 解决post请求中文乱码的问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }

        // 处理响应乱码的问题
        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(request, response);
    }
    @Override
    public void destroy(){

    }
}
