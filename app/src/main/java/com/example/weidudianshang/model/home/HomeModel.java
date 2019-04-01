package com.example.weidudianshang.model.home;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.home.HomeBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class HomeModel {

    public void getHttpData(){
        ApiService apiService = RetrofitUtils.getInstance().daGet(Api.HomeFragmentUrl, ApiService.class);
        final Flowable<HomeBean> home = apiService.home();
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeListener!=null){
                            homeListener.onHome(homeBean);
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


    //接口回调
    public interface onHomeListener{
        void onHome(HomeBean homeBean);
    };

    private onHomeListener homeListener;

    public void setHomeListener(onHomeListener homeListener){
        this.homeListener=homeListener;
    }
}
