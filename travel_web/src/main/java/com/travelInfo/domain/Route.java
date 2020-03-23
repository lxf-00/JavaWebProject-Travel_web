package com.travelInfo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 旅游线路实体类
 */
public class Route implements Serializable {
    private int rid;   // 线路id
    private String rname;  // 线路名称
    private double price;   // 价格
    private String routeIntroduce;  // 线路介绍
    private String rflag;   // 是否上架：0 未上架  1 上架
    private String rdate;   // 上架时间
    private String isThemeTour;  // 是否主体旅游：0 否  1 是
    private int count;   // 收藏数量
    private int cid;    // 所属分类
    private  String rimage;  // 缩略图
    private int sid;   // 所属商家
    private int soureceId;  // 抓取数据的来源id

    private Category category;//所属分类

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    private Seller seller;//所属商家
    private List<RouteImg> routeImgList;//商品详情图片列表

    public Route(){}

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSoureceId() {
        return soureceId;
    }

    public void setSoureceId(int soureceId) {
        this.soureceId = soureceId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Route{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", price=" + price +
                ", routeIntroduce='" + routeIntroduce + '\'' +
                ", rflag='" + rflag + '\'' +
                ", rdate='" + rdate + '\'' +
                ", isThemeTour='" + isThemeTour + '\'' +
                ", count=" + count +
                ", cid=" + cid +
                ", rimage='" + rimage + '\'' +
                ", sid=" + sid +
                ", soureceId=" + soureceId +
                ", category=" + category +
                ", seller=" + seller +
                ", routeImgList=" + routeImgList +
                '}';
    }
}
