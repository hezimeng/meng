package com.example.weidudianshang.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.weidudianshang.app.MyApp;

/**
 * @项目名称: privatebridge
 * @类描述: name：SharedPreferences 工具类
 * @创建人: Administrator
 * @创建时间: 2018/7/17 上午 10:35
 * @修改时间: 上午 10:35
 * @describe
 */
public class SharedPreferencesUtils {

    private static SharedPreferences sp;

    /**
     * 保存boolean信息 这样就好了，这样的话可以方便用
     */
    public static void saveBoolean(String key, boolean value) {
        //name : 保存信息的xml文件的名秿
        //mode : 读写权限
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        //保存操作
        sp.edit().putBoolean(key, value).apply();

    }

    /**
     * 获取保存的boolean值
     */
    public static boolean getBoolean(String key, boolean defValue) {
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    /**
     * 删除保存的值
     */
    public static void remove(String key) {
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).apply();
    }

    /**
     * 清空保存的值
     */
    public static void clear() {
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        sp.edit().clear().apply();
    }


    /**
     * 保存String信息
     */
    public static void saveString(String key, String value) {

        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    /***
     * 保存int信息
     */
    public static void saveInt(String key, int value) {
        //name : 保存信息的xml文件的名秿
        //mode : 读写权限
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        //保存操作
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 获取保存的int倿
     */
    public static int getInt(String key, int defValue) {
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

    /**
     * 保存long信息
     */
    public static void saveLong(String key, long value) {

        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        sp.edit().putLong(key, value).apply();
    }

    /**
     * 获取long信息
     */
    public static long getLong(String key, long defValue) {
        if (sp == null) {
            sp = MyApp.context.getSharedPreferences("deng", Context.MODE_PRIVATE);
        }
        return sp.getLong(key, defValue);
    }
}
