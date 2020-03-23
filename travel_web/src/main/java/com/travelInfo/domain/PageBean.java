package com.travelInfo.domain;

import java.util.List;

/**
 * 分类分页数据  实体类
 */

public class PageBean<T> {
    private int total_page;   // 总页数
    private int total;   // 总记录数
    private List<T> list;  // 每页的总数据集合
    private int current_page;  // 当前页
    private int rows;    // 每页显示的条数

    public PageBean() {
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total_page=" + total_page +
                ", total=" + total +
                ", list=" + list +
                ", current_page=" + current_page +
                ", rows=" + rows +
                '}';
    }
}
