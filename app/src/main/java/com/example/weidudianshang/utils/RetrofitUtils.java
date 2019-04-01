package com.example.weidudianshang.utils;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    /**
     * 单例模式
     */
    private static RetrofitUtils retrofitUtils;

    public RetrofitUtils(){

    }

    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }


    /**
     * 请求头的网路工具类
     */
  // 获取OkHttp
    private static OkHttpClient okHttpClient;

    //无请求头
    private static synchronized OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //Log.i("lj","log:"+message);
            }
        });

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(3000,TimeUnit.SECONDS)
                .readTimeout(3000,TimeUnit.SECONDS)
                .build();
        return RetrofitUtils.okHttpClient;
    }

    /**
     * 获取retrofit的方法
     */
    private static Retrofit retrofit;

    public static synchronized Retrofit getRetrofit(String url){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();

        return retrofit;
    }


    /**
     * 动态代理
     */

    public <T> T daGet(String url,Class<T> apiService){
        Retrofit retrofit = getRetrofit(url);
        T t = retrofit.create(apiService);
        return t;
    }

    /**
     * 请求头的网路工具类
     */
    //获取OkHttp
    private static OkHttpClient okHttpClient2;

    private static synchronized OkHttpClient getOkHttpClient1(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //Log.i("lj","log:"+message);
            }
        });

        okHttpClient2 = new OkHttpClient.Builder()
                //添加请求头
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request aarequest = chain.request();
                        Request request = aarequest.newBuilder()
                                .addHeader("userId",SharedPreferencesUtils.getString("userId",""))//获取的时候第二个参数是 默认值
                                .addHeader("sessionId",SharedPreferencesUtils.getString("sessionId",""))
                                .method(aarequest.method(),aarequest.body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(3000,TimeUnit.SECONDS)
                .readTimeout(3000,TimeUnit.SECONDS)
                .build();
        return RetrofitUtils.okHttpClient2;
    }

    /**
     * 获取retrofit的方法
     */
    private static Retrofit retrofit2;

    public static synchronized Retrofit getRetrofit1(String url){
        retrofit2 = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient1())
                .build();

        return retrofit2;
    }


    /**
     * 动态代理
     */

    public <T> T daGet2(String url,Class<T> apiService){
        retrofit2 = getRetrofit1(url);
        T t = retrofit2.create(apiService);
        return t;
    }
}
