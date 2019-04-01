package com.example.weidudianshang.preseter;

import com.example.weidudianshang.bean.RegBean;
import com.example.weidudianshang.model.RegModel;
import com.example.weidudianshang.preseter.BasePresenter;
import com.example.weidudianshang.view.RegView;

public class RegPresenter extends BasePresenter<RegView> {

    private final RegView regView;
    private final RegModel regModel;

    public RegPresenter(RegView view){
        this.regView=view;
        regModel=new RegModel();
    }

    public void onRelated(String phone,String pwd){
        regModel.getHttpData(phone,pwd);
        regModel.setRegListener(new RegModel.onRegListener() {
            @Override
            public void onReg(RegBean regdata) {
                regView.getRegData(regdata);
            }
        });

    }

}
