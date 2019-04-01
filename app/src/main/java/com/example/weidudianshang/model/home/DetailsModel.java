package com.example.weidudianshang.model.home;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.home.DetailsBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class DetailsModel {

    public void getHttpData(int commodityId){
        ApiService apiService = RetrofitUtils.getInstance().daGet(Api.DetailsUrl, ApiService.class);
        Flowable<DetailsBean> details = apiService.Details(commodityId);
        details.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<DetailsBean>() {
                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        DetailsBean.ResultBean result = detailsBean.getResult();
                        if (detailsListener!=null){
                            detailsListener.onDetails(result);
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
     * 定义接口
     */
    public interface onDetailsListener{
        void onDetails(DetailsBean.ResultBean resultBean);
    }

    onDetailsListener detailsListener;

    public void setDetailsListener(onDetailsListener detailsListener){
        this.detailsListener=detailsListener;
    }
}
