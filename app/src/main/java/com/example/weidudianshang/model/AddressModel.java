package com.example.weidudianshang.model;

import com.example.weidudianshang.api.Api;
import com.example.weidudianshang.api.ApiService;
import com.example.weidudianshang.bean.AddressBean;
import com.example.weidudianshang.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class AddressModel {

    public void getHttpData(String realName,String phone,String address,String zipCode){
        ApiService apiService = RetrofitUtils.getInstance().daGet2(Api.NewaddressUrl, ApiService.class);
        Flowable<AddressBean> address1 = apiService.address(realName, phone, address, zipCode);
        address1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<AddressBean>() {
                    @Override
                    public void onNext(AddressBean addressBean) {
                        if (addressListener!=null){
                            addressListener.onAddress(addressBean);
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

//努力最坏的结果是失败 但不尝试的结果是无止境的懊悔 怀疑 还有平庸
// 迷茫不可怕说明你还在向前走，失败也不可怕只要你还能爬起来

    /**
     * 接口回调
     */
    public interface onAddressListener{
        void onAddress(AddressBean addressBean);
    }

    onAddressListener addressListener;

    public void setAddressListener(onAddressListener addressListener){
        this.addressListener=addressListener;
    }
}
