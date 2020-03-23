package com.travelInfo.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("BasesServlet 方法中的service被调用了");

        //1， 完成方法的分发
        // 1.1 获取请求的路径uri
        String uri = req.getRequestURI();
        System.out.println("获取的uri为：" + uri);  // travel_web/user/add
        // 1.2 截取方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("获取的方法名为：" + methodName);
        // 1.3 获取方法对象的Method
        // this 谁调用我，我代表谁
        // System.out.println(this);
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            try {
                // 执行方法
                // 暴力反射 忽略访问权限的安全检查
                // method.setAccessible(true);
                method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 序列化对象为json格式并写出
     * @param obj
     * @param resp
     */

    public void jsonSerializedAndOut(Object obj, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8;");
        mapper.writeValue(resp.getOutputStream(), obj);
    }

    /**
     * 将object对象序列化为json
     * @param obj
     * @return
     */
    public String jsonSerialized(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
