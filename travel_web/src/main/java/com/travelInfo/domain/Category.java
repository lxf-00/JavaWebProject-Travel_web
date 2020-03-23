package com.travelInfo.domain;

import java.io.Serializable;

/**
 * 分类信息 实体类
 */
public class Category implements Serializable {
    private int cid;
    private String cname;

    // 空参构造
    public Category(){}
    // 有参构造
    public Category(String cname) {
        this.cname = cname;
    }

    public Category(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

}
