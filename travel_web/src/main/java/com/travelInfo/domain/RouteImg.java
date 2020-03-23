package com.travelInfo.domain;

import java.io.Serializable;

/**
 * 线路图片实体类
 */
public class RouteImg implements Serializable {
    private int rgid;   // 线路图片id
    private int rid;   // 线路id
    private String bigPic;  // 详情大图
    private String smallPic;  // 详情小图

    public RouteImg(){}

    public RouteImg(int rgid, int rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
