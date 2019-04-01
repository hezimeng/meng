package com.example.weidudianshang.preseter.shopping;

import com.example.weidudianshang.bean.shopping.SynchBean;
import com.example.weidudianshang.model.shopping.SynchModel;
import com.example.weidudianshang.preseter.BasePresenter;
import com.example.weidudianshang.view.shopping.SynchView;

public class SynchPresenter extends BasePresenter<SynchView> {

    private SynchView synchView;
    private SynchModel synchModel;

    public SynchPresenter(SynchView view){
        this.synchView=view;
        synchModel=new SynchModel();
    }

    public void onRelated(String data){
        synchModel.getHttpData(data);
        synchModel.setSynchListener(new SynchModel.onSynchListener() {
            @Override
            public void onSynch(SynchBean synchBean) {
                synchView.getSynchData(synchBean);
            }
        });
    }


}
