package com.example.weidudianshang.model;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.RegBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class RegModel {

    public void getHttpData(String phone,String pwd){
        ApiService apiService = RetrofitUtils.getInstance().daGet(Api.RegUrl, ApiService.class);
        Flowable<RegBean> register = apiService.register(phone, pwd);
        register.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegBean>() {
                    @Override
                    public void onNext(RegBean regBean) {
                        if (regListener!=null){
                            regListener.onReg(regBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 接口回调
     */
    public interface onRegListener{
        void onReg(RegBean regdata);
    }

    public onRegListener regListener;

    public void setRegListener(onRegListener regListener){
        this.regListener=regListener;
    }
}
