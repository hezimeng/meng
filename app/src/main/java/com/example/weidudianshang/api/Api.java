package com.example.weidudianshang.api;

public class Api {

    public static final boolean isIntranet = false;

    public static final String mIntranet = "http://172.17.8.100/";

    public static final String mNotIntranet = "http://mobile.bwstudent.com/";

    public static final String mBaseUrl = isIntranet ? mIntranet : mNotIntranet;

    //注册接口http://172.17.8.100/small/user/v1/register
    public static final String RegUrl = mBaseUrl + "small/user/v1/";
    //登录接口http://172.17.8.100/small/user/v1/login
    public static final String LogUrl = mBaseUrl + "small/user/v1/";
    //首页三级列表的接口http://172.17.8.100/small/commodity/v1/commodityList
    public static final String HomeFragmentUrl = mBaseUrl + "small/commodity/v1/";
    //首页轮播图的接口http://172.17.8.100/small/commodity/v1/bannerShow
    public static final String XBanner_Api = mBaseUrl + "small/commodity/v1/";

    //商品详情的接口http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=13
    public static final String DetailsUrl = mBaseUrl + "small/commodity/v1/";

    //同步购物车http://172.17.8.100/small/order/verify/v1/syncShoppingCart
    public static final String synchUrl = mBaseUrl + "small/order/verify/v1/";

    //查询购物车http://172.17.8.100/small/order/verify/v1/findShoppingCart
    public static final String queryUrl = mBaseUrl + "small/order/verify/v1/";

    //新增收货地址http://172.17.8.100/small/user/verify/v1/addReceiveAddress
    public static final String NewaddressUrl=mBaseUrl+"small/user/verify/v1/";

}
