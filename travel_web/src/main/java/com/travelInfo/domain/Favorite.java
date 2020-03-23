package com.travelInfo.domain;

import java.io.Serializable;

/**
 * 收藏信息 实体类
 */
public class Favorite implements Serializable {
    private int rid;  // 线路id
    private String date; // 收藏日期
    private int uid;   // 用户id

    public Favorite(){}

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
