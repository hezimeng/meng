package com.example.weidudianshang.model.shopping;


import android.util.Log;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.shopping.SynchBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class SynchModel {

    public void getHttpData(String data){
        ApiService apiService = RetrofitUtils.getInstance().daGet2(Api.synchUrl, ApiService.class);
        Flowable<SynchBean> synchronization = apiService.synchronization(data);
        synchronization.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SynchBean>() {
                    @Override
                    public void onNext(SynchBean synchBean) {
                        if (synchListener!=null){
                            synchListener.onSynch(synchBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("error", "onError: "+t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }





    /**
     * 接口回调
     */
    public interface onSynchListener{
        void onSynch(SynchBean synchBean);
    }

    onSynchListener synchListener;

    public void setSynchListener(onSynchListener synchListener){
        this.synchListener=synchListener;
    }
}
