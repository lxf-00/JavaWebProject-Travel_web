package com.travelInfo.web.servlet;

import com.travelInfo.domain.Category;
import com.travelInfo.domain.ResultInfo;
import com.travelInfo.service.CategoryService;
import com.travelInfo.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();
    private ResultInfo info = new ResultInfo();

    /**
     * 首页分类信息展示功能
     *
     * @param req
     * @param resp
     */
    public void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1， 调用service方法查询所有的分类信息
        List<Category> categories = service.findAll();
        // 2， 返回数据
        jsonSerializedAndOut(categories, resp);
    }
}
