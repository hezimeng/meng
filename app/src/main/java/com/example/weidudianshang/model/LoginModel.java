package com.example.weidudianshang.model;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.LogBean;
import com.example.weidudianshang.bean.RegBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class LoginModel {

    public void getHttpData(String phone,String pwd){
        ApiService apiService = RetrofitUtils.getInstance().daGet(Api.LogUrl, ApiService.class);
        Flowable<LogBean> login = apiService.login(phone, pwd);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<LogBean>() {
                    @Override
                    public void onNext(LogBean logBean) {
                        if (loginListener!=null){
                            loginListener.onReg(logBean);
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
    public interface onLoginListener{
        void onReg(LogBean logBean);
    }

    public onLoginListener loginListener;

    public void setRegListener(onLoginListener loginListener){
        this.loginListener=loginListener;
    }
}
