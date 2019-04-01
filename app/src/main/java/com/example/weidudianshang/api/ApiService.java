package com.example.weidudianshang.api;

import com.example.weidudianshang.bean.AddressBean;
import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.bean.shopping.SynchBean;
import com.example.weidudianshang.bean.home.DetailsBean;
import com.example.weidudianshang.bean.home.HomeBannerBean;
import com.example.weidudianshang.bean.home.HomeBean;
import com.example.weidudianshang.bean.LogBean;
import com.example.weidudianshang.bean.RegBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
    //注册
    @POST("register")
    @FormUrlEncoded
    Flowable<RegBean> register(@Field("phone") String phone,@Field("pwd") String pwd);

    //登录
    @POST("login")
    @FormUrlEncoded
    Flowable<LogBean> login(@Field("phone") String phone, @Field("pwd") String pwd);

    //首页三级列表的接口http://172.17.8.100/small/commodity/v1/commodityList
    @GET("commodityList")
    Flowable<HomeBean> home();

    //首页轮播图的接口http://172.17.8.100/small/commodity/v1/bannerShow
    @GET("bannerShow")
    Flowable<HomeBannerBean> banner();

    //商品详情的接口http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=13
    @GET("findCommodityDetailsById")
    Flowable<DetailsBean> Details(@Query("commodityId") int commodityId);

    //同步购物车http://172.17.8.100/small/order/verify/v1/syncShoppingCart
    @PUT("syncShoppingCart")
    @FormUrlEncoded
    Flowable<SynchBean> synchronization(@Field("data") String data);

    //查询购物车http://172.17.8.100/small/order/verify/v1/findShoppingCart
    @GET("findShoppingCart")
    Flowable<QueryBean> querylist();

    //新增收货地址http://172.17.8.100/small/user/verify/v1/addReceiveAddress
    @FormUrlEncoded
    @POST("addReceiveAddress")
    Flowable<AddressBean> address(@Field("realName")String realName,@Field("phone")String phone,@Field("address")String address,@Field("zipCode")String zipCode);


}
