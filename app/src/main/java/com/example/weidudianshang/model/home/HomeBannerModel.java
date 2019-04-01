package com.example.weidudianshang.model.home;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.home.HomeBannerBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class HomeBannerModel {

    public void getHttpData(){
        ApiService apiService = RetrofitUtils.getInstance().daGet(Api.XBanner_Api, ApiService.class);
        final Flowable<HomeBannerBean> banner = apiService.banner();
        banner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<HomeBannerBean>() {
                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        if (homeBannerListener!=null){
                            homeBannerListener.onHomeBanner(homeBannerBean);
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
    public interface onHomeBannerListener{
        void onHomeBanner(HomeBannerBean homeBannerBean);
    };

    private onHomeBannerListener homeBannerListener;

    public void setHomeBannerListener(onHomeBannerListener homeBannerListener){
        this.homeBannerListener=homeBannerListener;
    }
}
