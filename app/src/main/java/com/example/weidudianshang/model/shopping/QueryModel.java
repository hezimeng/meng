package com.example.weidudianshang.model.shopping;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class QueryModel {

    public void getHttpData(){
        ApiService apiService = RetrofitUtils.getInstance().daGet2(Api.queryUrl, ApiService.class);
        Flowable<QueryBean> querylist = apiService.querylist();

        /**** 下面这个放P层吧 *****/
        querylist.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<QueryBean>() {
                    @Override
                    public void onNext(QueryBean queryBean) {
                        if (queryListener!=null){
                            queryListener.onQuery(queryBean);
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
    public interface onQueryListener{
        void onQuery(QueryBean queryBean);
    }

    onQueryListener queryListener;

    public void setQueryListener(onQueryListener queryListener){
        this.queryListener=queryListener;
    }
}
