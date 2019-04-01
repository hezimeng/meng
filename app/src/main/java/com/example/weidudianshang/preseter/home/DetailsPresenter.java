package com.example.weidudianshang.preseter.home;

import com.example.weidudianshang.bean.home.DetailsBean;
import com.example.weidudianshang.model.home.DetailsModel;
import com.example.weidudianshang.preseter.BasePresenter;
import com.example.weidudianshang.view.home.DetailsView;

public class DetailsPresenter extends BasePresenter<DetailsView> {

    private DetailsView detailsView;
    private DetailsModel detailsModel;


    public DetailsPresenter(DetailsView view){
        this.detailsView=view;
        detailsModel=new DetailsModel();
    }

    public void onRelated(int commodityId){
        detailsModel.getHttpData(commodityId);
        detailsModel.setDetailsListener(new DetailsModel.onDetailsListener() {
            @Override
            public void onDetails(DetailsBean.ResultBean resultBean) {
                detailsView.getDetailsData(resultBean);
            }
        });
    }
}
